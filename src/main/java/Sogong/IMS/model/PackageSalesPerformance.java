package Sogong.IMS.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PackageSalesPerformance {
    private String packageID;
    private String packageName;
    private String type;
    private String company;
    private int price;
    private int totalPrice;
    private int numTotalPurchase;

    public PackageSalesPerformance(String packageID, String packageName, String type, String company, int price, int totalPrice, int numTotalPurchase) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.type = type;
        this.company = company;
        this.price = price;
        this.totalPrice = totalPrice;
        this.numTotalPurchase = numTotalPurchase;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumTotalPurchase() {
        return numTotalPurchase;
    }

    public void setNumTotalPurchase(int numTotalPurchase) {
        this.numTotalPurchase = numTotalPurchase;
    }
}
