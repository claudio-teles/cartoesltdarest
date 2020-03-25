package claudioteles.com.github.cartoesltdarest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import claudioteles.com.github.cartoesltdarest.dao.SaldoDAO;
import claudioteles.com.github.cartoesltdarest.dao.TransacaoDAO;
import claudioteles.com.github.cartoesltdarest.models.Transacao;
import claudioteles.com.github.cartoesltdarest.services.SaldoService;
import claudioteles.com.github.cartoesltdarest.services.TransacaoService;

@CrossOrigin
@RestController
@RequestMapping("/")
@SessionScope
public class Controller {
	
	@Autowired
	private SaldoDAO saldoDAO;
	@Autowired
	private TransacaoDAO transacaoDAO;
	
	@Autowired
	private SaldoService saldoService;
	@Autowired
	private TransacaoService transacaoService;
	
	
	/**
	 * A API deverá fornecer uma consulta de saldo, através de método GET, contendo os valores já
	 * disponíveis e os valores a receber, baseado na data de acionamento desta consulta:
	 * @param consultaJson
	 * @return
	 */
	@GetMapping("/saldo")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Float> consultarSaldo(@RequestBody Map<String, String> consultaJson) {
		return saldoService.consultarSaldo(consultaJson.get("user"), consultaJson.get("password"), saldoDAO);
	}
	
	
	/**
	 * Após cada transação, a máquina de cartão envia para uma API um objeto JSON...
	 * @param transacaoJson
	 * @return
	 */
	@PostMapping("/transacao")
	@ResponseStatus(HttpStatus.CREATED)
	public Transacao salvarTransacao(@RequestBody Map<String, String> transacaoJson) {
		return transacaoService.persistirTransacao(transacaoJson, saldoDAO, transacaoDAO);
	}
	
	
	/**
	 * A API deverá fornecer um extrato, através de método GET, contendo o detalhamento de todas as
	 * transações realizadas:
	 * @param transacaoJson
	 * @return
	 */
	@GetMapping("/transacao")
	@ResponseStatus(HttpStatus.OK)
	public List<Transacao> listarTransacoes(@RequestBody Map<String, String> transacaoJson) {
		return transacaoService.consultarTodasTrasacoes(transacaoJson, saldoDAO, transacaoDAO);
	}

}
