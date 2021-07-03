package pjatk.adrwoj.jazs16095nbp.repository;

import org.springframework.stereotype.Repository;
import pjatk.adrwoj.jazs16095nbp.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    @Override
    <S extends Currency> S save(S s);
}