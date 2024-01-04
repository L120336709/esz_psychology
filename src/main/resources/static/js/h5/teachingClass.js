var hasLoadGrade = false;
var hasLoadSubject = false;
$(document).ready(function () {
    //加载教师授课班级数据
    initEduTeachClassData();
    //添加任教班级
    $("#addClassBtn").click(function () {
        //加载科目列表
        loadSubject();
        //加载年级列表
        loadGrade();
        layer.open({
            type: 1,
            title: '添加班级',
            area: ['350px', '400px'],
            shade: [0.5, '#000', false],
            scrollbar: false,
            btn: ['确定', '取消'],
            content: $("#addClass"),
            yes: function (idx, layero) {
                addTeachClass(idx, layero);
            }
        });
    });
});

//根据学校配置年级
function loadGrade() {

    if (hasLoadGrade) {
        //已加载过数据，则跳过
        return;
    }
    $("#grade").empty();
    $.ajax({
        type: 'post',
        url:"/schedules/gradeList",
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                var gradeList = result.data;
                if (gradeList != null && gradeList.length > 0) {
                    var items = [];
                    for (var i = 0; i < gradeList.length; i++) {
                        var item = gradeList[i];
                        items.push('<label><input type="radio" name="gradeId" class="radio" value="' + item.gradeId + '"><i>' + item.gradeName + '</i></label>');
                    }
                    $("#grade").html(items.join(''));
                    $('input[name="gradeId"]').change(function () {
                        var gradeId = $('input[name="gradeId"]:checked').val();
                        loadClazz(gradeId);
                    });
                    //默认选中第一个
                    $('input[name="gradeId"]').eq(0).prop("checked", true);
                    var gradeId = $('input[name="gradeId"]').eq(0).val();
                    loadClazz(gradeId);
                    hasLoadGrade = true;
                }
            } else {
                layer.msg(result.msg, {icon: 2, time: 2000});
                changecss()
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
            changecss()
        }
    });
}


//根据年级配置班级
function loadClazz(gradeId) {
    if (gradeId == null || gradeId == "") {
        layer.tips('请选择年级', '#grade', {time: 1000});
        return false;
    }
    $("#clazz").empty();
    $.ajax({
        type: 'post',
        url: '/schedules/classList',
        data: {'gradeId': gradeId},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                var clazzList = result.data;
                if (clazzList != null && clazzList.length > 0) {
                    var items = [];
                    for (var i = 0; i < clazzList.length; i++) {
                        var item = clazzList[i];
                        items.push('<label><input type="radio" name="clazzId" class="radio" value="' + item.classId + '"><i>' +item.className + '</i></label>');
                    }
                    $("#clazz").html(items.join(''));
                }
            } else {
                layer.msg(result.msg, {icon: 2, time: 2000});
                changecss()
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
            changecss()
        }
    });
}

//获取科目列表
function loadSubject() {
    if (hasLoadSubject) {
        //已加载过数据，则跳过
        return;
    }
    $("#subject").empty().html('<label class="t-grey">---</label>');
    $.ajax({
        type: 'post',
        url: '/schedules/getTeaClsSubject',
        data: {},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                var subjectList = result.data;
                if (subjectList != null && subjectList.length > 0) {
                    var items = [];
                    for (var i = 0; i < subjectList.length; i++) {

                        var item = subjectList[i].subject;
                        items.push('<label><input type="radio" name="subjectId" class="radio" value="' + subjectList[i].subjectid + '"><i>' + item + '</i></label>');
                    }
                    $("#subject").html(items.join(''));
                    hasLoadSubject = true;
                }
            } else {
                layer.msg(result.msg, {icon: 2, time: 2000});
                changecss()
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
            changecss();
        }
    });
}

