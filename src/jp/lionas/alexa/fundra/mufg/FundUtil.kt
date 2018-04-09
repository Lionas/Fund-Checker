package jp.lionas.alexa.fundra.mufg

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Response
import jp.lionas.alexa.fundra.Const
import jp.lionas.alexa.fundra.mufg.model.CodeItem
import jp.lionas.alexa.fundra.mufg.model.FundItem
import java.util.Optional

object FundUtil {

    private fun getLatestFundStatus(item: CodeItem): String? {

        try {
            val fundCode: String? = item.fundCode

            fundCode?.let{
                val funds = MufgFund.getLatestFund(it)
                if (funds == null || funds.isEmpty()) {
                    return Const.ERROR
                }

                val fundItem = funds[0]
                return String.format(Const.STATUS_MESSAGE,
                        fundItem.fundName,
                        fundItem.nav,
                        if (fundItem.cmpPrevDay >= 0) Const.PLUS else Const.MINUS,
                        Math.abs(fundItem.cmpPrevDay))
            }

        } catch (e: Exception) {
            return Const.ERROR
        }

        return ""

    }

    fun getLastFund(handlerInput: HandlerInput,
                    sessionAttributes: MutableMap<String, Any>,
                    lastItem: CodeItem): Optional<Response> {

        // 1件だけマッチした場合
        sessionAttributes[Const.STATE_KEY] = Const.STATE_END
        val speechText = FundUtil.getLatestFundStatus(lastItem)
        return handlerInput.responseBuilder
                .withSpeech(speechText)
                .withSimpleCard(Const.SKILL_NAME, speechText)
                .withShouldEndSession(true)
                .build()

    }

    fun getRepeatedFunds(handlerInput: HandlerInput,
                         sessionAttributes: MutableMap<String, Any>,
                         found: List<CodeItem>): Optional<Response> {

        var speechText = ""
        val targetIndex = sessionAttributes[Const.INDEX_KEY] as Int
        if (targetIndex == 0) {
            speechText += String.format(Const.FOUND_MULTIPLE, found.size)
        }
        sessionAttributes[Const.INDEX_KEY] = targetIndex + 1

        return if (targetIndex < found.size - 1) {
            // 最後の要素でない場合
            sessionAttributes[Const.STATE_KEY] = Const.STATE_REPEAT
            speechText += FundUtil.getLatestFundStatus(found[targetIndex])
            speechText += Const.ASK_NEXT
            handlerInput.responseBuilder
                    .withSpeech(speechText)
                    .withSimpleCard(Const.SKILL_NAME, speechText)
                    .withReprompt(Const.ASK_NEXT)
                    .withShouldEndSession(false)
                    .build()
        } else {
            // 最後の要素の場合
            getLastFund(handlerInput, sessionAttributes, found[targetIndex])
        }

    }

}
