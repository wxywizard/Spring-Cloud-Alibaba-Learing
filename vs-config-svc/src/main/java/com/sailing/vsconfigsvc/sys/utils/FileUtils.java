package com.sailing.vsconfigsvc.sys.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Title:  文件上传<br>
 * Description: 文件上传 <br>
 *
 * @author gaowei
 * @date 2017-12-19
 */
@Slf4j
public class FileUtils {
    /**
     * 保存文件至本地
     * @param file 文件流
     * @param filePath 文件路径
     * @param fileName 文件名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath +"/"+ fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    /**
     * 保存文件至本地
     * @param file 文件流
     * @param filePath 文件路径及文件名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath) throws Exception {
        String path = filePath.substring(0,filePath.lastIndexOf("/")) ;
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath);
        out.write(file);
        out.flush();
        out.close();
    }
    public static boolean exists( String filePath ) {
        File targetFile = new File(filePath);
        return targetFile.exists();
    }
    /**
     * 获取文件扩展名
     * @param fileName 文件名
     * @return
     */
    public static  String  getExtension(String fileName){
        String ext;
        if(fileName.contains(".")){
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }else{
            log.error("文件类型未知");
            return "";
        }
    }

    /**
     * 根据文件全名获取文件名，如 file.zip => file
     * @param fileName
     * @return
     */
    public static  String  getFileName(String fileName){
        String ext;
        if(fileName.contains(".") && fileName.contains("/")){
            return fileName.substring(fileName.lastIndexOf("/")+1,fileName.lastIndexOf("."));
        }else if(fileName.contains(".")) {
            return fileName.substring(0,fileName.lastIndexOf("."));
        }else {
            return fileName;
        }
    }
    /**
     * 根据路径获取文件名，如 d:/file.zip => file.zip
     * @param fileName
     * @return
     */
    public static  String  getFileByPath(String fileName){
        String ext;
        if(fileName.contains("/")){
            return fileName.substring(fileName.lastIndexOf("/")+1);
        }else{
            return fileName;
        }
    }

    //base64字符串转化成图片
    public static boolean uploadBase64Image(String base64, String filePath, String fileName)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (base64 == null){
            //图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(filePath+"/"+fileName);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
