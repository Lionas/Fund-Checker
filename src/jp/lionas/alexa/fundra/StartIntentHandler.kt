package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import jp.lionas.alexa.fundra.mufg.FundUtil
import jp.lionas.alexa.fundra.mufg.MufgFund
import java.util.Optional

import com.amazon.ask.request.Predicates.intentName
import com.amazon.ask.request.Predicates.sessionAttribute

class StartIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName(Const.START_INTENT_NAME)
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_START).negate())
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_END).negate())) ||
                handlerInput.matches(intentName("AMAZON.StartOverIntent"))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {

        val sessionAttributes = handlerInput.attributesManager.sessionAttributes
        sessionAttributes[Const.STATE_KEY] = Const.STATE_START
        sessionAttributes[Const.INDEX_KEY] = 0

        var fundName = ""
        val slots = SlotUtil.getSlots(handlerInput)
        slots?.let {
            fundName = it[Const.QUERY_FUND_NAME]?.value.toString()
            sessionAttributes[Const.FUND_NAME] = fundName
        }

        val found = MufgFund.findFund(fundName)
        val noData = if (!fundName.isEmpty()) String.format(Const.NOT_FOUND, fundName) else Const.NO_SPOKEN

        return when {

            found.isEmpty() -> {
                // データが0件
                sessionAttributes[Const.STATE_KEY] = Const.STATE_END
                handlerInput.responseBuilder
                        .withSpeech(noData)
                        .withSimpleCard(Const.SKILL_NAME, noData)
                        .build()
            }

            found.size == 1 -> {
                // 1件だけマッチした場合
                FundUtil.getLastFund(handlerInput, sessionAttributes, found[0])
            }

            else -> {
                // 複数マッチした場合
                FundUtil.getRepeatedFunds(handlerInput, sessionAttributes, found)
            }

        }

    }

}
