package com.javadocfast.crud.service;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private Cloudinary cloudinary;
    private String cloudName = "fpt-aptech-huypq";
    private String apiKey = "791563656445282";
    private String apiSecret = "d4rpeFOJuFrOuZaizHIMkSfCGWo";
    private Map<String, String> valuesMap;

    public CloudinaryService() {

        valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", cloudName);
        valuesMap.put("api_key", apiKey);
        valuesMap.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile, Map<String, String> options) throws IOException {
        File file = convert(multipartFile);

        Map result = cloudinary.uploader().upload(file, options);
        file.delete();
        return result;
    }

    public Map delete(String id, Map<String, String> options) throws IOException {
        Map result = cloudinary.uploader().destroy(id, options);
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }

}
