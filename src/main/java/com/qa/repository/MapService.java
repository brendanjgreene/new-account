package com.qa.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class MapService implements ServiceInterface{
	
	private static final Logger LOGGER = Logger.getLogger(MapService.class);
	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Account> accountMap;
	private Long ID;
	
	@Inject
	private JSONUtil util;
	
	public MapService() {
		this.accountMap = new HashMap<Long, Account>();
		ID = INITIAL_COUNT;
		initAccountMap();
	}

	private void initAccountMap() {
		Account account = new Account("Brendan", "Greene", "09876");
		accountMap.put(1L, account);
		
	}


	@Override
	public void setUtil(JSONUtil util) {
		this.util = util;
		
	}

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accountMap.values());
	}

	@Override
	public String findAnAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createAnAccount(String mockObject) {
		ID++;
		Account newAccount = util.getObjectForJSON(mockObject, Account.class);
		accountMap.put(ID, newAccount);
		return mockObject;
	}

	@Override
	public String updateAnAccount(long id, String mockObject) {
		Account newAccount = util.getObjectForJSON(mockObject, Account.class);
		accountMap.put(id, newAccount);
		return mockObject;
	}

	@Override
	public String deleteAccount(long id) {
		accountMap.remove(id);
		return "{\"message\"; \"account deleted\"]";
	}

	@Override
	public void setManager(EntityManager manager) {
		
		
	}

}
