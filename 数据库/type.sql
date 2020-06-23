/*
 Navicat Premium Data Transfer

 Source Server         : location
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : database

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 23/06/2020 15:57:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 0, '饮料', '2020-03-21 00:00:00', '2020-06-19 12:37:38', 1);
INSERT INTO `type` VALUES (2, 1, '碳酸饮料', '2020-06-18 23:31:23', '2020-06-19 12:37:48', 1);
INSERT INTO `type` VALUES (3, 2, '芬达', '2020-06-18 23:44:14', '2020-06-19 12:37:50', 1);
INSERT INTO `type` VALUES (4, 2, '可乐', '2020-06-19 12:37:55', '2020-06-19 12:38:01', 1);
INSERT INTO `type` VALUES (5, 0, '食品', '2020-06-22 15:06:36', '2020-06-22 15:06:42', 1);
INSERT INTO `type` VALUES (6, 5, '零食', '2020-06-22 15:07:12', '2020-06-22 15:07:20', 1);
INSERT INTO `type` VALUES (8, 1, '茶', '2020-06-23 09:06:46', '2020-06-23 10:21:46', 1);
INSERT INTO `type` VALUES (9, 2, '雪碧', '2020-06-23 09:09:45', NULL, 1);
INSERT INTO `type` VALUES (10, 5, '薯', '2020-06-23 14:10:20', '2020-06-23 14:10:33', 1);
INSERT INTO `type` VALUES (11, 5, '点心', '2020-06-23 14:37:26', '2020-06-23 14:37:42', 1);
INSERT INTO `type` VALUES (12, 11, '达利园', '2020-06-23 15:32:04', '2020-06-23 15:32:08', 1);

SET FOREIGN_KEY_CHECKS = 1;
