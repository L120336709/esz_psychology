function toIndex() {
    window.location.href = "/psychology/login"
}


function toPxsm() {

    window.location.href = "/psychology/pxsm"

}

function toXxjd() {
    window.location.href = "/psychology/xxjd"
}


function toKclb(CourseId) {
    window.open("/psychology/kclb?CourseId=" + CourseId)
}

function toStudy(LessonId, CourseId) {

    window.location.href = "/psychology/videoplay?LessonId=" + LessonId + "&CourseId=" + CourseId
}

function videoToKclb() {
    window.location.href = "/psychology/kclb?CourseId=" + CourseId
}


function setCopyRight() {
    document.getElementById("copy_right").innerHTML = " <div>\n" +
        "        <p>版权信息：Copyright © 2021-2022 恩施土家族苗族自治州教育局版权所有 版本号：V3.0</p>\n" +
        "        <p>主办：恩施土家族苗族自治州教育局 技术支持：0718-8232617</p>\n" +
        "        <p>备案信息： 鄂ICP备2020019434号-1</p>\n" +
        "    </div>"
}

function saveTime() {
    var ca = document.cookie.split(';');
    var time = ""
    for (var i = 0; i < ca.length; i++) {
        if (ca[i].indexOf("videoTime=") >= 0) {
            videoTime = ca[i].substring(ca[i].indexOf("videoTime=") + 10);
        }
        if (ca[i].indexOf("videoTimeAll=") >= 0) {
            videoTimeAll = ca[i].substring(ca[i].indexOf("videoTimeAll=") + 13);
        }
        if (ca[i].indexOf("allTime=") >= 0) {
            allTime = ca[i].substring(ca[i].indexOf("allTime=") + 8);
        }
        if (ca[i].indexOf("videoNum=") >= 0) {
            videoNum = ca[i].substring(ca[i].indexOf("videoNum=") + 9).split(",");
        }

        if (ca[i].indexOf("cooo=") >= 0) {
            time = ca[i].substring(ca[i].indexOf("cooo=") + 5);
        }

    }
    // console.log("videoTime=" + videoTime)
    // console.log("videoTimeAll=" + videoTimeAll)
    // console.log("allTime=" + allTime)
    // console.log("videoNum=" + videoNum)
    // console.log("time=" + time)


    if (videoTimeAll <= videoTime) {
        doness = 1
    }

    $.ajax({
        cache: true,
        type: "POST",
        url: "/psychology/course/saveTime",
        data: {
            "videoId": videoIdNow,
            "LessonId": LessonId,
            "CourseId": CourseId,
            "videoTime": videoTime,
            "videoTimeAll": videoTimeAll,
            "videoNum": (videoNum.length - 1),
            "allTime": allTime,
            "startTime": time
        },
        success: function (data) {
            allTime = 0;
            document.cookie = 'allTime=0; path=https://xypx.eszedu.com/psychology';
        }
    })

}

