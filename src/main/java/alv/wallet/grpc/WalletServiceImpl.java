package alv.wallet.grpc;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import alv.wallet.CurrencyType;
import alv.wallet.DepositRequest;
import alv.wallet.Empty;
import alv.wallet.WalletServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

@GRpcService
public class WalletServiceImpl extends WalletServiceGrpc.WalletServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Override
    public void deposit(DepositRequest request, StreamObserver<Empty> responseObserver) {
        LOGGER.info("Server received {}", request);

        try {
            Enum.valueOf(CurrencyType.class, request.getCurrency().toUpperCase());
        } catch (IllegalArgumentException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Unknown currency.")
                    .augmentDescription("The " + request.getCurrency() + " currency is invalid.")
                    // .withCause(e)
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).augmentDescription("Error")
                    // .withCause(e)
                    .asRuntimeException());
        }

        Empty response = Empty.newBuilder().build();
        LOGGER.info("Empty response ready", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
