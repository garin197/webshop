/*
 Navicat Premium Data Transfer

 Source Server         : Vm仅主机Mysql-192.168.13.105
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.168.13.105:3306
 Source Schema         : db_webshop

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 16/04/2019 12:36:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'lin', '212');
INSERT INTO `t_admin` VALUES (2, 'zhou', 'zhou');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, '服装');
INSERT INTO `t_category` VALUES (5, '手表');
INSERT INTO `t_category` VALUES (10, '食品饮料');
INSERT INTO `t_category` VALUES (15, '生活家居');
INSERT INTO `t_category` VALUES (16, '电器');
INSERT INTO `t_category` VALUES (17, '饰品');
INSERT INTO `t_category` VALUES (18, '箱包');
INSERT INTO `t_category` VALUES (19, '母婴用品');
INSERT INTO `t_category` VALUES (20, '玩具');
INSERT INTO `t_category` VALUES (21, '礼品');
INSERT INTO `t_category` VALUES (22, '鞋靴');
INSERT INTO `t_category` VALUES (23, '其他');
INSERT INTO `t_category` VALUES (24, '化妆品');
INSERT INTO `t_category` VALUES (25, '手机数码');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `post` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮政编号',
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人信息',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `userMessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户备注信息',
  `createDate` datetime(0) DEFAULT NULL COMMENT '订单创建日期',
  `payDate` datetime(0) DEFAULT NULL COMMENT '支付日期',
  `deliveryDate` datetime(0) DEFAULT NULL COMMENT '发货日期',
  `confirmDate` datetime(0) DEFAULT NULL COMMENT '确认收货日期',
  `uid` int(11) DEFAULT NULL COMMENT 'user-id',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单状态',
  `deliver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发货状态',
  PRIMARY KEY (`orderId`) USING BTREE,
  INDEX `fk_order_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`uid`) REFERENCES `t_user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (51, '20190411202653000866', '1', '11111111111111111111', '111111111111111', '11', '', '2019-04-11 20:26:53', '2019-04-14 15:54:50', '2019-04-14 18:20:43', '2019-04-14 18:46:33', 1, '已收货', '已发货');
INSERT INTO `t_order` VALUES (52, '20190412201428000230', 'afsdf', 'sadfsad', 'sadfs', '1111111111111', '324214', '2019-04-12 20:14:28', '2019-04-14 15:54:46', '2019-04-07 04:50:37', '2019-04-16 12:31:50', 1, '已收货', '已发货');
INSERT INTO `t_order` VALUES (56, '20190413003127000699', '士大夫撒 ', '爽肤水', '111111111111111', '1111111111111', '', '2019-04-13 00:31:27', '2019-04-14 15:54:11', '2019-04-15 18:19:55', '2019-04-16 12:31:10', 1, '已收货', '已发货');
INSERT INTO `t_order` VALUES (58, '20190413134117000794', 'dsfadfdsaf', 'sdafsdaf', '111111111111111', '1111111111111', 'sda f', '2019-04-13 13:41:17', '2019-04-14 15:54:52', '2019-04-06 12:56:45', '2019-04-16 12:31:22', 1, '已收货', '已发货');
INSERT INTO `t_order` VALUES (59, '20190405183628000620', '结构化钢结构', '11111111111111111111', '111111111111111', '1111111111111', 'webshop-0.0.1-SNAPSHOT.jar', '2019-04-05 18:36:28', NULL, NULL, NULL, 1, '未付款', '未发货');

-- ----------------------------
-- Table structure for t_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `t_orderitem`;
CREATE TABLE `t_orderitem`  (
  `orderitemId` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `oId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderitemId`) USING BTREE,
  INDEX `pid`(`productId`) USING BTREE,
  INDEX `uid`(`userId`) USING BTREE,
  INDEX `t_orderitem_ibfk_3`(`oId`) USING BTREE,
  CONSTRAINT `t_orderitem_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `t_product` (`productId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `t_orderitem_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `t_orderitem_ibfk_3` FOREIGN KEY (`oId`) REFERENCES `t_order` (`orderId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_orderitem
-- ----------------------------
INSERT INTO `t_orderitem` VALUES (37, 129, 1, 1, 51);
INSERT INTO `t_orderitem` VALUES (38, 90, 1, 1, 52);
INSERT INTO `t_orderitem` VALUES (42, 133, 1, 1, 56);
INSERT INTO `t_orderitem` VALUES (44, 133, 1, 1, 58);
INSERT INTO `t_orderitem` VALUES (45, 129, 1, 2, 59);

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `productId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'key',
  `productName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品名称',
  `subTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小标题',
  `originalPrice` float DEFAULT NULL COMMENT '原始价格',
  `promotePrice` float DEFAULT NULL COMMENT '优惠价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `cid` int(11) DEFAULT NULL COMMENT 't_category-Id',
  `createDate` datetime(0) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`productId`) USING BTREE,
  INDEX `fk_product_category`(`cid`) USING BTREE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`cid`) REFERENCES `t_category` (`categoryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 181 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (90, '遮肚子雪纺衫长袖春装洋气小衫女宽松新款大码女装胖mm上衣妈妈装', '1', 69, 60, 78, 1, '2019-03-26 00:05:34');
INSERT INTO `t_product` VALUES (91, '女装 针织连衣裙(短袖) 413744 优衣库UNIQLO', '初上市价格249元', 199, 180, 981, 1, '2019-03-26 00:48:01');
INSERT INTO `t_product` VALUES (94, ' 水墨烟灰缸 方形小号陶瓷烟灰缸 个性时尚家居摆件 酒店办公烟缸', ' ', 40, 31, 2000, 15, '2019-03-26 01:01:02');
INSERT INTO `t_product` VALUES (95, '卫生间旋转毛巾杆免打孔浴室厨房抹布可活动多杆挂架不锈钢304', ' ', 100, 100, 102, 15, '2019-03-26 01:02:22');
INSERT INTO `t_product` VALUES (96, '创意实用家居用品居家用生活日用品百货店小玩意东西家庭日常宿舍', ' ', 8.8, 8.8, 202, 15, '2019-03-26 01:03:03');
INSERT INTO `t_product` VALUES (97, '创意家居厨房用品用具小百货生活实用神器懒人小东西日用品居家用', ' ', 49, 49, 22, 15, '2019-03-26 01:03:57');
INSERT INTO `t_product` VALUES (99, '创意小礼品定制印logo公司活动礼品开业促销礼品居家实用广告宣传', ' ', 12.9, 12.9, 301, 15, '2019-03-26 01:04:44');
INSERT INTO `t_product` VALUES (105, '正品乳酸菌饮品整箱340mlx12瓶益生菌饮料儿童发酵酸奶牛奶早餐奶', ' ', 69, 69, 50, 10, '2019-03-26 01:22:56');
INSERT INTO `t_product` VALUES (106, '19年新货正宗唯椰椰子汁245ml*16罐装整箱椰肉鲜榨椰汁饮料椰奶', ' ', 28.9, 28.9, 555, 10, '2019-03-26 01:23:50');
INSERT INTO `t_product` VALUES (107, '好想你清菲菲礼盒红枣湘莲银耳汤冻干364g 冲泡速食方便食品早餐', ' ', 139, 139, 500, 10, '2019-03-26 01:24:26');
INSERT INTO `t_product` VALUES (108, '【周黑鸭旗舰店_锁鲜】气调盒装卤鸭脖320g 武汉特产食品零食小吃', ' ', 36.9, 36.9, 500, 10, '2019-03-26 01:24:50');
INSERT INTO `t_product` VALUES (110, '上好旺宫廷小桃酥饼干浙江特产传统糕点整箱小包装休闲零食品小吃', ' ', 78, 78, 780, 10, '2019-03-26 01:25:21');
INSERT INTO `t_product` VALUES (111, '亲润孕妇面膜孕妇专用补水保湿天然面贴膜孕期专用护肤化妆品套装', ' ', 258, 258, 555, 24, '2019-03-26 01:26:03');
INSERT INTO `t_product` VALUES (112, '袋鼠妈妈 孕妇睡眠面膜天然保湿补水面膜孕妇护肤品化妆品', ' ', 88, 88, 100, 24, '2019-03-26 01:26:36');
INSERT INTO `t_product` VALUES (113, 'HFP金盏花舒缓爽肤水 控油收缩毛孔补水保湿健康水化妆护肤品男女', ' ', 279, 279, 279, 24, '2019-03-26 01:27:14');
INSERT INTO `t_product` VALUES (114, '美宝莲fitme粉底液 轻薄控油遮瑕液膏霜持久隐形毛孔化妆品旗舰店', ' ', 139, 139, 230, 24, '2019-03-26 01:31:16');
INSERT INTO `t_product` VALUES (115, '袋鼠妈妈 茶树无瑕新肌面膜28片 孕妇补水面膜 护肤品化妆品', ' ', 498, 498, 230, 24, '2019-03-26 01:31:42');
INSERT INTO `t_product` VALUES (116, '软皮女包中老年人大包中年女士妈妈包大容量单肩斜挎包奶奶婆婆包', ' ', 199, 199, 200, 18, '2019-03-26 01:33:32');
INSERT INTO `t_product` VALUES (117, '女士包包 女包 新款2019中年女包尼龙帆布包防水单肩斜挎包妈妈包', ' ', 220, 220, 80, 18, '2019-03-26 01:34:14');
INSERT INTO `t_product` VALUES (118, 'MUZI定制包包女2018冬季新款韩版简约潮流百搭时尚锁扣复古小方包', ' ', 498, 498, 600, 18, '2019-03-26 01:35:31');
INSERT INTO `t_product` VALUES (119, '真皮羊皮女包包时尚女士中年妈妈包单肩斜挎手提包2019新款小方包', ' ', 299, 299, 500, 18, '2019-03-26 01:36:01');
INSERT INTO `t_product` VALUES (120, '包包女2019新款时尚百搭大容量手提包简约单肩斜挎包女包大包2018', ' ', 588, 588, 800, 18, '2019-03-26 01:36:35');
INSERT INTO `t_product` VALUES (121, '小香风针织开衫2019春季新款针织衫外套开衫黑白口袋毛衣外搭外披', ' 猪年吉祥 Doider朵尼丹尔开工大吉 当天发货', 136.9, 136.9, 504, 1, '2019-03-26 01:37:26');
INSERT INTO `t_product` VALUES (122, '防晒衣女2019夏季新款中长款韩版百搭防晒服户外骑车薄款沙滩外套', ' ', 380, 380, 500, 1, '2019-03-26 01:37:59');
INSERT INTO `t_product` VALUES (123, '2019新款女装春装碎花雪纺连衣裙长袖夏季中长款气质长裙春秋裙子', '有内衬 大裙摆 垂感好', 560, 560, 3312, 1, '2019-03-26 01:39:13');
INSERT INTO `t_product` VALUES (129, '美国泰拉蒙空气净化器家用除甲醛雾霾pm2.5除菌卧室负离子静音P92', '家装节钜惠 购机省1000+抢699元滤网卡', 9999, 9999, 9996, 16, '2019-03-26 01:58:53');
INSERT INTO `t_product` VALUES (130, '佳泰无线充电吸尘器家用小型手持式强力大功率超静音车载吸尘机', '', 2999, 2999, 292, 16, '2019-03-26 01:59:27');
INSERT INTO `t_product` VALUES (131, 'Robam/老板 ZTD100B-727T智能消毒柜家用嵌入式臭氧紫外线消毒柜', '阿里智能 五重净化 紫外线杀菌', 3990, 3990, 391, 16, '2019-03-26 01:59:56');
INSERT INTO `t_product` VALUES (132, '富士通将军空气净化器家用36高配水洗无耗材静音卧室除甲醛二手烟', '终身无耗材 每年省钱给宝宝买尿不湿', 5899, 5899, 52, 16, '2019-03-26 02:00:41');
INSERT INTO `t_product` VALUES (133, '微和凹面电磁炉 家用电节能大功率电磁炉3500W电池炉套装炒菜凹型', '', 547, 547, 119, 16, '2019-03-26 02:01:21');
INSERT INTO `t_product` VALUES (134, '【3+64G版立省50元】vivo U1水滴全面屏大电池拍照智能手机面部指纹双识别官方正品vivou1 y73 z1 Z3 iqoo', '晒单评价赢爱奇艺月卡/整点购机赢好礼', 799, 799, 653, 25, '2019-03-26 07:05:20');
INSERT INTO `t_product` VALUES (136, '【限时直降200】OPPO K1 oppok1手机新款全面屏超薄正品 oppo新品 k1 r17 r15x a7x r11 a3 k3 a1 k5 0oppok1', '限时直降200 分期免息送耳机碎屏保全额报销', 1799, 1799, 601, 25, '2019-03-26 07:08:28');
INSERT INTO `t_product` VALUES (138, '正品新款Nokia/诺基亚 新106老人机超长待机移动直板按键功能机大字大声老年机学生儿童备用迷你经典小手机', '正品诺基亚全国联保一年内手机质量问题包换', 309, 309, 603, 25, '2019-03-26 07:11:57');
INSERT INTO `t_product` VALUES (139, '正品X23S超薄全面水滴屏指纹智能游戏 学生价全网通 5g网络新手机', '', 1616, 1616, 230, 25, '2019-03-26 07:12:23');
INSERT INTO `t_product` VALUES (144, '2019英伦真皮复古雕花皮鞋男士马丁靴男鞋子潮鞋高帮靴短靴男靴子', '雕花男靴 头层牛皮 加绒保暖', 338, 338, 502, 22, '2019-03-26 07:16:59');
INSERT INTO `t_product` VALUES (145, '2019新款春秋冬季猫跟深口高跟鞋女鞋子踝靴及裸靴子小跟短靴单鞋', '全牛皮磨砂踝靴 百搭韩版细跟尖头窝窝鞋', 358, 358, 402, 22, '2019-03-26 07:17:48');
INSERT INTO `t_product` VALUES (146, 'Timberland添柏岚男鞋新款马丁靴经典6英寸鞋靴|A1QR2', '皮革材质 舒适鞋领 牢固耐磨 经典款型', 1490, 1490, 302, 22, '2019-03-26 07:18:15');
INSERT INTO `t_product` VALUES (147, '【经典款】Timberland添柏岚男鞋轻便透气经典鞋靴|A17BB', '泡棉鞋垫 经典翻靴 两穿设计 环保透气', 1350, 1350, 502, 22, '2019-03-26 07:19:00');
INSERT INTO `t_product` VALUES (148, '羊皮真皮里 丝绒面 方头粗跟高跟及踝靴裸靴短靴大码女靴冬加绒鞋', '', 258, 258, 24, 22, '2019-03-26 07:19:31');
INSERT INTO `t_product` VALUES (149, 'Danielwellington 丹尼尔惠灵顿DW手表女28mm简约时尚女表', '2年保修 顺丰包邮 进口腕表 品牌直营', 1290, 1290, 24, 5, '2019-03-26 07:20:15');
INSERT INTO `t_product` VALUES (150, '天王表男表正品防水休闲商务石英表日历情侣表间钨钢钢带男表3673', '品牌直营 分期免息', 699, 699, 353, 5, '2019-03-26 07:20:45');
INSERT INTO `t_product` VALUES (151, 'DanielWellington 丹尼尔惠灵顿dw手表男40mm黑表盘石英男表', '2年保修 顺丰包邮 进口腕表 品牌直营', 1490, 1490, 2030, 5, '2019-03-26 07:21:18');
INSERT INTO `t_product` VALUES (152, '表魅手表男石英表女士学生防水美国鹰洋银币定制时尚创意情侣腕表', '', 1769, 1769, 3021, 5, '2019-03-26 07:21:45');
INSERT INTO `t_product` VALUES (153, '表魅奥地利国家音乐银币防水小众雕刻石英明星同款复古百搭手腕表', '', 1769, 1769, 3021, 5, '2019-03-26 07:22:12');
INSERT INTO `t_product` VALUES (154, '成人3d立体手模玩具创意送礼生日礼物三维百变魔术针画脸印特大号', '', 58, 58, 301, 20, '2019-03-26 07:22:49');
INSERT INTO `t_product` VALUES (155, '诺森德 儿童滑板车闪光3-6岁小孩溜溜车2-12岁宝宝折叠滑滑宽轮车', '跑车造型 一秒折叠 高度调节 宽轮顺畅', 586, 586, 602, 20, '2019-03-26 07:23:21');
INSERT INTO `t_product` VALUES (157, '婴幼儿1-3岁配对拼图益智早教玩具1-2-3岁宝宝玩具72对拼图儿童', '72对配对拼图 送拼图早教玩法课程', 120, 120, 603, 20, '2019-03-26 07:56:28');
INSERT INTO `t_product` VALUES (158, '台湾weplay原装进口幼儿童早教平衡幼儿园感统教具豌豆荚豆荳夹', '', 298, 298, 306, 20, '2019-03-26 07:56:53');
INSERT INTO `t_product` VALUES (159, '幼儿童木质拼图开发智力趣味玩具益智2-3-4-5岁宝宝启蒙积木玩具', '', 39.9, 39.9, 160, 20, '2019-03-26 07:57:24');
INSERT INTO `t_product` VALUES (160, 'PANDORA潘多拉皇冠字母O 925银串饰797401创意时尚DIY串珠饰品女', '华丽优雅 永恒经典', 298, 298, 302, 17, '2019-03-26 07:57:59');
INSERT INTO `t_product` VALUES (161, '【买二送一】纯银耳钉S925银饰韩版耳环耳饰耳扣饰品女流行防过敏', '', 29.9, 29.9, 30, 17, '2019-03-26 07:58:21');
INSERT INTO `t_product` VALUES (162, '【买二送一】S925纯银耳钉韩版耳饰耳扣饰品女生流行 防过敏耳环', '', 38.8, 38.8, 201, 17, '2019-03-26 07:58:55');
INSERT INTO `t_product` VALUES (163, '全银S925银耳环女款素银气质百搭耳饰品纯银小耳圈简约花朵耳扣新', '', 38, 38, 3, 17, '2019-03-26 07:59:22');
INSERT INTO `t_product` VALUES (164, '韩国小胸针女简约百搭配饰品外套开衫插针式别针一字优雅大气胸花', '', 64, 64, 30, 17, '2019-03-26 07:59:48');
INSERT INTO `t_product` VALUES (165, '新生儿礼盒套装婴儿衣服春夏满月礼物初生刚出生宝宝必备用品大全', '精美柳编花篮 色纺布料无染色 无骨缝制', 398, 398, 398, 19, '2019-03-26 08:00:15');
INSERT INTO `t_product` VALUES (166, 'eoodoo新生儿礼盒套装春夏初生刚出生婴儿衣服宝宝满月母婴用品', '贵族白！纯棉婴儿衣服套装礼盒，送礼佳品', 259.72, 259.72, 301, 19, '2019-03-26 08:00:43');
INSERT INTO `t_product` VALUES (167, '婴儿衣服纯棉新生儿礼盒套装春秋夏初生刚出生男女宝宝用品满月礼', '无骨缝制无荧光剂礼盒包装无甲醛权威检测', 179, 179, 675, 19, '2019-03-26 08:01:18');
INSERT INTO `t_product` VALUES (168, '亲润孕妇护肤品套装天然豆乳纯补水保湿化妆品哺乳期怀孕期专用', '明星豆乳 补水保湿', 218, 218, 450, 19, '2019-03-26 08:01:50');
INSERT INTO `t_product` VALUES (169, '新生儿礼盒初生婴儿衣服套装用品送礼男春秋刚出生女满月宝宝礼物', '天然色纱无印染无荧光剂精美礼盒包装无骨缝', 198, 198, 455, 19, '2019-03-26 08:03:43');
INSERT INTO `t_product` VALUES (170, '?K-Touch/天语 R7移动电信版男女超薄老人机超长待机正品按键直板老年手机大屏大字大声天翼功能小学生备用', '?大字大声?待机王?强信号手电筒?来电报姓名', 728, 728, 302, 25, '2019-03-26 08:09:58');
INSERT INTO `t_product` VALUES (172, 'Mymikku 夏目友人帐公仔 猫咪老师手办 全套4款场景模型 礼物', '', 68, 68, 201, 21, '2019-03-26 08:13:17');
INSERT INTO `t_product` VALUES (174, '吉猫堂 招财猫摆件开业 店铺礼品创意金色陶瓷发财猫电动摇手猫', '★吉祥祝福★免费刻字★金猫开运★大吉大利', 298, 298, 200, 21, '2019-03-26 08:14:09');
INSERT INTO `t_product` VALUES (176, '活动小礼品婚礼婚庆抛洒布娃娃套餐大礼包小公仔玩偶毛绒玩具批发', '方便实惠，适用于各种活动礼品', 188, 188, 11, 21, '2019-03-26 08:15:07');
INSERT INTO `t_product` VALUES (177, '花花公子男士内裤男平角裤中腰四角短裤头冰丝透气青年内裤男大码', '收藏加购享优先发货', 79, 79, 24, 23, '2019-03-26 08:15:54');
INSERT INTO `t_product` VALUES (178, '北欧双层乳胶布艺沙发小户型可拆洗现代简约客厅沙发整装组合家具', '', 3780, 3780, 6651, 23, '2019-03-26 08:16:21');
INSERT INTO `t_product` VALUES (179, '楠欧双层乳胶布艺沙发小户型可拆洗现代简约客厅沙发整装组合家具', '美多地市', 2562, 2560, 562, 23, '2019-04-14 00:42:53');
INSERT INTO `t_product` VALUES (180, '表魅奥地利国家音乐银币防水小众雕刻石英明星同款复古百搭手腕表', 'fdasfssd', 565, 555, 525, 5, '2019-04-14 10:29:52');

-- ----------------------------
-- Table structure for t_productimage
-- ----------------------------
DROP TABLE IF EXISTS `t_productimage`;
CREATE TABLE `t_productimage`  (
  `imgId` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT 't_product-Id',
  `imgType` int(11) DEFAULT NULL COMMENT '类型:单个0图片或1详情图片',
  `imgUrl` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`imgId`) USING BTREE,
  INDEX `fk_productimage_product`(`pid`) USING BTREE,
  CONSTRAINT `fk_productimage_product` FOREIGN KEY (`pid`) REFERENCES `t_product` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 659 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_productimage
-- ----------------------------
INSERT INTO `t_productimage` VALUES (251, 90, 0, 'd8c14645-5caa-4285-80b7-771920097641_图片3.png');
INSERT INTO `t_productimage` VALUES (252, 90, 1, '09cd7889-2098-4eac-8eb5-8841859dc5b4_iNdeX_图片1.png');
INSERT INTO `t_productimage` VALUES (253, 90, 0, '84f98f8c-e2fa-45e2-b8b5-a198f60f7da9_图片2.png');
INSERT INTO `t_productimage` VALUES (254, 90, 0, '97d7207a-b7c2-4829-a7fc-5213b5689eaa_图片4.png');
INSERT INTO `t_productimage` VALUES (255, 90, 0, '79aab79f-f5d4-42ed-a3bf-ff7cba2b2bbf_图片5.png');
INSERT INTO `t_productimage` VALUES (260, 91, 1, 'b0d981dd-357c-4d16-b68e-5071c11a9a90_iNdeX_新建 DOCX 文档234.png');
INSERT INTO `t_productimage` VALUES (261, 91, 0, 'e3b7fb77-277b-4215-b83d-01ae8989a02b_新建 DOCX 文档238.png');
INSERT INTO `t_productimage` VALUES (262, 91, 0, '9e423be4-8292-47a2-b01e-d55ad47b6e41_新建 DOCX 文档235.png');
INSERT INTO `t_productimage` VALUES (263, 91, 0, '3555b938-5636-4591-a707-8c278b258612_新建 DOCX 文档237.png');
INSERT INTO `t_productimage` VALUES (264, 91, 0, 'ad7b76e2-05e0-417f-83cf-4118eb8cffa1_新建 DOCX 文档239.png');
INSERT INTO `t_productimage` VALUES (265, 94, 0, '85feb4ce-c567-4b95-b222-14725b217f45_新建 DOCX 文档1556.png');
INSERT INTO `t_productimage` VALUES (266, 94, 1, '22a54c1e-2611-46fa-9a9f-7fa54f0c15fb_iNdeX_新建 DOCX 文档1555.png');
INSERT INTO `t_productimage` VALUES (267, 94, 0, 'cd226902-3c25-4937-8ff0-fbc1a5712727_新建 DOCX 文档1558.png');
INSERT INTO `t_productimage` VALUES (268, 94, 0, 'a8c125d5-f0be-4684-a1b3-ec51cfdb0b6e_新建 DOCX 文档1559.png');
INSERT INTO `t_productimage` VALUES (269, 94, 0, '726adb5f-68f6-42c5-ad22-2bd1f4fefc07_新建 DOCX 文档1560.png');
INSERT INTO `t_productimage` VALUES (270, 95, 1, '5b405e3f-4b44-4545-a9a4-2b9d80250fc2_iNdeX_新建 DOCX 文档1646.png');
INSERT INTO `t_productimage` VALUES (271, 95, 0, 'bdb8e103-1e78-4900-8de8-b59c8632072b_新建 DOCX 文档1647.png');
INSERT INTO `t_productimage` VALUES (272, 95, 0, 'cee2990d-8b3a-4dc3-a3cb-92150267ed29_新建 DOCX 文档1651.png');
INSERT INTO `t_productimage` VALUES (273, 95, 0, 'afc0d1f3-5783-48fb-997c-c45e17728d66_新建 DOCX 文档1650.png');
INSERT INTO `t_productimage` VALUES (274, 95, 0, 'e39d7607-02dc-4af7-aa35-ab0952a305da_新建 DOCX 文档1654.png');
INSERT INTO `t_productimage` VALUES (275, 96, 1, '83c238c2-deb8-4428-931b-87ea94c568bc_iNdeX_新建 DOCX 文档1758.png');
INSERT INTO `t_productimage` VALUES (276, 96, 0, 'bac8c1ee-1213-452c-9ed2-f2ad49186651_新建 DOCX 文档1759.png');
INSERT INTO `t_productimage` VALUES (277, 96, 0, '50275cfe-23ac-448e-a7f2-ec8071654f4c_新建 DOCX 文档1761.png');
INSERT INTO `t_productimage` VALUES (278, 96, 0, '049e6644-fab3-4c1e-b7a2-a6a70e0e5d51_新建 DOCX 文档1762.png');
INSERT INTO `t_productimage` VALUES (279, 96, 0, '500a1270-e3fe-4017-af79-dfaa2e8ffbd8_新建 DOCX 文档1760.png');
INSERT INTO `t_productimage` VALUES (280, 97, 1, '0f3528aa-a5bd-442a-89a9-85d48f78639e_iNdeX_新建 DOCX 文档1914.png');
INSERT INTO `t_productimage` VALUES (281, 97, 0, '437241db-1852-40ae-b90b-470dc32752b9_新建 DOCX 文档1917.png');
INSERT INTO `t_productimage` VALUES (282, 97, 0, '7c35ebb8-fe54-4ed1-8576-27411f581b96_新建 DOCX 文档1918.png');
INSERT INTO `t_productimage` VALUES (283, 97, 0, 'd901d09b-2ff1-4f36-8edc-01f5ce3e1680_新建 DOCX 文档1915.png');
INSERT INTO `t_productimage` VALUES (284, 97, 0, 'fcf8f218-1a17-4b66-97e8-14ce2f7177bd_新建 DOCX 文档1916.png');
INSERT INTO `t_productimage` VALUES (285, 99, 0, 'b79d8674-b12b-409d-83f8-8a286d97746a_新建 DOCX 文档1988.png');
INSERT INTO `t_productimage` VALUES (286, 99, 0, '7e0bc01d-5907-41f8-9766-a4b23abe8991_新建 DOCX 文档1989.png');
INSERT INTO `t_productimage` VALUES (287, 99, 0, '81cccf5a-9d02-4f03-a7cb-58e077938f25_新建 DOCX 文档1990.png');
INSERT INTO `t_productimage` VALUES (288, 99, 1, 'e41d177d-8183-42cc-bcbf-19abc9cf7ddc_iNdeX_新建 DOCX 文档1987.png');
INSERT INTO `t_productimage` VALUES (289, 99, 0, '86036c8a-6ce9-49a9-8f56-d17a9cba703f_新建 DOCX 文档1991.png');
INSERT INTO `t_productimage` VALUES (295, 105, 0, '6f085175-9383-4aed-bb1c-d59cb0b8d652_新建 DOCX 文档2056.png');
INSERT INTO `t_productimage` VALUES (296, 105, 0, 'c3c06381-ca08-48d6-961e-891cc88dfc85_新建 DOCX 文档2059.png');
INSERT INTO `t_productimage` VALUES (297, 105, 1, '5ab11d4d-c3fd-4c13-925c-578557d4d27a_iNdeX_新建 DOCX 文档2055.png');
INSERT INTO `t_productimage` VALUES (298, 105, 0, '58cd3118-3649-465a-8249-1e9bd779f331_新建 DOCX 文档2057.png');
INSERT INTO `t_productimage` VALUES (299, 105, 0, '34762051-ec65-4345-8677-e1e885a7cfd7_新建 DOCX 文档2058.png');
INSERT INTO `t_productimage` VALUES (300, 106, 1, 'd4132079-b156-421b-bfb0-0cddd08b9d47_iNdeX_新建 DOCX 文档2466.png');
INSERT INTO `t_productimage` VALUES (301, 106, 0, '4ff71644-5492-4600-bf4e-a50715412302_新建 DOCX 文档2468.png');
INSERT INTO `t_productimage` VALUES (302, 106, 0, '40a087db-c095-4397-9539-58b4af9e88ed_新建 DOCX 文档2470.png');
INSERT INTO `t_productimage` VALUES (303, 106, 0, 'af956b88-fcc8-4802-abeb-975ded129d84_新建 DOCX 文档2469.png');
INSERT INTO `t_productimage` VALUES (304, 106, 0, 'ea832daf-398c-4a37-9501-828892f16c1e_新建 DOCX 文档2467.png');
INSERT INTO `t_productimage` VALUES (305, 107, 1, '0d26b355-6de1-4c73-950a-c3836aec2c16_iNdeX_新建 DOCX 文档2739.png');
INSERT INTO `t_productimage` VALUES (306, 107, 0, '111a3afc-723e-4547-9938-ef631ee158de_新建 DOCX 文档2742.png');
INSERT INTO `t_productimage` VALUES (307, 107, 0, 'f65ae6d5-bdb6-473c-bf9e-6a20c6894b38_新建 DOCX 文档2741.png');
INSERT INTO `t_productimage` VALUES (308, 107, 0, '86aa90df-60d6-49ef-a7fe-99fe58688171_新建 DOCX 文档2743.png');
INSERT INTO `t_productimage` VALUES (309, 107, 0, 'a6c46cb5-8782-4979-9aaf-28d103c63225_新建 DOCX 文档2740.png');
INSERT INTO `t_productimage` VALUES (310, 108, 0, '40c994fb-4dc2-438b-907b-fc44099d9a60_新建 DOCX 文档3081.png');
INSERT INTO `t_productimage` VALUES (311, 108, 0, '1c4b937f-b980-4798-8ed6-fb4d6f289777_新建 DOCX 文档3082.png');
INSERT INTO `t_productimage` VALUES (312, 108, 1, '281439ed-bc10-43ca-bfc4-0dce51621660_iNdeX_新建 DOCX 文档3080.png');
INSERT INTO `t_productimage` VALUES (313, 108, 0, '0d24cc44-df19-465f-9f8d-297697c9bdb1_新建 DOCX 文档3084.png');
INSERT INTO `t_productimage` VALUES (314, 108, 0, 'b25f29c9-7cd8-4275-bfee-0f81a351d8e8_新建 DOCX 文档3083.png');
INSERT INTO `t_productimage` VALUES (315, 110, 0, '60461c87-53fe-4610-95a6-f6c6562e87da_新建 DOCX 文档3377.png');
INSERT INTO `t_productimage` VALUES (316, 110, 0, 'a6646faa-0b4a-42a6-913d-5ffc0e47d602_新建 DOCX 文档3379.png');
INSERT INTO `t_productimage` VALUES (317, 110, 0, 'b02d575b-383b-4c94-9724-5ab6c1f97251_新建 DOCX 文档3380.png');
INSERT INTO `t_productimage` VALUES (318, 110, 0, '16468a25-1de9-44bc-a881-3c880e5ea3b0_新建 DOCX 文档3378.png');
INSERT INTO `t_productimage` VALUES (319, 110, 1, '3be5aa6a-f40b-4b6e-a241-67b8c4c590df_iNdeX_新建 DOCX 文档3376.png');
INSERT INTO `t_productimage` VALUES (320, 111, 0, '3028b3de-32fa-47f3-9819-2d4d0f41e4f3_新建 DOCX 文档3748.png');
INSERT INTO `t_productimage` VALUES (321, 111, 0, '83064073-103a-4c6b-b60f-1420ff9e49b2_新建 DOCX 文档3749.png');
INSERT INTO `t_productimage` VALUES (322, 111, 0, '5aea86cd-dc67-4b30-983b-9b509e94a609_新建 DOCX 文档3750.png');
INSERT INTO `t_productimage` VALUES (323, 111, 1, '88a793f4-51d8-4721-a43e-41bd8e1f26fb_iNdeX_新建 DOCX 文档3747.png');
INSERT INTO `t_productimage` VALUES (324, 111, 0, '80310166-da2b-4533-a054-49d59ed0f38f_新建 DOCX 文档3751.png');
INSERT INTO `t_productimage` VALUES (325, 112, 1, '4e9309ac-fcfe-43ca-a12d-f849fadeb5c4_iNdeX_新建 DOCX 文档3940.png');
INSERT INTO `t_productimage` VALUES (326, 112, 0, '98738388-eeb7-4ad2-9519-45510f886c29_新建 DOCX 文档3941.png');
INSERT INTO `t_productimage` VALUES (327, 112, 0, 'b6f39f34-ae33-4640-8340-9ae370a2a4cf_新建 DOCX 文档3942.png');
INSERT INTO `t_productimage` VALUES (328, 112, 0, '9205f916-bd7e-49bd-8113-85a3772fc881_新建 DOCX 文档3943.png');
INSERT INTO `t_productimage` VALUES (329, 112, 0, '7083bce7-cf64-460e-8b88-8850092b41a9_新建 DOCX 文档3944.png');
INSERT INTO `t_productimage` VALUES (335, 113, 1, '270f71cf-ac04-4c62-87e0-c0046fb41f9e_iNdeX_新建 DOCX 文档4102.png');
INSERT INTO `t_productimage` VALUES (336, 113, 0, '84a4b70e-bb3e-4074-a434-e17f6537bf85_新建 DOCX 文档4103.png');
INSERT INTO `t_productimage` VALUES (337, 113, 0, '0456eae1-6e44-41d8-ad6e-5d93591b4cb4_新建 DOCX 文档4105.png');
INSERT INTO `t_productimage` VALUES (338, 113, 0, '2113d41a-24e2-4c1e-b94a-61ed363b2df4_新建 DOCX 文档4104.png');
INSERT INTO `t_productimage` VALUES (339, 113, 0, 'b082662f-ae85-4c07-9a54-671ab3cebbc0_新建 DOCX 文档4106.png');
INSERT INTO `t_productimage` VALUES (340, 114, 0, 'ca67decc-1885-4f93-8acf-278062a626be_新建 DOCX 文档4409.png');
INSERT INTO `t_productimage` VALUES (341, 114, 0, '33c54579-d29b-41ff-927a-889c800f7d93_新建 DOCX 文档4411.png');
INSERT INTO `t_productimage` VALUES (342, 114, 0, 'e5e49fb1-abd7-4552-9351-1211b5b6471d_新建 DOCX 文档4410.png');
INSERT INTO `t_productimage` VALUES (343, 114, 0, 'cc889e21-9ca0-4936-967c-eaf173f66782_新建 DOCX 文档4412.png');
INSERT INTO `t_productimage` VALUES (344, 114, 1, 'ef835aef-b299-4e9d-9849-1206297e2bf8_iNdeX_新建 DOCX 文档4408.png');
INSERT INTO `t_productimage` VALUES (345, 115, 0, 'c454dbe9-eb0e-4c2c-aa45-d2f146ba8930_新建 DOCX 文档4701.png');
INSERT INTO `t_productimage` VALUES (346, 115, 0, '16244229-41f9-4ed5-9371-f4c0a820fb8c_新建 DOCX 文档4702.png');
INSERT INTO `t_productimage` VALUES (347, 115, 0, 'fdca83e5-f63b-4d86-a260-d494c2ee493c_新建 DOCX 文档4703.png');
INSERT INTO `t_productimage` VALUES (348, 115, 1, '3882818b-ec30-41a0-963c-7bbd994708e2_iNdeX_新建 DOCX 文档4700.png');
INSERT INTO `t_productimage` VALUES (349, 115, 0, 'd6c0361f-d47c-45dd-a626-90b0712c4722_新建 DOCX 文档4699.png');
INSERT INTO `t_productimage` VALUES (350, 116, 1, 'fb1d6f11-b255-4d32-9700-2edfd2a61455_iNdeX_新建 DOCX 文档4847.png');
INSERT INTO `t_productimage` VALUES (351, 116, 0, '054f9abc-0f8e-47e8-bebf-3a10d57ae8af_新建 DOCX 文档4848.png');
INSERT INTO `t_productimage` VALUES (352, 116, 0, 'a39a4f39-f4d4-46ce-b390-a4bc2fe164f0_新建 DOCX 文档4850.png');
INSERT INTO `t_productimage` VALUES (353, 116, 0, 'ff6cdaa8-47b1-4e31-9d63-9ad7680090bf_新建 DOCX 文档4851.png');
INSERT INTO `t_productimage` VALUES (354, 116, 0, 'f46a0ba3-b6bf-41dc-b3be-a19ed9194bbe_新建 DOCX 文档4849.png');
INSERT INTO `t_productimage` VALUES (355, 117, 1, 'd503b91b-6a51-41d4-adad-4035f43e3393_iNdeX_新建 DOCX 文档5251.png');
INSERT INTO `t_productimage` VALUES (356, 117, 0, '565d74dd-2a0b-4a19-a922-3a361b8bdbd2_新建 DOCX 文档5253.png');
INSERT INTO `t_productimage` VALUES (357, 117, 0, '790eaa61-d0e5-4aa1-a769-fe2f6bbf6a63_新建 DOCX 文档5254.png');
INSERT INTO `t_productimage` VALUES (358, 117, 0, '2f9b38eb-fce4-4b97-a184-28d3e1f01973_新建 DOCX 文档5255.png');
INSERT INTO `t_productimage` VALUES (359, 117, 0, '1828151c-8a4e-489a-a9bc-b8dbbadb1d30_新建 DOCX 文档5252.png');
INSERT INTO `t_productimage` VALUES (360, 118, 1, 'd901fb36-0e24-4958-a250-d003abdfbdbb_iNdeX_新建 DOCX 文档5579.png');
INSERT INTO `t_productimage` VALUES (361, 118, 0, '336f44b0-7a2d-4d24-8431-c23c030e3949_新建 DOCX 文档5581.png');
INSERT INTO `t_productimage` VALUES (362, 118, 0, '64556c39-ae70-4ef5-baed-7d082761ae08_新建 DOCX 文档5583.png');
INSERT INTO `t_productimage` VALUES (363, 118, 0, '1451e9c8-4b71-48ab-b218-a3647e45713b_新建 DOCX 文档5582.png');
INSERT INTO `t_productimage` VALUES (364, 118, 0, '13c254f6-d016-4053-a170-14083a670249_新建 DOCX 文档5580.png');
INSERT INTO `t_productimage` VALUES (365, 119, 0, '5402e5c3-fabc-4dfd-9a96-3cb906ab4210_新建 DOCX 文档5870.png');
INSERT INTO `t_productimage` VALUES (366, 119, 0, '9584cf63-3880-4dbd-9a87-4de809561dd0_新建 DOCX 文档5873.png');
INSERT INTO `t_productimage` VALUES (367, 119, 1, '9093b401-d49b-4044-a308-2664ec919a8b_iNdeX_新建 DOCX 文档5869.png');
INSERT INTO `t_productimage` VALUES (368, 119, 0, '47bc2f08-5d77-4110-8cac-535a4e31b1b1_新建 DOCX 文档5872.png');
INSERT INTO `t_productimage` VALUES (369, 119, 0, '6eb374a5-6aa9-4423-94dd-e72231d414a0_新建 DOCX 文档5871.png');
INSERT INTO `t_productimage` VALUES (370, 120, 0, '54f319bc-67e0-4b20-9c26-af686654259b_新建 DOCX 文档6196.png');
INSERT INTO `t_productimage` VALUES (371, 120, 1, '8944c544-92aa-42d0-8f95-8f30eb0ef1c3_iNdeX_新建 DOCX 文档6195.png');
INSERT INTO `t_productimage` VALUES (372, 120, 0, '7c1a560d-9995-481b-9a59-b1879d65a1b0_新建 DOCX 文档6197.png');
INSERT INTO `t_productimage` VALUES (373, 120, 0, 'bbfc213f-0d26-41da-95de-b3affb9e59e0_新建 DOCX 文档6198.png');
INSERT INTO `t_productimage` VALUES (374, 120, 0, '92c91097-5f01-4611-81b9-5d908ce499c7_新建 DOCX 文档6199.png');
INSERT INTO `t_productimage` VALUES (375, 121, 1, '879aaf91-000d-4fa8-9c98-cd28515438d5_iNdeX_新建 DOCX 文档504.png');
INSERT INTO `t_productimage` VALUES (376, 121, 0, '59d1ba6d-2add-4000-8598-3347f36ba3ac_新建 DOCX 文档507.png');
INSERT INTO `t_productimage` VALUES (377, 121, 0, 'e67770e4-8ece-461c-a3f7-8e6a11033059_新建 DOCX 文档506.png');
INSERT INTO `t_productimage` VALUES (378, 121, 0, '97308f5d-1c76-4331-aaa2-38976e24d7cd_新建 DOCX 文档508.png');
INSERT INTO `t_productimage` VALUES (379, 121, 0, 'dac174f7-322d-4782-ab3f-675994b09cb2_新建 DOCX 文档505.png');
INSERT INTO `t_productimage` VALUES (380, 122, 1, 'ebd7c379-1412-4a09-b50b-2ae997c547d0_iNdeX_新建 DOCX 文档726.png');
INSERT INTO `t_productimage` VALUES (381, 122, 0, '98390f3c-a79c-4497-9611-4ab064c3a18c_新建 DOCX 文档727.png');
INSERT INTO `t_productimage` VALUES (382, 122, 0, '6c5dd498-1738-4c6e-9ef3-f0b15d41225d_新建 DOCX 文档729.png');
INSERT INTO `t_productimage` VALUES (383, 122, 0, '4eca944a-bb53-470d-97f4-e6960e6aab5c_新建 DOCX 文档730.png');
INSERT INTO `t_productimage` VALUES (384, 122, 0, 'bfe5a864-c1f2-4a4e-926b-59b0c1a4718c_新建 DOCX 文档728.png');
INSERT INTO `t_productimage` VALUES (385, 123, 0, '72018737-e463-46bb-a569-805729964615_新建 DOCX 文档1191.png');
INSERT INTO `t_productimage` VALUES (386, 123, 0, 'd7c2cae4-4942-4360-9493-669b75e6ffaa_新建 DOCX 文档1189.png');
INSERT INTO `t_productimage` VALUES (387, 123, 1, 'b840ec65-0082-4b93-96d4-4d3e32579af4_iNdeX_新建 DOCX 文档1188.png');
INSERT INTO `t_productimage` VALUES (388, 123, 0, 'f1253166-2370-4d71-a798-90b0c3d83c29_新建 DOCX 文档1190.png');
INSERT INTO `t_productimage` VALUES (389, 123, 0, '3e563fae-1fb7-4a3a-8995-f5a25ca34538_新建 DOCX 文档1192.png');
INSERT INTO `t_productimage` VALUES (415, 129, 0, 'aeb039f3-6d31-4b6f-b9d1-2c25a6e01868_新建 DOCX 文档6305.png');
INSERT INTO `t_productimage` VALUES (416, 129, 0, '2ad95fbc-1b1d-459b-860b-567989ee4a1e_新建 DOCX 文档6307.png');
INSERT INTO `t_productimage` VALUES (417, 129, 1, '75775547-e26f-477f-bfbf-81cb76ae888c_iNdeX_新建 DOCX 文档6303.png');
INSERT INTO `t_productimage` VALUES (418, 129, 0, '314482ec-e141-4b7b-90e7-2e08898bd45a_新建 DOCX 文档6304.png');
INSERT INTO `t_productimage` VALUES (419, 129, 0, 'f52e604f-905a-4148-9369-2ac3801c690a_新建 DOCX 文档6306.png');
INSERT INTO `t_productimage` VALUES (420, 130, 1, 'c515767f-8d74-4de1-89fc-1f0c9f01f21c_iNdeX_新建 DOCX 文档6959.png');
INSERT INTO `t_productimage` VALUES (421, 130, 0, 'f9728689-7e5a-43be-b9d7-e0f87b8cb46b_新建 DOCX 文档6960.png');
INSERT INTO `t_productimage` VALUES (422, 130, 0, 'a4d8492a-305e-41af-9505-a87b1ccfd59c_新建 DOCX 文档6961.png');
INSERT INTO `t_productimage` VALUES (423, 130, 0, '87c2fd63-32f5-49ad-b5ad-98eb3568be68_新建 DOCX 文档6963.png');
INSERT INTO `t_productimage` VALUES (424, 130, 0, '576d5113-85e9-4f50-9b91-0e4b27d3f86b_新建 DOCX 文档6962.png');
INSERT INTO `t_productimage` VALUES (425, 131, 1, '94bf7183-9c33-484f-873f-63106eafc63d_iNdeX_新建 DOCX 文档7288.png');
INSERT INTO `t_productimage` VALUES (426, 131, 0, '28a597cc-8f33-4b8e-a216-34691a26012e_新建 DOCX 文档7289.png');
INSERT INTO `t_productimage` VALUES (427, 131, 0, '33d1286a-4969-4d23-816c-f2c9f36f7389_新建 DOCX 文档7290.png');
INSERT INTO `t_productimage` VALUES (428, 131, 0, '92bcbf92-ad0b-483e-9a3e-d252de63b8f0_新建 DOCX 文档7291.png');
INSERT INTO `t_productimage` VALUES (429, 131, 0, '8bdd9f44-2381-4c21-a853-fc3083a50cfc_新建 DOCX 文档7292.png');
INSERT INTO `t_productimage` VALUES (430, 132, 1, '4f7eac1c-79d0-4967-a972-ddc7f83109d4_iNdeX_新建 DOCX 文档8051.png');
INSERT INTO `t_productimage` VALUES (431, 132, 0, '7f45d91d-a573-43a4-94ce-bfe98b4720d4_新建 DOCX 文档8052.png');
INSERT INTO `t_productimage` VALUES (432, 132, 0, 'cd625815-3940-43f0-88af-988970ddbe3c_新建 DOCX 文档8054.png');
INSERT INTO `t_productimage` VALUES (433, 132, 0, 'b7565f35-946c-48c1-90fe-0ef854fc9cd5_新建 DOCX 文档8055.png');
INSERT INTO `t_productimage` VALUES (434, 132, 0, '39ebf4dd-3fc6-4e10-b578-3a79ec7ba860_新建 DOCX 文档8053.png');
INSERT INTO `t_productimage` VALUES (435, 133, 0, '1d51d915-7066-4cf8-90f3-a37f898b5782_新建 DOCX 文档8689.png');
INSERT INTO `t_productimage` VALUES (436, 133, 0, '1f17671f-31dc-4859-a0a6-ae9515df8b63_新建 DOCX 文档8690.png');
INSERT INTO `t_productimage` VALUES (437, 133, 1, 'b469b3f7-0db0-4338-8957-11f60dc89ed1_iNdeX_新建 DOCX 文档8686.png');
INSERT INTO `t_productimage` VALUES (438, 133, 0, 'ca46ecaa-66e7-4479-a884-65ca29f81e92_新建 DOCX 文档8687.png');
INSERT INTO `t_productimage` VALUES (439, 133, 0, '1b6fa770-8273-4290-adc6-fe22bc9448bc_新建 DOCX 文档8688.png');
INSERT INTO `t_productimage` VALUES (440, 134, 0, 'c125b29e-7ea9-4d83-bc8f-d3d107d68fa8_新建 DOCX 文档9126.png');
INSERT INTO `t_productimage` VALUES (441, 134, 0, '36c74da5-9b5b-4eaa-98ff-0453affb7021_新建 DOCX 文档9124.png');
INSERT INTO `t_productimage` VALUES (442, 134, 1, 'dd943544-c8d7-4828-9fd7-ccac1fa8c09e_iNdeX_新建 DOCX 文档9123.png');
INSERT INTO `t_productimage` VALUES (443, 134, 0, '87b72131-f350-459c-b6ea-06029d92295f_新建 DOCX 文档9125.png');
INSERT INTO `t_productimage` VALUES (444, 134, 0, '63cd78c0-dacb-40e6-9882-b71a459f2a36_新建 DOCX 文档9127.png');
INSERT INTO `t_productimage` VALUES (448, 136, 1, '9a088c0e-7a9d-4e66-80fa-c33ebea0fb27_iNdeX_新建 DOCX 文档10630.png');
INSERT INTO `t_productimage` VALUES (449, 136, 0, '21d983f9-e9a0-48bb-8173-21da8edc4ba9_新建 DOCX 文档10634.png');
INSERT INTO `t_productimage` VALUES (455, 138, 1, 'df91788a-ad29-4f91-b710-50a2ec1c0537_iNdeX_新建 DOCX 文档11475.png');
INSERT INTO `t_productimage` VALUES (456, 138, 0, 'b417d206-f366-4dfb-b0c7-9a49b6a61dae_新建 DOCX 文档11477.png');
INSERT INTO `t_productimage` VALUES (457, 138, 0, '1ce70990-ffb4-476f-8fcf-4b25d864a45e_新建 DOCX 文档11479.png');
INSERT INTO `t_productimage` VALUES (458, 138, 0, 'ce235467-ed9e-484a-bac4-2f78fc1bf264_新建 DOCX 文档11476.png');
INSERT INTO `t_productimage` VALUES (459, 138, 0, 'cd2a0fc1-f76a-4cf9-bfff-b8e75de8f740_新建 DOCX 文档11478.png');
INSERT INTO `t_productimage` VALUES (460, 139, 0, 'b1b5be9a-7ef2-4a9a-87bf-869256065a53_新建 DOCX 文档12292.png');
INSERT INTO `t_productimage` VALUES (461, 139, 0, 'b545092e-8051-48e8-a7a3-b5b1fed8a5d0_新建 DOCX 文档12293.png');
INSERT INTO `t_productimage` VALUES (462, 139, 0, '17174b0e-6bbe-42ce-b58c-b2c29248c3de_新建 DOCX 文档12294.png');
INSERT INTO `t_productimage` VALUES (463, 139, 1, '0e56d5fe-1499-4f7c-ba08-6cee2187a330_iNdeX_新建 DOCX 文档12291.png');
INSERT INTO `t_productimage` VALUES (464, 139, 0, 'd98964a9-37dc-4d31-89c8-5e0a15d9ea53_新建 DOCX 文档12295.png');
INSERT INTO `t_productimage` VALUES (485, 144, 1, 'c95eb975-37e3-42e3-8dbe-bff00e62db95_iNdeX_新建 DOCX 文档13667.png');
INSERT INTO `t_productimage` VALUES (486, 144, 0, 'fdf2ec8b-2e92-45a1-a696-22ef33dff9a1_新建 DOCX 文档13668.png');
INSERT INTO `t_productimage` VALUES (487, 144, 0, '6fd39e0d-bbc5-4236-9a78-7e7f00534540_新建 DOCX 文档13669.png');
INSERT INTO `t_productimage` VALUES (488, 144, 0, '6c6f5bbd-8a51-44da-a6d3-2bacbd1692ef_新建 DOCX 文档13670.png');
INSERT INTO `t_productimage` VALUES (489, 144, 0, '4d372ffb-5b84-4861-b8d8-6f06d8fa8042_新建 DOCX 文档13671.png');
INSERT INTO `t_productimage` VALUES (490, 145, 0, 'fdc9cffa-fd5f-473e-9a40-e75e14a430eb_新建 DOCX 文档12374.png');
INSERT INTO `t_productimage` VALUES (491, 145, 1, '87631d13-4e1d-466c-82b6-24f9c6ec9caa_iNdeX_新建 DOCX 文档12372.png');
INSERT INTO `t_productimage` VALUES (492, 145, 0, 'a62c69e1-5896-4072-84e1-c3caeaee1ee3_新建 DOCX 文档12373.png');
INSERT INTO `t_productimage` VALUES (493, 145, 0, 'aba55fa3-0e46-4d09-95be-f37d5157ad37_新建 DOCX 文档12375.png');
INSERT INTO `t_productimage` VALUES (494, 145, 0, '07c9532e-67f6-4537-a036-47263c508f51_新建 DOCX 文档12376.png');
INSERT INTO `t_productimage` VALUES (495, 146, 1, 'a5a62e45-bbf1-4d71-a36b-59d7e594e3d7_iNdeX_新建 DOCX 文档12735.png');
INSERT INTO `t_productimage` VALUES (496, 146, 0, 'e0d9d93e-1b65-40fe-a586-348f6611293f_新建 DOCX 文档12736.png');
INSERT INTO `t_productimage` VALUES (497, 146, 0, '5416db58-d519-43cd-b8a3-95bf0a56bf42_新建 DOCX 文档12738.png');
INSERT INTO `t_productimage` VALUES (498, 146, 0, '8c594477-fc3c-4438-b4e4-053a68415dce_新建 DOCX 文档12739.png');
INSERT INTO `t_productimage` VALUES (499, 146, 0, '47a65a4c-832e-4c30-960b-0175440a4fdf_新建 DOCX 文档12737.png');
INSERT INTO `t_productimage` VALUES (500, 147, 1, '469742de-bc84-4685-a19b-3f6e10543542_iNdeX_新建 DOCX 文档13027.png');
INSERT INTO `t_productimage` VALUES (501, 147, 0, '4e4b82c5-7875-4565-b7b1-da68a93e4feb_新建 DOCX 文档13028.png');
INSERT INTO `t_productimage` VALUES (502, 147, 0, '36226efe-d19d-4020-bd34-1875e251fc38_新建 DOCX 文档13029.png');
INSERT INTO `t_productimage` VALUES (503, 147, 0, 'f08b6c00-4786-4e60-b73c-bb1d9dd227b4_新建 DOCX 文档13030.png');
INSERT INTO `t_productimage` VALUES (504, 147, 0, '6cb48463-b025-42c7-a85a-72eac82a3af3_新建 DOCX 文档13031.png');
INSERT INTO `t_productimage` VALUES (505, 148, 0, '72f73b26-66be-47ed-87b2-8030ff18b400_新建 DOCX 文档13340.png');
INSERT INTO `t_productimage` VALUES (506, 148, 1, '6fc9137c-2267-49b6-bf1d-647f15648e0a_iNdeX_新建 DOCX 文档13338.png');
INSERT INTO `t_productimage` VALUES (507, 148, 0, '6d6d99ce-7ae3-4452-9e46-c27bc20b4960_新建 DOCX 文档13341.png');
INSERT INTO `t_productimage` VALUES (508, 148, 0, 'd2633fbd-123c-4687-8e15-88d375f634b9_新建 DOCX 文档13339.png');
INSERT INTO `t_productimage` VALUES (509, 148, 0, '097e9aba-d46d-4690-aed7-de33a80e0f7b_新建 DOCX 文档13343.png');
INSERT INTO `t_productimage` VALUES (510, 149, 0, '083a8a30-2f61-48a2-8b52-28672ab3e24f_新建 DOCX 文档14101.png');
INSERT INTO `t_productimage` VALUES (511, 149, 1, 'c2c3371b-f789-4add-a540-673e128240a5_iNdeX_新建 DOCX 文档14100.png');
INSERT INTO `t_productimage` VALUES (512, 149, 0, 'bd845cd2-65c8-41f0-a1d6-ef015e70bb06_新建 DOCX 文档14102.png');
INSERT INTO `t_productimage` VALUES (513, 149, 0, '145ccd3f-4bb7-4760-9380-dd2ef5b6ff85_新建 DOCX 文档14103.png');
INSERT INTO `t_productimage` VALUES (514, 149, 0, '913711b6-13d5-48c7-b826-3bc1ceafdc65_新建 DOCX 文档14104.png');
INSERT INTO `t_productimage` VALUES (515, 150, 0, '2c8ef248-6a52-483d-bf77-ea5562547bff_新建 DOCX 文档14483.png');
INSERT INTO `t_productimage` VALUES (516, 150, 0, '43fa9a1f-4fea-47d2-b1f3-1ed1e9c93d40_新建 DOCX 文档14484.png');
INSERT INTO `t_productimage` VALUES (517, 150, 0, '63541822-9a27-4118-8bf2-f6567d184846_新建 DOCX 文档14482.png');
INSERT INTO `t_productimage` VALUES (518, 150, 0, '825c059c-6b42-4b09-b335-1c846c5ce30b_新建 DOCX 文档14485.png');
INSERT INTO `t_productimage` VALUES (519, 150, 1, '4e9a7d9b-6877-4375-ae35-b231cfbf15f0_iNdeX_新建 DOCX 文档14481.png');
INSERT INTO `t_productimage` VALUES (520, 151, 1, '75361dbf-7cda-4d0d-a40a-ca4a4c98d2c7_iNdeX_新建 DOCX 文档14835.png');
INSERT INTO `t_productimage` VALUES (521, 151, 0, '4163f35c-951a-4005-bc52-afebc9e50233_新建 DOCX 文档14839.png');
INSERT INTO `t_productimage` VALUES (522, 151, 0, 'e97c6e9c-9fa1-4f62-b330-6445c500b61a_新建 DOCX 文档14838.png');
INSERT INTO `t_productimage` VALUES (523, 151, 0, 'e01650b6-da78-4853-91ad-a11672534c13_新建 DOCX 文档14837.png');
INSERT INTO `t_productimage` VALUES (524, 151, 0, '16210851-571c-4863-9ac9-ab48f0e61797_新建 DOCX 文档14836.png');
INSERT INTO `t_productimage` VALUES (525, 152, 0, 'f8624a4d-e9cc-43c2-b13b-c3e54867b76c_新建 DOCX 文档15199.png');
INSERT INTO `t_productimage` VALUES (526, 152, 1, 'cce1df7f-7520-493b-b6b9-258b8e01247a_iNdeX_新建 DOCX 文档15198.png');
INSERT INTO `t_productimage` VALUES (527, 152, 0, 'e1c3ad40-14ca-400c-ac26-f00070b218e7_新建 DOCX 文档15200.png');
INSERT INTO `t_productimage` VALUES (528, 152, 0, 'd3050ea2-d988-4d70-84f3-555e6f110ac9_新建 DOCX 文档15201.png');
INSERT INTO `t_productimage` VALUES (529, 152, 0, '6bce3137-20bc-4a1a-a178-8357f5d1cbb8_新建 DOCX 文档15202.png');
INSERT INTO `t_productimage` VALUES (530, 153, 0, 'f726cfaa-0515-4ff6-947d-4120b87e5604_新建 DOCX 文档15588.png');
INSERT INTO `t_productimage` VALUES (531, 153, 1, 'a9f58bfb-6c6d-48a7-b6e2-fda0280f6894_iNdeX_新建 DOCX 文档15586.png');
INSERT INTO `t_productimage` VALUES (532, 153, 0, 'ecfd9044-77cf-454e-a449-d2c82143abc4_新建 DOCX 文档15590.png');
INSERT INTO `t_productimage` VALUES (533, 153, 0, '8b0760b8-e868-4a9c-82df-34f181641687_新建 DOCX 文档15587.png');
INSERT INTO `t_productimage` VALUES (534, 153, 0, '5d2ed800-5f8f-4129-8958-9fb7f4dad666_新建 DOCX 文档15589.png');
INSERT INTO `t_productimage` VALUES (535, 154, 0, '8fda54a6-384b-4927-89fc-99ab9df74b74_新建 DOCX 文档16001.png');
INSERT INTO `t_productimage` VALUES (536, 154, 1, '51e82448-ca50-423e-a2eb-1268edc2723d_iNdeX_新建 DOCX 文档16000.png');
INSERT INTO `t_productimage` VALUES (537, 154, 0, '794745c5-63e0-46f3-866b-1ae0208a96cb_新建 DOCX 文档16004.png');
INSERT INTO `t_productimage` VALUES (538, 154, 0, 'd89e7bdd-7c6e-41d3-aee2-ff8d726a6197_新建 DOCX 文档16003.png');
INSERT INTO `t_productimage` VALUES (539, 154, 0, 'd7eb0921-5206-45e2-9d04-05134701047c_新建 DOCX 文档16002.png');
INSERT INTO `t_productimage` VALUES (540, 155, 0, '046ef8e5-8a88-49f5-b6ec-49de7db2a203_新建 DOCX 文档16219.png');
INSERT INTO `t_productimage` VALUES (541, 155, 1, '728c70b3-ae6b-4635-9e90-ce07457c65bb_iNdeX_新建 DOCX 文档16218.png');
INSERT INTO `t_productimage` VALUES (542, 155, 0, '01e82e13-e72e-4d6e-b1c4-b56e52742fa1_新建 DOCX 文档16222.png');
INSERT INTO `t_productimage` VALUES (543, 155, 0, 'bde9f169-8b08-4496-a44c-f68236122022_新建 DOCX 文档16221.png');
INSERT INTO `t_productimage` VALUES (544, 155, 0, 'cedb6585-fc41-4823-b288-dd67110c2c83_新建 DOCX 文档16220.png');
INSERT INTO `t_productimage` VALUES (545, 157, 1, '701666fb-76c4-4335-aa98-af39215ba4a5_iNdeX_新建 DOCX 文档16646.png');
INSERT INTO `t_productimage` VALUES (546, 157, 0, 'f54de616-f45d-4ef6-a1d9-c12b42461993_新建 DOCX 文档16647.png');
INSERT INTO `t_productimage` VALUES (547, 157, 0, '263582e2-c861-4457-809c-8bc9e02e1ca0_新建 DOCX 文档16648.png');
INSERT INTO `t_productimage` VALUES (548, 157, 0, '5674d4f7-cae7-4389-82e8-252ebb72315f_新建 DOCX 文档16649.png');
INSERT INTO `t_productimage` VALUES (549, 157, 0, '558c157b-0c76-487b-92d0-210f59ff03e8_新建 DOCX 文档16650.png');
INSERT INTO `t_productimage` VALUES (550, 158, 0, '025278af-3de0-4133-ad5a-f2f3948da68d_新建 DOCX 文档16858.png');
INSERT INTO `t_productimage` VALUES (551, 158, 1, '9e17cdc7-2494-4391-8d8b-b037aeaebdf7_iNdeX_新建 DOCX 文档16857.png');
INSERT INTO `t_productimage` VALUES (552, 158, 0, '7891ffdf-dce3-420c-9721-5e28328a98b1_新建 DOCX 文档16859.png');
INSERT INTO `t_productimage` VALUES (553, 158, 0, '355ad214-f638-45a4-baea-7835756113b2_新建 DOCX 文档16860.png');
INSERT INTO `t_productimage` VALUES (554, 158, 0, '373886aa-ae8d-4b68-8889-d5dabd154f54_新建 DOCX 文档16861.png');
INSERT INTO `t_productimage` VALUES (555, 159, 1, '8f36190d-ef7b-420f-8e2f-a61e88f1ace7_iNdeX_新建 DOCX 文档17087.png');
INSERT INTO `t_productimage` VALUES (556, 159, 0, 'c96e4dc7-d02f-40be-86ee-17de146d6cfb_新建 DOCX 文档17088.png');
INSERT INTO `t_productimage` VALUES (557, 159, 0, '011a1bc3-13b8-4912-a096-d9f1a68ae8ec_新建 DOCX 文档17089.png');
INSERT INTO `t_productimage` VALUES (558, 159, 0, 'f35c5744-f1bc-4c93-92ad-45eb8dc7e765_新建 DOCX 文档17090.png');
INSERT INTO `t_productimage` VALUES (559, 159, 0, 'b4cf2990-1d18-4e76-a3b5-bedb76a73b53_新建 DOCX 文档17091.png');
INSERT INTO `t_productimage` VALUES (560, 160, 0, '4524c212-7565-450c-a118-b460f36f5c78_新建 DOCX 文档17438.png');
INSERT INTO `t_productimage` VALUES (561, 160, 0, '0ed35d1c-16d9-41ba-99b5-7f681e251c35_新建 DOCX 文档17439.png');
INSERT INTO `t_productimage` VALUES (562, 160, 0, 'aa0c6bf2-29e7-4286-ada6-b538fd2eaeee_新建 DOCX 文档17442.png');
INSERT INTO `t_productimage` VALUES (563, 160, 1, '29911d8c-4a02-4a9c-9c33-7ffd5806590d_iNdeX_新建 DOCX 文档17437.png');
INSERT INTO `t_productimage` VALUES (564, 160, 0, 'ca1ca4d1-27dc-4e65-bd0c-6b169cc26e58_新建 DOCX 文档17440.png');
INSERT INTO `t_productimage` VALUES (565, 161, 0, 'e2b86474-b0db-40bc-b157-e4a4f03481e9_新建 DOCX 文档17654.png');
INSERT INTO `t_productimage` VALUES (566, 161, 0, '2f5fb410-38ee-4f70-9de6-d26afb8fd8b9_新建 DOCX 文档17653.png');
INSERT INTO `t_productimage` VALUES (567, 161, 0, 'e3d92233-5719-45ab-b9e8-cdc70f0d9b5c_新建 DOCX 文档17655.png');
INSERT INTO `t_productimage` VALUES (568, 161, 0, '0eaff330-21b0-44ee-aac8-75917406fa14_新建 DOCX 文档17656.png');
INSERT INTO `t_productimage` VALUES (569, 161, 1, '83a68351-7fe2-4c30-ab09-646b00923072_iNdeX_新建 DOCX 文档17652.png');
INSERT INTO `t_productimage` VALUES (570, 162, 1, 'd6467b62-7499-45d8-bc47-6e48a0e298ca_iNdeX_新建 DOCX 文档17879.png');
INSERT INTO `t_productimage` VALUES (571, 162, 0, '9f933c9e-2ab5-4e41-8d9f-d5f8a089a653_新建 DOCX 文档17880.png');
INSERT INTO `t_productimage` VALUES (572, 162, 0, '86750a85-f78c-4b32-9465-0b5339b6c540_新建 DOCX 文档17881.png');
INSERT INTO `t_productimage` VALUES (573, 162, 0, '98ccaf82-2caf-43a7-9799-9abec1914887_新建 DOCX 文档17882.png');
INSERT INTO `t_productimage` VALUES (574, 162, 0, '74bd9992-8d4d-4dce-9f27-14aad0aeb34d_新建 DOCX 文档17883.png');
INSERT INTO `t_productimage` VALUES (575, 163, 0, '4984c82e-9629-4f3e-827a-e009c71ab01b_新建 DOCX 文档18312.png');
INSERT INTO `t_productimage` VALUES (576, 163, 1, 'a85bbb47-4e00-4ff3-90e7-5e516cb43446_iNdeX_新建 DOCX 文档18308.png');
INSERT INTO `t_productimage` VALUES (577, 163, 0, 'a16bbedb-c57e-4348-8f7c-3d5e531fe565_新建 DOCX 文档18311.png');
INSERT INTO `t_productimage` VALUES (578, 163, 0, '84ad70e5-57c7-4e8d-bc22-84ab8e6a2cd5_新建 DOCX 文档18310.png');
INSERT INTO `t_productimage` VALUES (579, 163, 0, 'a6c59000-894d-4978-a975-f5de7dd825f3_新建 DOCX 文档18309.png');
INSERT INTO `t_productimage` VALUES (580, 164, 0, '4daa3944-daab-4e5e-97b1-526a56c9b7ba_新建 DOCX 文档18516.png');
INSERT INTO `t_productimage` VALUES (581, 164, 1, 'e0c29b72-819f-4522-97cd-d41bdba670d9_iNdeX_新建 DOCX 文档18512.png');
INSERT INTO `t_productimage` VALUES (582, 164, 0, '8a86769b-5e90-4513-906a-e06fab2989d0_新建 DOCX 文档18513.png');
INSERT INTO `t_productimage` VALUES (583, 164, 0, '0ec6f164-8c0d-41da-b763-cadea6f3fba8_新建 DOCX 文档18515.png');
INSERT INTO `t_productimage` VALUES (584, 164, 0, '9c37d666-e234-456b-b95c-5037ba2cde86_新建 DOCX 文档18517.png');
INSERT INTO `t_productimage` VALUES (585, 165, 0, '1668f480-0f43-4968-8ec2-a9f737c5601e_新建 DOCX 文档18715.png');
INSERT INTO `t_productimage` VALUES (586, 165, 1, '6cff0a9c-6e9e-4299-9f05-98a08793a224_iNdeX_新建 DOCX 文档18712.png');
INSERT INTO `t_productimage` VALUES (587, 165, 0, '0014f327-ca92-4209-9222-42434df26388_新建 DOCX 文档18713.png');
INSERT INTO `t_productimage` VALUES (588, 165, 0, '7295d227-3c9e-4703-9998-19e2bec11c59_新建 DOCX 文档18716.png');
INSERT INTO `t_productimage` VALUES (589, 165, 0, '55fdf053-2426-43b0-a12a-c206ee52e786_新建 DOCX 文档18717.png');
INSERT INTO `t_productimage` VALUES (590, 166, 1, '03db97a8-dccb-404c-b37f-201ee8996973_iNdeX_新建 DOCX 文档19170.png');
INSERT INTO `t_productimage` VALUES (591, 166, 0, '39e4990b-8fce-4d47-938b-83c35557660f_新建 DOCX 文档19171.png');
INSERT INTO `t_productimage` VALUES (592, 166, 0, 'ea813c22-1bf1-463a-92d3-423b5c46e4e0_新建 DOCX 文档19172.png');
INSERT INTO `t_productimage` VALUES (593, 166, 0, '1de5769c-a8cd-41d7-b466-3f2d6efd5b0b_新建 DOCX 文档19173.png');
INSERT INTO `t_productimage` VALUES (594, 166, 0, 'bdbfed48-6bf0-4233-a58e-1fbea269d2d8_新建 DOCX 文档19174.png');
INSERT INTO `t_productimage` VALUES (595, 167, 0, 'cd02c904-8142-477c-9f39-57b714fa9400_新建 DOCX 文档19484.png');
INSERT INTO `t_productimage` VALUES (596, 167, 1, 'e56a1465-eae4-4066-9802-5a387e5af454_iNdeX_新建 DOCX 文档19483.png');
INSERT INTO `t_productimage` VALUES (597, 167, 0, '600ede88-a0e5-44ac-8ce5-f10331483892_新建 DOCX 文档19485.png');
INSERT INTO `t_productimage` VALUES (598, 167, 0, '255f2c80-58f0-4a98-a15e-96048a682e06_新建 DOCX 文档19486.png');
INSERT INTO `t_productimage` VALUES (599, 167, 0, 'f4839b63-6732-4a00-bd0f-3d1ad76303dd_新建 DOCX 文档19487.png');
INSERT INTO `t_productimage` VALUES (600, 168, 0, '2cce736d-4b22-4a7c-982c-85841e458200_新建 DOCX 文档19897.png');
INSERT INTO `t_productimage` VALUES (601, 168, 0, 'a58c3243-a838-4303-b7e6-f34b32efd612_新建 DOCX 文档19894.png');
INSERT INTO `t_productimage` VALUES (602, 168, 1, 'd2de5b9a-671e-4f38-ab61-c411ddb2a31e_iNdeX_新建 DOCX 文档19893.png');
INSERT INTO `t_productimage` VALUES (603, 168, 0, '21d0c134-3cf6-41aa-af2b-6e1e4c31b12c_新建 DOCX 文档19896.png');
INSERT INTO `t_productimage` VALUES (604, 168, 0, '60941416-f3f4-4cbb-8108-25a38c406bb1_新建 DOCX 文档19895.png');
INSERT INTO `t_productimage` VALUES (605, 169, 0, '29694d9e-a942-476a-9e8a-8a80cf60675a_新建 DOCX 文档20221.png');
INSERT INTO `t_productimage` VALUES (606, 169, 1, 'da377ce0-8435-4ac5-884c-e7072e145dde_iNdeX_新建 DOCX 文档20213.png');
INSERT INTO `t_productimage` VALUES (607, 169, 0, 'bba5fd44-b119-4591-aba3-bb8f6e598f96_新建 DOCX 文档20222.png');
INSERT INTO `t_productimage` VALUES (608, 169, 0, '3fcc0b72-be34-4457-ad85-97fde1a13c1e_新建 DOCX 文档20214.png');
INSERT INTO `t_productimage` VALUES (609, 169, 0, '7211905a-bde9-455e-bc49-ee56ef68dc58_新建 DOCX 文档20223.png');
INSERT INTO `t_productimage` VALUES (610, 170, 1, 'dd3ac0ee-814c-4582-aa06-3d1fdc9a448c_iNdeX_新建 DOCX 文档9858.png');
INSERT INTO `t_productimage` VALUES (611, 170, 0, 'a424623b-5fca-4df1-afb9-915bdfa205ac_新建 DOCX 文档9860.png');
INSERT INTO `t_productimage` VALUES (612, 170, 0, 'a7415dc2-d1d9-4667-8cc0-c18d84e70d33_新建 DOCX 文档9862.png');
INSERT INTO `t_productimage` VALUES (613, 170, 0, '2f2bb8fe-4130-4122-863e-f575a7dded5f_新建 DOCX 文档9861.png');
INSERT INTO `t_productimage` VALUES (614, 170, 0, '0acd35e4-4add-475e-8a08-6b3ef53ef0b7_新建 DOCX 文档9859.png');
INSERT INTO `t_productimage` VALUES (615, 172, 0, '9318b06f-9234-4691-bc80-8ca8bae08f4a_新建 DOCX 文档20722.png');
INSERT INTO `t_productimage` VALUES (616, 172, 1, 'ca6d42e8-a3de-441a-974e-1ea4d0076772_iNdeX_新建 DOCX 文档20721.png');
INSERT INTO `t_productimage` VALUES (617, 172, 0, '3963d986-9bb5-4a68-9df5-7381897bc560_新建 DOCX 文档20723.png');
INSERT INTO `t_productimage` VALUES (618, 172, 0, '4d1e3c80-99f7-4f89-8c79-de546cc53995_新建 DOCX 文档20724.png');
INSERT INTO `t_productimage` VALUES (619, 172, 0, 'cbb1526c-eb2d-43ff-b797-7138bf7bbb94_新建 DOCX 文档20725.png');
INSERT INTO `t_productimage` VALUES (628, 174, 0, 'db2223d7-eeeb-42c0-bea3-3cc63b79099c_新建 DOCX 文档21471.png');
INSERT INTO `t_productimage` VALUES (629, 174, 0, 'cb411a50-04b5-4fde-9634-d2c67585f499_新建 DOCX 文档21472.png');
INSERT INTO `t_productimage` VALUES (630, 174, 0, '81c0b4fd-0889-466b-9f5d-01a05274cc8a_新建 DOCX 文档21473.png');
INSERT INTO `t_productimage` VALUES (631, 174, 1, '55a27ed0-6d55-4564-a07d-2dd23bdfca91_iNdeX_新建 DOCX 文档21470.png');
INSERT INTO `t_productimage` VALUES (632, 174, 0, '3c66805b-a918-4eb1-b629-af694b278e34_新建 DOCX 文档21474.png');
INSERT INTO `t_productimage` VALUES (643, 176, 0, 'c4d1dc3f-a15c-43a3-88ba-1feaa4f608d1_新建 DOCX 文档22085.png');
INSERT INTO `t_productimage` VALUES (644, 176, 0, '2a5ccb69-1efe-4d41-9fda-86713d5d79d4_新建 DOCX 文档22087.png');
INSERT INTO `t_productimage` VALUES (645, 176, 0, '57b3c3ec-1b5d-4eb3-8cb1-967111058ca9_新建 DOCX 文档22086.png');
INSERT INTO `t_productimage` VALUES (646, 176, 1, '3f7615e6-7ba6-4f4b-bf12-0dd5c8cf84fe_iNdeX_新建 DOCX 文档22084.png');
INSERT INTO `t_productimage` VALUES (647, 176, 0, '7806cf3a-e295-4a38-b329-96c0e2b5ff5e_新建 DOCX 文档22088.png');
INSERT INTO `t_productimage` VALUES (648, 177, 1, 'c0396190-680b-4dbc-b2b8-6de1e088c553_iNdeX_新建 DOCX 文档22598.png');
INSERT INTO `t_productimage` VALUES (649, 177, 0, '3e6be29d-568f-4cfa-9987-43797801e6a2_新建 DOCX 文档22599.png');
INSERT INTO `t_productimage` VALUES (650, 177, 0, 'af127837-5fc6-4fca-842f-c41b5f387cf8_新建 DOCX 文档22602.png');
INSERT INTO `t_productimage` VALUES (651, 177, 0, '81f82cf1-5429-4a48-b38a-1315cbdfc5ce_新建 DOCX 文档22601.png');
INSERT INTO `t_productimage` VALUES (652, 177, 0, '0b894dfd-4d9c-4e6e-87c7-348ff9409743_新建 DOCX 文档22600.png');
INSERT INTO `t_productimage` VALUES (653, 178, 0, 'da3f0b98-e445-495f-8540-b617398ce3e8_新建 DOCX 文档23258.png');
INSERT INTO `t_productimage` VALUES (654, 178, 1, 'a0ad62e6-be7b-4e03-ac57-9177c5efb00a_iNdeX_新建 DOCX 文档23257.png');
INSERT INTO `t_productimage` VALUES (655, 178, 0, '17b06025-a1b6-414b-88d8-329eccdf1832_新建 DOCX 文档23262.png');
INSERT INTO `t_productimage` VALUES (656, 178, 0, '50085f2c-bb34-4846-b8ab-caca7f322886_新建 DOCX 文档23260.png');
INSERT INTO `t_productimage` VALUES (657, 178, 0, '1e31dfa2-039e-4fc7-8fbb-e65d20c57ee7_新建 DOCX 文档23261.png');
INSERT INTO `t_productimage` VALUES (658, 180, 1, 'f8624a4d-e9cc-43c2-b13b-c3e54867b76c_新建 DOCX 文档15199.png');

-- ----------------------------
-- Table structure for t_property
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property`  (
  `propertyId` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL COMMENT 't_category-Id',
  `propertyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`propertyId`) USING BTREE,
  INDEX `fk_property_category`(`cid`) USING BTREE,
  CONSTRAINT `fk_property_category` FOREIGN KEY (`cid`) REFERENCES `t_category` (`categoryId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_property
-- ----------------------------
INSERT INTO `t_property` VALUES (1, 1, '品牌');
INSERT INTO `t_property` VALUES (3, 1, '材质');
INSERT INTO `t_property` VALUES (4, 1, '尺码');
INSERT INTO `t_property` VALUES (6, 1, '颜色');
INSERT INTO `t_property` VALUES (19, 1, '适用年龄');
INSERT INTO `t_property` VALUES (33, 15, '品牌');
INSERT INTO `t_property` VALUES (34, 15, '颜色');
INSERT INTO `t_property` VALUES (35, 15, '货号');
INSERT INTO `t_property` VALUES (36, 15, '材质');
INSERT INTO `t_property` VALUES (37, 10, '品牌');
INSERT INTO `t_property` VALUES (38, 10, '生产许可证编号');
INSERT INTO `t_property` VALUES (39, 10, '厂名');
INSERT INTO `t_property` VALUES (40, 10, '保质期');
INSERT INTO `t_property` VALUES (41, 10, '储藏方法');
INSERT INTO `t_property` VALUES (42, 10, '食品添加剂');
INSERT INTO `t_property` VALUES (43, 10, '净含量');
INSERT INTO `t_property` VALUES (44, 10, '产地');
INSERT INTO `t_property` VALUES (45, 10, '省份');
INSERT INTO `t_property` VALUES (46, 10, '城市');
INSERT INTO `t_property` VALUES (47, 24, '品牌');
INSERT INTO `t_property` VALUES (48, 24, '货号');
INSERT INTO `t_property` VALUES (49, 24, '规格类型');
INSERT INTO `t_property` VALUES (50, 24, '原料成分');
INSERT INTO `t_property` VALUES (51, 24, '功效');
INSERT INTO `t_property` VALUES (52, 24, '是否为特殊用途化妆品');
INSERT INTO `t_property` VALUES (53, 24, '适合肤质');
INSERT INTO `t_property` VALUES (54, 18, '品牌');
INSERT INTO `t_property` VALUES (55, 18, '图案');
INSERT INTO `t_property` VALUES (57, 18, '成色');
INSERT INTO `t_property` VALUES (58, 18, '大小');
INSERT INTO `t_property` VALUES (59, 16, '品牌');
INSERT INTO `t_property` VALUES (60, 25, '品牌名称');
INSERT INTO `t_property` VALUES (61, 25, '证书编号');
INSERT INTO `t_property` VALUES (62, 25, '机身颜色');
INSERT INTO `t_property` VALUES (63, 25, '运行内存RAM');
INSERT INTO `t_property` VALUES (64, 25, '存储容量');
INSERT INTO `t_property` VALUES (65, 22, '品牌名称');
INSERT INTO `t_property` VALUES (66, 22, '颜色');
INSERT INTO `t_property` VALUES (67, 22, '尺码');
INSERT INTO `t_property` VALUES (68, 5, '品牌名称');
INSERT INTO `t_property` VALUES (69, 5, '颜色');
INSERT INTO `t_property` VALUES (70, 5, '手表种类');
INSERT INTO `t_property` VALUES (71, 20, '品牌');
INSERT INTO `t_property` VALUES (72, 20, '型号');
INSERT INTO `t_property` VALUES (73, 20, '适用年龄');
INSERT INTO `t_property` VALUES (74, 20, '适用性别');
INSERT INTO `t_property` VALUES (75, 20, '颜色分类');
INSERT INTO `t_property` VALUES (76, 20, '大小描述');
INSERT INTO `t_property` VALUES (77, 17, '品牌名称');
INSERT INTO `t_property` VALUES (78, 17, '配件类型');
INSERT INTO `t_property` VALUES (79, 17, '成色');
INSERT INTO `t_property` VALUES (80, 17, '价格区间');
INSERT INTO `t_property` VALUES (81, 17, '颜色分类');
INSERT INTO `t_property` VALUES (82, 17, '货号');
INSERT INTO `t_property` VALUES (83, 19, '品牌名称');
INSERT INTO `t_property` VALUES (84, 19, '型号');
INSERT INTO `t_property` VALUES (85, 19, '适用年龄');
INSERT INTO `t_property` VALUES (86, 19, '货号');
INSERT INTO `t_property` VALUES (87, 19, '颜色分类');
INSERT INTO `t_property` VALUES (88, 21, '品牌名称');
INSERT INTO `t_property` VALUES (90, 21, '型号');
INSERT INTO `t_property` VALUES (91, 21, '材质');
INSERT INTO `t_property` VALUES (92, 21, '颜色分类');
INSERT INTO `t_property` VALUES (93, 21, '大小');
INSERT INTO `t_property` VALUES (94, 23, '品牌名称');
INSERT INTO `t_property` VALUES (95, 23, '材质');

-- ----------------------------
-- Table structure for t_propertyvalue
-- ----------------------------
DROP TABLE IF EXISTS `t_propertyvalue`;
CREATE TABLE `t_propertyvalue`  (
  `propertyvalueId` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT 't_product-Id',
  `ptid` int(11) DEFAULT NULL COMMENT 't_property-Id',
  `propertyValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`propertyvalueId`) USING BTREE,
  INDEX `fk_propertyvalue_property`(`ptid`) USING BTREE,
  INDEX `fk_propertyvalue_product`(`pid`) USING BTREE,
  CONSTRAINT `fk_propertyvalue_product` FOREIGN KEY (`pid`) REFERENCES `t_product` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_propertyvalue_property` FOREIGN KEY (`ptid`) REFERENCES `t_property` (`propertyId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_propertyvalue
-- ----------------------------
INSERT INTO `t_propertyvalue` VALUES (1, 94, 36, '陶瓷');
INSERT INTO `t_propertyvalue` VALUES (2, 94, 34, '方形胭脂烟灰缸 方形牙色烟灰缸 ');
INSERT INTO `t_propertyvalue` VALUES (3, 95, 35, 'M5');
INSERT INTO `t_propertyvalue` VALUES (4, 95, 33, '渔将');
INSERT INTO `t_propertyvalue` VALUES (5, 95, 34, '旗舰版皇冠帽活动2杆 旗舰版皇冠');
INSERT INTO `t_propertyvalue` VALUES (6, 96, 33, '力彩');
INSERT INTO `t_propertyvalue` VALUES (7, 96, 35, 'lc003');
INSERT INTO `t_propertyvalue` VALUES (8, 96, 34, '七钩典雅黑 七钩纯净白 七钩摩卡铜');
INSERT INTO `t_propertyvalue` VALUES (9, 97, 33, '克欧克');
INSERT INTO `t_propertyvalue` VALUES (10, 97, 35, '669');
INSERT INTO `t_propertyvalue` VALUES (11, 99, 34, '灰色 浅蓝色 粉红色 橘色');
INSERT INTO `t_propertyvalue` VALUES (12, 105, 37, '荷润');
INSERT INTO `t_propertyvalue` VALUES (13, 105, 38, 'SC10632083100128');
INSERT INTO `t_propertyvalue` VALUES (14, 105, 39, '江苏金生缘食品科技有限公司');
INSERT INTO `t_propertyvalue` VALUES (15, 105, 40, '240 天');
INSERT INTO `t_propertyvalue` VALUES (16, 105, 41, '置于阴凉处存放');
INSERT INTO `t_propertyvalue` VALUES (17, 105, 42, '见包装');
INSERT INTO `t_propertyvalue` VALUES (18, 105, 43, ' 4080ml');
INSERT INTO `t_propertyvalue` VALUES (19, 105, 45, '江苏省');
INSERT INTO `t_propertyvalue` VALUES (20, 105, 44, '中国大陆');
INSERT INTO `t_propertyvalue` VALUES (21, 105, 46, '淮安市');
INSERT INTO `t_propertyvalue` VALUES (22, 106, 38, 'SC10644200003332');
INSERT INTO `t_propertyvalue` VALUES (23, 106, 39, '中山市恬之乐饮料有限公司');
INSERT INTO `t_propertyvalue` VALUES (24, 106, 41, '常温');
INSERT INTO `t_propertyvalue` VALUES (25, 106, 40, '365');
INSERT INTO `t_propertyvalue` VALUES (26, 106, 42, '见罐身');
INSERT INTO `t_propertyvalue` VALUES (27, 106, 44, '中国大陆');
INSERT INTO `t_propertyvalue` VALUES (28, 106, 46, '');
INSERT INTO `t_propertyvalue` VALUES (29, 106, 45, '江西省');
INSERT INTO `t_propertyvalue` VALUES (30, 106, 43, '3920ml');
INSERT INTO `t_propertyvalue` VALUES (31, 107, 37, '好想你');
INSERT INTO `t_propertyvalue` VALUES (32, 107, 38, 'SC11635062900021');
INSERT INTO `t_propertyvalue` VALUES (33, 107, 39, '福建立兴食品有限公司');
INSERT INTO `t_propertyvalue` VALUES (34, 107, 41, '阴凉干燥处');
INSERT INTO `t_propertyvalue` VALUES (35, 107, 40, '360 天');
INSERT INTO `t_propertyvalue` VALUES (36, 107, 42, '无');
INSERT INTO `t_propertyvalue` VALUES (37, 107, 43, '364g');
INSERT INTO `t_propertyvalue` VALUES (38, 107, 44, '中国大陆');
INSERT INTO `t_propertyvalue` VALUES (39, 107, 45, ' 福建省');
INSERT INTO `t_propertyvalue` VALUES (40, 108, 37, '周黑鸭');
INSERT INTO `t_propertyvalue` VALUES (41, 108, 38, 'SC20142011200014');
INSERT INTO `t_propertyvalue` VALUES (42, 108, 39, '湖北周黑鸭食品工业园有限公司');
INSERT INTO `t_propertyvalue` VALUES (43, 108, 41, '冷藏保存');
INSERT INTO `t_propertyvalue` VALUES (44, 108, 40, '5 天');
INSERT INTO `t_propertyvalue` VALUES (45, 108, 44, '中国大陆');
INSERT INTO `t_propertyvalue` VALUES (46, 108, 45, '湖北省');
INSERT INTO `t_propertyvalue` VALUES (47, 108, 46, '武汉市');
INSERT INTO `t_propertyvalue` VALUES (48, 108, 43, '320g');
INSERT INTO `t_propertyvalue` VALUES (49, 110, 37, '上好旺');
INSERT INTO `t_propertyvalue` VALUES (50, 110, 38, 'SC10833010900262');
INSERT INTO `t_propertyvalue` VALUES (51, 110, 39, '杭州诺丁食品有限公司');
INSERT INTO `t_propertyvalue` VALUES (52, 110, 40, '360 天');
INSERT INTO `t_propertyvalue` VALUES (53, 110, 42, '详见包装');
INSERT INTO `t_propertyvalue` VALUES (54, 110, 41, '存放于阴凉干燥处，避免阳光直射，开封后请立即食用。');
INSERT INTO `t_propertyvalue` VALUES (55, 110, 43, '500g');
INSERT INTO `t_propertyvalue` VALUES (56, 110, 44, '中国大陆');
INSERT INTO `t_propertyvalue` VALUES (57, 110, 45, '浙江省');
INSERT INTO `t_propertyvalue` VALUES (58, 110, 46, '杭州市');
INSERT INTO `t_propertyvalue` VALUES (59, 111, 47, 'MOM FACE/亲润');
INSERT INTO `t_propertyvalue` VALUES (60, 111, 48, 'QRZT-343');
INSERT INTO `t_propertyvalue` VALUES (61, 111, 50, '其他');
INSERT INTO `t_propertyvalue` VALUES (62, 111, 51, '提亮肤色');
INSERT INTO `t_propertyvalue` VALUES (63, 111, 49, '正常规格');
INSERT INTO `t_propertyvalue` VALUES (64, 111, 52, '否');
INSERT INTO `t_propertyvalue` VALUES (65, 111, 53, '任何肤质');
INSERT INTO `t_propertyvalue` VALUES (66, 112, 47, 'Kangaroo Mommy/袋鼠妈妈');
INSERT INTO `t_propertyvalue` VALUES (67, 112, 48, ' xm111');
INSERT INTO `t_propertyvalue` VALUES (68, 112, 49, ' 正常规格');
INSERT INTO `t_propertyvalue` VALUES (69, 112, 51, '深度保湿');
INSERT INTO `t_propertyvalue` VALUES (70, 112, 50, '其他');
INSERT INTO `t_propertyvalue` VALUES (71, 112, 52, '否');
INSERT INTO `t_propertyvalue` VALUES (72, 112, 53, ' 任何肤质');
INSERT INTO `t_propertyvalue` VALUES (73, 113, 47, 'HomeFacialPro');
INSERT INTO `t_propertyvalue` VALUES (74, 113, 51, '补水 舒缓肌肤');
INSERT INTO `t_propertyvalue` VALUES (75, 113, 52, ' 否');
INSERT INTO `t_propertyvalue` VALUES (76, 113, 49, '正常规格');
INSERT INTO `t_propertyvalue` VALUES (77, 113, 53, '任何肤质');
INSERT INTO `t_propertyvalue` VALUES (78, 116, 54, 'other/其他');
INSERT INTO `t_propertyvalue` VALUES (79, 116, 55, '纯色');
INSERT INTO `t_propertyvalue` VALUES (80, 116, 57, '全新');
INSERT INTO `t_propertyvalue` VALUES (81, 116, 58, '中');
INSERT INTO `t_propertyvalue` VALUES (82, 117, 54, 'other/其他');
INSERT INTO `t_propertyvalue` VALUES (83, 117, 58, '中');
INSERT INTO `t_propertyvalue` VALUES (84, 117, 57, '全新');
INSERT INTO `t_propertyvalue` VALUES (85, 117, 55, '纯色');
INSERT INTO `t_propertyvalue` VALUES (86, 118, 54, '慕姿');
INSERT INTO `t_propertyvalue` VALUES (87, 118, 55, '纯色');
INSERT INTO `t_propertyvalue` VALUES (88, 118, 57, '全新');
INSERT INTO `t_propertyvalue` VALUES (89, 118, 58, '小');
INSERT INTO `t_propertyvalue` VALUES (90, 119, 54, 'other/其他');
INSERT INTO `t_propertyvalue` VALUES (91, 119, 55, '纯色');
INSERT INTO `t_propertyvalue` VALUES (92, 119, 57, '全新');
INSERT INTO `t_propertyvalue` VALUES (93, 119, 58, '中');
INSERT INTO `t_propertyvalue` VALUES (94, 120, 58, '大');
INSERT INTO `t_propertyvalue` VALUES (95, 120, 54, 'BEEP');
INSERT INTO `t_propertyvalue` VALUES (96, 120, 55, '纯色');
INSERT INTO `t_propertyvalue` VALUES (97, 120, 57, '全新');
INSERT INTO `t_propertyvalue` VALUES (98, 90, 6, '绿色 黑色 红色 粉色');
INSERT INTO `t_propertyvalue` VALUES (99, 90, 4, 'S M L XL 2XL 3XL 4XL');
INSERT INTO `t_propertyvalue` VALUES (100, 91, 1, 'Peoleo/飘蕾');
INSERT INTO `t_propertyvalue` VALUES (101, 91, 19, '25-29周岁');
INSERT INTO `t_propertyvalue` VALUES (102, 91, 6, '藏青色');
INSERT INTO `t_propertyvalue` VALUES (103, 121, 19, '适用年龄: 18-24周岁');
INSERT INTO `t_propertyvalue` VALUES (104, 121, 3, '棉');
INSERT INTO `t_propertyvalue` VALUES (105, 121, 4, ' S M L XL 2XL');
INSERT INTO `t_propertyvalue` VALUES (106, 122, 4, 'M L XL 2XL');
INSERT INTO `t_propertyvalue` VALUES (107, 122, 6, '米白色 黑色 粉红色 卡其色 蓝色');
INSERT INTO `t_propertyvalue` VALUES (108, 122, 1, '罗琳罗卡');
INSERT INTO `t_propertyvalue` VALUES (109, 123, 19, '25-29周岁');
INSERT INTO `t_propertyvalue` VALUES (110, 123, 4, '大码XL 大码XXL 大码XXXL 大码XXXXL 大码L');
INSERT INTO `t_propertyvalue` VALUES (111, 123, 3, '其他');
INSERT INTO `t_propertyvalue` VALUES (112, 123, 6, '白色 条纹');
INSERT INTO `t_propertyvalue` VALUES (113, 129, 59, 'Telamon ');
INSERT INTO `t_propertyvalue` VALUES (114, 130, 59, ' FURJA/富佳');
INSERT INTO `t_propertyvalue` VALUES (115, 131, 59, 'Robam/老板');
INSERT INTO `t_propertyvalue` VALUES (116, 132, 59, 'Fujitsu/富士通');
INSERT INTO `t_propertyvalue` VALUES (117, 133, 59, '微和');
INSERT INTO `t_propertyvalue` VALUES (118, 134, 60, 'vivo');
INSERT INTO `t_propertyvalue` VALUES (119, 134, 61, '2018011606118627');
INSERT INTO `t_propertyvalue` VALUES (120, 134, 62, '极光色 极光红 星夜黑');
INSERT INTO `t_propertyvalue` VALUES (121, 134, 63, '3GB/4GB');
INSERT INTO `t_propertyvalue` VALUES (122, 134, 64, ' 3+32GB 3+64GB 4+64GB');
INSERT INTO `t_propertyvalue` VALUES (123, 136, 60, 'K-Touch/天语');
INSERT INTO `t_propertyvalue` VALUES (124, 136, 61, '2018011606053530');
INSERT INTO `t_propertyvalue` VALUES (125, 136, 62, '红色 白色 黑色 金色 蓝色');
INSERT INTO `t_propertyvalue` VALUES (126, 136, 63, '128MB');
INSERT INTO `t_propertyvalue` VALUES (127, 136, 64, '128MB');
INSERT INTO `t_propertyvalue` VALUES (128, 138, 63, '4GB 6GB');
INSERT INTO `t_propertyvalue` VALUES (129, 138, 64, '4+64GB 6+64GB');
INSERT INTO `t_propertyvalue` VALUES (130, 138, 61, '2018011606113631');
INSERT INTO `t_propertyvalue` VALUES (131, 138, 62, '银光绿 摩卡红 梵星蓝 墨玉黑');
INSERT INTO `t_propertyvalue` VALUES (132, 138, 60, 'OPPO');
INSERT INTO `t_propertyvalue` VALUES (133, 139, 60, 'Nokia/诺基亚');
INSERT INTO `t_propertyvalue` VALUES (134, 139, 61, '2018011606062484');
INSERT INTO `t_propertyvalue` VALUES (135, 139, 62, '黑色 蓝色 白色 灰色 红色');
INSERT INTO `t_propertyvalue` VALUES (136, 139, 63, '4MB');
INSERT INTO `t_propertyvalue` VALUES (137, 139, 64, '4MB');
INSERT INTO `t_propertyvalue` VALUES (138, 170, 60, 'VJVJ');
INSERT INTO `t_propertyvalue` VALUES (139, 144, 65, '安妮可');
INSERT INTO `t_propertyvalue` VALUES (140, 145, 65, 'TIMBERLAND/添柏岚');
INSERT INTO `t_propertyvalue` VALUES (141, 145, 67, '40 41 41.5 42 43 43.5 44 44.5 45');
INSERT INTO `t_propertyvalue` VALUES (142, 145, 66, 'A1QR2M/铁灰绿');
INSERT INTO `t_propertyvalue` VALUES (143, 146, 65, 'TIMBERLAND/添柏岚');
INSERT INTO `t_propertyvalue` VALUES (144, 146, 67, '46 47.5 39.5 40 41 41.5 42 43 43.5 44 44.5 45');
INSERT INTO `t_propertyvalue` VALUES (145, 146, 66, 'A17BBW/小麦色');
INSERT INTO `t_propertyvalue` VALUES (146, 147, 65, ' TOCLASSY');
INSERT INTO `t_propertyvalue` VALUES (147, 147, 67, '35 36 37 38 39 40 41 42 43');
INSERT INTO `t_propertyvalue` VALUES (148, 147, 66, '黑色+羊皮内里 酒红色+羊皮内里');
INSERT INTO `t_propertyvalue` VALUES (149, 148, 65, 'Aubany');
INSERT INTO `t_propertyvalue` VALUES (150, 148, 66, '177黑色 177青灰色 177咖啡色 177杏色 177');
INSERT INTO `t_propertyvalue` VALUES (151, 148, 67, ' 38 39 40 41 42 43 44');
INSERT INTO `t_propertyvalue` VALUES (152, 149, 68, 'Daniel Wellington');
INSERT INTO `t_propertyvalue` VALUES (153, 149, 69, 'Melrose Black Sterling Black Melrose Wh');
INSERT INTO `t_propertyvalue` VALUES (154, 149, 70, '女');
INSERT INTO `t_propertyvalue` VALUES (155, 150, 68, 'Tian Wang/天王');
INSERT INTO `t_propertyvalue` VALUES (156, 150, 70, '男');
INSERT INTO `t_propertyvalue` VALUES (157, 150, 69, '白色 黑色');
INSERT INTO `t_propertyvalue` VALUES (158, 151, 68, 'Daniel Wellington');
INSERT INTO `t_propertyvalue` VALUES (159, 151, 70, ' 男');
INSERT INTO `t_propertyvalue` VALUES (160, 151, 69, 'St Mawes Rosegold Bristol Rose');
INSERT INTO `t_propertyvalue` VALUES (161, 152, 68, '表魅');
INSERT INTO `t_propertyvalue` VALUES (162, 152, 69, '白银表盘+光圈+牛皮表带 白银表盘+光圈+鳄鱼皮表带 玫瑰金表盘+光圈+牛皮表带 镀金表盘+光圈+牛皮表带 如需更换表带颜色请联系客服 镀金表盘+镶钻表圈+牛皮表带 玫瑰金盘+镶钻表圈+牛皮表带 白银表盘+镶钻表圈+牛皮表带 白银表盘+镶钻表圈+鳄鱼皮表带');
INSERT INTO `t_propertyvalue` VALUES (163, 152, 70, '中性');
INSERT INTO `t_propertyvalue` VALUES (164, 153, 68, '表魅');
INSERT INTO `t_propertyvalue` VALUES (165, 153, 69, '白银表盘+光圈+牛皮表带 白银表盘+光圈+鳄鱼皮表带 白银表盘+镶钻表圈+牛皮表带 白银表盘+镶钻表圈+鳄鱼皮表带 玫瑰金表盘+镶钻表圈+牛皮表带 玫瑰金表盘+光圈+牛皮表带 镀金表盘+镶钻表圈+牛皮表带 镀金表盘+光圈+牛皮表带 表带颜色均可变换请联系客服');
INSERT INTO `t_propertyvalue` VALUES (166, 153, 70, '中性');
INSERT INTO `t_propertyvalue` VALUES (167, 154, 71, 'jlshou');
INSERT INTO `t_propertyvalue` VALUES (168, 154, 73, '6岁 7岁 8岁 9岁 10岁 11岁 12岁 13岁 14岁 14岁以上');
INSERT INTO `t_propertyvalue` VALUES (169, 154, 74, '中性');
INSERT INTO `t_propertyvalue` VALUES (170, 154, 75, '紫色 天蓝色 深蓝色 玫红色 红色 黄色 绿色 彩色 金属');
INSERT INTO `t_propertyvalue` VALUES (171, 154, 72, 'jlsm01');
INSERT INTO `t_propertyvalue` VALUES (172, 155, 71, '诺森德');
INSERT INTO `t_propertyvalue` VALUES (173, 155, 72, ' N-511');
INSERT INTO `t_propertyvalue` VALUES (174, 155, 73, '3岁 4岁 5岁 6岁 7岁 8岁 9岁 10岁 11岁 12岁 13岁 14岁');
INSERT INTO `t_propertyvalue` VALUES (175, 155, 75, '【豪华跑车版】【音乐+大灯】【5CM大宽轮】兰博坚黄 【豪华跑车版】【音乐+大灯】【5CM大宽轮】穆杰罗红 【豪华跑车版】【音乐+大灯】【5CM大宽轮】樱花粉 【豪华跑车版】【音乐+大灯】【5CM大宽轮】多瑙河蓝 【精英跑车版】【5CM大宽轮】兰博坚黄 【精英跑车版】【5CM大宽轮】穆杰罗红 【精英跑车版】【5CM大宽轮】樱花粉 【精英跑车版】【5CM大宽轮】多瑙河蓝 【基础跑车版】兰博坚黄 【基础跑车版】穆杰罗红 【基础跑车版】樱花粉 【基础跑车版】多瑙河蓝');
INSERT INTO `t_propertyvalue` VALUES (176, 157, 71, 'Paperfox/纸贵满堂');
INSERT INTO `t_propertyvalue` VALUES (177, 157, 72, '拼图');
INSERT INTO `t_propertyvalue` VALUES (178, 157, 73, ' ');
INSERT INTO `t_propertyvalue` VALUES (179, 158, 73, '12个月 18个月 2岁 3岁');
INSERT INTO `t_propertyvalue` VALUES (180, 158, 74, ' ');
INSERT INTO `t_propertyvalue` VALUES (181, 158, 71, 'WEPLAY');
INSERT INTO `t_propertyvalue` VALUES (182, 158, 72, '丫丫圈:KT3002-006 触觉棒:KT3304 触觉球（9CM):KT3306 触觉球（7CM).');
INSERT INTO `t_propertyvalue` VALUES (183, 158, 75, ' ');
INSERT INTO `t_propertyvalue` VALUES (184, 159, 71, ' other/其他');
INSERT INTO `t_propertyvalue` VALUES (185, 159, 72, ' QM-ETPT');
INSERT INTO `t_propertyvalue` VALUES (186, 159, 73, '2岁 3岁 4岁 5岁 6岁 7岁 8岁 9岁 10岁 11岁 12岁');
INSERT INTO `t_propertyvalue` VALUES (187, 159, 74, '中性');
INSERT INTO `t_propertyvalue` VALUES (188, 159, 75, ' 小美女拼图组合 动物世界拼');
INSERT INTO `t_propertyvalue` VALUES (189, 157, 75, '配对拼图72个');
INSERT INTO `t_propertyvalue` VALUES (190, 155, 74, '中性');
INSERT INTO `t_propertyvalue` VALUES (191, 154, 76, '超大号 大号 中号 小号');
INSERT INTO `t_propertyvalue` VALUES (192, 160, 77, 'pandora/潘多拉');
INSERT INTO `t_propertyvalue` VALUES (193, 160, 78, '散珠');
INSERT INTO `t_propertyvalue` VALUES (194, 160, 79, '全新');
INSERT INTO `t_propertyvalue` VALUES (195, 160, 80, '201-300元');
INSERT INTO `t_propertyvalue` VALUES (196, 160, 81, '银色');
INSERT INTO `t_propertyvalue` VALUES (197, 160, 82, '797401');
INSERT INTO `t_propertyvalue` VALUES (198, 161, 77, ' other/其他');
INSERT INTO `t_propertyvalue` VALUES (199, 161, 79, '全新');
INSERT INTO `t_propertyvalue` VALUES (200, 161, 80, ' 25-29.99元');
INSERT INTO `t_propertyvalue` VALUES (201, 161, 81, 'R009白色 R009紫色 R020 R021白色 R021紫色 R022 R036白色 R036紫色 R059白色 R059紫色 R060');
INSERT INTO `t_propertyvalue` VALUES (202, 162, 77, 'other/其他');
INSERT INTO `t_propertyvalue` VALUES (203, 162, 79, '全新');
INSERT INTO `t_propertyvalue` VALUES (204, 162, 80, '30-39.99元');
INSERT INTO `t_propertyvalue` VALUES (205, 162, 82, ' WH206');
INSERT INTO `t_propertyvalue` VALUES (206, 163, 77, '老银匠');
INSERT INTO `t_propertyvalue` VALUES (207, 163, 79, '全新');
INSERT INTO `t_propertyvalue` VALUES (208, 163, 80, ' 30-39.99元');
INSERT INTO `t_propertyvalue` VALUES (209, 163, 81, '三花耳扣素银版（梅花） 三花耳扣渡白金版（梅花）');
INSERT INTO `t_propertyvalue` VALUES (210, 163, 82, 'ET3303');
INSERT INTO `t_propertyvalue` VALUES (211, 164, 77, 'other/其他');
INSERT INTO `t_propertyvalue` VALUES (212, 164, 81, '白色 乳白色 红色 气质白 优雅白 清新白 高雅白 简约白 典雅白 时尚白 杏白色 气质蓝');
INSERT INTO `t_propertyvalue` VALUES (213, 164, 80, '51-100元');
INSERT INTO `t_propertyvalue` VALUES (214, 164, 79, '全新');
INSERT INTO `t_propertyvalue` VALUES (215, 165, 83, '推推口袋');
INSERT INTO `t_propertyvalue` VALUES (216, 165, 84, '1099');
INSERT INTO `t_propertyvalue` VALUES (217, 165, 85, '新生 3个月 6个月 12个月');
INSERT INTO `t_propertyvalue` VALUES (218, 165, 86, ' 1099');
INSERT INTO `t_propertyvalue` VALUES (219, 165, 87, '白色 四季款 蓝色 四季款 粉色 四季款 白色 保暖款 蓝色 保暖款 粉色 保暖款 灰色 保暖款 灰色 四季款');
INSERT INTO `t_propertyvalue` VALUES (220, 166, 83, 'eoodoo');
INSERT INTO `t_propertyvalue` VALUES (221, 166, 84, 'HX528754');
INSERT INTO `t_propertyvalue` VALUES (222, 166, 85, '新生 3个月 6个月');
INSERT INTO `t_propertyvalue` VALUES (223, 166, 86, 'HX528754');
INSERT INTO `t_propertyvalue` VALUES (224, 166, 87, '贵族白蒲公英礼盒10件含玩偶 白底旺旺小精灵加厚4件套 待定');
INSERT INTO `t_propertyvalue` VALUES (225, 167, 83, '婴恩贝');
INSERT INTO `t_propertyvalue` VALUES (226, 167, 84, 'YEB806');
INSERT INTO `t_propertyvalue` VALUES (227, 167, 85, ' 新生 3个月 6个月 12个月');
INSERT INTO `t_propertyvalue` VALUES (228, 167, 86, 'YEB806');
INSERT INTO `t_propertyvalue` VALUES (229, 167, 87, '806四季绿23件 806四季绿20件 806四季绿17件 806四季咖23件 806四季咖20件 806四季咖17件 806保暖绿23件 806保暖绿20件 806保暖绿17件 806保暖咖23件 806保暖咖20件 806保暖咖17件');
INSERT INTO `t_propertyvalue` VALUES (230, 168, 83, 'MOM FACE/亲润');
INSERT INTO `t_propertyvalue` VALUES (231, 168, 86, 'X105');
INSERT INTO `t_propertyvalue` VALUES (232, 168, 85, ' 孕早期（0-12周） 孕中期（12—28周） 孕晚期（28—40周）');
INSERT INTO `t_propertyvalue` VALUES (233, 169, 83, ' 巴贝迪');
INSERT INTO `t_propertyvalue` VALUES (234, 169, 84, 'bbd1922');
INSERT INTO `t_propertyvalue` VALUES (235, 169, 85, '新生 3个月 6个月 12个月');
INSERT INTO `t_propertyvalue` VALUES (236, 169, 86, 'bbd1922');
INSERT INTO `t_propertyvalue` VALUES (237, 169, 87, '灰色提花20件套【四季款】 蓝色提花20件套【四季款】 粉色提花20件套【四季款】 灰色提花21件套【四季款】 蓝色提花21件套【四季款】 粉色提花21件套【四季款】 灰色提花20件套【保暖款】 蓝色提花20件套【保暖款】 粉色提花20件套【保暖款】 灰色提花21件套【保暖款】 蓝色提花21件套【保暖款】 粉色提花21件套【保暖款】');
INSERT INTO `t_propertyvalue` VALUES (238, 172, 88, 'Mymikku');
INSERT INTO `t_propertyvalue` VALUES (239, 172, 90, 'MC-101');
INSERT INTO `t_propertyvalue` VALUES (240, 172, 91, '塑料');
INSERT INTO `t_propertyvalue` VALUES (241, 172, 92, 'A组（炸虾） B组（吃面条） C组（银狐）');
INSERT INTO `t_propertyvalue` VALUES (242, 172, 93, '原盒装 手工礼品包装');
INSERT INTO `t_propertyvalue` VALUES (243, 174, 88, '吉猫堂');
INSERT INTO `t_propertyvalue` VALUES (244, 174, 91, ' ');
INSERT INTO `t_propertyvalue` VALUES (245, 176, 88, 'Little CuCu');
INSERT INTO `t_propertyvalue` VALUES (246, 176, 91, ' 毛绒');
INSERT INTO `t_propertyvalue` VALUES (247, 176, 92, '咨询客服有惊喜');
INSERT INTO `t_propertyvalue` VALUES (248, 174, 93, '25*23*28CM');
INSERT INTO `t_propertyvalue` VALUES (249, 177, 95, '粘纤');
INSERT INTO `t_propertyvalue` VALUES (250, 177, 94, 'PLAYBOY/花花公子');
INSERT INTO `t_propertyvalue` VALUES (251, 178, 94, '欧惠家');
INSERT INTO `t_propertyvalue` VALUES (252, 178, 95, '木');

-- ----------------------------
-- Table structure for t_review
-- ----------------------------
DROP TABLE IF EXISTS `t_review`;
CREATE TABLE `t_review`  (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `uid` int(11) DEFAULT NULL COMMENT 't_user-Id',
  `pid` int(11) DEFAULT NULL COMMENT 't_product-Id',
  `createDate` datetime(0) DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`reviewId`) USING BTREE,
  INDEX `fk_review_product`(`pid`) USING BTREE,
  INDEX `fk_review_user`(`uid`) USING BTREE,
  CONSTRAINT `fk_review_product` FOREIGN KEY (`pid`) REFERENCES `t_product` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_review_user` FOREIGN KEY (`uid`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_review
-- ----------------------------
INSERT INTO `t_review` VALUES (1, 'dsafsafd', 1, 129, '2019-04-11 20:26:53');
INSERT INTO `t_review` VALUES (2, '11111', 1, 129, '2019-04-11 20:26:53');
INSERT INTO `t_review` VALUES (3, 'sdfasdfsdfsda', 1, 129, '2019-04-11 20:26:53');
INSERT INTO `t_review` VALUES (4, 'dsafsdafsdafsda', 1, 129, '2019-04-11 20:26:53');
INSERT INTO `t_review` VALUES (5, 'fsadfsdafsdaf', 1, 129, '2019-04-11 20:26:53');
INSERT INTO `t_review` VALUES (6, 'sadfsadfcvxzc x z', 1, 129, '2019-04-11 20:26:53');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '11', '11', '465912173@qq.com');
INSERT INTO `t_user` VALUES (14, '111', '1', '953626691@qq.com');

-- ----------------------------
-- View structure for v_orderdetail
-- ----------------------------
DROP VIEW IF EXISTS `v_orderdetail`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `v_orderdetail` AS select `t_order`.`orderId` AS `orderId`,`t_order`.`orderCode` AS `orderCode`,`t_order`.`address` AS `address`,`t_order`.`post` AS `post`,`t_order`.`receiver` AS `receiver`,`t_order`.`phone` AS `phone`,`t_order`.`userMessage` AS `userMessage`,`t_order`.`createDate` AS `ordercreateDate`,`t_order`.`payDate` AS `payDate`,`t_order`.`deliveryDate` AS `deliveryDate`,`t_order`.`confirmDate` AS `confirmDate`,`t_order`.`status` AS `status`,`t_order`.`deliver` AS `deliver`,`t_orderitem`.`orderitemId` AS `orderitemId`,`t_orderitem`.`number` AS `number`,`t_product`.`productId` AS `productId`,`t_product`.`productName` AS `productName`,`t_product`.`subTitle` AS `subTitle`,`t_product`.`originalPrice` AS `originalPrice`,`t_product`.`promotePrice` AS `promotePrice`,`t_product`.`stock` AS `stock`,`t_user`.`userId` AS `userId`,`t_user`.`userName` AS `userName`,`t_user`.`email` AS `email`,`t_product`.`createDate` AS `productcreateDate` from (((`t_order` join `t_orderitem` on((`t_orderitem`.`oId` = `t_order`.`orderId`))) join `t_product` on((`t_orderitem`.`productId` = `t_product`.`productId`))) join `t_user` on(((`t_order`.`uid` = `t_user`.`userId`) and (`t_orderitem`.`userId` = `t_user`.`userId`))));

SET FOREIGN_KEY_CHECKS = 1;
