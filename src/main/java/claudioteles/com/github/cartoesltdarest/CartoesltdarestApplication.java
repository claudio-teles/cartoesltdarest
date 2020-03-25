package claudioteles.com.github.cartoesltdarest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import claudioteles.com.github.cartoesltdarest.models.Saldo;
import claudioteles.com.github.cartoesltdarest.repositories.SaldoRepository;

@SpringBootApplication
public class CartoesltdarestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartoesltdarestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner inicializarSaldo(SaldoRepository saldoRepository) {
		return (args) -> {
			
			saldoRepository.save(new Saldo("terminal", "123456", 1000f, 0f));
			saldoRepository.save(new Saldo("portal", "123456", 1000f, 0f));
			
			saldoRepository.save(new Saldo("user3", "senha3", 1000f, 0f));
			saldoRepository.save(new Saldo("user4", "senha4", 1000f, 0f));
			saldoRepository.save(new Saldo("user5", "senha5", 1000f, 0f));
			
			
			int quantidadeDeUsuarios = (int) saldoRepository.count();
			
			for (int i = 0; i < quantidadeDeUsuarios; i++) {
				List<Saldo> saldos = new ArrayList<>();
				
				saldos.addAll(saldoRepository.findAll());
				
				Saldo saldo = new Saldo();
				saldo = saldos.get(i);
				System.err.println(
						"ID: "+saldo.getId()+ ", User: "+saldo.getUser()+", Disponível: "+saldo.getDisponivel()
						+", Receber: "+saldo.getReceber()
					);
			}

		};
		
	}

}
