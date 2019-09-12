package com.overseass.demo.controller;

import com.overseass.demo.Service.IndexService;
import com.overseass.demo.pojo.ContactUs;
import com.overseass.demo.pojo.Index;
import com.overseass.demo.pojo.Welcome;
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
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    private final static Log log = LogFactory.getLog(LoginController.class);

    @PostMapping(value="/background_pic", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> addPhoto(@RequestParam("picture") MultipartFile imgFile) throws Exception{
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
            indexService.storeBackPic(fileName);
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @GetMapping(value = "proverb_title")
    public Map<String,String> addProverbTitle(@RequestParam("proverbTitle") String s) {
        Map<String, String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("添加谚语标题: " + s);
        }
        if(indexService.addProverbTitle(s) == 1) {
            result.put("result","sucess");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @GetMapping(value = "proverb_content")
    public Map<String,String> addProverbContent(@RequestParam("proverbContent") String s) {
        Map<String, String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("添加谚语内容: " + s);
        }
        if(indexService.addProverbContent(s) == 1) {
            result.put("result","sucess");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @GetMapping(value = "/team_feature")
    public List<Index> getAllTeamFeature() {
        if(log.isTraceEnabled()) {
            log.trace("getAllTeamFeature");
        }
        return indexService.getAllTeamFeature();
    }

    @DeleteMapping(value = "/delete_feature/{feature}")
    public Map<String,String> deleteFeature(@PathVariable String feature) {
        Map<String,String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("删除特点: " + feature);
        }
        if(indexService.deleteFeature(feature) == 1) {
            result.put("result","success");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value = "/add_feature")
    public Map<String,String> addFeature(@RequestBody Index index) {
        Map<String,String> result = new HashMap<>();
        if(log.isTraceEnabled() && index != null) {
            log.trace("增加特点: " + index.getStrName());
        }
        if(index != null) {
            index.setStrType("team_feature");
            if(indexService.addFeature(index) == 1) {
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

    @PostMapping(value = "/team_feature_edit")
    public Map<String,String> editTeamFeature(@RequestBody List<Index> indexList) {
        Map<String,String> result = new HashMap<>();
        if(indexList != null) {
            Iterator<Index> indexIterator = indexList.iterator();
            int count1 = 0,count2 = 0;
            while(indexIterator.hasNext()) {
                Index index = indexIterator.next();
                if(log.isTraceEnabled()) {
                    log.trace("修改特点:" + index.getStrName());
                }
                count2 ++;
                if(indexService.editTeamFeature(index) == 1) {
                    count1 ++;
                };
            }
            if(count1 == count2) {
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

    @GetMapping(value = "contact_us")
    public ContactUs getContactUs() {
        if(log.isTraceEnabled()) {
            log.trace("getAllContactWays");
        }
        return indexService.getContactUs();
    }

    @PostMapping(value = "/contact_us_edit")
    public Map<String,String> editContactUs(@RequestBody ContactUs contactUs) {
        Map<String,String> result = new HashMap<>();
        if(log.isTraceEnabled()) {
            log.trace("修改联系方式");
        }
        int count = 0;
        if(indexService.editContactUs("tel",contactUs.getTel()) == 1) {count ++;}
        if(indexService.editContactUs("net",contactUs.getNet()) == 1) {count ++;}
        if(indexService.editContactUs("address",contactUs.getAddress()) == 1) {count ++;}
        if(indexService.editContactUs("wechat",contactUs.getWeChat()) == 1) {count ++;}
        if(count == 4) {
            result.put("result","success");
            return result;
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @GetMapping(value = "welcome")
    public Welcome getWelcome() {
        if(log.isTraceEnabled()) {
            log.trace("getWelcom");
        }
        List<Index> indexList = indexService.getWelCome();
        Iterator<Index> indexIterator = indexList.iterator();
        Welcome welcome = new Welcome();
        while(indexIterator.hasNext()) {
            Index index = indexIterator.next();
            if(index.getStrType().equals("background_picture")) {welcome.setBackPic(index.getStrContent());}
            else if(index.getStrType().equals("proverb_title")) {welcome.setProverbTitle(index.getStrContent());}
            else if(index.getStrType().equals("proverb_content")) {welcome.setProverbContent(index.getStrContent());}
        }
        return welcome;
    }

    @PostMapping(value="/qr_code", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> addQRCode(@RequestParam("qrcode") MultipartFile imgFile) throws Exception{
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
            indexService.storeQRCode(fileName);
            return result;
        }else {
            throw new RuntimeException("不支持的格式，仅支持jpg和png格式");
        }
    }

    @GetMapping(value = "/qr_code")
    public String getQRCode() {
        if(log.isTraceEnabled()) {
            log.trace("获取二维码");
        }
        return indexService.getQRcode();
    }
}