function getCourse() {
    setCopyRight()
    var value = window.location.search;
    value = decodeURIComponent(value);
    var inx1 = value.indexOf("accessToken=")
    var accessToken0 = value.substring(inx1 + 12)
    var accessToken = ""
    var ca = document.cookie.split(';');
console.log()
    var time = ""
    for (var i = 0; i < ca.length; i++) {

        if (ca[i].indexOf("cooo=") >= 0) {
            time = ca[i].substring(ca[i].indexOf("cooo=") + 5);
        }
        if (ca[i].indexOf("accessTokenESZ=") >= 0) {
            accessToken = ca[i].substring(ca[i].indexOf("accessTokenESZ=") + 15);
        }
    }
    var lo="https://xypx.eszedu.com"

    if (accessToken != accessToken0) {
        document.cookie = 'videoNum=; path='+lo+'/psychology';
        document.cookie = 'allTime=0; path='+lo+'/psychology';
        document.cookie = 'videoTimeAll=0; path='+lo+'/psychology';
        document.cookie = 'cooo=' + Date.now() + '; path='+lo+'/psychology';
        document.cookie = 'accessTokenESZ=' + accessToken0 + '; path='+lo+'/psychology';
    }
    $.ajax({
        cache: true,
        type: "POST",
        url: "/psychology/course/list",
        success: function (data) {
            if (data.code == 200) {
                //获取课程学习情况
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: "/psychology/userstudy/getJLbyUserId",
                    data: {},//需要获取当前用户登录
                    success: function (psyUserstudyList) {
                        var course = ""
                        for (var i = 0; i < data.total; i++) {
                            var coursemes = data.rows[i]
                            var chours = "<div class='bac_03_03'><p >共：" + coursemes.allClasshour + "课时</p></div>"
                            var schedule = 0;
                            var studyjd = "开始学习"
                            if (psyUserstudyList != null) {
                                for (var p = 0; p < psyUserstudyList.length; p++) {
                                    if (psyUserstudyList[p].kcId == coursemes.id) {
                                        if (psyUserstudyList[p].schedule != null) {
                                            schedule = psyUserstudyList[p].schedule
                                        }
                                        if (schedule != 0) {
                                            studyjd = "继续学习"
                                        }
                                        chours = "<div  class='bac_03_03'><p >共:" + coursemes.allClasshour
                                            + "课时</p><p>,已学:" + psyUserstudyList[p].getClasshour
                                            + "课时</p><p>,学习进度:" + (schedule * 100).toFixed(2) + "%</p></div>"
                                    }
                                }
                            }

                            var teanames=coursemes.adorealName.split(",")
                            var teaname=teanames[0]
                            if(teanames.length>1){
                                teaname=teaname+"..."
                            }

                            course = course + "<div class='bac_03_02'  >"
                                + "<img  onclick='toKclb(\"" + coursemes.id + "\")' src='" + coursemes.ctImgUrl + "' alt>"
                                + "<div style='width: 80px'><a onclick='toKclb(\"" + coursemes.id + "\")'>" + studyjd + "</a></div>"
                                + "<div style='width: 160px;'  >" +
                                "<span style='font-weight:bold'>" + coursemes.name + "</span> </div>"

                                + "<div style='padding-top: 10px'> " + teaname + " </div>"
                                + "<div>" + coursemes.adolectorDesc + "</div>"
                                + chours
                                + "</div>"
                        }

                        document.getElementById("course").innerHTML = course
                        //+ course + course + course + course
                    }
                })
            } else {

            }
        }
    });
}

function toPsjc(done, schedule, CourseId) {
    if (done == 1) {
        window.location.href = "/psychology/pxjc?CourseId=" + CourseId
    } else {
        layer.msg("本课程的学习进度达到100%后可以进行考试！当前进度：" + (schedule * 100).toFixed(2) + "%", {area: ['450px', '70px']})
    }
}

