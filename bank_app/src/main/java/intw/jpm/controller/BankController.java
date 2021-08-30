package intw.jpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import intw.jpm.model.BankAccount;
import intw.jpm.model.BankAccountResponse;
import intw.jpm.service.BankService;


//@CrossOrigin(origins = "http://localhost:4040")
@RestController
@RequestMapping("/api")
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping("/bankaccount")
	public ResponseEntity<BankAccountResponse> validateBankAccount(@RequestBody BankAccount bankAccount,  @RequestHeader HttpHeaders headers) {
		try {
			BankAccountResponse responseObject = bankService.validateBankAccount(headers, bankAccount);
			return new ResponseEntity<>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
