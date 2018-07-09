
DROP TABLE IF EXISTS `pictures`;
DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `items`;


CREATE TABLE `items` (
  `item_id` varchar(30)  NOT NULL COMMENT '商品id',  
  `item_time` datetime DEFAULT NULL COMMENT '爬取时间',
  `item_url` varchar(1024) NOT NULL COMMENT '商品url',
  `item_name` varchar(100) NOT NULL COMMENT '商品名称',
  `item_shop` varchar(100) DEFAULT NULL COMMENT '店铺名',
  `item_price` DOUBLE DEFAULT NULL COMMENT '商品价格',
  `item_sale_num` int DEFAULT NULL COMMENT '商品销量',
  `item_comment_num` int DEFAULT NULL COMMENT '商品评论数',
  `item_stock` int  DEFAULT NULL COMMENT '商品库存',
  `item_detail` text DEFAULT NULL COMMENT '商品描述',
  `item_area` varchar(300)  NOT NULL COMMENT '商品所在地区',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
  `cm_id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `item_id` varchar(30)  NOT NULL COMMENT '评论商品id',
  `cm_author` varchar(30) NOT NULL COMMENT '评论者',
  `cm_time` datetime DEFAULT NULL COMMENT '评论时间',
  `cm_detail` varchar(4096) DEFAULT NULL COMMENT '评论内容',
  `cm_type` tinyint DEFAULT NULL COMMENT '评论级别:1好评,2中评,3差评',
  `cm_ai_type` tinyint DEFAULT NULL COMMENT '评论级别:0 正常,1	暴恐违禁,2	文本色情,3	政治敏感,4	恶意推广,5	低俗辱骂',
  PRIMARY KEY (`cm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table comments add foreign key (item_id) references items (item_id);

CREATE TABLE `pictures` (
  `pic_id` int NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `item_id` varchar(30)  NOT NULL COMMENT '商品id',
  `pic_url` varchar(1024) NOT NULL COMMENT '图片下载地址',
  `pic_dir` varchar(1024) DEFAULT NULL COMMENT '图片本地目录',
  `pic_ai_type` tinyint DEFAULT NULL COMMENT '图片级别:0 正常,1	暴恐违禁,2	文本色情,3	政治敏感,4	恶意推广,5	低俗辱骂',
  PRIMARY KEY (`pic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table pictures add foreign key (item_id) references items (item_id);



