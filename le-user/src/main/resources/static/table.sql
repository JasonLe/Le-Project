DROP DATABASE IF EXISTS le_data;

CREATE DATABASE le_data;

USE le_data;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(30) NOT NULL COMMENT '姓名',
    `password` varchar(255) NOT NULL COMMENT '密码',
    `mobile` int(11) NULL DEFAULT NULL COMMENT '电话',
    `location` varchar(50) NULL DEFAULT NULL COMMENT '地址',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1;