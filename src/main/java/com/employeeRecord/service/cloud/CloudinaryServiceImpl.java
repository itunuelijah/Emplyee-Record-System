package com.employeeRecord.service.cloud;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;



 @Service("cloudinary-com.employeeRecord.service")
 public class CloudinaryServiceImpl implements CloudService {

        @Autowired
        Cloudinary cloudinary;

        @Override
        public Map<?, ?> upload(byte[] bytes, Map<?, ?> params) throws IOException {
            return cloudinary.uploader().upload(bytes,  params);
        }
//    @Override
//    public Map<?, ?> upload(File file, Map<?, ?> params) throws IOException {
//        return cloudinary.uploader().upload(Files.readAllBytes(file.toPath()),  params);
//    }
//    @Override
//    public Map<?, ?> upload(MultipartFile multipart, Map<?, ?> params) throws IOException {
//        return cloudinary.uploader().upload(multipart.getBytes(),  params);

    }



