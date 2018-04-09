package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response

import java.util.Optional

import com.amazon.ask.request.Predicates.intentName
import jp.lionas.alexa.fundra.def.Message

class HelpIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName("AMAZON.HelpIntent"))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Message.HELP_SPEECH_TEXT)
                .withSimpleCard(Message.SKILL_NAME, Message.HELP_SPEECH_TEXT)
                .withReprompt(Message.HELP_SPEECH_TEXT)
                .build()
    }

}
