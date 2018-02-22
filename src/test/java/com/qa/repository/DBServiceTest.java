package com.qa.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.repository.DBService;
import com.qa.util.JSONUtil;

import junit.framework.Assert;



@RunWith(MockitoJUnitRunner.class)
public class DBServiceTest {

	@InjectMocks
	private DBService endpoint;
	
	@Mock
	private EntityManager manager;
	
	@Mock
	private Query query;
	
	private JSONUtil util;
	
	private static final String MOCK_ARRAY =  "[{\"firstName\":\"Brendan\",\"secondName\":\"Greene\",\"accountNumber\":\"1234\"}]";
	
	private static final String MOCK_OBJECT =  "{\"firstName\":\"Brendan\",\"secondName\":\"Greene\",\"accountNumber\":\"1234\"}";

	
	@Before
	public void setUp() {
		endpoint.setManager(manager);
		util = new JSONUtil();
		endpoint.setUtil(util);
		
	}
	
	@Test
	public void testGetAllAccounts() {
		
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("Brendan", "Greene", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(MOCK_ARRAY, endpoint.getAllAccounts());
	}
	
	
	
	@Test
	public void testCreateAnAccount() {
		String reply = endpoint.createAnAccount(MOCK_OBJECT);
		assertEquals(reply, "{\"message\": \"account sucessfully created\"}");
		
	}
	
	@Test
	public void testUpdateAnAccount() {
		String reply = endpoint.updateAnAccount(1L, MOCK_OBJECT);
		assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
		
	}
	
	@Test
	public void testDeleteAccount() {
		String reply = endpoint.deleteAccount(1L);
		assertEquals(reply, "{\"message\": \"account sucessfully deleted\"}");
		
	}

}
