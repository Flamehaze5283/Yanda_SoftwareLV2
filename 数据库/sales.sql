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

 Date: 23/06/2020 16:14:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
