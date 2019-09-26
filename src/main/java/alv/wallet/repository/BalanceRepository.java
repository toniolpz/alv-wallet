package alv.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import alv.wallet.entity.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    List<Balance> findByUserId(int userId);

    @Query("select b from Balance b where b.userId = :userId and b.currency = :currency")
    Balance findByUserIdAndCurrency(@Param("userId") int userId, @Param("currency") String currency);
}