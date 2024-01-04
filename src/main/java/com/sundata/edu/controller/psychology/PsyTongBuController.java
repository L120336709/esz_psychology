package com.sundata.edu.controller.psychology;


import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sundata.edu.dao.*;
import com.sundata.edu.domain.*;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.service.IPsyCChaptersService;
import com.sundata.edu.service.IPsyCCourseService;
import com.sundata.edu.service.IPsyCStructureService;
import com.sundata.edu.service.IPsyXExercisesService;
import com.sundata.edu.util.DateUttils;
import com.sundata.edu.util.psy.HttpUtils;
import com.sundata.edu.vo.UserinfoVo;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Controller
@RequestMapping("/psychology/course")
public class PsyTongBuController extends BaseController {


    @Autowired
    private IPsyCCourseService psyCCourseService;
    @Autowired
    private IPsyCStructureService psyCStructureService;
    @Autowired
    private PsyCVideosignMapper psyCVideosignMapper;
    @Autowired
    private PsyCUnitsMapper psyCUnitsMapper;
    @Autowired
    private PsyCLessonsMapper psyCLessonsMapper;
    @Autowired
    private PsyCChaptersMapper psyCChaptersMapper;
    @Autowired
    private PsyUserjdMapper psyUserjdMapper;
    @Autowired
    private PsyRecordMapper psyRecordMapper;
    @Autowired
    private PsyUserstudyMapper psyUserstudyMapper;
    @Autowired
    private IPsyXExercisesService psyXExercisesService;

