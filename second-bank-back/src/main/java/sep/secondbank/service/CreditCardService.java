package sep.secondbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.secondbank.exceptions.CreditCardNotFoundException;
import sep.secondbank.model.CreditCard;
import sep.secondbank.repositories.CreditCardRepository;

@Service
public class CreditCardService {
    private final CreditCardRepository cardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CreditCard getByPAN(String PAN) throws CreditCardNotFoundException {
        CreditCard card = cardRepository.findByPAN(PAN);
        if(card != null) return card;
        throw new CreditCardNotFoundException();
    }
}
