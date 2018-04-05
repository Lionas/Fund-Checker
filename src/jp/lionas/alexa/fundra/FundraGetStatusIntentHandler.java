package jp.lionas.alexa.fundra;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import jp.lionas.alexa.fundra.mufg.MufgFund;
import jp.lionas.alexa.fundra.mufg.model.CodeItem;
import jp.lionas.alexa.fundra.mufg.model.FundItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FundraGetStatusIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Const.INTENT_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        String fundName = "";
        Map<String, Slot> slots = SlotUtil.getSlots(handlerInput);
        if(slots != null) {
            fundName = slots.get(Const.QUERY_FUND_NAME).getValue();
        }

        String speechText = getLatestFundStatuses(fundName);

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(Const.SKILL_NAME, speechText)
                .build();
    }

    private String getLatestFundStatuses(String fundName) {

        String result = "";
        final String NO_DATA =
                (fundName != null && !fundName.isEmpty()) ? String.format(Const.NOT_FOUND, fundName) : Const.NO_SPOKEN;

        try {
            List<CodeItem> found = MufgFund.findFund(fundName);
            if(found == null || found.size() == 0) {
                return NO_DATA;
            }

            // 複数マッチした場合
            if(found.size() > 1) {
                result = String.format(Const.FOUND_MULTIPLE, found.size());
            }

            CodeItem item = found.get(0);
            List<FundItem> funds = MufgFund.getLatestFund(item.getFundCode());
            if(funds == null || funds.isEmpty()) {
                return NO_DATA;
            }

            FundItem fundItem = funds.get(0);
            result += String.format(Const.STATUS_MESSAGE,
                    fundItem.getFundName(),
                    fundItem.getNav(),
                    fundItem.getCmpPrevDay() >= 0 ? Const.PLUS : Const.MINUS,
                    Math.abs(fundItem.getCmpPrevDay()));

        } catch (Exception e) {
            return Const.ERROR;
        }

        return result;

    }

}
