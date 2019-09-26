package alv.wallet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.core.Is;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import alv.wallet.grpc.WalletServiceClient;
import io.grpc.StatusRuntimeException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WalletApplicationTest {
    @Autowired
    private WalletServiceClient client;

    private final Empty emptyObject = Empty.newBuilder().build();
    private final String INSUFFICIENT_FUNDS = "INTERNAL: Insufficient Funds.";

    @Test(expected = StatusRuntimeException.class)
    public void testA() {
        try {
            client.withdraw(1, 200, "USD");
        } catch (StatusRuntimeException re) {
            assertEquals(INSUFFICIENT_FUNDS, re.getMessage());
            throw re;
        }
        fail("Insufficient Funds exception did not throw!");
    }

    @Test
    public void testB() {
        Empty response = client.deposit(1, 100, "USD");
        assertThat(response, Is.is(emptyObject));
    }

    @Test
    public void testC() {
        BalanceResponse response = client.getBalance(1);
        assertTrue(response.getCurrencyAmountList().stream()
                .anyMatch(c -> c.getCurrency().equals(CurrencyType.USD.toString()) && c.getAmount() == 100));
    }

    @Test(expected = StatusRuntimeException.class)
    public void testD() {
        try {
            client.withdraw(1, 200, "USD");
        } catch (StatusRuntimeException re) {
            assertEquals(INSUFFICIENT_FUNDS, re.getMessage());
            throw re;
        }
        fail("Insufficient Funds exception did not throw!");
    }

    @Test
    public void testE() {
        Empty response = client.deposit(1, 100, "EUR");
        assertThat(response, Is.is(emptyObject));
    }

    @Test
    public void testF() {
        BalanceResponse response = client.getBalance(1);
        assertTrue(response.getCurrencyAmountList().stream()
                .anyMatch(c -> c.getCurrency().equals(CurrencyType.USD.toString()) && c.getAmount() == 100)
                && response.getCurrencyAmountList().stream()
                        .anyMatch(c -> c.getCurrency().equals(CurrencyType.EUR.toString()) && c.getAmount() == 100));
    }

    @Test(expected = StatusRuntimeException.class)
    public void testG() {
        try {
            client.withdraw(1, 200, "USD");
        } catch (StatusRuntimeException re) {
            assertEquals(INSUFFICIENT_FUNDS, re.getMessage());
            throw re;
        }
        fail("Insufficient Funds exception did not throw!");
    }

    @Test
    public void testH() {
        Empty response = client.deposit(1, 100, "USD");
        assertThat(response, Is.is(emptyObject));
    }

    @Test
    public void testI() {
        BalanceResponse response = client.getBalance(1);
        assertTrue(response.getCurrencyAmountList().stream()
                .anyMatch(c -> c.getCurrency().equals(CurrencyType.USD.toString()) && c.getAmount() == 200)
                && response.getCurrencyAmountList().stream()
                        .anyMatch(c -> c.getCurrency().equals(CurrencyType.EUR.toString()) && c.getAmount() == 100));
    }

    @Test
    public void testJ() {
        Empty response = client.withdraw(1, 200, "USD");
        assertThat(response, Is.is(emptyObject));
    }

    @Test
    public void testK() {
        BalanceResponse response = client.getBalance(1);
        assertTrue(response.getCurrencyAmountList().stream()
                .anyMatch(c -> c.getCurrency().equals(CurrencyType.USD.toString()) && c.getAmount() == 0)
                && response.getCurrencyAmountList().stream()
                        .anyMatch(c -> c.getCurrency().equals(CurrencyType.EUR.toString()) && c.getAmount() == 100));
    }

    @Test(expected = StatusRuntimeException.class)
    public void testL() {
        Empty response = client.withdraw(1, 200, "USD");
        assertThat(response, Is.is(emptyObject));
    }
}