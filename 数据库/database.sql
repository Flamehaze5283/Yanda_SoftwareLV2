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

 Date: 23/06/2020 18:29:08
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES (1, 4, '可口可乐（香草）', '当前打八五折', 'drinks/carbonic/cola/cola(vanila).png', 'drinks/carbonic/cola/cola(vanila).png', '可口可乐香草味', 8.90, 105, 1, '2020-06-19 21:06:33', '2020-06-22 15:19:15');
INSERT INTO `sales` VALUES (2, 4, '可口可乐（樱桃）', '不打折', 'drinks/carbonic/cola/cola(cherry).png', 'drinks/carbonic/cola/cola(cherry)1.png', '可口可乐樱桃味', 8.90, 100, 1, '2020-06-20 16:18:35', '2020-06-22 15:19:20');
INSERT INTO `sales` VALUES (3, 1, '芬达', '买2送1', 'drinks/carbonic/fanta/fanta.png', 'drinks/carbonic/fanta/fanta.png', '芬达好喝', 3.50, 50, 1, '2020-06-21 16:24:38', '2020-06-22 15:19:25');
INSERT INTO `sales` VALUES (4, 1, '黑沙松士', '保证好喝到吐', 'drinks/carbonic/sasae.png', 'drinks/carbonic/sasae.png', '黑沙松士', 5.00, 3, 1, '2020-06-21 17:13:10', '2020-06-22 15:19:32');
INSERT INTO `sales` VALUES (5, 1, '波子汽水', '日本畅销饮料', 'drinks/carbonic/bourbon.png', 'drinks/carbonic/bourbon.png', '波子汽水', 9.80, 100, 1, '2020-06-21 17:16:36', '2020-06-22 15:19:38');
INSERT INTO `sales` VALUES (6, 6, '乐事薯片', '乐事出品', 'food/snack/lets.png', 'food/snack/lets.png', '乐事原味薯片', 4.20, 500, 1, '2020-06-22 01:50:23', '2020-06-22 15:11:22');

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
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `sales` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (1, 1, 1, 1, 1, '2020-03-21 00:00:00', '2020-06-22 11:14:53');
INSERT INTO `shopping_cart` VALUES (2, 1, 2, 2, 1, '2020-06-23 09:24:30', '2020-06-23 09:47:03');
INSERT INTO `shopping_cart` VALUES (3, 1, 4, 4, 1, '2020-06-23 09:50:24', '2020-06-23 09:50:28');
INSERT INTO `shopping_cart` VALUES (4, 1, 3, 3, 1, '2020-06-23 09:58:45', '2020-06-23 09:58:47');
INSERT INTO `shopping_cart` VALUES (5, 2, 2, 1, 1, '2020-06-23 09:59:05', '2020-06-23 09:59:09');

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
INSERT INTO `user` VALUES (2, '123456', '12345', '13133132323', '111@qq.com', '管理员', '你吃了吗？', '吃了', '2020-06-17 21:05:55', '2020-06-22 10:46:38');
INSERT INTO `user` VALUES (3, '17800', '8080', '888', '8848', '用户', '', '', '2020-06-19 18:10:42', '2020-06-19 18:10:42');
INSERT INTO `user` VALUES (4, '小黑吃了吗', '111', '123456123', '114514@qq.com', '用户', '没有问题', '没有答案', '2020-06-22 14:12:28', '2020-06-22 14:12:41');
INSERT INTO `user` VALUES (5, '123', '123', '147893635', '123456', '用户', 'who am I', 'Peng', '2020-06-19 19:31:40', '2020-06-23 09:40:01');

SET FOREIGN_KEY_CHECKS = 1;
