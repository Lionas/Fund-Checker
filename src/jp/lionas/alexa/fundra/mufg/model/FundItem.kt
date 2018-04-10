package jp.lionas.alexa.fundra.mufg.model

import com.google.gson.annotations.SerializedName

class FundItem {

    @SerializedName("fund_name")
    var fundName: String? = null

    @SerializedName("cmp_prev_day")
    var cmpPrevDay: Int = 0

    @SerializedName("nav")
    var nav: Int = 0
}
