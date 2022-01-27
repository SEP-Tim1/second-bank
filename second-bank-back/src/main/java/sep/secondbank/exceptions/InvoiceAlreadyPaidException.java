package sep.secondbank.exceptions;

public class InvoiceAlreadyPaidException extends Exception {
    private static final String message = "Invoice already paid!";

    public InvoiceAlreadyPaidException() {
        super(message);
    }
}
