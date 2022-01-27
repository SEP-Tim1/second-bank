package sep.secondbank.exceptions;

public class CreditCardInfoNotValidException extends Exception {
    private static final String message = "Credit card info is not valid!";

    public CreditCardInfoNotValidException() {
        super(message);
    }
}
