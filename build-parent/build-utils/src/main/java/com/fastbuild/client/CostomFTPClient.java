package com.fastbuild.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *  链接FTP 客户端
 */
public class CostomFTPClient {

    public static FTPClient getConnectionFTP(String hostName, int port,
                                             String userName, String passWord) {
        // 创建FTPClient对象
        org.apache.commons.net.ftp.FTPClient ftpClient = new org.apache.commons.net.ftp.FTPClient();
        try {
            // 连接FTP服务器
            ftpClient.connect(hostName, port);
            // 被动连接
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("UTF-8");
            // 登录ftp
            ftpClient.login(userName, passWord);
            ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);
            // 设置以字节流传输模式
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
//            ftpClient.setAutodetectUTF8(true);
            // 保存FTP控制连接使用的字符集，必须在连接前设置
            ftpClient.setControlEncoding("UTF-8");
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    public static boolean downFtpFile(String hostName, int port, String username, String password, String remotePath,
                                      String fileName, String localPath) {
        boolean success = false;
        org.apache.commons.net.ftp.FTPClient ftp = getConnectionFTP(hostName, port, username, password);
        try {
            if(ftp.isConnected()){

            }else{
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                String fname = new String(ff.getName().getBytes("iso-8859-1"), "gbk");
                if (fname.equals(fileName)) {
                    File localFile = new File(localPath + fname);
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                    break;
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * @author xh 测试成功 可以上传中文文件
     * @param port
     * @param username
     * @param password
     * @param path
     * @param filename
     * @param input
     * @return
     */
    public static boolean uploadFile(String hostName, int port, String username, String password, String path,
                                     String filename, InputStream input) {
        boolean success = false;
        org.apache.commons.net.ftp.FTPClient ftp = getConnectionFTP(hostName, port, username, password);
        try {
            ftp.changeWorkingDirectory(path);
            ftp.setControlEncoding("ISO-8859-1");
            ftp.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);
            ftp.storeFile(new String(filename.getBytes("GBK"), "ISO-8859-1"), input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {

                }
            }
        }
        return success;
    }
}
