package com.xgq.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * fastdfs客户端包装类
 */
@Slf4j
@Component
public class FastDFSClientWrapper {
  @Autowired
  private FastFileStorageClient storageClient;

  /**
   * 上传文件
   * 
   * @param file 文件对象
   * @return 文件访问地址
   * @throws IOException
   */
  public String uploadFile(MultipartFile file) throws IOException {
    StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
        FilenameUtils.getExtension(file.getOriginalFilename()), null);
    return getResAccessUrl(storePath);
  }

//  /**
//   * 上传缩略图
//   *
//   * @param file 文件对象
//   * @return 文件访问地址
//   * @throws IOException
//   */
//  public String uploadSalveFile(MultipartFile file, String parentPath) throws IOException {
//    StorePath parentStorePath = StorePath.praseFromUrl(parentPath);
//    String groupName = parentStorePath.getGroup();
//    String masterFilename = parentStorePath.getPath();
//    ByteArrayOutputStream os = new ByteArrayOutputStream();
//    Thumbnails.of(file.getInputStream()).size(150, 150).toOutputStream(os);
//    byte[] byteArray = os.toByteArray();
//    ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
//    StorePath storePath = storageClient.uploadSlaveFile(groupName, masterFilename, bis,
//        byteArray.length, FilenameUtils.getExtension(file.getOriginalFilename()), "150*150");
//    return getResAccessUrl(storePath);
//  }



  // 封装图片完整URL地址
  private String getResAccessUrl(StorePath storePath) {
    String fileUrl = storePath.getFullPath();
    return fileUrl;
  }

  /**
   * 删除文件，并删除缩略图
   * 
   * @param fileUrl 文件访问地址,
   * @return
   */
  public void deleteFile(String fileUrl) {
    if (StringUtils.isEmpty(fileUrl)) {
      return;
    }
    try {
      StorePath storePath = StorePath.praseFromUrl(fileUrl);
      storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    } catch (FdfsUnsupportStorePathException e) {
      log.warn(e.getMessage());
    }
  }
  /**
   * 下载文件
   */
  public InputStream downLoadImg(String fileUrl){

    if (StringUtils.isEmpty(fileUrl)) {
      return null;
    }
    StorePath storePath = StorePath.praseFromUrl(fileUrl);
  
    DownloadCallback<InputStream> callBack = new DownloadCallback<InputStream>() {

      @Override
      public InputStream recv(InputStream ins) throws IOException {
        return ins;
      }
      
    };
    InputStream in = (InputStream)storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), callBack  );
    return in;
  }

//  public String uploadFile(String base64String, String name) throws IOException {
//    BASE64Decoder decoder = new BASE64Decoder();
//    byte[] bytes = decoder.decodeBuffer(base64String);
//    for (int i = 0; i < bytes.length; ++i) {
//      if (bytes[i] < 0) {
//        // 调整异常数据
//        bytes[i] += 256;
//      }
//    }
//    // 将byte数组包装为MultipartFile类
//    Base64StrToMultipartFile a = new Base64StrToMultipartFile(bytes, name);
//    return this.uploadFile(a);
//  }
}
