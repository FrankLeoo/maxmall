# ************************************************************
# Sequel Pro SQL dump
# Version 5224
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: cd-cdb-3l5oajvw.sql.tencentcdb.com (MySQL 5.7.18-txsql_57_0918-log)
# Database: db_maxmall
# Generation Time: 2019-08-20 02:01:19 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table mcs_distributor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_distributor`;

CREATE TABLE `mcs_distributor` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `distributor_sn` varchar(64) DEFAULT NULL COMMENT '分销商编号（推广编号）',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `merchant_sn` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级分销商',
  `level_id` bigint(20) DEFAULT NULL COMMENT '分销等级id',
  `level` int(3) DEFAULT NULL COMMENT '分销等级',
  `level_name` varchar(64) DEFAULT NULL COMMENT '分销等级名称',
  `self_sales_ratio` double(10,2) DEFAULT NULL COMMENT '本级销售分销比率',
  `parent_sales_ratio` double(10,2) DEFAULT NULL COMMENT '父级销售分销比率',
  `grand_sales_ratio` double(10,2) DEFAULT NULL COMMENT '祖父级销售分销比率',
  `fans_num` int(11) DEFAULT NULL COMMENT '会员数量',
  `distributor_num` int(11) DEFAULT NULL COMMENT '推广分销商数量',
  `manager_user_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `manager_user_name` varchar(32) DEFAULT NULL COMMENT '管理员名称',
  `manager_user_pic` varchar(255) DEFAULT NULL COMMENT '管理员头像',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `total_commission` double(16,2) DEFAULT NULL COMMENT '总佣金',
  `extracted_commission` double(16,2) DEFAULT NULL COMMENT '可提取佣金',
  `unextracted_commissio` double(16,2) DEFAULT NULL COMMENT '已提取佣金',
  `controbute_commission` double(16,2) DEFAULT NULL COMMENT '分销佣金',
  `profit_commission` double(16,2) DEFAULT NULL COMMENT '利润返点 按月结算',
  `agent_commission` double(16,2) DEFAULT NULL COMMENT '代理分销佣金',
  `split_pay_type` int(3) DEFAULT NULL COMMENT '分账类型 0个人|1商户',
  `split_pay_account` varchar(64) DEFAULT NULL COMMENT '分账账号',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销商表';

LOCK TABLES `mcs_distributor` WRITE;
/*!40000 ALTER TABLE `mcs_distributor` DISABLE KEYS */;

INSERT INTO `mcs_distributor` (`id`, `distributor_sn`, `merchant_id`, `merchant_sn`, `shop_id`, `parent_id`, `level_id`, `level`, `level_name`, `self_sales_ratio`, `parent_sales_ratio`, `grand_sales_ratio`, `fans_num`, `distributor_num`, `manager_user_id`, `manager_user_name`, `manager_user_pic`, `status`, `total_commission`, `extracted_commission`, `unextracted_commissio`, `controbute_commission`, `profit_commission`, `agent_commission`, `split_pay_type`, `split_pay_account`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,'100000001',10000,'10000',1,NULL,1,1,'普通分销员',10.00,1.00,1.00,10,0,1,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,1200.00,1000.00,500.00,1000.00,NULL,1001.00,1,'13699063675',NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mcs_distributor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_distributor_audit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_distributor_audit`;

CREATE TABLE `mcs_distributor_audit` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `distributor_sn` varchar(64) DEFAULT NULL COMMENT '分销商编号（推广编号）',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `merchant_sn` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级分销商',
  `level_id` bigint(20) DEFAULT NULL COMMENT '分销商等级',
  `level` int(3) DEFAULT NULL COMMENT '分销等级',
  `level_name` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `is_dues` int(3) DEFAULT NULL COMMENT '是否需要会费 0:否 1:是',
  `dues_point` double(10,2) DEFAULT NULL COMMENT '需要会费',
  `is_payed` int(3) DEFAULT NULL COMMENT '是否支付',
  `inner_pay_sn` varchar(64) DEFAULT NULL COMMENT '内部支付单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `outer_pay_sn` varchar(128) DEFAULT NULL COMMENT '外部支付单号',
  `self_sales_ratio` double(10,2) DEFAULT NULL COMMENT '本级销售分销比率',
  `parent_sales_ratio` double(10,2) DEFAULT NULL COMMENT '父级销售分销比率',
  `grand_sales_ratio` double(10,2) DEFAULT NULL COMMENT '祖父级销售分销比率',
  `manager_user_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `manager_user_name` varchar(32) DEFAULT NULL COMMENT '管理员名称',
  `manager_user_pic` varchar(255) DEFAULT NULL COMMENT '管理员头像',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `split_pay_type` int(3) DEFAULT NULL COMMENT '分账类型 个人|商户',
  `split_pay_account` varchar(64) DEFAULT NULL COMMENT '分账账号',
  `reason` varchar(255) DEFAULT NULL COMMENT '审核原因',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_note` varchar(255) DEFAULT NULL COMMENT '处理备注',
  `handle_man_id` bigint(20) DEFAULT NULL COMMENT '处理人员id',
  `handle_man_name` varchar(32) DEFAULT NULL COMMENT '处理人员名称',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销商表申请表';

LOCK TABLES `mcs_distributor_audit` WRITE;
/*!40000 ALTER TABLE `mcs_distributor_audit` DISABLE KEYS */;

