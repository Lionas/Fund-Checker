package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response

import java.util.Optional

import com.amazon.ask.request.Predicates.intentName

class HelpIntentHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(intentName("AMAZON.HelpIntent"))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Const.HELP_SPEECH_TEXT)
                .withSimpleCard(Const.SKILL_NAME, Const.HELP_SPEECH_TEXT)
                .withReprompt(Const.HELP_SPEECH_TEXT)
                .build()
    }

}
