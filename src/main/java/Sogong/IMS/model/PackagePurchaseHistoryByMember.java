package Sogong.IMS.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PackagePurchaseHistoryByMember extends PackagePurchaseHistory {
    private Member member;

    public PackagePurchaseHistoryByMember(Package aPackage, LocalDateTime purchaseDate, int totalPrice, int numPurchase, String state, String paymentMethod, Member member) {
        super(aPackage, purchaseDate, totalPrice, numPurchase, state, paymentMethod);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
