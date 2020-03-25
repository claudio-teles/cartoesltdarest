package claudioteles.com.github.cartoesltdarest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import claudioteles.com.github.cartoesltdarest.models.Transacao;
import claudioteles.com.github.cartoesltdarest.repositories.TransacaoRepository;

@Repository
public class TransacaoDAO {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	public void savarTransacao(Transacao transacao) {
		transacaoRepository.save(transacao);
	}
	
	public Transacao obterTransacao(Long nsu) {
		return transacaoRepository.getOne(nsu);
	}
	
	public List<Transacao> listarTodasAsTransacoes() {
		return transacaoRepository.findAll();
	}

}
