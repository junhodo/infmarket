package Sogong.IMS.model;

import lombok.Data;

public @Data class Package {
    private String packageID;
    private String registrantID;
    private String packageName;
    private String company;
    private String type;
    private String explanation;
    private int price;

    public Package(String packageID, String registrantID, String packageName, String company, String type, String explanation, int price) {
        this.packageID = packageID;
        this.registrantID = registrantID;
        this.packageName = packageName;
        this.company = company;
        this.type = type;
        this.explanation = explanation;
        this.price = price;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getRegistrantID() {
        return registrantID;
    }

    public void setRegistrantID(String registrantID) {
        this.registrantID = registrantID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
