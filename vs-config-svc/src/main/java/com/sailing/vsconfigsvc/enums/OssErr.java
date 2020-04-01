package com.sailing.vsconfigsvc.enums;

/**
 * Title:  <br>
 * Description: <br>
 *
 * @author gaowei
 * @date 2017-12-19
 */
public enum OssErr {

    /**
     * 请求图片无后缀名
     */
    no_picture_format(20001, "请求图片无后缀名"),
    /**
     * 图片格式不正确
     */
    picture_format_err(20002, "请求图片格式不正确"),
    /**
     * 上传图片超过4mb
     */
    picture_max_size(20003, "上传图片超过4mb"),
    /**
     * 上传文件不能为空
     */
    file_null(20004, "上传文件不能为空"),
    /**
     * 文件类型未知
     */
    unknown_file_type(20005, "文件类型未知"),
    /**
     * 路径不存在
     */
    path_not_exist(20006, "路径不存在"),

    sys_unknown_err(20007, "文件写入磁盘未知错误");

    private int code;
    private String description;

    OssErr() {
        this.setCode(99999);
        this.setDescription("系统未知错误");
    }

    OssErr(int code, String description) {
        this.setCode(code);
        this.setDescription(description);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
