package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import java.util.Optional

import com.amazon.ask.request.Predicates.intentName
import com.amazon.ask.request.Predicates.sessionAttribute

class AnswerNoIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName(Const.ANSWER_NO_INTENT_NAME)
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_END).negate()))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {

        val sessionAttributes = handlerInput.attributesManager.sessionAttributes
        sessionAttributes[Const.STATE_KEY] = Const.STATE_END

        return handlerInput.responseBuilder
                .withSpeech(Const.CANCEL_SPEECH_TEXT)
                .withSimpleCard(Const.SKILL_NAME, Const.CANCEL_SPEECH_TEXT)
                .withShouldEndSession(true)
                .build()

    }

}
