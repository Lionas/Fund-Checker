package jp.lionas.alexa.fundra.mufg.model;

import com.google.gson.annotations.SerializedName;

public class CodeItem {

    @SerializedName("fund_name")
    private String fundName;

    @SerializedName("isin_cd")
    private String isinCode;

    @SerializedName("association_fund_cd")
    private String associationFundCode;

    @SerializedName("fund_cd")
    private String fundCode;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getIsinCode() {
        return isinCode;
    }

    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }

    public String getAssociationFundCode() {
        return associationFundCode;
    }

    public void setAssociationFundCode(String associationFundCode) {
        this.associationFundCode = associationFundCode;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
}
