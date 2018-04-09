package jp.lionas.alexa.fundra;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class AnswerNoIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(Const.ASK_NO_INTENT_NAME)
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_END).negate()));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Const.STATE_KEY, Const.STATE_END);

        return handlerInput.getResponseBuilder()
                .withSpeech(Const.CANCEL_SPEECH_TEXT)
                .withSimpleCard(Const.SKILL_NAME, Const.CANCEL_SPEECH_TEXT)
                .withShouldEndSession(true)
                .build();

    }

}
