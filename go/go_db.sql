/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50020
Source Host           : localhost:3306
Source Database       : go_db

Target Server Type    : MYSQL
Target Server Version : 50020
File Encoding         : 65001

Date: 2017-06-10 18:40:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admin_inf`
-- ----------------------------
DROP TABLE IF EXISTS `admin_inf`;
CREATE TABLE `admin_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `TELNUMBER` varchar(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_inf
-- ----------------------------
INSERT INTO admin_inf VALUES ('1', '123456', '123456', null);

-- ----------------------------
-- Table structure for `cargoodlink_inf`
-- ----------------------------
DROP TABLE IF EXISTS `cargoodlink_inf`;
CREATE TABLE `cargoodlink_inf` (
  `car_ID` int(11) NOT NULL default '0',
  `good_ID` int(11) NOT NULL default '0',
  `AMOUNT` int(11) default NULL,
  PRIMARY KEY  (`car_ID`,`good_ID`),
  KEY `good_ID` (`good_ID`),
  CONSTRAINT `cargoodlink_inf_ibfk_1` FOREIGN KEY (`car_ID`) REFERENCES `car_inf` (`ID`),
  CONSTRAINT `cargoodlink_inf_ibfk_2` FOREIGN KEY (`good_ID`) REFERENCES `good_inf` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cargoodlink_inf
-- ----------------------------
INSERT INTO cargoodlink_inf VALUES ('1', '2', '7');
INSERT INTO cargoodlink_inf VALUES ('1', '3', '7');
INSERT INTO cargoodlink_inf VALUES ('1', '4', '7');

-- ----------------------------
-- Table structure for `car_inf`
-- ----------------------------
DROP TABLE IF EXISTS `car_inf`;
CREATE TABLE `car_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `customer_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `customer_ID` (`customer_ID`),
  CONSTRAINT `car_inf_ibfk_1` FOREIGN KEY (`customer_ID`) REFERENCES `customer_inf` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_inf
-- ----------------------------
INSERT INTO car_inf VALUES ('1', '1');

-- ----------------------------
-- Table structure for `category_inf`
-- ----------------------------
DROP TABLE IF EXISTS `category_inf`;
CREATE TABLE `category_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(20) NOT NULL,
  `remark` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_inf
-- ----------------------------
INSERT INTO category_inf VALUES ('1', '1', null);
INSERT INTO category_inf VALUES ('2', '2', null);
INSERT INTO category_inf VALUES ('3', '3', null);

-- ----------------------------
-- Table structure for `customer_inf`
-- ----------------------------
DROP TABLE IF EXISTS `customer_inf`;
CREATE TABLE `customer_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `PAYNUMBER` int(11) default NULL,
  `ADDRESS` varchar(50) default NULL,
  `SEX` varchar(16) default NULL,
  `AGE` int(11) default NULL,
  `TELNUMBER` varchar(20) default NULL,
  `EMAIL` varchar(20) default NULL,
  `STATUS` int(11) NOT NULL default '0',
  `createdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_inf
-- ----------------------------
INSERT INTO customer_inf VALUES ('1', '123456', '123456', '123456', null, 'female', '16', '111', '3782@qq.com', '0', '2017-06-02 18:52:41');

-- ----------------------------
-- Table structure for `good_inf`
-- ----------------------------
DROP TABLE IF EXISTS `good_inf`;
CREATE TABLE `good_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `store_ID` int(11) NOT NULL,
  `category_ID` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `image` varchar(50) default NULL,
  `PRICE` double default NULL,
  `remark` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `store_ID` (`store_ID`),
  KEY `category_ID` (`category_ID`),
  CONSTRAINT `good_inf_ibfk_1` FOREIGN KEY (`store_ID`) REFERENCES `store_inf` (`ID`),
  CONSTRAINT `good_inf_ibfk_2` FOREIGN KEY (`category_ID`) REFERENCES `category_inf` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_inf
-- ----------------------------
INSERT INTO good_inf VALUES ('2', '1', '1', 'anta', 'image/goods/shuishou/1.jpg', '100', null);
INSERT INTO good_inf VALUES ('3', '1', '1', 'ant', 'image/goods/shuishou/1.jpg', '100', null);
INSERT INTO good_inf VALUES ('4', '1', '1', 'nike', 'image/goods/shuishou/1.jpg', '111', '1');
INSERT INTO good_inf VALUES ('5', '1', '1', '360', 'image/goods/shuishou/1.jpg', '99', '');

-- ----------------------------
-- Table structure for `ordergoodlink_inf`
-- ----------------------------
DROP TABLE IF EXISTS `ordergoodlink_inf`;
CREATE TABLE `ordergoodlink_inf` (
  `order_ID` int(11) NOT NULL default '0',
  `good_ID` int(11) NOT NULL default '0',
  `AMOUNT` int(11) default NULL,
  PRIMARY KEY  (`order_ID`,`good_ID`),
  KEY `good_ID` (`good_ID`),
  CONSTRAINT `ordergoodlink_inf_ibfk_1` FOREIGN KEY (`order_ID`) REFERENCES `order_inf` (`ID`),
  CONSTRAINT `ordergoodlink_inf_ibfk_2` FOREIGN KEY (`good_ID`) REFERENCES `good_inf` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordergoodlink_inf
-- ----------------------------
INSERT INTO ordergoodlink_inf VALUES ('8', '2', null);
INSERT INTO ordergoodlink_inf VALUES ('8', '3', null);

-- ----------------------------
-- Table structure for `order_inf`
-- ----------------------------
DROP TABLE IF EXISTS `order_inf`;
CREATE TABLE `order_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` int(11) default NULL,
  `TOTAL` double default NULL,
  `customer_ID` int(11) default NULL,
  `STATUS` int(11) NOT NULL default '0',
  `createdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `ADDRESS` varchar(50) default NULL,
  `remark` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `customer_ID` (`customer_ID`),
  CONSTRAINT `order_inf_ibfk_1` FOREIGN KEY (`customer_ID`) REFERENCES `customer_inf` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_inf
-- ----------------------------
INSERT INTO order_inf VALUES ('1', '1', '200', '1', '3', '2017-06-04 14:46:30', null, null);

-- ----------------------------
-- Table structure for `seller_inf`
-- ----------------------------
DROP TABLE IF EXISTS `seller_inf`;
CREATE TABLE `seller_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `PAYNUMBER` int(11) default NULL,
  `ADDRESS` varchar(50) default NULL,
  `SEX` varchar(16) default NULL,
  `AGE` int(11) default NULL,
  `TELNUMBER` varchar(20) default NULL,
  `EMAIL` varchar(20) default NULL,
  `STATUS` int(11) NOT NULL default '0',
  `createdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller_inf
-- ----------------------------
INSERT INTO seller_inf VALUES ('1', '123456', '123456', '1234', null, null, null, null, null, '0', '2017-06-02 19:04:56');

-- ----------------------------
-- Table structure for `store_inf`
-- ----------------------------
DROP TABLE IF EXISTS `store_inf`;
CREATE TABLE `store_inf` (
  `ID` int(11) NOT NULL auto_increment,
  `seller_ID` int(11) default NULL,
  `NAME` varchar(20) NOT NULL,
  `remark` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `seller_ID` (`seller_ID`),
  CONSTRAINT `store_inf_ibfk_1` FOREIGN KEY (`seller_ID`) REFERENCES `seller_inf` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store_inf
-- ----------------------------
INSERT INTO store_inf VALUES ('1', '1', 'zhao', null);
