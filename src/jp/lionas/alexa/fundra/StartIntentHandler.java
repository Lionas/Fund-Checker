package jp.lionas.alexa.fundra;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import jp.lionas.alexa.fundra.mufg.FundUtil;
import jp.lionas.alexa.fundra.mufg.MufgFund;
import jp.lionas.alexa.fundra.mufg.model.CodeItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class StartIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(Const.START_INTENT_NAME)
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_START).negate())
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_END).negate())) ||
                handlerInput.matches(intentName("AMAZON.StartOverIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Const.STATE_KEY, Const.STATE_START);
        sessionAttributes.put(Const.INDEX_KEY, 0);

        String fundName = "";
        Map<String, Slot> slots = SlotUtil.getSlots(handlerInput);
        if(slots != null) {
            fundName = slots.get(Const.QUERY_FUND_NAME).getValue();
        }

        List<CodeItem> found = MufgFund.findFund(fundName);
        final String NO_DATA =
                (fundName != null && !fundName.isEmpty()) ? String.format(Const.NOT_FOUND, fundName) : Const.NO_SPOKEN;

        if(found == null || found.size() == 0) {
            // データが0件
            sessionAttributes.put(Const.STATE_KEY, Const.STATE_END);
            return handlerInput.getResponseBuilder()
                    .withSpeech(NO_DATA)
                    .withSimpleCard(Const.SKILL_NAME, NO_DATA)
                    .build();
        } else if(found.size() == 1) {
            // 1件だけマッチした場合
            return FundUtil.getLastFund(handlerInput, sessionAttributes, found.get(0));
        } else {
            // 複数マッチした場合
            return FundUtil.getRepeatedFunds(handlerInput, sessionAttributes, found);
        }

    }

}