//添加任教班级
function addTeachClass(idx, layero) {
    //校验参数
    var gradeId = $('input[name="gradeId"]:checked').val();
    var clazzId = $('input[name="clazzId"]:checked').val();
    var subjectId = $('input[name="subjectId"]:checked').val();
    //获取多个选中的班级
    if (gradeId == null || gradeId == "") {
        layer.tips('请选择年级', '#grade', {time: 1000});
        return false;
    }
    if (clazzId == null ||clazzId == "") {
        layer.tips('请选择班级', '#clazz', {time: 1000});
        return false;
    }
    if (subjectId == null || subjectId == "") {
        layer.tips('请选择科目', '#subject', {time: 1000});
        return false;
    }

    $(layero).find('.layui-layer-btn0').css('pointer-events', 'none');
    //保存数据
    $.ajax({
        type: 'post',
        url: '/schedules/saveEduTeachClassList',
        data: {'subjectid':subjectId, 'gradeid':gradeId, 'classid':clazzId},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                layer.close(idx);//关闭弹出层
                layer.msg(result.msg, {icon: 1, time: 2000}, function () {
                	initEduTeachClassData();
                });
                changecss();
            } else {
                layer.msg(result.msg, {icon: 2, time: 3000}, function () {
                    $(layero).find('.layui-layer-btn0').removeAttr("style");
                    layer.close(index);
                });
                changecss();
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000}, function () {
                $(layero).find('.layui-layer-btn0').removeAttr("style");
                layer.close(index);
            });
            changecss();
        }
    });
}

//得到该用户所有任课班级数据
function initEduTeachClassData() {

    var params=null;
    $.ajax({
        type: 'post',
        url: '/schedules/subjobList',
        data: params,
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                appendDataClass(result);
            } else {
                layer.msg(result.msg, {icon: 2, time: 2000});
                changecss();
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
            changecss();
        }
    });
}

//拼装授课班级数据
function appendDataClass(result) {
    var html = '';
    if (result.data != null && result.data.length > 0) {
        var html = '<table >'
        // html += '<tr ><th class="bs-checkbox "><input data-index="0" name="btSelectAll" type="checkbox"></th>'
        html += '<th >编号</th>'
        html +='<th >用户</th>'
        html +='<th >年级</th>'
        html +='<th >班级</th>'
        html +='<th >科目</th>'
        html +='<th >操作</th></tr >'
        $.each(result.data, function (idx, t) {
            var abb=1+idx;
            // html += '<tr ><td class="bs-checkbox "><input data-index="0" name="btSelectItem" type="checkbox"></td>'
            html += '<td >'+abb+'</td>'
            html += '<td>' +result.data[idx].userid+'</td>'
            html += '<td>' +result.data[idx].gradeid+'</td>'
            html += '<td>' +result.data[idx].classid+'</td>'
            html += '<td style="width: 90px">' +result.data[idx].subjectid+'</td>'
            html += '<td> <a class="btn btn-danger btn-xs " href="#" onclick="delTeachClass('+result.data[idx].teachid+')"><i class="fa fa-remove"></i>删除</a></td></tr>'
        });
        $("#eduTeachSubject").empty().append(html);
    } else {
        layer.msg("目前还没有授课信息请先添加",{area: '40px'});
        changecss();
        $("#eduTeachSubject").empty().append(html);
    }
}

//删除授课班级
function delTeachClass(teachid) {
    layer.confirm("确认删除该授课班级？", {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'post',
            url: path + '/schedules/deleteEduTeachClass',
            data: {"teachid": teachid},
            dataType: 'JSON',
            success: function (result) {
                if (result.code == "200") {
                    layer.msg("删除该授课班级成功！", {icon: 1, time: 2000}, function () {
                        initEduTeachClassData();
                    });
                    changecss()
                } else {
                    layer.msg("删除该授课班级失败！", {icon: 2, time: 2000});
                    changecss();
                }
            },
            error: function () {
                layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
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
/*
* ①οnclick="javascript:window.location.href=' URL'"
　　②οnclick="location=' URL'"
　　③οnclick="window.location.href=' URL?id=11'"
*
* */
