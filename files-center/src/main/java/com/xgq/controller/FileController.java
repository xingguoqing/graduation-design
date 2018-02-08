package com.xgq.controller;

import java.io.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import sun.misc.BASE64Encoder;
import util.exception.BusinessRuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/8 下午2:43
 */
@RestController
@RequestMapping(value = "/private/file")
@Api(value = "文件管理相关接口", tags = "FileController")
public class FileController {


    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);


    /**
     * 暂时存放在工程目录，后续加上fastdfs
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "文件上传")
    @ApiResponse(message = "上传结果", code = 200)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ICommonResponse uploadFiles(HttpServletRequest request) {

        try {
//            String pathPre = ClassLoader.getSystemClassLoader().getResource("").getPath();
            List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                    .getFiles("file");
            MultipartFile file = null;
            BufferedOutputStream stream = null;
            List<String> paths = new ArrayList<String>();
            String fileName;
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    byte[] bytes = file.getBytes();
                    fileName = System.currentTimeMillis() + file.getOriginalFilename();
                    paths.add(fileName);
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(fileName)));
                    stream.write(bytes);
                    stream.close();
                }
            }
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, paths);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "文件上传失败");
        }
    }


    @ApiOperation(value = "文件下载")
    @ApiResponse(message = "下载结果", code = 200)
    @ApiImplicitParam(name = "path", paramType = "body", dataType = "String", value = "文件路径", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ICommonResponse downloadFiles(@RequestBody List<String> paths) {

        try {
//            下载文件用
//            File file = new File("C:/test.txt");
//            resp.setHeader("content-type", "application/octet-stream");
//            resp.setContentType("application/octet-stream");
//
//            resp.setHeader("Content-Disposition", "attachment;filename=" + path);
//            byte[] buff = new byte[1024];
//            BufferedInputStream bis = null;
//            OutputStream os = null;
//            try {
//                os = resp.getOutputStream();
//                bis = new BufferedInputStream(new FileInputStream(path));
//                int i = bis.read(buff);
//                while (i != -1) {
//                    os.write(buff, 0, buff.length);
//                    os.flush();
//                    i = bis.read(buff);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            File file;
            List<String> base64s = new ArrayList<String>();
            BASE64Encoder base64Encoder = new BASE64Encoder();
            for(String path:paths){
                file = new File(path);
                FileInputStream inputFile = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputFile.read(buffer);
                inputFile.close();
                base64s.add(base64Encoder.encode(buffer));
            }
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, base64s);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "文件下载失败");
        }
    }


}
