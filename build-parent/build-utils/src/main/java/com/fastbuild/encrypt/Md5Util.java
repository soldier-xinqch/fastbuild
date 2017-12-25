package com.fastbuild.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * 
 * @author lsq
 */
public class Md5Util {
	
	
	/**
	 * Md
	 * 
	 * @param value
	 *            the value
	 * @return the string
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String md5(String value,String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return md5(value.getBytes(charset));
	}
	
	/**
	 * Md-UTF8
	 * 
	 * @param value
	 *            the value
	 * @return the string
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String value) throws Exception {
		try {
			return md5(value.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/**
	 * Md
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	public static String md5(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(bytes);
			return toHex(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * To hex
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	private static String toHex(byte bytes[]) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = Integer.toHexString(bytes[n] & 0xff);
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString();
	}

	/**
	 * 获取文件MD5
	 */
	public static String getMD5ByFile(String filePath) {
		String value = null;
		File file = new File(filePath);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
