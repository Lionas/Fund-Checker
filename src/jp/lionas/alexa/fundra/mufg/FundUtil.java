package jp.lionas.alexa.fundra.mufg;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.google.gson.Gson;
import jp.lionas.alexa.fundra.Const;
import jp.lionas.alexa.fundra.mufg.model.CodeItem;
import jp.lionas.alexa.fundra.mufg.model.FundItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FundUtil {

    private static String getLatestFundStatus(CodeItem item) {

        String result;

        try {
            List<FundItem> funds = MufgFund.getLatestFund(item.getFundCode());
            if(funds == null || funds.isEmpty()) {
                return Const.ERROR;
            }

            FundItem fundItem = funds.get(0);
            result = String.format(Const.STATUS_MESSAGE,
                    fundItem.getFundName(),
                    fundItem.getNav(),
                    fundItem.getCmpPrevDay() >= 0 ? Const.PLUS : Const.MINUS,
                    Math.abs(fundItem.getCmpPrevDay()));

        } catch (Exception e) {
            return Const.ERROR;
        }

        return result;

    }

    public static Optional<Response> getLastFund(HandlerInput handlerInput,
                                                 Map<String, Object> sessionAttributes,
                                                 CodeItem lastItem) {

        // 1件だけマッチした場合
        sessionAttributes.put(Const.STATE_KEY, Const.STATE_END);
        String speechText = FundUtil.getLatestFundStatus(lastItem);
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(Const.SKILL_NAME, speechText)
                .withShouldEndSession(true)
                .build();

    }

    public static Optional<Response> getRepeatedFunds(HandlerInput handlerInput,
                                                      Map<String, Object> sessionAttributes,
                                                      List<CodeItem> found) {

        String speechText = "";
        int targetIndex = (int) sessionAttributes.get(Const.INDEX_KEY);
        if(targetIndex == 0) {
            speechText += String.format(Const.FOUND_MULTIPLE, found.size());
        }
        sessionAttributes.put(Const.INDEX_KEY, targetIndex+1);

        if(targetIndex < found.size()-1) {
            // 最後の要素でない場合
            sessionAttributes.put(Const.STATE_KEY, Const.STATE_REPEAT);
            speechText += FundUtil.getLatestFundStatus(found.get(targetIndex));
            speechText += Const.ASK_NEXT;
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard(Const.SKILL_NAME, speechText)
                    .withReprompt(Const.ASK_NEXT)
                    .withShouldEndSession(false)
                    .build();
        } else {
            // 最後の要素の場合
            return getLastFund(handlerInput, sessionAttributes, found.get(targetIndex));
        }

    }

}
