package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName
import java.util.*

class CancelandStopIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Const.CANCEL_SPEECH_TEXT)
                .withSimpleCard(Const.SKILL_NAME, Const.CANCEL_SPEECH_TEXT)
                .build()
    }

}
