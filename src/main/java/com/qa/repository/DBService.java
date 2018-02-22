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
public class DBService implements DBServiceInterface {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	
	@Override
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	
	@Override
	public void setUtil(JSONUtil util)  {
		this.util = util;
	}

	
	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	
	@Override
	public String findAnAccount(Long id){
		return util.getJSONForObject(manager.find(Account.class, id));
	}

	
	@Override
	@Transactional(REQUIRED)
	public String createAnAccount(String mockObject) {
		Account anAccount = util.getObjectForJSON(mockObject, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account sucessfully created\"}";
	}
	
	
	@Override
	@Transactional(REQUIRED)
	public String updateAnAccount(long id, String mockObject) {
		Account updatedAccount = util.getObjectForJSON(mockObject, Account.class);
		Account accountFromDB = util.getObjectForJSON(findAnAccount(id), Account.class);
		if (mockObject != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}

	
	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(long id) {
		Account accountInDB = util.getObjectForJSON(findAnAccount(id), Account.class);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	

	

}
