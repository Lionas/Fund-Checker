package jp.lionas.alexa.fundra.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.LaunchRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import jp.lionas.alexa.fundra.def.Message

import java.util.Optional

class LaunchRequestHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(Predicates.requestType(LaunchRequest::class.java))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Message.LAUNCH_SPEECH_TEXT)
                .withSimpleCard(Message.SKILL_NAME, Message.LAUNCH_SPEECH_TEXT)
                .withReprompt(Message.LAUNCH_SPEECH_TEXT)
                .withShouldEndSession(false)
                .build()
    }

}
