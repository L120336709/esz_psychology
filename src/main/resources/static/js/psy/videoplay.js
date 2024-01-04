var value = window.location.search;
value = decodeURIComponent(value);
var inx1 = value.indexOf("LessonId=")
var inx2 = value.indexOf("&CourseId=")
var LessonId = value.substring(inx1 + 9, inx2)
var CourseId = value.substring(inx2 + 10)


getVideoPlay()

function getVideoPlay() {
    setCopyRight()
    $.ajax({
        cache: true,
        type: "POST",
        url: "/psychology/userjd/getPsyUserjdbyUserId",
        data: {"lessonsId": LessonId},//需要获取当前用户登录
        success: function (datajd) {
            $.ajax({
                cache: true,
                type: "POST",
                url: "/psychology/course/getVideo",
                data: {"LessonId": LessonId},//需要获取当前用户登录
                success: function (data) {


                    if (data != null) {

                        var psyCLessons = data.psyCLessons
                        var kcxx = "<div style='font-size: 20px'>" + psyCLessons.name + "</div>"
                        var clessname=psyCLessons.name.split(" ")
                        if(clessname.length>1){
                            clessname=clessname[0]+"."
                        } else {
                            clessname=""
                        }
                        var psyCUnitsList = data.psyCUnitsList
                        var psyCVideosignList = data.psyCVideosignList
                        if (psyCUnitsList != null) {
                            var psyCChapterHtml = "<div class='cheaper'>"
                            var  j=1;
                            for (var i = 0; i < psyCUnitsList.length; i++) {
                                //获取每个章节的课程数据
                                if (psyCUnitsList[i].contentType == 1) {
                                    psyCChapterHtml = psyCChapterHtml + "<div><div style='padding: 10px 0;font-size: 18px'>" +
                                        clessname+(j)+"、"+psyCUnitsList[i].name +
                                        "</div>"
                                    j=j+1
                                    if (psyCVideosignList != null) {
                                        for (var l = 0; l < psyCVideosignList.length; l++) {

                                            var jds="style='font-size: 15px'";
                                            for(var jd=0;jd<datajd.length;jd++){
                                                if(datajd[jd].videoId==psyCVideosignList[l].videoId){
                                                    if(datajd[jd].done==1){
                                                        jds="style='font-size: 15px;color:#00CC7E'";
                                                    }else {
                                                        jds="style='font-size: 15px;color:blue'";
                                                    }
                                                }
                                            }

                                            if (psyCVideosignList[l].unitsId == psyCUnitsList[i].id) {
                                                psyCChapterHtml = psyCChapterHtml
                                                    + "<div style='padding: 10px 3px;margin-left: 40px;color: rgba(0,0,0,0.5)'>" +
                                                    "<img style='width: 25px;position: absolute;margin-left: -25px;' hidden id='img"
                                                    + psyCVideosignList[l].videoId + "' src='/img/psy/jiantou.png'  >" +
                                                    "<a  "+jds+" title='点击播放'  id='" + psyCVideosignList[l].videoId + "' " +
                                                    "onclick='setVideo(\"" + psyCVideosignList[l].videoId + "\")'>"
                                                    + psyCVideosignList[l].name
                                                    + "</a></div>"
                                            }

                                        }
                                    }

                                    psyCChapterHtml = psyCChapterHtml + "</div>"
                                } else if (psyCUnitsList[i].contentType == 3) {
                                    psyCChapterHtml = psyCChapterHtml + "<div><div style='padding: 5px 0;'>" +

                                        // "<a style='text-decoration: underline;color: blue;' " +
                                        // "onclick='windd(\"" + psyCUnitsList[i].contentUrl + "\")' title='点击下载' >" +
                                        "<a href='" + psyCUnitsList[i].contentUrl + "' title='点击下载' download='"+psyCUnitsList[i].name+".pdf'>" +
                                        (j)+"、"+psyCUnitsList[i].name +
                                        "</a></div></div>"
                                    j=j+1
                                } else if (psyCUnitsList[i].contentType == 6) {
                                    psyCChapterHtml = psyCChapterHtml + "<div><div style='padding: 5px 0;'>" +
                                        (j)+"、"+psyCUnitsList[i].name +
                                        "</div></div>"
                                    j=j+1
                                }


                            }
                            psyCChapterHtml = psyCChapterHtml + "</div>"
                            kcxx = kcxx + psyCChapterHtml
                        }
                        document.getElementById("kclb").innerHTML = kcxx

                    }
                }
            })
        }
    })



}


function windd(url) {

    window.open(url,'_self');
    //var newUrl=url.replace('http','https')
    //     window.open(newUrl,'_self');
}

var videoTime = 0;//本视频播放时间
var videoTimeAll = 0;
var allTime = 0;//本次学习所有时间
var videoNum = new Array();//本次学习播放的视频个数
var videoIdNow = ""

//页面刷新事件，保存进度
window.addEventListener('beforeunload', function (ev) {
    if (videoIdNow != "") {
        saveTime();
    }
    console.log('触发视频播放记录保存 --->>>' + videoIdNow)
})
//页面关闭事件，保存进度
window.addEventListener('unload', function (event) {
    saveTime();
});

