package com.Validation.lecturerDB.service;

import com.Validation.lecturerDB.model.LecturerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {

    private List<LecturerModel> lecturers;

    public List<LecturerModel> addLecturer(LecturerModel lecturerModel){
        if(lecturers==null){
            lecturers=new ArrayList<>();
        }
        lecturers.add(lecturerModel);
        return lecturers;

    }

    public  LecturerModel get(int id){
        Optional<LecturerModel> lm=lecturers.stream().filter(x->x.getId()==id).findFirst();
        return lm.isPresent()?lm.get():null;
    }

    public void delete(int id){
        Optional<LecturerModel> result=lecturers.stream().filter(x->x.getId()==id).findFirst();
        result.ifPresent(res->lecturers.remove(res));
    }



}
