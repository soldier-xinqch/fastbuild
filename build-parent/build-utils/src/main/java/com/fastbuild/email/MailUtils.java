package com.fastbuild.email;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件工具类
 *
 * @author xinqch
 *
 */
public class MailUtils {

    /**
     * 封装邮件基本数据
     *
     * @param session
     * @param sendFrom 发件人
     * @param sendTo   收件人
     * @param subject  主题
     * @param ccs      抄送人
     * @param bccs     密送人
     * @return
     */
    private static MimeMessage baseSendMessage(Session session,
                                               String sendFrom, String sendTo, String subject, String[] ccs,
                                               String[] bccs) {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 指明邮件的发件人
            message.setFrom(new InternetAddress(sendFrom));
            if (null != ccs && ccs.length > 0) {
                String cc = "";
                for (String string : ccs) {
                    cc += string + ",";
                }
                // 添加抄送人
                message.setRecipient(Message.RecipientType.CC,
                        new InternetAddress(cc));
            }
            if (null != bccs && bccs.length > 0) {
                String bcc = "";
                for (String string : bccs) {
                    bcc += string + ",";
                }
                // 添加密送人
                message.setRecipient(Message.RecipientType.CC,
                        new InternetAddress(bcc));
            }
            // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                    sendTo));
            // 邮件的标题
            message.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * 创建一封只包含文本的邮件
     *
     * @param session
     * @param sendFrom 发件人
     * @param sendTo   收件人
     * @param subject  主题
     * @param obj      内容
     * @throws Exception
     */
    public static MimeMessage createSimpleMail(Session session,
                                               String sendFrom, String sendTo, String subject, Object obj,
                                               String[] ccs, String[] bccs, String saveEmailPath) throws Exception {
        // 创建邮件对象
        MimeMessage message = baseSendMessage(session, sendFrom, sendTo, subject, ccs, bccs);
        // 邮件的文本内容
        message.setContent(obj, "text/html;charset=UTF-8");
        //保存发送的邮件
        SaveSendMail(message, sendTo, saveEmailPath);
        return message;
    }

    /**
     * 生成一封邮件正文带图片的邮件
     *
     * @param session
     * @param sendFrom      发件人
     * @param sendTo        收件人
     * @param subject       主题
     * @param obj           内容
     * @param imagePath     内嵌图片地址
     * @param saveEmailPath 邮件保存地址
     * @throws Exception
     */
    public static MimeMessage createImageMail(Session session, String sendFrom,
                                              String sendTo, String subject, Object obj, String[] ccs,
                                              String[] bccs, String imagePath, String saveEmailPath)
            throws Exception {
        // 创建邮件
        MimeMessage message = baseSendMessage(session, sendFrom, sendTo, subject, ccs, bccs);
        MimeMultipart mm = setImageMessage(obj, imagePath);
        message.setContent(mm);
        message.saveChanges();
        // 将创建好的邮件写入到E盘以文件的形式进行保存
        SaveSendMail(message, sendTo, saveEmailPath);
        // 返回创建好的邮件
        return message;
    }

    /**
     * 创建一封带附件的邮件
     *
     * @param session
     * @param sendFrom
     * @param sendTo
     * @param subject
     * @param obj
     * @param imagePath
     * @param imagePath
     * @param saveEmailPath
     * @throws Exception
     */
    public static MimeMessage createAttachMail(Session session,
                                               String sendFrom, String sendTo, String subject, Object obj,
                                               String[] ccs, String[] bccs, String imagePath, List<String> attachFilePath, String saveEmailPath)
            throws Exception {
        MimeMessage message = baseSendMessage(session, sendFrom, sendTo, subject, ccs, bccs);
        MimeMultipart mp = setAttachmentMessage(obj, imagePath, attachFilePath, false);
        message.setContent(mp);
        message.saveChanges();
        // 将创建的Email写入到E盘存储
        //保存发送的邮件
        SaveSendMail(message, sendTo, saveEmailPath);
        // 返回生成的邮件
        return message;
    }

    /**
     * 生成一封带附件和带图片的邮件
     *
     * @param session
     * @param sendFrom
     * @param sendTo
     * @param subject
     * @param obj
     * @param imagePath
     * @param saveEmailPath
     * @throws Exception
     */
    public static MimeMessage createMixedMail(Session session, String sendFrom,
                                              String sendTo, String subject, Object obj, String[] ccs,
                                              String[] bccs, String imagePath, List<String> attachFilePath, String saveEmailPath)
            throws Exception {
        // 创建邮件
        MimeMessage message = baseSendMessage(session, sendFrom, sendTo, subject, ccs, bccs);
        // 描述关系:正文和附件
        MimeMultipart mp2 = setAttachmentMessage(obj, imagePath, attachFilePath, true);
        message.setContent(mp2);
        message.saveChanges();
        //保存发送的邮件
        SaveSendMail(message, sendTo, saveEmailPath);
        // 返回创建好的的邮件
        return message;
    }

    /**
     * 设置内嵌图片的内容
     *
     * @param obj
     * @param imagePath
     * @return
     */
    private static MimeMultipart setImageMessage(Object obj, String imagePath) {
        MimeMultipart mm = new MimeMultipart();
        try {
            // 开始准备邮件数据 准备邮件正文数据
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(obj, "text/html;charset=UTF-8");
            MimeBodyPart image = new MimeBodyPart();
            // 准备图片数据
            DataHandler dh = new DataHandler(new FileDataSource(imagePath));
            image.setDataHandler(dh);
            /**
             * 在 HTML 格式的正文中内含图片是使用 MimeBody.setContentID() 方法设置对应的资源文件的唯一标识符， 即
             * MIME 协议对于邮件的结构组织格式中的 Content-ID 头字段；而在 MIME 邮件中添加附件是使用
             * MimeBodyPart.setFileName() 方法来关联 FileSourceData 对象指向的资源文件。
             */
            int lastIndex = imagePath.lastIndexOf("/");
            String contentId = imagePath.substring(lastIndex + 1, imagePath.length());
            image.setContentID(contentId);
            // 描述数据关系
            mm.addBodyPart(text);
            mm.addBodyPart(image);
            mm.setSubType("related");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mm;
    }

    /**
     * 设置含有附件的内容
     *
     * @param obj
     * @param imagePath
     * @return
     */
    private static MimeMultipart setAttachmentMessage(Object obj, String imagePath, List<String> attachFilePath, boolean contentFlag) {
        MimeMultipart mp = new MimeMultipart();
        try {
            //循环将附件导入内容中
            for (String filePath : attachFilePath) {
                // 创建邮件附件
                MimeBodyPart attach = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource(filePath));
                attach.setDataHandler(dh);
                attach.setFileName(dh.getName());
                mp.addBodyPart(attach);
            }
            if (contentFlag) {
                // 创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
                MimeBodyPart text = new MimeBodyPart();
                text.setContent(obj, "text/html;charset=UTF-8");
                // 创建容器描述数据关系
                mp.addBodyPart(text);
            } else {
                // 描述关系:正文和图片
                MimeMultipart mp1 = setImageMessage(obj, imagePath);
                // 代表正文的bodypart
                MimeBodyPart content = new MimeBodyPart();
                content.setContent(mp1);
                mp.addBodyPart(content);
            }
            /**
             * multipart/mixed：附件。
             * multipart/related：内嵌资源。
             * multipart/alternative：纯文本与超文本共存。
             */
            mp.setSubType("mixed");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mp;
    }

    private static void SaveSendMail(MimeMessage message, String sendTo, String saveEmailPath) {
        try {
            File file = new File(saveEmailPath);
            if (!file.exists()) file.mkdirs();
            saveEmailPath = saveEmailPath + sendTo + "_" + (new Date()).getTime() + ".eml";
            message.writeTo(new FileOutputStream(saveEmailPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     */
    public static byte[] getByte(File file) {
        byte[] b = null;
        try {
            if (null != file && file.exists()) {
                FileInputStream in = new FileInputStream(file);
                ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
                System.out.println("bytes available:" + in.available());
                byte[] temp = new byte[1024];
                int size = 0;
                while ((size = in.read(temp)) != -1) {
                    out.write(temp, 0, size);
                }
                in.close();
                b = out.toByteArray();
                System.out.println("bytes size got is:" + b.length);
            }
        } catch (Exception e) {
            System.out.println("邮件文件读取出错");
        }
        return b;
    }
}