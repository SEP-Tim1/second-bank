package sep.secondbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sep.secondbank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByMerchantIdAndMerchantPassword(long merchantId, String merchantPassword);
}
