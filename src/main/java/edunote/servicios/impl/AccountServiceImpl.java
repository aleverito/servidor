package edunote.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edunote.pojos.Account;
import edunote.repositorios.AccountRepository;
import edunote.servicios.AccountService;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository $$cuenta;
	@Override
	public Account authentication(String username) {
		return $$cuenta.findByUsername(username);
	}
}
