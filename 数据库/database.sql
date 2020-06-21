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

 Date: 21/06/2020 21:51:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `shipping_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `payment` decimal(20, 2) UNSIGNED NOT NULL,
  `payment_type` int(1) NOT NULL,
  `postage` decimal(20, 2) UNSIGNED NULL DEFAULT 0.00,
  `status` int(1) NOT NULL,
  `payment_time` datetime(0) NOT NULL,
  `send_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  `close_time` datetime(0) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `order_index`(`order_no`, `user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `current_unit__price` decimal(20, 2) UNSIGNED NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` decimal(20, 2) UNSIGNED NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_no`(`order_no`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pay_platform` int(1) NOT NULL,
  `platform_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `platform_status` datetime(0) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `main_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sub_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(20, 2) NULL DEFAULT NULL,
  `stock` int(255) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES (1, 5, '可口可乐（香草）', '当前打八五折', 'images/goods/1.png', 'images/goods/1.png,images/goods/2.png', '可口可乐香草味', 8.90, 105, 1, '2020-06-19 21:06:33', '2020-06-21 21:47:48');
INSERT INTO `sales` VALUES (2, 6, '可口可乐（樱桃）', '不打折', 'images/goods/2.png', 'images/goods/2.png', '可口可乐樱桃味', 8.90, 100, 1, '2020-06-20 16:18:35', '2020-06-21 21:47:49');
INSERT INTO `sales` VALUES (3, 3, '芬达', '买3送1', 'images/goods/3.png', 'images/goods/3.png', '芬达', 3.00, 20, 1, '2020-06-21 16:24:38', '2020-06-21 21:47:52');
INSERT INTO `sales` VALUES (4, 1, '黑沙松士', '保证好喝到吐', 'images/goods/4.png', 'images/goods/4.png', '黑沙松士', 5.00, 3, 1, '2020-06-21 17:13:10', '2020-06-21 21:47:51');
INSERT INTO `sales` VALUES (5, 1, '波子汽水', '日本畅销饮料', 'images/goods/5.png', 'images/goods/5.png', '波子汽水', 9.80, 100, 1, '2020-06-21 17:16:36', '2020-06-21 21:47:54');

-- ----------------------------
-- Table structure for shipping_addr
-- ----------------------------
DROP TABLE IF EXISTS `shipping_addr`;
CREATE TABLE `shipping_addr`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `receiver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_mobile` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_zip` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shopping car
-- ----------------------------
DROP TABLE IF EXISTS `shopping car`;
CREATE TABLE `shopping car`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `checked` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping car
-- ----------------------------
INSERT INTO `shopping car` VALUES (1, 1, '                1', '              1', '是', '2020-03-21 00:00:00', '2020-03-21 00:00:00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 0, '饮料', '2020-03-21 00:00:00', '2020-06-19 12:37:38', 1);
INSERT INTO `type` VALUES (2, 1, '碳酸饮料', '2020-06-18 23:31:23', '2020-06-19 12:37:48', 1);
INSERT INTO `type` VALUES (3, 2, '芬达', '2020-06-18 23:44:14', '2020-06-19 12:37:50', 1);
INSERT INTO `type` VALUES (4, 2, '可乐', '2020-06-19 12:37:55', '2020-06-19 12:38:01', 1);
INSERT INTO `type` VALUES (5, 4, '可乐（香草味）', '2020-06-19 12:38:15', NULL, 1);
INSERT INTO `type` VALUES (6, 4, '可乐（樱桃味）', '2020-06-19 12:38:51', '2020-06-19 12:38:59', 1);
INSERT INTO `type` VALUES (7, 4, '可乐（健怡）', '2020-06-19 12:39:16', NULL, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '1234', '1101', '123456@qq.com', '用户', '生日', '5.06', '2020-05-20 00:00:00', '2020-05-20 00:00:00');
INSERT INTO `user` VALUES (2, '123456', '1234', '13133132323', '111@qq.com', '管理员', '你吃了吗？', '吃了', '2020-06-17 21:05:55', '2020-06-19 12:52:13');
INSERT INTO `user` VALUES (3, '17800', '8080', '888', '8848', '用户', '', '', '2020-06-19 18:10:42', '2020-06-19 18:10:42');
INSERT INTO `user` VALUES (5, '123', '123', '147893635', '123456', '用户', '', '', '2020-06-19 19:31:40', '2020-06-19 19:31:40');

SET FOREIGN_KEY_CHECKS = 1;
