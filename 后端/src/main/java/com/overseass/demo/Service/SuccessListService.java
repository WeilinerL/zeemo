package com.overseass.demo.Service;

import com.overseass.demo.dao.SuccessListMapper;
import com.overseass.demo.pojo.Consultant;
import com.overseass.demo.pojo.SuccessList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SuccessListService {

    @Autowired
    private SuccessListMapper successListMapper;

    public List<SuccessList> getAllSuccessList() {return successListMapper.getAllSuccessList();}

    public int deleteSuccessList(String content) {return successListMapper.deleteByContent(content);}

    public int storeBackPic(String picName, String content) {
        List<SuccessList> successLists = successListMapper.selectByContent(content);
        if(successLists.size() == 0) {
            SuccessList successList = new SuccessList();
            successList.setStrContent(content);
            successList.setStrPicture("img/" + picName);
            return successListMapper.insert(successList);
        }
        return 0;
    }

    public int successListEdit(SuccessList successList) {return successListMapper.updateByPrimaryKey(successList);}

}
