/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lab

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-06-29 10:45:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dailyjob
-- ----------------------------
DROP TABLE IF EXISTS `dailyjob`;
CREATE TABLE `dailyjob` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `weekly` int(10) DEFAULT NULL,
  `time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '实验室助理ID',
  `content` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  `systemTime` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='日常工作';

-- ----------------------------
-- Records of dailyjob
-- ----------------------------
INSERT INTO `dailyjob` VALUES ('1', '1', '2019-12-09 ', '201706', '扫地', '2019-12-09 19:39:04');
INSERT INTO `dailyjob` VALUES ('2', '3', '2019-12-10 ', '201801', '扫地', '2019-12-10 12:09:01');
INSERT INTO `dailyjob` VALUES ('3', '5855', '2020-06-11', '201801', '555', '2020-06-11 16:03:15 ');
INSERT INTO `dailyjob` VALUES ('4', '7', '2020-06-27', 'yonghu', '清洁教室', '2020-06-27 20:37:43 ');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dep_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `dep_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='二级学院';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '大数据与软件工程');
INSERT INTO `department` VALUES ('2', '电子与信息工程');

-- ----------------------------
-- Table structure for form
-- ----------------------------
DROP TABLE IF EXISTS `form`;
CREATE TABLE `form` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `plan_id` int(10) DEFAULT NULL,
  `user_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '（0为报名中，1面试失败，2面试成功）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='报名表';

-- ----------------------------
-- Records of form
-- ----------------------------
INSERT INTO `form` VALUES ('9', '1', '201706', '1');
INSERT INTO `form` VALUES ('10', '8', '201706', '2');
INSERT INTO `form` VALUES ('12', '8', 'yonghu', '2');
INSERT INTO `form` VALUES ('13', '10', 'yonghu', '1');

-- ----------------------------
-- Table structure for labs
-- ----------------------------
DROP TABLE IF EXISTS `labs`;
CREATE TABLE `labs` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lab_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10021 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='实验室';

-- ----------------------------
-- Records of labs
-- ----------------------------
INSERT INTO `labs` VALUES ('10010', '软件开发中心', '1');
INSERT INTO `labs` VALUES ('10020', '电本实验室', '2');

-- ----------------------------
-- Table structure for recruitment
-- ----------------------------
DROP TABLE IF EXISTS `recruitment`;
CREATE TABLE `recruitment` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `plan_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '招聘名称',
  `required` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '岗位需求说明',
  `require_num` int(10) DEFAULT NULL COMMENT '岗位需求人数',
  `section_id` int(10) DEFAULT NULL COMMENT '部门id',
  `lab_id` int(10) DEFAULT NULL COMMENT '实验技术人员ID',
  `status` int(10) DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`id`),
  KEY `section_id` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='应聘计划表';

-- ----------------------------
-- Records of recruitment
-- ----------------------------
INSERT INTO `recruitment` VALUES ('1', 'javaEEE', '要求头发少00', '10', '1', '10010', '1');
INSERT INTO `recruitment` VALUES ('8', 'ssh', '无', '47', '1', '10010', '2');
INSERT INTO `recruitment` VALUES ('12', '6666', '6666', '6666', '666', '666', null);

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descrip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fix_time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='报修表';

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('2', '显示屏', '对的', '2019-12-09 20:20:13', '201801');
INSERT INTO `repair` VALUES ('3', '8655', '55', '2020-06-11 16:32:06 ', '201801');
INSERT INTO `repair` VALUES ('4', '22222', '5252', '2020-06-11 16:45:21 ', '201801');
INSERT INTO `repair` VALUES ('5', '8888', '555', '2020-06-11 18:34:47 ', '201706');
INSERT INTO `repair` VALUES ('6', '主机箱', '冒烟', '2020-06-27 20:38:29 ', 'yonghu');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生');
INSERT INTO `role` VALUES ('2', '助理');
INSERT INTO `role` VALUES ('3', '实验室技术人员');
INSERT INTO `role` VALUES ('4', '管理员');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `section_id` int(10) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `laboratory_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='部门';

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('1', '开发部', '10010');
INSERT INTO `section` VALUES ('2', '后勤部', '10010');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `pwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '照片',
  `section_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '201706', '林才楗', '123', null, '1');
INSERT INTO `user` VALUES ('2', '201801', '林亦博', '123', null, null);
INSERT INTO `user` VALUES ('3', '50001', '许传本', '123', null, '1');
INSERT INTO `user` VALUES ('7', 'admin', '管理员', '123', null, null);
INSERT INTO `user` VALUES ('8', 'yonghu', '用户', '123', null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', 'admin', '4');
INSERT INTO `user_role` VALUES ('5', '50001', '3');
INSERT INTO `user_role` VALUES ('6', '201706', '2');
INSERT INTO `user_role` VALUES ('7', '201801', '1');
INSERT INTO `user_role` VALUES ('8', 'yonghu', '2');
