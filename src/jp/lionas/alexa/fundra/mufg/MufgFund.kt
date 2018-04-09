package jp.lionas.alexa.fundra.mufg

import com.google.gson.Gson
import jp.lionas.alexa.fundra.mufg.model.CodeItem
import jp.lionas.alexa.fundra.mufg.model.CodeList
import jp.lionas.alexa.fundra.mufg.model.FundItem
import jp.lionas.alexa.fundra.mufg.model.LatestFund
import okhttp3.OkHttpClient
import okhttp3.Request

import java.util.ArrayList

class MufgFund {

    companion object {

        private const val BASE_URL = "http://developer.am.mufg.jp/"

        private val codeList: List<CodeItem>?
            get() {

                var result: List<CodeItem>? = null

                val client = OkHttpClient()
                val request = Request.Builder()
                        .url(BASE_URL + "code_list")
                        .build()

                try {
                    client.newCall(request).execute().use { response ->
                        val body = response.body()
                        if (body != null) {
                            val gson = Gson()
                            val list = gson.fromJson(body.string(), CodeList::class.java)
                            result = list.value
                        }
                    }
                } catch (e: Exception) {
                    return null
                }

                return result

            }

        @JvmStatic
        @Throws(Exception::class)
        fun getLatestFund(fundCode: String): List<FundItem>? {

            var result: List<FundItem>? = null

            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(BASE_URL + "fund_information_latest/fund_cd/" + fundCode)
                    .build()

            client.newCall(request).execute().use { response ->
                val body = response.body()
                if (body != null) {
                    val gson = Gson()
                    val data = gson.fromJson(body.string(), LatestFund::class.java)
                    result = data.value
                }
            }

            return result

        }

        @JvmStatic
        fun findFund(name: String): List<CodeItem> {

            val result = ArrayList<CodeItem>()
            val items = codeList

            if (items != null && !items.isEmpty()) {
                for (item in items) {
                    var replacedFundName = ""
                    item.fundName?.let {
                        replacedFundName = it
                                .replace("[？！”＃＄％＆’（）・＜＞＿｜〜＋＊＝＾￥：；「」｛｝、。]".toRegex(), "")
                                .replace(" ", "")
                        if (replacedFundName.contains(name)) {
                            result.add(item)
                        }
                    }
//                    println(String.format("original(api) = %s, replaced = %s, voice = %s", item.fundName, replacedFundName, name))
                }
            }

            return result

        }
    }


}
