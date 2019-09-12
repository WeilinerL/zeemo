package com.overseass.demo.Service;

import com.overseass.demo.dao.ConsultantMapper;
import com.overseass.demo.pojo.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantMapper consultantMapper;

    public List<Consultant> getConsultants() {return consultantMapper.getAllConsultants();}

    public int deleteConsultant(String name) {return consultantMapper.deleteByName(name);}

    public int storeBackPic(String picName, Consultant consultant) {
        List<Consultant> consultantList = consultantMapper.selectByName(consultant.getStrName());
        if(consultantList.size() == 0) {
            int resultCount = 0;
            if(consultant != null) {
                consultant.setStrPicture("img/" + picName);
                resultCount += consultantMapper.insert(consultant);
            }else {
                return 0;
            }
            return resultCount;
        }
        return 0;
    }

    public int consultantEdit(Consultant consultant) {return consultantMapper.updateByPrimaryKey(consultant);}

}
