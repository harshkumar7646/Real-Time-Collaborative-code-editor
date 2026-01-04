package com.example.learningSpring.service;


import com.example.learningSpring.Repository.stdrepo;
import com.example.learningSpring.entity.studentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class studentService {

    @Autowired
    private stdrepo ss;



    public List<studentDetails> getdata(){
        return ss.findAll();
    }

    public Optional<studentDetails>getdatabyID(String id){
        return ss.findById(id);
    }

    public void deltebyID(String id){
        ss.deleteById(id);
    }

    public void  updatedatabyID(String id,studentDetails data){
        studentDetails x=ss.findById(id).orElse(null);
        if(x!=null){
            if(data.getName()!=null){
                x.setName(data.getName());
            }
            else if(data.getAge()!=0){
                x.setAge(data.getAge());
            }
            else if(data.getRoll_no()!=0){
                x.setRoll_no(data.getRoll_no());
            }
            ss.save(x);
        }

    }
}
