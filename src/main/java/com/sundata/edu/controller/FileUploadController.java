package com.sundata.edu.controller;
import com.sundata.edu.framework.core.Resource;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/uploads")
public class FileUploadController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //    private String ext=".jpg;.png;.jepg;";
    @Autowired
    private Resource resource;

    @PostMapping()
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile fileUpload) {
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
//        //获取文件后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        //重新生成文件名
//        fileName = UUID.randomUUID() + suffixName;
        String newFileName = UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        String filePath = "/uploads/" + Utils.dateToString(new Date(), "yyyy/MM/dd") + "/";
        try {
            String temp = resource.WEB_UPLOAD_PATH + filePath;
            File dest = new File(temp);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            //将图片保存到static文件夹里
//            fileUpload.transferTo(dest);
            fileUpload.transferTo(new File(resource.WEB_UPLOAD_PATH + filePath + newFileName));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            return AjaxResult.success("上传失败");
        }
        return AjaxResult.success("上传成功").put("path", filePath + newFileName).put("contentType", fileUpload.getContentType()).put("fileName", fileUpload.getOriginalFilename()).put("fileSize", fileUpload.getSize());

    }

    @GetMapping("/getconfig")
    @ResponseBody
    public Map<String, Object> getconfig() {

        return null;
    }

    @PostMapping("/file")
    @ResponseBody
    public Map<String, Object> upload1(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "/uploads/" + Utils.dateToString(new Date(), "yyyy/MM/dd") + "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        File dest = new File(resource.WEB_UPLOAD_PATH + filePath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        file.transferTo(dest);
        Map<String, Object> result = new HashMap<>(16);
        result.put("contentType", file.getContentType());
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", file.getSize() + "");
        result.put("path", filePath);
        result.put("code", 200);
        return result;
    }

    @PostMapping("/files")
    @ResponseBody
    public List<Map<String, String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file : files) {
            // TODO Spring Mvc 提供的写入方式
            String fileName = file.getOriginalFilename();
            String filePath = "/uploads/" + Utils.dateToString(new Date(), "yyyy/MM/dd") + "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
            File dest = new File(resource.WEB_UPLOAD_PATH + filePath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            file.transferTo(dest);
            Map<String, String> map = new HashMap<>(16);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            map.put("path", filePath);
            results.add(map);
        }
        return results;
    }

    @PostMapping("/filebase64")
    @ResponseBody
    public void upload2(String base64) throws IOException {
        // TODO BASE64 方式的 格式和名字需要自己控制（如 png 图片编码后前缀就会是 data:image/png;base64,）
        File dest = new File(resource.WEB_UPLOAD_PATH + "1.jpg");
        if (!dest.exists()) {
            dest.mkdirs();
        }
        // TODO 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes, dest);
    }

//    @Autowired
//    private FileUpAndDownService fileUpAndDownService;
//
//    @RequestMapping(value = "/image", method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxResult setFileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {
//        try {
//            Map<String, Object> resultMap = upload(file);
//            if (ResultCode.SUCCESS != resultMap.get("result")) {
//                return AjaxResult.error((String) resultMap.get("msg"));
//            }
//            return AjaxResult.success().put("path", (String) resultMap.get("path"));
//        } catch (ServiceException ex) {
//            logger.error(">>>>>>图片上传异常，ex={}", ex.getMessage());
//            return AjaxResult.error(ex.getMessage());
//        }
//    }
//
//    private Map<String, Object> upload(MultipartFile file) throws ServiceException {
//        Map<String, Object> returnMap = new HashMap<>();
//        try {
//            if (!file.isEmpty()) {
//                Map<String, Object> picMap = fileUpAndDownService.uploadPicture(file);
//                if (ResultCode.SUCCESS == picMap.get("result")) {
//                    return picMap;
//                } else {
//                    returnMap.put("result", ResultCode.FAIL);
//                    returnMap.put("msg", picMap.get("result"));
//                }
//            } else {
//                logger.info(">>>>>>上传图片为空文件");
//                returnMap.put("result", ResultCode.FAIL);
//                returnMap.put("msg", "上传图片为空");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException("上传图片失败");
//        }
//        return returnMap;
//    }
        @PostMapping("/fileExcel")
        @ResponseBody
        public Map<String, Object> fileexcel(@RequestParam("file") MultipartFile file) throws IOException {
            String fileName = file.getOriginalFilename();
            String filePath = "/uploads/" + Utils.dateToString(new Date(), "yyyy/MM/dd") + "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
            File dest = new File(resource.WEB_UPLOAD_PATH + filePath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            file.transferTo(dest);
            Map<String, Object> result = new HashMap<>(16);
            result.put("contentType", file.getContentType());
            result.put("fileName", file.getOriginalFilename());
            result.put("fileSize", file.getSize() + "");
            result.put("path", filePath);
            result.put("code", 200);
            return result;
        }
}
