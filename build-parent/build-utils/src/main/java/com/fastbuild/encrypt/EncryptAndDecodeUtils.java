package com.fastbuild.encrypt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Title: EncryptAndDecodeUtils.java
 * @Description: 文件的加密及解密
 * @author :qianglei
 * @date :2017-2-16 下午2:39:34
 * @version :V1.0
 */
public class EncryptAndDecodeUtils {

	private static Logger logger = LoggerFactory.getLogger(EncryptAndDecodeUtils.class);

//	public static void main(String[] args) throws IOException {
//
//		File file = new File("C:/Users/qianglei/git/yys-bSystem/bSystem-sdk-web/src/test/java/com/jd/yys/bSystem/message/yuanwen.zip");
//
//		readZip("源文件", file);
//		/**
//		 * 加密zip
//		 */
//		File encryptZipFile = encryptZip(file, "miwen.zip");
//
//		readZip("加密后文件", encryptZipFile);
//		/**
//		 * 解密zip
//		 */
//		File decryptZipFile = decryptZip(encryptZipFile, "jiemi.zip",password);
//
//		readZip("解密后文件", decryptZipFile);
//	}
	
	/**
	 *  给文件加密并压缩
	 * @param file
	 * @param name
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public static File encryptZip(File file, String name,String password) throws IOException {
		if(StringUtils.isEmpty(name)) return null;
		String nameDir = name.split("\\.")[0];
		// 文件转为字节流
		byte[] in2b = fileTobyteArray(file);
		// 字节流加密
		byte[] encryptResult = encrypt(in2b, password);
		// 二进制转为16进制
		String hexStr = parseByte2HexStr(encryptResult);
		String encryptFileDir = file.getParentFile().getAbsolutePath() + File.separator +nameDir;
		File dirFile = new File(encryptFileDir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String fileName = file.getParentFile().getAbsolutePath() + File.separator +nameDir+File.separator+ file.getName();
//		new Date().getTime() + file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());			
		File tofile = new File(fileName);
		// 16进制流转为文件
		byteArrayToFile(hexStr.getBytes(), tofile);
		logger.debug("加密文件并压缩-加密文件地址：{}"+fileName);
		// 压缩文件
		List<File> flist = new ArrayList<File>();
		flist.add(tofile);
		File zipfile = zip(flist, tofile.getParentFile().getAbsolutePath() + File.separator + name);	
		//这里不删除 生成文件
//		file.deleteOnExit();
//		tofile.deleteOnExit();
		return zipfile;
	}
	
	
	public static List<File> decryptZipToContent(File file,String password) throws IOException {
		List<File> flist = new ArrayList<File>();
		// 解密zip过程
		List<File> compressList = unzip(file);
		if(CollectionUtils.isEmpty(compressList)){
			logger.debug("解压文件并解密-解压文件为空");
			return null;
		}
		String nameDir = file.getName().split("\\.")[0];
		if(compressList.size() == 1){
			File unzipFile = compressList.get(0);
			// 文件转为字节流
			byte[] in2b1 = fileTobyteArray(unzipFile);
			// 16进制转为二进制
			byte[] in2b2 = parseHexStr2Byte(new String(in2b1));
			// 解密
			byte[] decryptResult = decrypt(in2b2, password);
//			String result = new String(decryptResult, "UTF-8");
//			resultStrs.add(result);
			
			File dirFile = new File(unzipFile.getParentFile().getAbsolutePath() + File.separator + nameDir);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			// byte[]转为字节流
			String fileName = unzipFile.getParentFile().getAbsolutePath() + File.separator + nameDir+File.separator+unzipFile.getName();
//			+ unzipFile.getName().substring(unzipFile.getName().lastIndexOf("."), unzipFile.getName().length());
			File tofile = new File(fileName);
			byteArrayToFile(decryptResult, tofile);
			logger.debug("解压文件并解密-解密文件地址为：{}"+fileName);
			flist.add(tofile);
		}else{
			for (File item : compressList) {
				// 文件转为字节流
				byte[] in2b1 = fileTobyteArray(item);
				// 16进制转为二进制
				byte[] in2b2 = parseHexStr2Byte(new String(in2b1));
				// 解密
				byte[] decryptResult = decrypt(in2b2, password);

				File dirFile = new File(item.getParentFile().getAbsolutePath() + File.separator + nameDir);
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}
				// byte[]转为字节流
				String fileName = item.getParentFile().getAbsolutePath() + File.separator + nameDir + File.separator + item.getName();
//				.substring(item.getName().lastIndexOf("."), item.getName().length());
				File tofile = new File(fileName);
				byteArrayToFile(decryptResult, tofile);
				logger.debug("解压文件并解密-解密文件地址为：{}"+fileName);

				flist.add(tofile);
			}
		}
		return flist;
	}

	/**
	 * 读取zip文件内容
	 * 
	 * @param name
	 * @param file
	 * @throws IOException
	 */
	private static void readZip(String name, File file) throws IOException {
		// 解密zip过程
		List<File> list = unzip(file);
		for (File item : list) {
			String line = FileUtils.readFileToString(item);
			System.out.println(name + ":【" + item.getName() + "】:" + line);
		}

	}

	

