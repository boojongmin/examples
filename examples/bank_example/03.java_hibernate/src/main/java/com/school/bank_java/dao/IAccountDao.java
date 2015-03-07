package com.school.bank_java.dao;

import java.util.List;

import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;

public interface IAccountDao {

	public abstract AccountVo selectAccountVoByUid(int uid);

	public abstract List<AccountDetailVo> selectAcocuntDetailByUid(int uid);

	public abstract int insertAccountDetail(AccountDetailVo vo);

	public abstract AccountDetailVo selectAccountDetailByUid(int uid);

	public abstract List<AccountDetailVo> selectAcocuntDetailListByAccountUid(
			int accountUid);

	public abstract int insertAccount(AccountVo vo);

	public abstract int selectAccountCountByUserUid(int useruid);

	public abstract List<AccountVo> selectAccountList(int user_uid);

	public abstract int updateAccount(AccountVo accountVo);

	public abstract AccountVo selectAccountVoByAccountNumber(
			String accountNumber);

}