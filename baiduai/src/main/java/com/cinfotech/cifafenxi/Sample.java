package com.cinfotech.cifafenxi;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class Sample {
    //����APPID/AK/SK
    public static final String APP_ID = "11460286";
    public static final String API_KEY = "EYPYGGE6AkyIG7F52K3s806n";
    public static final String SECRET_KEY = "R3C2LfpfGhtuDjOnh0S0koddnXkqEGfr";

    public static void main(String[] args) {
        // ��ʼ��һ��AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // ��ѡ�������������Ӳ���
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // ��ѡ�����ô����������ַ, http��socket��ѡһ�����߾�������
//        client.setHttpProxy("proxy_host", proxy_port);  // ����http����
//        client.setSocketProxy("proxy_host", proxy_port);  // ����socket����

        // ��ѡ������log4j��־�����ʽ���������ã���ʹ��Ĭ������
        // Ҳ����ֱ��ͨ��jvm�����������ô˻�������
        System.setProperty("aip.log4j.conf", ".src/log4j.properties");

        // ���ýӿ�
        String text = "�ٶ���һ�Ҹ߿Ƽ���˾";
        JSONObject res = client.lexer(text, null);
        System.out.println(res.toString(2));
        
    }
}