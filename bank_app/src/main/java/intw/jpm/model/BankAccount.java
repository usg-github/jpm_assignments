package intw.jpm.model;

import java.util.List;

import javax.validation.constraints.NotBlank;


public class BankAccount {

	@NotBlank(message = "{Account Number is not defined}")
	private String accountNumber;

	private List<String> providers;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<String> getProviders() {
		return providers;
	}

	public void setProviders(List<String> providers) {
		this.providers = providers;
	}



}
