package com.fastbuild.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.ImageUtils;


/**
 * 图片工具类
 *
 * @author xinqch http://blog.csdn.net/cyjch/article/details/51658889
 *         http://blog.sina.com.cn/s/blog_12f2b61090102wyov.html
 *         http://www.cnblogs.com/dyllove98/archive/2013/08/02/3233984.html
 */
public class ImageUtil {

    /**
     * 指定大小进行缩放
     *
     * @param srcUrl
     *            源图片地址
     * @param targetUrl
     *            目标图片地址
     * @param width
     *            宽
     * @param height
     *            高
     * @throws IOException
     */
    public static void resize(String srcUrl, String targetUrl, int width, int height){
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        try {
            Thumbnails.of(srcUrl).size(width, height).toFile(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照比例进行缩放
     *
     * @param srcUrl
     *            源图片地址
     * @param targetUrl
     *            目标图片地址
     * @param num
     *            质量比例如 0.8
     * @throws IOException
     */
    public static void scale(String srcUrl, String targetUrl, double num){
        try {
            Thumbnails.of(srcUrl).scale(num).toFile(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 水印
     *
     * @param srcUrl
     *            源图片地址
     * @param targetUrl
     *            目标图片地址
     * @param width
     *            宽
     * @param height
     *            高
     * @param num
     *            质量比例如 0.8
     * @param pos
     *            显示位置: Positions.BOTTOM_RIGHT
     * @throws IOException
     */
    public static void watermark(String srcUrl, String targetUrl, int width, int height, float num, Positions pos){
        try {
            Thumbnails.of(srcUrl).size(width, height).watermark(pos, ImageIO.read(new File(targetUrl)), num)
                    .outputQuality(num).toFile(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪
     *
     * @param srcUrl
     *            源图片地址
     * @param targetUrl
     *            目标图片地址
     * @param width
     *            宽
     * @param height
     *            高
     * @param pos
     *            显示位置: Positions.BOTTOM_RIGHT
     * @param x
     *            区域宽度
     * @param y
     *            区域高度
     * @throws IOException
     */
    public static void cut(String srcUrl, String targetUrl, int width, int height, Positions pos, int x, int y){
        try {
            Thumbnails.of(srcUrl).sourceRegion(pos, x, y).size(width, height).keepAspectRatio(false).toFile(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪--指定坐标/大小
     *
     * @param srcUrl
     *            源图片地址
     * @param targetUrl
     *            目标图片地址
     * @param width
     *            宽
     * @param height
     *            高
     * @param pointA_1
     *            坐标A1
     * @param pointA_2 坐标A2
     * @param pointB_1 坐标B1
     * @param pointB_2 坐标B2
     * @throws IOException
     */
    public static void cut(String srcUrl, String targetUrl, int width, int height, int pointA_1, int pointA_2,
                           int pointB_1, int pointB_2) {
        try {
            Thumbnails.of(srcUrl).sourceRegion(pointA_1, pointA_2, pointB_1, pointB_2).size(width, height)
                    .keepAspectRatio(false).toFile(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转化图像格式
     *
     * @param srcUrl
     *            源图片地址
     * @param targetUrl
     *            目标图片地址
     * @param width
     *            宽
     * @param height
     *            高
     * @param format
     *            格式 如png/gif/jpg
     * @throws IOException
     */
    public static void format(String srcUrl, String targetUrl, int width, int height, String format){
        try {
            Thumbnails.of(srcUrl).size(width, height).outputFormat(format).toFile(targetUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出到OutputStream
     *
     * @param srcUrl
     * @param targetUrl
     * @param width
     * @param height
     * @throws IOException
     */
    public static OutputStream toOutPutStream(String srcUrl, String targetUrl, int width, int height) {
        try {
            OutputStream os = new FileOutputStream(targetUrl);
            Thumbnails.of(srcUrl).size(width, height).toOutputStream(os);
            return os;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出到BufferedImage
     *
     * @param srcUrl
     * @param targetUrl
     * @param width
     * @param height
     * @param format
     * @throws IOException
     */
    public static void toBufferedImage(String srcUrl, String targetUrl, int width, int height, String format){
        try {
            BufferedImage thumbnail = Thumbnails.of(srcUrl).size(width, height).asBufferedImage();
            ImageIO.write(thumbnail, format, new File(targetUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片转化成base64字符串
     *
     * @param in
     * @param imageType
     * @return
     */
    public static String GetImageStr(InputStream in, String imageType) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            if (null == in)
                return null;
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return base64ImageHeader(imageType) + Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
    }

    private static String base64ImageHeader(String imageType) {
        String imageHeader = "";
        switch (imageType) {
            case "jpg":
                imageHeader = "jpeg";
                break;
            case "png":
                imageHeader = "png";
                break;
        }

        return "data:image/" + imageHeader + ";base64,";
    }

    /**
     * base64字符串转化成图片
     *
     * @param imgStr
     * @return
     */
    public static String generateImage(String imgStr,String filePath, String makeImageName) { //
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return null;
        try {
            if(imgStr.contains("base64,")){
                String[] tempStrs =imgStr.split(";base64,");
                imgStr = tempStrs[1];
                String imgType = tempStrs[0];
                imgType = imgType.split("data:image/")[1];
                makeImageName = makeImageName+"."+imgType;
            }

            // Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            // 生成jpeg图片
            String imgFilePath = filePath + File.separator + makeImageName;// 新生成的图片
            File imgFile = new File(imgFilePath);
            if(!imgFile.exists()){
                imgFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String imgStr= "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";
        generateImage(imgStr, "C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.240253267110270479.6060\\uploadFiles", "min-15120Q42531-1.jpg");
    }

    /**
     * 根据图片输入流进行压缩并转换base64编码
     * @param in
     * @param width
     * @param height
     * @param format
     * @return
     */
    public static String parseImage(InputStream in,int width,int height,String format,boolean isHasImgType) {
        if(null != in){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream ins = null;
            try {
                Thumbnails.of(in).size(width, height).outputFormat(format).toOutputStream(out);
                ins = new ByteArrayInputStream(out.toByteArray());
                //进行base64编码
                String dataValue = ImageUtil.GetImageStr(ins,"png");
                if(dataValue.contains(",")&&null != dataValue.split(",")&&dataValue.split(",").length>0&&!isHasImgType){
                    return dataValue.split(",")[1];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据图片输入流进行压缩并转换base64编码
     * @param width
     * @param height
     * @param format
     * @return
     */
    public static String parseImage(String imgPath,int width,int height,String format,boolean isHasImgType) {
        if(!StringUtils.isEmpty(imgPath)){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream ins = null;
            try {
                Thumbnails.of(imgPath).size(width, height).outputFormat(format).toOutputStream(out);
                ins = new ByteArrayInputStream(out.toByteArray());
                //进行base64编码
                String dataValue = ImageUtil.GetImageStr(ins,"png");
                if(dataValue.contains(",")&&null != dataValue.split(",")&&dataValue.split(",").length>0&&!isHasImgType){
                    return dataValue.split(",")[1];
                }else{
                    return dataValue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String[] dealFaceHttpUrl(String faceUrl){
        String[] urlParams = faceUrl.split("/");
        String host = urlParams[0]+"//"+urlParams[2];
        String url = faceUrl.split(host)[1];
        return new String[]{url,host};
    }

}

