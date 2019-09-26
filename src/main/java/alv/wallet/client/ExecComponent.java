package alv.wallet.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alv.wallet.grpc.client.WalletServiceClient;

@Component
public class ExecComponent {

    @Autowired
    private WalletServiceClient client;

    public void executeRounds(int users, int threads, int rounds) {
        // Concurrent users
        for (Integer i = 1; i <= users; i++) {
            // Simultaneous threads per user
            for (int j = 0; j <= threads; j++) {
                RoundExecuter executer = new RoundExecuter(i, rounds, client);
                Thread thread = new Thread(executer, i.toString());
                thread.start();
                System.out.println("New Thread for user " + i);
            }
        }
    }
}