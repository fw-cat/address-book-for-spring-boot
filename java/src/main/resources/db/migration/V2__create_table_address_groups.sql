DROP TABLE IF EXISTS `address_groups`;
CREATE TABLE IF NOT EXISTS `address_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,

  `group_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,

  `deleted_at` datetime DEFAULT NULL COMMENT '削除日時',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) CHARACTER SET utf8mb4 COLLATE 'utf8mb4_general_ci';
