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
public class RefundHistory {
    private String refundHistoryID;
    private String paymentHistoryID;
    private String registrantID;
    private String resonsRefund;
    private int refundPrice;
    private LocalDateTime refundTime;
    private String refundMethod;
}