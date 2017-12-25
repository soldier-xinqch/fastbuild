package com.fastbuild.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpBllUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpBllUtil.class);

	/**
	 * HTTPS　上传文件
	 * @param reqURL
	 * @param params
	 * @param header
	 * @param encodeCharset
	 * @param uploadFile
	 * @return
	 */
	public static String httpsPostUpLoadFile(String reqURL,	Map<String, String> params, Map<String, String> header, String encodeCharset,File uploadFile) {
		logger.debug("【请求远程服务器并推送数据】start-->,请求地址：{},参数：{},头信息:{},编码:{},文件路径:{}", reqURL,MapUtils.isNotEmpty(params)?JSONObject.toJSON(params):null
				,MapUtils.isNotEmpty(header)?JSONObject.toJSON(header):null,encodeCharset,null != uploadFile?uploadFile.getAbsolutePath():null);
		logger.debug("推送文件详情，文件长度：{},文件名称：{}",uploadFile.length(),uploadFile.getName());
		String responseCode = null;
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String responseContent = null;
		try {
			httpClient =  buildSSLCloseableHttpClient(); // 创建默认的httpClient实例
			httpPost = new HttpPost(reqURL);
			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列
			if(!MapUtils.isEmpty(params)){
				for (Map.Entry<String, String> entry : params.entrySet()) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			setHeader(httpPost, header);
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));
			FileBody binFileBody = new FileBody(uploadFile);

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			// add the file params
			multipartEntityBuilder.addPart("file", binFileBody);
			// 设置上传的其他参数
			setUploadParams(multipartEntityBuilder, params);

			HttpEntity reqEntity = multipartEntityBuilder.build();
			httpPost.setEntity(reqEntity);
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			responseCode = ""+response.getStatusLine().getStatusCode();
			
			responseContent = getEntity(response, reqURL, encodeCharset);
					
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("HTTP请求响应码为:" + response.getStatusLine().getStatusCode());
			}
			if(StringUtils.isEmpty(responseContent)){
				logger.debug("httpsPost 请求地址:{},响应状态:{},响应长度:{},响应内容: {}", reqURL, response.getStatusLine(),responseContent);
				return "上传京东红包卡账单失败";
			}
		} catch (Exception e) {
			logger.debug("httpsPost 该异常通常是协议错误导致,比如构造HttpPost对象时传入的协议不对(将'http'写成'htp')或者服务器端返回的内容不符合HTTP协议要求等,堆栈信息如下" + e);
			return  "请求服务器超时:" + e.getMessage();
		} finally {
//			//报文流水号
//			String transactionId = UtilFunction.createUUID();
//			logger.debug("【请求远程服务器完毕】reqUrl:{},请求内容：{},响应内容:{}",  reqURL,uploadFile.getName(),responseContent);
//			TransLog.addHttpsJDLog( null , uploadFile.getName(),responseContent, transactionId, RestModelManger.isFail(restModel) ? "1" : "0", String.valueOf(restModel.getCode()));

		}
		return responseContent;
	}
	
	/**
	 * 设置上传文件时所附带的其他参数
	 * 
	 * @param multipartEntityBuilder
	 * @param params
	 */
	private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder, Map<String, String> params) {
		if (params != null && params.size() > 0) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				multipartEntityBuilder.addPart(key, new StringBody(params.get(key), ContentType.TEXT_PLAIN));
			}
		}
	}
	
	 /** 
     * 获取response header中Content-Disposition中的filename值 
     * @param response 
     * @return 
     */  
    public static String getFileName(HttpResponse response) {  
        Header contentHeader = response.getFirstHeader("Content-Disposition");  
        String filename = null;  
        if (contentHeader != null) {  
            HeaderElement[] values = contentHeader.getElements();  
            if (values.length == 1) {  
                NameValuePair param = values[0].getParameterByName("filename");  
                if (param != null) {  
                    try {  
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");  
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");  
                        filename = param.getValue();  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return filename;  
    }  
	/**
	 * 将返回结果转化为String
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private static String getRespString(HttpEntity entity) throws Exception {
		if (entity == null) {
			return null;
		}
		InputStream is = entity.getContent();
		StringBuffer strBuf = new StringBuffer();
		byte[] buffer = new byte[4096];
		int r = 0;
		while ((r = is.read(buffer)) > 0) {
			strBuf.append(new String(buffer, 0, r, "UTF-8"));
		}
		is.close();
		return strBuf.toString();
	}
	
	
	
	
	public static String httpsGet( String reqURL, Map<String, String> header,
			String decodeCharset) {
		long responseLength = 0; // 响应长度
		String responseContent = null; // 响应内容
		Date reqDate = new Date();
		try {
			HttpResponse response = sendHttpsGetRequest(reqURL, header, decodeCharset);
			responseContent = getEntity(response, reqURL, decodeCharset);

			logger.debug("httpsGet 请求地址:{},响应状态:{},响应长度:{},响应内容: {}", reqURL, response.getStatusLine(), responseLength,
					responseContent);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("HTTP请求响应码为:" + response.getStatusLine().getStatusCode());
			}
			return responseContent;
		} catch (Exception e) {
			logger.debug(
					"httpsGet 该异常通常是协议错误导致,比如构造HttpGet对象时传入的协议不对(将'http'写成'htp')或者服务器端返回的内容不符合HTTP协议要求等,堆栈信息如下" + e);
			return "[httpsGet]请求服务器异常:" + e.getMessage();
		} finally {
			logger.debug("【请求远程服务器完毕】reqUrl:{},请求内容：{},响应内容:{}", reqURL, null, responseContent);
		}
	}

	public static String httpsPost( String reqURL, Map<String, String> params,
			Map<String, String> header, String encodeCharset) {
		String responseContent = null;
		Date reqDate = new Date();
		try {
			HttpResponse response = sendHttpsPostRequest(reqURL, params, header, encodeCharset);
			responseContent = getEntity(response, reqURL, encodeCharset);
			logger.debug("httpsPost 请求地址:{},响应状态:{},响应长度:{},响应内容: {}", reqURL, response.getStatusLine(),
					responseContent);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("HTTP请求响应码为:" + response.getStatusLine().getStatusCode());
			}
			return responseContent;
		} catch (Exception e) {
			logger.debug(
					"httpsPost 该异常通常是协议错误导致,比如构造HttpPost对象时传入的协议不对(将'http'写成'htp')或者服务器端返回的内容不符合HTTP协议要求等,堆栈信息如下" + e);
			return "[HttpPost]请求服务器异常:" + e.getMessage();
		} finally {
			logger.debug("【请求远程服务器完毕】reqUrl:{},请求内容：{},响应内容:{}", reqURL, null, responseContent);
		}
	}

	private static String getEntity(HttpResponse response, String reqURL, String decodeCharset) throws Exception {
		HttpEntity entity = response.getEntity();
		String responseContent = null;
		if (null != entity) {
			responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
			// 关闭流
			EntityUtils.consume(entity);
		}
		logger.debug("sendHttpsGetRequest 请求地址:{},响应状态:{},响应内容: {}", reqURL, response.getStatusLine(), responseContent);

		return responseContent;
	}

	/**
	 * 忽略https 证书直接调用https服务
	 * 
	 * @return
	 */
	private static CloseableHttpClient buildSSLCloseableHttpClient() {
		SSLContext sslContext;
		CloseableHttpClient httpClient = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			// ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// .setMaxConnTotal(totalConn).setDefaultRequestConfig(config).build();
			System.setProperty("jsse.enableSNIExtension", "false");
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return httpClient;
	}

	/**
	 * https GET请求
	 * 
	 * @param reqURL
	 * @param decodeCharset
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse sendHttpsGetRequest(String reqURL, Map<String, String> header, String decodeCharset)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = buildSSLCloseableHttpClient();
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
		HttpGet httpGet = new HttpGet(reqURL);
		httpGet.setConfig(requestConfig);
		setHeader(httpGet, header);
		HttpResponse response = httpClient.execute(httpGet); 
		httpGet.releaseConnection(); 
		httpClient.close();
		return response;
	}

	/**
	 * https POST 请求
	 * 
	 * @param reqURL
	 * @param params
	 * @param encodeCharset
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	public static HttpResponse sendHttpsPostRequest(String reqURL, Map<String, String> params,
			Map<String, String> header, String encodeCharset)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = buildSSLCloseableHttpClient(); 
		HttpPost httpPost = new HttpPost(reqURL);
		// 创建参数队列
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if(!MapUtils.isEmpty(params)){
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		setHeader(httpPost, header);
		httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));
		HttpResponse response = httpClient.execute(httpPost); 
		return response;
//		try {
//			return httpClient.execute(httpPost);
//		} finally {
//			httpPost.releaseConnection(); // 关闭连接,释放资源
//			httpClient.close();
//		}
	}

	/**
	 * 设置https 请求头
	 * @param base
	 * @param headerMap
	 */
	private static void setHeader(HttpRequestBase base, Map<String, String> headerMap) {
		if (null != headerMap) {
			for (String headerKey : headerMap.keySet()) {
				base.setHeader(headerKey, headerMap.get(headerKey));
			}
		}
	}

	public static void main(String[] args) {
		try {
//			String url = "http://172.16.20.46:9910/flow_app/interface";
//			String reqJson = "{\"biz\":{\"activityId\":\"00000000497DF1F1B81E4315E053433210ACCF54\",\"activityName\":\"今日头条\",\"phoneNumber\":\"17729380749\",\"rechargeDate\":\"2017-04-24 19:03:08\",\"sendContent\":\"\",\"sendType\":\"\"},\"head\":{\"appCode\":\"A00101\",\"attach\":\"hello, 189.cn\",\"method\":\"ORDER.3TO4.CREATE\",\"reqTime\":\"2017-04-24 19:03:05\",\"sign\":\"7e032eecc0172d3bc9395ec64d298bd6\",\"sysCode\":\"S00101\",\"transactionId\":\"S00101A00101170424190371ee1988cabc46e9bcbb3935bb580a0e\",\"version\":1}}";
//			System.out.println(HttpUtility.post(url, reqJson, null, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
