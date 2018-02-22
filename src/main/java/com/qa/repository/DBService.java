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

	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#setManager(javax.persistence.EntityManager)
	 */
	@Override
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#setUtil(com.qa.util.JSONUtil)
	 */
	@Override
	public void setUtil(JSONUtil util)  {
		this.util = util;
	}

	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#getAllAccounts()
	 */
	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#findAnAccount(long)
	 */
	@Override
	public String findAnAccount(Long id){
		return util.getJSONForObject(manager.find(Account.class, id));
	}

	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#createAnAccount(java.lang.String)
	 */
	@Override
	@Transactional(REQUIRED)
	public String createAnAccount(String mockObject) {
		Account anAccount = util.getObjectForJSON(mockObject, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account sucessfully created\"}";
	}
	
	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#updateAnAccount(long, java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.qa.repository.DBServiceInterface#deleteAccount(long)
	 */
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
