package alv.wallet.grpc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import alv.wallet.BalanceResponse.CurrencyAmount;
import alv.wallet.entity.Balance;
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

            // Search for the Balance in the dataBase
            Balance dbBalanceResult = balanceRepository.findByUserIdAndCurrency(request.getUserId(),
                    request.getCurrency());

            // If the record exists, update the existing record summarizing the amounts
            if (dbBalanceResult != null) {
                dbBalanceResult.setAmount(dbBalanceResult.getAmount() + request.getAmount());
                balanceRepository.save(dbBalanceResult);
            } else {
                // If there is no record with this ID, generate a new Balance to save
                Balance balance = new Balance();
                balance.setUserId(request.getUserId());
                balance.setCurrency(request.getCurrency());
                balance.setAmount(request.getAmount());

                balanceRepository.save(balance);
            }

            Empty response = Empty.newBuilder().build();
            LOGGER.info("Empty response for deposit instruction ready {}", response);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            // Handle unknown currency error
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Unknown currency.")
                    .augmentDescription("The " + request.getCurrency() + " currency is invalid.").asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).augmentDescription("Error").asRuntimeException());
        }
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Empty> responseObserver) {
        LOGGER.info("Server withdraw instruction received {}", request);

        try {
            // Validate Currency
            Enum.valueOf(CurrencyType.class, request.getCurrency().toUpperCase());

            // Search for the Balance in the dataBase
            Balance dbBalanceResult = balanceRepository.findByUserIdAndCurrency(request.getUserId(),
                    request.getCurrency());

            // If the record exists, update the existing amount
            if (dbBalanceResult != null) {
                // If there is no engouth money in the balance, throw an
                // InsufficientFundsException
                if (dbBalanceResult.getAmount() < request.getAmount()) {
                    throw new InsufficientFundsException();
                }

                // Update Currency Balance for User
                dbBalanceResult.setAmount(dbBalanceResult.getAmount() - request.getAmount());
                balanceRepository.save(dbBalanceResult);
            } else {
                // If there is no record with this ID, throw an InsufficientFundsException
                throw new InsufficientFundsException();
            }

            Empty response = Empty.newBuilder().build();
            LOGGER.info("Empty response for withdraw instruction ready {}", response);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

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
    }

    @Override
    public void balance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        LOGGER.info("Server Balance request received {}", request);

        // Get Balance from DB
        List<Balance> dbBalanceList = balanceRepository.findByUserId(request.getUserId());

        // Fill list for response with DB data
        List<CurrencyAmount> listCurrencyAmout = dbBalanceList.stream().map(r -> BalanceResponse.CurrencyAmount
                .newBuilder().setAmount(r.getAmount()).setCurrency(r.getCurrency()).build())
                .collect(Collectors.toList());

        // Create the response object
        BalanceResponse response = BalanceResponse.newBuilder().addAllCurrencyAmount(listCurrencyAmout).build();

        LOGGER.info("Account Balance: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        ;
    }
}
