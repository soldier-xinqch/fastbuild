package com.fastbuild.email;

import java.io.Serializable;
import java.util.List;

/**
 *  封装邮件发送的相关参数
 * @author xinqch
 *
 */
public class EmailPojo implements Serializable {

    private static final long serialVersionUID = 3262880328739255424L;
    /**
     *  发件人
     */
    String sendFrom;
    /**
     * 收件人
     */
    String sendTo ;
    /**
     *  主题
     */
    String subject ;
    /**
     *  内容
     */
    String obj ;
    /**
     *  发件账号
     */
    String sendFromUser;
    /**
     * 发件密码
     */
    String sendFromPassWord;
    /**
     * 抄送人列表
     */
    String[] ccs;
    /**
     * 密送人列表
     */
    String[] bccs;
    /**
     *  内嵌内容图片地址
     */
    String imagePath;
    /**
     *  附件文件路径
     */
    List<String> attachFilePath;
    /**
     * 邮件保存路径
     */
    String saveEmailPath;
    /**
     *  邮件内容类型
     */
    Integer methodType;
    /**
     *  发件邮件平台编码
     */
    String smtpHostName;

    public Integer getMethodType() {
        return methodType;
    }
    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }
    public String getSendFrom() {
        return sendFrom;
    }
    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }
    public String getSendTo() {
        return sendTo;
    }
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getObj() {
        return obj;
    }
    public void setObj(String obj) {
        this.obj = obj;
    }
    public String getSendFromUser() {
        return sendFromUser;
    }
    public void setSendFromUser(String sendFromUser) {
        this.sendFromUser = sendFromUser;
    }
    public String getSendFromPassWord() {
        return sendFromPassWord;
    }
    public void setSendFromPassWord(String sendFromPassWord) {
        this.sendFromPassWord = sendFromPassWord;
    }
    public String[] getCcs() {
        return ccs;
    }
    public void setCcs(String[] ccs) {
        this.ccs = ccs;
    }
    public String[] getBccs() {
        return bccs;
    }
    public void setBccs(String[] bccs) {
        this.bccs = bccs;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public List<String> getAttachFilePath() {
        return attachFilePath;
    }
    public void setAttachFilePath(List<String> attachFilePath) {
        this.attachFilePath = attachFilePath;
    }
    public String getSaveEmailPath() {
        return saveEmailPath;
    }
    public void setSaveEmailPath(String saveEmailPath) {
        this.saveEmailPath = saveEmailPath;
    }
    public String getSmtpHostName() {
        return smtpHostName;
    }
    public void setSmtpHostName(String smtpHostName) {
        this.smtpHostName = smtpHostName;
    }
}