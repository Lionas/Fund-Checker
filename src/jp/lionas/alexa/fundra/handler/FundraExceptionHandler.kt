package jp.lionas.alexa.fundra.handler

import com.amazon.ask.dispatcher.exception.ExceptionHandler
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Response

import java.util.Optional

import jp.lionas.alexa.fundra.def.Message

class FundraExceptionHandler : ExceptionHandler {

    override fun canHandle(handlerInput: HandlerInput, e: Throwable): Boolean {
        return true
    }

    override fun handle(handlerInput: HandlerInput, e: Throwable): Optional<Response> {
        return handlerInput.responseBuilder
                .withSpeech(Message.CAN_NOT_UNDERSTAND_TEXT)
                .withSimpleCard(Message.SKILL_NAME, Message.CAN_NOT_UNDERSTAND_TEXT)
                .withReprompt(Message.CAN_NOT_UNDERSTAND_TEXT)
                .withShouldEndSession(false)
                .build()
    }

}
