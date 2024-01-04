var schedules;
$(document).ready(function () {
    initSchGraClaData();
    $("#selectBtn").click(function () {
        loadSchool();

        layer.open({
            type: 1,
            title: '查询学生编号',
            area: ['800px', '580px'],
            shade: [0.5, '#000', false],
            scrollbar: false,
            btn: ['确定', '取消'],
            content: $("#addSubject"),
            yes: function (idx, layero) {
                selectJob(idx, layero);
            }
        });
    });


});

//获取学段列表
function loadSchool() {
    $.ajax({
        type: 'post',
        async: false,
        url:  '/schedules/userschool',
        data: [],
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                schedules = result.data;
                //添加年级信息
                if (schedules != null && schedules.length > 0) {
                    //添加学校信息，只有一个
                    var items = [];
                    items.push('<label><input type="radio" name="schoolID" class="radio" value="' + schedules[0].phasestudy + '"><i>' + schedules[0].phasestudy + '</i></label>');
                    $("#subject").html(items.join(''));
                    $('input[name="schoolID"]').eq(0).prop("checked", true);

                    var items2 = [];
                    var grades = [];


                    for (var i = 0; i < schedules.length; i++) {
                        var boo=false;
                        var item = schedules[i];
                        for(var j=0;j<grades.length;j++){
                            if(grades[j]==item.gradeid){
                                boo=true;
                                break;
                            }
                        }
                        if(boo==false){
                            var ll=grades.length;
                            grades[ll]=item.gradeid;
                            items2.push('<label><input type="radio" name="gradeId" class="radio" value="' + item.gradeid + '"><i>' + item.gradeid + '</i></label>');
                        }
                    }
                    $("#grade").html(items2.join(''));

                    $('input[name="gradeId"]').eq(0).prop("checked", true);
                    var gradeId = $('input[name="gradeId"]:checked').val();
                    loadClazz(gradeId);

                    $('input[name="gradeId"]').change(function () {
                        var gradeId = $('input[name="gradeId"]:checked').val();
                        loadClazz(gradeId);
                    });

                }


            } else {
                layer.msg(result.msg, {icon: 2});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
        }
    });
}

function loadClazz(gradeId) {
    $("#clazz").empty();
    if(gradeId!=null){
        var  items=[];
        for(var i=0;i<schedules.length;i++){

            if(gradeId==schedules[i].gradeid){
                var item = schedules[i];
                items.push('<label><input type="radio" name="clazzId" class="radio" value="' + item.classid + '"><i>' + item.subjectid + '</i></label>');
            }
        }
        $("#clazz").html(items.join(''));
        $('input[name="clazzId"]').eq(0).prop("checked", true);
    }


}

//添加任教科目
function selectJob(idx, layero) {
    // var phaseCode = $('input[name="phaseId"]:checked').val();
    // var subjectId = $('input[name="subjectId"]:checked').val();
    // //校验参数
    // if (phaseCode == null || phaseCode == "") {
    //     layer.tips('请选择学段', '#phase', {time: 1000});
    //     return false;
    // }
    // if (subjectId == null || subjectId == "") {
    //     layer.tips('请选择科目', '#subject', {time: 1000});
    //     return false;
    // }
    //保存数据
    layer.close(idx);//关闭弹出层
    initSchGraClaData();

    // $.ajax({
    //     type: 'post',
    //     url:  '/schedules/classnumlist',
    //     // data:{'phaseCode':phaseCode, 'subjectId':subjectId},
    //     dataType: 'JSON',
    //     success: function (result) {
    //         if (result.code == "200") {
    //             layer.close(idx);//关闭弹出层
    //             layer.msg(result.msg, {icon: 1, time: 2000}, function () {
    //             	initSchGraClaData();
    //             });
    //         } else {
    //             layer.msg(result.msg, {icon: 2, time: 3000}, function () {
    //                 $(layero).find('.layui-layer-btn0').removeAttr("style");
    //
    //             });
    //         }
    //     },
    //     error: function () {
    //         layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000}, function () {
    //             $(layero).find('.layui-layer-btn0').removeAttr("style");
    //         });
    //     }
    // });
}

/**
 * 获取已经创建的科目列表
 * @returns
 */
function initSchGraClaData() {
    //校验参数
    var gradeid = $('input[name="gradeId"]:checked').val();
    var classid = $('input[name="clazzId"]:checked').val();
    var params=null;
    $.ajax({
        type: 'post',
        url:  '/schedules/classnumlist',
        data: {"gradeid":gradeid,"classid":classid},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                appendData(result);
            } else {
                layer.msg(result.msg, {icon: 2});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
        }
    });
}
//拼装任教科目数据
function appendData(result) {
    var html = '';
    if (result.rows != null && result.rows.length > 0) {
        var html = '<table >'
        //html += '<tr ><th class="bs-checkbox "><input data-index="0" name="btSelectAll" type="checkbox"></th>'
        html += '<th >编号</th>'
        html +='<th >用户</th>'
        html +='<th >学生编号</th>'
        html +='<th >学校</th>'
        html +='<th >年级</th>'
        html +='<th >班级</th></tr >'
        $.each(result.rows, function (idx, t) {
            var abb=1+idx;
            //html += '<tr ><td class="bs-checkbox "><input data-index="0" name="btSelectItem" type="checkbox"></td>'
            html += '<tr ><td >'+abb+'</td>'
            html += '<td>' +result.rows[idx].studentName+'</td>'
            html += '<td>' +result.rows[idx].jobnumber+'</td>'
            html += '<td>' +result.rows[idx].schoolcode+'</td>'
            html += '<td>' +result.rows[idx].gradeCode+'</td>'
            html += '<td>' +result.rows[idx].classCode+'</td></tr>'
            // html += '<td> <a class="btn btn-danger btn-xs " href="#" onclick="chooseSubjectremove('+result.rows[idx].id+')"><i class="fa fa-remove"></i>删除</a></td></tr>'
        });
        $("#eduTeachSubject").empty().append(html);
    } else {
        layer.msg("目前还没有学生编号信息请先添加任课科目，或者该班级没有学生信息");
        $("#eduTeachSubject").empty().append(html);
    }
}

// function chooseSubjectremove(id) {
//     layer.confirm("确认删除该任课教目？", {
//         btn: ['确定', '取消']
//     }, function () {
//         $.ajax({
//             cache: true,
//             type: 'POST',
//             url:  '/schedules/removes',
//             data: {"id":id},
//             dataType: 'json',
//             success: function (result) {
//                 if (result.code == "200") {
//                     layer.msg("删除该任课教目成功！", {icon: 1, time: 2000}, function () {
//                         initSchGraClaData();
//                     });
//                 } else {
//                     layer.msg("删除该任课教目失败！", {icon: 2, time: 2000});
//                 }
//             },
//             error: function () {
//                 layer.msg("系统繁忙，请稍后再试！", {icon: 2});
//             }
//         });
//     });
// }

function chooseSubjectTab() {
    window.location.href = "/schedules/rtusject";
}
function chooseClassTab() {
    window.location.href = "/schedules/teachingClass";
}

function chooseClassNumTab() {
    window.location.href = "/schedules/classnum";
}
