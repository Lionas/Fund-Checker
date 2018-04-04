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

public class FundraIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("FundraIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        String spoken = "";
        if(slots != null) {
            spoken = slots.get("name").getValue();
        }

        String speechText = getLatestFundStatuses(spoken);

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .build();
    }

    private String getLatestFundStatuses(String spoken) {

        StringBuilder result = new StringBuilder();
        final String NO_DATA =
                (spoken != null && !spoken.isEmpty()) ? spoken + "の情報はありません" : "指定の銘柄の情報はありません";

        try {
            List<CodeItem> found = MufgFund.findFund(spoken);
            if(found == null || found.size() == 0) {
                return NO_DATA;
            }

            // TODO 複数マッチした場合の対応を考える
            CodeItem item = found.get(0);
            List<FundItem> funds = MufgFund.getLatestFund(item.getFundCode());
            if(funds == null || funds.isEmpty()) {
                return NO_DATA;
            }

            FundItem fundItem = funds.get(0);
            result.append(fundItem.getFundName())
                    .append("の価格は")
                    .append(fundItem.getNav())
                    .append("円です。")
                    .append("前日比")
                    .append(fundItem.getCmpPrevDay() >= 0 ? "プラス" : "マイナス")
                    .append(Math.abs(fundItem.getCmpPrevDay()))
                    .append("円です。");

        } catch (Exception e) {
            return "情報の取得に失敗しました。";
        }

        return result.toString();

    }

}
