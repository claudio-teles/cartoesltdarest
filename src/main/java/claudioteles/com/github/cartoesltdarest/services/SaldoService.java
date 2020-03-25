package claudioteles.com.github.cartoesltdarest.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import claudioteles.com.github.cartoesltdarest.dao.SaldoDAO;
import claudioteles.com.github.cartoesltdarest.models.Saldo;

@Service
public class SaldoService {
	
	public Map<String, Float> consultarSaldo(String user, String password, SaldoDAO saldoDAO) {
		Saldo saldo = new Saldo();
		if (saldoDAO.existeSaldo(user, password)) {
			saldo = saldoDAO.obterSaldo(user);
			return saldoDAO.obterSaldo(saldo.getId());
		} else {
			return null;
		}
	}

}
