$(document).ready(function () {
	//获取已经创建的科目列表
    initEduTeachSubjectData();
    //添加任教科目
    $("#addSubjectBtn").click(function () {
        //加载学段列表
        loadPhases();
        layer.open({
            type: 1,
            title: '添加科目',
            area: ['350px', '400px'],
            shade: [0.5, '#000', false],
            scrollbar: false,
            btn: ['确定', '取消'],
            content: $("#addSubject"),
            yes: function (idx, layero) {
                addTeachSubject(idx, layero);
            }
        });
    });


    $('input[name="phaseId"]').change(function () {
        var phaseCode = $('input[name="phaseId"]:checked').val();
        loadSubjects(phaseCode);
    });

});


//获取学段列表
function loadPhases() {
    $.ajax({
        type: 'post',
        async: false,
        url:  '/schedules/phaseofstudylist',
        data: [],
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                var isNotExist = true;
                var phaseList = result.rows;
                if (phaseList != null && phaseList.length > 0) {
                    var phaseCode = result.rows[0].phasestudyid;
                    $('input[name="phaseId"]').each(function () {
                        if (phaseCode == $(this).val()) {
                            $(this).prop("checked", true);
                            loadSubjects(phaseCode);
                            isNotExist = false;
                        }
                    });
                }
                if (isNotExist) {
                    //默认选中第一个
                    $('input[name="phaseId"]').eq(0).prop("checked", true);
                    var phaseCode = $('input[name="phaseId"]').eq(0).val();
                    loadSubjects(phaseCode);
                }
            } else {
                layer.msg(result.msg, {icon: 2});
                changecss()
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
            changecss()
        }
    });
}

function loadSubjects(phaseCode) {
    $("#subject").empty();
    $.ajax({
        type: 'post',
        async: false,
        url:  '/schedules/phasestudylist',
        data: {'phaseCode': phaseCode},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                var subjectList = result.data;
                if (subjectList != null && subjectList.length > 0) {
                    var items = [];
                    for (var i = 0; i < subjectList.length; i++) {
                        var item = subjectList[i].subjectname;
                        items.push('<label><input type="radio" name="subjectId" class="radio" value="' + subjectList[i].subjectid + '"><i>' + item + '</i></label>');
                    }
                    $("#subject").html(items.join(''));
                }
            } else {
                layer.msg(result.msg, {icon: 2});
                changecss()
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
            changecss()
        }
    });
}

//添加任教科目
function addTeachSubject(idx, layero) {
    var phaseCode = $('input[name="phaseId"]:checked').val();
    var subjectId = $('input[name="subjectId"]:checked').val();
    //校验参数
    if (phaseCode == null || phaseCode == "") {
        layer.tips('请选择学段', '#phase', {time: 1000});
        return false;
    }
    if (subjectId == null || subjectId == "") {
        layer.tips('请选择科目', '#subject', {time: 1000});
        return false;
    }
    //保存数据
    $.ajax({
        type: 'post',
        url:  '/schedules/addTeachSubject',
        data:{'phaseCode':phaseCode, 'subjectId':subjectId},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
               // layer.close(index);//关闭loading
                layer.close(idx);//关闭弹出层
                layer.msg(result.msg, {icon: 1, time: 2000}, function () {
                	initEduTeachSubjectData();
                });
                changecss();
            } else {
                layer.msg(result.msg, {icon: 2, time: 3000}, function () {
                    $(layero).find('.layui-layer-btn0').removeAttr("style");

                });
                changecss()
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000}, function () {
                $(layero).find('.layui-layer-btn0').removeAttr("style");
            });
            changecss()
        }
    });
}

/**
 * 获取已经创建的科目列表
 * @returns
 */
function initEduTeachSubjectData() {
    var params=null;
    $.ajax({
        type: 'post',
        url:  '/schedules/subeaerlist',
        data: params,
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                appendData(result);
            } else {
                layer.msg(result.msg, {icon: 2});
                changecss()
            }

        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
            changecss()
        }
    });
}
//拼装任教科目数据
function appendData(result) {
    var html = '';
    if (result.rows != null && result.rows.length > 0) {
        var html = '<table >'
        // html += '<tr ><th class="bs-checkbox "><input data-index="0" name="btSelectAll" type="checkbox"></th>'
        html += '<th >编号</th>'
        html +='<th >用户</th>'
        html +='<th >学科</th>'
        html +='<th >学段</th>'
        html +='<th >操作</th></tr >'
        $.each(result.rows, function (idx, t) {
            var abb=1+idx;
            // html += '<tr ><td class="bs-checkbox "><input data-index="0" name="btSelectItem" type="checkbox"></td>'
            html += '<td >'+abb+'</td>'
            html += '<td>' +result.rows[idx].userid+'</td>'
            html += '<td>' +result.rows[idx].subject+'</td>'
            html += '<td>' +result.rows[idx].phasestudy+'</td>'
            html += '<td> <a class="btn btn-danger btn-xs " href="#" onclick="chooseSubjectremove('+result.rows[idx].id+')"><i class="fa fa-remove"></i>删除</a></td></tr>'
        });
        $("#eduTeachSubject").empty().append(html);
    } else {
         layer.msg("目前还没有任课科目信息请先添加任课科目");
        changecss();
        $("#eduTeachSubject").empty().append(html);
    }
}

function chooseSubjectremove(id) {

    layer.confirm("确认删除该任课教目？", {
        area: ['50px', 'auto'],
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            cache: true,
            type: 'POST',
            url:  '/schedules/removes',
            data: {"id":id},
            dataType: 'json',
            success: function (result) {
                if (result.code == "200") {
                    layer.msg("删除该任课教目成功！", {icon: 1, time: 2000}, function () {
                        initEduTeachSubjectData();
                    });
                    changecss();
                } else {
                    layer.msg("删除该任课教目失败！", {icon: 2, time: 2000});
                    changecss();
                }
            },
            error: function () {
                layer.msg("系统繁忙，请稍后再试！", {icon: 2});
                changecss();
            }
        });
    });
     changecss();

}
function changecss() {
    $( "[id^='layui-layer']" ).css("left","none")
    $( "[id^='layui-layer']" ).css("min-width","90%")
    $( "[id^='layui-layer']" ).css("width","90%")
    $( "[id^='layui-layer']" ).css("left","5%")
    $( "[id^='layui-layer-shade']" ).css("background-color","#f2f2f200")
}
function chooseSubjectTab() {
    window.location.href = "/schedules/rtusject";
}
function chooseClassTab() {
    window.location.href = "/schedules/teachingClass";
}
