package jp.lionas.alexa.fundra;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.google.gson.Gson;
import jp.lionas.alexa.fundra.mufg.FundUtil;
import jp.lionas.alexa.fundra.mufg.model.CodeItem;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class AskYesIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(Const.ASK_YES_INTENT_NAME)
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_REPEAT)));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        Gson gson = new Gson();
        CodeItem[] found = gson.fromJson((String)sessionAttributes.get(Const.FOUND_ITEMS_KEY), CodeItem[].class);
        List<CodeItem> foundList = Arrays.asList(found);

        // 複数マッチした場合
        return FundUtil.getRepeatedFunds(handlerInput, sessionAttributes, foundList);

    }


}
