package com.school.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.school.domain.AccountDetail;

public interface AccountDetailRepository extends CrudRepository<AccountDetail, Long> {
	 @Modifying
	  @Transactional
	  @Query("delete from AccountDetail")
	  void deleteInactiveUsers();

}
