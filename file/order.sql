/*
 Navicat Premium Data Transfer

 Source Server         : Gree-local
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : a_test

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/07/2021 17:09:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 205 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (195, '2c96da52-871e-4157-91e2-4b262205bc95', 1);
INSERT INTO `order` VALUES (196, '6b2f4c82-d349-4bc6-915d-e4ffe640fa03', 1);
INSERT INTO `order` VALUES (197, 'fc398639-abc8-47dd-a8aa-d10254666679', 1);
INSERT INTO `order` VALUES (198, '03fdc284-3cd9-4ad3-b86d-756d4e5088c1', 1);
INSERT INTO `order` VALUES (199, 'a89092c1-9198-41b2-90fe-12e15a726f9f', 1);
INSERT INTO `order` VALUES (200, '1e720b58-b8a0-49fd-966b-f2d1e716315a', 1);
INSERT INTO `order` VALUES (201, '0bc99299-dba4-4e1c-9bb1-db9a98eec725', 1);
INSERT INTO `order` VALUES (202, 'b148db4e-46b9-4d10-85ac-172dab301a65', 1);
INSERT INTO `order` VALUES (203, '28015921-45e3-4a67-8af3-140792621f79', 1);
INSERT INTO `order` VALUES (204, 'c54400e8-c97c-4d7e-8edd-c71f04fcb9ac', 1);

-- ----------------------------
-- Table structure for orm_role
-- ----------------------------
DROP TABLE IF EXISTS `orm_role`;
CREATE TABLE `orm_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Spring Boot Demo Orm 系列示例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orm_role
-- ----------------------------
INSERT INTO `orm_role` VALUES (2, '普通用户');
INSERT INTO `orm_role` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for orm_user
-- ----------------------------
DROP TABLE IF EXISTS `orm_user`;
CREATE TABLE `orm_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密使用的盐',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `phone_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `last_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Spring Boot Demo Orm 系列示例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orm_user
-- ----------------------------
INSERT INTO `orm_user` VALUES (1, 'user_1', 'ff342e862e7c3285cdc07e56d6b8973b', '412365a109674b2dbb1981ed561a4c70', 'user1@xkcoding.com', '17300000001', 1, '2021-03-18 20:27:06', NULL, '2021-03-18 20:27:06');
INSERT INTO `orm_user` VALUES (2, 'user_2', '6c6bf02c8d5d3d128f34b1700cb1e32c', 'fcbdd0e8a9404a5585ea4e01d0e4d7a0', 'user2@xkcoding.com', '17300000002', 1, '2021-03-18 20:27:06', NULL, '2021-03-18 20:27:06');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stock` int(255) NULL DEFAULT NULL,
  `version` tinyint(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '苹果手机', 0, 0);

-- ----------------------------
-- Table structure for test_course
-- ----------------------------
DROP TABLE IF EXISTS `test_course`;
CREATE TABLE `test_course`  (
  `id` int(11) NOT NULL COMMENT '课程编号',
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_course
-- ----------------------------
INSERT INTO `test_course` VALUES (1, '语文', 2);
INSERT INTO `test_course` VALUES (2, '数学', 1);
INSERT INTO `test_course` VALUES (3, '英语', 3);

-- ----------------------------
-- Table structure for test_score
-- ----------------------------
DROP TABLE IF EXISTS `test_score`;
CREATE TABLE `test_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认主键',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生编号',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程编号',
  `score` int(255) NULL DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_score
-- ----------------------------
INSERT INTO `test_score` VALUES (2, 1, 1, 80);
INSERT INTO `test_score` VALUES (3, 1, 2, 90);
INSERT INTO `test_score` VALUES (4, 1, 3, 99);
INSERT INTO `test_score` VALUES (5, 2, 1, 70);
INSERT INTO `test_score` VALUES (6, 2, 2, 60);
INSERT INTO `test_score` VALUES (7, 2, 3, 80);
INSERT INTO `test_score` VALUES (8, 3, 1, 80);
INSERT INTO `test_score` VALUES (9, 3, 2, 80);
INSERT INTO `test_score` VALUES (10, 3, 3, 80);
INSERT INTO `test_score` VALUES (11, 4, 1, 50);
INSERT INTO `test_score` VALUES (12, 4, 2, 30);
INSERT INTO `test_score` VALUES (13, 4, 3, 20);
INSERT INTO `test_score` VALUES (14, 5, 1, 76);
INSERT INTO `test_score` VALUES (15, 5, 2, 87);
INSERT INTO `test_score` VALUES (16, 6, 1, 31);
INSERT INTO `test_score` VALUES (17, 6, 3, 34);
INSERT INTO `test_score` VALUES (18, 7, 2, 89);
INSERT INTO `test_score` VALUES (19, 7, 3, 98);

-- ----------------------------
-- Table structure for test_student
-- ----------------------------
DROP TABLE IF EXISTS `test_student`;
CREATE TABLE `test_student`  (
  `id` int(11) NOT NULL COMMENT '学生编号',
  `sname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `sage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出生年月',
  `ssex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_student
-- ----------------------------
INSERT INTO `test_student` VALUES (1, '赵雷', '1990-01-01', '男');
INSERT INTO `test_student` VALUES (2, '钱电', '1990-12-21', '男');
INSERT INTO `test_student` VALUES (3, '孙风', '1990-05-20', '男');
INSERT INTO `test_student` VALUES (4, '李云', '1990-08-06', '男');
INSERT INTO `test_student` VALUES (5, '周梅', '1991-12-01', '女');
INSERT INTO `test_student` VALUES (6, '吴兰', '1992-03-01', '女');
INSERT INTO `test_student` VALUES (7, '郑竹', '1989-07-01', '女');
INSERT INTO `test_student` VALUES (8, '王菊', '1990-01-20', '女');

-- ----------------------------
-- Table structure for test_teacher
-- ----------------------------
DROP TABLE IF EXISTS `test_teacher`;
CREATE TABLE `test_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师编号',
  `tname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_teacher
-- ----------------------------
INSERT INTO `test_teacher` VALUES (1, '张三');
INSERT INTO `test_teacher` VALUES (2, '李四');
INSERT INTO `test_teacher` VALUES (3, '王五');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1111', 18, 'test1@baomidou.com');
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@baomidou.com');
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com');
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com');
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com');
INSERT INTO `user` VALUES (6, 'AB', 50, 'aa');
INSERT INTO `user` VALUES (7, '事务插入测试人员20210319164032', 1, 'qq.com');

SET FOREIGN_KEY_CHECKS = 1;