	/**
	 * 解密zip文件
	 * 
	 * @param file
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static File decryptZip(File file, String name,String password) throws IOException {
		File zipfile = null;
		// 解密zip过程
		List<File> compressList = unzip(file);
		for (File item : compressList) {
			// 文件转为字节流
			byte[] in2b1 = fileTobyteArray(item);
			// 16进制转为二进制
			byte[] in2b2 = parseHexStr2Byte(new String(in2b1));
			// 解密
			byte[] decryptResult = decrypt(in2b2, password);
			// byte[]转为字节流
			String fileName = item.getParentFile().getAbsolutePath() + File.separator + new Date().getTime() + File.separator+item.getName();
			File tofile = new File(fileName);
			if(!tofile.getParentFile().exists()){
				tofile.getParentFile().mkdirs();
			}
			byteArrayToFile(decryptResult, tofile);
			// 压缩文件
			List<File> flist = new ArrayList<File>();
			flist.add(tofile);
			zipfile = zip(flist, item.getParentFile().getAbsolutePath() + File.separator + name);
			//删除原文件
			item.deleteOnExit();
			//删除解密后文件
			tofile.deleteOnExit();
			//删除空文件夹
			tofile.getParentFile().deleteOnExit();
		}
		return zipfile;
	}

	/**
	 * 加密zip文件
	 * 
	 * @param item,name.password
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static File encryptFile(File item, String name, String password) throws IOException {
		// 文件转为字节流
		byte[] in2b = fileTobyteArray(item);
		// 字节流加密
		byte[] encryptResult = encrypt(in2b, password);
		// 二进制转为16进制
		String hexStr = parseByte2HexStr(encryptResult);
		String fileName = item.getParentFile().getAbsolutePath() + File.separator + new Date().getTime()+File.separator+item.getName();
		File tofile = new File(fileName);
		if(!tofile.getParentFile().exists()){
			tofile.getParentFile().mkdirs();
		}
		// 16进制流转为文件
		byteArrayToFile(hexStr.getBytes(), tofile);
		// 压缩文件
		List<File> flist = new ArrayList<File>();
		flist.add(tofile);
		File zipfile = zip(flist, tofile.getParentFile().getAbsolutePath() + File.separator + name);
		item.deleteOnExit();
		tofile.deleteOnExit();
		return zipfile;
	}
	
	/**
	 * 加密zip文件
	 * 
	 * @param file
	 * @param name
	 * @return
	 * @throws IOException
	 */
//	public static File encryptZip(File file, String name,String password) throws IOException {
//		File zipfile = null;
//		// 加密zip过程
//		List<File> list = unzip(file);
//		for (File item : list) {
//			// 文件转为字节流
//			byte[] in2b = fileTobyteArray(item);
//			// 字节流加密
//			byte[] encryptResult = encrypt(in2b, password);
//			// 二进制转为16进制
//			String hexStr = parseByte2HexStr(encryptResult);
//			String fileName = item.getParentFile().getAbsolutePath() + File.separator + new Date().getTime() + item.getName().substring(item.getName().lastIndexOf("."), item.getName().length());
//			File tofile = new File(fileName);
//			// 16进制流转为文件
//			byteArrayToFile(hexStr.getBytes(), tofile);
//			// 压缩文件
//			List<File> flist = new ArrayList<File>();
//			flist.add(tofile);
//			zipfile = zip(flist, tofile.getParentFile().getAbsolutePath() + File.separator + name);
//			item.deleteOnExit();
//			tofile.deleteOnExit();
//		}
//		return zipfile;
//	}

