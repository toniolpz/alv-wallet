package alv.wallet.grpc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import alv.wallet.CurrencyType;
import alv.wallet.DepositRequest;
import alv.wallet.WalletServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class WalletServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceClient.class);

    private WalletServiceGrpc.WalletServiceBlockingStub walletServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
        walletServiceBlockingStub = WalletServiceGrpc.newBlockingStub(managedChannel);
    }

    public Boolean deposit(Integer idUser, double amount, String currency) {
        DepositRequest deposit = DepositRequest.newBuilder().setUserId(idUser).setAmount(amount).setCurrency(currency).build();

        walletServiceBlockingStub.deposit(deposit);
        return true;
    }
}