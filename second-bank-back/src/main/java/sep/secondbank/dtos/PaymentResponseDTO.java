package sep.secondbank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentResponseDTO {
    private String transactionStatus;

    private long merchantOrderId;

    private long requestId;

    private long acquirerOrderId;

    private LocalDateTime acquirerTimestamp;

    private long paymentId;

    private String errorMessage;
}
