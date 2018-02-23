package com.qa.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.qa.business.BusinessLogic;
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
	
	@Inject
	private BusinessLogic bizz;
	
	
	
	public MapService() {
		LOGGER.info("in MapService Mapservice constructor");
		this.accountMap = new HashMap<Long, Account>();
		ID = INITIAL_COUNT;
		initAccountMap();
	}

	private void initAccountMap() {
		LOGGER.info("in MapService initAccountMap");
		Account account = new Account("Brendan", "Greene", "9876");
		accountMap.put(1L, account);
		
	}


	@Override
	public void setUtil(JSONUtil util) {
		LOGGER.info("in MapService setUtil util = "+util);
		this.util = util;
		
	}

	@Override
	public String getAllAccounts() {
		LOGGER.info("in MapService getAllAccounts");
		return util.getJSONForObject(accountMap.values());
	}

	@Override
	public String findAnAccount(Long id) {
		LOGGER.info("in MapService findAnAccount");
		return util.getJSONForObject(accountMap.get(id));
	}

	@Override
	public String createAnAccount(String mockObject) {
		String reply;
		LOGGER.info("in MapService createAnAccount");
		ID++;
		LOGGER.info("in MapService createAnAccount increment ID");
		Account newAccount = util.getObjectForJSON(mockObject, Account.class);
		LOGGER.info("in MapService createAnAccount initialize Account Object");
		
		if (bizz.deny9999(newAccount)==false) {
			reply = "{\"message\": \"This account is blocked\"}";

		}else{
			accountMap.put(ID, newAccount);
			reply = "{\"message\": \"account sucessfully created\"}";
		}
		return reply;
	}

	@Override
	public String updateAnAccount(long id, String mockObject) {
		Account newAccount = util.getObjectForJSON(mockObject, Account.class);
		accountMap.put(id, newAccount);
		return "{\"message\": \"account updated\"} "+mockObject;
	}

	@Override
	public String deleteAccount(long id) {
		accountMap.remove(id);
		return "{\"message\": \"account deleted\"}";
	}

	@Override
	public void setManager(EntityManager manager) {
		
		
	}
	

}
