/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50126
Source Host           : localhost:3306
Source Database       : qx

Target Server Type    : MYSQL
Target Server Version : 50126
File Encoding         : 65001

Date: 2014-06-16 16:31:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aname
-- ----------------------------
DROP TABLE IF EXISTS `aname`;
CREATE TABLE `aname` (
  `id` varchar(50) NOT NULL COMMENT '用户ID',
  `name` varchar(100) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aname
-- ----------------------------
INSERT INTO `aname` VALUES ('1111', '2');

-- ----------------------------
-- Table structure for dictype
-- ----------------------------
DROP TABLE IF EXISTS `dictype`;
CREATE TABLE `dictype` (
  `uid` varchar(50) NOT NULL COMMENT '主键',
  `text` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `seq` varchar(50) DEFAULT NULL,
  `id` varchar(50) DEFAULT NULL COMMENT '字典编码',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictype
-- ----------------------------
INSERT INTO `dictype` VALUES ('0', '资源类型', '添加资源时候的字典', 'no', '0', 'zylx');
INSERT INTO `dictype` VALUES ('1', '菜单', '资源类型', 'zylx', '1', 'menu');
INSERT INTO `dictype` VALUES ('2', '功能', '资源类型', 'zylx', '2', 'feature');
INSERT INTO `dictype` VALUES ('57ea55c7-a9a5-4427-9786-8dc348a6c594', '性别', 'x', 'no', '100', 'sex');
INSERT INTO `dictype` VALUES ('8683d90c-f3c5-4032-bb87-a48b15a19d01', '男', '1', 'sex', '100', '1');
INSERT INTO `dictype` VALUES ('f8841207-8f8b-4bdc-9efa-0d66a8d341a7', '女', '2', 'sex', '100', '2');

-- ----------------------------
-- Table structure for tresource
-- ----------------------------
DROP TABLE IF EXISTS `tresource`;
CREATE TABLE `tresource` (
  `ID` varchar(36) NOT NULL,
  `ICON` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  `TRESOURCETYPE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_m0i6pj14hcg1mleojnl7igg6o` (`PID`),
  KEY `FK_6w0sgqbec1o81uqqjsmejg57p` (`TRESOURCETYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tresource
-- ----------------------------
INSERT INTO `tresource` VALUES ('0bbe996b-c2ad-4ab7-bed4-d0fed0f7ecf9', 'attach', '添加数据字典', '', '102', '/dicController/add', '8fccf796-9462-467a-9a14-b99e621788bb', 'feature');
INSERT INTO `tresource` VALUES ('0c36995d-d8f2-42db-bc2e-164d1bb5efdb', 'attach', '编辑数据字典', '', '104', '/dicController/edit', '8fccf796-9462-467a-9a14-b99e621788bb', 'feature');
INSERT INTO `tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', 'bell', 'test', 'test', '100', '/anameController/manager', '', 'menu');
INSERT INTO `tresource` VALUES ('4c70b20c-2e7b-4554-b6f1-ee247afebc51', 'attach', '添加数据字典页面', '', '101', '/dicController/addPage', '8fccf796-9462-467a-9a14-b99e621788bb', 'feature');
INSERT INTO `tresource` VALUES ('7695a83a-9ff0-44fa-bd3e-621d7c0b3045', 'attach', '字典列表', '数据字典列表', '100', '/dicController/treeGrid', '8fccf796-9462-467a-9a14-b99e621788bb', 'feature');
INSERT INTO `tresource` VALUES ('89cdcec7-d748-4633-b9f3-1b3753ed3352', 'attach', '删除数据字典', '', '105', '/dicController/delete', '8fccf796-9462-467a-9a14-b99e621788bb', 'feature');
INSERT INTO `tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', 'asterisk_yellow', '字典管理', '系统数据字典', '4', '/dicController/manager', 'xtgl', 'menu');
INSERT INTO `tresource` VALUES ('b75bf0b0-059b-48ae-ab6c-ca01f2efafa1', 'attach', '编辑数据字典页面', '', '103', '/dicController/editPage', '8fccf796-9462-467a-9a14-b99e621788bb', 'feature');
INSERT INTO `tresource` VALUES ('jsgl', 'tux', '角色管理', null, '2', '/roleController/manager', 'xtgl', 'menu');
INSERT INTO `tresource` VALUES ('jsglAdd', 'wrench', '添加角色', null, '3', '/roleController/add', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglAddPage', 'wrench', '添加角色页面', null, '2', '/roleController/addPage', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglDelete', 'wrench', '删除角色', null, '6', '/roleController/delete', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglEdit', 'wrench', '编辑角色', null, '5', '/roleController/edit', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglEditPage', 'wrench', '编辑角色页面', null, '4', '/roleController/editPage', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglGrant', 'wrench', '角色授权', null, '8', '/roleController/grant', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglGrantPage', 'wrench', '角色授权页面', null, '7', '/roleController/grantPage', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('jsglTreeGrid', 'wrench', '角色表格', null, '1', '/roleController/treeGrid', 'jsgl', 'feature');
INSERT INTO `tresource` VALUES ('wjgl', 'server_database', '文件管理', null, '6', '', 'xtgl', 'feature');
INSERT INTO `tresource` VALUES ('wjglUpload', 'server_database', '上传文件', null, '2', '/fileController/upload', 'wjgl', 'feature');
INSERT INTO `tresource` VALUES ('wjglView', 'server_database', '浏览服务器文件', null, '1', '/fileController/fileManage', 'wjgl', 'feature');
INSERT INTO `tresource` VALUES ('xtgl', 'plugin', '系统管理', null, '0', null, null, 'menu');
INSERT INTO `tresource` VALUES ('yhgl', 'status_online', '用户管理', null, '3', '/userController/manager', 'xtgl', 'menu');
INSERT INTO `tresource` VALUES ('yhglAdd', 'wrench', '添加用户', null, '3', '/userController/add', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglAddPage', 'wrench', '添加用户页面', null, '2', '/userController/addPage', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglBatchDelete', 'wrench', '批量删除用户', null, '7', '/userController/batchDelete', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglDateGrid', 'wrench', '用户表格', null, '1', '/userController/dataGrid', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglDelete', 'wrench', '删除用户', null, '6', '/userController/delete', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglEdit', 'wrench', '编辑用户', null, '5', '/userController/edit', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglEditPage', 'wrench', '编辑用户页面', null, '4', '/userController/editPage', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglEditPwd', 'wrench', '用户修改密码', null, '11', '/userController/editPwd', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglEditPwdPage', 'wrench', '用户修改密码页面', null, '10', '/userController/editPwdPage', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglGrant', 'wrench', '用户授权', null, '9', '/userController/grant', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('yhglGrantPage', 'wrench', '用户授权页面', null, '8', '/userController/grantPage', 'yhgl', 'feature');
INSERT INTO `tresource` VALUES ('zygl', 'database_gear', '资源管理', '管理系统中所有的菜单或功能', '1', '/resourceController/manager', 'xtgl', 'menu');
INSERT INTO `tresource` VALUES ('zyglAdd', 'wrench', '添加资源', null, '4', '/resourceController/add', 'zygl', 'feature');
INSERT INTO `tresource` VALUES ('zyglAddPage', 'wrench', '添加资源页面', null, '3', '/resourceController/addPage', 'zygl', 'feature');
INSERT INTO `tresource` VALUES ('zyglDelete', 'wrench', '删除资源', null, '7', '/resourceController/delete', 'zygl', 'feature');
INSERT INTO `tresource` VALUES ('zyglEdit', 'wrench', '编辑资源', null, '6', '/resourceController/edit', 'zygl', 'feature');
INSERT INTO `tresource` VALUES ('zyglEditPage', 'wrench', '编辑资源页面', null, '5', '/resourceController/editPage', 'zygl', 'feature');
INSERT INTO `tresource` VALUES ('zyglMenu', 'wrench', '功能菜单', null, '2', '/resourceController/tree', 'zygl', 'feature');
INSERT INTO `tresource` VALUES ('zyglTreeGrid', 'wrench', '资源表格', '显示资源列表', '1', '/resourceController/treeGrid', 'zygl', 'feature');

-- ----------------------------
-- Table structure for trole
-- ----------------------------
DROP TABLE IF EXISTS `trole`;
CREATE TABLE `trole` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_3qq9eyhwbhblv83lt270gxbik` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trole
-- ----------------------------
INSERT INTO `trole` VALUES ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null);
INSERT INTO `trole` VALUES ('3f47201d-4944-4190-a126-43b2478cdf68', 'test', 'sss', '100', 'guest');
INSERT INTO `trole` VALUES ('bugAdmin', 'BUG管理员', null, '5', '0');
INSERT INTO `trole` VALUES ('guest', 'Guest', '只拥有只看的权限', '1', null);
INSERT INTO `trole` VALUES ('jsAdmin', '角色管理员', null, '2', '0');
INSERT INTO `trole` VALUES ('sjyAdmin', '数据源管理员', null, '4', '0');
INSERT INTO `trole` VALUES ('yhAdmin', '用户管理员', null, '3', '0');
INSERT INTO `trole` VALUES ('zyAdmin', '资源管理员', null, '1', '0');

-- ----------------------------
-- Table structure for trole_tresource
-- ----------------------------
DROP TABLE IF EXISTS `trole_tresource`;
CREATE TABLE `trole_tresource` (
  `TRESOURCE_ID` varchar(36) NOT NULL,
  `TROLE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`TROLE_ID`,`TRESOURCE_ID`),
  KEY `FK_aunc1ssqh18meky8cxl48i4m9` (`TROLE_ID`),
  KEY `FK_18oms8g4ib4h67dqx0f3fwaed` (`TRESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trole_tresource
-- ----------------------------
INSERT INTO `trole_tresource` VALUES ('0bbe996b-c2ad-4ab7-bed4-d0fed0f7ecf9', '0');
INSERT INTO `trole_tresource` VALUES ('0c36995d-d8f2-42db-bc2e-164d1bb5efdb', '0');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', '0');
INSERT INTO `trole_tresource` VALUES ('4c70b20c-2e7b-4554-b6f1-ee247afebc51', '0');
INSERT INTO `trole_tresource` VALUES ('7695a83a-9ff0-44fa-bd3e-621d7c0b3045', '0');
INSERT INTO `trole_tresource` VALUES ('89cdcec7-d748-4633-b9f3-1b3753ed3352', '0');
INSERT INTO `trole_tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', '0');
INSERT INTO `trole_tresource` VALUES ('b75bf0b0-059b-48ae-ab6c-ca01f2efafa1', '0');
INSERT INTO `trole_tresource` VALUES ('jsgl', '0');
INSERT INTO `trole_tresource` VALUES ('jsglAdd', '0');
INSERT INTO `trole_tresource` VALUES ('jsglAddPage', '0');
INSERT INTO `trole_tresource` VALUES ('jsglDelete', '0');
INSERT INTO `trole_tresource` VALUES ('jsglEdit', '0');
INSERT INTO `trole_tresource` VALUES ('jsglEditPage', '0');
INSERT INTO `trole_tresource` VALUES ('jsglGrant', '0');
INSERT INTO `trole_tresource` VALUES ('jsglGrantPage', '0');
INSERT INTO `trole_tresource` VALUES ('jsglTreeGrid', '0');
INSERT INTO `trole_tresource` VALUES ('wjgl', '0');
INSERT INTO `trole_tresource` VALUES ('wjglUpload', '0');
INSERT INTO `trole_tresource` VALUES ('wjglView', '0');
INSERT INTO `trole_tresource` VALUES ('xtgl', '0');
INSERT INTO `trole_tresource` VALUES ('yhgl', '0');
INSERT INTO `trole_tresource` VALUES ('yhglAdd', '0');
INSERT INTO `trole_tresource` VALUES ('yhglAddPage', '0');
INSERT INTO `trole_tresource` VALUES ('yhglBatchDelete', '0');
INSERT INTO `trole_tresource` VALUES ('yhglDateGrid', '0');
INSERT INTO `trole_tresource` VALUES ('yhglDelete', '0');
INSERT INTO `trole_tresource` VALUES ('yhglEdit', '0');
INSERT INTO `trole_tresource` VALUES ('yhglEditPage', '0');
INSERT INTO `trole_tresource` VALUES ('yhglEditPwd', '0');
INSERT INTO `trole_tresource` VALUES ('yhglEditPwdPage', '0');
INSERT INTO `trole_tresource` VALUES ('yhglGrant', '0');
INSERT INTO `trole_tresource` VALUES ('yhglGrantPage', '0');
INSERT INTO `trole_tresource` VALUES ('zygl', '0');
INSERT INTO `trole_tresource` VALUES ('zyglAdd', '0');
INSERT INTO `trole_tresource` VALUES ('zyglAddPage', '0');
INSERT INTO `trole_tresource` VALUES ('zyglDelete', '0');
INSERT INTO `trole_tresource` VALUES ('zyglEdit', '0');
INSERT INTO `trole_tresource` VALUES ('zyglEditPage', '0');
INSERT INTO `trole_tresource` VALUES ('zyglMenu', '0');
INSERT INTO `trole_tresource` VALUES ('zyglTreeGrid', '0');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', '3f47201d-4944-4190-a126-43b2478cdf68');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', 'guest');
INSERT INTO `trole_tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', 'guest');
INSERT INTO `trole_tresource` VALUES ('jsgl', 'guest');
INSERT INTO `trole_tresource` VALUES ('jsglTreeGrid', 'guest');
INSERT INTO `trole_tresource` VALUES ('xtgl', 'guest');
INSERT INTO `trole_tresource` VALUES ('yhgl', 'guest');
INSERT INTO `trole_tresource` VALUES ('yhglDateGrid', 'guest');
INSERT INTO `trole_tresource` VALUES ('zygl', 'guest');
INSERT INTO `trole_tresource` VALUES ('zyglTreeGrid', 'guest');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsgl', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglAdd', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglAddPage', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglDelete', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglEdit', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglEditPage', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglGrant', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglGrantPage', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('jsglTreeGrid', 'jsAdmin');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', 'sjyAdmin');
INSERT INTO `trole_tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', 'sjyAdmin');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhgl', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglAdd', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglAddPage', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglBatchDelete', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglDateGrid', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglDelete', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglEdit', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglEditPage', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglEditPwd', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglEditPwdPage', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglGrant', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('yhglGrantPage', 'yhAdmin');
INSERT INTO `trole_tresource` VALUES ('401e5182-8108-45b8-a23d-0789966516d7', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('8fccf796-9462-467a-9a14-b99e621788bb', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zygl', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglAdd', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglAddPage', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglDelete', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglEdit', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglEditPage', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglMenu', 'zyAdmin');
INSERT INTO `trole_tresource` VALUES ('zyglTreeGrid', 'zyAdmin');

-- ----------------------------
-- Table structure for tuser
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `ID` varchar(36) NOT NULL,
  `CREATEDATETIME` datetime DEFAULT NULL,
  `MODIFYDATETIME` datetime DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `PWD` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_doflky41g81kf7ydx6y0a99nm` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser
-- ----------------------------
INSERT INTO `tuser` VALUES ('0', '2014-05-12 13:53:50', null, 'jt56', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `tuser` VALUES ('1', '2014-05-12 13:53:50', null, 'admin1', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `tuser` VALUES ('2', '2014-05-12 13:53:50', null, 'admin2', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `tuser` VALUES ('3', '2014-05-12 13:53:50', null, 'admin3', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `tuser` VALUES ('4', '2014-05-12 13:53:50', null, 'admin4', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for tuser_trole
-- ----------------------------
DROP TABLE IF EXISTS `tuser_trole`;
CREATE TABLE `tuser_trole` (
  `TUSER_ID` varchar(36) NOT NULL,
  `TROLE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`TROLE_ID`,`TUSER_ID`),
  KEY `FK_30t0khk63muiwisjpp0h7e57l` (`TROLE_ID`),
  KEY `FK_mipcojqd9xymdghov18fobf7e` (`TUSER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser_trole
-- ----------------------------
INSERT INTO `tuser_trole` VALUES ('0', '0');
INSERT INTO `tuser_trole` VALUES ('2', 'jsAdmin');
INSERT INTO `tuser_trole` VALUES ('4', 'sjyAdmin');
INSERT INTO `tuser_trole` VALUES ('3', 'yhAdmin');
INSERT INTO `tuser_trole` VALUES ('1', 'zyAdmin');
