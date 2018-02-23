package com.qa.business;

import com.qa.domain.Account;

public class BusinessLogic {
	
	public boolean deny9999(Account account) {
		boolean ans;
		if (account.getAccountNumber().equals("9999")) {
			ans = false;

		}else {
			ans = true;
		}
		return ans;
	}

}
