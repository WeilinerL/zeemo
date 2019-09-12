package com.overseass.demo.controller;

import com.overseass.demo.Service.ConsultantService;
import com.overseass.demo.pojo.Consultant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@RequestMapping(value = "/consultant")
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    private final static Log log = LogFactory.getLog(OSSController.class);

    @GetMapping(value = "get_consultants")
    public List<Consultant> getAllConsultants() {
        if(log.isTraceEnabled()) {
            log.trace("获取所有顾问信息");
        }
        return consultantService.getConsultants();
    }

    @DeleteMapping(value = "delete_consultant/{name}")
    public Map<String,String> deleteConsultant(@PathVariable String name) {
        Map<String,String> result = new HashMap<>();
        if(name != null) {
            if(consultantService.deleteConsultant(name) != 0) {
                result.put("result","success");
                return result;
            }else {
                result.put("result","fail");
                return result;
            }
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value="/add_consultant/{name}/{title}/{content}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> addSFBackPic(@RequestParam("picture") MultipartFile imgFile, @PathVariable String name, @PathVariable String title, @PathVariable String content) throws Exception{
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
            if(!name.equals("") && !title.equals("") && !content.equals("")) {
                Consultant consultant = new Consultant();
                consultant.setStrName(name);
                consultant.setStrTitle(title);
                consultant.setStrIntroduction(content);
                if(consultantService.storeBackPic(fileName,consultant) != 0) {
                    result.put("result","success");
                }
            }
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @PostMapping(value = "/consultant_edit")
    public Map<String,String> consultantEdit(@RequestBody List<Consultant> consultants) {
        Map<String,String> result = new HashMap<>();
        if(consultants != null) {
            Iterator<Consultant> consultantIterator = consultants.iterator();
            while(consultantIterator.hasNext()) {
                Consultant consultant = consultantIterator.next();
                if(log.isTraceEnabled()) {
                    log.trace("修改顾问信息: " + consultant.getStrName());
                }
                if(consultantService.consultantEdit(consultant) != 0) {
                    result.put(consultant.getStrName(),"success");
                }
            }
            return result;
        }else {
            result.put("result", "fail");
            return result;
        }
    }

}
