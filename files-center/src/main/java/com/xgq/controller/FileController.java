package com.xgq.controller;

import java.io.*;

import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.proto.storage.FdfsInputStream;
import com.xgq.util.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import responsecode.ICommonResponse;
import responsecode.enums.CommonRespCodeEnum;
import responsecode.response.CommonResponse;
import sun.misc.BASE64Encoder;
import util.exception.BusinessRuntimeException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/8 下午2:43
 */
@RestController
@RequestMapping(value = "/file")
@Api(value = "文件管理相关接口", tags = "FileController")
public class FileController {


    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);


    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

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
            List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                    .getFiles("files");

            List<String> paths = new ArrayList<String>();
            String path;
            for (MultipartFile file : files) {
                path = fastDFSClientWrapper.uploadFile(file);
                paths.add(path);
            }
            LOGGER.info("文件上传成功，返回路径为:{}", JSONObject.toJSONString(paths));
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE, paths);
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "文件上传失败");
        }
    }


    @ApiOperation(value = "文件下载")
    @ApiResponse(message = "下载结果", code = 200)
    @ApiImplicitParam(name = "path", paramType = "body", dataType = "String", value = "文件路径", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ICommonResponse downloadFiles(@RequestParam("path") String path) {

        try {
            InputStream fileInputStream = fastDFSClientWrapper.downLoadImg(path);
            byte[] imgByteArray = IOUtils.toByteArray(fileInputStream);
            // Base64解码
            for (int i = 0; i < imgByteArray.length; ++i) {
                if (imgByteArray[i] < 0) {// 调整异常数据
                    imgByteArray[i] += 256;
                }
            }
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return new CommonResponse(CommonRespCodeEnum.SUCCESS_CODE,base64Encoder.encode(imgByteArray));
        } catch (Exception e) {
            return BusinessRuntimeException.responseException(e, "文件下载失败");
        }
    }


}
