package claudioteles.com.github.cartoesltdarest.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import claudioteles.com.github.cartoesltdarest.dao.SaldoDAO;
import claudioteles.com.github.cartoesltdarest.dao.TransacaoDAO;
import claudioteles.com.github.cartoesltdarest.models.Saldo;
import claudioteles.com.github.cartoesltdarest.models.Transacao;

@Service
public class TransacaoService {
	
	public TransacaoService() {
		super();
	}

	/**
	 * Regra de negócio antes de persistir uma transação
	 * @param transacaoJson
	 * @param saldoDAO
	 * @param transacaoDAO
	 * @return
	 */
	public Transacao persistirTransacao(Map<String, String> transacaoJson, SaldoDAO saldoDAO, TransacaoDAO transacaoDAO) {
		String user = transacaoJson.get("user");
		String password = transacaoJson.get("password");
		
		String modalidade = transacaoJson.get("modalidade");
//		
//		- Não permitir valores negativos
//		- Não permitir valores inválidos (textos, números com 2 vírgulas, etc, datas incorretas)
//		- Aceitar apenas as modalidades e bandeiras descritas no cenário
		DecimalFormat df = new DecimalFormat("#.00");
		String valorArredondadoString = df.format(Double.parseDouble(transacaoJson.get("valor")));
		String valorArredondadoPontoFlutuante = valorArredondadoString.replace(",", ".");
		Float valorArredondado = Float.parseFloat(valorArredondadoPontoFlutuante);
		
		Date dataDisponivel = null;
		
		Transacao transacao = null;
		
		@SuppressWarnings("deprecation")
		int diaHoje = new Date().getDate();
		int proximoDia = diaHoje + 1;
		if (modalidade.equals("debito")) {
			@SuppressWarnings("deprecation")
			// Transações em débito: Próximo dia útil
			Date data = new Date(new Date().getYear(), new Date().getMonth(), proximoDia);
			Float valorDescontado = (float) (Math.abs(valorArredondado - (valorArredondado * .02)));// Débito: 2%
			transacao = new Transacao(valorDescontado, transacaoJson.get("bandeira"),
					modalidade);
			dataDisponivel = data;
		}
		
		if (modalidade.equals("credito")) {
			@SuppressWarnings("deprecation")
			// Transações em crédito: Próximo dia útil após 30 corridos
			Date data = new Date(
						new Date().getYear(), new Date().getMonth(), proximoDia, new Date().getHours(), 30
					);
			Float valorDescontado = (float) (Math.abs(valorArredondado - (valorArredondado * .03)));// Crédito: 3%
			transacao = new Transacao(valorDescontado, transacaoJson.get("bandeira"),
					modalidade);
			dataDisponivel = data;
		}
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dataDisponivel);
		
		transacao.setHorario(new Date());
		transacao.setDisponivel(dataDisponivel);
		
		Saldo saldo = new Saldo();
		saldo = saldoDAO.obterSaldo(user);
		saldo.setDisponivel(saldo.getDisponivel() - transacao.getValor());
		saldo.setReceber(transacao.getValor());
		
		saldoDAO.salveOuAtualize(saldo);
		
		Float liquido = (saldo.getDisponivel());
		
		transacao.setLiquido(liquido);
		
//		- Considerar dias úteis de segunda a sexta. Não há necessidade de analisar feriados
//		- Cálculo dos valores deverá ser através de arredondamentos matemáticos (2 casas)
		int dia = calendario.get(Calendar.DAY_OF_WEEK);
		
		if ( (dia != Calendar.SATURDAY) || (dia != Calendar.SUNDAY) ) {
			if (saldo.getDisponivel() > 0) {
//				- A API deverá oferecer recurso de autenticação (basic authentication)
				if (saldoDAO.existeSaldo(user, password)) {
					transacaoDAO.savarTransacao(transacao);
					return transacao;
				}
			} 
		}
		return null;
	}
	
	/**
	 * Servico que permite encontrar todas as transações
	 * @param transacaoJson
	 * @param saldoDAO
	 * @param transacaoDAO
	 * @return
	 */
	public List<Transacao> consultarTodasTrasacoes(Map<String, String> transacaoJson, SaldoDAO saldoDAO, TransacaoDAO transacaoDAO) {
		String user = transacaoJson.get("user");
		String password = transacaoJson.get("password");
		List<Transacao> transacaos = new ArrayList<>();
//		- A API deverá oferecer recurso de autenticação (basic authentication)
		if (saldoDAO.existeSaldo(user, password)) {
			transacaos.addAll(transacaoDAO.listarTodasAsTransacoes());
			return transacaos;
		}
		return null;
	}

}
