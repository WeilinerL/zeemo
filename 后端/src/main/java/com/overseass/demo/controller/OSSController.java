package com.overseass.demo.controller;

import com.overseass.demo.Service.OSSService;
import com.overseass.demo.pojo.OSS;
import com.overseass.demo.pojo.OSSInfo;
import com.overseass.demo.pojo.ServiceFlow;
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
@RequestMapping(value = "overseas_study_service")
public class OSSController {

    @Autowired
    private OSSService ossService;

    private final static Log log = LogFactory.getLog(OSSController.class);

    @GetMapping(value = "/getoss")
    public List<OSSInfo> getOss() {
        if(log.isTraceEnabled()) {
            log.trace("getoss");
        }
        return ossService.getAllOss();
    }

    @GetMapping(value = "/get_service_flows")
    public List<ServiceFlow> getServiceFlows() {
        if(log.isTraceEnabled()) {
            log.trace("getServiceFlows");
        }
        return ossService.getAllServiceFlows();
    }

    @DeleteMapping(value = "/delete_oss/{name}")
    public Map<String,String> deleteOss(@PathVariable String name) {
        Map<String,String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("删除留学信息: " + name);
        }
        if(ossService.deleteOss(name) != 0) {
            result.put("result","success");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value = "add_oss")
    public Map<String,String> addOss(@RequestBody OSSInfo ossInfo) {
        Map<String,String> result = new HashMap<>();
        if(ossInfo != null) {
            if(log.isTraceEnabled()) {
                log.trace("增加留学信息: " + ossInfo.getOssTitle());
            }
            if(ossService.addOss(ossInfo) != 0) {
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

    @PostMapping(value = "ossinfo_edit")
    public Map<String,String> ossInfoEdit(@RequestBody List<OSSInfo> ossInfoList) {
        Map<String,String> result = new HashMap<>();
        if(ossInfoList != null) {
            Iterator<OSSInfo> ossInfoIterator = ossInfoList.iterator();
            while (ossInfoIterator.hasNext()) {
                OSSInfo ossInfo = ossInfoIterator.next();
                if(log.isTraceEnabled()) {
                    log.trace("修改留学信息: " + ossInfo.getOssTitle());
                }
                if(ossService.ossInfoEdit(ossInfo) != 0) {
                    result.put(ossInfo.getOssTitle(),"success");
                }else {
                    result.put(ossInfo.getOssTitle(),"fail");
                }
            }
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @DeleteMapping(value = "/delete_service_flow/{name}")
    public Map<String,String> deleteServiceFlow(@PathVariable String name) {
        Map<String,String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("删除服务流程信息: " + name);
        }
        if(ossService.deleteServiceFlow(name) != 0) {
            result.put("result","success");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value="/background_pic/{title}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> addSFBackPic(@RequestParam("picture") MultipartFile imgFile, @PathVariable String title) throws Exception{
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
            ossService.storeBackPic(fileName,title);
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @PostMapping(value = "add_service_flow")
    public Map<String,String> addServiceFlow(@RequestBody ServiceFlow serviceFlow) {
        Map<String,String> result = new HashMap<>();
        if(serviceFlow != null) {
            if(log.isTraceEnabled()) {
                log.trace("增加服务流程信息: " + serviceFlow.getSFTitle());
            }
            if(ossService.addServiceFlow(serviceFlow) != 0) {
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

    @PostMapping(value = "service_flow_info_edit")
    public Map<String,String> serviceFlowInfoEdit(@RequestBody List<ServiceFlow> serviceFlowList) {
        Map<String,String> result = new HashMap<>();
        if(serviceFlowList != null) {
            Iterator<ServiceFlow> serviceFlowIterator = serviceFlowList.iterator();
            while (serviceFlowIterator.hasNext()) {
                ServiceFlow serviceFlow = serviceFlowIterator.next();
                if(log.isTraceEnabled()) {
                    log.trace("修改服务步骤: " + serviceFlow.getSFTitle());
                }
                if(ossService.serviceFlowEdit(serviceFlow) != 0) {
                    result.put(serviceFlow.getSFTitle(),"success");
                }else {
                    result.put(serviceFlow.getSFTitle(),"fail");
                }
            }
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

}
