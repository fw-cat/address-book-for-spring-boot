ALTER TABLE `address_books`
CHANGE `zip_code` `zip_code` varchar(8) COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT '' COMMENT '郵便番号' AFTER `phone_number`,
CHANGE `prefecture` `prefecture` varchar(16) COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT '' COMMENT '都道府県' AFTER `zip_code`,
CHANGE `city` `city` varchar(128) COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT '' COMMENT '市区町村' AFTER `prefecture`,
CHANGE `address` `address` varchar(128) COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT '' COMMENT '番地' AFTER `city`;

ALTER TABLE `address_books`
CHANGE `last_name` `last_name` varchar(128) COLLATE 'utf8mb4_general_ci' NOT NULL COMMENT '名前（姓）' AFTER `id`,
CHANGE `first_name` `first_name` varchar(128) COLLATE 'utf8mb4_general_ci' NOT NULL COMMENT '名前（名）' AFTER `last_name`,

CHANGE `mail_address` `mail_address` varchar(2048) COLLATE 'utf8mb4_general_ci' NOT NULL COMMENT 'メールアドレス',
CHANGE `phone_number` `phone_number` varchar(16) COLLATE 'utf8mb4_general_ci' NOT NULL COMMENT '電話番号',

CHANGE `zip_code` `zip_code` varchar(8) COLLATE 'utf8mb4_general_ci' NULL COMMENT '郵便番号',
CHANGE `prefecture` `prefecture` varchar(16) COLLATE 'utf8mb4_general_ci' NULL COMMENT '都道府県',
CHANGE `city` `city` varchar(128) COLLATE 'utf8mb4_general_ci' NULL COMMENT '市区町村',
CHANGE `address` `address` varchar(128) COLLATE 'utf8mb4_general_ci' NULL COMMENT '番地',
CHANGE `building` `building` varchar(512) COLLATE 'utf8mb4_general_ci' NULL COMMENT 'ビル',

COLLATE 'utf8mb4_general_ci';

ALTER TABLE `address_books`
ADD `deleted_at` datetime NULL DEFAULT NULL COMMENT '削除日時' AFTER `building`,
CHANGE `updated_at` `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時' AFTER `deleted_at`,
CHANGE `created_at` `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時' AFTER `updated_at`;