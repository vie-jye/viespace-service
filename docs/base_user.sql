CREATE TABLE `base_user` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
    `create_user` bigint unsigned DEFAULT '0' COMMENT '创建者',
    `update_time` bigint unsigned DEFAULT '0' COMMENT '更新时间',
    `update_user` bigint unsigned DEFAULT '0' COMMENT '更新者',
    `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：1.是 0.否',
    `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
    `session` varchar(255) DEFAULT NULL COMMENT '会话信息',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户';
