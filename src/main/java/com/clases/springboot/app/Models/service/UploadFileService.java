package com.clases.springboot.app.Models.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
    private String folder = "src//main//resources//static//imagenes//";

    public String saveImage(MultipartFile file) throws IOException{
        
        if(!file.isEmpty()){
            byte[] bytesImg = file.getBytes();
			Path rutaCompleta = Paths.get(folder + "//" + file.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);
            return file.getOriginalFilename();
        }
        return "default.jpg";

    }

    public void deleteImage(String nombre){
        String ruta = "src//main//resources//static//imagenes//";
        File file = new File(ruta+nombre);
        file.delete();
    }

}
