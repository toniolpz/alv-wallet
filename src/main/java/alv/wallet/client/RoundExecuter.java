package alv.wallet.client;

import java.util.ArrayList;
import java.util.Random;

import alv.wallet.WithdrawRequest;
import alv.wallet.grpc.client.WalletServiceClient;

public class RoundExecuter implements Runnable {
    private Integer rounds;
    private Random random = new Random();
    private ArrayList<Round> roundList = new ArrayList<Round>();

    private WalletServiceClient client;

    public RoundExecuter(Integer userId, Integer rounds, WalletServiceClient client) {
        this.client = client;
        this.rounds = rounds;

        class RoundA implements Round {
            public void run() {
                depositTask(userId, 100, "USD");
                depositTask(userId, 100, "USD");
                withdrawTask(userId, 200, "USD");
                depositTask(userId, 100, "EUR");
                getBalanceTask(userId);
                withdrawTask(userId, 100, "USD");
                getBalanceTask(userId);
                withdrawTask(userId, 100, "USD");
            }
        }

        class RoundB implements Round {
            public void run() {
                depositTask(userId, 100, "GBP");
                depositTask(userId, 300, "GPB");
                withdrawTask(userId, 100, "GBP");
                withdrawTask(userId, 100, "GBP");
                withdrawTask(userId, 100, "GBP");
            }
        }

        class RoundC implements Round {
            public void run() {
                getBalanceTask(userId);
                getBalanceTask(userId);
                depositTask(userId, 100, "USD");
                depositTask(userId, 100, "USD");
                withdrawTask(userId, 100, "USD");
                depositTask(userId, 100, "USD");
                getBalanceTask(userId);
                withdrawTask(userId, 200, "USD");
                getBalanceTask(userId);
            }
        }

        RoundA roundA = new RoundA();
        RoundB roundB = new RoundB();
        RoundC roundC = new RoundC();

        roundList.add(roundA);
        roundList.add(roundB);
        roundList.add(roundC);
    }

    public void run() {
        // Rounds per thread
        for (int times = 0; times < rounds; times++) {
            int i = random.nextInt(roundList.size());
            roundList.get(i).run();
        }
    }

    public void depositTask(int userId, double amount, String currency) {
        try {
            client.deposit(userId, amount, currency);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void withdrawTask(int userId, double amount, String currency) {
        try {
            client.withdraw(userId, amount, currency);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getBalanceTask(int userId) {
        try {
            client.getBalance(userId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}