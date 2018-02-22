package com.qa.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.inject.Inject;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class DBService {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util)  {
		this.util = util;
	}

	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	public Object findAnAccount(long id){
		return manager.find(Account.class, id);
	}

	@Transactional(REQUIRED)
	public String createAnAccount(String mockObject) {
		Account anAccount = util.getObjectForJSON(mockObject, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account sucessfully created\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateAnAccount(long id, String mockObject) {
		Account updatedAccount = util.getObjectForJSON(mockObject, Account.class);
		Account accountFromDB = (Account) findAnAccount(id);
		if (mockObject != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(long id) {
		Account accountInDB = (Account) findAnAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	

	

}
