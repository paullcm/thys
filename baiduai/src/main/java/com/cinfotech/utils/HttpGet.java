package com.cinfotech.utils;

import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * <p>
 * Title: 个人开发的API
 * </p>
 * <p>
 * Description: 将指定的HTTP网络资源在本地以文件形式存放
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: NewSky
 * </p>
 * 
 * @author MagicLiao
 * @version 1.0
 */
public class HttpGet {
	public final static boolean DEBUG = true;// 调试用
	private static int BUFFER_SIZE = 8096;// 缓冲区大小
	private Vector vDownLoad = new Vector();// URL列表
	private Vector vFileList = new Vector();// 下载后的保存文件名列表
	private static int counter = 0;
	private static int counter1 = 0;
	/**
	 * 构造方法
	 */
	public HttpGet() {
	}

	/**
	 * 清除下载列表
	 */
	public void resetList() {
		vDownLoad.clear();
		vFileList.clear();
	}

	/**
	 * 增加下载列表项
	 * 
	 * @param url
	 *            String
	 * @param filename
	 *            String
	 */
	public void addItem(String url, String filename) {
		vDownLoad.add(url);
		vFileList.add(filename);
	}

	/**
	 * 根据列表下载资源
	 */
	public void downLoadByList(HttpGetCallBack cb) {
		String url = null;
		String filename = null;

		// 按列表顺序保存资源
		for (int i = 0; i < vDownLoad.size(); i++) {
			url = (String) vDownLoad.get(i);
			filename = (String) vFileList.get(i);
			try {
				saveToFile(url, filename);
				if(cb!=null) cb.downFinished(url, filename);
			} catch (Exception err) {
				if (DEBUG) {
					System.out.println("资源[" + url + "]下载失败!!!");
				}
			}
		}
		if (DEBUG) {
			System.out.println("下载完成!!!");
		}
	}

	/**
	 * 将HTTP资源另存为文件
	 * 
	 * @param destUrl
	 *            String
	 * @param fileName
	 *            String
	 * @throws Exception
	 */
	public void saveToFile(String destUrl, String fileName) throws  Exception {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		int ts = 0;

		int chunked=0;
		try {
			// 建立链接
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
//			httpUrl.setRequestProperty("Accept-Encoding", "gzip");//为什么没有deflate呢
			httpUrl.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
			httpUrl.addRequestProperty("Connection", "Close");

			// 连接指定的资源
			httpUrl.connect();
			System.out.println(counter+"下载回应消息:" + httpUrl.getResponseCode());
			String h=httpUrl.getHeaderField("Transfer-Encoding");
			if(h==null || h.indexOf("chunked")<0){
				chunked=-1;
			}else{
				chunked=1;
			}
			// 获取网络输入流
			bis = new BufferedInputStream(httpUrl.getInputStream());
			// 建立文件
			new File(getDir(fileName)).mkdirs();
			fos = new FileOutputStream(fileName);
			if (this.DEBUG)
				System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["
						+ fileName + "]");
			// 保存文件
			while ((size = bis.read(buf)) != -1){				 
				ts+=size;
				fos.write(buf, 0, size);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(fos!=null)	fos.close();
			if(bis!=null)	bis.close();
			if(httpUrl!=null) httpUrl.disconnect();
		}
		counter++;
 
	}
 

	/**
	 * 设置认证用户名与密码
	 * 
	 * @param uid
	 *            String
	 * @param pwd
	 *            String
	 */
	public void setAuthenticator(String uid, String pwd) {
		Authenticator.setDefault(new MyAuthenticator(uid, pwd));
	}

	/**
	 * 主方法(用于测试)
	 * 
	 * @param argv
	 *            String[]
	 */
	public static void main(String args[]) {
		HttpGet oInstance = new HttpGet();
		//oInstance.setProxyServer("135.251.208.163", "80");
		try { 
			
			for (int i = 0; i <1; i++) 
			{
				 
					oInstance.addItem("http://222.211.94.245:8084/tyfoSrvEx/imagedeal?path=T1SHKbBXDT1R4bAZ6K.jpg", getFileNmae("http://222.211.94.245:8084/tyfoSrvEx/imagedeal?path=T1SHKbBXDT1R4bAZ6K.jpg"));
					oInstance.downLoadByList(null);
					oInstance.resetList() ;
					Thread.sleep(10000);
			}
			// 开始下载
			
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

	private String getDir(String fileName) {
		int idx=fileName.lastIndexOf("/");
		if(idx>=0){
			return fileName.substring(0,idx);
		}
		return "";
	}
/*
 * http://222.211.94.245:8084/tyfoSrvEx/imagedeal?path=T1SHKbBXDT1R4bAZ6K.jpg
 * http://item.tyfo.com:80/css/buy-guide.jpg
 */
	public static  String getFileNmae(String url) {
		String tmpName="";
	  	int idx=url.lastIndexOf("/");
    	if(idx>=0){
    		int eidx=url.indexOf("?",idx);
    		if(eidx>=0){
    			 tmpName=url.substring(eidx+1);
    			 eidx=url.indexOf("=",eidx);
    			 if(eidx>=0){
    				 tmpName=url.substring(eidx+1);
    			 }
    		}
    		tmpName=addDirNmae(tmpName);
    	}else{
    		System.err.println("bad name:"+url);
    	}
		return tmpName;
	}

	public  static String addDirNmae(String fileName) {
		String dirname="imgs/";
		if(fileName.length()>3){
			dirname=dirname+fileName.substring(0,3)+"/";
			if(fileName.length()>6){
				dirname=dirname+fileName.substring(3,6)+"/";
				if(fileName.length()>9){
					dirname=dirname+fileName.substring(6,9)+"/";
				}
			}
		}
		return dirname+fileName;
	}

	class MyAuthenticator extends Authenticator {
		String uid = "";
		String pwd = "";

		public MyAuthenticator(String u, String p) {
			uid = u;
			pwd = p;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(uid, pwd.toCharArray());
		}
	}
}