/*
 Navicat Premium Data Transfer

 Source Server         : Database
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : database

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 02/07/2020 22:32:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_limit
-- ----------------------------
DROP TABLE IF EXISTS `login_limit`;
CREATE TABLE `login_limit`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `level` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` int(100) NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `shipping_id` int(100) NOT NULL,
  `payment` decimal(20, 2) UNSIGNED NOT NULL,
  `payment_type` int(1) NULL DEFAULT NULL,
  `postage` decimal(20, 2) UNSIGNED NULL DEFAULT 0.00,
  `status` int(1) NOT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_time` datetime(0) NULL DEFAULT NULL,
  `send_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `close_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_index`(`order_no`, `user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (74, 74, 6, 7, 8.90, NULL, 0.00, 3, '', '2020-06-30 16:07:32', '2020-06-30 16:08:12', NULL, NULL, '2020-06-30 16:07:14', '2020-06-30 16:08:11');
INSERT INTO `order` VALUES (75, 75, 5, 15, 995.00, NULL, 0.00, 3, '', '2020-06-30 16:17:36', NULL, NULL, NULL, '2020-06-30 16:17:26', '2020-06-30 16:33:40');
INSERT INTO `order` VALUES (76, 76, 5, 15, 43.00, NULL, 0.00, 3, '', '2020-06-30 16:18:39', '2020-06-30 20:53:27', NULL, NULL, '2020-06-30 16:18:27', '2020-06-30 20:53:27');
INSERT INTO `order` VALUES (80, 80, 5, 15, 365.40, 1, 0.00, 2, '', '2020-07-02 20:33:26', NULL, NULL, NULL, '2020-07-02 20:32:59', '2020-07-02 20:33:28');
INSERT INTO `order` VALUES (81, 81, 5, 15, 12.00, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:39:02', '2020-07-02 20:39:02');
INSERT INTO `order` VALUES (82, 82, 5, 15, 8.90, 1, 0.00, 2, '', '2020-07-02 20:41:34', NULL, NULL, NULL, '2020-07-02 20:40:09', '2020-07-02 20:41:35');
INSERT INTO `order` VALUES (83, 83, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:41:04', '2020-07-02 20:41:04');
INSERT INTO `order` VALUES (84, 84, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:42:27', '2020-07-02 20:42:27');
INSERT INTO `order` VALUES (85, 85, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:42:49', '2020-07-02 20:42:49');
INSERT INTO `order` VALUES (86, 86, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:43:03', '2020-07-02 20:43:03');
INSERT INTO `order` VALUES (87, 87, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:43:54', '2020-07-02 20:43:54');
INSERT INTO `order` VALUES (88, 88, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:45:34', '2020-07-02 20:45:34');
INSERT INTO `order` VALUES (89, 89, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:45:38', '2020-07-02 20:45:38');
INSERT INTO `order` VALUES (90, 90, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:45:55', '2020-07-02 20:45:55');
INSERT INTO `order` VALUES (91, 91, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:45:59', '2020-07-02 20:45:59');
INSERT INTO `order` VALUES (92, 92, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:46:02', '2020-07-02 20:46:02');
INSERT INTO `order` VALUES (93, 93, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:46:09', '2020-07-02 20:46:09');
INSERT INTO `order` VALUES (94, 94, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:47:14', '2020-07-02 20:47:14');
INSERT INTO `order` VALUES (95, 95, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:47:18', '2020-07-02 20:47:18');
INSERT INTO `order` VALUES (96, 96, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:47:22', '2020-07-02 20:47:22');
INSERT INTO `order` VALUES (97, 97, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:47:27', '2020-07-02 20:47:27');
INSERT INTO `order` VALUES (98, 98, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:49:37', '2020-07-02 20:49:37');
INSERT INTO `order` VALUES (99, 99, 5, 15, 8.90, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:50:01', '2020-07-02 20:50:01');
INSERT INTO `order` VALUES (71, 71, 8, 16, 12.00, NULL, 0.00, 3, '', NULL, NULL, NULL, NULL, '2020-06-30 15:28:35', '2020-06-30 16:31:50');
INSERT INTO `order` VALUES (79, 79, 5, 15, 33.70, 1, 0.00, 1, '', NULL, NULL, NULL, NULL, '2020-07-02 20:26:44', '2020-07-02 20:26:44');
INSERT INTO `order` VALUES (73, 73, 8, 16, 995.00, NULL, 0.00, 3, '', '2020-06-30 15:31:13', '2020-06-30 16:05:56', NULL, NULL, '2020-06-30 15:30:41', '2020-06-30 16:05:56');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` int(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `current_unit_price` decimal(20, 2) UNSIGNED NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` decimal(20, 2) UNSIGNED NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (70, 83, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:41:04', NULL);
INSERT INTO `order_detail` VALUES (71, 84, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:42:27', NULL);
INSERT INTO `order_detail` VALUES (72, 85, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:42:49', NULL);
INSERT INTO `order_detail` VALUES (73, 86, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:43:03', NULL);
INSERT INTO `order_detail` VALUES (74, 87, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:43:54', NULL);
INSERT INTO `order_detail` VALUES (75, 88, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:45:34', NULL);
INSERT INTO `order_detail` VALUES (76, 89, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:45:38', NULL);
INSERT INTO `order_detail` VALUES (77, 90, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:45:55', NULL);
INSERT INTO `order_detail` VALUES (78, 91, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:45:59', NULL);
INSERT INTO `order_detail` VALUES (79, 92, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:46:02', NULL);
INSERT INTO `order_detail` VALUES (80, 93, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:46:09', NULL);
INSERT INTO `order_detail` VALUES (81, 94, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:47:14', NULL);
INSERT INTO `order_detail` VALUES (82, 95, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:47:18', NULL);
INSERT INTO `order_detail` VALUES (83, 96, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:47:22', NULL);
INSERT INTO `order_detail` VALUES (84, 97, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:47:27', NULL);
INSERT INTO `order_detail` VALUES (85, 98, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:49:37', NULL);
INSERT INTO `order_detail` VALUES (86, 99, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:50:01', NULL);
INSERT INTO `order_detail` VALUES (69, 82, 5, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-07-02 20:40:09', NULL);
INSERT INTO `order_detail` VALUES (68, 81, 5, 2, '可口可乐（樱桃）', 12.00, 1, 12.00, '2020-07-02 20:39:02', NULL);
INSERT INTO `order_detail` VALUES (66, 80, 5, 15, '法国Perrier巴黎水西柚柠檬含气天然矿泉水330ml*12', 119.00, 3, 357.00, '2020-07-02 20:33:00', NULL);
INSERT INTO `order_detail` VALUES (67, 80, 5, 6, '乐事薯片', 4.20, 2, 8.40, '2020-07-02 20:33:00', NULL);
INSERT INTO `order_detail` VALUES (60, 76, 5, 2, '可口可乐（樱桃）', 12.00, 3, 36.00, '2020-06-30 16:18:27', NULL);
INSERT INTO `order_detail` VALUES (59, 76, 5, 3, '芬达', 3.50, 2, 7.00, '2020-06-30 16:18:27', NULL);
INSERT INTO `order_detail` VALUES (58, 75, 5, 14, 'Martell/马爹利蓝带干邑白兰地500ml进口洋酒 官方正品 ', 995.00, 1, 995.00, '2020-06-30 16:17:26', NULL);
INSERT INTO `order_detail` VALUES (57, 74, 6, 1, '可口可乐（香草）', 8.90, 1, 8.90, '2020-06-30 16:07:15', NULL);
INSERT INTO `order_detail` VALUES (53, 71, 8, 2, '可口可乐（樱桃）', 12.00, 1, 12.00, '2020-06-30 15:28:35', NULL);
INSERT INTO `order_detail` VALUES (65, 79, 5, 3, '芬达', 3.50, 2, 7.00, '2020-07-02 20:26:44', NULL);
INSERT INTO `order_detail` VALUES (64, 79, 5, 1, '可口可乐（香草）', 8.90, 3, 26.70, '2020-07-02 20:26:44', NULL);
INSERT INTO `order_detail` VALUES (56, 73, 8, 14, 'Martell/马爹利蓝带干邑白兰地500ml进口洋酒 官方正品 ', 995.00, 1, 995.00, '2020-06-30 15:30:41', NULL);

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
  `detail` varchar(15000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(20, 2) NULL DEFAULT NULL,
  `stock` int(255) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES (1, 4, '可口可乐（香草）', '当前打八五折', 'drinks/carbonic/cola/cola(vanila).png', 'xxx.jpg', '<p><img width=\"13\" height=\"13\" src=\"https://gd2.alicdn.com/tps/i2/T1BzTmXnNbXXX1azE.-13-13.png\">                                        生产日期: 2020年02月28日 至 2020年02月28日                                                    </p><p>                           </p><p>                        </p><p>&nbsp;</p><p><img src=\"https://img.alicdn.com/imgextra/i3/3821028395/O1CN01jGLRsx2BsxZvXD3MR_!!3821028395.jpg\"></p><p><img alt=\"香草可乐2.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3821028395/O1CN01miDOfq2BsxZbsC8aZ_!!3821028395.jpg\"><br><img alt=\"3.jpg\" src=\"https://img.alicdn.com/imgextra/i1/3821028395/O1CN013P5OVm2BsxZavwyX2_!!3821028395.jpg\"><br><img alt=\"2.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3821028395/O1CN01Ew3FeW2BsxZZwLcjM_!!3821028395.jpg\"><br><img alt=\"4.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3821028395/O1CN01ya42X12BsxZckuZvr_!!3821028395.jpg\"><br><img alt=\"5.jpg\" src=\"https://img.alicdn.com/imgextra/i3/3821028395/O1CN01qnQUrU2BsxZaPsAjE_!!3821028395.jpg\"></p>', 8.90, 105, 1, '2020-06-19 21:06:33', '2020-06-30 11:10:56');
INSERT INTO `sales` VALUES (2, 4, '可口可乐（樱桃）', '不打折', 'drinks/carbonic/cola/cola(cherry).png', 'xxx.jpg', '<p>可口可乐樱桃味</p>', 12.00, 1, 1, '2020-06-20 16:18:35', '2020-06-29 23:47:39');
INSERT INTO `sales` VALUES (3, 1, '芬达', '买2送1', 'drinks/carbonic/fanta/fanta.png', 'drinks/carbonic/fanta/fanta.png', '芬达好喝', 3.50, 50, 1, '2020-06-21 16:24:38', '2020-06-22 15:19:25');
INSERT INTO `sales` VALUES (4, 1, '黑沙松士', '保证好喝到吐', 'drinks/carbonic/sasae.png', 'drinks/carbonic/sasae.png', '<p>								</p><p>									</p><p><span style=\"color: rgb(64, 64, 64);\">		</span>				 		 																	 </p><p>											</p><p>										     	    																											    	</p><p><br><span style=\"color: rgb(64, 64, 64);\">												</span>				</p><p><span style=\"color: rgb(64, 64, 64);\">	                		                     </span>	        </p><p><img src=\"https://img.alicdn.com/imgextra/i2/495474633/O1CN019UixHX1k5xT6vn2CV_!!495474633.jpg\"><img src=\"https://img.alicdn.com/imgextra/i2/495474633/O1CN01qFgt9v1k5xT8esuai_!!495474633.jpg\"><img src=\"https://img.alicdn.com/imgextra/i2/495474633/O1CN01RGQ80a1k5xZLF38D3_!!495474633.jpg\"></p><p> </p><p><span style=\"color: rgb(64, 64, 64);\">	                    </span>			</p><p>	    <img width=\"790\" src=\"https://img.alicdn.com/tfs/TB1.CUdsY9YBuNjy0FgXXcxcXXa-1572-394.png\"></p>', 5.00, 3, 1, '2020-06-21 17:13:10', '2020-06-29 17:00:13');
INSERT INTO `sales` VALUES (5, 1, '波子汽水', '日本畅销饮料', 'drinks/carbonic/bourbon.png', 'drinks/carbonic/bourbon.png', '波子汽水', 9.80, 100, 0, '2020-06-21 17:16:36', '2020-06-30 11:11:00');
INSERT INTO `sales` VALUES (6, 6, '乐事薯片', '乐事出品', 'food/snack/lets.png', 'food/snack/lets.png', '乐事原味薯片', 4.20, 500, 1, '2020-06-22 01:50:23', '2020-06-30 11:11:08');
INSERT INTO `sales` VALUES (14, 1, 'Martell/马爹利蓝带干邑白兰地500ml进口洋酒 官方正品 ', '白兰地', 'drinks/33c55d8c316c4e1aa439ed1b94a027da.jpg', 'xxx.jpg', '<p>&nbsp;</p><p><span style=\"color: rgb(255, 0, 0);\">声明：为了提高消费者体验，现在Martell/马爹利蓝带干邑白兰地500ml进口洋酒大部分仓开始售卖有盒产品，由于前期售卖的都是光瓶，部分仓还有货品，因此光瓶和有盒混合发货，还请大家见谅！</span></p><p><img src=\"https://img.alicdn.com/imgextra/i3/2203084792908/O1CN01Dcb7gD1XLuMMxU9YS_!!2203084792908-1-scmitem6000.gif\">&nbsp;</p><p><img src=\"https://img.alicdn.com/imgextra/i3/2203084792908/O1CN01xbpIzv1XLuMTSuWIe_!!2203084792908-0-scmitem6000.jpg\">&nbsp;</p><p><img src=\"https://img.alicdn.com/imgextra/i3/2203084792908/O1CN01lLhg2V1XLuMOs1IcO_!!2203084792908-0-scmitem6000.jpg\">&nbsp;</p><p><img src=\"https://img.alicdn.com/imgextra/i3/2203084792908/O1CN01X0Bnbh1XLuMSjiWoZ_!!2203084792908-0-scmitem6000.jpg\"></p><p><img src=\"https://img.alicdn.com/imgextra/i1/2203084792908/O1CN0100GGQu1XLuLi6uLmh_!!2203084792908-0-scmitem6000.jpg\"></p><p><img src=\"https://img.alicdn.com/imgextra/i2/2203084792908/O1CN01Mo4rqE1XLuLgKHDOB_!!2203084792908-0-scmitem6000.jpg\"></p><p><img src=\"https://img.alicdn.com/imgextra/i1/2203084792908/O1CN01NbzLiR1XLuLY2K7rC_!!2203084792908-0-scmitem6000.jpg\"></p><p><img src=\"https://img.alicdn.com/imgextra/i2/2203084792908/O1CN01OQDsYt1XLuLhtptn0_!!2203084792908-0-scmitem6000.jpg\"></p><p><img src=\"https://img.alicdn.com/imgextra/i1/2203084792908/O1CN01SMcyUJ1XLuLfxkwEZ_!!2203084792908-0-scmitem6000.jpg\"></p><p><img src=\"https://img.alicdn.com/imgextra/i3/2203084792908/O1CN01GwK2xg1XLuLk8EMNS_!!2203084792908-0-scmitem6000.jpg\"></p><p><img width=\"790\" src=\"https://img.alicdn.com/tfs/TB1.CUdsY9YBuNjy0FgXXcxcXXa-1572-394.png\"></p><h4>售后服务</h4><p>正品保障天猫超市出售均为正品，承诺提供“正品保障”服务。</p><p>提供发票对于在天猫超市购货的买家均提供商品发票</p><p>7天退货对于在天猫超市购物的买家提供“7天无理由退货”保障服务</p><p>天猫超市购物承诺</p><ul><li>1、天猫超市所有在售商品均可开具正规发票</li><li>2、天猫超市所有在售商品均保障正品</li></ul><p>退货说明与流程</p><p><img width=\"710\" height=\"123\" src=\"https://img.alicdn.com/tps/i1/T1FyZ4FaFbXXXUVYrv-710-123.jpg\"></p><p>退货服务标准</p><p>支持“7天无理由退货”服务：</p><ul><li>天猫超市出售的商品可以享受“7天无理由退货”服务，除天猫官方规定不支持7天无理由退换服务的类目商品外；</li><li>退货商品请保证是未使用过，不影响二次销售（质量问题的商品除外）；</li><li>退货提交有效期：在确认签收商品之日起7天内提交（以物流签收时间为准）。</li></ul><p>退货费用说明：</p><ul><li>如果是买家原因退货，由买家承担退货费用。</li><li>如果是商品质量问题而导致的退货，退货费用由天猫超市承担。</li></ul><p>退货提交方式：</p><ul><li>在线申请：进入“我的淘宝”—“已买到的宝贝”，选中该笔交易，点击“退款/退货”，提交退货申请；</li><li>电话申请：拨打电话400-988-8898。</li></ul><p>安全提示：</p><p>请勿随意接收任何来源不明的文件，请勿随意点击任何来源不明的链接。涉及资金往来的事项请务必仔细核对资金往来信息。 天猫不会以订单有问题，让您提供任何银行卡、密码、手机验证码！遇到可疑情况可在钱盾“诈骗举报”中进行举报,&nbsp;<a href=\"https://qd.alibaba.com/go/v/pcdetail\" target=\"_blank\">安全推荐</a></p>', 995.00, 12, 1, '2020-06-29 23:23:19', NULL);
INSERT INTO `sales` VALUES (15, 1, '法国Perrier巴黎水西柚柠檬含气天然矿泉水330ml*12', '灵感水气泡水', 'drinks/8c6df94d5d7d4e9193b48cbb1324fdda.jpg', 'xxx.jpg', '<p><img alt=\"330柠檬 西柚_01.jpg\" src=\"https://img.alicdn.com/imgextra/i3/3932427524/O1CN01yozlsg25S2XcPMdfG_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_02.jpg\" src=\"https://img.alicdn.com/imgextra/i1/3932427524/O1CN018xFMA325S2XZqXTYV_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_03.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3932427524/O1CN019nGjg425S2XZ77RwT_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_04.jpg\" src=\"https://img.alicdn.com/imgextra/i3/3932427524/O1CN01YFKwWD25S2XceTha9_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_05.jpg\" src=\"https://img.alicdn.com/imgextra/i3/3932427524/O1CN01DKNAWZ25S2Xbj5uW1_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_06.jpg\" src=\"https://img.alicdn.com/imgextra/i1/3932427524/O1CN01gRnzmI25S2XceTZHz_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_07.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3932427524/O1CN01ZOQWsF25S2XZjHrfm_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_08.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3932427524/O1CN01IRk9NG25S2XZ76Ayw_!!3932427524.jpg\"><img alt=\"330柠檬 西柚_09.jpg\" src=\"https://img.alicdn.com/imgextra/i4/3932427524/O1CN01oa470j25S2XagHQwu_!!3932427524.jpg\"></p><p> </p><p><span style=\"color: rgb(64, 64, 64);\">	                    </span>				    </p>', 119.00, 255, 1, '2020-06-30 13:51:42', NULL);
INSERT INTO `sales` VALUES (16, 17, '橙子', '10元1斤', 'food/null/c4205624417149bc9d24422ed2d6d566.jpg', 'xxx.jpg', '<p><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01szNd7S1R1Ow0w1ya8_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01bOU0HK1R1OvYJjcaU_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i2/2939922051/O1CN01It70Mg1R1OvQPhbOd_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01h49BF31R1OvXwItpJ_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01lipZln1R1OvWRmWpz_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i4/2939922051/O1CN01eeU01h1R1Ovcng2oF_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i2/2939922051/O1CN010aFyMy1R1OvYJhXil_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i2/2939922051/O1CN01l9WdvI1R1OvXwKuWW_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i3/2939922051/O1CN01k9M0931R1OvYJj1Ca_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i2/2939922051/O1CN01JJZeGU1R1OvYJh4dH_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01LLjjN01R1OvV7I5cs_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01XDLTve1R1OwBSKZNt_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01WQm7w51R1Ovd6Cqhu_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i4/2939922051/O1CN01wqFH371R1Ovd6Bi20_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i4/2939922051/O1CN01XkJSb71R1Otmqopsf_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i4/2939922051/O1CN01lzfnf01R1OtgGuaK3_!!2939922051.jpg\"><img src=\"https://img.alicdn.com/imgextra/i1/2939922051/O1CN01NMQCqO1R1OtjAJQfE_!!2939922051.jpg\"></p><p><img width=\"790\" src=\"https://img.alicdn.com/tfs/TB1.CUdsY9YBuNjy0FgXXcxcXXa-1572-394.png\"></p>', 10.00, 100, 1, '2020-07-02 21:37:14', NULL);

-- ----------------------------
-- Table structure for shipping_addr
-- ----------------------------
DROP TABLE IF EXISTS `shipping_addr`;
CREATE TABLE `shipping_addr`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `receiver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_district` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_zip` int(6) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shipping_addr
-- ----------------------------
INSERT INTO `shipping_addr` VALUES (2, 2, '吴彦祖', '022-11144514', '1145147777', '天津', '天津', '天津市', '天津市', 300131, '2020-06-24 17:00:45', '2020-06-25 00:33:23');
INSERT INTO `shipping_addr` VALUES (16, 8, 'PENG', NULL, '17806504678', '江西', '台州', '余杭区', '78号', 563987, '2020-06-30 15:17:28', '2020-06-30 15:17:28');
INSERT INTO `shipping_addr` VALUES (15, 5, 'PENG', NULL, '17806504678', '福建', '绍兴', '拱墅区', '78号', 563987, '2020-06-30 10:00:31', '2020-06-30 10:00:31');
INSERT INTO `shipping_addr` VALUES (7, 6, 'ZBC', NULL, '13133564968', '浙江', '温州', '余杭区', '江南皮革厂', 114514, '2020-06-29 23:55:57', '2020-06-29 23:55:57');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (5, 2, 2, 1, 1, '2020-06-23 09:59:05', '2020-06-23 09:59:09');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `en_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 0, '饮料', 'drinks', '2020-03-21 00:00:00', '2020-06-24 00:47:06', 1);
INSERT INTO `type` VALUES (2, 1, '碳酸饮料', 'carbonic', '2020-06-18 23:31:23', '2020-06-24 00:47:10', 1);
INSERT INTO `type` VALUES (3, 2, '芬达', 'fanta', '2020-06-18 23:44:14', '2020-06-24 00:47:14', 1);
INSERT INTO `type` VALUES (4, 2, '可乐', 'cola', '2020-06-19 12:37:55', '2020-06-24 00:47:16', 1);
INSERT INTO `type` VALUES (5, 0, '食品', 'food', '2020-06-22 15:06:36', '2020-06-24 00:47:22', 1);
INSERT INTO `type` VALUES (6, 5, '干果', 'snack', '2020-06-22 15:07:12', '2020-07-01 10:27:33', 1);
INSERT INTO `type` VALUES (8, 1, '茶', 'tea', '2020-06-23 09:06:46', '2020-06-30 09:56:15', 1);
INSERT INTO `type` VALUES (9, 2, '雪碧', 'sprite', '2020-06-23 09:09:45', '2020-06-24 00:47:43', 1);
INSERT INTO `type` VALUES (13, 8, '绿茶', NULL, '2020-06-29 23:03:57', NULL, 1);
INSERT INTO `type` VALUES (14, 8, '红茶', NULL, '2020-06-29 23:06:23', NULL, 1);
INSERT INTO `type` VALUES (15, 13, '茉莉绿茶', NULL, '2020-06-29 23:06:38', NULL, 1);
INSERT INTO `type` VALUES (16, 8, '冰红茶', NULL, '2020-06-30 09:56:27', NULL, 1);
INSERT INTO `type` VALUES (17, 5, '水果', NULL, '2020-07-01 10:17:45', NULL, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '1234', '1101', '123456@qq.com', '用户', '生日', '5.06', '2020-05-20 00:00:00', '2020-05-20 00:00:00');
INSERT INTO `user` VALUES (2, '123456', '12345', '13133132323', '111@qq.com', '管理员', '你吃了吗？', '吃了', '2020-06-17 21:05:55', '2020-06-22 10:46:38');
INSERT INTO `user` VALUES (3, '17800', '8080', '888', '8848', '用户', '', '', '2020-06-19 18:10:42', '2020-06-19 18:10:42');
INSERT INTO `user` VALUES (4, '小黑吃了吗', '111', '123456123', '114514@qq.com', '用户', '没有问题', '没有答案', '2020-06-22 14:12:28', '2020-06-22 14:12:41');
INSERT INTO `user` VALUES (5, '123', '123', '147893635', '123456', '用户', 'who am I', 'Peng', '2020-06-19 19:31:40', '2020-07-02 20:25:47');
INSERT INTO `user` VALUES (6, '无情哈拉少', '12345', '13133564968', '13133564968@qq.com', '用户', '123', '123', '2020-06-29 23:37:58', '2020-06-29 23:43:17');
INSERT INTO `user` VALUES (7, '17012', '123', '123', '123', '用户', '123', '123', '2020-06-30 08:30:20', '2020-06-30 08:30:20');
INSERT INTO `user` VALUES (8, 'jerex', '123', '17800305636', '789', '用户', '123', '123', '2020-06-30 14:17:43', '2020-06-30 14:22:58');
INSERT INTO `user` VALUES (9, '1', '1', '1', '1', '用户', '1', '1', '2020-07-01 10:57:58', '2020-07-01 10:57:58');
INSERT INTO `user` VALUES (10, 'asdasd', 'aaa', 'asdasd', 'asd', '用户', 'asd', 'asd', '2020-07-01 10:59:32', '2020-07-01 10:59:32');

SET FOREIGN_KEY_CHECKS = 1;
