package sep.secondbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.secondbank.model.Currency;
import sep.secondbank.model.ExchangeRate;
import sep.secondbank.repositories.ExchangeRateRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRateRepository repository;

    public boolean conversionSupported(Currency src, Currency dest) {
        return src == dest ||
                repository.existsBySrcAndDest(src, dest) ||
                repository.existsBySrcAndDest(dest, src);
    }

    public BigDecimal exchange(Currency src, BigDecimal srcAmount, Currency dest) {
        if (src == dest) {
            return srcAmount;
        }
        Optional<ExchangeRate> rate = repository.findBySrcAndDest(src, dest);
        if (rate.isPresent()) {
            return exchange(srcAmount, rate.get().getRate());
        }
        rate = repository.findBySrcAndDest(dest, src);
        if (rate.isPresent()) {
            return exchange(srcAmount, 1/rate.get().getRate());
        }
        return null;
    }

    private BigDecimal exchange(BigDecimal amount, double rate) {
        return amount.multiply(new BigDecimal(rate));
    }
}
