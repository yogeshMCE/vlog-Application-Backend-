package com.app.vlog.ServiceImpl;

import com.app.vlog.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // file name   abc.png
        String name= file.getOriginalFilename();

        // random generation of file name
        String randomId= UUID.randomUUID().toString();
        String fileName= randomId.concat(name.substring(name.lastIndexOf(".")));

        // full path
        String fullPath= path+ File.separator+fileName;

        // create folder if not created
        File f= new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(fullPath));
        return fileName;
    }

    @Override
    public InputStream getResourse(String path, String fileName) throws FileNotFoundException{
        String fullPath= path+File.separator+fileName;
        return new FileInputStream(fullPath);
    }
}
