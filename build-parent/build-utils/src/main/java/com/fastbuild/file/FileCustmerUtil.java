package com.fastbuild.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  对文件的处理
 *  对路径的获取 文件的检索 文件的压缩等
 */
public class FileCustmerUtil {
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out
                    .println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    public static List<File> traverseFolder(String path,String fileType){
        List<File> files = new ArrayList<>();
        traverseFolder(path,files,fileType);
        return files;
    }
    /**
     * 遍历文件夹下的所有文件
     * @param path
     */
    public static List<File> traverseFolder(String path,List<File> fileList,String fileType) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println(path+"目录下文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath(),fileList,fileType);
                    } else {
                        if (fileType.equals(getFileExtension(file2))){
                            fileList.add(file2);
                        }
                    }
                    System.out.println("文件:" + file2.getAbsolutePath());
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileList;
    }

    /**
     * 获取文件扩展名
     * @param file
     * @return
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
