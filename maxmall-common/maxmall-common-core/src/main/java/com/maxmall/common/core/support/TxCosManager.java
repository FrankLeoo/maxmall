package com.maxmall.common.core.support;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public class TxCosManager {

    private static final String IMAGE_BUCKET = "maxmall-1257020268";
    private static final String IMAGE_PATH = "https://maxmall-1257020268.cos.ap-chengdu.myqcloud.com/";

    @Autowired
    private COSClient cosClient;

    /**
     * 上传文件到cos
     * 文件名称自动生成
     *
     * @param multipartFile
     * @return
     */
    public String imageUpload(MultipartFile multipartFile,String fileName) {
        try{
            if (!isImageContentType(multipartFile.getContentType())) {
                throw new RuntimeException("文件类型错误，请上传图片文件");
            }
            InputStream fileInput = multipartFile.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(fileInput.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(IMAGE_BUCKET, fileName, fileInput, objectMetadata);
            PutObjectResult result = cosClient.putObject(putObjectRequest);

            return IMAGE_PATH+fileName;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @return String
     */
    private Boolean isImageContentType(String contentType) {
        if (contentType.equalsIgnoreCase("image/jpeg")) {
            return true;
        }
        if (contentType.equalsIgnoreCase("image/bmp")) {
            return true;
        }
        if (contentType.equalsIgnoreCase("image/png")) {
            return true;
        }

        return false;
    }

}
