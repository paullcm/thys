
https://console.bce.baidu.com/ai/#/ai/antiporn/overview/index

http://ai.baidu.com/docs#/TextCensoring-API/top

�ı����v2�ӿڣ���ʽ�棩
�ӿ�����
����ҵ�����ȵ����ѧϰ�������ж�һ���ı������Ƿ�������緢�Ĺ淶��ʵ���Զ��������ܻ����ı���ˡ��������Ŀǰ��Ϊ5����ɫ���ı����������С������ƹ㡢���籩�֡��������v2��ʽ��֧������ı��ĺڰ��������ã��ҿ�ͨ��������ֵ������˵��ɽ��ȱ�׼�������������Ի��ı�������˵�������ϸ����˵�����AI��������˵������

����˵��
����ʾ��

HTTP����: POST
����URL: https://aip.baidubce.com/rest/2.0/antispam/v2/spam
URL������
����	ֵ
access_token	ͨ��API Key��Secret Key��ȡ��access_token,�ο���Access Token��ȡ��
Header���£�
����	ֵ
Content-Type	application/x-www-form-urlencoded
body����ʾ��: content=��������������
�������

��������	����	�Ƿ����	��ϸ˵��
content	string	��	������ı���UTF-8������Ϊ�գ�������20000�ֽ�
����˵��
���ز���

��������	����	��ϸ˵��
logid	int	��ȷ�������ɵ�Ψһ��ʶ�룬�������ⶨλ
result	object	������˽������
+spam	int	�������Ƿ����Υ����0��ʾ��Υ����1��ʾΥ����2��ʾ�����˹�����
+reject	array	���δͨ��������б�������
+review	array	���˹����������б�������
+pass	array	���ͨ��������б�������
++label	int	�����е�Υ������
++score	float	Υ�����֣���Χ0~1����ֵ�ӵ͵��ߴ�����ճ̶ȵĸߵ�
++hit	array	Υ�����Ͷ�Ӧ���е�Υ���ʼ��ϣ�����Ϊ��
Υ��labels����˵��

ȡֵ	��ϸ˵��
1	����Υ��
2	�ı�ɫ��
3	��������
4	�����ƹ�
5	��������
����ʾ��
���ͨ���ķ���ʾ��

{
  "result": {
  	"spam": 0,
      "reject": [],
      "review": [],
      "pass": [
          {"label":1,"score":0.3,"hit":[]},  
          {"label":2,"score":0.33,"hit":[]}, 
          {"label":3,"score":0.2,"hit":[]}, 
          {"label":4,"score":0.31,"hit":[]}, 
          {"label":5,"score":0.19,"hit":[]}, 
      ]
  },
  "log_id": 5284009342430354247
}
���δͨ���ķ���ʾ��

{
  "result": {
      "spam": 1,
      "reject": [
          {"label":1,"score":0.87,"hit":["ɵX"]},
          {"label":3,"score":0.92,"hit":["˫Ͳ��ǹ"]},   
      ],
      "review": [{"label":4,"score":0.5,"hit":[]}],
      "pass": [
          {"label":2,"score":0.3,"hit":[]},
          {"label":5,"score":0.6,"hit":[]}
      ]
  },
  "log_id": 5284009342430354247
}
����踴��ķ���ʾ��

 {
   "result": {
       "spam": 2,
       "reject": [],
       "review": [
           {"label":1,"score":0.6,"hit":[]},  
           {"label":3,"score":0.4,"hit":["��װ��"]}, 
           {"label":4,"score":0.5,"hit":[]}     
           {"label":5,"score":0.6,"hit":[]}
       ],
       "pass": [
           {"label":2,"score":0.3,"hit":[]},  
       ]
   },
   "log_id": 5284009342430354247
 }
�쳣����ʾ��

{
  "error_code": 282000,
  "error_msg": "internal error",
  "log_id": 5284009342430354247
}