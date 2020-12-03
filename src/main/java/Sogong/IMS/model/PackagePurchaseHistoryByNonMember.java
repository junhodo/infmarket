package Sogong.IMS.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PackagePurchaseHistoryByNonMember extends PackagePurchaseHistory {
    private String buyerName;
    private String buyerEmail;
    private String buyerPhoneNumber;

    public PackagePurchaseHistoryByNonMember(Package aPackage, LocalDateTime purchaseDate, int totalPrice, int numPurchase, String state, String paymentMethod, String buyerName, String buyerEmail, String buyerPhoneNumber) {
        super(aPackage, purchaseDate, totalPrice, numPurchase, state, paymentMethod);
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }
}
