/*
Navicat MySQL Data Transfer

Source Server         : ss
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : photoshoot

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-06 17:29:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ps_collection
-- ----------------------------
DROP TABLE IF EXISTS `ps_collection`;
CREATE TABLE `ps_collection` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_userid` int(11) NOT NULL,
  `collection_tagid` int(11) DEFAULT NULL,
  `collection_photo_location` varchar(150) DEFAULT NULL,
  `collection_photoname` varchar(12) DEFAULT NULL,
  `collection_photointroduction` varchar(150) DEFAULT NULL,
  `collection_price` decimal(10,1) DEFAULT NULL,
  `collection_photoinformation` varchar(100) DEFAULT NULL,
  `collection_adress` char(12) DEFAULT NULL,
  `collection_flag` tinyint(1) NOT NULL DEFAULT '1',
  `collection_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `collection_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`collection_id`),
  KEY `collection_userid` (`collection_userid`),
  KEY `fk_ps_collection_ps_collection_1` (`collection_tagid`),
  CONSTRAINT `fk_ps_collection_ps_collection_1` FOREIGN KEY (`collection_tagid`) REFERENCES `ps_tag` (`tag_id`),
  CONSTRAINT `fk_ps_collection_ps_collection_2` FOREIGN KEY (`collection_userid`) REFERENCES `ps_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_collection
-- ----------------------------
INSERT INTO `ps_collection` VALUES ('1', '1', '5', 'images/2017/09/09/111832674.jpg', '美女', 'this is a photo', '100.0', 'used mobilephone', 'ZhuHai', '1', '2017-07-26 09:55:47', '2017-10-24 10:24:03');
INSERT INTO `ps_collection` VALUES ('2', '1', '1', 'images/2017/09/16/041818353.jpg', 'dsad', null, '111.0', null, null, '2', '2017-10-12 11:32:25', '2017-10-19 10:48:35');
INSERT INTO `ps_collection` VALUES ('3', '1', '1', 'images/2017/09/17/053255228.jpg', 'sadf', null, '111.0', null, null, '1', '2017-10-12 11:32:25', '2017-10-17 09:59:14');
INSERT INTO `ps_collection` VALUES ('4', '1', '1', 'images/2017/09/27/023814730.jpg', 'asdf', null, '111.0', null, null, '1', '2017-10-12 11:32:25', '2017-10-17 09:59:30');
INSERT INTO `ps_collection` VALUES ('14', '1', null, 'images/2017/10/19/101819680.jpg', 'sad', null, null, null, null, '1', '2017-10-19 10:18:21', '2017-10-19 10:48:45');
INSERT INTO `ps_collection` VALUES ('15', '3', null, 'images/2017/10/19/105132482.jpg', null, null, null, null, null, '1', '2017-10-19 10:51:33', '2017-10-19 10:51:33');
INSERT INTO `ps_collection` VALUES ('16', '3', null, 'images/2017/10/19/105132482.jpg', null, null, null, null, null, '1', '2017-10-19 10:51:33', '2017-10-19 10:51:33');
INSERT INTO `ps_collection` VALUES ('17', '3', null, 'images/2017/10/19/105132482.jpg', null, null, null, null, null, '1', '2017-10-19 10:51:33', '2017-10-19 10:51:33');
INSERT INTO `ps_collection` VALUES ('18', '3', null, 'images/2017/10/19/105157309.jpg', null, null, null, null, null, '1', '2017-10-19 10:51:57', '2017-10-19 10:51:57');
INSERT INTO `ps_collection` VALUES ('19', '3', null, 'images/2017/10/19/105157309.jpg', null, null, null, null, null, '1', '2017-10-19 10:51:57', '2017-10-19 10:51:57');
INSERT INTO `ps_collection` VALUES ('20', '3', null, 'images/2017/10/19/105157309.jpg', null, null, null, null, null, '1', '2017-10-19 10:51:57', '2017-10-19 10:51:57');
INSERT INTO `ps_collection` VALUES ('21', '3', null, 'images/2017/10/20/090224678.jpg', null, null, null, null, null, '1', '2017-10-20 09:03:22', '2017-10-20 09:03:22');
INSERT INTO `ps_collection` VALUES ('22', '3', null, 'images/2017/10/20/090224678.jpg', null, null, null, null, null, '1', '2017-10-20 09:03:22', '2017-10-20 09:03:22');
INSERT INTO `ps_collection` VALUES ('23', '1', null, 'images/2017/10/20/093812186.jpg', null, null, null, null, null, '1', '2017-10-20 09:44:05', '2017-10-20 09:44:05');
INSERT INTO `ps_collection` VALUES ('24', '1', null, 'images/2017/10/20/093812186.jpg', null, null, null, null, null, '1', '2017-10-20 09:44:05', '2017-10-20 09:44:05');
INSERT INTO `ps_collection` VALUES ('25', '1', null, 'images/2017/11/03/043315204.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('26', '1', null, 'images/2017/11/03/043316722.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('27', '1', null, 'images/2017/11/03/043316807.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('28', '1', null, 'images/2017/11/03/043316875.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('29', '1', null, 'images/2017/11/03/043317948.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('30', '1', null, 'images/2017/11/03/043318555.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('31', '1', null, 'images/2017/11/03/043319359.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('32', '1', null, 'images/2017/11/03/043320233.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('33', '1', null, 'images/2017/11/03/043321007.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('34', '1', null, 'images/2017/11/03/043322048.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('35', '1', null, 'images/2017/11/03/043322761.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('36', '1', null, 'images/2017/11/03/043323131.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('37', '1', null, 'images/2017/11/03/043323829.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('38', '1', null, 'images/2017/11/03/043324418.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('39', '1', null, 'images/2017/11/03/043325068.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('40', '1', null, 'images/2017/11/03/043325502.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('41', '1', null, 'images/2017/11/03/043325816.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('42', '1', null, 'images/2017/11/03/043326273.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('43', '1', null, 'images/2017/11/03/043326842.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('44', '1', null, 'images/2017/11/03/043327372.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('45', '1', null, 'images/2017/11/03/043328159.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('46', '1', null, 'images/2017/11/03/043328403.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('47', '1', null, 'images/2017/11/03/043328998.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('48', '1', null, 'images/2017/11/03/043329866.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('49', '1', null, 'images/2017/11/03/043330056.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('50', '1', null, 'images/2017/11/03/043330381.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('51', '1', null, 'images/2017/11/03/043331735.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('52', '1', null, 'images/2017/11/03/043332498.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('53', '1', null, 'images/2017/11/03/043333722.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('54', '1', null, 'images/2017/11/03/043334613.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('55', '1', null, 'images/2017/11/03/043334778.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('56', '1', null, 'images/2017/11/03/043335026.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('57', '1', null, 'images/2017/11/03/043335143.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('58', '1', null, 'images/2017/11/03/043335284.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('59', '1', null, 'images/2017/11/03/043335463.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('60', '1', null, 'images/2017/11/03/043335954.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('61', '1', null, 'images/2017/11/03/043336127.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('62', '1', null, 'images/2017/11/03/043336552.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('63', '1', null, 'images/2017/11/03/043336681.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('64', '1', null, 'images/2017/11/03/043336747.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('65', '1', null, 'images/2017/11/03/043337302.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('66', '1', null, 'images/2017/11/03/043338184.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('67', '1', null, 'images/2017/11/03/043339415.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('68', '1', null, 'images/2017/11/03/043339872.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('69', '1', null, 'images/2017/11/03/043340748.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('70', '1', null, 'images/2017/11/03/043341591.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('71', '1', null, 'images/2017/11/03/043342494.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('72', '1', null, 'images/2017/11/03/043342711.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('73', '1', null, 'images/2017/11/03/043343182.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('74', '1', null, 'images/2017/11/03/043343411.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('75', '1', null, 'images/2017/11/03/043343790.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('76', '1', null, 'images/2017/11/03/043343953.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('77', '1', null, 'images/2017/11/03/043344139.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('78', '1', null, 'images/2017/11/03/043344822.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('79', '1', null, 'images/2017/11/03/043345188.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('80', '1', null, 'images/2017/11/03/043345368.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('81', '1', null, 'images/2017/11/03/043345757.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('82', '1', null, 'images/2017/11/03/043346278.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('83', '1', null, 'images/2017/11/03/043346381.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('84', '1', null, 'images/2017/11/03/043346477.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('85', '1', null, 'images/2017/11/03/043346665.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('86', '1', null, 'images/2017/11/03/043346810.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('87', '1', null, 'images/2017/11/03/043347163.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('88', '1', null, 'images/2017/11/03/043347756.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('89', '1', null, 'images/2017/11/03/043350290.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('90', '1', null, 'images/2017/11/03/043350481.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('91', '1', null, 'images/2017/11/03/043351251.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('92', '1', null, 'images/2017/11/03/043351361.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('93', '1', null, 'images/2017/11/03/043351487.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('94', '1', null, 'images/2017/11/03/043351561.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('95', '1', null, 'images/2017/11/03/043351718.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('96', '1', null, 'images/2017/11/03/043352536.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('97', '1', null, 'images/2017/11/03/043352662.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('98', '1', null, 'images/2017/11/03/043352732.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('99', '1', null, 'images/2017/11/03/043353549.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('100', '1', null, 'images/2017/11/03/043353791.jpg', null, null, null, null, null, '1', '2017-11-03 16:36:27', '2017-11-03 16:36:27');
INSERT INTO `ps_collection` VALUES ('101', '1', null, 'images/2017/11/06/013535057.jpg', null, null, null, null, null, '1', '2017-11-06 13:35:36', '2017-11-06 13:35:36');

-- ----------------------------
-- Table structure for ps_comment
-- ----------------------------
DROP TABLE IF EXISTS `ps_comment`;
CREATE TABLE `ps_comment` (
  `comment_id` int(11) NOT NULL,
  `comment_userid` int(11) DEFAULT NULL,
  `comment_content` varchar(150) DEFAULT NULL,
  `comment_time` timestamp NULL DEFAULT NULL,
  KEY `comment_userid` (`comment_userid`),
  KEY `fk_ps_comment_ps_comment_1` (`comment_id`),
  CONSTRAINT `fk_ps_comment_ps_comment_1` FOREIGN KEY (`comment_id`) REFERENCES `ps_collection` (`collection_id`),
  CONSTRAINT `fk_ps_comment_ps_comment_2` FOREIGN KEY (`comment_userid`) REFERENCES `ps_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ps_like
-- ----------------------------
DROP TABLE IF EXISTS `ps_like`;
CREATE TABLE `ps_like` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `like_collectionid` int(11) NOT NULL,
  `like_userid` int(11) NOT NULL,
  `like_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `like_status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`like_id`),
  KEY `fk_ps_like_ps_like_1` (`like_userid`),
  KEY `fk_ps_like_ps_like_2` (`like_collectionid`),
  CONSTRAINT `fk_ps_like_ps_like_1` FOREIGN KEY (`like_userid`) REFERENCES `ps_user` (`user_id`),
  CONSTRAINT `fk_ps_like_ps_like_2` FOREIGN KEY (`like_collectionid`) REFERENCES `ps_collection` (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_like
-- ----------------------------
INSERT INTO `ps_like` VALUES ('1', '1', '1', '2017-10-16 09:56:21', '1');
INSERT INTO `ps_like` VALUES ('2', '2', '1', '2017-10-16 09:56:23', '1');
INSERT INTO `ps_like` VALUES ('3', '1', '2', '2017-10-16 09:56:25', '1');
INSERT INTO `ps_like` VALUES ('4', '4', '4', '2017-10-24 14:40:51', '1');
INSERT INTO `ps_like` VALUES ('5', '3', '2', '2017-10-24 14:56:42', '0');
INSERT INTO `ps_like` VALUES ('6', '4', '1', '2017-10-24 16:54:13', '0');
INSERT INTO `ps_like` VALUES ('16', '2', '2', '2017-10-30 14:19:01', '1');

-- ----------------------------
-- Table structure for ps_role
-- ----------------------------
DROP TABLE IF EXISTS `ps_role`;
CREATE TABLE `ps_role` (
  `role_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_name` char(50) NOT NULL DEFAULT 'ROLE_ORDIN',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_role
-- ----------------------------
INSERT INTO `ps_role` VALUES ('1', 'ROLE_ORDIN');
INSERT INTO `ps_role` VALUES ('2', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for ps_tag
-- ----------------------------
DROP TABLE IF EXISTS `ps_tag`;
CREATE TABLE `ps_tag` (
  `tag_id` int(11) NOT NULL,
  `tag_name` char(100) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_tag
-- ----------------------------
INSERT INTO `ps_tag` VALUES ('1', '风景');

-- ----------------------------
-- Table structure for ps_user
-- ----------------------------
DROP TABLE IF EXISTS `ps_user`;
CREATE TABLE `ps_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `user_name` char(12) DEFAULT NULL,
  `user_password` varchar(220) NOT NULL,
  `user_nickname` varchar(10) DEFAULT NULL,
  `user_role` int(5) NOT NULL DEFAULT '1',
  `user_sex` tinyint(1) DEFAULT '1',
  `user_age` tinyint(3) DEFAULT NULL,
  `user_introduction` varchar(220) DEFAULT NULL,
  `user_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_headphoto_location` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `ps_user_ibfk_1` (`user_id`),
  KEY `fk_ps_user_ps_user_1` (`user_role`),
  CONSTRAINT `fk_ps_user_ps_user_1` FOREIGN KEY (`user_role`) REFERENCES `ps_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_user
-- ----------------------------
INSERT INTO `ps_user` VALUES ('1', '0', 'Amy', '$2a$05$baP5xNaDvSHNOtXK35g96.Wb9c0EbyqgxlCh5bhJgYTCnHzdec4v2', 'Amy', '1', '2', '25', 'I can swim', '2017-07-26 09:47:34', '2017-10-18 10:58:51', 'headpic/2017/07/26/Amy.jpg');
INSERT INTO `ps_user` VALUES ('2', '0', 'Jean', '$2a$05$TRfx4t3VMMEVegQEQVNUB.CWxzZ8ErPMsBWeQsk.izeAnAHhkIPve', 'Jean', '1', '2', '28', 'I can swim', '2017-07-27 09:47:34', '2017-10-18 11:01:21', 'headpic/2017/07/26/Jean.jpg');
INSERT INTO `ps_user` VALUES ('3', '0', 'Tom', '$2a$05$7ZWWXf4Ox3dXK/fE.2FkqeW/Tp/TML4L4Yr1Y7lqxNJ9MWEG5CpVC', 'Tom', '1', '1', '28', 'I can swim', '2017-07-28 10:19:05', '2017-10-18 11:01:31', 'headpic/2017/07/26/Tom.jpeg');
INSERT INTO `ps_user` VALUES ('4', '0', 'Ant', '$2a$05$7ZWWXf4Ox3dXK/fE.2FkqeW/Tp/TML4L4Yr1Y7lqxNJ9MWEG5CpVC', 'Ant', '1', '1', '28', 'I can swim', '2017-07-28 10:19:05', '2017-10-18 11:01:49', 'headpic/2017/07/26/Amy.jpg');
INSERT INTO `ps_user` VALUES ('5', '0', 'Sally', '$2a$05$7ZWWXf4Ox3dXK/fE.2FkqeW/Tp/TML4L4Yr1Y7lqxNJ9MWEG5CpVC', 'Sally', '1', '1', '28', 'I can swim', '2017-07-28 10:19:05', '2017-10-18 11:02:02', 'headpic/2017/07/26/Amy.jpg');
INSERT INTO `ps_user` VALUES ('6', '0', 'Sam', '$2a$05$7ZWWXf4Ox3dXK/fE.2FkqeW/Tp/TML4L4Yr1Y7lqxNJ9MWEG5CpVC', 'Sam', '1', '1', '28', 'I can swim', '2017-07-28 10:19:05', '2017-10-18 11:02:06', 'headpic/2017/07/26/Amy.jpg');
INSERT INTO `ps_user` VALUES ('7', '0', 'Wang', '$2a$10$bSrZiYjL7nU1nVk2MVo1W.2biV50MwmX/cVOnTxhbN4a0NRtX3BLu', 'Wang', '1', '1', null, null, '2017-10-18 10:13:55', '2017-10-24 13:19:01', null);
INSERT INTO `ps_user` VALUES ('8', '0', 'Li', '$2a$10$q7YGy3lJX1v2wN8QeMM8ZeGUR.or40bEzxREau1gkeZ9mZhOseK8G', 'Li', '1', '1', null, null, '2017-10-18 10:16:37', '2017-10-24 13:19:04', null);
INSERT INTO `ps_user` VALUES ('9', '0', 'Dam', '$2a$10$xsQgNGm3wedoGry6Vh1P4eWtaBkfCfzdWECVvhZ9bsc2O3JLVHHSy', 'Dam', '1', '1', null, null, '2017-10-18 10:37:12', '2017-10-24 13:19:06', null);
INSERT INTO `ps_user` VALUES ('10', '0', 'Wu', '$2a$10$w7vextFxwqFOnoAsBchhbutunilh7HkPk8GjKsZLX1j.GpeVp9WDO', 'Wu', '1', '1', null, null, '2017-10-18 10:40:40', '2017-10-24 13:19:08', null);
INSERT INTO `ps_user` VALUES ('11', '0', 'Hanna', '$2a$10$P.Qzu9leWbcisYiLOqUY1u/spz.l9KHvuqtiBcirKJdq2Mm9wjGce', 'Hanna', '1', '1', null, null, '2017-10-18 10:42:06', '2017-10-24 13:19:10', null);
INSERT INTO `ps_user` VALUES ('12', '0', 'GuoJing', '$2a$10$z/YCpq1hySAAOvb4RogvVOq5ymNoD/sNu/QVeVVG8j9JqlNFoQv9G', 'GuoJing', '1', '1', null, null, '2017-10-18 10:45:46', '2017-10-24 13:19:13', null);

-- ----------------------------
-- Table structure for ps_watch
-- ----------------------------
DROP TABLE IF EXISTS `ps_watch`;
CREATE TABLE `ps_watch` (
  `watch_userid` int(11) NOT NULL,
  `watch_fansid` int(11) DEFAULT NULL,
  KEY `watch_fansid` (`watch_fansid`),
  KEY `fk_ps_watch_ps_watch_1` (`watch_userid`),
  CONSTRAINT `fk_ps_watch_ps_watch_1` FOREIGN KEY (`watch_userid`) REFERENCES `ps_user` (`user_id`),
  CONSTRAINT `fk_ps_watch_ps_watch_2` FOREIGN KEY (`watch_fansid`) REFERENCES `ps_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ps_watch
-- ----------------------------
INSERT INTO `ps_watch` VALUES ('1', '2');
INSERT INTO `ps_watch` VALUES ('1', '3');
INSERT INTO `ps_watch` VALUES ('2', '1');
SET FOREIGN_KEY_CHECKS=1;
