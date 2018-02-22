package com.qa.repository;

import javax.persistence.EntityManager;
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