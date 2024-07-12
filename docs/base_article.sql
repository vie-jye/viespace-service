CREATE TABLE `base_article` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
    `create_time` bigint unsigned DEFAULT '0' COMMENT '创建时间',
    `create_user` bigint unsigned DEFAULT '0' COMMENT '创建者',
    `update_time` bigint unsigned DEFAULT '0' COMMENT '更新时间',
    `update_user` bigint unsigned DEFAULT '0' COMMENT '更新者',
    `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：1.是 0.否',
    `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
    `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
    `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
    `article_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章类型',
    `tags` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签',
    `category_id` bigint unsigned NOT NULL COMMENT '分类id',
    `category` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
    `views` bigint unsigned NOT NULL DEFAULT '0' COMMENT '浏览次数',
    `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
    `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态：0.上线 1.下线',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章';
