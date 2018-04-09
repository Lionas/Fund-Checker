package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.LaunchRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates

import java.util.Optional

class LaunchRequestHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(Predicates.requestType(LaunchRequest::class.java))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Const.LAUNCH_SPEECH_TEXT)
                .withSimpleCard(Const.SKILL_NAME, Const.LAUNCH_SPEECH_TEXT)
                .withReprompt(Const.LAUNCH_SPEECH_TEXT)
                .build()
    }

}
