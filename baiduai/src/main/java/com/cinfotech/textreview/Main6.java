package com.cinfotech.textreview;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.cinfotech.common.HttpUtil;
 
public class Main6 {
	public static void main(String[] args)
	{
		Main6 m = new Main6();
//		 System.out.println(m.gettoken());
//		 String access_token=m.gettoken();
//		 System.out.println(access_token);
		String access_token = "24.7a654709273715b73cd4035a626d0978.2592000.1532766318.282335-11459933";
		try {
			m.get_text("������,�ٵ�", "https://aip.baidubce.com/rest/2.0/antispam/v2/spam", access_token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public String gettoken() {
 
		// ������ȡ�� API Key ����Ϊ��ע���
		String clientId = "syKWIEuwzBT787N5lumVHX6Y";
		// ������ȡ�� Secret Key ����Ϊ��ע���
		String clientSecret = "Zbyv8ClrpvhMBgMiZQ2VpyKMuzGG44v4 ";
		return getAuth(clientId, clientSecret);
	}
	
	public static String getAuth(String ak, String sk) {
		//
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// 1. grant_typeΪ�̶�����
				+ "grant_type=client_credentials"
				// 2. ������ȡ�� API Key
				+ "&client_id=" + ak
				// 3. ������ȡ�� Secret Key
				+ "&client_secret=" + sk;
		try {
			URL realUrl = new URL(getAccessTokenUrl);
			// �򿪺�URL֮�������
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			//Map<String, List<String>> map = connection.getHeaderFields();
			// ���� BufferedReader����������ȡURL����Ӧ
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = "";
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			/**
			 * ���ؽ��ʾ��
			 */
			JSONObject jsonObject = new JSONObject(result);
			String access_token = jsonObject.getString("access_token");
			return access_token;
		} catch (Exception e) {
			System.err.printf("��ȡtokenʧ�ܣ�");
			e.printStackTrace(System.err);
		}
		return null;
	}
	public String get_text(String content,String url,String accessToken)
	{
		String param;
		String data;
		try {
			//��������ı���
			param = "content="+URLEncoder.encode(content,"UTF-8");
			//���Ͳ�ȡ�ý��
			data = HttpUtil.post(url, accessToken, param);
			System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}