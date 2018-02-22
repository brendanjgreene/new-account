package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.qa.util.JSONUtil;

public interface DBServiceInterface {

	void setManager(EntityManager manager);

	void setUtil(JSONUtil util);

	String getAllAccounts();

	String findAnAccount(Long id);

	String createAnAccount(String mockObject);

	String updateAnAccount(long id, String mockObject);

	String deleteAccount(long id);

}