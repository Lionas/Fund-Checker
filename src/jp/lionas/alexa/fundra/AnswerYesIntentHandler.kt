package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName
import com.amazon.ask.request.Predicates.sessionAttribute
import jp.lionas.alexa.fundra.def.State
import jp.lionas.alexa.fundra.mufg.FundUtil
import jp.lionas.alexa.fundra.mufg.MufgFund
import java.util.*

class AnswerYesIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName("AMAZON.YesIntent")
                .and(sessionAttribute(State.STATE_KEY, State.STATE_REPEAT)))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {

        val sessionAttributes = handlerInput.attributesManager.sessionAttributes
        val fundName = sessionAttributes[State.FUND_NAME] as String
        val found = MufgFund.findFund(fundName)

        // 複数マッチした場合
        return FundUtil.getRepeatedFunds(handlerInput, sessionAttributes, found)

    }


}