function getKclb() {
    setCopyRight()
    var value = window.location.search;
    value = decodeURIComponent(value);
    var inx = value.indexOf("CourseId=")
    var CourseId = value.substring(inx + 9)
    $.ajax({
        cache: true,
        type: "POST",
        url: "/psychology/course/getKclb",
        data: {"CourseId": CourseId},
        success: function (data) {
            console.log(data)

            if (data != null) {
                var psyCCourse = data.psyCCourse
                if (psyCCourse != null) {

                    var ks = 0
                    var schedule = 0
                    $.ajax({
                        cache: true,
                        async: false,
                        type: "POST",
                        url: "/psychology/examination/getlist",
                        data: {"kcId": CourseId},
                        success: function (data) {
                            if (data.code == 200) {
                                var datas = data.rows
                                var boo = false
                                for (var i = 0; i < datas.length; i++) {
                                    if (datas[i].passed == 1) {
                                        boo = true
                                    }
                                }
                                if (boo == true) {
                                    ks = 1
                                }
                            } else {
                                console.log(data)
                            }
                        }
                    })


                    if (ks == 0) {
                        $.ajax({
                            cache: true,
                            async: false,
                            type: "POST",
                            url: "/psychology/userstudy/getJLbyUserId",
                            data: {"kcId": CourseId},
                            success: function (userstudydata) {
                                if (userstudydata.length > 0) {
                                    schedule = userstudydata[0].schedule
                                }
                            }
                        })
                    }

                    if (ks == 1) {
                        ks = "<button class='jcbutton' >已完成考试</button>"
                    } else if (ks == 0) {
                        if (schedule == 1) {
                            ks = "<button class='jcbutton'  title='完成所有视频课程的学习之后，方可以进行在线测评！' onclick='toPsjc(1," + schedule + ",\"" + CourseId + "\",\"" + psyCCourse.name + "\")'>" +
                                "进行考试</button>"
                        } else {
                            ks = "<button class='jcbutton2' title='完成所有视频课程的学习之后，方可以进行在线测评！' onclick='toPsjc(0," + schedule + ",\"" + CourseId + "\")'>进行考试</button>"
                        }
                    }

                    //测试方便用，后续删除
                   // ks = "<button class='jcbutton2' title='完成所有视频课程的学习之后，方可以进行在线测评！'  onclick='toPsjc(1," + schedule + ",\"" + CourseId + "\",\"" + psyCCourse.name + "\")'>" +
                    //    "进行考试</button>"

                   var adolargeFaceUrls= psyCCourse.adolargeFaceUrl.split(",")
                    var adorealName=psyCCourse.adorealName.split(",")
                    var tealists="";
                    for(var i=0;i<4;i++){
                        if(i<adolargeFaceUrls.length){
                            tealists=tealists+"<div style='width: 120px;float: left;text-align: center'> " +
                                "<img style='width: 80px;border-radius:50%;padding: 5px' src='" + adolargeFaceUrls[i] + "' alt>" +
                                "<p style='padding: 5px;margin: 0'>" + adorealName[i] + "</p>" +
                                "</div>"
                        }
                    }
                    var tealistdiv=""
                    var tealistdivnum=Math.ceil(adolargeFaceUrls.length/4);

                    if(tealistdivnum>1){
                        for(var n=0;n<tealistdivnum;n++){
                            tealistdiv=tealistdiv+ "<div onclick='setTealist("+n+",\""+psyCCourse.adolargeFaceUrl+"\",\""+psyCCourse.adorealName+"\")' " +
                                "style='background-color: #85B706;border-radius: 15px;height: 10px;margin-top: 10px;cursor: pointer'></div>"
                        }
                    }

                    var kcxx = "<div>" +
                        "<div style='width: 500px;float: left;padding: 20px'>" +
                        "<img style='width: 100%' src='" + psyCCourse.ctImgUrl + "' alt></div>" +
                        "<div style='width: 560px;float: right;padding:0 20px;'>" +
                        "<p style='font-size: 24px;font-weight: bold;'>" + psyCCourse.name + "</p>" +
                        "<p style='margin-top: 10px;float: left'>总课时：" + psyCCourse.allClasshour + "</p>" +
                        ks +
                        "</div>" +
                        "<div class='teap'>" +
                          psyCCourse.adolectorDesc +
                        "<div id='tealist'> "+
                        tealists+
                        "</div>"+
                        "<div id='tealist-1' style='margin-top: 20px;margin-right: 20px;width: 10px;float: right;text-align: center'> " +
                        tealistdiv+
                         "</div>" +
                        "</div>" +
                        "</div>"
                    kcxx = kcxx+"<div style='width: 100%'><div style='color:red;padding: 10px 20px'>提示：点击课程目录进入具体章节学习 ↓</div></div>"
                    if (psyCCourse.jsonContent != null) {

                        kcxx = kcxx + "<div style='width: 100%'><div style='padding: 20px 20px 0 20px;'>课程推介:</div>" +
                            "<div style='padding: 20px;line-height: 2; text-indent: 2em;background-color: #F5F8F4;'> " + psyCCourse.jsonContent + "</div></div>"
                    }
                    var psyCChaptersList = data.psyCChaptersList
                    var psyCLessonsList = data.psyCLessonsList
                    if (psyCChaptersList != null) {
                        var psyCChapterHtml = "<div class='cheaper'>"
                        psyCChapterHtml = psyCChapterHtml+"<div style='font-size: 20px' >课程目录：</div>"


                        for (var i = 0; i < psyCChaptersList.length; i++) {
                            psyCChapterHtml = psyCChapterHtml + "<div><div style='padding: 25px 0;font-size: 20px'>" +
                                psyCChaptersList[i].name +
                                "</div>"
                            //获取每个章节的课程数据
                            if (psyCLessonsList != null) {
                                for (var l = 0; l < psyCLessonsList.length; l++) {
                                    if (psyCLessonsList[l].chapersId == psyCChaptersList[i].id) {
                                        var clecolor="rgba(0,0,0,0.5)";
                                        var clestate="（未开始）"
                                        if(psyCLessonsList[l].done==1){
                                            clecolor="#00CC7E"
                                            clestate="（已完成）"
                                        }else  if(psyCLessonsList[l].done==2){
                                            clecolor="blue"
                                            clestate="（学习中）"
                                        }else  if(psyCLessonsList[l].done==3){
                                            clecolor="black"
                                            clestate="（文档）"
                                        }

                                        psyCChapterHtml = psyCChapterHtml
                                            + "<div style='padding: 10px 3px;margin-left: 40px;color: "+clecolor+"'>" +
                                            "<a onclick='toStudy(\"" + psyCLessonsList[l].id + "\",\"" + CourseId + "\")' " +
                                            "style='font-size: 15px;'>"
                                            + psyCLessonsList[l].name+clestate
                                            + "</a></div>"
                                    }
                                }
                            }
                            psyCChapterHtml = psyCChapterHtml + "</div>"
                        }
                        psyCChapterHtml = psyCChapterHtml + "</div>"
                        kcxx = kcxx + psyCChapterHtml
                    }
                    document.getElementById("kclb").innerHTML = kcxx
                }
            }
        }
    })
}

