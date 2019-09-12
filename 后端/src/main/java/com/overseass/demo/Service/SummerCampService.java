package com.overseass.demo.Service;

import com.overseass.demo.dao.SummerCampMapper;
import com.overseass.demo.pojo.SummerCamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummerCampService {

    @Autowired
    private SummerCampMapper summerCampMapper;

    public SummerCamp getMainObject() {return summerCampMapper.selectByTitle("main_object");}

    public List<SummerCamp> getAdvantages() {return summerCampMapper.selectAllExcept("main_object");}

    public int storeBackPic(String title, String picName) {
        SummerCamp summerCamp = summerCampMapper.selectByTitle(title);
        if(summerCamp != null) {
            summerCamp.setStrImg("img/" + picName);
            return summerCampMapper.updateByPrimaryKey(summerCamp);
        }else {
            return 0;
        }
    }

    public int mainObjectContentEdit(String title, String content) {
        SummerCamp summerCamp = summerCampMapper.selectByTitle(title);
        if(summerCamp != null) {
            summerCamp.setStrContent(content);
            return summerCampMapper.updateByPrimaryKey(summerCamp);
        }
        return 0;
    }

    public int deleteAdavantage(String title) {return summerCampMapper.deleteByTitle(title);}

    public int addAdvantage(String title, String content, String picName) {
        SummerCamp summerCamp = summerCampMapper.selectByTitle(title);
        if(summerCamp != null) {
            return 0;
        }else {
            SummerCamp summerCamp1 = new SummerCamp();
            summerCamp1.setStrTitle(title);
            summerCamp1.setStrContent(content);
            summerCamp1.setStrImg("img/" + picName);
            return summerCampMapper.insert(summerCamp1);
        }
    }

    public int advantageEdit(SummerCamp summerCamp) {return summerCampMapper.updateByPrimaryKey(summerCamp);}

}
