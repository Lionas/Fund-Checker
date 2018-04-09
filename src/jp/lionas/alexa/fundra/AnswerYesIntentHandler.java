package jp.lionas.alexa.fundra;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import jp.lionas.alexa.fundra.mufg.FundUtil;
import jp.lionas.alexa.fundra.mufg.MufgFund;
import jp.lionas.alexa.fundra.mufg.model.CodeItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class AnswerYesIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(Const.ASK_YES_INTENT_NAME)
                .and(sessionAttribute(Const.STATE_KEY, Const.STATE_REPEAT)));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        String fundName = (String) sessionAttributes.get(Const.FUND_NAME);
        List<CodeItem> found = MufgFund.findFund(fundName);

        // 複数マッチした場合
        return FundUtil.getRepeatedFunds(handlerInput, sessionAttributes, found);

    }


}
