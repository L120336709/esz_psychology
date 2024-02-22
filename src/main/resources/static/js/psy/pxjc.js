//刷新触发提交事件
//时间倒计时结束触发提交事件
function saveExam() {
    layer.confirm("确认提交成绩？", {
        btn: ['确定', '取消']
    }, function () {

        //获取考试答案，比对生成成绩
        var answerList = ""
        var anserScore = 0
        var ques = [0, 0, 0]

        for (var i = 1; i <= psyXExercisesList.length; i++) {

            if (psyXExercisesList[i - 1].type == 1 || psyXExercisesList[i - 1].type == 4) {
                var answernum = $("input[name='" + i + "']:checked").val()
                var optionDtos = JSON.parse(psyXExercisesList[i - 1].optionDtos);
                if (answernum != null) {
                    if (optionDtos[answernum - 1].answer == true) {
                        //分数计算
                        anserScore = anserScore + 4
                        ques[0]++
                    } else {
                        ques[1]++
                    }
                } else {
                    answernum = 0
                    ques[2]++
                }

                var ass = document.getElementById("field" + i);
                var laArr = ass.getElementsByTagName("p");
                for (var o = 0; o < optionDtos.length; o++) {
                    if (optionDtos[o].answer == true) {
                        laArr[o].style.backgroundColor = "#3784E4"
                        laArr[o].style.color = "white"
                    }
                }

                answerList = answerList + "," + answernum

            } else if (psyXExercisesList[i - 1].type == 2) {
                var checkboxs = document.getElementsByName(i + "");
                var optionDtos = JSON.parse(psyXExercisesList[i - 1].optionDtos);
                var answer = null
                for (var j = 0; j < optionDtos.length; j++) {
                    if (answer == null) {
                        answer = optionDtos[j].answer
                    } else {
                        answer = answer + "-" + optionDtos[j].answer
                    }
                }

                var laArr = document.getElementById("field" + i).getElementsByTagName("p");
                for (var o = 0; o < optionDtos.length; o++) {
                    if (optionDtos[o].answer == true) {
                        laArr[o].style.backgroundColor = "#3784E4"
                        laArr[o].style.color = "white"
                    }
                }

                var value = null
                var unnum=0;
                for (var j = 0; j < checkboxs.length; j++) {
                    if (checkboxs[j].checked) {
                        unnum++
                        if (value == null) {
                            value = "true"
                        } else {
                            value = value + "-" + "true"
                        }
                    } else {
                        if (value == null) {
                            value = "false"
                        } else {
                            value = value + "-" + "false"
                        }
                    }
                }
                if (unnum!=0) {
                    if (answer == value) {
                        anserScore = anserScore + 4
                        ques[0]++
                    } else {
                        ques[1]++
                    }
                } else {
                    value = 0
                    ques[2]++
                }
                answerList = answerList + "," + value

            } else if (psyXExercisesList[i - 1].type == 3) {
                var answer = document.getElementById(i + "z").value.replace(/\s*/g, "");
                if (answer == "") {
                    answer = 0
                    ques[2]++
                } else {
                    if (psyXExercisesList[i-1].stdAnswer == answer) {
                        anserScore = anserScore + 4
                        ques[0]++
                    } else {
                        ques[1]++
                    }
                }
                var laArr = document.getElementById("field" + i).getElementsByTagName("div");
                if(laArr.length>0){
                    laArr[0].innerHTML=laArr[0].innerText+"<span style='color: #3784E4'>(答案:"+psyXExercisesList[i-1].stdAnswer+")</span>"
                }
                answerList = answerList + "," + answer
            }
        }

        if (answerList.length > 0) {
            answerList = answerList.substring(1);
        }

        var passed = "<span>本次测评未通过</span>"
        if (anserScore >= 90) {
            psyXExamination.passed = 1
            passed = "<span style='color: #00B83F'>本次测评通过</span>"
        } else {
            psyXExamination.passed = 0
        }

        psyXExamination.score = anserScore
        psyXExamination.anwserList = answerList
        psyXExamination.quesTruenum = ques[0]
        psyXExamination.quesFalsenum = ques[1]
        psyXExamination.quesNullnum = ques[2]
        var jsonUserData = JSON.stringify(psyXExamination);
        $.ajax({
            cache: true,
            type: "POST",
            url: "/psychology/examination/saveExam",
            data: {"jsonString": jsonUserData},
            success: function (data) {
                if (data.code == 200) {
                    layer.msg("试卷提交成功！", {icon: 1, time: 1500})
                    window.scrollTo(0, 0)
                    setColor()
                    document.getElementById("jcbutton").hidden = true
                    timedone = 1
                    countdown()
                    count = 3600 - count
                    var min = Math.floor(count / 60) + "分钟"
                    var ms = count % 60 + "秒"
                    document.getElementById("times").innerHTML = min + ms
                    document.getElementById("timeName").innerHTML = "考试用时"

                    document.getElementById("score").innerHTML = "总题数：" + psyXExamination.quesTotalnum +
                        "、答对<span>" + ques[0] + "</span>题、"
                        + " 答错<span>" + ques[1] + "</span>题、"
                        + " 未完成<span>" + ques[2] + "</span>题、"
                        + " 成绩：<span>" + anserScore + "</span>分、" + passed

                    document.getElementById("score").hidden = false
                }
            }
        })
    });
}

