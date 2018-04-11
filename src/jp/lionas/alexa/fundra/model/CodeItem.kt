package jp.lionas.alexa.fundra.model

import com.google.gson.annotations.SerializedName

class CodeItem {

    @SerializedName("fund_name")
    var fundName: String? = null

    @SerializedName("isin_cd")
    var isinCode: String? = null

    @SerializedName("association_fund_cd")
    var associationFundCode: String? = null

    @SerializedName("fund_cd")
    var fundCode: String? = null
}
