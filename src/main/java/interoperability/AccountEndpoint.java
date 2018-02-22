package interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.domain.Account;
import com.qa.repository.DBService;
import com.qa.util.JSONUtil;

@Path("/account")
public class AccountEndpoint {
	
	@Inject
	private DBService repo;
	
	@Path("/json")
	@GET
	@Produces({ "application/json"})
	public String getAllAccounts() {
		return repo.getAllAccounts();
	};
	
	@Path("/json/{id}")
	@GET
	@Produces({ "application/json"})
	public String getAnAccount(@PathParam("id") Long id) {
		return repo.findAnAccount(id);
	};
	
	@Path("/json")
	@POST
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String addAccount(String account) {
		return repo.createAnAccount(account);
	};
	
	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return repo.updateAnAccount(id, account);
		
	}
	
	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String deleteAnAccount(@PathParam("id") Long id) {
		return repo.deleteAccount(id);
	}

}