//试卷设置不可编辑
function setColor() {
    //var elements = document.querySelectorAll('.field');
    var timestampArray = document.getElementsByClassName("field");

    for (var i = (timestampArray.length - 1); i >= 0; i--) {
        timestampArray[i].className = "fieldclose";
    }

    // elements.forEach(element = > {
    //     element.classList.replace('field', 'fieldclose');
    // })
}

document.oncontextmenu = function (ev) {
    console.log('禁止右键')
    return false
}


function setscro() {
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;

    //调整悬浮框滑动时固定距离顶部的高度
    if (scrollTop < 350) {
        document.getElementById("xf").style.top = (450 - scrollTop) + "px"
    } else {
        document.getElementById("xf").style.top = "100px"
    }
}


getXt()

var count = 3600;
var timedone = 0

function countdown() {
    if (timedone == 1) {
        return
    }
    if (count >= 0) {
        var min = Math.floor(count / 60) + "分钟"
        var ms = count % 60 + "秒"
        document.getElementById("times").innerHTML = min + ms
        count--;
        setTimeout(countdown, 1000);
    } else {
        saveExam()
    }
}


var psyXExercisesList
var psyXExamination

function getXt() {
    var value = window.location.search;
    value = decodeURIComponent(value);
    var inx1 = value.indexOf("CourseId=")
    var CourseId = value.substring(inx1 + 9)
    //根据CourseId获取对应习题，拼凑试卷，默认100分，每题5分，一共20题，95分以上通过
    $.ajax({
        cache: true,
        type: "POST",
        url: "/psychology/examination/getExam",
        data: {"kcId": CourseId},
        success: function (data) {
            console.log(data)
            psyXExercisesList = data.psyXExercisesList
            psyXExamination = data.psyXExamination
            if (psyXExercisesList != null && psyXExercisesList.length > 0) {
                var html = "<div style='padding: 15px;color: #3784E4;font-size: 18px'>测评课程：" + psyXExamination.kcName +
                    "<div id='score' class='score'  hidden>成绩:</div>"
                    + "</div>"

                for (var i = 0; i < psyXExercisesList.length; i++) {
                    var type = psyXExercisesList[i].type
                    var typename = ""
                    var typeinput = "radio"
                    var tigan = ""

                    if (type == 1) {
                        typename = "单选题—"
                    }
                    if (type == 2) {
                        typename = "多选题—"
                        typeinput = "checkbox"
                        console.log("多选题")
                    }
                    if (type == 3) {
                        typename = "填空题—"
                        console.log("填空题")
                    }
                    if (type == 4) {
                        typename = "判断题—"
                        console.log("判断题")
                    }
                    var timu = "<div class='field'  id='field" + (i + 1) + "'><div style='margin: 15px 0'>" + (i + 1) + "、" + psyXExercisesList[i].plainTextTitle
                        + "(" + typename  + "4分)</div><fieldset> "
                    // + "(" + typename + psyXExercisesList[i].score + "分)</div><fieldset> "

                    if (type == 3) {
                        timu = "<div class='field'  id='field" + (i + 1) + "'><div style='margin: 15px 0'>" + (i + 1) + "、" + psyXExercisesList[i].plainTextTitle
                            + " (" + typename +  "4分)"
                            //   + " (" + typename + psyXExercisesList[i].score + "分)"
                            + "</div><div>"
                            + "<input class='setinput'   type='text' id='" + (i + 1) + "z'>" +
                            "</div>"

                    } else if (type == 4) {
                        var optionDtos = JSON.parse(psyXExercisesList[i].optionDtos);
                        for (var j = 0; j < optionDtos.length; j++) {
                            tigan = tigan + "<input type='" + typeinput + "' name='" + (i + 1) + "' " +
                                "value='" + (j + 1) + "' id='" + (i + 1) + (j + 1) + "'><label for='" + (i + 1) + (j + 1) + "'>" +
                                "<p>" + optionDtos[j].content + "</p></label>"
                        }
                        tigan = tigan + "</fieldset>"
                    } else {
                        var optionDtos = JSON.parse(psyXExercisesList[i].optionDtos);
                        for (var j = 0; j < optionDtos.length; j++) {
                            tigan = tigan + "<input type='" + typeinput + "' name='" + (i + 1) + "' value='" + (j + 1) + "' " +
                                "id='" + (i + 1) + (j + 1) + "'><label for='" + (i + 1) + (j + 1) + "'>" + optionDtos[j].content + "</label>"
                        }
                        tigan = tigan + "</fieldset>"

                    }
                    html = html + timu + tigan + "</div>"
                }
                document.getElementById("courses").innerHTML = html
                countdown();
                setscro()
                window.onscroll = function () {
                    setscro()
                }
            } else {
                document.getElementById("jcbutton").hidden = true
                document.getElementById("courses").innerHTML = "<div>当前无试卷信息！</div>"
            }


        }
    })


    setCopyRight()
}