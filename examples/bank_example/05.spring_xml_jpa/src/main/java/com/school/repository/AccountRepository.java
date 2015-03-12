package com.school.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.school.vo.Account;
import com.school.vo.User;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findByAccountNumber(String accountNumber);
	Long countByUser(User user);
	Set<Account> findByUserId(Long id);

}