INSERT INTO `mcs_distributor_audit` (`id`, `distributor_sn`, `merchant_id`, `merchant_sn`, `shop_id`, `parent_id`, `level_id`, `level`, `level_name`, `is_dues`, `dues_point`, `is_payed`, `inner_pay_sn`, `pay_time`, `outer_pay_sn`, `self_sales_ratio`, `parent_sales_ratio`, `grand_sales_ratio`, `manager_user_id`, `manager_user_name`, `manager_user_pic`, `status`, `split_pay_type`, `split_pay_account`, `reason`, `handle_time`, `handle_note`, `handle_man_id`, `handle_man_name`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,'10001',10000,'10001',1,NULL,1,1,'普通分销商',NULL,NULL,NULL,NULL,NULL,NULL,10.00,1.00,1.00,1,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mcs_distributor_audit` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_distributor_commission_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_distributor_commission_config`;

CREATE TABLE `mcs_distributor_commission_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `name` varchar(64) DEFAULT NULL COMMENT '返点名称',
  `min_sales` double(16,2) DEFAULT NULL COMMENT '最小销售额',
  `max_sales` double(16,2) DEFAULT NULL COMMENT '最大销售额',
  `sales_ratio` double(10,2) DEFAULT NULL COMMENT '分销额返点比率',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销商销售返点规则表';

LOCK TABLES `mcs_distributor_commission_config` WRITE;
/*!40000 ALTER TABLE `mcs_distributor_commission_config` DISABLE KEYS */;

INSERT INTO `mcs_distributor_commission_config` (`id`, `merchant_id`, `name`, `min_sales`, `max_sales`, `sales_ratio`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(2,10000,'普通返点',500.00,1000.00,3.00,'普通销售返点3%',0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mcs_distributor_commission_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_distributor_level_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_distributor_level_config`;

CREATE TABLE `mcs_distributor_level_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `name` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `self_sales_ratio` double(10,2) DEFAULT NULL COMMENT '本级销售分销比率',
  `parent_sales_ratio` double(10,2) DEFAULT NULL COMMENT '父级销售分销比率',
  `grand_sales_ratio` double(10,2) DEFAULT NULL COMMENT '祖父级销售分销比率',
  `is_dues` int(3) DEFAULT NULL COMMENT '是否需要会费 0:否 1:是',
  `dues_point` double(16,2) DEFAULT NULL COMMENT '需要会费',
  `self_dues_ratio` double(10,2) DEFAULT NULL COMMENT '邀请人会费分销比率',
  `parent_dues_ratio` double(10,2) DEFAULT NULL COMMENT '邀请人父级会费分销比率',
  `grand_dues_ratio` double(10,2) DEFAULT NULL COMMENT '邀请人祖父级会费分销比率',
  `description` varchar(500) DEFAULT NULL COMMENT '等级描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销商等级规则表';

LOCK TABLES `mcs_distributor_level_config` WRITE;
/*!40000 ALTER TABLE `mcs_distributor_level_config` DISABLE KEYS */;

INSERT INTO `mcs_distributor_level_config` (`id`, `merchant_id`, `name`, `self_sales_ratio`, `parent_sales_ratio`, `grand_sales_ratio`, `is_dues`, `dues_point`, `self_dues_ratio`, `parent_dues_ratio`, `grand_dues_ratio`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(2,10000,'普通分销商',10.00,1.00,1.00,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(3,10000,'金牌分销商',12.00,1.00,1.00,1,599.00,30.00,10.00,10.00,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,10000,'钻石分销商',13.00,1.00,1.00,1,NULL,20.00,10.00,10.00,'钻石分销商分成13%',1,1,'超级管理员','2019-06-25 02:16:14',1,'超级管理员','2019-06-25 05:19:02');

/*!40000 ALTER TABLE `mcs_distributor_level_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_distributor_money_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_distributor_money_history`;

CREATE TABLE `mcs_distributor_money_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商id',
  `type` int(3) DEFAULT NULL COMMENT '操作类型 0:分销 1:返点 2:提现',
  `source_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `history` varchar(500) DEFAULT NULL COMMENT '操作记录',
  `money` double(10,2) DEFAULT NULL COMMENT '操作金额',
  `handler_user_id` bigint(20) DEFAULT NULL COMMENT '操作员id',
  `handler_user_name` varchar(32) DEFAULT NULL COMMENT '操作员名称',
  `handler_user_pic` varchar(255) DEFAULT NULL COMMENT '操作员头像',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销商操作记录表';

LOCK TABLES `mcs_distributor_money_history` WRITE;
/*!40000 ALTER TABLE `mcs_distributor_money_history` DISABLE KEYS */;

INSERT INTO `mcs_distributor_money_history` (`id`, `merchant_id`, `distributor_id`, `type`, `source_id`, `history`, `money`, `handler_user_id`, `handler_user_name`, `handler_user_pic`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(2,10000,1,1,1000,'分销订单100001；分销金额10元',10.00,NULL,NULL,NULL,0,NULL,NULL,'2018-10-11 14:04:19',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mcs_distributor_money_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_member_level_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_member_level_config`;

CREATE TABLE `mcs_member_level_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `name` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `level` int(3) DEFAULT NULL COMMENT '等级',
  `growth_point` int(11) DEFAULT NULL COMMENT '需要积分',
  `growth_coupon_id` bigint(20) DEFAULT NULL COMMENT '等级赠送优惠券ID',
  `growth_coupon_name` varchar(64) DEFAULT NULL COMMENT '等级赠送优惠券名称',
  `description` varchar(500) DEFAULT NULL COMMENT '等级描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户会员等级规则表';

LOCK TABLES `mcs_member_level_config` WRITE;
/*!40000 ALTER TABLE `mcs_member_level_config` DISABLE KEYS */;

INSERT INTO `mcs_member_level_config` (`id`, `merchant_id`, `name`, `level`, `growth_point`, `growth_coupon_id`, `growth_coupon_name`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,'普通会员',1,500,3,NULL,'系统普通会员',0,NULL,NULL,'2018-10-11 14:04:19',1,'超级管理员','2019-07-03 23:40:16'),
	(2,10000,'金牌会员',2,1000,8,NULL,'金牌会员',0,1,'超级管理员','2019-06-28 06:36:30',1,'超级管理员','2019-08-05 02:08:05');

/*!40000 ALTER TABLE `mcs_member_level_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_merchant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_merchant`;

CREATE TABLE `mcs_merchant` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_sn` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `name` varchar(64) DEFAULT NULL COMMENT '商户名称\\r\\n商户名称',
  `contact_username` varchar(32) DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(32) DEFAULT NULL COMMENT '联系人电话',
  `pic` varchar(255) DEFAULT NULL COMMENT '商户主图片',
  `album_pics` text COMMENT '商户相册 逗号分割',
  `description` varchar(500) DEFAULT NULL COMMENT '商户描述信息',
  `receiver_province` varchar(64) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(64) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(64) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `manager_user_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `manager_user_name` varchar(32) DEFAULT NULL COMMENT '管理员名称',
  `manager_user_pic` varchar(255) DEFAULT NULL COMMENT '管理员头像',
  `status` int(3) DEFAULT NULL COMMENT '商户状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `union_merchant_sn` (`merchant_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户主表';

LOCK TABLES `mcs_merchant` WRITE;
/*!40000 ALTER TABLE `mcs_merchant` DISABLE KEYS */;

INSERT INTO `mcs_merchant` (`id`, `merchant_sn`, `name`, `contact_username`, `contact_phone`, `pic`, `album_pics`, `description`, `receiver_province`, `receiver_city`, `receiver_region`, `receiver_detail_address`, `manager_user_id`, `manager_user_name`, `manager_user_pic`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(10000,'10000','MaxMall测试商户','ivoter','13699063675',NULL,NULL,'测试商户','四川省','成都市','高新区','银泰城9层906室',1,'admin',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mcs_merchant` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_merchant_audit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_merchant_audit`;

CREATE TABLE `mcs_merchant_audit` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '商户名称',
  `contact_username` varchar(64) DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(32) DEFAULT NULL COMMENT '联系人电话',
  `pic` varchar(255) DEFAULT NULL COMMENT '商户主图片',
  `album_pics` varchar(500) DEFAULT NULL COMMENT '商户相册 逗号分割',
  `description` varchar(255) DEFAULT NULL COMMENT '商户描述信息',
  `receiver_province` varchar(64) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(64) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(64) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址',
  `manager_user_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `manager_user_name` varchar(32) DEFAULT NULL COMMENT '管理员名称',
  `manager_user_pic` varchar(255) DEFAULT NULL COMMENT '管理员头像',
  `status` int(3) DEFAULT NULL COMMENT '审核状态',
  `reason` varchar(255) DEFAULT NULL COMMENT '审核原因',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_note` varchar(255) DEFAULT NULL COMMENT '处理备注',
  `handle_man_id` bigint(20) DEFAULT NULL COMMENT '处理人员id',
  `handle_man_name` varchar(32) DEFAULT NULL COMMENT '处理人员名称',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table mcs_merchant_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_merchant_config`;

CREATE TABLE `mcs_merchant_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `merchant_sn` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `is_distributor` tinyint(1) DEFAULT NULL COMMENT '是否开启分销',
  `is_commission` tinyint(1) DEFAULT NULL COMMENT '是否销售额返点',
  `is_point` tinyint(1) DEFAULT NULL COMMENT '是否开启积分属性',
  `is_growth` tinyint(1) DEFAULT NULL COMMENT '是否开启会员成长',
  `pay_type` int(3) DEFAULT NULL COMMENT '支付类型 个人|商户',
  `pay_account` varchar(64) DEFAULT NULL COMMENT '支付账号',
  `point_charge` int(11) DEFAULT NULL COMMENT '积分兑换1元',
  `use_point_limit` int(11) DEFAULT NULL COMMENT '积分使用限制（单最高抵用百分比）',
  `growth_charge` int(11) DEFAULT NULL COMMENT '消费1元=多少成长值',
  `flash_order_overtime` int(11) DEFAULT NULL COMMENT '秒杀订单超时关闭时间(分) 默认10分钟',
  `normal_order_overtime` int(11) DEFAULT NULL COMMENT '正常订单超时时间(分) 默认15分钟',
  `confirm_overtime` int(11) DEFAULT NULL COMMENT '发货后自动确认收货时间（天） 默认3天',
  `finish_overtime` int(11) DEFAULT NULL COMMENT '自动完成交易时间，不能申请售后（天）默认30天',
  `comment_overtime` int(11) DEFAULT NULL COMMENT '订单完成后自动好评时间（天）默认7天',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户配置表';

LOCK TABLES `mcs_merchant_config` WRITE;
/*!40000 ALTER TABLE `mcs_merchant_config` DISABLE KEYS */;

INSERT INTO `mcs_merchant_config` (`id`, `merchant_id`, `merchant_sn`, `is_distributor`, `is_commission`, `is_point`, `is_growth`, `pay_type`, `pay_account`, `point_charge`, `use_point_limit`, `growth_charge`, `flash_order_overtime`, `normal_order_overtime`, `confirm_overtime`, `finish_overtime`, `comment_overtime`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,'10000',0,0,1,1,1,'10093831',100,500,100,7,7,30,3,7,0,NULL,NULL,NULL,1,'超级管理员','2019-08-10 10:00:17');

/*!40000 ALTER TABLE `mcs_merchant_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mcs_shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mcs_shop`;

CREATE TABLE `mcs_shop` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `merchant_sn` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `name` varchar(32) DEFAULT NULL COMMENT '店铺名称',
  `title` varchar(128) DEFAULT NULL COMMENT '店铺标题',
  `contact_username` varchar(32) DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(32) DEFAULT NULL COMMENT '联系人电话',
  `pic` varchar(255) DEFAULT NULL COMMENT '店铺主图片',
  `album_pics` text COMMENT '店铺相册 逗号分割',
  `description` varchar(500) DEFAULT NULL COMMENT '店铺描述信息',
  `receiver_province` varchar(64) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(64) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(64) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `latitude` double(16,2) DEFAULT NULL COMMENT '地址纬度',
  `longitude` double(16,2) DEFAULT NULL COMMENT '地址经度',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

LOCK TABLES `mcs_shop` WRITE;
/*!40000 ALTER TABLE `mcs_shop` DISABLE KEYS */;

INSERT INTO `mcs_shop` (`id`, `merchant_id`, `merchant_sn`, `name`, `title`, `contact_username`, `contact_phone`, `pic`, `album_pics`, `description`, `receiver_province`, `receiver_city`, `receiver_region`, `receiver_detail_address`, `latitude`, `longitude`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,'10000','Aimi的沐沐店铺','我的小店','Aimi','15688998787','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',NULL,NULL,'四川','成都','高新','高新大道189号',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mcs_shop` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table msc_member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `msc_member`;

CREATE TABLE `msc_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商ID',
  `inviter_id` bigint(20) DEFAULT NULL COMMENT '邀请人id',
  `inviter_name` varchar(64) DEFAULT NULL COMMENT '邀请人姓名',
  `inviter_icon` varchar(255) DEFAULT NULL COMMENT '邀请人头像',
  `nickname` varchar(64) DEFAULT NULL COMMENT '微信昵称',
  `icon` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `gender` int(3) DEFAULT NULL COMMENT '性别 0->未知 1->男 2->女',
  `birthday` varchar(32) DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `job` varchar(64) DEFAULT NULL COMMENT '职业',
  `source_type` int(3) DEFAULT NULL COMMENT '会员来源 0->微信',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  `member_level_id` bigint(20) DEFAULT NULL COMMENT '等级ID',
  `member_level_name` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `status` int(3) DEFAULT NULL COMMENT '状态 0->正常 1->禁用',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员信息表';

LOCK TABLES `msc_member` WRITE;
/*!40000 ALTER TABLE `msc_member` DISABLE KEYS */;

INSERT INTO `msc_member` (`id`, `merchant_id`, `distributor_id`, `inviter_id`, `inviter_name`, `inviter_icon`, `nickname`, `icon`, `gender`, `birthday`, `city`, `job`, `source_type`, `openid`, `integration`, `growth`, `member_level_id`, `member_level_name`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,NULL,NULL,NULL,NULL,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',1,'1987-03-08','成都',NULL,0,NULL,100,100,1,'普通会员',0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `msc_member` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table msc_member_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `msc_member_account`;

CREATE TABLE `msc_member_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '会员微信昵称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员微信头像',
  `consume_amount` decimal(16,2) DEFAULT NULL COMMENT '累计消费金额',
  `order_count` int(11) DEFAULT NULL COMMENT '订单数量',
  `coupon_count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `comment_count` int(11) DEFAULT NULL COMMENT '评价数',
  `return_order_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员统计账目表';

LOCK TABLES `msc_member_account` WRITE;
/*!40000 ALTER TABLE `msc_member_account` DISABLE KEYS */;

INSERT INTO `msc_member_account` (`id`, `merchant_id`, `distributor_id`, `member_id`, `member_nickname`, `member_icon`, `consume_amount`, `order_count`, `coupon_count`, `comment_count`, `return_order_count`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,NULL,1,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',200.00,13,2,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `msc_member_account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table msc_member_address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `msc_member_address`;

CREATE TABLE `msc_member_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '会员微信昵称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员微信头像',
  `receiver_name` varchar(32) DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) DEFAULT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(16) DEFAULT NULL COMMENT '优惠券数量',
  `receiver_province` varchar(64) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_city` varchar(64) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_region` varchar(64) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员收货地址表';



# Dump of table msc_member_growth_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `msc_member_growth_history`;

CREATE TABLE `msc_member_growth_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '会员微信昵称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员微信头像',
  `change_type` int(3) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_note` varchar(500) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(3) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  `operate_man_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `operate_man_name` varchar(64) DEFAULT NULL COMMENT '操作人名称',
  `operate_man_icon` varchar(255) DEFAULT NULL COMMENT '操作人',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员成长值记录表';

LOCK TABLES `msc_member_growth_history` WRITE;
/*!40000 ALTER TABLE `msc_member_growth_history` DISABLE KEYS */;

INSERT INTO `msc_member_growth_history` (`id`, `merchant_id`, `distributor_id`, `member_id`, `member_nickname`, `member_icon`, `change_type`, `change_count`, `operate_note`, `source_type`, `operate_man_id`, `operate_man_name`, `operate_man_icon`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,NULL,1,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,100,NULL,0,1,NULL,NULL,0,NULL,NULL,'2018-10-11 14:04:19',NULL,NULL,NULL);

/*!40000 ALTER TABLE `msc_member_growth_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table msc_member_integration_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `msc_member_integration_history`;

CREATE TABLE `msc_member_integration_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商ID',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '会员微信昵称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员微信头像',
  `change_type` int(3) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_note` varchar(500) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(3) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  `operate_man_id` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  `operate_man_name` varchar(64) DEFAULT NULL COMMENT '操作人名称',
  `operate_man_icon` varchar(255) DEFAULT NULL COMMENT '操作人',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员积分记录表';

LOCK TABLES `msc_member_integration_history` WRITE;
/*!40000 ALTER TABLE `msc_member_integration_history` DISABLE KEYS */;

INSERT INTO `msc_member_integration_history` (`id`, `merchant_id`, `distributor_id`, `member_id`, `member_nickname`, `member_icon`, `change_type`, `change_count`, `operate_note`, `source_type`, `operate_man_id`, `operate_man_name`, `operate_man_icon`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,NULL,1,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,100,NULL,0,1,NULL,NULL,0,NULL,NULL,'2018-10-11 14:04:19',NULL,NULL,NULL),
	(2,10000,NULL,1,'Aimi','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,100,NULL,0,1,NULL,NULL,0,NULL,NULL,'2018-10-11 14:04:19',NULL,NULL,NULL);

/*!40000 ALTER TABLE `msc_member_integration_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table oms_company_address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oms_company_address`;

CREATE TABLE `oms_company_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `address_name` varchar(200) DEFAULT NULL COMMENT '地址名称',
  `is_default` int(1) DEFAULT NULL COMMENT '是否默认地址',
  `type` int(3) DEFAULT NULL COMMENT '类型 1:收货 2:发货',
  `name` varchar(64) DEFAULT NULL COMMENT '收发货人姓名',
  `phone` varchar(64) DEFAULT NULL COMMENT '收货人电话',
  `province` varchar(64) DEFAULT NULL COMMENT '省/直辖市',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `region` varchar(64) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司收发货地址表';



# Dump of table oms_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oms_order`;

CREATE TABLE `oms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商id',
  `distributor_name` varchar(64) DEFAULT NULL COMMENT '分销商名称',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '门店id',
  `shop_name` varchar(64) DEFAULT NULL COMMENT '门店名称',
  `distributor_pic` varchar(255) DEFAULT NULL COMMENT '分销商头像',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `member_name` varchar(64) DEFAULT NULL COMMENT '用户帐号',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
  `use_integration` int(11) DEFAULT NULL COMMENT '下单时使用的积分',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵扣金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券抵扣金额',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `coupon_name` varchar(64) DEFAULT NULL COMMENT '优惠券名称',
  `coupon_type` int(3) DEFAULT NULL COMMENT '优惠券类型',
  `promotion_id` bigint(20) DEFAULT NULL COMMENT '活动id',
  `promotion_name` varchar(64) DEFAULT NULL COMMENT '活动名称',
  `promotion_type` int(3) DEFAULT NULL COMMENT '活动类型',
  `receiver_name` varchar(100) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `receiver_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '收货人电话',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_province` varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(32) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(32) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `express_type` int(3) DEFAULT NULL COMMENT '物流类型 0:手动发货; 1:系统自动发货',
  `express_company` varchar(64) DEFAULT NULL COMMENT '物流公司(配送方式)',
  `express_sn` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `express_time` datetime DEFAULT NULL COMMENT '发货时间',
  `bill_type` int(1) DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
  `bill_header` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `bill_content` varchar(200) DEFAULT NULL COMMENT '发票内容',
  `bill_receiver_email` varchar(64) DEFAULT NULL COMMENT '收票人邮箱',
  `bill_receiver_phone` varchar(32) DEFAULT NULL COMMENT '收票人电话',
  `order_type` int(1) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
  `source_type` int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  `is_fenxiao_order` int(1) DEFAULT NULL COMMENT '是否分销订单',
  `self_sales_ratio` decimal(6,2) DEFAULT NULL COMMENT '本级销售分销比率',
  `parent_sales_ratio` decimal(6,2) DEFAULT NULL COMMENT '父级销售分销比率',
  `grand_sales_ratio` decimal(6,2) DEFAULT NULL COMMENT '祖父级销售分销比率',
  `is_payed` int(1) DEFAULT NULL COMMENT '是否支付',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `inner_transaction_no` varchar(128) DEFAULT NULL COMMENT '内部支付流水号',
  `outer_transaction_no` varchar(128) DEFAULT NULL COMMENT '外部支付流水号',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `phase_payments` varchar(500) DEFAULT NULL COMMENT '支付信息',
  `is_settle` int(1) DEFAULT NULL COMMENT '是否结算',
  `settle_time` datetime DEFAULT NULL COMMENT '结算时间',
  `is_receive` int(1) DEFAULT NULL COMMENT '是否收货状态：0->未确认；1->已确认',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `is_comment` int(1) DEFAULT NULL COMMENT '是否评价',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `order_status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `status` int(1) NOT NULL DEFAULT '0',
  `integration` int(11) DEFAULT NULL COMMENT '可以获得的积分',
  `growth` int(11) DEFAULT NULL COMMENT '可以活动的成长值',
  `auto_confirm_day` int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `last_operator` varchar(32) DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

LOCK TABLES `oms_order` WRITE;
/*!40000 ALTER TABLE `oms_order` DISABLE KEYS */;

INSERT INTO `oms_order` (`id`, `order_sn`, `merchant_id`, `distributor_id`, `distributor_name`, `shop_id`, `shop_name`, `distributor_pic`, `member_id`, `member_name`, `member_icon`, `total_amount`, `pay_amount`, `freight_amount`, `promotion_amount`, `use_integration`, `integration_amount`, `coupon_amount`, `coupon_id`, `coupon_name`, `coupon_type`, `promotion_id`, `promotion_name`, `promotion_type`, `receiver_name`, `receiver_phone`, `receiver_post_code`, `receiver_province`, `receiver_city`, `receiver_region`, `receiver_detail_address`, `note`, `express_type`, `express_company`, `express_sn`, `express_time`, `bill_type`, `bill_header`, `bill_content`, `bill_receiver_email`, `bill_receiver_phone`, `order_type`, `source_type`, `is_fenxiao_order`, `self_sales_ratio`, `parent_sales_ratio`, `grand_sales_ratio`, `is_payed`, `pay_type`, `inner_transaction_no`, `outer_transaction_no`, `payment_time`, `phase_payments`, `is_settle`, `settle_time`, `is_receive`, `receive_time`, `is_comment`, `comment_time`, `order_status`, `status`, `integration`, `growth`, `auto_confirm_day`, `creator_id`, `creator`, `create_time`, `last_operator`, `last_operator_id`, `update_time`)
VALUES
	(12,'201809150101000001',10000,1,'成都分销代理',NULL,'淘小铺',NULL,1,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',18732.00,16377.75,20.00,2344.25,0,10.00,10.00,2,'满100减5元',1,NULL,NULL,NULL,'大梨','18033441849','518000','江苏省','常州市','天宁区','东晓街道','xxx',1,'','',NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,10.00,NULL,NULL,1,1,NULL,NULL,'2018-10-11 14:04:19',NULL,0,NULL,NULL,NULL,NULL,NULL,0,0,13284,13284,15,NULL,NULL,'2018-09-15 12:24:27',NULL,NULL,'2018-10-30 14:43:49'),
	(13,'201809150102000002',10000,NULL,NULL,NULL,NULL,NULL,2,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',18732.00,16377.75,0.00,2344.25,1000,0.00,10.00,NULL,NULL,NULL,14,'双十一满500减100活动',0,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,1,NULL,NULL,'2018-10-11 14:04:19',NULL,0,NULL,NULL,NULL,NULL,NULL,1,0,13284,13284,15,NULL,NULL,'2018-09-15 14:24:29',NULL,NULL,NULL),
	(14,'201809130101000001',10000,NULL,NULL,NULL,NULL,NULL,2,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',18732.00,16377.75,0.00,2344.25,NULL,10.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,NULL,'顺丰快递','201707196398345','2018-10-16 13:43:41',NULL,NULL,NULL,NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,2,NULL,NULL,'2018-10-13 13:44:04',NULL,0,NULL,NULL,NULL,NULL,NULL,2,0,13284,13284,15,NULL,NULL,'2018-09-13 16:57:40',NULL,NULL,NULL);

/*!40000 ALTER TABLE `oms_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table oms_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oms_order_item`;

CREATE TABLE `oms_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_pic` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `product_brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `product_brand_name` varchar(64) DEFAULT NULL COMMENT '品牌名称',
  `product_sn` varchar(64) DEFAULT NULL COMMENT '商品编码',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '销售价格',
  `product_quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品sku编号',
  `product_sku_code` varchar(50) DEFAULT NULL COMMENT '商品sku条码',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `product_category_name` varchar(64) DEFAULT NULL COMMENT '商品分类名称',
  `sale_attribute` text COMMENT '商品的销售属性',
  `promotion_id` bigint(20) DEFAULT NULL COMMENT '活动id',
  `promotion_name` varchar(64) DEFAULT NULL COMMENT '商品促销名称',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '商品促销分解金额',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `coupon_name` varchar(64) DEFAULT NULL COMMENT '优惠券名称',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券优惠分解金额',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分优惠分解金额',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '该商品经过优惠后的分解金额',
  `gift_integration` int(11) DEFAULT '0' COMMENT '赠送积分',
  `gift_growth` int(11) DEFAULT '0' COMMENT '赠送成长值',
  `is_present` int(1) DEFAULT NULL COMMENT '是否赠品',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单中所包含的商品';

LOCK TABLES `oms_order_item` WRITE;
/*!40000 ALTER TABLE `oms_order_item` DISABLE KEYS */;

INSERT INTO `oms_order_item` (`id`, `order_id`, `order_sn`, `merchant_id`, `product_id`, `product_pic`, `product_name`, `product_brand_id`, `product_brand_name`, `product_sn`, `product_price`, `product_quantity`, `product_sku_id`, `product_sku_code`, `product_category_id`, `product_category_name`, `sale_attribute`, `promotion_id`, `promotion_name`, `promotion_amount`, `coupon_id`, `coupon_name`, `coupon_amount`, `integration_amount`, `real_amount`, `gift_integration`, `gift_growth`, `is_present`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(21,12,'201809150101000001',10000,26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20',1,'华为','6946605',3788.00,1,90,'201806070026001',19,NULL,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]',1,'单品促销',200.00,NULL,NULL,2.02,0.00,3585.98,3788,3788,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(22,12,'201809150101000001',10000,27,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8',2,'小米','7437788',2699.00,3,98,'201808270027001',19,NULL,'[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]',2,'打折优惠：满3件，打7.50折',674.75,NULL,NULL,1.44,0.00,2022.81,2699,2699,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(23,13,'201809150101000001',10000,28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A',2,'小米','7437789',649.00,1,102,'201808270028001',19,NULL,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]',3,'满减优惠：满1000.00元，减120.00元',57.60,NULL,NULL,0.35,0.00,591.05,649,649,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(24,14,'201809150101000001',10000,28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A',2,'小米','7437789',699.00,1,103,'201808270028001',19,NULL,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]',3,'满减优惠：满1000.00元，减120.00元',62.40,NULL,NULL,0.37,0.00,636.23,649,649,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(25,14,'201809150101000001',10000,29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus',3,'苹果','7437799',5499.00,1,106,'201808270029001',19,NULL,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]',NULL,'无优惠',0.00,NULL,NULL,2.94,0.00,5496.06,5499,5499,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `oms_order_item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table oms_order_operate_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oms_order_operate_history`;

CREATE TABLE `oms_order_operate_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商ID',
  `old_order_status` int(3) DEFAULT NULL COMMENT '原订单状态',
  `new_order_status` int(3) DEFAULT NULL COMMENT '新订单状态',
  `description` varchar(500) DEFAULT NULL COMMENT '流转描述信息',
  `handle_man_id` bigint(20) DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
  `handle_man_name` int(32) DEFAULT NULL COMMENT '处理人员名称',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `last_operator` varchar(32) DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单操作历史记录';



# Dump of table oms_order_return_apply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oms_order_return_apply`;

CREATE TABLE `oms_order_return_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '供应商',
  `distributor_id` bigint(20) DEFAULT NULL COMMENT '分销商id',
  `distributor_name` varchar(64) DEFAULT NULL COMMENT '分销商名称',
  `order_item_id` bigint(20) DEFAULT NULL COMMENT '订单itemid',
  `product_id` bigint(20) DEFAULT NULL COMMENT '退货商品id',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `product_pic` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `product_brand_id` bigint(20) DEFAULT NULL COMMENT '商品品牌id',
  `product_brand_name` varchar(64) DEFAULT NULL COMMENT '商品品牌',
  `sale_attribute` varchar(500) DEFAULT NULL COMMENT '商品销售属性：颜色：红色；尺码：xl;',
  `return_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `product_real_price` decimal(10,2) DEFAULT NULL COMMENT '商品实际支付单价',
  `product_quantity` int(11) DEFAULT NULL COMMENT '销售数量',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '销售金额',
  `return_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员',
  `member_name` varchar(64) DEFAULT NULL COMMENT '会员用户名',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `return_name` varchar(100) DEFAULT NULL COMMENT '退货人姓名',
  `return_phone` varchar(100) DEFAULT NULL COMMENT '退货人电话',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `proof_pics` varchar(1000) DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `apply_status` int(1) DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
  `status` int(1) DEFAULT NULL COMMENT '状态0:正常',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_note` varchar(500) DEFAULT NULL COMMENT '处理备注',
  `handle_man_id` bigint(20) DEFAULT NULL COMMENT '处理人员id',
  `handle_man_name` varchar(32) DEFAULT NULL COMMENT '处理人员',
  `receive_type` int(3) DEFAULT NULL COMMENT '收货方式：0->无需收货（直接退货）；1->客户回递（等待确认收货）',
  `express_company` varchar(64) DEFAULT NULL COMMENT '物流公司(配送方式)',
  `express_sn` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `express_time` varchar(64) DEFAULT NULL COMMENT '回递时间',
  `receive_man` varchar(32) DEFAULT NULL COMMENT '收货人',
  `receive_phone` varchar(32) DEFAULT NULL COMMENT '收货人电话',
  `receive_address` varchar(500) DEFAULT NULL COMMENT '收货地址',
  `is_received` int(3) DEFAULT NULL COMMENT '是否收货',
  `received_time` datetime DEFAULT NULL COMMENT '收货时间',
  `received_note` varchar(500) DEFAULT NULL COMMENT '收货备注',
  `received_man_id` bigint(20) DEFAULT NULL COMMENT '确认收货人员id',
  `received_man_name` varchar(32) DEFAULT NULL COMMENT '确认收货人员名称',
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  `creator` varchar(32) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单退货申请';

LOCK TABLES `oms_order_return_apply` WRITE;
/*!40000 ALTER TABLE `oms_order_return_apply` DISABLE KEYS */;

INSERT INTO `oms_order_return_apply` (`id`, `order_id`, `order_sn`, `merchant_id`, `distributor_id`, `distributor_name`, `order_item_id`, `product_id`, `product_name`, `product_pic`, `product_brand_id`, `product_brand_name`, `sale_attribute`, `return_count`, `product_price`, `product_real_price`, `product_quantity`, `real_amount`, `return_amount`, `member_id`, `member_name`, `member_icon`, `return_name`, `return_phone`, `reason`, `proof_pics`, `note`, `apply_status`, `status`, `handle_time`, `handle_note`, `handle_man_id`, `handle_man_name`, `receive_type`, `express_company`, `express_sn`, `express_time`, `receive_man`, `receive_phone`, `receive_address`, `is_received`, `received_time`, `received_note`, `received_man_id`, `received_man_name`, `creator_id`, `create_time`, `creator`, `last_operator`, `last_operator_id`, `update_time`)
VALUES
	(4,12,'201809150101000001',10000,NULL,NULL,21,27,'小米8','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg',NULL,'小米','{\"颜色\":\"黑色\",\"内存\":\"32G\"}',1,2699.00,2022.81,2,3585.98,3585.98,NULL,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','大梨','18000000000','质量问题','','不够高端',2,0,'2019-06-13 03:22:50','确认退货',1,'admin',0,NULL,NULL,NULL,NULL,NULL,NULL,1,'2018-10-18 13:55:58','无需退货',1,'超级管理员',1,'2019-06-13 03:22:50','超级管理员','超级管理员',1,'2019-06-13 03:22:50'),
	(5,12,'201809150101000001',10000,NULL,NULL,22,28,'红米5A','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg',NULL,'小米','{\"颜色\":\"白色\",\"内存\":\"16G\"}',1,649.00,591.05,1,3585.98,3585.98,NULL,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','大梨','18000000000','质量问题','','颜色太土',1,0,'2019-06-13 03:24:20','确认退货',1,'admin',1,NULL,NULL,NULL,'小王','13677878909','四川成都高新区银泰城189号',1,'2018-10-18 13:55:58','无需退货',1,'超级管理员',1,'2019-06-13 03:24:20','超级管理员','超级管理员',1,'2019-06-13 03:24:20'),
	(8,13,'201809150102000002',10000,NULL,NULL,24,28,'红米5A','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg',NULL,'小米','{\"颜色\":\"金色\",\"内存\":\"32G\"}',1,649.00,591.05,3,3585.98,3585.98,NULL,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','大梨','18000000000','质量问题','','颜色太土',3,0,'2019-06-13 03:25:00','拒绝退货',1,'admin',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-06-13 03:25:00','超级管理员','超级管理员',1,'2019-06-13 03:25:00'),
	(9,14,'201809130101000001',10000,NULL,NULL,25,26,'华为 HUAWEI P20','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg',NULL,'华为','{\"颜色\":\"黑色\",\"内存\":\"32G\"}',1,3788.00,3585.98,1,3585.98,3500.00,NULL,'test','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','大梨','18000000000','质量问题','','老是卡',0,0,'2018-10-24 15:44:56','呵呵',1,'admin',0,NULL,NULL,NULL,'admin',NULL,NULL,NULL,'2018-10-24 15:46:35','收货了',NULL,NULL,NULL,'2018-10-17 14:34:57',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `oms_order_return_apply` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table oms_order_return_reason
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oms_order_return_reason`;

CREATE TABLE `oms_order_return_reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `name` varchar(100) DEFAULT NULL COMMENT '退货类型',
  `sort` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态：0->不启用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退货原因表';

LOCK TABLES `oms_order_return_reason` WRITE;
/*!40000 ALTER TABLE `oms_order_return_reason` DISABLE KEYS */;

INSERT INTO `oms_order_return_reason` (`id`, `merchant_id`, `name`, `sort`, `status`, `create_time`, `creator_id`, `creator`, `last_operator`, `last_operator_id`, `update_time`)
VALUES
	(16,10000,'质量问题',10,0,'2018-10-11 14:04:19',NULL,NULL,'超级管理员',1,'2019-07-03 23:30:42'),
	(17,10000,'测试4444',100,1,'2019-06-13 06:07:22',1,'超级管理员','超级管理员',1,'2019-06-13 06:07:22'),
	(18,10000,'fff',100,1,'2019-06-13 06:08:53',1,'超级管理员','超级管理员',1,'2019-06-13 06:08:53'),
	(19,10000,'七天无理由退款',2,0,'2019-06-13 06:10:37',1,'超级管理员','超级管理员',1,'2019-06-13 06:10:37'),
	(20,10000,'发发发',100,1,'2019-06-13 06:22:57',1,'超级管理员','超级管理员',1,'2019-06-13 06:22:57');

/*!40000 ALTER TABLE `oms_order_return_reason` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pms_brand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pms_brand`;

CREATE TABLE `pms_brand` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `name` varchar(64) DEFAULT NULL COMMENT '品牌名称',
  `en_name` varchar(64) DEFAULT NULL COMMENT '品牌英文名称',
  `first_letter` varchar(16) DEFAULT NULL COMMENT '首字母',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `logo` varchar(255) DEFAULT NULL COMMENT '品牌logo',
  `description` varchar(500) DEFAULT NULL COMMENT '品牌故事',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品品牌表';

LOCK TABLES `pms_brand` WRITE;
/*!40000 ALTER TABLE `pms_brand` DISABLE KEYS */;

INSERT INTO `pms_brand` (`id`, `merchant_id`, `name`, `en_name`, `first_letter`, `sort`, `logo`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,'万和','wanke','W',1,'https://maxmall-1257020268.cos.ap-chengdu.myqcloud.com/46f1d0c2d4a54badb1520134b52de3fa.png','Victoria\'s Secret的故事',0,NULL,NULL,'2018-10-16 13:43:41',1,'超级管理员','2019-07-03 23:25:48'),
	(2,10000,'三星','smang','S',100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (1).jpg','三星的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(3,10000,'华为','hawei','H',100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/17f2dd9756d9d333bee8e60ce8c03e4c_222_222.jpg','Victoria\'s Secret的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(4,10000,'格力','graaw','G',30,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/dc794e7e74121272bbe3ce9bc41ec8c3_222_222.jpg','Victoria\'s Secret的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(5,10000,'方太',NULL,'F',20,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (4).jpg','Victoria Secret的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(6,10000,'小米','mi','M',500,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/1e34aef2a409119018a4c6258e39ecfb_222_222.png','小米手机的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(21,10000,'OPPO','oppo','O',0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(6).jpg','string',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(49,10000,'七匹狼',NULL,'S',200,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/18d8bc3eb13533fab466d702a0d3fd1f40345bcd.jpg','BOOB的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(50,10000,'海澜之家',NULL,'H',200,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/99d3279f1029d32b929343b09d3c72de_222_222.jpg','海澜之家的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(51,10000,'苹果','apple','A',200,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg','苹果的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(58,10000,'NIKE','nike','N',0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/timg (51).jpg','NIKE的故事',0,NULL,NULL,'2018-10-16 13:43:41',NULL,NULL,NULL),
	(59,10000,'cc','dd','d',100,'https://maxmall-1257020268.cos.ap-chengdu.myqcloud.com/9a08ab34b3ff419dbc61ad8831624ec5.png','dd',0,1,'超级管理员','2019-06-30 22:15:01',1,'超级管理员','2019-06-30 22:15:51');

/*!40000 ALTER TABLE `pms_brand` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pms_category_attribute
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pms_category_attribute`;

CREATE TABLE `pms_category_attribute` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称 "颜色"',
  `attr_type` int(3) DEFAULT NULL COMMENT '属性类型0:普通 1:有单独pic',
  `attr_values` varchar(500) DEFAULT NULL COMMENT '属性项目"红色,褐色"',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类目销售属性表';

LOCK TABLES `pms_category_attribute` WRITE;
/*!40000 ALTER TABLE `pms_category_attribute` DISABLE KEYS */;

INSERT INTO `pms_category_attribute` (`id`, `merchant_id`, `category_id`, `name`, `attr_type`, `attr_values`, `sort`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(11,NULL,29,'颜色',0,'红色,',100,1,'超级管理员','2019-07-03 23:24:11',1,'超级管理员','2019-07-03 23:24:11'),
	(12,NULL,53,'颜色',1,'红色,黑色,',100,1,'超级管理员','2019-07-03 23:26:18',1,'超级管理员','2019-07-03 23:26:18'),
	(13,NULL,53,'型号',0,'X,XL,XXL,M,L,S,',100,1,'超级管理员','2019-07-03 23:26:18',1,'超级管理员','2019-07-03 23:26:18'),
	(14,NULL,53,'大小',0,'175',100,1,'超级管理员','2019-07-03 23:26:18',1,'超级管理员','2019-07-03 23:26:18'),
	(19,NULL,1,'颜色',0,'红色,绿色,',1,1,'超级管理员','2019-07-04 07:15:43',1,'超级管理员','2019-07-04 07:15:43'),
	(20,NULL,1,'型号',0,'X,XL,M,S,',100,1,'超级管理员','2019-07-04 07:15:43',1,'超级管理员','2019-07-04 07:15:43'),
	(21,NULL,1,'分类',0,'夏季,秋季,冬季,春季,',101,1,'超级管理员','2019-07-04 07:15:43',1,'超级管理员','2019-07-04 07:15:43'),
	(22,NULL,1,'性别',0,'男,女,',100,1,'超级管理员','2019-07-04 07:15:43',1,'超级管理员','2019-07-04 07:15:43'),
	(23,NULL,1,'配置',0,'高级,普通,',100,1,'超级管理员','2019-07-04 07:15:43',1,'超级管理员','2019-07-04 07:15:43'),
	(24,NULL,1,'大小',0,'小号,大号,',100,1,'超级管理员','2019-07-04 07:15:43',1,'超级管理员','2019-07-04 07:15:43');

/*!40000 ALTER TABLE `pms_category_attribute` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pms_product_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pms_product_category`;

CREATE TABLE `pms_product_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID',
  `parent_ids` varchar(500) DEFAULT NULL COMMENT '父类目IDS逗号分割',
  `name` varchar(64) DEFAULT NULL COMMENT '类目名称',
  `level` int(3) DEFAULT NULL COMMENT '层级',
  `is_leaf` int(3) DEFAULT NULL COMMENT '是否叶子节点',
  `show_status` int(3) DEFAULT NULL COMMENT '显示状态',
  `sort` int(6) DEFAULT NULL COMMENT '排序值',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `keywords` varchar(128) DEFAULT NULL COMMENT '关键字',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类';

LOCK TABLES `pms_product_category` WRITE;
/*!40000 ALTER TABLE `pms_product_category` DISABLE KEYS */;

INSERT INTO `pms_product_category` (`id`, `merchant_id`, `parent_id`, `parent_ids`, `name`, `level`, `is_leaf`, `show_status`, `sort`, `icon`, `keywords`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,0,NULL,'服装',0,NULL,1,1,NULL,'服装','服装分类',0,NULL,NULL,NULL,1,'超级管理员','2019-07-04 07:15:43'),
	(2,10000,0,NULL,'手机数码',0,NULL,1,1,NULL,'手机数码','手机数码',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(3,10000,0,NULL,'家用电器',0,NULL,1,1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jiadian.png','家用电器','家用电器',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,10000,0,NULL,'家具家装',0,NULL,1,1,NULL,'家具家装','家具家装',1,1,'超级管理员','2019-06-18 06:44:06',1,'超级管理员','2019-06-18 06:44:06'),
	(5,10000,0,NULL,'汽车用品',0,NULL,1,1,NULL,'汽车用品','汽车用品',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(7,10000,1,'1,','外套',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_waitao.png','外套','外套',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(8,10000,1,'1,','T恤',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_tshirt.png','T恤','T恤',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(9,10000,1,'1,','休闲裤',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xiuxianku.png','休闲裤','休闲裤',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(10,10000,1,'1,','牛仔裤',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_niuzaiku.png','牛仔裤','牛仔裤',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(11,10000,1,'1,','衬衫',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_chenshan.png','衬衫','衬衫分类',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(29,10000,1,'null1,','男鞋',1,NULL,1,1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xie.png','','',0,NULL,NULL,NULL,1,'超级管理员','2019-07-03 23:24:11'),
	(31,10000,2,'2,','摄影摄像',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_sheying.png','','',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(32,10000,2,'2,','影音娱乐',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png','','',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(33,10000,2,'2,','数码配件',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png','','',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(34,10000,2,'2,','智能设备',1,NULL,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_zhineng.png','','',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(42,10000,3,'3,','个护健康',1,NULL,1,0,'','','',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(52,10000,31,'2,31,','测试分类',2,NULL,1,100,'https://maxmall-1257020268.cos.ap-chengdu.myqcloud.com/e71d5ab1394b4960a5d683f423dacd6d.png','测试分类','存储',0,1,'超级管理员','2019-07-01 01:57:30',1,'超级管理员','2019-07-01 02:04:27');

/*!40000 ALTER TABLE `pms_product_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pms_product_sku
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pms_product_sku`;

CREATE TABLE `pms_product_sku` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `spu_id` bigint(20) DEFAULT NULL COMMENT 'spuid',
  `sku_code` varchar(64) DEFAULT NULL COMMENT 'spu编码',
  `price` decimal(16,2) DEFAULT NULL COMMENT '销售价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '预警库存',
  `pic` varchar(255) DEFAULT NULL COMMENT '商品主图',
  `album_pics` varchar(500) DEFAULT NULL COMMENT '商品图集',
  `sale_num` int(11) DEFAULT NULL COMMENT '销售价格',
  `lock_stock` int(11) DEFAULT NULL COMMENT '锁定库存',
  `sku_attrs` varchar(500) DEFAULT NULL COMMENT '销售属性',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品sku';



# Dump of table pms_product_spu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pms_product_spu`;

CREATE TABLE `pms_product_spu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(64) DEFAULT NULL COMMENT '品牌名称',
  `category_id` bigint(20) DEFAULT NULL COMMENT '类目id',
  `category_name` varchar(64) DEFAULT NULL COMMENT '类目名称',
  `product_attrs` varchar(500) DEFAULT NULL COMMENT '销售属性（来源于category和自定义）\r\n[{key:"颜色",value:"红色,褐色"},{key:容量,value:"32G,64G"},{key:内存,value:" 4G,8G"}]',
  `name` varchar(64) DEFAULT NULL COMMENT '商品名',
  `pic` varchar(255) DEFAULT NULL COMMENT '商品主图片',
  `spu_code` varchar(64) DEFAULT NULL COMMENT '商品编码',
  `hot_status` int(3) DEFAULT NULL COMMENT '是否热卖',
  `new_status` int(3) DEFAULT NULL COMMENT '是否新品',
  `recommand_status` int(3) DEFAULT NULL COMMENT '是否推荐',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `sale_num` int(11) DEFAULT NULL COMMENT '销量',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `price` decimal(16,2) DEFAULT NULL COMMENT '交易价格',
  `market_price` decimal(16,2) DEFAULT NULL COMMENT '市场价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '预警库存',
  `unit` varchar(64) DEFAULT NULL COMMENT '单位',
  `weight` varchar(64) DEFAULT NULL COMMENT '重量',
  `keywords` varchar(128) DEFAULT NULL COMMENT '关键字',
  `album_pics` varchar(500) DEFAULT NULL COMMENT '图片集合',
  `detail_desc` text COMMENT 'web端描述',
  `is_publish` int(3) DEFAULT NULL COMMENT '是否发布',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品spu表';

LOCK TABLES `pms_product_spu` WRITE;
/*!40000 ALTER TABLE `pms_product_spu` DISABLE KEYS */;

INSERT INTO `pms_product_spu` (`id`, `merchant_id`, `brand_id`, `brand_name`, `category_id`, `category_name`, `product_attrs`, `name`, `pic`, `spu_code`, `hot_status`, `new_status`, `recommand_status`, `sort`, `sale_num`, `sub_title`, `description`, `price`, `market_price`, `stock`, `low_stock`, `unit`, `weight`, `keywords`, `album_pics`, `detail_desc`, `is_publish`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,49,'七匹狼',7,'外套',NULL,'银色星芒刺绣网纱底裤','https://img10.360buyimg.com/n1/s350x449_jfs/t1/79809/2/1856/303086/5d02f97fE3809afde/b999b769591bf6fe.jpg!cc_350x449.jpg','No86577',0,0,0,100,0,'111','银色星芒刺绣网纱底裤',100.00,100.00,100,20,'件','1000.00','银色星芒刺绣网纱底裤',NULL,'银色星芒刺绣网纱底裤',1,0,1,'超级管理员','2019-06-14 22:39:04',1,'超级管理员','2019-06-14 22:39:04'),
	(7,10000,1,'万和',7,'外套',NULL,'女式超柔软拉毛运动开衫','https://img12.360buyimg.com/n1/s350x449_jfs/t1/52119/12/631/332367/5ce36819E6bc251d5/7488d5661b934c8b.jpg!cc_350x449.jpg','No86577',1,1,1,0,0,'匠心剪裁，垂感质地','string',249.00,249.00,100,0,'件','0.00','女式超柔软拉毛运动开衫','string','string',0,0,1,'超级管理员','2019-06-14 22:39:07',1,'超级管理员','2019-06-14 22:39:07'),
	(26,10000,3,'华为',19,'手机通讯',NULL,'华为 HUAWEI P20 ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','6946605',0,2,2,100,0,'AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待','',3788.00,249.00,1000,0,'件','0.00','','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg','',1,0,3,NULL,NULL,NULL,NULL,NULL),
	(27,10000,6,'小米',19,'手机通讯',NULL,'小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','7437788',0,3,3,0,0,'骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购','',2699.00,249.00,100,0,'','0.00','','','',1,0,3,NULL,NULL,NULL,NULL,NULL),
	(30,10000,50,'海澜之家',8,'T恤',NULL,'HLA海澜之家简约动物印花短袖T恤','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg','HNTBJ2E042A',0,4,4,0,0,'2018夏季新品微弹舒适新款短T男生 6月6日-6月20日，满300减30，参与互动赢百元礼券，立即分享赢大奖','',98.00,249.00,100,0,'','0.00','','','',1,0,1,NULL,NULL,NULL,NULL,NULL),
	(33,10000,6,'小米',35,'手机数码',NULL,'小米（MI）小米电视4A ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg','4609652',0,0,0,0,0,'小米（MI）小米电视4A 55英寸 L55M5-AZ/L55M5-AD 2GB+8GB HDR 4K超高清 人工智能网络液晶平板电视','',2499.00,249.00,100,0,'','0.00','','','',1,0,1,NULL,NULL,NULL,NULL,NULL),
	(35,10000,58,'NIKE',29,'男鞋',NULL,'耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b235bb9Nf606460b.jpg','6799342',0,0,0,0,0,'耐克NIKE 男子 休闲鞋 ROSHE RUN 运动鞋 511881-010黑色41码','',369.00,249.00,100,0,'','0.00','','','',1,0,1,'超级管理员','2019-06-14 22:39:11',1,'超级管理员','2019-06-14 22:39:11');

/*!40000 ALTER TABLE `pms_product_spu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms_coupon
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_coupon`;

CREATE TABLE `sms_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券',
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '优惠码',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `per_limit` int(11) DEFAULT NULL COMMENT '每人限领张数',
  `min_point` decimal(10,2) DEFAULT NULL COMMENT '使用门槛；0表示无门槛',
  `start_type` int(1) DEFAULT NULL COMMENT '使用计时方式（0:固定时间；1:领取后几天）',
  `after_day` int(6) DEFAULT NULL COMMENT '领取后几天',
  `start_time` datetime DEFAULT NULL COMMENT '使用开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '使用结束时间',
  `use_type` int(1) DEFAULT NULL COMMENT '使用类型：0->全场通用；1->指定分类；2->指定商品',
  `description` varchar(200) DEFAULT NULL COMMENT '备注',
  `publish_count` int(11) DEFAULT NULL COMMENT '发行数量',
  `use_count` int(11) DEFAULT NULL COMMENT '已使用数量',
  `receive_count` int(11) DEFAULT NULL COMMENT '领取数量',
  `enable_time` datetime DEFAULT NULL COMMENT '可以领取的日期',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠卷表';

LOCK TABLES `sms_coupon` WRITE;
/*!40000 ALTER TABLE `sms_coupon` DISABLE KEYS */;

INSERT INTO `sms_coupon` (`id`, `merchant_id`, `type`, `name`, `code`, `count`, `amount`, `per_limit`, `min_point`, `start_type`, `after_day`, `start_time`, `end_time`, `use_type`, `description`, `publish_count`, `use_count`, `receive_count`, `enable_time`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(3,10000,0,'小米手机专用券',NULL,92,50.00,1,1000.00,1,6,'2018-08-27 16:40:47','2018-11-16 16:40:47',2,'小米手机专用优惠券',100,0,8,'2018-08-27 16:40:47',0,NULL,NULL,NULL,1,'超级管理员','2019-07-04 07:18:55'),
	(7,10000,0,'T恤分类专用优惠券',NULL,93,50.00,1,500.00,0,NULL,'2018-08-27 16:40:47','2018-08-15 16:40:47',1,'满500减50',100,0,7,'2018-08-27 16:40:47',0,NULL,NULL,NULL,NULL,NULL,NULL),
	(8,10000,0,'新优惠券',NULL,100,100.00,1,1000.00,0,NULL,'2018-11-08 00:00:00','2018-11-27 00:00:00',0,'测试',100,0,1,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(12,10000,0,'移动端全品类通用券',NULL,1,10.00,1,100.00,1,10,'2018-11-08 00:00:00','2018-11-10 00:00:00',0,NULL,100,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `sms_coupon` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms_coupon_relation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_coupon_relation`;

CREATE TABLE `sms_coupon_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '关联类型0:商品 1:分类',
  `relation_id` bigint(20) DEFAULT NULL,
  `relation_name` varchar(64) DEFAULT NULL COMMENT '关联名称',
  `relation_sn` varchar(64) DEFAULT NULL COMMENT '关联编码',
  `relation_pic` varchar(255) DEFAULT NULL COMMENT '关联图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券的关系表';

LOCK TABLES `sms_coupon_relation` WRITE;
/*!40000 ALTER TABLE `sms_coupon_relation` DISABLE KEYS */;

INSERT INTO `sms_coupon_relation` (`id`, `merchant_id`, `coupon_id`, `type`, `relation_id`, `relation_name`, `relation_sn`, `relation_pic`)
VALUES
	(32,10000,3,1,7,'女式超柔软拉毛运动开衫','No86577','https://img12.360buyimg.com/n1/s350x449_jfs/t1/52119/12/631/332367/5ce36819E6bc251d5/7488d5661b934c8b.jpg!cc_350x449.jpg'),
	(33,10000,3,1,26,'华为 HUAWEI P20 ','6946605','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg'),
	(34,10000,3,1,30,'HLA海澜之家简约动物印花短袖T恤','HNTBJ2E042A','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg'),
	(35,10000,3,1,33,'小米（MI）小米电视4A ','4609652','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg'),
	(36,10000,3,1,27,'小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待','7437788','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg'),
	(37,10000,3,1,1,'银色星芒刺绣网纱底裤','No86577','https://img10.360buyimg.com/n1/s350x449_jfs/t1/79809/2/1856/303086/5d02f97fE3809afde/b999b769591bf6fe.jpg!cc_350x449.jpg');

/*!40000 ALTER TABLE `sms_coupon_relation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms_member_coupon
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_member_coupon`;

CREATE TABLE `sms_member_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `type` int(1) DEFAULT NULL COMMENT '优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券',
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '优惠码',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `min_point` decimal(10,2) DEFAULT NULL COMMENT '使用门槛；0表示无门槛',
  `start_time` datetime DEFAULT NULL COMMENT '使用开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '使用结束时间',
  `use_type` int(1) DEFAULT NULL COMMENT '使用类型：0->全场通用；1->指定分类；2->指定商品',
  `description` varchar(200) DEFAULT NULL COMMENT '备注',
  `member_id` bigint(20) DEFAULT NULL COMMENT 'C端用户会员id',
  `member_name` varchar(32) DEFAULT NULL COMMENT '用户帐号',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `is_used` int(1) DEFAULT NULL COMMENT '是否使用',
  `used_time` date DEFAULT NULL COMMENT '使用时间',
  `used_source` varchar(64) DEFAULT NULL COMMENT '使用source（例如：订单id）',
  `used_source_type` int(3) DEFAULT NULL COMMENT '使用类型（1:订单 2:赠送）',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员优惠卷表';

LOCK TABLES `sms_member_coupon` WRITE;
/*!40000 ALTER TABLE `sms_member_coupon` DISABLE KEYS */;

INSERT INTO `sms_member_coupon` (`id`, `merchant_id`, `coupon_id`, `type`, `name`, `code`, `amount`, `min_point`, `start_time`, `end_time`, `use_type`, `description`, `member_id`, `member_name`, `member_icon`, `is_used`, `used_time`, `used_source`, `used_source_type`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(22,10000,3,0,'小米手机专用券',NULL,50.00,1000.00,'2018-08-27 16:40:47','2018-11-16 16:40:47',2,'小米手机专用优惠券',1,'小明同学','https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3',1,'2018-08-27','1000987266544',1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(23,10000,3,0,'T恤分类专用优惠券',NULL,50.00,500.00,'2018-08-27 16:40:47','2018-08-15 16:40:47',1,'满500减50',1,'小明同学','https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3',0,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(24,10000,3,0,'新优惠券',NULL,100.00,1000.00,'2018-11-08 00:00:00','2018-11-27 00:00:00',0,'测试',2,'王小虎','https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3',0,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `sms_member_coupon` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms_promotion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_promotion`;

CREATE TABLE `sms_promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `type` int(3) DEFAULT NULL COMMENT '活动类型；0->满减；1->满赠；2->限时优惠；3->赠品',
  `name` varchar(200) DEFAULT NULL COMMENT '活动名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始日期',
  `end_time` datetime DEFAULT NULL COMMENT '结束日期',
  `use_type` int(1) DEFAULT NULL COMMENT '使用类型：0->全场通用；1->指定分类；2->指定商品',
  `full_price` decimal(12,2) DEFAULT NULL COMMENT '满金额',
  `reduce_price` decimal(12,2) DEFAULT NULL COMMENT '减金额',
  `gift_type` int(3) DEFAULT NULL COMMENT '赠品类型（0:商品 1:优惠券）',
  `gift_id` bigint(20) DEFAULT NULL COMMENT '赠品id',
  `gift_name` varchar(64) DEFAULT NULL COMMENT '赠品名称',
  `gift_pic` varchar(255) DEFAULT NULL COMMENT '赠品图片',
  `discount` int(3) DEFAULT NULL COMMENT '限时折扣',
  `description` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT NULL COMMENT '上下线状态',
  `create_time` datetime DEFAULT NULL COMMENT '秒杀时间段名称',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动信息表';

LOCK TABLES `sms_promotion` WRITE;
/*!40000 ALTER TABLE `sms_promotion` DISABLE KEYS */;

INSERT INTO `sms_promotion` (`id`, `merchant_id`, `type`, `name`, `start_time`, `end_time`, `use_type`, `full_price`, `reduce_price`, `gift_type`, `gift_id`, `gift_name`, `gift_pic`, `discount`, `description`, `status`, `create_time`, `creator_id`, `creator`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(14,10000,1,'双十一满500减100活动','2018-08-27 00:00:00','2018-09-27 00:00:00',1,500.00,100.00,1,7,'T恤分类专用优惠券','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg',NULL,NULL,0,NULL,NULL,NULL,1,'超级管理员','2019-07-03 23:34:39'),
	(15,10000,1,'妈咪爱满200赠倍增奶瓶','2018-11-16 11:11:31','2018-12-16 11:11:31',1,200.00,NULL,NULL,1,'倍增品牌奶瓶奶瓶','https://img11.360buyimg.com/n7/jfs/t1006/286/984697783/48671/b5406080/556290d1N33a289aa.jpg',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `sms_promotion` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms_promotion_notify
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_promotion_notify`;

CREATE TABLE `sms_promotion_notify` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `promotion_id` bigint(20) DEFAULT NULL COMMENT '活动id',
  `promotion_name` varchar(64) DEFAULT NULL COMMENT '活动名称',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `member_name` varchar(64) DEFAULT NULL COMMENT '会员名称',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `member_phone` varchar(32) DEFAULT NULL COMMENT '会员手机号',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `product_pic` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `subscribe_time` datetime DEFAULT NULL COMMENT '订阅时间',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `is_seand` int(1) DEFAULT NULL COMMENT '是否发送 0:否 1:是',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动开始通知表';



# Dump of table sms_promotion_relation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_promotion_relation`;

CREATE TABLE `sms_promotion_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL,
  `promotion_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '关联类型0:商品 1:分类',
  `relation_id` bigint(20) DEFAULT NULL,
  `relation_name` varchar(64) DEFAULT NULL COMMENT '关联名称',
  `relation_sn` varchar(64) DEFAULT NULL COMMENT '关联编码',
  `relation_pic` varchar(255) DEFAULT NULL COMMENT '关联图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠活动的关系表';

LOCK TABLES `sms_promotion_relation` WRITE;
/*!40000 ALTER TABLE `sms_promotion_relation` DISABLE KEYS */;

INSERT INTO `sms_promotion_relation` (`id`, `merchant_id`, `promotion_id`, `type`, `relation_id`, `relation_name`, `relation_sn`, `relation_pic`)
VALUES
	(11,10000,15,0,7,'女式超柔软拉毛运动开衫','No86577','https://img12.360buyimg.com/n1/s350x449_jfs/t1/52119/12/631/332367/5ce36819E6bc251d5/7488d5661b934c8b.jpg'),
	(31,10000,14,1,1,'银色星芒刺绣网纱底裤','No86577','https://img10.360buyimg.com/n1/s350x449_jfs/t1/79809/2/1856/303086/5d02f97fE3809afde/b999b769591bf6fe.jpg!cc_350x449.jpg'),
	(32,10000,14,1,7,'女式超柔软拉毛运动开衫','No86577','https://img12.360buyimg.com/n1/s350x449_jfs/t1/52119/12/631/332367/5ce36819E6bc251d5/7488d5661b934c8b.jpg!cc_350x449.jpg'),
	(33,10000,14,1,26,'华为 HUAWEI P20 ','6946605','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg');

/*!40000 ALTER TABLE `sms_promotion_relation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ssc_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ssc_account`;

CREATE TABLE `ssc_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '商户Id',
  `merchant_sn` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `is_master` tinyint(1) DEFAULT NULL COMMENT '是否主账户',
  `login_name` varchar(32) DEFAULT NULL COMMENT '登录账户名',
  `login_pwd` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户手机',
  `user_source` int(3) DEFAULT NULL COMMENT '来源',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '最近登录ip',
  `last_login_location` varchar(255) DEFAULT NULL COMMENT '最近登录地址',
  `last_login_time` int(11) DEFAULT NULL COMMENT '最近登录时间',
  `status` int(11) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户用户表';

LOCK TABLES `ssc_account` WRITE;
/*!40000 ALTER TABLE `ssc_account` DISABLE KEYS */;

INSERT INTO `ssc_account` (`id`, `merchant_id`, `merchant_sn`, `is_master`, `login_name`, `login_pwd`, `user_name`, `icon`, `phone`, `user_source`, `note`, `last_login_ip`, `last_login_location`, `last_login_time`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,10000,'10000',1,'admin','$2a$10$WMNxsjMUTBo.Bu3hq7C9ZuihobMalBGSMFtXtQvBWTCFyb6dLjM6W','超级管理员','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','13699063675',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,1,'超级管理员','2019-05-27 03:25:01'),
	(2,10000,'10000',0,'subuser','$2a$10$FLQqFyZsuPxj536Y3PvVOeBj1Cjmrh6r01fVppcenVVebQ2rpfAFu','子用户','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','13688909876',NULL,NULL,NULL,NULL,NULL,0,1,'超级管理员','2019-05-27 03:36:41',1,'超级管理员','2019-05-27 03:36:41');

/*!40000 ALTER TABLE `ssc_account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ssc_account_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ssc_account_role`;

CREATE TABLE `ssc_account_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `ssc_account_role` WRITE;
/*!40000 ALTER TABLE `ssc_account_role` DISABLE KEYS */;

INSERT INTO `ssc_account_role` (`id`, `user_id`, `role_id`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),
	(2,2,1,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ssc_account_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ssc_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ssc_menu`;

CREATE TABLE `ssc_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父ID',
  `menu_name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `permission` varchar(32) DEFAULT NULL COMMENT '权限编码',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单编码',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` int(3) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `sort` int(3) DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '权限描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户系统菜单';

LOCK TABLES `ssc_menu` WRITE;
/*!40000 ALTER TABLE `ssc_menu` DISABLE KEYS */;

INSERT INTO `ssc_menu` (`id`, `pid`, `menu_name`, `permission`, `menu_code`, `icon`, `type`, `url`, `sort`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,0,'运营工作台',NULL,'root',NULL,1,NULL,1,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(2,1,'商户中心',NULL,'merchant',NULL,1,NULL,2,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(3,1,'营销中心',NULL,'marketing',NULL,1,NULL,3,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,1,'订单中心',NULL,'order',NULL,1,NULL,4,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),
	(5,2,'配置管理',NULL,'merchant_config',NULL,1,NULL,5,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ssc_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ssc_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ssc_role`;

CREATE TABLE `ssc_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(64) DEFAULT NULL COMMENT '角色编号',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户角色';

LOCK TABLES `ssc_role` WRITE;
/*!40000 ALTER TABLE `ssc_role` DISABLE KEYS */;

INSERT INTO `ssc_role` (`id`, `name`, `code`, `sort`, `description`, `status`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,'超级管理员','admin',1,'超级管理员',0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ssc_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ssc_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ssc_role_menu`;

CREATE TABLE `ssc_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `creator_id` bigint(20) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_operator_id` bigint(20) DEFAULT NULL,
  `last_operator` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `ssc_role_menu` WRITE;
/*!40000 ALTER TABLE `ssc_role_menu` DISABLE KEYS */;

INSERT INTO `ssc_role_menu` (`id`, `menu_id`, `role_id`, `creator_id`, `creator`, `create_time`, `last_operator_id`, `last_operator`, `update_time`)
VALUES
	(1,1,1,NULL,NULL,NULL,NULL,NULL,NULL),
	(2,2,1,NULL,NULL,NULL,NULL,NULL,NULL),
	(3,3,1,NULL,NULL,NULL,NULL,NULL,NULL),
	(4,4,1,NULL,NULL,NULL,NULL,NULL,NULL),
	(5,5,1,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ssc_role_menu` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
