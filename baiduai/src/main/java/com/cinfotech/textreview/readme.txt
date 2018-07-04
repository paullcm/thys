
https://console.bce.baidu.com/ai/#/ai/antiporn/overview/index

http://ai.baidu.com/docs#/TextCensoring-API/top

文本审核v2接口（正式版）
接口描述
运用业界领先的深度学习技术，判断一段文本内容是否符合网络发文规范，实现自动化、智能化的文本审核。审核内容目前分为5大方向：色情文本、政治敏感、恶意推广、网络暴恐、低俗辱骂。v2正式版支持审核文本的黑白名单配置，且可通过调整阈值控制审核的松紧度标准，大幅度满足个性化文本内容审核的需求，详细配置说明详见AI官网社区说明帖。

请求说明
请求示例

HTTP方法: POST
请求URL: https://aip.baidubce.com/rest/2.0/antispam/v2/spam
URL参数：
参数	值
access_token	通过API Key和Secret Key获取的access_token,参考“Access Token获取”
Header如下：
参数	值
Content-Type	application/x-www-form-urlencoded
body请求示例: content=苟利国家生死以
请求参数

参数名称	类型	是否必须	详细说明
content	string	是	待审核文本，UTF-8，不可为空，不超过20000字节
返回说明
返回参数

参数名称	类型	详细说明
logid	int	正确调用生成的唯一标识码，用于问题定位
result	object	包含审核结果详情
+spam	int	请求中是否包含违禁，0表示非违禁，1表示违禁，2表示建议人工复审
+reject	array	审核未通过的类别列表与详情
+review	array	待人工复审的类别列表与详情
+pass	array	审核通过的类别列表与详情
++label	int	请求中的违禁类型
++score	float	违禁检测分，范围0~1，数值从低到高代表风险程度的高低
++hit	array	违禁类型对应命中的违禁词集合，可能为空
违禁labels类型说明

取值	详细说明
1	暴恐违禁
2	文本色情
3	政治敏感
4	恶意推广
5	低俗辱骂
返回示例
审核通过的返回示例

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
审核未通过的返回示例

{
  "result": {
      "spam": 1,
      "reject": [
          {"label":1,"score":0.87,"hit":["傻X"]},
          {"label":3,"score":0.92,"hit":["双筒猎枪"]},   
      ],
      "review": [{"label":4,"score":0.5,"hit":[]}],
      "pass": [
          {"label":2,"score":0.3,"hit":[]},
          {"label":5,"score":0.6,"hit":[]}
      ]
  },
  "log_id": 5284009342430354247
}
审核需复查的返回示例

 {
   "result": {
       "spam": 2,
       "reject": [],
       "review": [
           {"label":1,"score":0.6,"hit":[]},  
           {"label":3,"score":0.4,"hit":["起爆装置"]}, 
           {"label":4,"score":0.5,"hit":[]}     
           {"label":5,"score":0.6,"hit":[]}
       ],
       "pass": [
           {"label":2,"score":0.3,"hit":[]},  
       ]
   },
   "log_id": 5284009342430354247
 }
异常返回示例

{
  "error_code": 282000,
  "error_msg": "internal error",
  "log_id": 5284009342430354247
}