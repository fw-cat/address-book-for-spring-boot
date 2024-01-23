ALTER TABLE `address_books`
CHANGE `zip_code` `zip_code` varchar(8) COLLATE 'utf8mb4_0900_ai_ci' NULL COMMENT '郵便番号' AFTER `phone_number`,
CHANGE `prefecture` `prefecture` varchar(16) COLLATE 'utf8mb4_0900_ai_ci' NULL COMMENT '都道府県' AFTER `zip_code`,
CHANGE `city` `city` varchar(128) COLLATE 'utf8mb4_0900_ai_ci' NULL COMMENT '市区町村' AFTER `prefecture`,
CHANGE `address` `address` varchar(128) COLLATE 'utf8mb4_0900_ai_ci' NULL COMMENT '番地' AFTER `city`;