	/**
	 * 压缩文件
	 * 
	 * @param files
	 * @param dir
	 * @return
	 */
	public static File zip(List<File> files, String dir) {
		if (files == null || files.size() <= 0) {
			return null;
		}
		ZipArchiveOutputStream zaos = null;
		File zipFile = new File(dir);
		try {
			zaos = new ZipArchiveOutputStream(zipFile);
			zaos.setUseZip64(Zip64Mode.AsNeeded);
			for (File strfile : files) {
				if (strfile != null) {
					ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(strfile, strfile.getName());
					zaos.putArchiveEntry(zipArchiveEntry);
					if (strfile.isDirectory()) {
						continue;
					}
					InputStream is = null;
					try {
						is = new BufferedInputStream(new FileInputStream(strfile));
						byte[] buffer = new byte[1024];
						int len = -1;
						while ((len = is.read(buffer)) != -1) {
							zaos.write(buffer, 0, len);
						}
						zaos.closeArchiveEntry();
					} catch (Exception e) {
						throw new RuntimeException(e);
					} finally {
						if (is != null)
							is.close();
					}
				}
			}
			zaos.finish();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (zaos != null) {
					zaos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return zipFile;
	}

	/**
	 * 解压文件
	 * 
	 * @param file
	 * @return
	 */
	public static List<File> unzip(File file) {
		List<File> list = new ArrayList<File>();
		// TODO Auto-generated method stub
		if (null != file && file.exists()) {
			InputStream is = null;
			ZipArchiveInputStream zais = null;
			try {
				is = new FileInputStream(file);
				zais = new ZipArchiveInputStream(is);
				ArchiveEntry archiveEntry = null;
				while ((archiveEntry = zais.getNextEntry()) != null) {
					// 获取文件名
					String entryFileName = archiveEntry.getName();
					// 构造解压出来的文件存放路径
					String entryFilePath = file.getParentFile().getAbsolutePath() + File.separator + entryFileName;
					OutputStream os = null;
					try {
						// 把解压出来的文件写到指定路径
						File entryFile = new File(entryFilePath);
						if (entryFileName.endsWith("/")) {
							entryFile.mkdirs();
						} else {
							os = new BufferedOutputStream(new FileOutputStream(entryFile));
							byte[] buffer = new byte[1024];
							int len = -1;
							while ((len = zais.read(buffer)) != -1) {
								os.write(buffer, 0, len);
							}
						}
						list.add(entryFile);
					} catch (IOException e) {
						throw new IOException(e);
					} finally {
						if (os != null) {
							os.flush();
							os.close();
						}
					}

				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (zais != null) {
						zais.close();
					}
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}

	/**
	 * 字节数组转为文件
	 * 
	 * @param data
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static void byteArrayToFile(byte[] data, File file) throws IOException {
		InputStream sbs = new ByteArrayInputStream(data);
		byte[] buff = new byte[100];
		int rc = 0;
		FileOutputStream out = new FileOutputStream(file);
		while ((rc = sbs.read(buff, 0, 100)) > 0) {
			out.write(buff, 0, rc);
		}
		out.flush();
		out.close();
	}

	/**
	 * 文件转为字节数组
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static byte[] fileTobyteArray(File file) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		FileInputStream in = new FileInputStream(file);
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = in.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		swapStream.close();
		in.close();
		return swapStream.toByteArray();
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	private static byte[] encrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128,random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES解密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128,random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
