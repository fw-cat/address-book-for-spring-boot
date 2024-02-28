ALTER TABLE `address_books`
ADD COLUMN `birth_dt` DATE DEFAULT NULL COMMENT '誕生日' AFTER building;
