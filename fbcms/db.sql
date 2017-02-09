/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2017-02-09 09:53:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_group
-- ----------------------------
DROP TABLE IF EXISTS `account_group`;
CREATE TABLE `account_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(10) NOT NULL,
  `group_no` varchar(20) NOT NULL,
  `group_name` varchar(32) NOT NULL,
  `level` tinyint(2) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  `note` varchar(128) DEFAULT NULL,
  `leader` varchar(32) DEFAULT NULL,
  `contact_phone` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_1` (`user_type`,`group_no`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for account_login_info
-- ----------------------------
DROP TABLE IF EXISTS `account_login_info`;
CREATE TABLE `account_login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `login_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `user_type` varchar(10) NOT NULL,
  `fund_id` varchar(20) DEFAULT NULL,
  `status` int(2) NOT NULL,
  `version` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`),
  UNIQUE KEY `idx_user_type` (`login_name`,`user_type`) USING BTREE,
  KEY `idx_user_type_1` (`user_type`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for account_module
-- ----------------------------
DROP TABLE IF EXISTS `account_module`;
CREATE TABLE `account_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(8) CHARACTER SET utf8mb4 NOT NULL,
  `code` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `logo_url` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
  `index_url` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
  `is_hidden` tinyint(1) DEFAULT '0',
  `note` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `menu_tree` text CHARACTER SET utf8mb4,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(8) CHARACTER SET utf8mb4 NOT NULL,
  `role_code` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `role_name` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `note` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `group_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_at_rc` (`user_type`,`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for account_role_ref_menu
-- ----------------------------
DROP TABLE IF EXISTS `account_role_ref_menu`;
CREATE TABLE `account_role_ref_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `role_code` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `module_code` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `menu_code` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `sub_funcs` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL,
  `read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '读权限 0 全部 1本人 2本组 3 本组及子组  -1 无权限',
  `edit` tinyint(4) NOT NULL DEFAULT '0' COMMENT '增加和修改权限 0 全部 1本人 2本组 3 本组及子组  -1 无权限',
  `delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除权限 0 全部 1本人 2本组 3 本组及子组  -1 无权限',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_type` (`user_type`,`role_code`,`module_code`,`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for account_user
-- ----------------------------
DROP TABLE IF EXISTS `account_user`;
CREATE TABLE `account_user` (
  `user_id` varchar(32) NOT NULL COMMENT '管理员编号',
  `user_no` varchar(32) NOT NULL,
  `group_no` varchar(32) NOT NULL COMMENT '所属部门编号',
  `group_name` varchar(64) DEFAULT NULL COMMENT '部门',
  `user_name` varchar(64) DEFAULT NULL COMMENT '人员姓名',
  `phone` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for account_user_ref_role
-- ----------------------------
DROP TABLE IF EXISTS `account_user_ref_role`;
CREATE TABLE `account_user_ref_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_code` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_type` (`user_type`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

INSERT INTO `account_login_info` VALUES ('0', 'admin', 'sa_mis', 'E99A18C428CB38D5F260853678922E03', 'admin', 'e', '', '1', '1', now(), now());
INSERT INTO `account_module` VALUES ('1', 'e', 'mis', '平台管理', null, '/index', '0', null, '{\"menu\":{\"default\":false,\"id\":\"0\",\"enable\":true,\"dir\":true,\"visible\":false},\"children\":[{\"menu\":{\"id\":\"dir-10001\",\"name\":\"订单管理\",\"icon\":\"order\",\"dir\":true},\"children\":[{\"menu\":{\"name\":\"我的订单\",\"dir\":false,\"url\":\"/order\",\"id\":\"order\",\"subFuncs\":{\"w\":\"写\",\"R\":\"读;\"}}}]}]}', '2015-10-03 10:46:11', '2017-02-08 17:18:48');
INSERT INTO `account_module` VALUES ('2', 'e', 'admin', '系统设置', null, '/preferences/e/account', '0', '', '{\r\n    \"children\": [\r\n        {\r\n            \"children\": [\r\n                {\r\n                    \"menu\": {\r\n                        \"dir\": false,\r\n                        \"id\": \"group\",\r\n                        \"name\": \"组织架构\",\r\n                        \"url\": \"/preferences/e/group\"\r\n                    }\r\n                },\r\n                {\r\n                    \"menu\": {\r\n                        \"dir\": false,\r\n                        \"id\": \"account\",\r\n                        \"name\": \"人员设置\",\r\n                        \"url\": \"/preferences/e/account\"\r\n                    }\r\n                },\r\n                {\r\n                    \"menu\": {\r\n                        \"dir\": false,\r\n                        \"id\": \"role\",\r\n                        \"name\": \"角色管理\",\r\n                        \"url\": \"/preferences/e/role\"\r\n                    }\r\n                },\r\n                {\r\n                    \"menu\": {\r\n                        \"dir\": false,\r\n                        \"id\": \"modules\",\r\n                        \"name\": \"模块菜单\",\r\n                        \"url\": \"/preferences/e/module\"\r\n                    }\r\n                }\r\n            ],\r\n            \"menu\": {\r\n                \"dir\": true,\r\n                \"icon\": \"\",\r\n                \"id\": \"preferences\",\r\n                \"name\": \"系统设置\"\r\n            }\r\n        }\r\n    ],\r\n    \"menu\": {\r\n        \"default\": false,\r\n        \"dir\": true,\r\n        \"enable\": true,\r\n        \"id\": \"0\",\r\n        \"visible\": false\r\n    }\r\n}', '2015-11-02 13:18:26', '2017-02-08 13:03:11');
