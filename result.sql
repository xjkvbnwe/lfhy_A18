/*
SQLyog v10.2 
MySQL - 5.7.27 : Database - hq_cosmic_sys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hq_cosmic_sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hq_cosmic_sys`;

/*Table structure for table `owner_goods` */

DROP TABLE IF EXISTS `owner_goods`;

CREATE TABLE `owner_goods` (
  `owner_id` varchar(1000) DEFAULT NULL,
  `goods_id` varchar(1000) DEFAULT NULL,
  `goods_number` varchar(1000) DEFAULT NULL,
  `goods_name` varchar(1000) DEFAULT NULL,
  `goods_total` bigint(20) DEFAULT NULL,
  `goods_createdate` datetime DEFAULT NULL,
  `goods_img` varchar(1000) DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `FId` bigint(20) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `owner_goods` */

insert  into `owner_goods`(`owner_id`,`goods_id`,`goods_number`,`goods_name`,`goods_total`,`goods_createdate`,`goods_img`,`goods_price`,`FId`) values ('3513bed163f8722d000c72d86de8af7f','86257965402314','6907992500010','优酸乳乳饮料――原味',4,'2023-01-31 13:25:41','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg',4.5,1610133784693309440),('3513bed163f8722d000c72d86de8af7f','95324795620123','6922456805166','康师傅绿茶（蜂蜜茉莉味）',6,'2023-01-31 13:28:35','http://static1.showapi.com/app2/img/barCode_img/20220119/2532c39f-6210-41ca-82c5-1a38269ab793.jpg',4,1610134276458676224),('3513bed163f8722d000c72d86de8af7f','57416983520145','6941760902743','川肉王火腿肠',4,'2023-01-31 13:29:27','http://app2.showapi.com/img/barCode_img/20180326/9b8ae777-cf0b-4ced-91f5-c310b955a51c.jpg',8,1610134790126699520),('3513bed163f8722d000c72d86de8af7f','94235786514025','6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）',3,'2023-01-31 13:30:32','http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg',4,1610135210135912448),('3513bed163f8722d000c72d86de8af7f','98234525612235','6939729901906','巧克力',9,'2023-02-01 22:03:40','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/qkl.jpg?sign=56315d7326286da015c025a99ff864ac&t=1679918250',5,1619089156891563028),('3513bed163f8722d000c72d86de8af7f','02118505215465','6902538004045','大瓶脉动',13,'2023-02-01 22:05:21','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/md.jpg?sign=bffcde5975759c19ad04eb63f3c6dce8&t=1679918290',6,1612742047852045870),('3513bed163f8722d000c72d86de8af7f','18912618921563','6926547702365','口含糖片',7,'2023-03-09 21:22:50','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/tp.png?sign=176639ca60c5837f2e5ad181b00f4d84&t=1679918272',5,161512489527892534),('3513bed163f8722d000c72d86de8af7f','12319520305633','6941025137545','顺滑固体胶',4,'2023-03-01 21:23:52','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/gtj.jpg?sign=1d059c50b0cdbbe94185c4817e188ff9&t=1679918227',4,1564189022610656),('3513bed163f8722d000c72d86de8af7f','15604189231568','6917878000879','雀巢奶香威化巧克力(代可可脂)20g',8,'2023-03-05 09:31:52','http://res-showapi.oss-cn-hangzhou.aliyuncs.com/barcode/20220720/d14aa7f5-25c2-4136-88f9-a73bd8473d0a.jpg',2,161984410564189056);

/*Table structure for table `owner_selllog` */

DROP TABLE IF EXISTS `owner_selllog`;

CREATE TABLE `owner_selllog` (
  `buy_tradeno` varchar(1000) DEFAULT NULL,
  `owner_id` varchar(1000) DEFAULT NULL,
  `buy_openid` varchar(1000) DEFAULT NULL,
  `buy_amount` double DEFAULT NULL,
  `buy_date` datetime DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `FId` bigint(20) DEFAULT '0',
  `fk_ozwe_textfield` varchar(50) DEFAULT ' '
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `owner_selllog` */

insert  into `owner_selllog`(`buy_tradeno`,`owner_id`,`buy_openid`,`buy_amount`,`buy_date`,`state`,`FId`,`fk_ozwe_textfield`) values ('86452375103602','3513bed163f8722d000c72d86de8af7f','1f437e2e642180da001dc47a5f9d957a',10,'2023-03-26 18:00:00','已提现',2867548614786254,' '),('35857733556107','3513bed163f8722d000c72d86de8af7f','1f437e2e642180da001dc47a5f9d957a',9,'2023-03-27 21:44:47','已提现',9493484958879090,' '),('66882642085309','3513bed163f8722d000c72d86de8af7f','1f437e2e642180da001dc47a5f9d957a',4,'2023-03-28 12:41:45','已提现',3085266704816732,' '),('65423071474007','3513bed163f8722d000c72d86de8af7f','1f437e2e642180da001dc47a5f9d957a',1.5,'2023-03-29 21:53:10','待支付',3790506990588879,' '),('60462775250963','3513bed163f8722d000c72d86de8af7f','fc8e64656401caf402c055502073891f',9,'2023-03-29 13:41:00','交易成功',4269017523545852,' ');

/*Table structure for table `tk_ozwe_advertisement` */

DROP TABLE IF EXISTS `tk_ozwe_advertisement`;

CREATE TABLE `tk_ozwe_advertisement` (
  `FID` bigint(20) NOT NULL,
  `fnumber` varchar(30) NOT NULL DEFAULT ' ',
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  `fstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fenable` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatetime` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fmasterid` bigint(20) DEFAULT NULL,
  `fk_ozwe_videoimg` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_videoamount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_videohot` bigint(20) DEFAULT NULL,
  `fk_ozwe_videosign` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_videoaddress` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_filename` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_advertisement` */

insert  into `tk_ozwe_advertisement`(`FID`,`fnumber`,`fname`,`fstatus`,`fcreatorid`,`fmodifierid`,`fenable`,`fcreatetime`,`fmodifytime`,`fmasterid`,`fk_ozwe_videoimg`,`fk_ozwe_videoamount`,`fk_ozwe_videohot`,`fk_ozwe_videosign`,`fk_ozwe_videoaddress`,`fk_ozwe_filename`) values (1596579163429404672,'1545615223418968','美好时光海苔','A',1411048922595459072,1411048922595459072,'1','2023-01-12 20:35:15','2023-03-29 12:21:23',1596579163429404672,'f7cda5434866442786cd6b12155ad792.png&v=1.0','0.0500000000',12,'美好时光,海苔','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/haitai1.mp4?sign=9dcd997aceb978ba5619a374a0b123a6&t=1673583017','Advertisement-01'),(1596580068719592448,'5301593780345026','太极急支糖浆-加强版','A',1411048922595459072,1411048922595459072,'1','2023-01-12 20:37:48','2023-03-29 12:21:14',1596580068719592448,'60c5bfe0c96948e1884d15f37828a23e.png&v=1.0','0.0200000000',18,'糖浆,太极','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/tangjiang.mp4?sign=7aae5b01adf2faaf7fa33d6879b4137b&t=1673582599','Advertisement-02'),(1597045051299464192,'1571668252418715','太极急支糖浆','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:02:25','2023-03-29 11:43:07',1597045051299464192,' ','0.0500000000',11,'太极;急支糖浆;猎豹;美女','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/tangjiang.mp4?sign=7aae5b01adf2faaf7fa33d6879b4137b&t=1673582599','Advertisement-05'),(1597045589764211712,'4516871546871548','我就是要旺旺碎冰冰','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:03:26','2023-03-29 11:43:28',1597045589764211712,' ','0.0800000000',25,'旺旺;碎冰冰;小孩;妈妈','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/suibinbin.mp4?sign=24cf6c0de8ba10b809220cb17af323c1&t=1673582636','Advertisement-07'),(1597046053276746752,'15641561051521065','日本沙雕牛奶广告，超级沙雕','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:04:30','2023-03-29 11:42:59',1597046053276746752,' ','0.0400000000',19,'日本;牛奶;沙雕;超级','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/shadiaoniunai.mp4?sign=4189480dcc61bce362cfc86106ff115e&t=1673582720','Advertisement-04'),(1597048071038959616,'489453419832465','汇源肾宝片，专治男性肾透支，精神不振，腰椎酸痛等各种问题','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:05:25','2023-03-29 11:43:34',1597048071038959616,' ','0.0900000000',30,'汇源;肾宝片;肾透支','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/shenbaopian.mp4?sign=2706cecd5f1b9570a31523c64c35190b&t=1673582960','Advertisement-08'),(1597049025100514304,'241980895241560','海苔我要美好时光，更酥脆，更营养','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:09:26','2023-03-29 11:43:13',1597049025100514304,' ','0.0700000000',24,'美好;海苔;营养','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/haitai1.mp4?sign=9dcd997aceb978ba5619a374a0b123a6&t=1673583017','Advertisement-06'),(1597051537018847232,'5648902315686','带给全家营养健康的美好时光海苔','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:11:20','2023-02-05 21:13:03',1597051537018847232,' ','0.0400000000',3,'美好时光;海苔','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/haitai2.mp4?sign=827ff611777c6f03579e19baebef271c&t=1673583374','Advertisement-03'),(1597051998518117376,'489456481023','百事可乐独家广告','A',1411048922595459072,1411048922595459072,'1','2023-01-13 12:16:19','2023-03-29 11:43:39',1597051998518117376,' ','0.0300000000',11,'可乐;百事可乐','https://786a-xjkvbnwe-5g4tba0j92d4c844-1313489977.tcb.qcloud.la/CasualBuying/baishi.mp4?sign=8957e5cc002c9696105a90f84c6e86b5&t=1673583429','Advertisement-09');

/*Table structure for table `tk_ozwe_advertisement_l` */

DROP TABLE IF EXISTS `tk_ozwe_advertisement_l`;

CREATE TABLE `tk_ozwe_advertisement_l` (
  `FPKID` varchar(36) NOT NULL,
  `FID` bigint(20) NOT NULL,
  `FLocaleID` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FPKID`),
  KEY `idx__ozwe_advertisement_L_0` (`FID`,`FLocaleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_advertisement_l` */

insert  into `tk_ozwe_advertisement_l`(`FPKID`,`FID`,`FLocaleID`,`fname`) values ('3/30Z0WU1J3K',1596579163429404672,'zh_CN','美好时光海苔'),('3/30Z0WU1J3L',1596579163429404672,'zh_TW','美好時光海苔'),('3/313N400+=O',1596580068719592448,'zh_CN','太极急支糖浆-加强版'),('3/313N400+=P',1596580068719592448,'zh_TW','太極急支糖漿-加強版'),('3/5=+ZO13YH3',1597045051299464192,'zh_CN','太极急支糖浆'),('3/5=+ZO13YH4',1597045051299464192,'zh_TW','太極急支糖漿'),('3/5=2WN5WMS=',1597045589764211712,'zh_CN','我就是要旺旺碎冰冰'),('3/5=2WN5WMSA',1597045589764211712,'zh_TW','我就是要旺旺碎冰冰'),('3/5=69CEKVPO',1597046053276746752,'zh_CN','日本沙雕牛奶广告，超级沙雕'),('3/5=69CEKVPP',1597046053276746752,'zh_TW','日本沙雕牛奶廣告，超級沙雕'),('3/5=JZT=HECQ',1597048071038959616,'zh_CN','汇源肾宝片，专治男性肾透支，精神不振，腰椎酸痛等各种问题'),('3/5=JZT=HECR',1597048071038959616,'zh_TW','彙源腎寶片，專治男性腎透支，精神不振，腰椎酸痛等各種問題'),('3/5=QXZNQEYQ',1597049025100514304,'zh_CN','海苔我要美好时光，更酥脆，更营养'),('3/5=QXZNQEYR',1597049025100514304,'zh_TW','海苔我要美好時光，更酥脆，更營養'),('3/5A77TGZSAC',1597051537018847232,'zh_CN','带给全家营养健康的美好时光海苔'),('3/5A77TGZSAD',1597051537018847232,'zh_TW','帶給全家營養健康的美好時光海苔'),('3/5A=KZI0RIY',1597051998518117376,'zh_CN','百事可乐独家广告'),('3/5A=KZI0RIZ',1597051998518117376,'zh_TW','百事可樂獨家廣告');

/*Table structure for table `tk_ozwe_advertisement_r3` */

DROP TABLE IF EXISTS `tk_ozwe_advertisement_r3`;

CREATE TABLE `tk_ozwe_advertisement_r3` (
  `FID` bigint(20) NOT NULL DEFAULT '0',
  `FRefStatus` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_advertisement_r3` */

/*Table structure for table `tk_ozwe_apply` */

DROP TABLE IF EXISTS `tk_ozwe_apply`;

CREATE TABLE `tk_ozwe_apply` (
  `FId` bigint(20) NOT NULL,
  `fbillno` varchar(30) NOT NULL DEFAULT ' ',
  `fbillstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fauditorid` bigint(20) DEFAULT NULL,
  `fauditdate` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fcreatetime` datetime DEFAULT NULL,
  `fk_ozwe_goodnumber` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodname` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodcategory` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_datetime` datetime DEFAULT NULL,
  `fk_ozwe_applier` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_pay` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_createdate` datetime DEFAULT NULL,
  `fk_ozwe_org` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_apply` */

insert  into `tk_ozwe_apply`(`FId`,`fbillno`,`fbillstatus`,`fcreatorid`,`fmodifierid`,`fauditorid`,`fauditdate`,`fmodifytime`,`fcreatetime`,`fk_ozwe_goodnumber`,`fk_ozwe_goodname`,`fk_ozwe_goodcategory`,`fk_ozwe_datetime`,`fk_ozwe_applier`,`fk_ozwe_pay`,`fk_ozwe_createdate`,`fk_ozwe_org`) values (1183575579885820,'8766021004344418','C',0,1411048922595459072,1411048922595459072,'2023-03-30 17:14:22','2023-03-30 17:14:22',NULL,' ',' ',' ',NULL,'Yyang','29.0000000000','2023-03-24 07:58:44',1613049374915428352),(4288901919345556,'5535702477387078','C',0,1411048922595459072,1411048922595459072,'2023-03-06 21:52:32','2023-03-06 21:52:32',NULL,' ',' ',' ',NULL,'醉倾梦','6.0000000000','2023-03-06 09:51:36',1613048174706950144),(4841141569137553,'5945577680783620','C',0,1411048922595459072,1411048922595459072,'2023-03-23 16:42:34','2023-03-23 16:42:34',NULL,' ',' ',' ',NULL,'汤姆猫','28.0000000000','2023-03-23 04:41:44',1613048174706950144),(14677186506945828,'4596298458119239','C',0,1411048922595459072,1411048922595459072,'2023-03-12 17:19:23','2023-03-12 17:19:23',NULL,' ',' ',' ',NULL,'别来无恙','28.0000000000','2023-03-12 05:18:41',1613048174706950144),(17552911552012496,'5301536108021030','A',0,0,0,NULL,NULL,NULL,' ',' ',' ',NULL,'我爱学英语','21.0000000000','2023-03-23 05:47:45',1613048174706950144),(23730660128583736,'2474414638467344','C',0,1411048922595459072,1411048922595459072,'2023-03-29 10:52:25','2023-03-29 10:52:25',NULL,' ',' ',' ',NULL,'醉倾梦','23.0000000000','2023-03-29 10:48:17',1613048174706950144),(26111924503226260,'1783597003476595','C',0,1411048922595459072,1411048922595459072,'2023-03-06 21:36:17','2023-03-06 21:36:17',NULL,' ',' ',' ',NULL,'醉倾梦','6.0000000000','2023-02-03 07:21:45',1613049374915428352),(26275322775345232,'7024183249394028','B',0,0,0,NULL,NULL,NULL,' ',' ',' ',NULL,'我爱学英语','25.0000000000','2023-03-23 05:44:37',1613048174706950144),(28774838400226064,'6677871495611518','C',0,1411048922595459072,1411048922595459072,'2023-03-07 12:40:16','2023-03-07 12:40:16',NULL,' ',' ',' ',NULL,'醉倾梦','6.0000000000','2023-03-07 12:39:37',1613048174706950144),(29024046415432416,'8331510102006748','C',0,1411048922595459072,1411048922595459072,'2023-03-30 21:14:30','2023-03-30 21:14:30',NULL,' ',' ',' ',NULL,'醉倾梦','50.0000000000','2023-03-30 09:12:59',1613049374915428352),(30207827581508340,'6413857100062383','C',0,1411048922595459072,1411048922595459072,'2023-03-06 21:36:38','2023-03-06 21:36:38',NULL,' ',' ',' ',NULL,'醉倾梦','3.0000000000','2023-03-06 09:34:53',1613048174706950144),(30696676848168168,'8012583860422599','C',0,1411048922595459072,1411048922595459072,'2023-03-07 14:22:36','2023-03-07 14:22:36',NULL,' ',' ',' ',NULL,'醉倾梦','18.0000000000','2023-03-07 02:21:53',1613048174706950144),(36017287974269620,'7467046007614375','C',0,1411048922595459072,1411048922595459072,'2023-03-09 21:01:11','2023-03-09 21:01:11',NULL,' ',' ',' ',NULL,'微信用户','69.0000000000','2023-03-09 08:59:51',1613048174706950144),(36439057386434928,'7878816608556917','C',0,1411048922595459072,1411048922595459072,'2023-03-12 21:08:40','2023-03-12 21:08:40',NULL,' ',' ',' ',NULL,'陈龙爹','3.0000000000','2023-03-12 09:07:57',1613048174706950144),(75766919771773456,'7391612283730380','C',0,1411048922595459072,1411048922595459072,'2023-03-06 21:36:17','2023-03-06 21:36:17',NULL,' ',' ',' ',NULL,'醉倾梦','63.0000000000','2023-01-17 12:25:34',1613048174706950144),(75990715716171072,'2201320651694736','C',0,1411048922595459072,1411048922595459072,'2023-03-23 17:11:33','2023-03-23 17:11:33',NULL,' ',' ',' ',NULL,'Yyang','33.0000000000','2023-03-23 05:11:00',1613048174706950144),(84141455392857616,'9665469586492652','C',0,1411048922595459072,1411048922595459072,'2023-03-07 12:25:48','2023-03-07 12:25:48',NULL,' ',' ',' ',NULL,'醉倾梦','18.0000000000','2023-03-07 12:25:11',1613048174706950144),(86519296889708432,'1368041402759334','C',0,1411048922595459072,1411048922595459072,'2023-03-06 21:36:17','2023-03-06 21:36:17',NULL,' ',' ',' ',NULL,'醉倾梦','3.0000000000','2023-01-17 06:46:02',1613049374915428352),(1612993251000714240,'8531023754106845','C',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-06 21:36:17','2023-03-06 21:36:17','2023-02-04 12:07:19',' ',' ',' ',NULL,'醉倾梦','15.0000000000','2023-02-04 12:07:19',1613048174706950144);

/*Table structure for table `tk_ozwe_applyentryentity` */

DROP TABLE IF EXISTS `tk_ozwe_applyentryentity`;

CREATE TABLE `tk_ozwe_applyentryentity` (
  `FId` bigint(20) NOT NULL,
  `FEntryId` bigint(20) NOT NULL,
  `FSeq` int(11) NOT NULL DEFAULT '0',
  `fmodifierfield` bigint(20) DEFAULT NULL,
  `fmodifydatefield` datetime DEFAULT NULL,
  `fk_ozwe_category` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_singleamount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_totalamount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_integerfield` bigint(20) DEFAULT NULL,
  `fk_ozwe_other` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_size` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodnumber` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodname` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodimg` varchar(255) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FEntryId`),
  KEY `idx__ozwe_applyentryentity_fk` (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_applyentryentity` */

insert  into `tk_ozwe_applyentryentity`(`FId`,`FEntryId`,`FSeq`,`fmodifierfield`,`fmodifydatefield`,`fk_ozwe_category`,`fk_ozwe_singleamount`,`fk_ozwe_totalamount`,`fk_ozwe_integerfield`,`fk_ozwe_other`,`fk_ozwe_size`,`fk_ozwe_goodnumber`,`fk_ozwe_goodname`,`fk_ozwe_goodimg`) values (23730660128583736,1978261789900737,2,NULL,NULL,' ','10.0000000000','20.0000000000',2,' ','3包*6提 小包','6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）','http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg'),(23730660128583736,9384662168114722,1,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(29024046415432416,10294941290803906,1,NULL,NULL,' ','10.0000000000','20.0000000000',2,' ','50g*10支','6941760902743','川肉王火腿肠','http://app2.showapi.com/img/barCode_img/20180326/9b8ae777-cf0b-4ced-91f5-c310b955a51c.jpg'),(1183575579885820,10817270995973316,2,NULL,NULL,' ','10.0000000000','20.0000000000',2,' ','50g*10支','6941760902743','川肉王火腿肠','http://app2.showapi.com/img/barCode_img/20180326/9b8ae777-cf0b-4ced-91f5-c310b955a51c.jpg'),(26275322775345232,10841344934286212,1,NULL,NULL,' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(1183575579885820,18840631998840596,1,NULL,NULL,' ','3.0000000000','9.0000000000',3,' ','500ml','6922456805166','康师傅绿茶（蜂蜜茉莉味）','http://static1.showapi.com/app2/img/barCode_img/20220119/2532c39f-6210-41ca-82c5-1a38269ab793.jpg'),(4841141569137553,35188827612784768,3,NULL,NULL,' ','10.0000000000','10.0000000000',1,' ','3包*6提 小包','6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）','http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg'),(75990715716171072,36642315852271432,1,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(17552911552012496,36934280689709608,1,NULL,NULL,' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(29024046415432416,38707948031603232,2,NULL,NULL,' ','10.0000000000','30.0000000000',3,' ','24包*28条（6片/包） 小包','6903244678070','C3624心相印茶语丝享系列24包装四层纸手帕（超迷你）','http://static1.showapi.com/app2/img/barCode_img/20220427/6a0e2398-6595-492f-87a5-9abb8e47559c.jpg'),(17552911552012496,43619845710039184,2,NULL,NULL,' ','3.0000000000','6.0000000000',2,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(75990715716171072,48380946342768080,2,NULL,NULL,' ','15.0000000000','30.0000000000',2,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(4841141569137553,57452572147736560,1,NULL,NULL,' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(4841141569137553,78148280611703920,2,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(26275322775345232,86369962765606912,2,NULL,NULL,' ','10.0000000000','10.0000000000',1,' ','3包*6提 小包','6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）','http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg'),(30207827581508340,131052598104074432,1,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(36439057386434928,978346043295592576,1,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(30696676848168168,1250777179289046528,1,NULL,NULL,' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(1612993251000714240,1612993251009101824,1,1411048922595459072,'2023-02-04 12:09:40',' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g',' '),(14677186506945828,2183769071637002752,1,NULL,NULL,' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(26111924503226260,2696349087942642176,1,NULL,NULL,' ','3.0000000000','6.0000000000',2,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(84141455392857616,2783650359101953536,2,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(28774838400226064,3141945524010541056,1,NULL,NULL,' ','3.0000000000','6.0000000000',2,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(75766919771773456,3157289462960538624,3,NULL,NULL,' ','10.0000000000','20.0000000000',2,' ','24包*28条（6片/包） 小包','6903244678070','C3624心相印茶语丝享系列24包装四层纸手帕（超迷你）','http://static1.showapi.com/app2/img/barCode_img/20220427/6a0e2398-6595-492f-87a5-9abb8e47559c.jpg'),(14677186506945828,3277873734971837952,2,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(36017287974269620,3284157458384456704,2,NULL,NULL,' ','3.0000000000','9.0000000000',3,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(75766919771773456,4044279256857951232,2,NULL,NULL,' ','10.0000000000','40.0000000000',4,' ','3包*6提 小包','6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）','http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg'),(30696676848168168,4125101897314165248,2,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(4288901919345556,4942751381071931392,1,NULL,NULL,' ','3.0000000000','6.0000000000',2,' ','250ml','6907992500010','优酸乳乳饮料――原味','http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg'),(36017287974269620,5749134357268484096,1,NULL,NULL,' ','15.0000000000','60.0000000000',4,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg'),(86519296889708432,7256440810028233728,1,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','500ml','6922456805166','康师傅绿茶（蜂蜜茉莉味）','http://static1.showapi.com/app2/img/barCode_img/20220119/2532c39f-6210-41ca-82c5-1a38269ab793.jpg'),(75766919771773456,8660463047884608512,1,NULL,NULL,' ','3.0000000000','3.0000000000',1,' ','500ml','6922456805166','康师傅绿茶（蜂蜜茉莉味）','http://static1.showapi.com/app2/img/barCode_img/20220119/2532c39f-6210-41ca-82c5-1a38269ab793.jpg'),(14677186506945828,8901669330290535424,3,NULL,NULL,' ','10.0000000000','10.0000000000',1,' ','3包*6提 小包','6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）','http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg'),(84141455392857616,9223372036854775807,1,NULL,NULL,' ','15.0000000000','15.0000000000',1,' ','233g','6901668005809','奥利奥夹心草莓味233g','http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg');

/*Table structure for table `tk_ozwe_goodlist` */

DROP TABLE IF EXISTS `tk_ozwe_goodlist`;

CREATE TABLE `tk_ozwe_goodlist` (
  `FID` bigint(20) NOT NULL,
  `fnumber` varchar(30) NOT NULL DEFAULT ' ',
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  `fstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fenable` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatetime` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fmasterid` bigint(20) DEFAULT NULL,
  `fk_ozwe_picturefield` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodcompany` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodintroduction` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodnum` bigint(20) DEFAULT NULL,
  `fk_ozwe_goodcategory` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_isoutput` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodamount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_integerfield` bigint(20) DEFAULT NULL,
  `fk_ozwe_integerfield1` bigint(20) DEFAULT NULL,
  `fk_ozwe_textareafield` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_isadequate` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_output` bigint(20) DEFAULT NULL,
  `fk_ozwe_bigintfield1` bigint(20) DEFAULT NULL,
  `fk_ozwe_total` bigint(20) DEFAULT NULL,
  `fk_ozwe_input` bigint(20) DEFAULT NULL,
  `fk_ozwe_orgfield` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_goodlist` */

insert  into `tk_ozwe_goodlist`(`FID`,`fnumber`,`fname`,`fstatus`,`fcreatorid`,`fmodifierid`,`fenable`,`fcreatetime`,`fmodifytime`,`fmasterid`,`fk_ozwe_picturefield`,`fk_ozwe_goodcompany`,`fk_ozwe_goodintroduction`,`fk_ozwe_goodnum`,`fk_ozwe_goodcategory`,`fk_ozwe_isoutput`,`fk_ozwe_goodamount`,`fk_ozwe_integerfield`,`fk_ozwe_integerfield1`,`fk_ozwe_textareafield`,`fk_ozwe_isadequate`,`fk_ozwe_output`,`fk_ozwe_bigintfield1`,`fk_ozwe_total`,`fk_ozwe_input`,`fk_ozwe_orgfield`) values (1587132273642177536,'6903244676915','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）','B',1411048922595459072,1411048922595459072,'1','2022-12-30 19:47:12','2023-02-04 14:27:10',1587132273642177536,'http://static1.showapi.com/app2/img/barCode_img/20220117/d0dbda20-8967-41b0-9114-988e4465a328.jpg',' ',' ',NULL,'纸制品','是',NULL,NULL,NULL,'纸面巾',' ',0,NULL,10,0,1613048174706950144),(1587257519342878720,'6907992500010','优酸乳乳饮料――原味','B',1411048922595459072,1411048922595459072,'1','2022-12-30 23:57:04','2023-02-04 14:27:38',1587257519342878720,'http://app2.showapi.com/img/barCode_img/20200520/e5eacae1-49a0-4de5-9fce-ca7b46a619d4.jpg',' ',' ',NULL,'饮品','是',NULL,NULL,NULL,' ',' ',0,NULL,10,0,1613048174706950144),(1588671211854889984,'6903244678070','C3624心相印茶语丝享系列24包装四层纸手帕（超迷你）','B',1411048922595459072,1411048922595459072,'1','2023-01-01 22:45:12','2023-02-04 14:27:21',1588671211854889984,'http://static1.showapi.com/app2/img/barCode_img/20220427/6a0e2398-6595-492f-87a5-9abb8e47559c.jpg',' ',' ',NULL,'纸制品','是',NULL,NULL,NULL,' ',' ',0,NULL,10,0,1613049374915428352),(1589886802892687360,'6926892565080','好粥道(莲子玉米粥)','B',1411048922595459072,1411048922595459072,'1','2023-01-03 15:00:22','2023-02-04 14:28:17',1589886802892687360,' ',' ',' ',NULL,'食品','是',NULL,NULL,NULL,' ',' ',0,NULL,10,0,1613049374915428352),(1589887617678182400,'6944392600001','丹麦吐司','B',1411048922595459072,1411048922595459072,'1','2023-01-03 15:02:11','2023-02-04 14:28:40',1589887617678182400,' ',' ',' ',NULL,'食品','是',NULL,NULL,NULL,'66',' ',0,NULL,10,0,1613049374915428352),(1589888117463058432,'6941760902743','川肉王火腿肠','B',1411048922595459072,1411048922595459072,'1','2023-01-03 15:02:55','2023-02-04 14:28:29',1589888117463058432,'http://app2.showapi.com/img/barCode_img/20180326/9b8ae777-cf0b-4ced-91f5-c310b955a51c.jpg',' ',' ',NULL,'食品','是',NULL,NULL,NULL,' ',' ',0,NULL,10,0,1613049374915428352),(1589888355078769664,'6922456805166','康师傅绿茶（蜂蜜茉莉味）','B',1411048922595459072,1411048922595459072,'1','2023-01-03 15:04:06','2023-02-04 14:28:04',1589888355078769664,'http://static1.showapi.com/app2/img/barCode_img/20220119/2532c39f-6210-41ca-82c5-1a38269ab793.jpg',' ',' ',NULL,'饮品','是',NULL,NULL,NULL,'茶饮品',' ',0,NULL,10,0,1613049374915428352),(1589891921898114048,'6920930316641','布绒娃娃水彩笔','B',1411048922595459072,1411048922595459072,'1','2023-01-03 15:10:04','2023-02-04 14:27:51',1589891921898114048,' ',' ',' ',NULL,'其他','是',NULL,NULL,NULL,'布绒娃娃水彩笔',' ',0,NULL,10,0,1613049374915428352),(1612991417972424704,'6901668005809','奥利奥夹心草莓味233g','B',1411048922595459072,1411048922595459072,'1','2023-02-04 12:05:40','2023-02-04 14:26:59',1612991417972424704,'http://static1.showapi.com/app2/img/barCode_img/20220906/b008a35a-30f6-4461-b4be-035c9615bf59.jpg',' ',' ',NULL,'食品','是',NULL,NULL,NULL,' ',' ',0,NULL,50,50,1613048174706950144),(1635377586223711232,'6917878000879','雀巢奶香威化巧克力(代可可脂)20g','B',1411048922595459072,1411048922595459072,'1','2023-03-07 09:22:48','2023-03-07 09:23:20',1635377586223711232,'http://res-showapi.oss-cn-hangzhou.aliyuncs.com/barcode/20220720/d14aa7f5-25c2-4136-88f9-a73bd8473d0a.jpg',' ',' ',NULL,'食品','是',NULL,NULL,NULL,' ',' ',0,NULL,10,0,1451449401980488704),(1652406974442112000,'6923644264116','早餐奶（核桃味）','B',1411048922595459072,1411048922595459072,'1','2023-03-30 21:17:31','2023-03-30 21:17:41',1652406974442112000,'http://res-showapi.oss-cn-hangzhou.aliyuncs.com/barcode/20200310/9ed31dfd-108c-41b7-8c74-436425d2d681.jpg',' ',' ',NULL,'其他','是',NULL,NULL,NULL,' ',' ',0,NULL,0,0,1451449401980488704);

/*Table structure for table `tk_ozwe_goodlist_l` */

DROP TABLE IF EXISTS `tk_ozwe_goodlist_l`;

CREATE TABLE `tk_ozwe_goodlist_l` (
  `FPKID` varchar(36) NOT NULL,
  `FID` bigint(20) NOT NULL,
  `FLocaleID` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FPKID`),
  KEY `idx__ozwe_goodlist_L_0` (`FID`,`FLocaleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_goodlist_l` */

insert  into `tk_ozwe_goodlist_l`(`FPKID`,`FID`,`FLocaleID`,`fname`) values ('2ZYSUQ6XNKUL',1587132273642177536,'zh_CN','DT15120（箱装）心相印茶语丝享系列120抽18包装三层塑装面巾纸（电商）'),('2ZZD5SJHWI=6',1587257162122396672,'zh_CN','农夫山泉 饮用天然水550ml'),('2ZZD78N5P1E+',1587257363029557248,'zh_CN','雀巢咖啡1+2原味即溶咖啡饮品'),('2ZZD8D1O4U+W',1587257519342878720,'zh_CN','优酸乳乳饮料――原味'),('3+3T0IPOC9RF',1588594443458446336,'zh_CN','农夫山泉 饮用天然水550ml'),('3+3TOUR27DH9',1588597916786753536,'zh_CN','农夫山泉 饮用天然水550ml'),('3+3TPRG4092L',1588598042464879616,'zh_CN','雀巢咖啡1+2原味即溶咖啡饮品'),('3+45CYN0P7I4',1588671211854889984,'zh_CN','C3624心相印茶语丝享系列24包装四层纸手帕（超迷你）'),('3+=+HY=M8LIN',1589886802892687360,'zh_CN','好粥道(莲子玉米粥)'),('3+=+NVV7+53D',1589887617678182400,'zh_CN','丹麦吐司'),('3+=+RHWK936Q',1589888117463058432,'zh_CN','川肉王火腿肠'),('3+=+T8E66Y4M',1589888355078769664,'zh_CN','康师傅绿茶（蜂蜜茉莉味）'),('3+=/G86KVP7/',1589891921898114048,'zh_CN','布绒娃娃水彩笔'),('3+=/G86KVP70',1589891921898114048,'zh_TW','布絨娃娃水彩筆'),('313OHVS/449K',1612991417972424704,'zh_CN','奥利奥夹心草莓味233g'),('33VY9H5PKEXM',1635377586223711232,'zh_CN','雀巢奶香威化巧克力(代可可脂)20g'),('35ZI4BS=FXZB',1652406974442112000,'zh_CN','早餐奶（核桃味）');

/*Table structure for table `tk_ozwe_goodlist_r3` */

DROP TABLE IF EXISTS `tk_ozwe_goodlist_r3`;

CREATE TABLE `tk_ozwe_goodlist_r3` (
  `FID` bigint(20) NOT NULL DEFAULT '0',
  `FRefStatus` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_goodlist_r3` */

/*Table structure for table `tk_ozwe_information` */

DROP TABLE IF EXISTS `tk_ozwe_information`;

CREATE TABLE `tk_ozwe_information` (
  `FID` bigint(20) NOT NULL,
  `FEntryId` bigint(20) NOT NULL,
  `FSeq` int(11) NOT NULL DEFAULT '0',
  `fk_ozwe_textfield` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_category` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_singleamount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_other` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_other_tag` longtext,
  `fk_ozwe_company` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_size` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_kcsituation` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_inputsituation` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_outputsituation` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FEntryId`),
  KEY `idx__ozwe_information_fk` (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_information` */

insert  into `tk_ozwe_information`(`FID`,`FEntryId`,`FSeq`,`fk_ozwe_textfield`,`fk_ozwe_category`,`fk_ozwe_singleamount`,`fk_ozwe_other`,`fk_ozwe_other_tag`,`fk_ozwe_company`,`fk_ozwe_size`,`fk_ozwe_kcsituation`,`fk_ozwe_inputsituation`,`fk_ozwe_outputsituation`) values (1587132273642177536,1587132273650566144,1,' ',' ','10.0000000000','无',NULL,'福建恒安集团有限公司','3包*6提 小包','正常','正常','正常'),(1587257519342878720,1587257519342878721,1,' ',' ','3.0000000000',' ',NULL,'内蒙古伊利实业集团股份有限公司','250ml','正常','正常','正常'),(1588671211854889984,1588671211854889985,1,' ',' ','10.0000000000',' ',NULL,'福建恒安集团有限公司','24包*28条（6片/包） 小包','正常','正常','正常'),(1589886802892687360,1589886802901075968,1,' ',' ','10.0000000000',' ',NULL,'厦门银鹭食品集团有限公司','280g','正常','正常','正常'),(1589887617678182400,1589887617678182401,1,' ',' ','1.5000000000',' ',NULL,'重庆市乐贝鲜食品有限公司','270克','正常','正常','正常'),(1589888117463058432,1589888117463058433,1,' ',' ','10.0000000000',' ',NULL,'成都希望食品有限公司','50g*10支','正常','正常','正常'),(1589888355078769664,1589888355078769665,1,' ',' ','3.0000000000',' ',NULL,'重庆顶津食品有限公司','500ml','正常','正常','正常'),(1589891921898114048,1589891922309155840,1,' ',' ','20.0000000000',' ',NULL,'温州市爱好笔业有限公司','5.8克','正常','正常','正常'),(1612991417972424704,1612991417980813312,1,' ',' ','15.0000000000',' ',NULL,'亿滋食品企业管理(上海)有限公司','233g','正常','正常','正常'),(1635377586223711232,1635377586274042880,1,' ',' ','1.5000000000',' ',NULL,'雀巢(中国)有限公司','20g','正常','正常','正常'),(1652406974442112000,1652406974601495552,1,' ',' ','5.0000000000',' ',NULL,'内蒙古蒙牛乳业(集团)股份有限公司','250ml','正常','正常','正常');

/*Table structure for table `tk_ozwe_invitationinfo` */

DROP TABLE IF EXISTS `tk_ozwe_invitationinfo`;

CREATE TABLE `tk_ozwe_invitationinfo` (
  `FID` bigint(20) NOT NULL,
  `fnumber` varchar(200) NOT NULL DEFAULT ' ',
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  `fstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fenable` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatetime` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fmasterid` bigint(20) DEFAULT NULL,
  `fk_ozwe_invitedowner` varchar(200) NOT NULL DEFAULT ' ',
  `fk_ozwe_createdate` datetime DEFAULT NULL,
  `fk_ozwe_amount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_profitno` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_invitationinfo` */

insert  into `tk_ozwe_invitationinfo`(`FID`,`fnumber`,`fname`,`fstatus`,`fcreatorid`,`fmodifierid`,`fenable`,`fcreatetime`,`fmodifytime`,`fmasterid`,`fk_ozwe_invitedowner`,`fk_ozwe_createdate`,`fk_ozwe_amount`,`fk_ozwe_profitno`) values (1640586870897246208,'3513bed163f8722d000c72d86de8af7f',' ','B',1411048922595459072,1411048922595459072,'1','2023-03-14 13:52:49','2023-03-14 13:53:16',1640586870897246208,'987a453764096f42039712c1174f6bff','2023-03-14 13:52:49','8.8800000000','103952138154'),(1640587112950531072,'3513bed163f8722d000c72d86de8af7f',' ','B',1411048922595459072,1411048922595459072,'1','2023-03-14 13:53:18','2023-03-14 13:53:44',1640587112950531072,'987a453764096f42039712c1174f6bff','2023-03-14 13:53:18','4.3200000000','106561561031');

/*Table structure for table `tk_ozwe_invitationinfo_l` */

DROP TABLE IF EXISTS `tk_ozwe_invitationinfo_l`;

CREATE TABLE `tk_ozwe_invitationinfo_l` (
  `FPKID` varchar(36) NOT NULL,
  `FID` bigint(20) NOT NULL,
  `FLocaleID` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FPKID`),
  KEY `idx__ozwe_invitationinfo_L_0` (`FID`,`FLocaleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_invitationinfo_l` */

/*Table structure for table `tk_ozwe_invitationinfo_r3` */

DROP TABLE IF EXISTS `tk_ozwe_invitationinfo_r3`;

CREATE TABLE `tk_ozwe_invitationinfo_r3` (
  `FID` bigint(20) NOT NULL DEFAULT '0',
  `FRefStatus` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_invitationinfo_r3` */

/*Table structure for table `tk_ozwe_machine` */

DROP TABLE IF EXISTS `tk_ozwe_machine`;

CREATE TABLE `tk_ozwe_machine` (
  `FID` bigint(20) NOT NULL,
  `fnumber` varchar(30) NOT NULL DEFAULT ' ',
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  `fstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fenable` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatetime` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fmasterid` bigint(20) DEFAULT NULL,
  `fk_ozwe_machinetype` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_ownerid` varchar(200) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_machine` */

insert  into `tk_ozwe_machine`(`FID`,`fnumber`,`fname`,`fstatus`,`fcreatorid`,`fmodifierid`,`fenable`,`fcreatetime`,`fmodifytime`,`fmasterid`,`fk_ozwe_machinetype`,`fk_ozwe_ownerid`) values (1608712078812512256,'Advertisement-Machine-01','广告机01','A',1411048922595459072,1411048922595459072,'1','2023-01-29 14:23:04','2023-03-26 14:38:24',1608712078812512256,'广告机','3513bed163f8722d000c72d86de8af7f'),(1629938195086967808,'Commodity-Machine-01','售卖机01','B',1411048922595459072,1411048922595459072,'1','2023-02-27 21:14:47','2023-02-27 21:16:14',1629938195086967808,'售卖机','3513bed163f8722d000c72d86de8af7f');

/*Table structure for table `tk_ozwe_machine_l` */

DROP TABLE IF EXISTS `tk_ozwe_machine_l`;

CREATE TABLE `tk_ozwe_machine_l` (
  `FPKID` varchar(36) NOT NULL,
  `FID` bigint(20) NOT NULL,
  `FLocaleID` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FPKID`),
  KEY `idx__ozwe_machine_L_0` (`FID`,`FLocaleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_machine_l` */

insert  into `tk_ozwe_machine_l`(`FPKID`,`FID`,`FLocaleID`,`fname`) values ('30L5YDPW456M',1608712078812512256,'zh_CN','广告机01'),('30L5YDPW456N',1608712078812512256,'zh_TW','廣告機01'),('336VYQYM1+LI',1629938195086967808,'zh_CN','售卖机01'),('336VYQYM1+LJ',1629938195086967808,'zh_TW','售賣機01');

/*Table structure for table `tk_ozwe_machine_r3` */

DROP TABLE IF EXISTS `tk_ozwe_machine_r3`;

CREATE TABLE `tk_ozwe_machine_r3` (
  `FID` bigint(20) NOT NULL DEFAULT '0',
  `FRefStatus` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_machine_r3` */

/*Table structure for table `tk_ozwe_order1` */

DROP TABLE IF EXISTS `tk_ozwe_order1`;

CREATE TABLE `tk_ozwe_order1` (
  `FId` bigint(20) NOT NULL,
  `fbillno` varchar(30) NOT NULL DEFAULT ' ',
  `fbillstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fauditorid` bigint(20) DEFAULT NULL,
  `fauditdate` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fcreatetime` datetime DEFAULT NULL,
  `fk_ozwe_goodname` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_goodcategory` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_datetime` datetime DEFAULT NULL,
  `fk_ozwe_billstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_pay` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_org` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_order1` */

insert  into `tk_ozwe_order1`(`FId`,`fbillno`,`fbillstatus`,`fcreatorid`,`fmodifierid`,`fauditorid`,`fauditdate`,`fmodifytime`,`fcreatetime`,`fk_ozwe_goodname`,`fk_ozwe_goodcategory`,`fk_ozwe_datetime`,`fk_ozwe_billstatus`,`fk_ozwe_pay`,`fk_ozwe_org`) values (1613065115702132736,'8531023754106845','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-02-04 14:29:37','2023-02-04 14:32:30','2023-02-04 14:32:26','醉倾梦',' ','2023-02-04 12:07:19','A','15.0000000000',1613048174706950144),(1613065203950289920,'1368041402759334','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-02-04 14:29:55','2023-02-04 14:32:38','2023-02-04 14:32:37','醉倾梦',' ','2023-01-17 06:46:02','A','3.0000000000',1613049374915428352),(1613065318991660032,'1783597003476595','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-02-04 14:32:05','2023-02-04 14:32:52','2023-02-04 14:32:51','醉倾梦',' ','2023-02-03 07:21:45','A','6.0000000000',1613049374915428352),(1613131604815774720,'7391612283730380','B',1411048922595459072,1411048922595459072,0,NULL,'2023-02-04 20:49:55','2023-02-04 16:44:33','醉倾梦',' ','2023-01-17 12:25:34','A','63.0000000000',1613048174706950144),(1635024940845498368,'6413857100062383','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-06 21:36:38','2023-03-06 21:42:44','2023-03-06 21:42:42','醉倾梦',' ','2023-03-06 09:34:53','A','3.0000000000',1613048174706950144),(1635030068650574848,'5535702477387078','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-06 21:52:32','2023-03-06 21:52:55','2023-03-06 21:52:53','醉倾梦',' ','2023-03-06 09:51:36','A','6.0000000000',1613048174706950144),(1635469880692048896,'9665469586492652','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-07 12:25:48','2023-03-07 12:26:45','2023-03-07 12:26:43','醉倾梦',' ','2023-03-07 12:25:11','A','18.0000000000',1613048174706950144),(1635476896714063872,'6677871495611518','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-07 12:40:16','2023-03-07 12:40:42','2023-03-07 12:40:39','醉倾梦',' ','2023-03-07 12:39:37','A','6.0000000000',1613048174706950144),(1635528553552413696,'8012583860422599','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-07 14:22:36','2023-03-07 14:23:21','2023-03-07 14:23:17','醉倾梦',' ','2023-03-07 02:21:53','A','18.0000000000',1613048174706950144),(1637178977976057856,'7467046007614375','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-09 21:01:11','2023-03-09 21:02:25','2023-03-09 21:02:23','微信用户',' ','2023-03-09 08:59:51','A','69.0000000000',1613048174706950144),(1639241567896076288,'4596298458119239','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-12 17:19:23','2023-03-12 17:20:25','2023-03-12 17:20:23','别来无恙',' ','2023-03-12 05:18:41','A','28.0000000000',1613048174706950144),(1639356700601352192,'7878816608556917','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-12 21:08:40','2023-03-12 21:09:09','2023-03-12 21:09:08','陈龙爹',' ','2023-03-12 09:07:57','A','3.0000000000',1613048174706950144),(1647195106253472768,'5945577680783620','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-23 16:42:34','2023-03-23 16:42:41','2023-03-23 16:42:38','汤姆猫',' ','2023-03-23 04:41:44','A','28.0000000000',1613048174706950144),(1647209678070875136,'2201320651694736','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-23 17:11:33','2023-03-23 17:11:39','2023-03-23 17:11:35','Yyang',' ','2023-03-23 05:11:00','A','33.0000000000',1613048174706950144),(1651367668088832000,'2474414638467344','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-29 10:52:25','2023-03-29 10:52:50','2023-03-29 10:52:46','醉倾梦',' ','2023-03-29 10:48:17','A','23.0000000000',1613048174706950144),(1652405977883869184,'8331510102006748','B',1411048922595459072,1411048922595459072,1411048922595459072,'2023-03-30 21:14:30','2023-03-30 21:15:45','2023-03-30 21:15:43','醉倾梦',' ','2023-03-30 09:12:59','A','50.0000000000',1613049374915428352);

/*Table structure for table `tk_ozwe_order1_lk` */

DROP TABLE IF EXISTS `tk_ozwe_order1_lk`;

CREATE TABLE `tk_ozwe_order1_lk` (
  `FId` bigint(20) NOT NULL,
  `FPKId` bigint(20) NOT NULL,
  `FSeq` int(11) NOT NULL DEFAULT '0',
  `FSTableId` bigint(20) DEFAULT NULL,
  `FSBillId` bigint(20) DEFAULT NULL,
  `FSId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`FPKId`),
  KEY `idx__ozwe_order1_lk_fk` (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_order1_lk` */

/*Table structure for table `tk_ozwe_order1_tc` */

DROP TABLE IF EXISTS `tk_ozwe_order1_tc`;

CREATE TABLE `tk_ozwe_order1_tc` (
  `FId` bigint(20) NOT NULL,
  `FTBillId` bigint(20) DEFAULT NULL,
  `FTTableId` bigint(20) DEFAULT NULL,
  `FTId` bigint(20) DEFAULT NULL,
  `FSBillId` bigint(20) DEFAULT NULL,
  `FSTableId` bigint(20) DEFAULT NULL,
  `FSId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`FId`),
  KEY `IDX_TK_OZWE_ORDER1_TC_TBILL` (`FTBillId`),
  KEY `IDX_TK_OZWE_ORDER1_TC_TID` (`FTId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_order1_tc` */

insert  into `tk_ozwe_order1_tc`(`FId`,`FTBillId`,`FTTableId`,`FTId`,`FSBillId`,`FSTableId`,`FSId`) values (1612993836357779456,1612993813733704704,1591573212456552448,1612993833639870464,26111924503226260,1585641157122065408,2696349087942642176),(1613065141606155264,1613065115702132736,1591573212456552448,1613065141572600832,1612993251000714240,1585641157122065408,1612993251009101824),(1613065212850601984,1613065203950289920,1591573212456552448,1613065212825436160,86519296889708432,1585641157122065408,7256440810028233728),(1613065329653579776,1613065318991660032,1591573212456552448,1613065329636802560,26111924503226260,1585641157122065408,2696349087942642176),(1613131643084603392,1613131604815774720,1591573212456552448,1613131643067826178,75766919771773456,1585641157122065408,3157289462960538624),(1613131643084603393,1613131604815774720,1591573212456552448,1613131643067826176,75766919771773456,1585641157122065408,8660463047884608512),(1613131643084604416,1613131604815774720,1591573212456552448,1613131643067826177,75766919771773456,1585641157122065408,4044279256857951232),(1635024963545072640,1635024940845498368,1591573212456552448,1635024961657635840,30207827581508340,1585641157122065408,131052598104074432),(1635030087155843072,1635030068650574848,1591573212456552448,1635030087113900032,4288901919345556,1585641157122065408,4942751381071931392),(1635469897049833472,1635469880692048896,1591573212456552448,1635469895950925825,84141455392857616,1585641157122065408,2783650359101953536),(1635469897049834496,1635469880692048896,1591573212456552448,1635469895950925824,84141455392857616,1585641157122065408,9223372036854775807),(1635476918608331776,1635476896714063872,1591573212456552448,1635476918599943168,28774838400226064,1585641157122065408,3141945524010541056),(1635528590596505600,1635528553552413696,1591573212456552448,1635528590437122049,30696676848168168,1585641157122065408,4125101897314165248),(1635528590596506624,1635528553552413696,1591573212456552448,1635528590437122048,30696676848168168,1585641157122065408,1250777179289046528),(1637178997513125888,1637178977976057856,1591573212456552448,1637178995818628097,36017287974269620,1585641157122065408,3284157458384456704),(1637178997513126912,1637178977976057856,1591573212456552448,1637178995818628096,36017287974269620,1585641157122065408,5749134357268484096),(1639241585923194880,1639241567896076288,1591573212456552448,1639241584488743936,14677186506945828,1585641157122065408,2183769071637002752),(1639241585923195904,1639241567896076288,1591573212456552448,1639241584488743938,14677186506945828,1585641157122065408,8901669330290535424),(1639241585923195905,1639241567896076288,1591573212456552448,1639241584488743937,14677186506945828,1585641157122065408,3277873734971837952),(1639356710642516992,1639356700601352192,1591573212456552448,1639356710592185344,36439057386434928,1585641157122065408,978346043295592576),(1647195130202947584,1647195106253472768,1591573212456552448,1647195125622767617,4841141569137553,1585641157122065408,78148280611703920),(1647195130202947585,1647195106253472768,1591573212456552448,1647195125622767616,4841141569137553,1585641157122065408,57452572147736560),(1647195130202948608,1647195106253472768,1591573212456552448,1647195125622767618,4841141569137553,1585641157122065408,35188827612784768),(1647209705635840000,1647209678070875136,1591573212456552448,1647209705493233664,75990715716171072,1585641157122065408,36642315852271432),(1647209705635841024,1647209678070875136,1591573212456552448,1647209705493233665,75990715716171072,1585641157122065408,48380946342768080),(1651367694169014272,1651367668088832000,1591573212456552448,1651367691862148096,23730660128583736,1585641157122065408,9384662168114722),(1651367694169015296,1651367668088832000,1591573212456552448,1651367691862148097,23730660128583736,1585641157122065408,1978261789900737),(1652405995248287744,1652405977883869184,1591573212456552448,1652405993000141824,29024046415432416,1585641157122065408,10294941290803906),(1652405995248288768,1652405977883869184,1591573212456552448,1652405993000141825,29024046415432416,1585641157122065408,38707948031603232);

/*Table structure for table `tk_ozwe_order1_wb` */

DROP TABLE IF EXISTS `tk_ozwe_order1_wb`;

CREATE TABLE `tk_ozwe_order1_wb` (
  `FId` bigint(20) NOT NULL,
  `FEntryId` bigint(20) NOT NULL,
  `FSeq` int(11) NOT NULL DEFAULT '0',
  `FRuleVerId` bigint(20) DEFAULT NULL,
  `FRuleItemId` bigint(20) DEFAULT NULL,
  `FOperate` varchar(50) NOT NULL DEFAULT ' ',
  `FSTableId` bigint(20) DEFAULT NULL,
  `FSId` bigint(20) DEFAULT NULL,
  `FSBillId` bigint(20) DEFAULT NULL,
  `FWriteValue` decimal(23,10) DEFAULT NULL,
  PRIMARY KEY (`FEntryId`),
  KEY `idx__ozwe_order1_wb_fk` (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_order1_wb` */

/*Table structure for table `tk_ozwe_profit` */

DROP TABLE IF EXISTS `tk_ozwe_profit`;

CREATE TABLE `tk_ozwe_profit` (
  `FId` bigint(20) DEFAULT NULL,
  `fbillno` varchar(30) DEFAULT ' ',
  `fbillstatus` varchar(50) DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fauditorid` bigint(20) DEFAULT NULL,
  `fauditdate` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fcreatetime` datetime DEFAULT NULL,
  `fk_ozwe_ownerid` varchar(50) DEFAULT ' ',
  `fk_ozwe_playtime` bigint(20) DEFAULT NULL,
  `fk_ozwe_allamount` decimal(23,10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_profit` */

insert  into `tk_ozwe_profit`(`FId`,`fbillno`,`fbillstatus`,`fcreatorid`,`fmodifierid`,`fauditorid`,`fauditdate`,`fmodifytime`,`fcreatetime`,`fk_ozwe_ownerid`,`fk_ozwe_playtime`,`fk_ozwe_allamount`) values (1622659952214541312,'35741896002357','C',1411048922595459072,1411048922595459072,0,NULL,'2023-03-03 19:56:00','2023-03-24 20:13:42','3513bed163f8722d000c72d86de8af7f',158,'7.9000000000'),(1622674756631265280,'94286758660185','C',1411048922595459072,1411048922595459072,0,NULL,'2023-03-15 10:56:00','2023-03-26 20:43:52','3513bed163f8722d000c72d86de8af7f',325,'9.8500000000'),(358755708106596416,'54644739703106','C',NULL,NULL,NULL,NULL,NULL,'2023-03-28 21:14:52','3513bed163f8722d000c72d86de8af7f',7,'0.3200000000'),(51682757552016792,'22502324012372','C',NULL,NULL,NULL,NULL,NULL,'2023-03-29 12:30:38','3513bed163f8722d000c72d86de8af7f',10,'0.3800000000'),(500929065339663744,'10132018869555','B',NULL,NULL,NULL,NULL,NULL,'2023-03-30 21:25:11','3513bed163f8722d000c72d86de8af7f',3,'0.1200000000'),(376113014407732288,'77571481082945','B',NULL,NULL,NULL,NULL,NULL,'2023-04-01 14:39:01','3513bed163f8722d000c72d86de8af7f',6,'0.2400000000');

/*Table structure for table `tk_ozwe_profit_entry` */

DROP TABLE IF EXISTS `tk_ozwe_profit_entry`;

CREATE TABLE `tk_ozwe_profit_entry` (
  `FId` bigint(20) DEFAULT NULL,
  `FEntryId` bigint(20) DEFAULT NULL,
  `FSeq` int(11) DEFAULT '0',
  `fk_ozwe_advertisementid` varchar(50) DEFAULT ' ',
  `fk_ozwe_adname` varchar(50) DEFAULT ' ',
  `fk_ozwe_adtime` bigint(20) DEFAULT NULL,
  `fk_ozwe_adamount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_amountfield` decimal(23,10) DEFAULT NULL,
  KEY `idx__ozwe_profit_entry_fk` (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_profit_entry` */

insert  into `tk_ozwe_profit_entry`(`FId`,`FEntryId`,`FSeq`,`fk_ozwe_advertisementid`,`fk_ozwe_adname`,`fk_ozwe_adtime`,`fk_ozwe_adamount`,`fk_ozwe_amountfield`) values (1622659952214541312,1622659952214540288,1,'1545615223418968','下饭吕布',158,'0.0500000000','7.9000000000'),(1622674756631265280,1622674756631266304,1,'5301593780345026','下饭姜子牙',160,'0.0200000000','3.2000000000'),(1622674756631265280,1622677502650482688,2,'5648902315686','带给全家营养健康的美好时光海苔',160,'0.0400000000','6.4000000000'),(1622674756631265280,1622677502650482689,3,'1545615223418968','下饭吕布',5,'0.0500000000','0.2500000000'),(358755708106596416,3733249026615308,64135,'1545615223418968','下饭吕布',6,'0.0500000000','0.3000000000'),(358755708106596416,7177272269622252,83451,'5301593780345026','下饭姜子牙',1,'0.0200000000','0.0200000000'),(51682757552016792,8615731712694209,61468,'1545615223418968','美好时光海苔',5,'0.0500000000','0.2500000000'),(51682757552016792,4171005464787082,53757,'5301593780345026','太极急支糖浆-加强版',4,'0.0200000000','0.0800000000'),(500929065339663744,2141891288719906,63893,'1545615223418968','美好时光海苔',1,'0.0500000000','0.0500000000'),(500929065339663744,7147528769911317,4993,'5301593780345026','太极急支糖浆-加强版',1,'0.0200000000','0.0200000000'),(376113014407732288,9835042999863532,33119,'1545615223418968','美好时光海苔',3,'0.0500000000','0.1500000000'),(376113014407732288,6799022970302541,87104,'5301593780345026','太极急支糖浆-加强版',2,'0.0200000000','0.0400000000');

/*Table structure for table `tk_ozwe_robot` */

DROP TABLE IF EXISTS `tk_ozwe_robot`;

CREATE TABLE `tk_ozwe_robot` (
  `FID` bigint(20) NOT NULL,
  `fnumber` varchar(30) NOT NULL DEFAULT ' ',
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  `fstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fenable` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatetime` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fmasterid` bigint(20) DEFAULT NULL,
  `fk_ozwe_engine` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_createdate` datetime DEFAULT NULL,
  `fk_ozwe_checkbox` char(1) NOT NULL DEFAULT '0',
  `fk_ozwe_reply` varchar(255) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_robot` */

insert  into `tk_ozwe_robot`(`FID`,`fnumber`,`fname`,`fstatus`,`fcreatorid`,`fmodifierid`,`fenable`,`fcreatetime`,`fmodifytime`,`fmasterid`,`fk_ozwe_engine`,`fk_ozwe_createdate`,`fk_ozwe_checkbox`,`fk_ozwe_reply`) values (1619800844813729792,'CustomerService-01','小买','A',1411048922595459072,1411048922595459072,'1','2023-02-13 21:22:55','2023-02-14 20:35:36',1619800844813729792,'ChatGPT','2023-02-13 21:22:55','1','您好，我是随手买自助客服：%name%，如果您遇到的是以下问题，直接点击即可以提问，如果以下问题不在您的问题范围内，您可以输入您的问题发送，我将根据您的问题进行回复。');

/*Table structure for table `tk_ozwe_robot_entry` */

DROP TABLE IF EXISTS `tk_ozwe_robot_entry`;

CREATE TABLE `tk_ozwe_robot_entry` (
  `FID` bigint(20) NOT NULL,
  `FEntryId` bigint(20) NOT NULL,
  `FSeq` int(11) NOT NULL DEFAULT '0',
  `fk_ozwe_keyword` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_rule` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_result` varchar(255) NOT NULL DEFAULT ' ',
  `fk_ozwe_result_tag` longtext,
  PRIMARY KEY (`FEntryId`),
  KEY `idx__ozwe_robot_entry_fk` (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_robot_entry` */

insert  into `tk_ozwe_robot_entry`(`FID`,`FEntryId`,`FSeq`,`fk_ozwe_keyword`,`fk_ozwe_rule`,`fk_ozwe_result`,`fk_ozwe_result_tag`) values (1619800844813729792,1619800845317046272,1,'取货,进货,申请','包含','车主选择并且付款成功提交了进货商品申请之后，需要等待场站管理员审核。场站管理员审核完成之后，车主需要前往提交申请的场站，向场站管理员出示申请二维码，场站管理员需要扫描二维码，生成进货订单，生成成功之后即可交易完成，场站管理员可以向车主打印小票。','车主选择并且付款成功提交了进货商品申请之后，需要等待场站管理员审核。场站管理员审核完成之后，车主需要前往提交申请的场站，向场站管理员出示申请二维码，场站管理员需要扫描二维码，生成进货订单，生成成功之后即可交易完成，场站管理员可以向车主打印小票。'),(1619800844813729792,1619821062357255168,2,'营业时间','包含','各个场站的营业时间有所不同，具体每个场站的营业时间请前往“进货”页面进行查看',NULL),(1619800844813729792,1619824647186939904,3,'缺货','包含','如果您有遇到缺货情况，您可以选择更换其他场站看看，如果同样也缺货可以联系场站管理员进行询问',NULL),(1619800844813729792,1619824647186939905,4,'收益','包含','车主可以利用广告赚取收益，每个广告赚取的收益是不一样的。当广告机前方有乘客的时候才会记录收益，若广告机前方所在位置没有感应到乘客则不会为车主记录收益。',NULL),(1619800844813729792,1619826702630781952,5,'扫一扫,扫码,扫描','包含','车主可以使用扫一扫功能扫码二维码。车主端的扫一扫功能仅能扫描与车主操作相关的二维码，如车主可以扫描硬件的绑定二维码进行设备绑定。',NULL),(1619800844813729792,1619831940167238656,6,'打印订单','包含','场站支持订单的打印，如要打印进货订单，请联系场站管理员协助打印。',NULL);

/*Table structure for table `tk_ozwe_robot_l` */

DROP TABLE IF EXISTS `tk_ozwe_robot_l`;

CREATE TABLE `tk_ozwe_robot_l` (
  `FPKID` varchar(36) NOT NULL,
  `FID` bigint(20) NOT NULL,
  `FLocaleID` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FPKID`),
  KEY `idx__ozwe_robot_L_0` (`FID`,`FLocaleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_robot_l` */

insert  into `tk_ozwe_robot_l`(`FPKID`,`FID`,`FLocaleID`,`fname`) values ('31Z=U0TPIS2U',1619800844813729792,'zh_CN','小买'),('31Z=U0TPIS2V',1619800844813729792,'zh_TW','小買');

/*Table structure for table `tk_ozwe_robot_qentry` */

DROP TABLE IF EXISTS `tk_ozwe_robot_qentry`;

CREATE TABLE `tk_ozwe_robot_qentry` (
  `FID` bigint(20) NOT NULL,
  `FEntryId` bigint(20) NOT NULL,
  `FSeq` int(11) NOT NULL DEFAULT '0',
  `fk_ozwe_question` varchar(255) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`FEntryId`),
  KEY `idx__ozwe_robot_qentry_fk` (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_robot_qentry` */

insert  into `tk_ozwe_robot_qentry`(`FID`,`FEntryId`,`FSeq`,`fk_ozwe_question`) values (1619800844813729792,1620339119215346688,1,'付款成功之后如何进行取货呢？'),(1619800844813729792,1620339119215346689,2,'可以打印订单吗？'),(1619800844813729792,1620339119215346690,3,'怎样才会记录广告收益呢？');

/*Table structure for table `tk_ozwe_robot_r3` */

DROP TABLE IF EXISTS `tk_ozwe_robot_r3`;

CREATE TABLE `tk_ozwe_robot_r3` (
  `FID` bigint(20) NOT NULL DEFAULT '0',
  `FRefStatus` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_robot_r3` */

/*Table structure for table `tk_ozwe_see` */

DROP TABLE IF EXISTS `tk_ozwe_see`;

CREATE TABLE `tk_ozwe_see` (
  `FId` bigint(20) NOT NULL,
  `fbillno` varchar(30) NOT NULL DEFAULT ' ',
  `fbillstatus` varchar(50) NOT NULL DEFAULT ' ',
  `fcreatorid` bigint(20) DEFAULT NULL,
  `fmodifierid` bigint(20) DEFAULT NULL,
  `fauditorid` bigint(20) DEFAULT NULL,
  `fauditdate` datetime DEFAULT NULL,
  `fmodifytime` datetime DEFAULT NULL,
  `fcreatetime` datetime DEFAULT NULL,
  `fk_ozwe_seebook` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_textfield1` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_category` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_author` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_seetime` bigint(20) DEFAULT NULL,
  `fk_ozwe_seeuser` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_pausetime` varchar(50) NOT NULL DEFAULT ' ',
  `fk_ozwe_second` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`FId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tk_ozwe_see` */

insert  into `tk_ozwe_see`(`FId`,`fbillno`,`fbillstatus`,`fcreatorid`,`fmodifierid`,`fauditorid`,`fauditdate`,`fmodifytime`,`fcreatetime`,`fk_ozwe_seebook`,`fk_ozwe_textfield1`,`fk_ozwe_category`,`fk_ozwe_author`,`fk_ozwe_seetime`,`fk_ozwe_seeuser`,`fk_ozwe_pausetime`,`fk_ozwe_second`) values (1481079705703548928,'0784280872','C',0,1411470698668360704,0,NULL,'2022-08-06 12:00:59','2022-08-06 12:00:59','高效大脑工作法:如何拥有超越常人的优异表现',' ','资料类','(英)艾米·布兰著',NULL,'沈峻弘','79s',79),(1481080384971081728,'3157527672','C',0,1411470698668360704,0,NULL,'2022-08-06 12:02:20','2022-08-06 12:02:20','我不',' ','文学类','大冰',NULL,'沈峻弘','25s',25),(1481165281760905216,'0256560086','C',0,1411470264490787840,0,NULL,'2022-08-06 14:51:01','2022-08-06 14:51:01','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'赵旭阳','13s',13),(1481165413755653120,'8858278164','C',0,1411470264490787840,0,NULL,'2022-08-06 14:51:17','2022-08-06 14:51:17','我不',' ','文学类','大冰',NULL,'赵旭阳','13s',13),(1481787384059136000,'0840417527','C',0,1411470698668360704,0,NULL,'2022-08-07 11:27:01','2022-08-07 11:27:01','龙族Ⅰ : 火之晨曦',' ','文学类','江南',NULL,'沈峻弘','2s',2),(1481787451218331648,'1063338881','C',0,1411470698668360704,0,NULL,'2022-08-07 11:27:09','2022-08-07 11:27:09','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','6s',6),(1481787628419288064,'4582773411','C',0,1411470698668360704,0,NULL,'2022-08-07 11:27:30','2022-08-07 11:27:30','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','大于80s',171),(1481789110820538368,'0334570618','C',0,1411470698668360704,0,NULL,'2022-08-07 11:30:27','2022-08-07 11:30:27','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','6s',6),(1481789796253698048,'4266142807','C',0,1411470698668360704,0,NULL,'2022-08-07 11:31:49','2022-08-07 11:31:49','马克思恩格斯选集',' ','政治类','韦建桦,[德]恩格斯',NULL,'沈峻弘','3s',3),(1481790172457602048,'3351508463','C',0,1411470698668360704,0,NULL,'2022-08-07 11:32:34','2022-08-07 11:32:34','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','5s',5),(1481790262249261056,'4845655347','C',0,1411470698668360704,0,NULL,'2022-08-07 11:32:44','2022-08-07 11:32:44','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','3s',3),(1481790850290681856,'8381170125','C',0,1411470698668360704,0,NULL,'2022-08-07 11:33:55','2022-08-07 11:33:55','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','53s',53),(1481791335663929344,'4276477821','C',0,1411470698668360704,0,NULL,'2022-08-07 11:34:52','2022-08-07 11:34:52','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','2s',2),(1481832485007394816,'4041760873','C',0,1411470698668360704,0,NULL,'2022-08-07 12:56:38','2022-08-07 12:56:38','模拟电子技术 : 模拟电子技术',' ','资料类','陈永强,魏金成,吴昌东 ',NULL,'沈峻弘','5s',5),(1481832543039785984,'1533433504','C',0,1411470698668360704,0,NULL,'2022-08-07 12:56:45','2022-08-07 12:56:45','三体·黑暗森林（图文版）',' ','文学类','刘慈欣著',NULL,'沈峻弘','2s',2),(1481901930165831680,'0574708360','C',0,1411470698668360704,0,NULL,'2022-08-07 15:14:36','2022-08-07 15:14:36','Mac应用大全',' ','科普类','王浩力,郭奕',NULL,'沈峻弘','3s',3),(1481901968367552512,'1707180037','C',0,1411470698668360704,0,NULL,'2022-08-07 15:14:41','2022-08-07 15:14:41','模拟电子技术 : 模拟电子技术',' ','资料类','陈永强,魏金成,吴昌东 ',NULL,'沈峻弘','3s',3),(1481902007047424000,'1185826246','C',0,1411470698668360704,0,NULL,'2022-08-07 15:14:45','2022-08-07 15:14:45','习近平谈治国理政第三卷',' ','政治类','习近平著',NULL,'沈峻弘','5s',5),(1481902060138924032,'5632226888','C',0,1411470698668360704,0,NULL,'2022-08-07 15:14:52','2022-08-07 15:14:52','三体·黑暗森林（图文版）',' ','文学类','刘慈欣著',NULL,'沈峻弘','4s',4),(1481919626035070976,'4438164018','C',0,1411470698668360704,0,NULL,'2022-08-07 15:49:46','2022-08-07 15:49:46','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'沈峻弘','3s',3),(1481919672004642816,'2057200162','C',0,1411470698668360704,0,NULL,'2022-08-07 15:49:51','2022-08-07 15:49:51','Mac应用大全',' ','科普类','王浩力,郭奕',NULL,'沈峻弘','1s',1),(1481919784831421440,'6840102835','C',0,1411470698668360704,0,NULL,'2022-08-07 15:50:05','2022-08-07 15:50:05','Mac应用大全',' ','科普类','王浩力,郭奕',NULL,'沈峻弘','大于80s',623),(1482139074939587584,'3140547042','C',0,1411048922595459072,0,NULL,'2022-08-07 23:05:46','2022-08-07 23:05:46','我不',' ','文学类','大冰',NULL,'杨颜','大于80s',195),(1482140729525405696,'8408025833','C',0,1411048922595459072,0,NULL,'2022-08-07 23:09:03','2022-08-07 23:09:03','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','2s',2),(1482195759423030272,'1334100417','C',0,1411048922595459072,0,NULL,'2022-08-08 00:58:23','2022-08-08 00:58:23','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','14s',14),(1482195891057067008,'0040033285','C',0,1411048922595459072,0,NULL,'2022-08-08 00:58:39','2022-08-08 00:58:39','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','10s',10),(1482196028487630848,'2836325727','C',0,1411048922595459072,0,NULL,'2022-08-08 00:58:56','2022-08-08 00:58:56','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','大于80s',297),(1482198544558325760,'0862056107','C',0,1411048922595459072,0,NULL,'2022-08-08 01:03:55','2022-08-08 01:03:55','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','9s',9),(1482198637738984448,'1342867642','C',0,1411048922595459072,0,NULL,'2022-08-08 01:04:07','2022-08-08 01:04:07','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','2s',2),(1482202693874222080,'3444504053','C',0,1411048922595459072,0,NULL,'2022-08-08 01:12:10','2022-08-08 01:12:10','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','2s',2),(1483622390616294400,'8273814103','C',0,1411048922595459072,0,NULL,'2022-08-10 00:12:51','2022-08-10 00:12:51','C程序设计 : 第五版',' ',' ','谭浩强',NULL,'杨颜','2s',2),(1484300279506535424,'2830712817','C',0,1411048922595459072,0,NULL,'2022-08-10 22:39:42','2022-08-10 22:39:42','马克思恩格斯选集',' ','政治类','韦建桦,[德]恩格斯',NULL,'杨颜','10s',10),(1484659495639450624,'2527477113','C',0,1411048922595459072,0,NULL,'2022-08-11 10:33:24','2022-08-11 10:33:24','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','18s',18),(1485065257306030080,'1752637878','C',0,1411048922595459072,0,NULL,'2022-08-11 23:59:34','2022-08-11 23:59:34','我不',' ','文学类','大冰',NULL,'杨颜','4s',4),(1485068004558372864,'6804057238','C',0,1411048922595459072,0,NULL,'2022-08-12 00:05:02','2022-08-12 00:05:02','我不',' ','文学类','大冰',NULL,'杨颜','3s',3),(1485068285216030720,'2032064267','C',0,1411048922595459072,0,NULL,'2022-08-12 00:05:35','2022-08-12 00:05:35','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','8s',8),(1485537470471733248,'5178308320','C',0,1411048922595459072,0,NULL,'2022-08-12 15:37:46','2022-08-12 15:37:46','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'杨颜','35s',35),(1485549257271280640,'5007707810','C',0,1411048922595459072,0,NULL,'2022-08-12 16:01:12','2022-08-12 16:01:12','龙族Ⅱ : 悼亡者之瞳',' ','文学类','江南',NULL,'杨颜','10s',10),(1485553205797454848,'0863501731','C',0,1411048922595459072,0,NULL,'2022-08-12 16:09:02','2022-08-12 16:09:02','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','27s',27),(1485661389363086336,'6381148028','C',0,1411048922595459072,0,NULL,'2022-08-12 19:43:59','2022-08-12 19:43:59','马克思恩格斯选集',' ','政治类','韦建桦,[德]恩格斯',NULL,'杨颜','28s',28),(1485749125218568192,'6312176262','C',0,1411048922595459072,0,NULL,'2022-08-12 22:38:18','2022-08-12 22:38:18','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','40s',40),(1485789812794329088,'7348210645','C',0,1411048922595459072,0,NULL,'2022-08-12 23:59:08','2022-08-12 23:59:08','淡定的人生不寂寞',' ',' ','吴文智，杨一兰　',NULL,'杨颜','1s',1),(1485789891596913664,'7888158570','C',0,1411048922595459072,0,NULL,'2022-08-12 23:59:17','2022-08-12 23:59:17','C程序设计 : 第五版',' ',' ','谭浩强',NULL,'杨颜','5s',5),(1485789995951196160,'0772021157','C',0,1411048922595459072,0,NULL,'2022-08-12 23:59:30','2022-08-12 23:59:30','C程序设计 : 第五版',' ',' ','谭浩强',NULL,'杨颜','23s',23),(1486294357383318528,'4440023701','C',0,1411048922595459072,0,NULL,'2022-08-13 16:41:34','2022-08-13 16:41:34','模拟电子技术 : 模拟电子技术',' ','资料类','陈永强,魏金成,吴昌东 ',NULL,'杨颜','31s',31),(1486304263716996096,'3678864067','C',0,1411048922595459072,0,NULL,'2022-08-13 17:01:15','2022-08-13 17:01:15','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','2s',2),(1486304346831323136,'2643373621','C',0,1411048922595459072,0,NULL,'2022-08-13 17:01:25','2022-08-13 17:01:25','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','11s',11),(1486410842743245824,'1205541364','C',0,1411048922595459072,0,NULL,'2022-08-13 20:33:01','2022-08-13 20:33:01','高效大脑工作法:如何拥有超越常人的优异表现',' ','资料类','(英)艾米·布兰著',NULL,'杨颜','34s',34),(1486418477794199552,'8222818356','C',0,1411048922595459072,0,NULL,'2022-08-13 20:48:11','2022-08-13 20:48:11','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ',' ','本书编委会',NULL,'杨颜','7s',7),(1486940669288123392,'8608386021','C',0,1411048922595459072,0,NULL,'2022-08-14 14:05:41','2022-08-14 14:05:41','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','24s',24),(1486942907041582080,'8044037316','C',0,1411048922595459072,0,NULL,'2022-08-14 14:10:08','2022-08-14 14:10:08','\"十二五\"普通高等教育本科国家级规划教材 : 高等数学',' ','教育类','本书编委会',NULL,'杨颜','0s',0),(1494187982549155840,'3357026602','C',0,1411048922595459072,0,NULL,'2022-08-24 14:04:48','2022-08-24 14:04:48','C#程序设计与实例分析',' ','科普类','郭奕,赵瑜,何建',NULL,'杨颜','0s',0),(1494188076073746432,'1073216028','C',0,1411048922595459072,0,NULL,'2022-08-24 14:04:59','2022-08-24 14:04:59','C#程序设计与实例分析',' ','科普类','郭奕,赵瑜,何建',NULL,'杨颜','0s',0),(1510181333706473472,'4603484563','C',0,1411048922595459072,0,NULL,'2022-09-15 15:40:44','2022-09-15 15:40:44','信号与系统分析(第3版面向新工科的电工电子信息基础课程系列教材)',' ','教育类','吴京;安成锦;周剑雄;邓新蒲著',NULL,'杨颜','3s',3),(1510217589681291264,'3381703187','C',0,1411048922595459072,0,NULL,'2022-09-15 16:52:46','2022-09-15 16:52:46','C#程序设计与实例分析',' ',' ','郭奕,赵瑜,何建',NULL,'杨颜','0s',0),(1510221903061581824,'4873106086','C',0,1411048922595459072,0,NULL,'2022-09-15 17:01:20','2022-09-15 17:01:20','Vue.js前端框架技术与实战:微课视频版',' ','资料类','储久良',NULL,'杨颜','0s',0),(1510274129452859392,'6347654404','C',0,1411048922595459072,0,NULL,'2022-09-15 18:45:06','2022-09-15 18:45:06','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'杨颜','0s',0),(1510400621851705344,'3843636386','C',0,1411048922595459072,0,NULL,'2022-09-15 22:56:25','2022-09-15 22:56:25','你不知道的JavaScript（中卷）',' ','资料类','Kyle Simpson',NULL,'杨颜','0s',0),(1510402315201282048,'6601284104','C',0,1411048922595459072,0,NULL,'2022-09-15 22:59:47','2022-09-15 22:59:47','Python数据分析从入门到精通',' ','资料类','张啸宇 李静',NULL,'杨颜','0s',0),(1510402399045420032,'8528751860','C',0,1411048922595459072,0,NULL,'2022-09-15 22:59:57','2022-09-15 22:59:57','C#程序设计与实例分析',' ',' ','郭奕,赵瑜,何建',NULL,'杨颜','0s',0),(1510402437406524416,'3766876130','C',0,1411048922595459072,0,NULL,'2022-09-15 23:00:02','2022-09-15 23:00:02','数据结构',' ','资料类','王红梅',NULL,'杨颜','0s',0),(1510402499532555264,'8234174407','C',0,1411048922595459072,0,NULL,'2022-09-15 23:00:09','2022-09-15 23:00:09','通信简史',' ','科普类','1',NULL,'杨颜','0s',0),(1510402543555970048,'5864033045','C',0,1411048922595459072,0,NULL,'2022-09-15 23:00:14','2022-09-15 23:00:14','数据结构简明教程（第2版）学习与上机实验指导/高等学校数据结构课程系列教材',' ','科普类','李春葆,蒋林,方颖 等',NULL,'杨颜','0s',0),(1510711140311629824,'2067754306','C',0,1451745037070108672,0,NULL,'2022-09-16 09:13:22','2022-09-16 09:13:22','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'小白鲨','0s',0),(1510719396027301888,'6345364235','C',0,1451745037070108672,0,NULL,'2022-09-16 09:29:46','2022-09-16 09:29:46','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'小白鲨','0s',0),(1510719937126073344,'8637704446','C',0,1451745037070108672,0,NULL,'2022-09-16 09:30:50','2022-09-16 09:30:50','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'小白鲨','0s',0),(1510726986350199808,'3813866714','C',0,1411470698668360704,0,NULL,'2022-09-16 09:44:51','2022-09-16 09:44:51','大话5G',' ','教育类','小火车,好多鱼',NULL,'沈峻弘','0s',0),(1510893782504046592,'2844281464','C',0,1411048922595459072,0,NULL,'2022-09-16 15:16:14','2022-09-16 15:16:14','大话5G',' ','教育类','小火车,好多鱼',NULL,'杨颜','0s',0),(1510941351573719040,'3361765813','C',0,1510929585871520768,0,NULL,'2022-09-16 16:50:45','2022-09-16 16:50:45','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'汤姆猫','0s',0),(1510941750368143360,'8841504668','C',0,1510929585871520768,0,NULL,'2022-09-16 16:51:33','2022-09-16 16:51:33','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'汤姆猫','0s',0),(1510941924498869248,'0363184081','C',0,1510929585871520768,0,NULL,'2022-09-16 16:51:53','2022-09-16 16:51:53','大话核心网（第2版）',' ','教育类','陈学梁,李丹',NULL,'汤姆猫','0s',0),(1510956378565381120,'6658637275','C',0,1510929585871520768,0,NULL,'2022-09-16 17:20:36','2022-09-16 17:20:36','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'汤姆猫','0s',0),(1510956753796204544,'6342137331','C',0,1510929585871520768,0,NULL,'2022-09-16 17:21:21','2022-09-16 17:21:21','走内涵发展道路　大力提升人才培养质量',' ','资料类',' 刘保县, 刘克剑, 唐洁主编',NULL,'汤姆猫','0s',0),(1510957497823791104,'8806281460','C',0,1510929585871520768,0,NULL,'2022-09-16 17:22:50','2022-09-16 17:22:50','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'汤姆猫','0s',0),(1510958444251712512,'3778431683','C',0,1510929585871520768,0,NULL,'2022-09-16 17:24:43','2022-09-16 17:24:43','新视野大学英语(2)-视听说教程(第3版)(智慧版)(含光盘)(华东版)',' ','教育类','郑树棠,金霞 主',NULL,'汤姆猫','0s',0),(1510960008743880704,'8525368301','C',0,1411048922595459072,0,NULL,'2022-09-16 17:27:49','2022-09-16 17:27:49','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'杨颜','0s',0),(1511089652356875264,'1206267406','C',0,1411048922595459072,0,NULL,'2022-09-16 21:45:24','2022-09-16 21:45:24','C#程序设计与实例分析',' ',' ','郭奕,赵瑜,何建',NULL,'杨颜','0s',0),(1511757521725948928,'6608517556','C',0,1411048922595459072,0,NULL,'2022-09-17 19:52:20','2022-09-17 19:52:20','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'杨颜','13s',13),(1511757698591358976,'7057681870','C',0,1411048922595459072,0,NULL,'2022-09-17 19:52:41','2022-09-17 19:52:41','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'杨颜','5s',5),(1511849425461314560,'6352810874','C',0,1411048922595459072,0,NULL,'2022-09-17 22:54:56','2022-09-17 22:54:56','新视野大学英语(2)-视听说教程(第3版)(智慧版)(含光盘)(华东版)',' ','教育类','郑树棠,金霞 主',NULL,'杨颜','0s',0),(1511854497314374656,'5514033115','C',0,1411048922595459072,0,NULL,'2022-09-17 23:05:01','2022-09-17 23:05:01','C#程序设计与实例分析',' ',' ','郭奕,赵瑜,何建',NULL,'杨颜','3s',3),(1511854582794290176,'6827016257','C',0,1411048922595459072,0,NULL,'2022-09-17 23:05:11','2022-09-17 23:05:11','C#程序设计与实例分析',' ',' ','郭奕,赵瑜,何建',NULL,'杨颜','9s',9),(1512178954453124096,'1013670658','C',0,1411048922595459072,0,NULL,'2022-09-18 09:49:39','2022-09-18 09:49:39','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'杨颜','3s',3),(1512179046987858944,'6707287185','C',0,1411048922595459072,0,NULL,'2022-09-18 09:49:50','2022-09-18 09:49:50','Python工程应用-数据分析基础与实践高等学校新工科应用型人才培养系列教材',' ','资料类','郭奕;黄永茂编著',NULL,'杨颜','7s',7),(1545649579720966144,'5533060068','C',0,1411048922595459072,0,NULL,'2022-11-03 14:09:48','2022-11-03 14:09:48','高频电子线路 : 高频电子线路',' ','资料类','曾兴雯　',NULL,'杨颜','3s',3),(1545649651703611392,'7027787628','C',0,1411048922595459072,0,NULL,'2022-11-03 14:09:57','2022-11-03 14:09:57','Python数据分析与挖掘实战',' ','资料类',' 张良均等. -- 2版',NULL,'杨颜','大于80s',219),(1555843814243958784,'1873537574','C',0,1411048922595459072,0,NULL,'2022-11-17 15:43:56','2022-11-17 15:43:56','Python数据分析与挖掘实战',' ','资料类',' 张良均等. -- 2版',NULL,'杨颜','0s',0),(1583472732505374720,'2370164215','C',0,1411048922595459072,0,NULL,'2022-12-25 18:37:39','2022-12-25 18:37:39','高频电子线路 : 高频电子线路',' ','资料类','曾兴雯　',NULL,'杨颜','0s',0),(1587058908671771648,'2716131428','C',0,1411048922595459072,0,NULL,'2022-12-30 17:22:45','2022-12-30 17:22:45','高频电子线路 : 高频电子线路',' ','资料类','曾兴雯　',NULL,'杨颜','9s',9);

/*Table structure for table `trade_info` */

DROP TABLE IF EXISTS `trade_info`;

CREATE TABLE `trade_info` (
  `buy_tradeno` varchar(1000) DEFAULT NULL,
  `owner_id` varchar(1000) DEFAULT NULL,
  `goods_id` varchar(1000) DEFAULT NULL,
  `buy_total` bigint(20) DEFAULT NULL,
  `FId` bigint(20) DEFAULT '0',
  `FEntryId` bigint(20) DEFAULT '0',
  `FSeq` int(11) DEFAULT '0',
  `fk_ozwe_picture` varchar(255) DEFAULT ' ',
  `fk_ozwe_textfield` varchar(100) DEFAULT ' ',
  `fk_ozwe_number` varchar(50) DEFAULT ' ',
  `fk_ozwe_total` bigint(20) DEFAULT NULL,
  `fk_ozwe_amount` decimal(23,10) DEFAULT NULL,
  `fk_ozwe_amountsingle` decimal(23,10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trade_info` */

insert  into `trade_info`(`buy_tradeno`,`owner_id`,`goods_id`,`buy_total`,`FId`,`FEntryId`,`FSeq`,`fk_ozwe_picture`,`fk_ozwe_textfield`,`fk_ozwe_number`,`fk_ozwe_total`,`fk_ozwe_amount`,`fk_ozwe_amountsingle`) values ('86452375103602','3513bed163f8722d000c72d86de8af7f','86257965402314',2,2867548614786254,6754261230471532684,0,' ',' ',' ',NULL,NULL,NULL),('86452375103602','3513bed163f8722d000c72d86de8af7f','95324795620123',4,2867548614786254,5012400003687402568,0,' ',' ',' ',NULL,NULL,NULL),('7894405618513','3513bed163f8722d000c72d86de8af7f','86257965402314',2,8839038746539543,530602859446592,1,' ',' ',' ',NULL,NULL,NULL),('7894405618513','3513bed163f8722d000c72d86de8af7f','95324795620123',3,8839038746539543,5763868303331500,2,' ',' ',' ',NULL,NULL,NULL),('45493727839457','3513bed163f8722d000c72d86de8af7f','86257965402314',1,92586665671922,5154236884435323,1,' ',' ',' ',NULL,NULL,NULL),('35857733556107','3513bed163f8722d000c72d86de8af7f','86257965402314',2,9493484958879090,9053205635984976,1,' ',' ',' ',NULL,NULL,NULL),('87202228941418','3513bed163f8722d000c72d86de8af7f','86257965402314',2,557490390427374,9837560499571984,1,' ',' ',' ',NULL,NULL,NULL),('66882642085309','3513bed163f8722d000c72d86de8af7f','15604189231568',1,3085266704816732,9673680362434896,1,' ',' ',' ',NULL,NULL,NULL),('66882642085309','3513bed163f8722d000c72d86de8af7f','86257965402314',1,3085266704816732,944110727683759,2,' ',' ',' ',NULL,NULL,NULL),('66297758929519','3513bed163f8722d000c72d86de8af7f','86257965402314',2,127816849037514,5019144683106347,1,' ',' ',' ',NULL,NULL,NULL),('66297758929519','3513bed163f8722d000c72d86de8af7f','95324795620123',3,127816849037514,7519418886245277,2,' ',' ',' ',NULL,NULL,NULL),('65423071474007','3513bed163f8722d000c72d86de8af7f','15604189231568',1,3790506990588879,9320222914393820,1,' ',' ',' ',NULL,NULL,NULL),('60462775250963','3513bed163f8722d000c72d86de8af7f','86257965402314',2,4269017523545852,8196434964239527,1,' ',' ',' ',NULL,NULL,NULL);

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_31873f3370af4daab79aab809b8b11b8` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_31873f3370af4daab79aab809b8b11b8` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_31873f3370af4daab79aab809b8b11b8`()
BEGIN IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = SCHEMA() AND (INDEX_NAME='IDX_PHM_EVENTDEAL_PROP_L')) THEN PREPARE stmt from 'CREATE INDEX IDX_PHM_EVENTDEAL_PROP_L ON T_PHM_EVENTDEAL_PROP_L (FID, FLOCALEID)'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_53a738b57b2f475689dea4c64185ce06` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_53a738b57b2f475689dea4c64185ce06` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_53a738b57b2f475689dea4c64185ce06`()
BEGIN IF NOT EXISTS (SELECT 1 FROM (select TABLE_NAME, case when TABLE_TYPE = 'BASE TABLE' then 'U' else 'V' end TABLE_XTYPE, TABLE_SCHEMA from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = SCHEMA()) AS KSQL_USERTABLES WHERE TABLE_SCHEMA = SCHEMA() AND (TABLE_NAME='T_TM_REQNOTE_OPTIONS')) THEN PREPARE stmt from 'CREATE TABLE T_TM_REQNOTE_OPTIONS (FID BIGINT(20) NOT NULL DEFAULT 0, FENTRYID BIGINT(20) NOT NULL DEFAULT 0, FSEQ INT NOT NULL DEFAULT 0, FDELIVERYSTARTDATE DATETIME, FDELIVERYENDDATE DATETIME, FADSTARTDATE DATETIME, FADENDDATE DATETIME, FDELIVERYDATE DATETIME, FOPTIONTYPE VARCHAR (50) NOT NULL DEFAULT '' '', FOPDIRECT VARCHAR (50) NOT NULL DEFAULT '' '', FTRADEDIRECT VARCHAR (50) NOT NULL DEFAULT '' '', FDELMODE VARCHAR (50) NOT NULL DEFAULT '' '', FEXERATE DECIMAL (23, 10) NOT NULL DEFAULT 0, FOPCURRENCYID BIGINT(20) NOT NULL DEFAULT 0, FOPFEEBOTTOM DECIMAL (23, 10) NOT NULL DEFAULT 0, FOPFEETOP DECIMAL (23, 10) NOT NULL DEFAULT 0, FINCURRENCYID BIGINT(20) NOT NULL DEFAULT 0, FOUTCURRENCYID BIGINT(20) NOT NULL DEFAULT 0, FAMTIN DECIMAL (23, 10) NOT NULL DEFAULT 0, FAMTOUT DECIMAL (23, 10) NOT NULL DEFAULT 0, FCOUNPTYID BIGINT(20) NOT NULL DEFAULT 0, FISBIZBILL CHAR (1) NOT NULL DEFAULT ''0'', FQUOTE VARCHAR (50) NOT NULL DEFAULT '' '', FADJDELIDATE DATETIME, FBIZDATE DATETIME, FBUSTYPE VARCHAR (50) NOT NULL DEFAULT '' '')'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_665a2a9e011a42e4939a2a6049563c1a` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_665a2a9e011a42e4939a2a6049563c1a` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_665a2a9e011a42e4939a2a6049563c1a`()
BEGIN IF NOT EXISTS (SELECT 1 FROM (select TABLE_NAME, case when TABLE_TYPE = 'BASE TABLE' then 'U' else 'V' end TABLE_XTYPE, TABLE_SCHEMA from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = SCHEMA()) AS KSQL_USERTABLES WHERE TABLE_SCHEMA = SCHEMA() AND (TABLE_NAME='t_occbo_kpistatistmid_r3')) THEN PREPARE stmt from 'CREATE TABLE t_occbo_kpistatistmid_r3 (FID BIGINT(20) NOT NULL DEFAULT 0, FRefStatus CHAR (1) NOT NULL DEFAULT ''0'', CONSTRAINT PK_t_occbo_kpistatistmid_r3 PRIMARY KEY (FID))'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_7ccc840faa3944bab855cfb804e720f6` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_7ccc840faa3944bab855cfb804e720f6` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_7ccc840faa3944bab855cfb804e720f6`()
BEGIN IF NOT EXISTS (SELECT 1 FROM (select TABLE_NAME, case when TABLE_TYPE = 'BASE TABLE' then 'U' else 'V' end TABLE_XTYPE, TABLE_SCHEMA from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = SCHEMA()) AS KSQL_USERTABLES WHERE TABLE_SCHEMA = SCHEMA() AND (TABLE_NAME='T_MRP_PLANPROORGENTRY')) THEN PREPARE stmt from 'CREATE TABLE T_MRP_PLANPROORGENTRY (FID BIGINT(20) NOT NULL DEFAULT 0, FENTRYID BIGINT(20) NOT NULL DEFAULT 0, FSEQ INT NOT NULL DEFAULT 0, FDEMANDORG BIGINT(20) NOT NULL DEFAULT 0, FINVSTRATEGY BIGINT(20) NOT NULL DEFAULT 0, FSUPPLYNET BIGINT(20) NOT NULL DEFAULT 0)'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_8ec6f6248d634bbb9b86683b4202ba71` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_8ec6f6248d634bbb9b86683b4202ba71` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_8ec6f6248d634bbb9b86683b4202ba71`()
BEGIN IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = SCHEMA() AND (INDEX_NAME='IDX_FINISH_COSTOBJ')) THEN PREPARE stmt from 'CREATE INDEX IDX_FINISH_COSTOBJ ON T_CAD_FACTNEDOUTPUTENTRY (FCOSTOBJECTID)'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_b49a30505c74421eab97b4769f7ceba5` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_b49a30505c74421eab97b4769f7ceba5` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_b49a30505c74421eab97b4769f7ceba5`()
BEGIN IF NOT EXISTS (SELECT 1 FROM (select TABLE_NAME, case when TABLE_TYPE = 'BASE TABLE' then 'U' else 'V' end TABLE_XTYPE, TABLE_SCHEMA from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = SCHEMA()) AS KSQL_USERTABLES WHERE TABLE_SCHEMA = SCHEMA() AND (TABLE_NAME='T_BCM_DSPISSCHEME')) THEN PREPARE stmt from 'CREATE TABLE T_BCM_DSPISSCHEME (FPKID BIGINT(20) NOT NULL DEFAULT 0, FID BIGINT(20) NOT NULL DEFAULT 0, FBASEDATAID BIGINT(20) NOT NULL DEFAULT 0)'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_c3e11e1655fa4d21bd0e4617469796f8` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_c3e11e1655fa4d21bd0e4617469796f8` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_c3e11e1655fa4d21bd0e4617469796f8`()
BEGIN IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = SCHEMA() AND ((TABLE_NAME='T_TCCIT_DEPRECIATE_QUICK' AND COLUMN_NAME='FADVANCEDCONFJSON'))) THEN PREPARE stmt from 'ALTER TABLE T_TCCIT_DEPRECIATE_QUICK ADD FADVANCEDCONFJSON LONGTEXT'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_c6092dfde31a482c89beadf98f5102ea` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_c6092dfde31a482c89beadf98f5102ea` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_c6092dfde31a482c89beadf98f5102ea`()
BEGIN IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = SCHEMA() AND ((UPPER(TABLE_NAME)='T_MPDM_MATERIALPLAN' AND COLUMN_NAME='FSOURCEBITINDEX'))) THEN PREPARE stmt from 'ALTER TABLE T_MPDM_MATERIALPLAN ADD FSOURCEBITINDEX INT NOT NULL DEFAULT 0'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `KSQL_TEMP_PROCEDURE_e30575a7fa204c2da342bd90370ffc22` */

/*!50003 DROP PROCEDURE IF EXISTS  `KSQL_TEMP_PROCEDURE_e30575a7fa204c2da342bd90370ffc22` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `KSQL_TEMP_PROCEDURE_e30575a7fa204c2da342bd90370ffc22`()
BEGIN IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = SCHEMA() AND (INDEX_NAME='IDX_PMPD_ASSPRG_FID')) THEN PREPARE stmt from 'CREATE INDEX IDX_PMPD_ASSPRG_FID ON T_PMPD_ASSPJMGORG (FID, FBASEDATAID)'; EXECUTE stmt; END IF; END */$$
DELIMITER ;

/* Procedure structure for procedure `p_AlterColumn` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_AlterColumn` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`crp_user`@`%` PROCEDURE `p_AlterColumn`(
TableName VARCHAR ( 50 ),
ColumnName VARCHAR ( 50 ),
ColumnType VARCHAR ( 50 ),
ColumnIsNull VARCHAR ( 10 ),
ModifyFlag VARCHAR ( 10 ),
DefaultValue VARCHAR ( 255 ) 
)
    SQL SECURITY INVOKER
BEGIN
DECLARE
	v_defaultvalue VARCHAR ( 500 );
DECLARE
	v_defaultsql VARCHAR ( 500 );
DECLARE
	v_ColumnType VARCHAR ( 500 );
IF
	ColumnType = 'IMAGE' THEN
	
	SET v_ColumnType = 'LONGBLOB';

ELSEIF ColumnType = 'NCLOB' THEN

SET v_ColumnType = 'LONGTEXT';

ELSEIF ColumnType = 'XMLTYPE' THEN

SET v_ColumnType = 'LONGTEXT';

ELSE 
	SET v_ColumnType = ColumnType;

END IF;

SET v_ColumnType = REPLACE(v_ColumnType, 'NVARCHAR', 'VARCHAR');

SET v_defaultsql = '';
IF
	DefaultValue IS NOT NULL 
	AND LENGTH( DefaultValue ) > 0 
	AND DefaultValue <> 'NULL' THEN
	IF
		DefaultValue = 'GETDATE()' THEN
			
			SET v_defaultvalue = ' current_timestamp ';
		ELSE
		IF
			INSTR( DefaultValue, '{ts' ) = 1 THEN
				
				SET v_defaultvalue = REPLACE ( REPLACE ( DefaultValue, '{ts', '' ), '}', '' );
			ELSE 
				SET v_defaultvalue = DefaultValue;
			
		END IF;
		
	END IF;
	
	SET v_defaultsql = concat( ' DEFAULT ', v_defaultvalue );
	
END IF;
IF
	SUBSTR( ModifyFlag, 1, 1 ) = '1' 
	AND ColumnType = 'NULL' THEN
		
		SET @ifSql = concat( 'SELECT count(1) into @existcol FROM information_schema.columns WHERE TABLE_SCHEMA = SCHEMA() AND table_name = ''', TableName, ''' AND column_name = ''', ColumnName, ''' ' );
	PREPARE stmt 
	FROM
		@ifSql;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	IF
		@existcol = 1 THEN
			
			SET @sqlcounts = concat( ' ALTER TABLE ', TableName, ' drop column ', ColumnName );
		PREPARE stmt 
		FROM
			@sqlcounts;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		
	END IF;
	ELSE
	IF
		SUBSTR( ModifyFlag, 3, 1 ) = '1' 
		AND ColumnIsNull = 'NOT NULL' 
		AND ( v_defaultvalue IS NOT NULL AND LENGTH( v_defaultvalue ) > 0 ) THEN
			
			SET @sqlcounts1 = concat( 'UPDATE ', TableName, ' SET ', ColumnName, ' = ', v_defaultvalue, ' WHERE ', ColumnName, ' IS NULL ' );
		PREPARE stmt1 
		FROM
			@sqlcounts1;
		EXECUTE stmt1;
		DEALLOCATE PREPARE stmt1;
		
	END IF;
	
	SET @sqlcounts = concat( 'ALTER TABLE ', TableName, ' modify ', ColumnName, ' ', v_ColumnType, ' ', ColumnIsNull, v_defaultsql, '' );
	PREPARE stmt 
	FROM
		@sqlcounts;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `p_AlterPK` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_AlterPK` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`crp_user`@`%` PROCEDURE `p_AlterPK`( PkName VARCHAR ( 50 ), TableName VARCHAR ( 50 ), FieldName VARCHAR ( 2000 ), IsClustered INT )
    SQL SECURITY INVOKER
BEGIN
DECLARE
	oldPkName VARCHAR ( 30 );
IF
	EXISTS ( SELECT 1 FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_SCHEMA = SCHEMA ( ) AND CONSTRAINT_TYPE = 'PRIMARY KEY' AND TABLE_NAME = TableName ) THEN
	
	SET @sqlcounts = concat( 'ALTER TABLE ', TableName, ' DROP primary key' );
PREPARE stmt 
FROM
	@sqlcounts;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

END IF;
IF
	FieldName IS NOT NULL 
	AND FieldName <> 'NULL' 
	AND FieldName <> '' THEN
		
		SET @sqlcounts = concat( 'ALTER TABLE ', TableName, ' ADD primary key(', FieldName, ')' );
	PREPARE stmt 
	FROM
		@sqlcounts;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `p_DropIdx` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_DropIdx` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`crp_user`@`%` PROCEDURE `p_DropIdx`( IdxName VARCHAR ( 50 ), TableName VARCHAR ( 50 ))
    SQL SECURITY INVOKER
BEGIN
DECLARE
 oldIdxName VARCHAR ( 30 );

IF EXISTS ( SELECT 1 FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = SCHEMA ( ) AND INDEX_NAME = IdxName) THEN

SET @sqlcounts = concat( 'DROP INDEX ', IdxName, ' ON ', TableName);
PREPARE stmt 
FROM
 @sqlcounts;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `p_ModifyObjectName` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_ModifyObjectName` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`crp_user`@`%` PROCEDURE `p_ModifyObjectName`(
TableName VARCHAR ( 50 ),
ObjectName VARCHAR ( 50 ),
NewObjectName VARCHAR ( 50 ),
ObjectType VARCHAR ( 10 ),
FieldType VARCHAR ( 50 ) 
)
    SQL SECURITY INVOKER
BEGIN
IF
	ObjectType = 'OBJECT' 
	AND EXISTS ( SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA ( ) AND table_name = ObjectName ) THEN
	
	SET @sqlcounts = concat( 'ALTER TABLE ', ObjectName, ' RENAME TO ', NewObjectName );
PREPARE stmt 
FROM
	@sqlcounts;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
ELSE
IF
	ObjectType = 'COLUMN' 
	AND EXISTS ( SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = SCHEMA ( ) AND table_name = TableName AND COLUMN_NAME = ObjectName ) THEN
		
		SET @sqlcounts = concat( 'ALTER TABLE ', TableName, ' CHANGE COLUMN ', ObjectName, ' ', NewObjectName, ' ', FieldType );
	PREPARE stmt 
	FROM
		@sqlcounts;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
END IF;

END IF;

END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
