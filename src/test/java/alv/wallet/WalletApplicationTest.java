package alv.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import alv.wallet.grpc.WalletServiceClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletApplicationTest {
    @Autowired
    private WalletServiceClient client;
    
    @Test
    public void test1() {
        assertThat(client.withdraw(1, 200, "USD").equals(Empty.newBuilder().build()));
    }

    @Test
    public void test2() {
        assertThat(client.deposit(1, 100, "USD").equals(Empty.newBuilder().build()));
    }

    @Test
    public void test3() {
        BalanceResponse response =client.getBalance(1);
        assertThat(response.getCurrencyAmountCount() > 0
            && response.getCurrencyAmountList().stream()
            .filter(c -> c.getCurrency().equals(CurrencyType.USD.toString())
            && c.getAmount() == 100).findAny() != null);
    }

    @Test
    public void test4() {
        assertThat(client.withdraw(1, 200, "USD").equals(Empty.newBuilder().build()));
    }

}