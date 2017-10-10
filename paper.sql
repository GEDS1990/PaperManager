/*
Navicat MySQL Data Transfer

Source Server         : HuTao
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : paper

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-10-11 06:50:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for choose
-- ----------------------------
DROP TABLE IF EXISTS `choose`;
CREATE TABLE `choose` (
  `C_no` int(10) NOT NULL AUTO_INCREMENT,
  `S_no` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `P_no` int(10) NOT NULL,
  `C_voNumber` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `C_state` int(3) NOT NULL,
  `C_time` date DEFAULT NULL,
  PRIMARY KEY (`C_no`),
  KEY `FK_paper` (`P_no`),
  KEY `FK_student` (`S_no`),
  CONSTRAINT `FK_paper` FOREIGN KEY (`P_no`) REFERENCES `paper` (`P_no`),
  CONSTRAINT `FK_student` FOREIGN KEY (`S_no`) REFERENCES `student` (`S_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `D_no` int(10) NOT NULL AUTO_INCREMENT,
  `D_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `D_tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`D_no`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for education
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `E_no` int(10) NOT NULL AUTO_INCREMENT,
  `E_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `E_tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `D_no` int(10) DEFAULT NULL,
  `E_password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`E_no`),
  KEY `FK_Depart` (`D_no`),
  CONSTRAINT `FK_Depart` FOREIGN KEY (`D_no`) REFERENCES `department` (`D_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `M_no` int(10) NOT NULL AUTO_INCREMENT,
  `D_no` int(10) DEFAULT NULL,
  `M_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`M_no`),
  KEY `FK_department` (`D_no`),
  CONSTRAINT `FK_department` FOREIGN KEY (`D_no`) REFERENCES `department` (`D_no`)
) ENGINE=InnoDB AUTO_INCREMENT=25007 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `P_no` int(10) NOT NULL AUTO_INCREMENT,
  `P_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `P_content` text COLLATE utf8_unicode_ci NOT NULL,
  `T_no` int(10) NOT NULL,
  `D_no` int(10) DEFAULT NULL,
  `P_maxNumber` int(3) NOT NULL,
  `P_chooNumber` int(3) DEFAULT NULL,
  `P_state` int(3) NOT NULL,
  `E_no` int(10) DEFAULT NULL,
  `P_eduTime` date DEFAULT NULL,
  `Z_no` int(10) DEFAULT NULL,
  `P_auTime` date DEFAULT NULL,
  `P_zhSuggest` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`P_no`),
  KEY `FK_depart_2` (`D_no`),
  KEY `FK_education` (`E_no`),
  KEY `FK_teacher` (`T_no`),
  KEY `FK_zhaunjia` (`Z_no`),
  CONSTRAINT `FK_depart_2` FOREIGN KEY (`D_no`) REFERENCES `department` (`D_no`),
  CONSTRAINT `FK_education` FOREIGN KEY (`E_no`) REFERENCES `education` (`E_no`),
  CONSTRAINT `FK_teacher` FOREIGN KEY (`T_no`) REFERENCES `teacher` (`T_no`),
  CONSTRAINT `FK_zhaunjia` FOREIGN KEY (`Z_no`) REFERENCES `zhuanjia` (`Z_no`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `S_no` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `M_no` int(10) DEFAULT NULL,
  `S_name` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `S_sex` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `S_class` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `S_tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `S_password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`S_no`),
  KEY `FK_major` (`M_no`),
  CONSTRAINT `FK_major` FOREIGN KEY (`M_no`) REFERENCES `major` (`M_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `T_no` int(10) NOT NULL AUTO_INCREMENT,
  `T_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `D_no` int(10) DEFAULT NULL,
  `T_tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `T_content` text COLLATE utf8_unicode_ci,
  `T_paperNum` int(3) DEFAULT NULL,
  `T_paperAuNum` int(3) DEFAULT NULL,
  `T_password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`T_no`),
  KEY `FK_department_2` (`D_no`),
  CONSTRAINT `FK_department_2` FOREIGN KEY (`D_no`) REFERENCES `department` (`D_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2002 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for zhuanjia
-- ----------------------------
DROP TABLE IF EXISTS `zhuanjia`;
CREATE TABLE `zhuanjia` (
  `Z_no` int(10) NOT NULL,
  `Z_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Z_tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Z_password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Z_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
SET FOREIGN_KEY_CHECKS=1;
