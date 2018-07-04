package com.cinfotech.picreview;


import java.net.URLEncoder;

import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.aip.util.Base64Util;

/**
* ͼ����˽ӿ�
*/
public class UserDefined {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String userDefined() {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
        try {
            // �����ļ�·��
            String filePath = "c:/1/z.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = "24.7a654709273715b73cd4035a626d0978.2592000.1532766318.282335-11459933";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UserDefined.userDefined();
    }
}