package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import java.util.Optional

import com.amazon.ask.request.Predicates.intentName
import com.amazon.ask.request.Predicates.sessionAttribute
import jp.lionas.alexa.fundra.def.Message
import jp.lionas.alexa.fundra.def.State

class AnswerNoIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName("AMAZON.NoIntent")
                .and(sessionAttribute(State.STATE_KEY, State.STATE_END).negate()))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {

        val sessionAttributes = handlerInput.attributesManager.sessionAttributes
        sessionAttributes[State.STATE_KEY] = State.STATE_END

        return handlerInput.responseBuilder
                .withSpeech(Message.CANCEL_SPEECH_TEXT)
                .withSimpleCard(Message.SKILL_NAME, Message.CANCEL_SPEECH_TEXT)
                .withShouldEndSession(true)
                .build()

    }

}