function setTealist(t,adolargeFaceUrl,adorealName) {
    var adolargeFaceUrls=adolargeFaceUrl.split(",")
    var adorealName=adorealName.split(",")
    var tealist=document.getElementById("tealist")
    if(tealist!=null){
        var tealist1=document.getElementById("tealist-1")
        if(tealist1!=null){
            var laArr = tealist1.getElementsByTagName("div");
            for(var i=0;i<laArr.length;i++){
                laArr[i].style.backgroundColor="#E6E6E6"
            }
            laArr[t].style.backgroundColor="#85B706"
        }

        var tealists=""
        for(var i=t*4;i<(t*4+4);i++){
            if(i<adolargeFaceUrls.length){
                tealists=tealists+"<div style='width: 120px;float: left;text-align: center'> " +
                    "<img style='width: 80px;border-radius:50%;padding: 5px' src='" + adolargeFaceUrls[i] + "' alt>" +
                    "<p style='padding: 5px;margin: 0'>" + adorealName[i] + "</p>" +
                    "</div>"
            }
        }
        tealist.innerHTML=tealists


    }
}

function getXxjd() {
    var userId=document.getElementById("userId").innerText

    var dataNow = new Date()
    var year = dataNow.getFullYear();
    var month = ('0' + (dataNow.getMonth() + 1)).slice(-2);
    var day = ('0' + dataNow.getDate()).slice(-2);
    var yyyymmdd = "  " + year + "年" + month + "月" + day + "日";

    document.getElementById("dataNow").innerHTML = yyyymmdd

    //获取当前用户，所有课程的学习总进度情况
    $.ajax({
        cache: true,
        type: "POST",
        url: "/psychology/userstudy/getJLbyUserId",
        data:{"userId":userId},
        success: function (data) {
            if (data.code == null) {
                if (data.length > 0) {

                    //完成学习的课程数量
                    var donenum = 0
                    //学习时长
                    var mins = 0
                    //学习课时
                    var classhour = 0


                    var realName=data[0].userName
                    if(userId!=null&&userId!=""){
                        document.getElementById("title").hidden=true
                        document.getElementById("titlename").innerText=realName+"的学习档案"
                    }
                    //获取所有学习科目的每次学习记录
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: "/psychology/record/getRecordbyUserId",
                        data:{"userId":userId},
                        success: function (dataRecord) {
                            if (dataRecord.code == null) {
                                var html = ""
                                for (var i = 0; i < data.length; i++) {
                                    var userstudy = data[i]
                                    if (userstudy.done == 1) {
                                        donenum++
                                    }
                                    mins = Number(mins) + Number(userstudy.getHour)
                                    classhour = Number(classhour) + Number(userstudy.getClasshour)

                                    var userstudyImgUrl = ""
                                    var gouImgUrl = ""
                                    var gouImgUrl2 = ""
                                    //测试用判断，正确判断用下面的done==1
                                   // if (userstudy.schedule > 0) {
                                   //     userstudyImgUrl = "/img/psy/passed.png"
                                   //     gouImgUrl = "/img/psy/gou2.png"
                                   //     gouImgUrl2 = "/img/psy/gou.png"
                                   // }

                                    if(userstudy.done==1){

                                        gouImgUrl="/img/psy/gou2.png"
                                        gouImgUrl2="/img/psy/gou.png"
                                    }



                                    var dataRecords = ""

                                    for (var r = 0; r < dataRecord.length; r++) {
                                        if (dataRecord[r].kcId == userstudy.kcId) {
                                            var gethour = dataRecord[r].getHour
                                            if (gethour < 60) {
                                                gethour = gethour + "秒"
                                            } else {
                                                gethour = Math.round(gethour / 60) + "分钟"
                                            }

                                            var startTime = new Date(parseInt(dataRecord[r].startTime));
                                            var year = startTime.getFullYear();
                                            var month = ('0' + (startTime.getMonth() + 1)).slice(-2);
                                            var day = ('0' + startTime.getDate()).slice(-2);
                                            var hour = ('0' + startTime.getHours()).slice(-2);
                                            var min = ('0' + startTime.getMinutes()).slice(-2);
                                            var sec = ('0' + startTime.getSeconds()).slice(-2);
                                            startTime = year + "年" + month + "月" + day + "日" + hour + ":" + min + ":" + sec;

                                            var endTime = new Date(parseInt(dataRecord[r].endTime));
                                            year = endTime.getFullYear();
                                            month = ('0' + (endTime.getMonth() + 1)).slice(-2);
                                            day = ('0' + endTime.getDate()).slice(-2);
                                            hour = ('0' + endTime.getHours()).slice(-2);
                                            min = ('0' + endTime.getMinutes()).slice(-2);
                                            sec = ('0' + endTime.getSeconds()).slice(-2);
                                            endTime = year + "年" + month + "月" + day + "日" + hour + ":" + min + ":" + sec;

                                            dataRecords = dataRecords + "<div>" + startTime + "-" + endTime
                                                + "学习时长" + gethour + "，学习内容"
                                                + dataRecord[r].getClasshour + "个课时</div>"
                                        }
                                    }

                                    var imgs = ""
                                    var jdcolor="#3784E4"
                                    if(userstudy.schedule==1){
                                        //      if(userstudy.schedule==1){
                                        imgs = "<img  class='userstudyimg2' src=\"" + gouImgUrl + "\">\n"
                                        jdcolor="limegreen"
                                    }
                                    var getClasshour=userstudy.getClasshour
                                    if(getClasshour==null){
                                        getClasshour=0
                                    }



                                    var PsyXExaminations = ""
                                    var examnum = 0;
                                    var examScoreHeight = 0;
                                    var examdone = "测评未通过"
                                    var examcolor="#3784E4"
                                    $.ajax({
                                        cache: true,
                                        async: false,
                                        type: "POST",
                                        url: "/psychology/examination/getlist",
                                        data: {"kcId": userstudy.kcId,"userId":userId},
                                        success: function (data) {

                                            var PsyXExamination = data
                                            examnum = PsyXExamination.length
                                            for (var p = 0; p < PsyXExamination.length; p++) {
                                                if (examScoreHeight < PsyXExamination[p].score) {
                                                    examScoreHeight = PsyXExamination[p].score
                                                }
                                                var dones = "测评未通过"
                                                if (PsyXExamination[p].passed == 1) {
                                                    examdone = "测评通过" + "！ <img class='userstudyimg2' src=\"" + gouImgUrl2 + "\">\n"
                                                    dones = "测评通过"
                                                    examcolor="limegreen"
                                                    userstudyImgUrl="/img/psy/passed.png"


                                                }
                                                var startTime = PsyXExamination[p].startTime
                                                startTime = startTime.substring(0, 4) + "年" + startTime.substring(5, 7) + "月" + startTime.substring(8, 10)
                                                    + "日" + startTime.substring(11)
                                                var endTime = PsyXExamination[p].endTime
                                                endTime = endTime.substring(0, 4) + "年" + endTime.substring(5, 7) + "月" + endTime.substring(8, 10)
                                                    + "日" + endTime.substring(11)
                                                PsyXExaminations = PsyXExaminations + "<div>" + startTime + "-" + endTime
                                                    + "测评题目" + PsyXExamination[p].quesTotalnum + "道，正确"
                                                    + PsyXExamination[p].quesTruenum + "道," + dones +
                                                    "</div>"
                                            }

                                            if (examnum == 0) {
                                                PsyXExaminations = "未进行考试"
                                            }


                                        }
                                    })

                                    html = html + "<div style='height: 250px'> <div class=\"userstudy_01\">" +
                                        "<img class='userstudyimg'  src='"+userstudyImgUrl+"' >" +
                                        "<span style=\"color: red;font-weight: bold;padding-left: 50px\">" + (i + 1)
                                        + "</span>" + userstudy.kcName +
                                        "</div>"

                                    html = html + "<div class=\"userstudy_02\">" +
                                        "课程学习情况：<span style='color: "+jdcolor+" '> 共" + userstudy.allClasshour + "个课时，已学"
                                        + getClasshour + "个课时，学习进度" + ((userstudy.schedule) * 100).toFixed(2) +
                                        "%</span>" +
                                        imgs +
                                        "<div class='userstudy_02_01' >\n" +
                                        dataRecords +
                                        "    </div>\n" +
                                        "</div>"


                                    html = html + "   <div class=\"userstudy_02\" >\n" +
                                        "  课程测评情况：<span style='color: "+examcolor+"'>累计测评" + examnum + "次，测评最高分" + examScoreHeight
                                        + "分，" + examdone +
                                        "</span><div  class='userstudy_02_01'>\n" +
                                        PsyXExaminations +
                                        "                </div>\n" +

                                        "            </div>"
                                    html = html + "</div>"
                                }

                                document.getElementById("nums").innerHTML = dataRecord.length + "次"
                                mins = Math.floor(mins / 60)
                                document.getElementById("mins").innerHTML = mins + "分钟"
                                document.getElementById("hours").innerHTML = classhour + "个课时"
                                document.getElementById("classs").innerHTML = donenum + "门课程"

                                if(donenum==0){
                                     document.getElementById("studyDone").innerHTML="学习结论：<span style=\"color: red\">\n" +
                                         "                    您尚未达到本次培训的学习要求，请继续努力！\n" +
                                         "                </span>"
                                }else {
                                    if(donenum==1){
                                        document.getElementById("studyDone").innerHTML="学习结论：<span style=\"color: #3784E4\">\n" +
                                            "                                        您已经达到本次培训的 C 级培训合格要求，请结合培训要求继续学习！\n" +
                                            "                                </span>"
                                    }else  if(donenum==2){
                                        document.getElementById("studyDone").innerHTML="学习结论：<span style=\"color: #3784E4\">\n" +
                                            "                                        您已经达到本次培训的 B 级培训合格要求，请结合培训要求继续学习！\n" +
                                            "                                </span>"
                                    }else  if(donenum > 2){
                                        document.getElementById("studyDone").innerHTML="学习结论：<span style=\"color: #3784E4\">\n" +
                                            "                                        您已经达到本次培训的 A 级培训合格要求，请结合培训要求继续学习！\n" +
                                            "                                </span>"
                                    }
                                }


                                document.getElementById("realName").innerHTML = realName
                                document.getElementById("userstudy").innerHTML = html
                            }
                        }
                    })
                }
                else {
                    document.getElementById("titleMes").innerHTML="未查询到用户数据！"
                }
            } else {
                console.log(data)
            }


        }
    })

    setCopyRight()
}



function getQzpxjd(){

}

