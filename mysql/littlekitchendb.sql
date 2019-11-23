/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : littleKitchen

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001


*/

use littlekitchen;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_tbl
-- ----------------------------
DROP TABLE IF EXISTS `user_tbl`;
CREATE TABLE `user_tbl` (
  `userid` INT(10) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(35) DEFAULT NULL,
  `password` VARCHAR(25) DEFAULT NULL,
  `nickname` VARCHAR(25) DEFAULT NULL,
  `gender` BOOLEAN DEFAULT NULL COMMENT '1|T:M,0|F:F',
  `birthday` DATE DEFAULT NULL,
  `photo` VARCHAR(100) DEFAULT NULL,
  `description` VARCHAR(100) DEFAULT NULL COMMENT '描述',
  `createDate` TIMESTAMP DEFAULT NOW(),
  PRIMARY KEY (`userid`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuid` INT(10) NOT NULL AUTO_INCREMENT,
  `userid` INT(10) NOT NULL,
  `title` VARCHAR(60) DEFAULT NULL,
  `description` VARCHAR(3000) DEFAULT NULL,
  `material` VARCHAR(3000) DEFAULT NULL,
  `step` VARCHAR(3000) DEFAULT NULL,
  `picture` VARCHAR(3000) DEFAULT NULL,
  `cover` VARCHAR(3000) DEFAULT NULL,
  `type` INT(10) DEFAULT NULL,
  `createTime` TIMESTAMP DEFAULT NOW(),
  PRIMARY KEY (`menuid`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `userid` INT(10) NOT NULL,
  `menuid` INT(10) NOT NULL,
  PRIMARY KEY (`userid`, `menuid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `thumbup`;
CREATE TABLE `thumbup` (
  `userid` INT(10) NOT NULL,
  `menuid` INT(10) NOT NULL,
  PRIMARY KEY (`userid`, `menuid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `userid1` INT(10) NOT NULL COMMENT '关注的人',
  `userid2` INT(10) NOT NULL COMMENT '被关注的人',
  PRIMARY KEY (`userid1`, `userid2`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO user_tbl (userid, email, password, nickname, gender, birthday, photo, description, createDate) VALUES (4, "1@qq.com","123456","first",1,"2019-1-1","photo1","这是第一个创建的用户","2019-11-10");
INSERT INTO user_tbl (userid, email, password, nickname, gender, birthday, photo, description, createDate) VALUES (1, "12@qq.com","123456","second",1,"2019-1-1","photo2","这是第二个创建的用户","2019-11-10");
INSERT INTO user_tbl (userid, email, password, nickname, gender, birthday, photo, description, createDate) VALUES (2, "123@qq.com","123456","third",1,"2019-1-1","photo3","这是第三个创建的用户","2019-11-10");
INSERT INTO user_tbl (userid, email, password, nickname, gender, birthday, photo, description, createDate) VALUES (3, "1234@qq.com","123456","fourth",1,"2019-1-1","photo4","这是第四个创建的用户","2019-11-10");

INSERT INTO favorite (userid, menuid) VALUES (1,1);

INSERT INTO follow (userid1, userid2) VALUES (1,2);
INSERT INTO follow (userid1, userid2) VALUES (2,3);
INSERT INTO follow (userid1, userid2) VALUES (2,1);

INSERT INTO menu (menuid, userid, title, description, material, step, picture, cover, type, createTime) VALUES (1, 1,"酸菜鱼1","这是第一份酸菜鱼","酸菜 鱼","1.放酸菜；2.放鱼",
                         "https://ali.xinshipu.cn/20141226/original/1419534344443.jpg","https://ali.xinshipu.cn/20141226/original/1419534344443.jpg",
1,"2019-11-10");
INSERT INTO menu (menuid, userid, title, description, material, step, picture, cover, type, createTime) VALUES (2, 1,"酸菜鱼2","这是第二份酸菜鱼","酸菜 鱼","1.放酸菜；2.放鱼",
                         "https://ali.xinshipu.cn/20141226/original/1419534344443.jpg","https://ali.xinshipu.cn/20141226/original/1419534344443.jpg",
                         1,"2019-11-10");
INSERT INTO menu (menuid, userid, title, description, material, step, picture, cover, type, createTime) VALUES (3, 1,"番茄炒蛋1","这是第一份番茄炒蛋","番茄 鸡蛋","1.放番茄；2.放鸡蛋",
                         "http://www.meishijr.com/d/swfuppic/20180803/1533260054932_s.jpg","http://www.meishijr.com/d/swfuppic/20180803/1533260054932_s.jpg",
                         1,"2019-11-10");
INSERT INTO menu (menuid, userid, title, description, material, step, picture, cover, type, createTime) VALUES (4, 1,"番茄炒蛋2","这是第二份番茄炒蛋","番茄 鸡蛋","1.放番茄；2.放鸡蛋",
                         "https://img.cook1cook.com/upload/cover/15/91/9779914994051761591.jpg","https://img.cook1cook.com/upload/cover/15/91/9779914994051761591.jpg",
                         1,"2019-11-10");

INSERT INTO thumbup (userid, menuid) VALUES (1,2);
INSERT INTO thumbup (userid, menuid) VALUES (1,3);