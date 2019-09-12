package com.overseass.demo.controller;

import com.overseass.demo.Service.SuccessListService;
import com.overseass.demo.pojo.Consultant;
import com.overseass.demo.pojo.SuccessList;
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
@RequestMapping(value = "success_list")
public class SuccessListController {

    @Autowired
    private SuccessListService successListService;

    private final static Log log = LogFactory.getLog(SuccessListController.class);

    @GetMapping(value = "get_all_success_list")
    public List<SuccessList> getAllSuccessList() {
        if(log.isTraceEnabled()) {
            log.trace("获取成功案例列表");
        }
        return successListService.getAllSuccessList();
    }

    @DeleteMapping(value = "delete_success_list/{content}")
    public Map<String,String> deleteSuccessList(@PathVariable String content) {
        Map<String,String> result = new HashMap<>();
        if(content != null) {
            if(successListService.deleteSuccessList(content) != 0) {
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

    @PostMapping(value="/add_success_list/{content}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> addSFBackPic(@RequestParam("picture") MultipartFile imgFile, @PathVariable String content) throws Exception{
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
            if(successListService.storeBackPic(fileName,content) != 0) {
                result.put("result","success");
            }
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @PostMapping(value = "/success_list_edit")
    public Map<String,String> successListEdit(@RequestBody List<SuccessList> successLists) {
        Map<String,String> result = new HashMap<>();
        if(successLists != null) {
            Iterator<SuccessList> successListIterator = successLists.iterator();
            while(successListIterator.hasNext()) {
                SuccessList successList = successListIterator.next();
                if(log.isTraceEnabled()) {
                    log.trace("修改成功案例信息: " + successList.getStrContent());
                }
                if(successListService.successListEdit(successList) != 0) {
                    result.put(successList.getStrContent(),"success");
                }
            }
            return result;
        }else {
            result.put("result", "fail");
            return result;
        }
    }

}
