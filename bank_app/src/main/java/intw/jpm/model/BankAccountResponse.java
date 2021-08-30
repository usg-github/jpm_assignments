package intw.jpm.model;

import java.util.List;

public class BankAccountResponse {

	private List<Result> result;

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public class Result{

		private String provider;

		private Boolean isValid;

		public String getProvider() {
			return provider;
		}

		public void setProvider(String provider) {
			this.provider = provider;
		}

		public Boolean getIsValid() {
			return isValid;
		}

		public void setIsValid(Boolean isValid) {
			this.isValid = isValid;
		}

	}

}