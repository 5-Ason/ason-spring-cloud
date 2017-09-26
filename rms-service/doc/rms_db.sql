SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rms_dept
-- ----------------------------
DROP TABLE IF EXISTS `rms_dept`;
CREATE TABLE `rms_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Records of rms_dept
-- ----------------------------
INSERT INTO `rms_dept` VALUES ('1', '开发一部', null);
INSERT INTO `rms_dept` VALUES ('2', '开发二部', null);

-- ----------------------------
-- Table structure for rms_menu
-- ----------------------------
DROP TABLE IF EXISTS `rms_menu`;
CREATE TABLE `rms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `levels` int(11) DEFAULT NULL COMMENT '层次，便于判别 属于几级根目录 ',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(512) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `status` tinyint(2) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of rms_menu
-- ----------------------------

-- ----------------------------
-- Table structure for rms_role
-- ----------------------------
DROP TABLE IF EXISTS `rms_role`;
CREATE TABLE `rms_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `create_user_id` bigint(11) DEFAULT NULL COMMENT '创建者id',
  `create_time` int(11) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of rms_role
-- ----------------------------
INSERT INTO `rms_role` VALUES ('1', 'admin', '超级管理员', '0', '1505294344');

-- ----------------------------
-- Table structure for rms_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `rms_role_menu`;
CREATE TABLE `rms_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of rms_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for rms_user
-- ----------------------------
DROP TABLE IF EXISTS `rms_user`;
CREATE TABLE `rms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(64) DEFAULT NULL COMMENT '登录账号(邮箱格式)',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别(1：男，2：女)',
  `birthday` varchar(32) DEFAULT NULL COMMENT '生日',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `create_time` bigint(11) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of rms_user
-- ----------------------------
INSERT INTO `rms_user` VALUES ('4', '412241262@qq.com', '111111', 'test', '1', null, null, '1', '1', '1', '1505016797', '0');
INSERT INTO `rms_user` VALUES ('6', '11@qq.com', '111111', '11', '1', null, null, '1', '2', '1', '1505027845', '4');
INSERT INTO `rms_user` VALUES ('9', 'test@163.com', '789456', '32', '2', null, null, null, null, '1', '1505295438', '6');
