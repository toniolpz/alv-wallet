package alv.wallet.grpc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import alv.wallet.BalanceRequest;
import alv.wallet.BalanceResponse;
import alv.wallet.CurrencyType;
import alv.wallet.DepositRequest;
import alv.wallet.Empty;
import alv.wallet.WalletServiceGrpc;
import alv.wallet.WithdrawRequest;
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

    public Empty deposit(Integer idUser, double amount, String currency) {
        DepositRequest request = DepositRequest.newBuilder().setUserId(idUser).setAmount(amount).setCurrency(currency)
                .build();
        Empty response = walletServiceBlockingStub.deposit(request);
        return response;
    }

    public Empty withdraw(Integer idUser, double amount, String currency) {
        WithdrawRequest request = WithdrawRequest.newBuilder().setUserId(idUser).setAmount(amount).setCurrency(currency)
                .build();
        Empty response = walletServiceBlockingStub.withdraw(request);
        return response;
    }

    public BalanceResponse getBalance(Integer idUser) {
        BalanceRequest request = BalanceRequest.newBuilder().setUserId(idUser).build();
        BalanceResponse response = walletServiceBlockingStub.balance(request);
        return response;
    }
}