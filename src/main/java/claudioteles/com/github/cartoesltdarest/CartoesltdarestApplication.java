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
		int quantidadeDeUsuarios = (int) saldoRepository.count();
		
		if (quantidadeDeUsuarios > 0) {
			for (int i = 0; i < quantidadeDeUsuarios; i++) {
				List<Saldo> saldos = new ArrayList<>();

				saldos.addAll(saldoRepository.findAll());

				Saldo saldo = new Saldo();
				saldo = saldos.get(i);
				System.err.println("ID: " + saldo.getId() + ", User: " + saldo.getUser() + ", Disponível: "
						+ saldo.getDisponivel() + ", Receber: " + saldo.getReceber());
			} 
		}
		return (args) -> {
			
			if (quantidadeDeUsuarios < 5) {
				saldoRepository.save(new Saldo(1l, "terminal", "123456", 1000f, 0f));
				saldoRepository.save(new Saldo(2l, "portal", "123456", 1000f, 0f));
				
				saldoRepository.save(new Saldo(3l, "user3", "senha3", 1000f, 0f));
				saldoRepository.save(new Saldo(4l, "user4", "senha4", 1000f, 0f));
				saldoRepository.save(new Saldo(5l, "user5", "senha5", 1000f, 0f));

			}

		};
		
	}

}
