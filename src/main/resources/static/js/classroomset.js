$(document).ready(function () {
    //根据默认选中的学年学期任教科目数据
    initEduTeachSubjectData();

    //添加任教科目
    $("#addSubjectBtn").click(function () {
        //加载学段列表
        layer.open({
            type: 1,
            title: '添加科目',
            area: ['800px', '580px'],
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

function loadSubjects(phaseCode) {
    $("#subject").empty();
    $("#edition").empty().html('<label class="t-grey">---</label>');
    $("#book").empty().html('<label class="t-grey">---</label>');
    $.ajax({
        type: 'post',
        async: false,
        url:  '/teachingSet/getSubjectByPhase',
        data: {'phaseCode': phaseCode},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "2000") {
                var subjectList = result.data;
                if (subjectList != null && subjectList.length > 0) {
                    var items = [];
                    for (var i = 0; i < subjectList.length; i++) {
                        var item = subjectList[i];
                        items.push('<label><input type="radio" name="subjectId" class="radio" value="' + item.subjectId + '"><i>' + item.subjectName + '</i></label>');
                    }
                    $("#subject").html(items.join(''));
                    $('input[name="subjectId"]').change(function () {
                        var phaseCode = $('input[name="phaseId"]:checked').val();
                        var subjectId = $('input[name="subjectId"]:checked').val();
                        loadEdition(phaseCode, subjectId);
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

//根据科目获取教材版本列表
function loadEdition(phaseCode, subjectId) {
    if (phaseCode == null || phaseCode == "") {
        layer.tips('请选择学段', '#phase', {time: 1000});
        return false;
    }
    if (subjectId == null || subjectId == "") {
        layer.tips('请选择科目', '#subject', {time: 1000});
        return false;
    }
    $("#edition").empty().html('<label class="t-grey">---</label>');
    $("#book").empty().html('<label class="t-grey">---</label>');
    $.ajax({
        type: 'post',
        url:  '/teachingSet/getEditionBySubject',
        data: {'subjectId': subjectId, 'phaseCode': phaseCode},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "2000") {
                var bookEditionList = result.data;
                if (bookEditionList != null && bookEditionList.length > 0) {
                    var items = [];
                    for (var i = 0; i < bookEditionList.length; i++) {
                        var item = bookEditionList[i];
                        items.push('<label><input type="radio" name="editionId" class="radio" value="' + item.editionId + '"><i>' + item.editionName + '</i></label>');
                    }
                    $("#edition").html(items.join(''));
                    $('input[name="editionId"]').change(function () {
                        var phaseCode = $('input[name="phaseId"]:checked').val();
                        var subjectId = $('input[name="subjectId"]:checked').val();
                        var editionId = $('input[name="editionId"]:checked').val();
                        loadBook(phaseCode, subjectId, editionId);
                    });
                }
            } else {
                layer.msg(result.msg, {icon: 2, time: 2000});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
        }
    });
}

//根据科目和教材版本获取课本列表
function loadBook(phaseCode, subjectId, editionId) {
    if (phaseCode == null || phaseCode == "") {
        layer.tips('请选择学段', '#phase', {time: 1000});
        return false;
    }
    if (subjectId == null || subjectId == "") {
        layer.tips('请选择科目', '#subject', {time: 1000});
        return false;
    }
    if (editionId == null || editionId == "") {
        layer.tips('请选择教材版本', '#edition', {time: 1000});
        return false;
    }
    $("#book").empty().html('<label class="t-grey">---</label>');
    $.ajax({
        type: 'post',
        url:  '/teachingSet/getBookBySubjectAndEidtion',
        data: {'subjectId': subjectId, 'editionId': editionId, 'phaseCode': phaseCode},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "2000") {
                var bookList = result.data;
                if (bookList != null && bookList.length > 0) {
                    var items = [];
                    for (var i = 0; i < bookList.length; i++) {
                        var item = bookList[i];
                        items.push('<label><input type="radio" name="bookId" class="radio" value="' + item.bookId + '"><i>' + item.bookName + '</i></label>');
                    }
                    $("#book").html(items.join(''));
                }
            } else {
                layer.msg(result.msg, {icon: 2});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon:2});
        }
    });
}

//根据学年获取学期列表
function loadStudyPeriod(studyYear) {
    $("#studyPeriod").empty();
    $.ajax({
        type: 'post',
        url:  '/teachingSet/getStudyPeriodSelList',
        data: {'studyYear': studyYear},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "2000") {
                var studyPeriodList = result.data;
                if (studyPeriodList != null && studyPeriodList.length > 0) {
                    var items = [];
                    for (var i = 0; i < studyPeriodList.length; i++) {
                        var item = studyPeriodList[i];
                        if (item.isCurrent == '1') {
                            items.push('<label><input type="radio" name="studyPeriod" class="radio" value="' + item.studyPeriod + '" checked><i>' + item.studyPeriodDesc + '</i></label>');
                        } else {
                            items.push('<label><input type="radio" name="studyPeriod" class="radio" value="' + item.studyPeriod + '"><i>' + item.studyPeriodDesc + '</i></label>');
                        }
                    }
                    $("#studyPeriod").html(items.join(''));
                    $('input[name="studyPeriod"]').eq(0).prop("checked", true);
                }
            } else {
                layer.msg(result.msg, {icon: 2, time: 2000});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
        }
    });
}

//添加任教科目
function addTeachSubject(idx, layero) {
    //校验参数
    var phaseCode = $('input[name="phaseId"]:checked').val();
    var subjectId = $('input[name="subjectId"]:checked').val();
    var editionId = $('input[name="editionId"]:checked').val();
    var bookId = $('input[name="bookId"]:checked').val();
    if (phaseCode == null || phaseCode == "") {
        layer.tips('请选择学段', '#phase', {time: 1000});
        return false;
    }
    if (subjectId == null || subjectId == "") {
        layer.tips('请选择科目', '#subject', {time: 1000});
        return false;
    }
    if (editionId == null || editionId == "") {
        layer.tips('请选择教材版本', '#edition', {time: 1000});
        return false;
    }
    if (bookId == null || bookId == "") {
        layer.tips('请选择课本', '#book', {time: 1000});
        return false;
    }
    $("#phaseName").val($('input[name="phaseId"]:checked').next('i').text());
    $("#subjectName").val($('input[name="subjectId"]:checked').next('i').text());
    $("#editionName").val($('input[name="editionId"]:checked').next('i').text());
    $("#bookName").val($('input[name="bookId"]:checked').next('i').text());

    //loading
    var index = layer.load(1, {
        shade: [0.5, '#000']
    });
    $(layero).find('.layui-layer-btn0').css('pointer-events', 'none');

    //保存数据
    $.ajax({
        type: 'post',
        url:  '/teachingSet/addTeachSubject',
        data: $("#addSubject").serialize(),
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "2000") {
                layer.close(index);//关闭loading
                layer.close(idx);//关闭弹出层
                layer.msg(result.msg, {icon: 1, time: 2000}, function () {
                    initEduTeachSubjectData();
                });
            } else {
                layer.msg(result.msg, {icon: 2, time: 3000}, function () {
                    $(layero).find('.layui-layer-btn0').removeAttr("style");
                    layer.close(index);
                });
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000}, function () {
                $(layero).find('.layui-layer-btn0').removeAttr("style");
                layer.close(index);
            });
        }
    });
}

/**
 * 根据 学段查询出所有的学科
 * @returns
 */
function initEduTeachSubjectData() {
    var params = {"phasestudy":phasestudy};
    $.ajax({
        type: 'post',
        url:  '/schedules/phasestudylist',
        data: params,
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
    if (result.data != null && result.data.length > 0) {
        $.each(result.data, function (idx, t) {

            html += '<li><h5 class="mb5 t-pink">' + t.studyYear + '学年';
            if("001" == t.studyPeriod) {
                html += "(上学期)";
            } else if ("002" == t.studyPeriod) {
                html += "(下学期)";
            }
            html+="</h5>";
            html+= '<h4>' + t.subjectName + (t.isMajor == "1" ? ' <span class="tags bg-yellow ml5">主授</span>' : '') + '</h4>';
            html+= '<p class="t-grey mt15"><span class="mr5">' + t.editionName + '</span><span class="mr5" title=\'' + t.bookName + '\'>' + t.bookName + '</span></p>';
            if (t.studyYear >= currentStudyYear&&t.studyPeriod>=currentStudyPeriod) {
                html+= '<a href="#" class="icon-del t-pink" onclick="delTeachSubject(\'' + t.teachSubjectId + '\',\'' + t.subjectName + '\',\'' + t.editionName + '\',\'' + t.bookName + '\');">&nbsp;删除</a>';
                html+= (t.isMajor == "0" ? '<a href="#" class="btn btn-invert btn-teal" onclick="setMajorSubject(\'' + t.teachSubjectId + '\');">&nbsp;设为主授&nbsp;</a>' : '');
            }
            html += '</li>';
        });
        $("#eduTeachSubject").empty().append(html);
    } else {
        html += '<div class="tc">';
        html += '<img src="' +  '/static/new/images/nodata.png">';
        html += '<p class="h4 mt30">暂无任教科目数据，请点击右上角添加科目！</p>';
        html += '</div>';
        $("#eduTeachSubject").empty().append(html);
    }
}

//删除任教科目
function delTeachSubject(teachSubjectId, subjectName, editionName, bookName) {
    var content = subjectName + editionName + bookName + " 删除后，您可以再次添加，该教材版本下的数据会恢复";
    layer.confirm(content, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            type: 'post',
            url:  '/teachingSet/deleteEduTeachSubject',
            data: {teachSubjectId: teachSubjectId},
            dataType: 'JSON',
            success: function (result) {
                if (result.code == "2000") {
                    layer.msg("删除该任教科目成功！", {icon: 1, time: 2000}, function () {
                        initEduTeachSubjectData();
                    });
                } else {
                    layer.msg("删除该任教科目失败！", {icon: 2, time: 2000});
                }
            },
            error: function () {
                layer.msg("系统繁忙，请稍后再试！", {icon: 5, time: 1000});
            }
        });
    })
}
//点击任教科目
function chooseSubjectTab() {
    window.location.href =  "/teachingSet/teachingSubject?chooseStudyYear=" + studyYear+"&chooseStudyPeriod="+studyPeriod;
}
//点击任教班级
function chooseClassTab() {

    window.location.href =  "/teachingSet/teachingClass?chooseStudyYear=" + studyYear+"&chooseStudyPeriod="+studyPeriod;
}
