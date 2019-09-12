package com.overseass.demo.Service;

import com.overseass.demo.dao.OSSMapper;
import com.overseass.demo.pojo.OSS;
import com.overseass.demo.pojo.OSSInfo;
import com.overseass.demo.pojo.ServiceFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OSSService {

    @Autowired
    private OSSMapper ossMapper;

    public List<OSSInfo> getAllOss() {
        List<OSS> ossList = new ArrayList<>();
        List<OSSInfo> ossInfoList = new ArrayList<>();
        Map<String,OSSInfo> tempMap = new HashMap<>();
        ossList = ossMapper.getAllByType("oss_object");
        Iterator<OSS> ossIterator = ossList.iterator();
        while(ossIterator.hasNext()) {
            OSS oss = ossIterator.next();
            OSSInfo ossInfo = new OSSInfo();
            //用临时map存放ossInfo对象
            //map中存有该对象,完善该对象的信息
            if(tempMap.containsKey(oss.getStrName())) {
                ossInfo = tempMap.get(oss.getStrName());
                if(oss.getStrAttribute().equals("back_color")) { ossInfo.setBacColor(oss.getStrContent());}
                else if(oss.getStrAttribute().equals("oss_content")) { ossInfo.setContent(oss.getStrContent());}
                ossInfo.setOssTitle(oss.getStrName());
                tempMap.replace(oss.getStrName(),ossInfo);
            }else {
                //map中不含该对象
                ossInfo.setOssTitle(oss.getStrName());
                if(oss.getStrAttribute().equals("back_color")) { ossInfo.setBacColor(oss.getStrContent()); }
                else if(oss.getStrAttribute().equals("oss_content")) { ossInfo.setContent(oss.getStrContent());}
                tempMap.put(oss.getStrName(),ossInfo);
            }
        }
        for(String key : tempMap.keySet()) {
            ossInfoList.add(tempMap.get(key));
        }
        return ossInfoList;
    }

    public List<ServiceFlow> getAllServiceFlows() {
        List<OSS> ossList = new ArrayList<>();
        List<ServiceFlow> serviceFlows = new ArrayList<>();
        Map<String,ServiceFlow> tempMap = new HashMap<>();
        List<ServiceFlow> serviceFlowList = new ArrayList<>();
        ossList = ossMapper.getAllByType("sf_object");
        Iterator<OSS> ossIterator = ossList.iterator();
        while(ossIterator.hasNext()) {
            OSS oss = ossIterator.next();
            ServiceFlow serviceFlow = new ServiceFlow();
            //用临时map存放ossInfo对象
            //map中存有该对象,完善该对象的信息
//            System.out.println(oss.getStrName());
            if(tempMap.containsKey(oss.getStrName())) {
                serviceFlow = tempMap.get(oss.getStrName());
                if(oss.getStrAttribute().equals("back_pic")) { serviceFlow.setSFbackPic(oss.getStrContent());}
                else if(oss.getStrAttribute().equals("sf_content")) { serviceFlow.setSFContent(oss.getStrContent());}
                else if(oss.getStrAttribute().equals("sf_back_color")) { serviceFlow.setSFBackColor(oss.getStrContent());}
                serviceFlow.setSFTitle(oss.getStrName());
//                tempMap.replace(oss.getStrName(),serviceFlow);
                if(serviceFlowList.contains(serviceFlow) == false) {
                    serviceFlowList.add(serviceFlow);
                }
            }else {
                //map中不含该对象
                serviceFlow.setSFTitle(oss.getStrName());
                if(oss.getStrAttribute().equals("back_pic")) { serviceFlow.setSFbackPic(oss.getStrContent());}
                else if(oss.getStrAttribute().equals("sf_content")) { serviceFlow.setSFContent(oss.getStrContent());}
                else if(oss.getStrAttribute().equals("sf_back_color")) { serviceFlow.setSFBackColor(oss.getStrContent());}
                tempMap.put(oss.getStrName(),serviceFlow);
            }
        }
//        用这个会打乱原本的数据在数据库的存储顺序
//        for(String key : tempMap.keySet()) {
//            System.out.println(key);
//            serviceFlows.add(tempMap.get(key));
//        }
        Iterator<ServiceFlow> serviceFlowIterator = serviceFlowList.iterator();
        while(serviceFlowIterator.hasNext()) {
            ServiceFlow serviceFlow = serviceFlowIterator.next();
//            System.out.println(serviceFlow.getSFTitle());
            serviceFlows.add(serviceFlow);
        }
        return serviceFlows;
    }

    public int deleteOss(String name) {return ossMapper.deleteByName(name);}

    public int addOss(OSSInfo ossInfo) {
        if(ossInfo != null) {
            if(ossMapper.selectByOssName(ossInfo.getOssTitle()).size() == 0) {
                OSS oss1 = new OSS();
                OSS oss2 = new OSS();
                oss1.setStrType("oss_object");
                oss1.setStrName(ossInfo.getOssTitle());
                oss1.setStrAttribute("back_color");
                oss1.setStrContent(ossInfo.getBacColor());
                oss2.setStrType("oss_object");
                oss2.setStrName(ossInfo.getOssTitle());
                oss2.setStrAttribute("oss_content");
                oss2.setStrContent(ossInfo.getContent());
                int resultCount = 0;
                if(ossMapper.insert(oss1) == 1) {resultCount ++;}
                if(ossMapper.insert(oss2) == 1) {resultCount ++;}
                return resultCount;
            }
            return 0;
        }else {
            return 0;
        }
    }

    public int ossInfoEdit(OSSInfo ossInfo) {
        if(ossInfo != null) {
            OSS oss1 = ossMapper.selectByNameAttributeName(ossInfo.getOssTitle(),"back_color");
            OSS oss2 = ossMapper.selectByNameAttributeName(ossInfo.getOssTitle(),"oss_content");
            if(oss1 != null && oss2 != null) {
                oss1.setStrContent(ossInfo.getBacColor());
                oss2.setStrContent(ossInfo.getContent());
                int resultCount = 0;
                if(ossMapper.updateByPrimaryKey(oss1) == 1) {resultCount ++;}
                if(ossMapper.updateByPrimaryKey(oss2) == 1) {resultCount ++;}
                return resultCount;
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }

    public int deleteServiceFlow(String name) {return ossMapper.deleteByName(name);}

    public int addServiceFlow(ServiceFlow serviceFlow) {
        if(serviceFlow != null) {
            OSS oss2 = new OSS();
            OSS oss3 = new OSS();
            oss2.setStrType("sf_object");
            oss2.setStrName(serviceFlow.getSFTitle());
            oss2.setStrAttribute("sf_content");
            oss2.setStrContent(serviceFlow.getSFContent());
            oss3.setStrType("sf_object");
            oss3.setStrName(serviceFlow.getSFTitle());
            oss3.setStrAttribute("sf_back_color");
            oss3.setStrContent(serviceFlow.getSFBackColor());
            int resultCount = 0;
            if(ossMapper.insert(oss2) == 1) {resultCount ++;}
            if(ossMapper.insert(oss3) == 1) {resultCount ++;}
            return resultCount;
        }else {
            return 0;
        }
    }

    public int storeBackPic(String picName, String title) {
        OSS oss = new OSS();
        oss.setStrType("sf_object");
        oss.setStrName(title);
        oss.setStrAttribute("back_pic");
        oss.setStrContent("img/" + picName);
        return ossMapper.insert(oss);
    }

    public int serviceFlowEdit(ServiceFlow serviceFlow) {
        if(serviceFlow != null) {
            OSS oss1 = ossMapper.selectByNameAttributeName(serviceFlow.getSFTitle(),"sf_back_color");
            OSS oss2 = ossMapper.selectByNameAttributeName(serviceFlow.getSFTitle(),"sf_content");
            if(oss1 != null && oss2 != null) {
                oss1.setStrContent(serviceFlow.getSFBackColor());
                oss2.setStrContent(serviceFlow.getSFContent());
                int resultCount = 0;
                if(ossMapper.updateByPrimaryKey(oss1) == 1) {resultCount ++;}
                if(ossMapper.updateByPrimaryKey(oss2) == 1) {resultCount ++;}
                return resultCount;
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }

}
