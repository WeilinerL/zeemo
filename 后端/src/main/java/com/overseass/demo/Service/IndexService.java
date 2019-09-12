package com.overseass.demo.Service;

import com.overseass.demo.dao.IndexMapper;
import com.overseass.demo.pojo.ContactUs;
import com.overseass.demo.pojo.Index;
import com.overseass.demo.pojo.Welcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    private IndexMapper indexMapper;

    public int storeBackPic(String picName) {
        Index index = new Index();
        index.setStrType("background_picture");
        index.setStrName(picName);
        index.setStrContent("img/" + picName);
        Index index2 = indexMapper.selectByIndexType("background_picture");
        if(index2 != null) {
            index.setIntIndexId(index2.getIntIndexId());
            return indexMapper.updateByPrimaryKeySelective(index);
        }else {
            return indexMapper.insert(index);
        }
    }

    public int addProverbTitle(String proverbTitle){
        Index index = new Index();
        index.setStrType("proverb_title");
        index.setStrName("title");
        index.setStrContent(proverbTitle);
        Index index2 = indexMapper.selectByIndexType("proverb_title");
        if(index2 != null) {
            index.setIntIndexId(index2.getIntIndexId());
            return indexMapper.updateByPrimaryKey(index);
        }else {
            return indexMapper.insert(index);
        }
    }

    public int addProverbContent(String proverbContent){
        Index index = new Index();
        index.setStrType("proverb_content");
        index.setStrName("content");
        index.setStrContent(proverbContent);
        Index index2 = indexMapper.selectByIndexType("proverb_content");
        if(index2 != null) {
            index.setIntIndexId(index2.getIntIndexId());
            return indexMapper.updateByPrimaryKey(index);
        }else {
            return indexMapper.insert(index);
        }
    }

    public List<Index> getAllTeamFeature() {
        return indexMapper.getAllBytype("team_feature");
    }

    public int deleteFeature(String feature) {
        return indexMapper.deleteByContent(feature);
    }

    public int addFeature(Index index) {
        if(indexMapper.selectByIndexName(index.getStrName()) != null) {
            return 0;
        }
        return indexMapper.insert(index);
    }

    public int editTeamFeature(Index index) {
        index.setStrType("team_feature");
        Index index2 = indexMapper.selectByIndexName(index.getStrName());
        if(index2 != null) {
            index.setIntIndexId(index2.getIntIndexId());
            return indexMapper.updateByPrimaryKey(index);
        }else {
            index.setIntIndexId(null);
            return indexMapper.insert(index);
        }
    }

    public int editContactUs(Index index) {
        index.setStrType("contact_us");
        Index index2 = indexMapper.selectByIndexName(index.getStrName());
        if(index2 != null) {
            index.setIntIndexId(index2.getIntIndexId());
            return indexMapper.updateByPrimaryKey(index);
        }else {
            index.setIntIndexId(null);
            return indexMapper.insert(index);
        }
    }

    public ContactUs getContactUs() {
        List<Index> indexList  = indexMapper.getAllBytype("contact_us");
        Iterator<Index> indexIterator = indexList.iterator();
        ContactUs contactUs = new ContactUs();
        while(indexIterator.hasNext()) {
            Index index = indexIterator.next();
            if(index.getStrName().equals("tel")) {
                contactUs.setTel(index.getStrContent());
            }else if(index.getStrName().equals("net")) {
                contactUs.setNet(index.getStrContent());
            }else if(index.getStrName().equals("address")) {
                contactUs.setAddress(index.getStrContent());
            }else if(index.getStrName().equals("wechat")) {
                contactUs.setWeChat(index.getStrContent());
            }
        }
        return contactUs;
    }

    public int editContactUs(String name, String value) {
        Index index = new Index();
        index.setStrType("contact_us");
        index.setStrName(name);
        index.setStrContent(value);
        Index index2 = indexMapper.selectByIndexName(name);
        index.setIntIndexId(index2.getIntIndexId());
        return indexMapper.updateByPrimaryKey(index);
    }

    public List<Index> getWelCome() {
        List<Index> indexList = new ArrayList<>();
        Index index  = indexMapper.selectByIndexType("background_picture");
        Index index2  = indexMapper.selectByIndexType("proverb_title");
        Index index3  = indexMapper.selectByIndexType("proverb_content");
        indexList.add(index);
        indexList.add(index2);
        indexList.add(index3);
        return indexList;
    }

    public int storeQRCode(String picName) {
        Index index = new Index();
        index.setStrType("QR_Code");
        index.setStrName(picName);
        index.setStrContent("img/" + picName);
        Index index2 = indexMapper.selectByIndexType("QR_Code");
        if(index2 != null) {
            index.setIntIndexId(index2.getIntIndexId());
            return indexMapper.updateByPrimaryKeySelective(index);
        }else {
            return indexMapper.insert(index);
        }
    }

    public String getQRcode() {
        Index index = indexMapper.selectByIndexType("QR_Code");
        return index.getStrContent();
    }
}
