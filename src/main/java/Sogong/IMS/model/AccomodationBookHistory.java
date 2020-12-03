package Sogong.IMS.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AccomodationBookHistory {

    private String accomodationBookHistoryID;
    private int numOfPeople;
    private String name;
    private String phoneNum;
    private LocalDate bookDate;
    private String bookState;
    private int paymentPrice;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String enteringState;
    private String memberID;
    private String registrantID;
    private String accomodationID;
    private int roomNum;

    
}