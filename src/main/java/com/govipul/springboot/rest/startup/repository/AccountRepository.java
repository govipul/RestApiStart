package com.govipul.springboot.rest.startup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govipul.springboot.rest.startup.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUserName(String userName);
}
