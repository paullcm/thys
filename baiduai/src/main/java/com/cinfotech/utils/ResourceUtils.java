package com.cinfotech.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ResourceUtils {
	String url;
	String userName ;
	String password ;
	private static Properties property ;
	private static ResourceUtils inst=null;
	private ResourceUtils(){
		
	}
	
	
	public synchronized static ResourceUtils getInstance(){
		if(inst==null){
			inst=new ResourceUtils();
			inst.init();
		}
		return inst;
	}
	public static void main(String[] args) {
		 ResourceUtils.getInstance();
	}

	private   void init() {
		// String configFile = "D:/test/application.properties";
		// 如果配置文件在classpath目录下可以使用ClassPathResource对象
		Resource resource = new ClassPathResource("/application.properties");
		// Resource resource = new FileSystemResource( configFile );
		try {
			  property = PropertiesLoaderUtils
					.loadProperties(resource);
			  url = property.getProperty("db.url");
			  userName = property.getProperty("db.user");
			  password = property.getProperty("db.pwd");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}


	public String getUrl() {
		return url;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}
	
	
}