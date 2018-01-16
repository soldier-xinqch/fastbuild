package com.fastbuild.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class BeginMail {

    private static Properties chooseMailPlatform(String smtpHostName,String sendFromUser){
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用s
        smtpHostName =null != smtpHostName&& "" != smtpHostName?smtpHostName:"smtp." + sendFromUser.split("@")[1];
        // 设置邮件服务器主机名
        props.setProperty("mail.host", smtpHostName);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");


        return props;
    }

    public static void sendMail(final EmailPojo emailPojo){
        try {
            Properties props = chooseMailPlatform(emailPojo.getSmtpHostName(),emailPojo.getSendFromUser());
            //1、创建session 验证发件人邮箱
            Session session = Session.getInstance(props, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailPojo.getSendFromUser(), emailPojo.getSendFromPassWord());
                }});
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、连上邮件服务器
            String smtpHostName = emailPojo.getSmtpHostName();
            smtpHostName = null != smtpHostName&& "" != smtpHostName?smtpHostName:"smtp." + emailPojo.getSendFromUser().split("@")[1];
            ts.connect(smtpHostName,emailPojo.getSendFromUser(), emailPojo.getSendFromPassWord());
            //4、创建邮件
            Message message = null;
            switch (emailPojo.getMethodType()) {
                case 1:
                    message =MailUtils.createSimpleMail(session, emailPojo.getSendFrom(), emailPojo.getSendTo(), emailPojo.getSubject(), emailPojo.getObj(),
                            emailPojo.getCcs(), emailPojo.getBccs(),emailPojo.getSaveEmailPath());
                    break;
                case 2:
                    message =MailUtils.createImageMail(session, emailPojo.getSendFrom(), emailPojo.getSendTo(), emailPojo.getSubject(), emailPojo.getObj(),
                            emailPojo.getCcs(), emailPojo.getBccs(),emailPojo.getImagePath(),emailPojo.getSaveEmailPath());
                    break;
                case 3:
                    message =MailUtils.createMixedMail(session, emailPojo.getSendFrom(), emailPojo.getSendTo(), emailPojo.getSubject(), emailPojo.getObj(),
                            emailPojo.getCcs(), emailPojo.getBccs(),emailPojo.getImagePath(),emailPojo.getAttachFilePath(),emailPojo.getSaveEmailPath());
                    break;
                case 4:
                    message =MailUtils.createAttachMail(session, emailPojo.getSendFrom(), emailPojo.getSendTo(), emailPojo.getSubject(), emailPojo.getObj(),
                            emailPojo.getCcs(), emailPojo.getBccs(),emailPojo.getImagePath(),emailPojo.getAttachFilePath(),emailPojo.getSaveEmailPath());
                    break;
            }
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}