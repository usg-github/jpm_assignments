package intw.jpm.model;

import javax.validation.constraints.NotBlank;


public class AccountDetails {

	@NotBlank(message = "{Account Number is not defined}")
	private String accountNumber;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}




}
