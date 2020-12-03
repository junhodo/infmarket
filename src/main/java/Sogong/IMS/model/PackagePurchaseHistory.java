package Sogong.IMS.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PackagePurchaseHistory {
    private Package aPackage;
    private LocalDateTime purchaseDate;
    private int totalPrice;
    private int numPurchase;
    private String state;
    private String paymentMethod;

    public PackagePurchaseHistory(Package aPackage, LocalDateTime purchaseDate, int totalPrice, int numPurchase, String state, String paymentMethod) {
        this.aPackage = aPackage;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
        this.numPurchase = numPurchase;
        this.state = state;
        this.paymentMethod = paymentMethod;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumPurchase() {
        return numPurchase;
    }

    public void setNumPurchase(int numPurchase) {
        this.numPurchase = numPurchase;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
