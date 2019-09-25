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
    public void testDeposit() {
        assertThat(client.deposit(1, 100, "MXP").equals(Empty.newBuilder().build()));
    }

    @Test
    public void testWithdraw() {
        assertThat(client.withdraw(1, 100, "EUR").equals(Empty.newBuilder().build()));
    }

    @Test
    public void testGetBalance() {
        assertThat(client.getBalance(1).getCurrencyAmountList().size() > 0);
    }
}