/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : cookiez

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 15/09/2020 09:11:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(0) NOT NULL,
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `comment_content` varchar(2550) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recipe_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `fk_comment_user_1`(`user_id`) USING BTREE,
  INDEX `fk_comment_recipe_1`(`recipe_id`) USING BTREE,
  CONSTRAINT `fk_comment_recipe_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, '11', NULL);

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `favorite_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `recipe_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`favorite_id`) USING BTREE,
  INDEX `fk_favorite_recipe_1`(`recipe_id`) USING BTREE,
  INDEX `fk_favorite_user_1`(`user_id`) USING BTREE,
  CONSTRAINT `fk_favorite_recipe_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_favorite_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img`  (
  `img_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img
-- ----------------------------

-- ----------------------------
-- Table structure for ingredient
-- ----------------------------
DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient`  (
  `ingredient_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ingredient_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ingredient
-- ----------------------------

-- ----------------------------
-- Table structure for ingredient_recipe_bridge
-- ----------------------------
DROP TABLE IF EXISTS `ingredient_recipe_bridge`;
CREATE TABLE `ingredient_recipe_bridge`  (
  `ingredient_recipe_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ingredient_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `recipe_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`ingredient_recipe_id`) USING BTREE,
  INDEX `fk_ingredient_recipe_bridge_ingredient_1`(`ingredient_id`) USING BTREE,
  INDEX `fk_ingredient_recipe_bridge_recipe_1`(`recipe_id`) USING BTREE,
  CONSTRAINT `fk_ingredient_recipe_bridge_ingredient_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ingredient_recipe_bridge_recipe_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ingredient_recipe_bridge
-- ----------------------------

-- ----------------------------
-- Table structure for ingredient_tag_bridge
-- ----------------------------
DROP TABLE IF EXISTS `ingredient_tag_bridge`;
CREATE TABLE `ingredient_tag_bridge`  (
  `ingredient_tag_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ingredient_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `tag_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`ingredient_tag_id`) USING BTREE,
  INDEX `fk_ingredient_tag_bridge_ingredient_1`(`ingredient_id`) USING BTREE,
  INDEX `fk_ingredient_tag_bridge_tag_1`(`tag_id`) USING BTREE,
  CONSTRAINT `fk_ingredient_tag_bridge_ingredient_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ingredient_tag_bridge_tag_1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ingredient_tag_bridge
-- ----------------------------

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like`  (
  `like_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `like_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `status` int(0) NULL DEFAULT NULL,
  `recipe_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `fk_like_user_1`(`user_id`) USING BTREE,
  INDEX `fk_like_recipe_1`(`recipe_id`) USING BTREE,
  CONSTRAINT `fk_like_recipe_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like
-- ----------------------------

-- ----------------------------
-- Table structure for no_eat
-- ----------------------------
DROP TABLE IF EXISTS `no_eat`;
CREATE TABLE `no_eat`  (
  `ingredient_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`) USING BTREE,
  INDEX `fk_no_eat_user_1`(`user_id`) USING BTREE,
  CONSTRAINT `fk_no_eat_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of no_eat
-- ----------------------------

-- ----------------------------
-- Table structure for pantry
-- ----------------------------
DROP TABLE IF EXISTS `pantry`;
CREATE TABLE `pantry`  (
  `pantry_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `ingredient_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`pantry_id`) USING BTREE,
  INDEX `fk_pantry_user_1`(`user_id`) USING BTREE,
  INDEX `fk_pantry_ingredient_1`(`ingredient_id`) USING BTREE,
  CONSTRAINT `fk_pantry_ingredient_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`ingredient_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pantry_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pantry
-- ----------------------------

-- ----------------------------
-- Table structure for recipe
-- ----------------------------
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe`  (
  `recipe_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recipe_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recipe_like` int(0) NULL DEFAULT NULL,
  `recipe_created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`recipe_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recipe
-- ----------------------------

-- ----------------------------
-- Table structure for recipe_tag_bridge
-- ----------------------------
DROP TABLE IF EXISTS `recipe_tag_bridge`;
CREATE TABLE `recipe_tag_bridge`  (
  `recipe_tag_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `recipe_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`recipe_tag_id`) USING BTREE,
  INDEX `fk_recipe_tag_bridge_tag_1`(`tag_id`) USING BTREE,
  INDEX `fk_recipe_tag_bridge_recipe_1`(`recipe_id`) USING BTREE,
  CONSTRAINT `fk_recipe_tag_bridge_recipe_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_recipe_tag_bridge_tag_1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recipe_tag_bridge
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for step
-- ----------------------------
DROP TABLE IF EXISTS `step`;
CREATE TABLE `step`  (
  `step_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `step_order` int(0) NULL DEFAULT NULL,
  `img_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `recipe_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `step_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`step_id`) USING BTREE,
  INDEX `fk_step_img_1`(`img_id`) USING BTREE,
  INDEX `fk_step_recipe_1`(`recipe_id`) USING BTREE,
  CONSTRAINT `fk_step_img_1` FOREIGN KEY (`img_id`) REFERENCES `img` (`img_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_step_recipe_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of step
-- ----------------------------

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tag_type_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE,
  INDEX `fk_tag_tag_type_1`(`tag_type_id`) USING BTREE,
  CONSTRAINT `fk_tag_tag_type_1` FOREIGN KEY (`tag_type_id`) REFERENCES `tag_type` (`tag_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for tag_type
-- ----------------------------
DROP TABLE IF EXISTS `tag_type`;
CREATE TABLE `tag_type`  (
  `tag_type_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tag_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag_type
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `avatar_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `fk_user_role_1`(`role_id`) USING BTREE,
  INDEX `fk_user_img_1`(`avatar_id`) USING BTREE,
  CONSTRAINT `fk_user_img_1` FOREIGN KEY (`avatar_id`) REFERENCES `img` (`img_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', '123', NULL, NULL);
INSERT INTO `user` VALUES (2, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
