SET FOREIGN_KEY_CHECKS = 0;
truncate table ACCOUNTDETAIL;
truncate table ACCOUNT;
truncate table USER;
SET FOREIGN_KEY_CHECKS = 1;
--h2전용
ALTER TABLE ACCOUNTDETAIL ALTER COLUMN id RESTART WITH 1;
ALTER TABLE ACCOUNT ALTER COLUMN id RESTART WITH 1;
ALTER TABLE USER ALTER COLUMN id RESTART WITH 1;

--insert into User(userId, name) values('boojongmin', '부종민');
insert into User(userId, password, name, enabled) values('boojongmin', '81dc9bdb52d04dc20036dbd8313ed055',  '부종민', 1);
insert into Account(user_id, accountNumber, amount) values (1, '0000100001', 0);
insert into AccountDetail(type, amount, account_id) values (0, 10000, 1),(1, -9000, 1);
update Account set amount = 1000 where id = 1 ;

--insert into UserRole
insert into UserRole(user_id, role) values(1, 'ROLE_ADMIN');
insert into UserRole(user_id, role) values(1, 'ROLE_USER');