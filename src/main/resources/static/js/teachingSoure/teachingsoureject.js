var bttuy =[];
var counta=0;
$(document).ready(function () {
     loadPhases();
    $('input[name="subjectId"]').change(function () {
        var teachid = $('input[name="subjectId"]:checked').val();
        initEduTeachSubjectData(teachid);
    });
});

function loadPhases() {
    $("#subject").empty();
    $.ajax({
        type: 'post',
        async: false,
        url:  '/jobfiletable/subjobList',
        data:null,
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                var subjectList = result.data;
                if (subjectList != null && subjectList.length > 0) {
                    var items = [];
                    for (var i = 0; i < subjectList.length; i++) {
                        var item = subjectList[i].gradeid+'级'+subjectList[i].classid+subjectList[i].subjectid;
                        items.push('<label><input type="radio" name="subjectId" onchange="cleandata()" class="radio" value="' + subjectList[i].teachid+ '"><i>' + item + '</i></label>');
                    }
                    $("#subject").html(items.join(''));

                    //默认选中第一个
                    $('input[name="subjectId"]').eq(0).prop("checked", true);
                    var teachid = $('input[name="subjectId"]').eq(0).val();
                    initEduTeachSubjectData(teachid);
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

/**
 *
 * @returns
 */
function initEduTeachSubjectData(teachid) {
    if (teachid == null || teachid == "") {
        layer.tips('请选择班级', '#subject', {time: 1000});
        return false;
    }
    $.ajax({
        type: 'post',
        url:  '/jobfiletable/getCalassStudent',
        data:{'teachid':teachid},
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
    bttuy =[];
    if (result.data!= null && result.data.length > 0) {
        var html = '<table >'
        html += '<tr ><th >作业编号</th>'
        html +='<th >姓名</th></tr >'
        $.each(result.data, function (idx, t) {
            bttuy.push(result.data[idx].jobnumber);
            html += '<tr ><td >'+result.data[idx].jobnumber+'</td>'
            html += '<td>'  +result.data[idx].studentName+'</td></tr>'
        });
        $("#eduTeachSubject").empty().append(html);
    } else {
        layer.msg("目前还没有，该班学生信息请先添加学校信息");
        $("#eduTeachSubject").empty().append(html);
    }
}

/**
 * 重置作业登分页面输入框
 */
function cleandata() {
    //document.getElementById("jobtype").value="";
    document.getElementById("homeworkscores").value="";
    document.getElementById("tagsinputval").value="";
    document.getElementById("StuMessage").value="";
    document.getElementById("enternum").value="";
    document.getElementById("noenternum").value="";
    document.getElementById("jobname").value="";
    selectnum()
    $("#TableStuMessage tbody").html("");
    jQuery("span").remove();
}
/**
 * 登分系统中的提交录入分数
 * */
function getinput(){
    var splitjobnumber = [];
    var Stjobnumber=  $('#tagsinputval').val();

    //var jobtype = $('#jobtype').val();
    var jobtype=document.getElementById("jobtype").value;

    var homeworkscores=$('#homeworkscores').val();
    var jobname=$('#jobname').val();
    var regex = /,/;
    // if(jobtype == null||jobtype==""||jobtype.length <=0){
    //     layer.msg("请输入作业类型");
    //     return false;
    // }

    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数

    if(regPos.test(homeworkscores) || regNeg.test(homeworkscores)) {
    }else {
        layer.msg("请正确输入分数");
        return false;
    }


    if(homeworkscores == null||homeworkscores==""||homeworkscores.length <=0){
        layer.msg("请输入分数");
        return false;
    }
    if(jobname == null||jobname==""||jobname.length <=0){
        layer.msg("请输入作业名称");
        return false;
    }

    if(homeworkscores<0){
        layer.msg("请正确输入分数");
        return false;
    }
    splitjobnumber=Stjobnumber.split(regex);
    var bool= isContained (bttuy,splitjobnumber);
    if(bool==false){
          layer.msg("请检查输入的学生编号是否正确");
          return false;
      }
    var teachid = $('input[name="subjectId"]:checked').val();
    if(splitjobnumber == null||splitjobnumber[0]==""||splitjobnumber.length <=0){
        layer.msg("请输入学生编号");
        return false;
    }
    if (teachid == null || teachid == "") {
        alert('请选择班级');
        return false;
    }
    $.ajax({
        type: 'post',
        url:  '/jobfiletable/subjobnumbernList',
        data:{'teachid':teachid,'jobnumber':Stjobnumber,'jobtype':jobtype,
            'homeworkscores':homeworkscores,'jobname':jobname},
        // data:{'teachid':teachid,'jobnumber':Stjobnumber,
        //     'homeworkscores':homeworkscores},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                //之前的显示数据已录入成功，显示有问题
                //var html ='';
                //html+='<p class="all">'+'作业'+'jobtype'+'已登记'+'result.data.countapp'+'</p>'
                //$("#countapp").empty().append(html);

                layer.msg("登分提交成功",{icon:1 ,time: 2000},function () {
                    //window.location.reload();
                    $.ajax({
                        type: 'post',
                        url:  '/jobfiletable/getNumofEnterMes',
                        data:{'teachid':teachid,'jobtype':jobtype,'jobname':jobname},
                        dataType: 'JSON',
                        success: function (result) {
                            if (result.code == "200") {
                                var x=result.data[1];
                                if(x==0){
                                    layer.msg("该门学科成绩已录入完成",{icon:1 ,time: 3000},function () {
                                        window.location.href="jobfiletable/showlafter?teachid="+teachid+"&jobtype="+jobtype+"&jobname="+jobname;
                                    });

                                }else {
                                    window.location.reload();
                                }
                            } else {
                                layer.msg(result.data, {icon: 2});
                            }
                        },
                        error: function () {
                            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
                        }
                    });
                });

            } else {
                layer.msg(result.msg, {icon: 2});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
        }
    });

}


/**
 * 登分系统中的一键登分功能
* */
function getallinput(){
    //1、判断当前教师teachid、作业类型
        //数据库去查询已录入人数，根据同教师、同班级、同学科和id判断
    var teachid = $('input[name="subjectId"]:checked').val();
    var jobtype=document.getElementById("jobtype").value;
    var jobname=document.getElementById("jobname").value;

    if(jobname == null||jobname==""||jobname.length <=0){
        layer.msg("请输入作业名称");
        return false;
    }
    //2、将剩余人数的成绩录入为100，其他根据获取的情况自动填入
    $.ajax({
        type: 'post',
        url:  '/jobfiletable/setAllStudent',
        //data:{'teachid':teachid},
        data:{'teachid':teachid,'jobtype':jobtype,'jobname':jobname},
        dataType: 'JSON',
        success: function (result) {
            if (result.code == "200") {
                layer.msg(result.msg,{icon:1 ,time: 2000},
                    function () {
                    //window.location.reload();
                    window.location.href="jobfiletable/showlafter?teachid="+teachid+"&jobtype="+jobtype+"&jobname="+jobname;

                    });


            } else {
                layer.msg(result.msg, {icon: 2});
            }
        },
        error: function () {
            layer.msg("系统繁忙，请稍后再试！", {icon: 2});
        }
    });
}


/*   判断b数组是否被a数组包含
* */
function isContained (a, b){
    if(!(a instanceof Array) || !(b instanceof Array)) return false;
    if(a.length < b.length) return false;
    var aStr = a.toString();
    for(var i = 0, len = b.length; i < len; i++){
        if(aStr.indexOf(b[i]) == -1) return false;
    }
    return true;
}
