package alv.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alv.wallet.entity.Balance;
import alv.wallet.entity.type.BalanceId;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, BalanceId>{

}