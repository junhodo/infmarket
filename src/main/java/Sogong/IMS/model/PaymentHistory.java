package Sogong.IMS.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentHistory {
    private String paymentHistoryID;
    private String accomodationBookHistoryID;
    private String registrantID;
    private int price;
    private String paymentMethod;
    private LocalDateTime paymentTime;
}