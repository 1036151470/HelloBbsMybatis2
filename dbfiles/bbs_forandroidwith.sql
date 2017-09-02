/*
Navicat MySQL Data Transfer

Source Server         : forliu
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : forliu

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-18 01:03:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bbs_everythingtotalkwith
-- ----------------------------
DROP TABLE IF EXISTS `bbs_forandroidwith`;
CREATE TABLE `bbs_forandroidwith` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `context` text,
  `createdtime` varchar(255) DEFAULT NULL,
  `changetime` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `withc` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
