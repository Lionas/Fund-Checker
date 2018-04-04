package jp.lionas.alexa.fundra;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech(Const.HELP_SPEECH_TEXT)
                .withSimpleCard(Const.SKILL_NAME, Const.HELP_SPEECH_TEXT)
                .withReprompt(Const.HELP_SPEECH_TEXT)
                .build();
    }

}
