package claudioteles.com.github.cartoesltdarest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import claudioteles.com.github.cartoesltdarest.models.Saldo;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
	
	Boolean existsByUser(String user);
	Boolean existsByPassword(String password);
	
	Saldo findByUser(String user);

}
