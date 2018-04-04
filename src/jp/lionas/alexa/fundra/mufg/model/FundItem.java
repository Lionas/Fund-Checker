package jp.lionas.alexa.fundra.mufg.model;

import com.google.gson.annotations.SerializedName;

public class FundItem {

    @SerializedName("fund_name")
    private String fundName;

    @SerializedName("cmp_prev_day")
    private int cmpPrevDay;

    @SerializedName("nav")
    private int nav;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public int getCmpPrevDay() {
        return cmpPrevDay;
    }

    public void setCmpPrevDay(int cmpPrevDay) {
        this.cmpPrevDay = cmpPrevDay;
    }

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }
}
