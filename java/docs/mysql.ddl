DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS groups;

/**********************************/
/* テーブル名: グループ */
/**********************************/
CREATE TABLE groups(
		ID                            		BIGINT		 NOT NULL IDENTITY,
		group_name                    		VARCHAR(256)		 NOT NULL,
		updated_at                    		TIMESTAMP		 NULL ,
		created_at                    		TIMESTAMP		 NULL ,
		deleted_at                    		TIMESTAMP		 NULL 
)
go

/**********************************/
/* テーブル名: アドレス帳 */
/**********************************/
CREATE TABLE addresses(
		ID                            		BIGINT		 NOT NULL IDENTITY,
		last_name                     		VARCHAR(256)		 NOT NULL,
		first_name                    		VARCHAR(256)		 NOT NULL,
		mail_address                  		VARCHAR(512)		 NOT NULL,
		phone_number                  		VARCHAR(16)		 NOT NULL,
		group_id                      		BIGINT		 NULL ,
		updated_at                    		TIMESTAMP		 NULL ,
		created_at                    		TIMESTAMP		 NULL ,
		deleted_at                    		TIMESTAMP		 NULL 
)
go


ALTER TABLE groups ADD CONSTRAINT IDX_groups_PK PRIMARY KEY (ID)
go

ALTER TABLE addresses ADD CONSTRAINT IDX_addresses_PK PRIMARY KEY (ID)
go
ALTER TABLE addresses ADD CONSTRAINT IDX_addresses_FK0 FOREIGN KEY (group_id) REFERENCES groups (ID)
go

