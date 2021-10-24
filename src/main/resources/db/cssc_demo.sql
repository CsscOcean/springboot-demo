/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : cssc_demo

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 24/10/2021 11:48:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标签',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_role_re_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_role_re_resource`;
CREATE TABLE `s_role_re_resource`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色资源关联id',
  `role_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '资源id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE,
  CONSTRAINT `s_role_re_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `s_role_re_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `s_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色资源关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `is_account_non_expired` tinyint(1) NULL DEFAULT NULL COMMENT '帐户未过期',
  `is_account_non_locked` tinyint(1) NULL DEFAULT NULL COMMENT '帐户未锁定',
  `is_credentials_non_expired` tinyint(1) NULL DEFAULT NULL COMMENT '凭证未过期',
  `is_enabled` tinyint(1) NULL DEFAULT NULL COMMENT '已启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_user_re_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_re_role`;
CREATE TABLE `s_user_re_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户角色关联id',
  `user_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `s_user_re_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `s_user_re_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_role_re_resource
-- ----------------------------
DROP VIEW IF EXISTS `v_role_re_resource`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_role_re_resource` AS select `s_role_re_resource`.`id` AS `id`,`s_role_re_resource`.`role_id` AS `role_id`,`s_role_re_resource`.`resource_id` AS `resource_id`,`s_resource`.`name` AS `name`,`s_resource`.`url` AS `url`,`s_resource`.`description` AS `description`,`s_role_re_resource`.`create_time` AS `create_time`,`s_role_re_resource`.`update_time` AS `update_time` from (`s_role_re_resource` left join `s_resource` on((`s_resource`.`id` = `s_role_re_resource`.`resource_id`)));

-- ----------------------------
-- View structure for v_user_re_role
-- ----------------------------
DROP VIEW IF EXISTS `v_user_re_role`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_user_re_role` AS select `s_user_re_role`.`id` AS `id`,`s_user_re_role`.`user_id` AS `user_id`,`s_user_re_role`.`role_id` AS `role_id`,`s_role`.`tag` AS `tag`,`s_role`.`name` AS `name`,`s_role`.`description` AS `description`,`s_user_re_role`.`create_time` AS `create_time`,`s_user_re_role`.`update_time` AS `update_time` from (`s_user_re_role` left join `s_role` on((`s_role`.`id` = `s_user_re_role`.`role_id`)));

SET FOREIGN_KEY_CHECKS = 1;
