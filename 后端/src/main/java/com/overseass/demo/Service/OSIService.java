package com.overseass.demo.Service;

import com.overseass.demo.dao.ArticleTypeMapper;
import com.overseass.demo.dao.OSIMapper;
import com.overseass.demo.pojo.ArticleType;
import com.overseass.demo.pojo.OSI;
import com.overseass.demo.pojo.OSInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OSIService {

    @Autowired
    private OSIMapper osiMapper;

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    public List<OSInfo> getAllOSInfo() {
        List<OSI> osiList = osiMapper.selectAllOSInfo();;
        Map<String,OSInfo> tempMap = new HashMap<>();
        List<OSInfo> osInfoList = new ArrayList<>();
        Iterator<OSI> osiIterator = osiList.iterator();
        while(osiIterator.hasNext()) {
            OSI osi = osiIterator.next();
            OSInfo osInfo = new OSInfo();
            if(tempMap.containsKey(osi.getStrType())) {
                osInfo = tempMap.get(osi.getStrType());
                if(!osi.getStrTime().equals("增加类别")) {
                    if(osInfoList.contains(osInfo)) {
                        osInfoList.remove(osInfo);
                    }
                    osInfo.addArticle(osi.getStrTitle());
                    osInfoList.add(osInfo);
                }
//                tempMap.replace(osi.getStrType(),osInfo);
            }else {
                //map中不含该对象
                if(osi.getStrTime().equals("增加类别")) {
                    osInfo.setType(osi.getStrType());
                    osInfo.addArticle("");
                }else {
                    osInfo.setType(osi.getStrType());
                    osInfo.addArticle(osi.getStrTitle());
                }
                osInfoList.add(osInfo);
                tempMap.put(osi.getStrType(),osInfo);
            }
        }
//        for(Map.Entry<String, OSInfo> entry : tempMap.entrySet()){
//            osInfoList.add(entry.getValue());
//        }
//        for(String key : tempMap.keySet()) {
//            osInfoList.add(tempMap.get(key));
//        }
        return osInfoList;
    }

    public OSI getArticle(String type, String title) {return osiMapper.selectArticle(type,title);}

    public int deleteType(String typeName) {
        int resultCount = 0;
        resultCount += osiMapper.deleteByType(typeName);
        resultCount += articleTypeMapper.deleteByType(typeName);
        return resultCount;
    }

    public int deleteArticle(String type,String title) {return osiMapper.deleteByTitle(type,title);}

    public int addType(String type) {
        int result = 0;
        if(articleTypeMapper.selectByTypeName(type) == null) {
            ArticleType articleType = new ArticleType();
            articleType.setStrType(type);
            result += articleTypeMapper.insert(articleType);
            OSI osi = new OSI();
            osi.setStrType(type);
            osi.setStrAuthor("增加类别");
            osi.setStrTitle("增加类别");
            osi.setStrTime("增加类别");
            osi.setStrContent("增加类别");
            result += osiMapper.insert(osi);
            return result;
        }
        return 0;
    }

    public int addArticle(OSI osi) {
        if(osi != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date now = new Date();
            String data = sdf.format(new Date(now.getTime()));
            osi.setStrTime(data);
            return osiMapper.insert(osi);
        }
        return 0;
    }
}
