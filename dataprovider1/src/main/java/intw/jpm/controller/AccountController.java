package intw.jpm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import intw.jpm.model.AccountDetails;

@CrossOrigin(origins = "http://localhost:4040")
@RestController
@RequestMapping("/api")
public class AccountController {

	@PostMapping("/validateBankAccount")
	public ResponseEntity<Boolean> validateBankAccount(@RequestBody AccountDetails accountDetails) {
		if(accountDetails != null && accountDetails.getAccountNumber() != null) {
			Boolean isAccountFound = List.of("98435544","12345678","08435544").stream()
					  .anyMatch(str -> str.equals(accountDetails.getAccountNumber()));
			return new ResponseEntity<>(isAccountFound, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
		}
	}

}
