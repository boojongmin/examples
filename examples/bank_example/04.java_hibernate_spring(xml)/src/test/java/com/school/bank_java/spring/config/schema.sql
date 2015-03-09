drop table if exists accountDetail;
drop table if exists account;
drop table if exists user;

create table user(
  uid int primary key auto_increment,
  userId varchar(25) not null,
  userName varchar(255) not null,
  createDt datetime default current_timestamp
)
;
create unique index idx_unique_user_userId on user(userid)
;
create table account (
  uid int primary key auto_increment,
  userUid int,
  accountNumber varchar(255) not null,
  amount int default 0,
  createDt datetime default current_timestamp
)
;
alter table account add constraint fk_account_user_uid foreign key(userUid) references user(uid)
;
create table accountDetail(
  uid int primary key auto_increment,
  accountUid int,
  type tinyint,
  amount int default 0,
  createDt datetime default current_timestamp
);
alter table accountDetail add constraint fk_accountDetail_accountUid foreign key(accountUid) references account(uid)
;
insert into user(userId, userName)
values('boojongmin', '부종민');
insert into account(userUid, accountNumber, amount)
values(1, '0000100001', 1000);
insert into accountDetail(accountUid, type, amount)
values(1, 0, 1000),
(1, 1, -9000);