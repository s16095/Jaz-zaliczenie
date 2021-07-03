package pjatk.adrwoj.jazs16095nbp.service;

import org.springframework.stereotype.Service;
import pjatk.adrwoj.jazs16095nbp.model.Currency;
import pjatk.adrwoj.jazs16095nbp.repository.CurrencyRepository;

@Service
public class NbpCurrencyService {
    private final CurrencyRepository currencyRepository;

    public NbpCurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }
}
