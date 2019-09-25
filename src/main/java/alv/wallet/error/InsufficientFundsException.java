package alv.wallet.error;

public class InsufficientFundsException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String INSUFFICIENT_FUNDS = "Insufficient Funds.";

    public InsufficientFundsException() {
        super(INSUFFICIENT_FUNDS);
    }
}