//页面失去焦点，设置播放
$(document).ready(function () {
    $(window).on("blur focus", function (e) {
        var prevType = $(this).data("prevType");
        if (prevType != e.type) {   //  reduce double fire issues
            switch (e.type) {
                case "blur":
                    console.log("您离开窗口了！");
                    if(player!=null){
                        pause()
                    }

                    break;
                case "focus":
                    console.log("您回来了！");
                    break;
            }
        }

        $(this).data("prevType", e.type);
    });
    window.focus();//触发获取焦点事件
});

var options = {}
var player = null;

function seek(seekSecs) {
    player.seek(seekSecs);
}

function desPlayer() {
    player.destroy();
}

function load() {
    player.load(options.videoData);
}

function play() {
    player.resume();
}

function stop() {
    player.stop();
}

function pause() {
    player.pause();
}

function getlog() {
    player.getLog(function (t) {
        alert(t);
    });
}

var doness = 0
var videoidlast=null
function setVideo(videoId) {
    document.getElementById("img"+videoId).hidden=false
    if(videoidlast!=null){
        document.getElementById("img"+videoidlast).hidden=true

    }

    if(document.getElementById(videoId).style.color!="rgb(0, 204, 126)"){
        document.getElementById(videoId).style.color="blue"
    }
    videoidlast=videoId


    doness = 0
    if (videoIdNow != "") {
        saveTime();
    }

    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        if (ca[i].indexOf("videoNum=") >= 0) {
            if (ca[i].substring(ca[i].indexOf("videoNum=" + 9)).length != 0) {
                videoNum = ca[i].substring(ca[i].indexOf("videoNum=") + 9).split(",");
            }
        }
    }
    var boo = true
    for (var i = 0; i < videoNum.length; i++) {
        if (videoNum[i] == videoId) {
            boo = false
        }
    }
    if (boo == true) {
        videoNum.push(videoId)
        document.cookie = 'videoNum=' + videoNum + '; path=https://xypx.eszedu.com/psychology';
    }
    videoIdNow = videoId

    $.ajax({
        cache: true,
        type: "POST",
        async: true,
        url: "/psychology/course/getSign",
        data: {"videoId": videoId},
        beforeSend : function () {
            document.getElementById("video").innerHTML=" <div " +
                "style=\"font-size: 1.3em;width: 200px;padding:236px 250px;display: block;background-color: black;color: white\">" +
                "<span>视频加载中</span> <img style='position: absolute;width: 30px' src='/img/loading2.gif'>\n" +
                "</div>"
        },
        success: function (data) {
            if (data.code == 200) {
                $.ajax({
                    cache: true,
                    async: true,
                    type: "POST",
                    url: "/psychology/userjd/getPsyUserjdbyUserId",
                    data: {"videoId": videoId},
                    success: function (list) {
                        document.getElementById('video').innerHTML = ""
                        var starttime=0
                        if (list != null && list.length > 0) {
                            if (list[0].timlong != null) {
                                videoTime = list[0].timlong
                                starttime = list[0].timlong
                                layer.msg("自动跳转到上次播放时间：" + list[0].timlong, {time: 1000})
                            }
                        }

                        options = {
                            parent: document.getElementById('video'),
                            autoStart: true,
                            videoData: {
                                videoId: videoId,
                                signature: data.msg,
                                start:  starttime
                            }
                        };
                        player = EduPlayer.create(options);

                        player.$on('onPlayEnd', function (data2) {
                            document.getElementById(videoId).style.color="#00CC7E"
                            player.$on('onSeek', function (data) {
                                setTimeout(function () {
                                    player.seek(data.newData);
                                    //seek(data.newData)
                                }, 10);
                            });
                        })
                        player.$on('onSeek', function (data) {
                            player.getPosition(function (pos) {
                                if (data.newData > pos) {
                                    var listdon = 0;
                                    if (list != null && list.length > 0) {
                                        if (list[0].done == 1) {
                                            listdon = 1;
                                        }
                                    }
                                    if (listdon == 0) {
                                        setTimeout(function () {
                                            if (doness == 0) {
                                                layer.msg("完成本视频的学习后可以快进！", {time: 2000})
                                            }
                                            player.seek(pos);
                                            //seek(pos)
                                        }, 10);
                                    }
                                }
                            });
                        });
                        player.$on('onTimeupdate', function (e) {
                            if (videoTime != e.currentTime) {
                                allTime++
                                document.cookie = 'allTime=' + allTime + '; path=https://xypx.eszedu.com/psychology/videoplay';
                            }
                            //保存时间，关闭时
                            videoTime = e.currentTime
                            videoTimeAll = e.duration
                            document.cookie = 'videoTimeAll=' + videoTimeAll + '; path=https://xypx.eszedu.com/psychology';
                        });
                        player.$on('onPause', function () {
                            saveTime()
                        });
                    }
                })
            }
        }
    })
}
