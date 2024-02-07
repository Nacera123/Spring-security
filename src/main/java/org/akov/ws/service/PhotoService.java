package com.abdel.stock.service;


import com.abdel.stock.model.Photo;
import com.abdel.stock.repository.PhotoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAll(){
        return  photoRepository.findAll();
    }

    public Photo getById( Integer id){
        return photoRepository.findById(id).orElse(null);

    }

    public Photo save( Photo ph){
        return  photoRepository.save(ph);

    }

    public  void  delete(Integer id ){
        Photo ph = photoRepository.findById(id).orElse(null);
        photoRepository.delete(ph);
    }
}
