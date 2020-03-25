package claudioteles.com.github.cartoesltdarest.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import claudioteles.com.github.cartoesltdarest.models.Saldo;
import claudioteles.com.github.cartoesltdarest.repositories.SaldoRepository;

@Repository
public class SaldoDAO {
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	public Map<String, Float> obterSaldo(Long id) {
		Saldo saldo = new Saldo();
		saldo = saldoRepository.getOne(id);
		Map<String, Float> respostaSaldo = new HashMap<>();
		respostaSaldo.put("disponivel", saldo.getDisponivel());
		respostaSaldo.put("receber", saldo.getReceber());
		return respostaSaldo;
	}
	
	public Saldo obterSaldo(String user) {
		return saldoRepository.findByUser(user);
	}
	
	public Boolean existeSaldo(String user, String password) {
		return saldoRepository.existsByUser(user);
	}
	
	public void salveOuAtualize(Saldo saldo) {
		saldoRepository.save(saldo);
	}

}
