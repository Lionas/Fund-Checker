package jp.lionas.alexa.fundra.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import jp.lionas.alexa.fundra.util.FundUtil
import jp.lionas.alexa.fundra.api.MufgFundApi
import jp.lionas.alexa.fundra.def.State
import java.util.Optional

import com.amazon.ask.request.Predicates.intentName
import com.amazon.ask.request.Predicates.sessionAttribute
import jp.lionas.alexa.fundra.util.SlotUtil
import jp.lionas.alexa.fundra.def.Message
import jp.lionas.alexa.fundra.def.Intent
import jp.lionas.alexa.fundra.def.Query

class StartIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName(Intent.START_INTENT_NAME)
                .and(sessionAttribute(State.STATE_KEY, State.STATE_START).negate())
                .and(sessionAttribute(State.STATE_KEY, State.STATE_END).negate())) ||
                handlerInput.matches(intentName("AMAZON.StartOverIntent"))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {

        val sessionAttributes = handlerInput.attributesManager.sessionAttributes
        sessionAttributes[State.STATE_KEY] = State.STATE_START
        sessionAttributes[State.INDEX_KEY] = 0

        var fundName = ""
        val slots = SlotUtil.getSlots(handlerInput)
        slots?.let {
            fundName = it[Query.FUND_NAME]?.value.toString()
            sessionAttributes[State.FUND_NAME] = fundName
        }

        val found = MufgFundApi.findFund(fundName)
        val noData = if (!fundName.isEmpty()) String.format(Message.NOT_FOUND, fundName) else Message.NO_SPOKEN

        return when {

            found.isEmpty() -> {
                // データが0件
                sessionAttributes[State.STATE_KEY] = State.STATE_END
                handlerInput.responseBuilder
                        .withSpeech(noData)
                        .withSimpleCard(Message.SKILL_NAME, noData)
                        .withShouldEndSession(true)
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