    //存储，分别每个视频、每次学习、总的学习，
    @PostMapping("/saveTime")
    @ResponseBody
    public AjaxResult saveTime(Long videoId, Long LessonId, Long CourseId, Long videoTime, Long videoTimeAll, Long videoNum, Long allTime, Long startTime) {
        AjaxResult ajaxResult = new AjaxResult();
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        //判断当前课时是否已学习
        PsyUserjd psyUserjd = new PsyUserjd();
        psyUserjd.setVideoId(videoId);
        psyUserjd.setUserId(userInfoVO.getUserId());
        List<PsyUserjd> psyUserjdList = psyUserjdMapper.selectPsyUserjdList(psyUserjd);


        int done = 0;
        PsyUserjd psyUserjdold = new PsyUserjd();

        if (psyUserjdList.size() == 0) {
            psyUserjd.setLessonsId(LessonId);
            psyUserjd.setKcId(CourseId);
            psyUserjd.setTimlong(videoTime);

            psyUserjd.setCreateTime(new Date());
            if (videoTimeAll <= videoTime) {
                psyUserjd.setDone("1");
                psyUserjd.setDoneTime(new Date());
                done = 1;
            } else {
                psyUserjd.setDone("0");
            }
            psyUserjdMapper.insertPsyUserjd(psyUserjd);
        } else if (psyUserjdList.size() == 1) {
            psyUserjdold = psyUserjdList.get(0);

            //本视频播放时间保存，未播放完成时，按照已播放的最长时间保存，播放完成后，按照每次播放的时间保存
            if (psyUserjdold.getDone().equals("0")) {
                if (videoTime > psyUserjdold.getTimlong()) {
                    psyUserjdold.setTimlong(videoTime);
                }

                //判断是否播放完成，并设置完成本视频学学习的结束时间
                if (videoTimeAll <= videoTime) {
                    psyUserjdold.setDone("1");
                    psyUserjdold.setDoneTime(new Date());
                    done = 1;
                }
            } else if (psyUserjdold.getDone().equals("1")) {
                psyUserjdold.setTimlong(videoTime);
            }

            psyUserjdMapper.updatePsyUserjd(psyUserjdold);
        } else {
            return AjaxResult.error("当前课时存储多条数据，请联系管理员处理！");
        }

        //存储本次学习的所有进度
        PsyRecord psyRecord = new PsyRecord();
        //psyRecord.setKcId(CourseId);
        psyRecord.setUserId(userInfoVO.getUserId());
        psyRecord.setStartTime(startTime);
        psyRecord.setKcId(CourseId);

        List<PsyRecord> psyRecordList = psyRecordMapper.selectPsyRecordList(psyRecord);
        if (psyRecordList.size() == 0) {
            psyRecord.setGetHour(allTime + "");
            if (psyRecord.getGetHour() != null && !psyRecord.getGetHour().equals("null") && psyRecord.getGetHour() != "") {
                psyRecord.setGetHour(allTime + "");
            }
            psyRecord.setGetClasshour(videoNum + "");
            psyRecord.setEndTime(new Date().getTime());


            psyRecordMapper.insertPsyRecord(psyRecord);
        } else if (psyRecordList.size() == 1) {
            PsyRecord psyRecordold = psyRecordList.get(0);
            if (psyRecordold.getGetHour() != null && !psyRecordold.getGetHour().equals("null") && psyRecordold.getGetHour() != "") {
                psyRecordold.setGetHour(allTime + Integer.parseInt(psyRecordold.getGetHour()) + "");
            }
            psyRecordold.setGetClasshour(videoNum + "");
            psyRecordold.setEndTime(new Date().getTime());
            psyRecordMapper.updatePsyRecord(psyRecordold);
        } else {
            return AjaxResult.error("本次学习进度记录存在多条数据，请联系管理员处理！");
        }


        //存储本次对应课程的总进度

        PsyUserstudy psyUserstudy = new PsyUserstudy();
        psyUserstudy.setUserId(userInfoVO.getUserId());
        psyUserstudy.setKcId(CourseId);

        List<PsyUserstudy> psyUserstudyList = psyUserstudyMapper.selectPsyUserstudyList(psyUserstudy);
        if (psyUserstudyList.size() == 0) {
            PsyCCourse psyCCourse = psyCCourseService.selectPsyCCourseById(CourseId);
            psyUserstudy.setKcName(psyCCourse.getName());
            psyUserstudy.setAllClasshour(psyCCourse.getAllClasshour());

            psyUserstudy.setGetHour(allTime + "");
            psyUserstudy.setSchedule("0");

            if (done == 1) {
                if (psyUserjdold != null) {
                    if (psyUserjdold.getDone() != null && psyUserjdold.getDone().equals("1")) {
                        psyUserstudy.setGetClasshour(Integer.parseInt(psyUserstudy.getGetClasshour()) + 1 + "");
                        psyUserstudy.setSchedule(String.format("%.3f", Double.parseDouble(psyUserstudy.getGetClasshour())
                                / Double.parseDouble(psyUserstudy.getAllClasshour())));
                    }
                }
            } else {
                psyUserstudy.setGetClasshour("0");
            }


            if (psyUserstudy.getGetHour() != null && !psyUserstudy.getGetHour().equals("null") && psyUserstudy.getGetHour() != "") {
                psyUserstudy.setGetHour(allTime + "");
            }

            if (psyUserstudy.getGetClasshour() != null && psyUserstudy.getGetClasshour().equals(psyUserstudy.getAllClasshour())) {
                psyUserstudy.setDone("1");
            } else {
                psyUserstudy.setDone("0");
            }

            psyUserstudyMapper.insertPsyUserstudy(psyUserstudy);
        } else if (psyUserstudyList.size() == 1) {
            PsyUserstudy psyUserstudyold = psyUserstudyList.get(0);
            if (psyUserjdold != null) {
                if (psyUserstudyold.getGetHour() != null && !psyUserstudyold.getGetHour().equals("null") && psyUserstudyold.getGetHour() != "") {
                    psyUserstudyold.setGetHour(allTime + Integer.parseInt(psyUserstudyold.getGetHour()) + "");
                }
            }

            if (done == 1) {
                if (psyUserjdold != null) {
                    if (psyUserjdold.getDone() != null && psyUserjdold.getDone().equals("1")) {
                        if (psyUserstudyold.getGetClasshour() == null) {
                            psyUserstudyold.setGetClasshour(1 + "");
                        } else {
                            psyUserstudyold.setGetClasshour(Integer.parseInt(psyUserstudyold.getGetClasshour()) + 1 + "");
                        }
                        psyUserstudyold.setSchedule(String.format("%.3f", Double.parseDouble(psyUserstudyold.getGetClasshour())
                                / Double.parseDouble(psyUserstudyold.getAllClasshour())));
                    }
                }
            }


            if (psyUserstudyold.getGetClasshour() != null && psyUserstudyold.getGetClasshour().equals(psyUserstudyold.getAllClasshour())) {
                psyUserstudyold.setDone("1");
            }

            psyUserstudyMapper.updatePsyUserstudy(psyUserstudyold);
        } else {
            return AjaxResult.error("本次学习进度记录存在多条数据，请联系管理员处理！");
        }


        return ajaxResult;
    }


    @PostMapping("/getVideo")
    @ResponseBody
    public AjaxResult getVideo(Long LessonId) {
        AjaxResult ajaxResult = new AjaxResult();

        PsyCLessons psyCLessons = psyCLessonsMapper.selectPsyCLessonsById(LessonId);
        ajaxResult.put("psyCLessons", psyCLessons);
        List<Long> lessons = new ArrayList<>();
        lessons.add(LessonId);

        List<PsyCUnits> psyCUnitsList = psyCUnitsMapper.selectPsyCUnitsListBylessonsId(lessons);
        ajaxResult.put("psyCUnitsList", psyCUnitsList);

        if (psyCUnitsList.size() > 0) {
            List<Long> unitsIds = new ArrayList<>();
            for (PsyCUnits psyCUnits : psyCUnitsList) {
                unitsIds.add(psyCUnits.getId());
            }
            List<PsyCVideosign> psyCVideosignList = psyCVideosignMapper.selectPsyCVideosignListByunitsId(unitsIds);
            ajaxResult.put("psyCVideosignList", psyCVideosignList);
        }
        return ajaxResult;
    }


