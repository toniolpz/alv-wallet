package alv.wallet.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import alv.wallet.grpc.client.WalletServiceClient;

@ComponentScan(basePackages = "alv.wallet.grpc.client")
public class App {
    private static int usersQuantity;
    private static int threadsQuantity;
    private static int roundsQuantity;

    @Autowired
    private WalletServiceClient client;

    public static void main(String[] args) {
        System.out.println("Users: ");
        String users = System.console().readLine();
        System.out.println("Concurrent threads per user: ");
        String threads = System.console().readLine();
        System.out.println("Rounds per thread: ");
        String rounds = System.console().readLine();

        System.out.println(users + " " + threads + " " + rounds);

        usersQuantity = Integer.parseInt(users);
        threadsQuantity = Integer.parseInt(threads);
        roundsQuantity = Integer.parseInt(rounds);

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        App app = context.getBean(App.class);
        app.executeRounds(usersQuantity, threadsQuantity, roundsQuantity);
    }

    private void executeRounds(int users, int threads, int rounds) {
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