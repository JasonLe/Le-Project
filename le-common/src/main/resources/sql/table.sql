DROP DATABASE IF EXISTS le-project;

CREATE DATABASE le-project;

USE le-project;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(30) NOT NULL COMMENT '姓名',
    `password` varchar(255) NOT NULL COMMENT '密码',
    `mobile` varchar(11) DEFAULT NULL COMMENT '电话',
    `email` varchar(50) NOT NULL COMMENT '邮箱',
    `location` varchar(50) DEFAULT NULL COMMENT '地址',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UX_user` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客id',
    `title` varchar(255) NOT NULL COMMENT '博客标题',
    `digest` varchar(255) DEFAULT NULL COMMENT '博客摘要',
    `content` text COMMENT '博客内容',
    `status` int(10) NOT NULL COMMENT '博客状态',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `IDX_title` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;