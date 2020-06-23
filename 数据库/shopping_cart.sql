/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : database

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 23/06/2020 10:18:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(12) NOT NULL,
  `checked` int(1) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `sales` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (1, 1, 1, 1, 1, '2020-03-21 00:00:00', '2020-06-22 11:14:53');
INSERT INTO `shopping_cart` VALUES (2, 1, 2, 2, 1, '2020-06-23 09:24:30', '2020-06-23 09:47:03');
INSERT INTO `shopping_cart` VALUES (3, 1, 4, 4, 1, '2020-06-23 09:50:24', '2020-06-23 09:50:28');
INSERT INTO `shopping_cart` VALUES (4, 1, 3, 3, 1, '2020-06-23 09:58:45', '2020-06-23 09:58:47');
INSERT INTO `shopping_cart` VALUES (5, 2, 2, 1, 1, '2020-06-23 09:59:05', '2020-06-23 09:59:09');

SET FOREIGN_KEY_CHECKS = 1;
