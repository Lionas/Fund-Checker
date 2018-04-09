package jp.lionas.alexa.fundra

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.model.SessionEndedRequest

import java.util.Optional

import com.amazon.ask.request.Predicates.requestType

class SessionEndedRequestHandler : RequestHandler {

    override fun canHandle(handlerInput: HandlerInput): Boolean {
        return handlerInput.matches(requestType(SessionEndedRequest::class.java))
    }

    override fun handle(handlerInput: HandlerInput): Optional<Response> {
        // any cleanup logic goes here
        return handlerInput.responseBuilder.build()
    }

}
