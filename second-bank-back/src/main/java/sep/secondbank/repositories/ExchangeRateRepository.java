package sep.secondbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sep.secondbank.model.Currency;
import sep.secondbank.model.ExchangeRate;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    boolean existsBySrcAndDest(Currency src, Currency dest);

    Optional<ExchangeRate> findBySrcAndDest(Currency src, Currency dest);
}
