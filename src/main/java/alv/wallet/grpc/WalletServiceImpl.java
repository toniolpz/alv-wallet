package alv.wallet.grpc;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import alv.wallet.BalanceRequest;
import alv.wallet.BalanceResponse;
import alv.wallet.CurrencyType;
import alv.wallet.DepositRequest;
import alv.wallet.Empty;
import alv.wallet.WalletServiceGrpc;
import alv.wallet.WithdrawRequest;
import alv.wallet.entity.Balance;
import alv.wallet.entity.type.BalanceId;
import alv.wallet.error.InsufficientFundsException;
import alv.wallet.repository.BalanceRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

@GRpcService
public class WalletServiceImpl extends WalletServiceGrpc.WalletServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public void deposit(DepositRequest request, StreamObserver<Empty> responseObserver) {
        LOGGER.info("Server deposit instruction received {}", request);

        try {
            // Validate Currency
            Enum.valueOf(CurrencyType.class, request.getCurrency().toUpperCase());

            // Search for the Balance Id in the dataBase
            BalanceId balanceId = new BalanceId(request.getUserId(), request.getCurrency());
            Balance dbBalance = balanceRepository.getOne(balanceId);

            // If the record exists, update the existing record summarizing the amounts
            if (dbBalance != null) {
                dbBalance.setAmount(dbBalance.getAmout() + request.getAmount());
            } else {
                // If there is no record with this ID, generate a new Balance to save
                dbBalance = new Balance();
                dbBalance.setId(balanceId);
                dbBalance.setAmount(request.getAmount());
            }

            // Update Currency Balance for User
            balanceRepository.save(dbBalance);

        } catch (IllegalArgumentException e) {
            // Handle unknown currency error
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Unknown currency.")
                    .augmentDescription("The " + request.getCurrency() + " currency is invalid.").asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).augmentDescription("Error").asRuntimeException());
        }

        Empty response = Empty.newBuilder().build();
        LOGGER.info("Empty response for deposit instruction ready {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Empty> responseObserver) {
        LOGGER.info("Server withdraw instruction received {}", request);

        try {
            // Validate Currency
            Enum.valueOf(CurrencyType.class, request.getCurrency().toUpperCase());

            // Search for the Balance Id in the dataBase
            BalanceId balanceId = new BalanceId(request.getUserId(), request.getCurrency());
            Balance dbBalance = balanceRepository.getOne(balanceId);

            // If the record exists, update the existing amount
            if (dbBalance != null) {
                // If there is no engouth money in the balance, throw an
                // InsufficientFundsException
                if (dbBalance.getAmout() < request.getAmount()) {
                    throw new InsufficientFundsException();
                }
                dbBalance.setAmount(dbBalance.getAmout() - request.getAmount());

                // Update Currency Balance for User
                balanceRepository.save(dbBalance);
            } else {
                // If there is no record with this ID, throw an InsufficientFundsException
                throw new InsufficientFundsException();
            }
        } catch (IllegalArgumentException e) {
            // Handle unknown currency error
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Unknown currency.")
                    .augmentDescription("The " + request.getCurrency() + " currency is invalid.").asRuntimeException());
        } catch (InsufficientFundsException e) {
            // Handle InsufficientFundsException
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).augmentDescription("Error").asRuntimeException());
        }

        Empty response = Empty.newBuilder().build();
        LOGGER.info("Empty response for withdraw instruction ready {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void balance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        LOGGER.info("Server Balance request received {}", request);

        BalanceResponse response = BalanceResponse.newBuilder().addCurrencyAmount(BalanceResponse.CurrencyAmount
                .newBuilder().setCurrency(CurrencyType.EUR.toString()).setAmount(100).build()).build();

        LOGGER.info("Account Balance: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        ;
    }
}
