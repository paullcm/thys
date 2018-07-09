package com.cinfotech.db;

import java.util.Map;

import com.cinfotech.utils.HttpGet;
import com.cinfotech.utils.HttpGetCallBack;
import com.cinfotech.utils.ResourceUtils;

public class DownLoadFromDB implements RowCallBack,HttpGetCallBack{
	ResourceUtils res=ResourceUtils.getInstance();
	SqlUtils sql=new SqlUtils();
	public static void main(String[] args) throws Exception {    
    	new DownLoadFromDB().down();
    }
	public  void down() throws Exception {
         sql.init(res.getUrl(),res.getUserName(), res.getPassword());
         sql.query("select distinct pic_url from pictures where pic_dir is null ",this);
         sql.close();
	}
	public void rowData(Map map) {
		HttpGet oInstance = new HttpGet();
		String url=(String) map.get("pic_url");
		oInstance.addItem(url, oInstance.getFileNmae(url) );
		oInstance.downLoadByList(this);
		oInstance.resetList() ;
	}
	public void downFinished(String url, String name)  {
		try {
			sql.updateSql("update pictures set pic_dir='"+name+"' where pic_url='"+url+"'");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
