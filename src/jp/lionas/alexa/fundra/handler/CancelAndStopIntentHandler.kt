package jp.lionas.alexa.fundra.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName
import jp.lionas.alexa.fundra.def.Message
import java.util.*

class CancelAndStopIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName("AMAZON.StopIntent")
                .or(intentName("AMAZON.CancelIntent")))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Message.CANCEL_SPEECH_TEXT)
                .withSimpleCard(Message.SKILL_NAME, Message.CANCEL_SPEECH_TEXT)
                .build()
    }

}
