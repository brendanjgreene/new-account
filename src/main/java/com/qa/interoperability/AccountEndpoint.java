package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.repository.DBService;
import com.qa.repository.ServiceInterface;

@Path("/account")
public class AccountEndpoint {
	
	private static final Logger LOGGER = Logger.getLogger(AccountEndpoint.class);
	
	@Inject
	private ServiceInterface repo;
	
	@Path("/json")
	@GET
	@Produces({ "application/json"})
	public String getAllAccounts() {
		LOGGER.info("in AccountEndpoint getAllAccounts");
		return repo.getAllAccounts();
	};
	
	@Path("/json/{id}")
	@GET
	@Produces({ "application/json"})
	public String getAnAccount(@PathParam("id") Long id) {
		LOGGER.info("in AccountEndpoint getAnAccounts");
		return repo.findAnAccount(id);
	};
	
	@Path("/json")
	@POST
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String addAccount(String account) {
		LOGGER.info("in AccountEndpoint addAccounts");
		return repo.createAnAccount(account);
	};
	
	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		LOGGER.info("in AccountEndpoint updateAccounts");
		return repo.updateAnAccount(id, account);
		
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String deleteAnAccount(@PathParam("id") Long id) {
		LOGGER.info("in AccountEndpoint deleteAnAccount");
		return repo.deleteAccount(id);
	}

}