    @PostMapping("/getKclb")
    @ResponseBody
    public AjaxResult getKclb(Long CourseId) {
        AjaxResult ajaxResult = new AjaxResult();
        PsyCCourse psyCCourse = psyCCourseService.selectPsyCCourseById(CourseId);
        ajaxResult.put("psyCCourse", psyCCourse);
        //根据课程id，获取1个结构id，多章节、多节、多课件、1视频
        PsyCStructure psyCStructure1 = new PsyCStructure();
        psyCStructure1.setCourseId(CourseId);
        List<PsyCStructure> psyCStructureList = psyCStructureService.selectPsyCStructureList(psyCStructure1);


        if (psyCStructureList.size() == 1) {
            PsyCStructure psyCStructure = psyCStructureList.get(0);
            ajaxResult.put("psyCStructure", psyCStructure);
            PsyCChapters psyCChapters0 = new PsyCChapters();
            psyCChapters0.setStructureId(psyCStructure.getId());

            List<PsyCChapters> psyCChaptersList = psyCChaptersMapper.selectPsyCChaptersList(psyCChapters0);
            if (psyCChaptersList.size() > 0) {
                ajaxResult.put("psyCChaptersList", psyCChaptersList);
                if (psyCChaptersList.size() > 0) {
                    List<Long> cheaperids = new ArrayList<>();
                    for (PsyCChapters psyCChapters : psyCChaptersList) {
                        cheaperids.add(psyCChapters.getId());
                    }
                    List<PsyCLessons> psyCLessonsList = psyCLessonsMapper.selectPsyCLessonsListByCheaperIds(cheaperids);
                    ajaxResult.put("psyCLessonsList", psyCLessonsList);
                    if (psyCLessonsList.size() > 0) {
                        List<Long> lessons = new ArrayList<>();
                        for (PsyCLessons psyCLessons : psyCLessonsList) {
                            lessons.add(psyCLessons.getId());
                        }
                        List<PsyCUnits> psyCUnitsList = psyCUnitsMapper.selectPsyCUnitsListBylessonsId(lessons);
                        //  ajaxResult.put("psyCUnitsList",psyCUnitsList);

                        if (psyCUnitsList.size() > 0) {
                            List<Long> unitsIds = new ArrayList<>();
                            for (PsyCUnits psyCUnits : psyCUnitsList) {
                                unitsIds.add(psyCUnits.getId());
                            }
                            List<PsyCVideosign> psyCVideosignList = psyCVideosignMapper.selectPsyCVideosignListByunitsId(unitsIds);
                            // ajaxResult.put("psyCVideosignList",psyCVideosignList);

                            if (psyCVideosignList.size() > 0) {
                                PsyUserjd psyUserjd = new PsyUserjd();
                                UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
                                psyUserjd.setUserId(userInfoVO.getUserId());
                                List<PsyUserjd> psyUserjdList = psyUserjdMapper.selectPsyUserjdList(psyUserjd);

                                for (PsyCLessons psyCLessons : psyCLessonsList) {

                                    int file=0;
                                    for (PsyCUnits psyCUnits : psyCUnitsList) {
                                        if (psyCUnits.getLessonsId().equals(psyCLessons.getId())) {
                                            if (psyCUnits.getContentType() == 1) {
                                                if(file==0){
                                                    file=2;
                                                }
                                                List<String> videoId = new ArrayList<>();
                                                for (PsyCVideosign psyCVideosign : psyCVideosignList) {
                                                    if (psyCVideosign.getUnitsId().equals(psyCUnits.getId())) {
                                                        videoId.add(psyCVideosign.getVideoId() + "");
                                                    }
                                                }
                                                Integer boo = 0;
                                                for (String v : videoId) {
                                                    Integer boovideo = 0;
                                                    for (PsyUserjd psyUserjd1 : psyUserjdList) {
                                                        if (v.equals(psyUserjd1.getVideoId() + "")) {
                                                            if (psyUserjd1.getDone().equals("1")) {
                                                                boovideo = 1;
                                                            } else {
                                                                boovideo = 2;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    //没有进度时，也是false
                                                    if (boovideo == 1) {
                                                        boo = 1;
                                                    }
                                                    if (boovideo == 2) {
                                                        boo = 2;
                                                        break;
                                                    }
                                                }

                                                if (psyCLessons.getDone() == null || psyCLessons.getDone().equals("0")) {
                                                    psyCLessons.setDone(boo + "");
                                                } else {
                                                    if (boo != 0) {
                                                        psyCLessons.setDone(boo + "");
                                                    } else {
                                                        psyCLessons.setDone("2");
                                                    }
                                                }

                                                //存在有未完成的课程，直接设置为本章节进行中
                                                if (boo == 2) {
                                                    break;
                                                }

                                            }
                                        }

                                    }


                                    //遍历完成以后，如果psyCLessons只有包含文件，则设置为3
                                    if(file==0){
                                        psyCLessons.setDone("3");
                                    }
                                }
                            }
                        }
                    }


                }
            }

        } else {

        }

        return ajaxResult;
    }

    @PostMapping("/downloadFile")
    @ResponseBody
    public AjaxResult getdownloadFile() {
        String downloadUrl = "http://nos.netease.com/edu-lesson-pdfsrc/EE17473C0E8D7CBC1909097D90FF8E50-1557367862057?download=%E5%AF%BC%E8%A8%80.pdf&Signature=PhWZeOpAEJr8yBQUi%2F4WbxvbpBMBhrCwVgwqAnHH9WE%3D&Expires=1703728970&NOSAccessKeyId=7db2f370ff9a412987155d36d55a6ead";
        String path = "F:\\05-SDProject\\testFile\\1\\";
        int s1 = downloadFile(downloadUrl, path, "t3.pdf");
        System.err.println(s1);
        return toAjax(1);
    }


    public static int downloadFile(String downloadUrl, String path, String filename) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        File filepath = new File(path);
        if (!filepath.exists() && !filepath.isDirectory()) {
            filepath.mkdirs();
        }


        try {
            URL url = new URL(downloadUrl);
            //这里没有使用 封装后的ResponseEntity 就是也是因为这里不适合一次性的拿到结果，放不下content,会造成内存溢出
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            //使用bufferedInputStream 缓存流的方式来获取下载文件，不然大文件会出现内存溢出的情况
            inputStream = new BufferedInputStream(connection.getInputStream());

            File file = new File(path + filename);
            if (file.exists()) {
                file.delete();
            }
            outputStream = new FileOutputStream(file);
            //这里也很关键每次读取的大小为5M 不一次性读取完
            byte[] buffer = new byte[1024 * 1024 * 5];// 5MB
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }
        return 1;
    }

    //getzj("1467863451");
    //getctId();
    @PostMapping("/getzj")
    @ResponseBody
    public AjaxResult getzj() throws NoSuchAlgorithmException {
        List<PsyCCourse> psyCCourseList = psyCCourseService.selectPsyCCourseList(new PsyCCourse());
        if (psyCCourseList.size() > 0) {
            String success = "";
            for (PsyCCourse psyCCourse : psyCCourseList) {
                String appId = "6805c77c4c098437de01c8b087508f04";
                Long nonce = DateUttils.getNowDate().getTime();
                String signature = hashData(nonce);
                String param = "appId=" + appId + "&nonce=" + nonce + "&signature=" + signature + "&timestamp=" + nonce
                        + "&termId=" + psyCCourse.getCtId();
                String result = HttpUtils.sendGet("https://www.icourse163.org/mm-open-api/open/term/structure", param);

                JSONObject jsonObject = JSONObject.parseObject(result);
                String resultCode = jsonObject.getString("code");//0000
                String error = "";
                int allhour = 0;
                if (resultCode.equals("200")) {
                    JSONObject jsonArray = JSONObject.parseObject(jsonObject.getString("result"));

                    PsyCStructure psyCStructure = new PsyCStructure();
                    psyCStructure.setId(jsonArray.getLong("id"));
                    psyCStructure.setCourseName(jsonArray.getString("courseName"));
                    psyCStructure.setCourseId(psyCCourse.getId());

                    String chapters = jsonArray.getString("chapters");
                    JSONArray chaptersArrays = (JSONArray) JSONArray.parse(chapters);

                    List<PsyCChapters> psyCChaptersList = new ArrayList<>();
                    List<PsyCLessons> psyCLessonsList = new ArrayList<>();
                    List<PsyCUnits> psyCUnitsList = new ArrayList<>();
                    List<PsyCVideosign> psyCVideosignList = new ArrayList<>();
                    if (chaptersArrays.size() > 0) {
                        for (int c = 0; c < chaptersArrays.size(); c++) {
                            JSONObject chaptersArrayList = JSONObject.parseObject(chaptersArrays.get(c).toString());

                            PsyCChapters psyCChapters = new PsyCChapters();
                            psyCChapters.setId(chaptersArrayList.getLong("id"));
                            psyCChapters.setName(chaptersArrayList.getString("name"));
                            psyCChapters.setPosition(chaptersArrayList.getLong("position"));
                            psyCChapters.setGmtModified(chaptersArrayList.getLong("gmtModified"));
                            psyCChapters.setReleaseTime(chaptersArrayList.getLong("releaseTime"));
                            psyCChapters.setStructureId(psyCStructure.getId());

                            psyCChaptersList.add(psyCChapters);

                            String lessons = chaptersArrayList.getString("lessons");
                            JSONArray lessonsArrays = (JSONArray) JSONArray.parse(lessons);
                            if (lessonsArrays != null) {
                                for (int l = 0; l < lessonsArrays.size(); l++) {
                                    JSONObject lessonsArrayList = JSONObject.parseObject(lessonsArrays.get(l).toString());
                                    PsyCLessons psyCLessons = new PsyCLessons();
                                    psyCLessons.setId(lessonsArrayList.getLong("id"));
                                    psyCLessons.setName(lessonsArrayList.getString("name"));
                                    psyCLessons.setPosition(lessonsArrayList.getLong("position"));
                                    psyCLessons.setGmtModified(lessonsArrayList.getLong("gmtModified"));
                                    psyCLessons.setReleaseTime(lessonsArrayList.getLong("releaseTime"));
                                    psyCLessons.setChapersId(psyCChapters.getId());

                                    psyCLessonsList.add(psyCLessons);

                                    String units = lessonsArrayList.getString("units");
                                    JSONArray unitsArrays = (JSONArray) JSONArray.parse(units);
                                    if (unitsArrays != null) {
                                        for (int u = 0; u < unitsArrays.size(); u++) {
                                            JSONObject unitsArrayList = JSONObject.parseObject(unitsArrays.get(u).toString());
                                            PsyCUnits psyCUnits = new PsyCUnits();
                                            psyCUnits.setId(unitsArrayList.getLong("id"));
                                            psyCUnits.setName(unitsArrayList.getString("name"));
                                            psyCUnits.setPosition(unitsArrayList.getLong("position"));
                                            psyCUnits.setContentType(unitsArrayList.getLong("contentType"));
                                            String tentUrl = unitsArrayList.getString("contentUrl");
                                            if (psyCUnits.getContentType() == 3) {

                                                String path = "/data/project/xljkpx2023/apache-tomcat-8.5.95/esz_psychology/WEB-INF/classes/static/file/" + psyCCourse.getId() + "/" +
                                                        psyCStructure.getId() + "/" + psyCChapters.getId() + "/" + psyCLessons.getId() + "/";
                                                int s = downloadFile(tentUrl, path, psyCUnits.getId() + ".pdf");
                                                if (s == 1) {
                                                    tentUrl = "https://xypx.eszedu.com/file/" + psyCCourse.getId() + "/" +
                                                            psyCStructure.getId() + "/" + psyCChapters.getId() + "/" + psyCLessons.getId() + "/" + psyCUnits.getId() + ".pdf";
                                                }
                                            }

                                            psyCUnits.setContentUrl(tentUrl);
                                            psyCUnits.setGmtModified(unitsArrayList.getLong("gmtModified"));
                                            psyCUnits.setLessonsId(psyCLessons.getId());

                                            psyCUnitsList.add(psyCUnits);


                                            String videoSign = unitsArrayList.getString("videoSign");
                                            JSONObject videoSignArrays = JSONObject.parseObject(videoSign);
                                            if (videoSignArrays != null) {
                                                PsyCVideosign psyCVideosign = new PsyCVideosign();
                                                psyCVideosign.setVideoId(videoSignArrays.getLong("videoId"));
                                                psyCVideosign.setName(videoSignArrays.getString("name"));
                                                psyCVideosign.setStatus(videoSignArrays.getLong("status"));
                                                psyCVideosign.setDuration(videoSignArrays.getLong("duration"));
                                                psyCVideosign.setVideoImgUrl(videoSignArrays.getString("videoImgUrl"));
                                                psyCVideosign.setSignature(videoSignArrays.getString("signature"));
                                                psyCVideosign.setUnitsId(psyCUnits.getId());

                                                psyCVideosignList.add(psyCVideosign);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        success = "  " + psyCStructure.getCourseName() + "没有课程信息";
                    }


                    //保存课程结构表
                    //psyCStructure
                    //根据id获取旧数据，有则更新，没有就插入
                    PsyCStructure psyCStructureOld = psyCStructureService.selectPsyCStructureById(psyCStructure.getId());
                    if (psyCStructureOld != null) {
                        psyCStructureService.updatePsyCStructure(psyCStructure);
                    } else {
                        psyCStructureService.insertPsyCStructure(psyCStructure);
                    }


                    //保存章节表，根据id批量获取旧数据，有则批量更新，没有就批量插入
                    List<String> PsyCChaptersIds = new ArrayList<>();
                    if (psyCChaptersList.size() > 0) {
                        for (PsyCChapters psyCChapters : psyCChaptersList) {
                            PsyCChaptersIds.add(psyCChapters.getId() + "");
                        }
                        List<PsyCChapters> psyCChaptersOldList = psyCChaptersMapper.selectPsyCChaptersListByIds(PsyCChaptersIds);
                        List<PsyCChapters> psyCChaptersUpdate = new ArrayList<>();
                        List<PsyCChapters> psyCChaptersInsert = new ArrayList<>();
                        for (PsyCChapters psyCChapters : psyCChaptersList) {
                            boolean boo = true;
                            for (PsyCChapters psyCChaptersOld : psyCChaptersOldList) {
                                if (psyCChaptersOld.getId().equals(psyCChapters.getId())) {
                                    boo = false;
                                }
                            }
                            if (boo == true) {
                                psyCChaptersInsert.add(psyCChapters);
                            } else {
                                psyCChaptersUpdate.add(psyCChapters);
                            }
                        }
                        if (psyCChaptersUpdate.size() > 0) {
                            psyCChaptersMapper.updatePsyCChaptersList(psyCChaptersUpdate);
                        }
                        if (psyCChaptersInsert.size() > 0) {
                            psyCChaptersMapper.insertPsyCChaptersList(psyCChaptersInsert);
                        }
                    }

                    // List<PsyCChapters> psyCChaptersList=new ArrayList<>();
                    //                    List<PsyCLessons> psyCLessonsList=new ArrayList<>();
                    //                    List<PsyCUnits> psyCUnitsList=new ArrayList<>();
                    //                    List<PsyCVideosign> psyCVideosignList=new ArrayList<>();
                    //保存节表
                    List<String> PsyCLessonsIds = new ArrayList<>();
                    if (psyCLessonsList.size() > 0) {
                        for (PsyCLessons psyCLessons : psyCLessonsList) {
                            PsyCLessonsIds.add(psyCLessons.getId() + "");
                        }
                        List<PsyCLessons> psyCLessonsOldList = psyCLessonsMapper.selectPsyCLessonsListByIds(PsyCLessonsIds);
                        List<PsyCLessons> psyCLessonsUpdate = new ArrayList<>();
                        List<PsyCLessons> psyCLessonsInsert = new ArrayList<>();
                        for (PsyCLessons psyCLessons : psyCLessonsList) {
                            boolean boo = true;
                            for (PsyCLessons psyCLessonsOld : psyCLessonsOldList) {
                                if (psyCLessonsOld.getId().equals(psyCLessons.getId())) {
                                    boo = false;
                                }
                            }
                            if (boo == true) {
                                psyCLessonsInsert.add(psyCLessons);
                            } else {
                                psyCLessonsUpdate.add(psyCLessons);
                            }
                        }
                        if (psyCLessonsUpdate.size() > 0) {
                            psyCLessonsMapper.updatePsyCLessonsList(psyCLessonsUpdate);
                        }
                        if (psyCLessonsInsert.size() > 0) {
                            psyCLessonsMapper.insertPsyCLessonsList(psyCLessonsInsert);
                        }
                    }


                    //保存课件表
                    List<String> psyCUnitsListIds = new ArrayList<>();
                    if (psyCUnitsList.size() > 0) {
                        for (PsyCUnits psyCUnits : psyCUnitsList) {
                            psyCUnitsListIds.add(psyCUnits.getId() + "");
                        }
                        List<PsyCUnits> psyCUnitsOldList = psyCUnitsMapper.selectPsyCUnitsListByIds(psyCUnitsListIds);
                        List<PsyCUnits> psyCUnitsUpdate = new ArrayList<>();
                        List<PsyCUnits> psyCUnitsInsert = new ArrayList<>();
                        for (PsyCUnits psyCUnits : psyCUnitsList) {
                            boolean boo = true;
                            for (PsyCUnits psyCUnitsOld : psyCUnitsOldList) {
                                if (psyCUnitsOld.getId().equals(psyCUnits.getId())) {
                                    boo = false;
                                }
                            }
                            if (boo == true) {
                                psyCUnitsInsert.add(psyCUnits);
                            } else {
                                psyCUnitsUpdate.add(psyCUnits);
                            }
                        }
                        if (psyCUnitsUpdate.size() > 0) {
                            psyCUnitsMapper.updatePsyCUnitsList(psyCUnitsUpdate);
                        }
                        if (psyCUnitsInsert.size() > 0) {
                            psyCUnitsMapper.insertPsyCUnitsList(psyCUnitsInsert);
                        }
                    }


                    //保存视频信息表
                    List<String> psyCVideosignIds = new ArrayList<>();
                    if (psyCVideosignList.size() > 0) {
                        for (PsyCVideosign psyCVideosign : psyCVideosignList) {
                            psyCVideosignIds.add(psyCVideosign.getVideoId() + "");
                        }
                        List<PsyCVideosign> psyCVideosignOldList = psyCVideosignMapper.selectPsyCVideosignListByIds(psyCVideosignIds);
                        List<PsyCVideosign> psyCVideosignUpdate = new ArrayList<>();
                        List<PsyCVideosign> psyCVideosignInsert = new ArrayList<>();
                        for (PsyCVideosign psyCVideosign : psyCVideosignList) {
                            boolean boo = true;
                            for (PsyCVideosign psyCVideosignOld : psyCVideosignOldList) {
                                if (psyCVideosignOld.getVideoId().equals(psyCVideosign.getVideoId())) {
                                    boo = false;
                                }
                            }
                            if (boo == true) {
                                psyCVideosignInsert.add(psyCVideosign);
                            } else {
                                psyCVideosignUpdate.add(psyCVideosign);
                            }
                        }
                        if (psyCVideosignUpdate.size() > 0) {
                            psyCVideosignMapper.updatePsyCVideosignList(psyCVideosignUpdate);
                        }
                        if (psyCVideosignInsert.size() > 0) {
                            psyCVideosignMapper.insertPsyCVideosignList(psyCVideosignInsert);
                        }

                    }

                    allhour = psyCVideosignList.size();
                } else {
                    System.err.println("====接口获取数据失败====");
                    error = "====接口获取数据失败====";
                    return AjaxResult.error(error);
                }

                psyCCourse.setAllClasshour(allhour + "");

                psyCCourseService.updatePsyCCourse(psyCCourse);
            }
            return AjaxResult.success(success);
        } else {
            return AjaxResult.success("暂无课程信息，获取课程结构为空！");
        }
    }

    @Autowired
    private IPsyCChaptersService psyCChaptersService;

    @PostMapping("/getSign")
    @ResponseBody
    public AjaxResult getSign(Long videoId) throws NoSuchAlgorithmException {
        String appId = "6805c77c4c098437de01c8b087508f04";
        Long nonce = DateUttils.getNowDate().getTime();
        String signature = hashData(nonce);
        String param = "appId=" + appId + "&nonce=" + nonce + "&signature=" + signature + "&timestamp=" + nonce
                + "&videoId=" + videoId;
        String result = HttpUtils.sendGet("https://www.icourse163.org/mm-open-api/open/video/videoSign", param);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String resultCode = jsonObject.getString("code");//0000
        if (resultCode.equals("200")) {
            JSONObject jsonArray = JSONObject.parseObject(jsonObject.getString("result"));
            String videSignature = jsonArray.getString("signature");
            return AjaxResult.success(videSignature);
        } else {
            return AjaxResult.error();
        }

    }

    @PostMapping("/getQuestions")
    @ResponseBody
    public AjaxResult getQuestions(String cid) throws NoSuchAlgorithmException {
        String appId = "6805c77c4c098437de01c8b087508f04";
        Long nonce = DateUttils.getNowDate().getTime();
        String signature = hashData(nonce);
        String param = "appId=" + appId + "&nonce=" + nonce + "&signature="
                + signature + "&timestamp=" + nonce;

        // 携带的body数据构造
        String bodyStr = "[\"" + cid + "\"]";
        //        String bodyStr ="[\"1466749188\",\"1466753194\"]"  ;
        String reponse = "";
        HttpResponse result = (HttpResponse) HttpUtil.createGet("https://www.icourse163.org/mm-open-api/open/course/questions?" + param)
                .header(Header.ACCEPT_ENCODING, "gzip, deflate, br")
                .header(Header.ACCEPT, "*/*")
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.CONNECTION, "keep-alive")
                .body(bodyStr) //携带数据
                .execute();
        reponse = result.body();

        System.err.println(reponse);

        JSONObject jsonObject = JSONObject.parseObject(reponse);

        String resultCode = jsonObject.getString("code");//0000
        String error = "";
        if (resultCode.equals("200")) {
            JSONArray jsonArrays = (JSONArray) JSONArray.parse(jsonObject.getString("result"));
            for (int i = 0; i < jsonArrays.size(); i++) {
                JSONObject jsonArray2 = JSONObject.parseObject(jsonArrays.get(i).toString());
                JSONArray jsonArrays3 = (JSONArray) JSONArray.parse(jsonArray2.getString("questions"));
                if (jsonArrays3 != null) {
                    for (int y = 0; y < jsonArrays3.size(); y++) {
                        JSONObject jsonArray4 = JSONObject.parseObject(jsonArrays3.get(y).toString());

                        PsyXExercises psyXExercises = new PsyXExercises();

                        psyXExercises.setId(jsonArray4.getLong("id"));
                        //获取课程id和课程名
                        psyXExercises.setKcId(jsonArray2.getLong("courseId"));
                        psyXExercises.setKcName(jsonArray2.getString("name"));

                        psyXExercises.setType(jsonArray4.getString("type"));
                        psyXExercises.setOptionDtos(jsonArray4.getString("optionDtos"));
                        psyXExercises.setPosition(jsonArray4.getLong("position"));

                        psyXExercises.setScore(jsonArray4.getLong("score"));

                        psyXExercises.setTitle(jsonArray4.getString("title"));

                        psyXExercises.setPlainTextTitle(jsonArray4.getString("plainTextTitle"));
                        psyXExercises.setTitleAttachment(jsonArray4.getString("titleAttachment"));
                        psyXExercises.setSampleAnswerJson(jsonArray4.getString("sampleAnswerJson"));
                        psyXExercises.setStdAnswer(jsonArray4.getString("stdAnswer"));
                        psyXExercises.setAnalyse(jsonArray4.getString("analyse"));
                        psyXExercises.setFillblankType(jsonArray4.getString("fillblankType"));
                        psyXExercises.setJudgerules(jsonArray4.getString("judgerules"));

                        PsyXExercises psyXExercisesOld = psyXExercisesService.selectPsyXExercisesById(psyXExercises.getId());
                        if (psyXExercisesOld != null) {
                            psyXExercisesService.updatePsyXExercises(psyXExercises);
                        } else {
                            psyXExercisesService.insertPsyXExercises(psyXExercises);
                        }

                    }
                }
            }
            return AjaxResult.success("数据获取并保存成功！");
        } else {
            System.err.println("====接口获取数据失败====");
            return AjaxResult.error(reponse);
        }

    }

    @PostMapping("/getctId")
    @ResponseBody
    //获取所有课程信息，保存到课程表中，主要获取ctid
    public AjaxResult getctId() throws NoSuchAlgorithmException {
        String appId = "6805c77c4c098437de01c8b087508f04";
        Long nonce = DateUttils.getNowDate().getTime();
        String signature = hashData(nonce);
        String param = "appId=" + appId + "&nonce=" + nonce + "&signature=" + signature + "&timestamp=" + nonce
                + "&pageSize=10&pageIndex=1&isNeedAppId=false";
        String result = HttpUtils.sendGet("https://www.icourse163.org/mm-open-api/open/courses/permission", param);
        System.err.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String resultCode = jsonObject.getString("code");//0000
        String error = "";
        if (resultCode.equals("200")) {
            JSONObject jsonArray = JSONObject.parseObject(jsonObject.getString("result"));
            String list = jsonArray.getString("list");
            JSONArray jsonArrays = (JSONArray) JSONArray.parse(list);
            if (jsonArrays.size() > 0) {
                for (int i = 0; i < jsonArrays.size(); i++) {
                    JSONObject jsonArray2 = JSONObject.parseObject(jsonArrays.get(i).toString());
                    PsyCCourse psyCCourse = new PsyCCourse();
                    psyCCourse.setId(jsonArray2.getLong("id"));
                    psyCCourse.setName(jsonArray2.getString("name"));
                    psyCCourse.setCtStartTime(jsonArray2.getLong("ctStartTime"));
                    psyCCourse.setCtEndTime(jsonArray2.getLong("ctEndTime"));
                    psyCCourse.setCtImgUrl(jsonArray2.getString("ctImgUrl"));
                    psyCCourse.setCtId(jsonArray2.getLong("ctId"));
                    psyCCourse.setJsonContent(jsonArray2.getString("jsonContent"));

                    //psyCCourse.setDescription(jsonArray2.getString("description"));

                    String ctLectorAdo = jsonArray2.getString("ctLectorAdo");
                    if (ctLectorAdo.length() > 0) {
                        JSONArray ctLectorAdoList = (JSONArray) JSONArray.parse(ctLectorAdo);
                        if (ctLectorAdoList.size() > 0) {
                            String largeFaceUrl = null;
                            String lectorDesc = null;
                            String name = null;
                            String realName = null;
                            String type = null;
                            String openUid = null;
                            int cs = 0;
                            for (int c = 0; c < ctLectorAdoList.size(); c++) {
                                JSONObject ctLectorAdoArray = JSONObject.parseObject(ctLectorAdoList.get(c).toString());
                                if (cs == 0) {
                                    largeFaceUrl = ctLectorAdoArray.getString("largeFaceUrl");
                                    lectorDesc = ctLectorAdoArray.getString("lectorDesc");
                                    name = ctLectorAdoArray.getString("name");
                                    realName = ctLectorAdoArray.getString("realName");
                                    type = ctLectorAdoArray.getString("type");
                                    openUid = ctLectorAdoArray.getString("openUid");

                                } else {
                                    largeFaceUrl = largeFaceUrl + "," + ctLectorAdoArray.getString("largeFaceUrl");
                                    lectorDesc = lectorDesc + "," + ctLectorAdoArray.getString("lectorDesc");
                                    name = name + "," + ctLectorAdoArray.getString("name");
                                    realName = realName + "," + ctLectorAdoArray.getString("realName");
                                    type = type + "," + ctLectorAdoArray.getString("type");
                                    openUid = openUid + "," + ctLectorAdoArray.getString("openUid");
                                }
                            }

                            psyCCourse.setAdolargeFaceUrl(largeFaceUrl);
                            psyCCourse.setAdolectorDesc(lectorDesc);
                            psyCCourse.setAdoname(name);
                            psyCCourse.setAdorealName(realName);
                            psyCCourse.setAdotype(type);
                            psyCCourse.setAdoopenUid(openUid);

                            //查询已保存数据，判断id是否重复即可

                            PsyCCourse psyCCourseOld = psyCCourseService.selectPsyCCourseById(psyCCourse.getId());
                            if (psyCCourseOld != null) {
                                psyCCourseService.updatePsyCCourse(psyCCourse);
                            } else {
                                psyCCourseService.insertPsyCCourse(psyCCourse);
                            }

                            if (ctLectorAdoList.size() > 1) {
                                System.err.println("====ctLectorAdo教师存在多位，默认保存第一位====");
                            }

                        } else {
                            error = "====ctLectorAdo教师信息为空====";
                            System.err.println("====ctLectorAdo教师信息为空====");
                        }
                    } else {
                        error = "====ctLectorAdo教师信息为空====";
                        System.err.println("====ctLectorAdo教师信息为空====");
                    }
                }
            } else {
                error = "====list为空====";
                System.err.println("====list为空====");
            }
            return AjaxResult.success(error);
        } else {

            System.err.println("====接口获取数据失败====");
            return AjaxResult.error("接口获取数据失败！");
        }
    }


    public static String hashData(Long nonce) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        String data = "70ebb8d83e2b42d7aacae2a23d0e0db4" + nonce + nonce;
        byte[] bytes = data.getBytes();
        digest.update(bytes);
        byte[] hashedBytes = digest.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        String hashedData = hexString.toString();
        System.out.println("加密结果：" + hashedData);
        return hashedData;
    }
}
