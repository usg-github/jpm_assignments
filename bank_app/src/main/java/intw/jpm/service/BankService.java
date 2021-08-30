package intw.jpm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import intw.jpm.dataprovider.AbstractExternalService;
import intw.jpm.model.AccountDetails;
import intw.jpm.model.BankAccount;
import intw.jpm.model.BankAccountResponse;
import intw.jpm.utils.JsonUtils;
import reactor.core.publisher.Mono;

@Service
public class BankService {

	public BankAccountResponse validateBankAccount( HttpHeaders headers, BankAccount bankAccount) {
		BankAccountResponse bankAccountResponse = new BankAccountResponse();
		if (bankAccount != null && !CollectionUtils.isEmpty(bankAccount.getProviders())) {
			Map<String, AbstractExternalService> externalServicesMap = AbstractExternalService.getExternalServices();
			AccountDetails accountDetails = new AccountDetails();
			accountDetails.setAccountNumber(bankAccount.getAccountNumber());
			List<BankAccountResponse.Result> resultList = new ArrayList<>(0);
			for (String provider : bankAccount.getProviders()) {
				AbstractExternalService abstractExternalService = externalServicesMap.get(provider);
				BankAccountResponse.Result result = bankAccountResponse.new Result();
				result.setProvider(provider);
				try {
					Mono<ResponseEntity<Boolean>> validAccountNumber = abstractExternalService.call("/api/validateBankAccount", "",
							HttpMethod.POST, headers, JsonUtils.toJSON(accountDetails));
					ResponseEntity<Boolean> response = validAccountNumber.block();
					result.setIsValid(response.getBody());
				} catch (Exception e) {
					result.setIsValid(Boolean.FALSE);
				}
				resultList.add(result);
			}
			bankAccountResponse.setResult(resultList);
		}

		return bankAccountResponse;
	}
}
