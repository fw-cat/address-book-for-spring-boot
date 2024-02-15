DROP TABLE IF EXISTS `address_books`;
CREATE TABLE IF NOT EXISTS `address_books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
 
  `group_id` int(11) NOT NULL ,
 
  `last_name` varchar(128) NOT NULL COMMENT '名前（姓）',
  `first_name` varchar(128) NOT NULL COMMENT '名前（名）',

  `mail_address` varchar(2048) NOT NULL COMMENT 'メールアドレス',
  `phone_number` varchar(16) NOT NULL COMMENT '電話番号',

  `zip_code` varchar(8) NOT NULL COMMENT '郵便番号',
  `prefecture` varchar(16) NOT NULL COMMENT '都道府県',
  `city` varchar(128) NOT NULL COMMENT '市区町村',
  `address` varchar(128) NOT NULL COMMENT '番地',
  `building` varchar(512) NULL COMMENT 'ビル',

  `deleted_at` datetime DEFAULT NULL COMMENT '削除日時',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (`id`),
  FOREIGN KEY (group_id) REFERENCES address_groups(id) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE 'utf8mb4_general_ci';