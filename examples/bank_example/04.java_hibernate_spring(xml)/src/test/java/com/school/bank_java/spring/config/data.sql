insert into user(userId, userName)
values('boojongmin', '부종민');
insert into account(userUid, accountNumber, amount)
values(1, '0000100001', 1000);
insert into accountDetail(accountUid, type, amount)
values(1, 0, 1000),
(1, 1, -9000);