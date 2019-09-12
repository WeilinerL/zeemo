package com.overseass.demo.controller;

import com.overseass.demo.Service.SummerCampService;
import com.overseass.demo.pojo.SummerCamp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Delete;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "summer_camp")
public class SummerCampController {

    @Autowired
    private SummerCampService summerCampService;

    private final static Log log = LogFactory.getLog(SuccessListController.class);

    @GetMapping(value = "welcome")
    public SummerCamp getMainObject() {
        if(log.isTraceEnabled()) {
            log.trace("获取主要对象");
        }
        return summerCampService.getMainObject();
    }

    @GetMapping(value = "advantages")
    public List<SummerCamp> getAdvantages() {
        if(log.isTraceEnabled()) {
            log.trace("获取优点");
        }
        return summerCampService.getAdvantages();
    }

    @PostMapping(value="/mainObjectImgEdit/{title}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> addBackPic(@RequestParam("picture") MultipartFile imgFile, @PathVariable String title) throws Exception{
        if(log.isTraceEnabled()) {
            log.trace("收到文件：" + imgFile.getOriginalFilename());
        }
        //保存文件
        File folder = new File("C:\\Data\\oss\\vue\\img\\");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = imgFile.getOriginalFilename();
        if(fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
            FileOutputStream fos = new FileOutputStream(new File(folder, fileName));
            IOUtils.copy(imgFile.getInputStream(), fos);
            fos.close();
            Map<String, String> result = new HashMap<>();
            result.put("picture", fileName);
            //存储路径到数据库
            summerCampService.storeBackPic(title,fileName);
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @PostMapping(value = "/mainObjectContentEdit/{title}/{content}")
    public Map<String,String> mainObjectContentEdit(@PathVariable String title, @PathVariable String content) {
         Map<String,String> result = new HashMap<>();
         if(log.isTraceEnabled()) {
            log.trace("主要对象内容编辑: " + title + content);
         }
         if(summerCampService.mainObjectContentEdit(title,content) != 0) {
             result.put("result","success");
             return result;
         }else {
             result.put("result","fail");
             return result;
         }
    }

    @DeleteMapping(value = "/delete_summer_camp/{title}")
    public Map<String,String> deleteAdvantage(@PathVariable String title) {
        Map<String,String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("删除优势: " + title);
        }
        if(summerCampService.deleteAdavantage(title) != 0) {
            result.put("result","success");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value = "/add_advantage/{title}/{content}")
    public Map<String, String> addAdvantage(@RequestParam("picture") MultipartFile imgFile, @PathVariable String title, @PathVariable String content) throws Exception{
        if(log.isTraceEnabled()) {
            log.trace("收到文件：" + imgFile.getOriginalFilename());
        }
        //保存文件
        File folder = new File("C:\\Data\\Tomcat\\webapps\\oss\\img\\");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = imgFile.getOriginalFilename();
        if(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".JPG") || fileName.endsWith(".PNG")) {
            FileOutputStream fos = new FileOutputStream(new File(folder, fileName));
            IOUtils.copy(imgFile.getInputStream(), fos);
            fos.close();
            Map<String, String> result = new HashMap<>();
            result.put("picture", fileName);
            //存储路径到数据库
            if(summerCampService.addAdvantage(title,content,fileName) != 0) {
                result.put("result", "success");
            }
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @PostMapping(value = "advantageEdit")
    public Map<String,String> advantageEdit(@RequestBody List<SummerCamp> summerCampList) {
        Map<String,String> result = new HashMap<>();
        Iterator<SummerCamp> summerCampIterator = summerCampList.iterator();
        while (summerCampIterator.hasNext()) {
            SummerCamp summerCamp = summerCampIterator.next();
            if(log.isTraceEnabled()) {
                log.trace("编辑优点: " + summerCamp.getStrTitle());
            }
            if(summerCampService.advantageEdit(summerCamp) != 0) {
                result.put(summerCamp.getStrTitle(),"success");
            }else {
                result.put(summerCamp.getStrTitle(),"fail");
            }
            return result;
        }
        result.put("result","fail");
        return result;
    }

}
