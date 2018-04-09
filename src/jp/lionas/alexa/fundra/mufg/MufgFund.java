package jp.lionas.alexa.fundra.mufg;

import com.google.gson.Gson;
import jp.lionas.alexa.fundra.mufg.model.CodeItem;
import jp.lionas.alexa.fundra.mufg.model.CodeList;
import jp.lionas.alexa.fundra.mufg.model.FundItem;
import jp.lionas.alexa.fundra.mufg.model.LatestFund;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class MufgFund {

    private static final String BASE_URL = "http://developer.am.mufg.jp/";

    public static List<FundItem> getLatestFund(String fundCode) throws Exception {

        List<FundItem> result = null;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "fund_information_latest/fund_cd/" + fundCode)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                Gson gson = new Gson();
                LatestFund data = gson.fromJson(body.string(), LatestFund.class);
                result = data.getValue();
            }
        }

        return result;

    }

    private static List<CodeItem> getCodeList() {

        List<CodeItem> result = null;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "code_list")
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                Gson gson = new Gson();
                CodeList list = gson.fromJson(body.string(), CodeList.class);
                result = list.getValue();
            }
        } catch (Exception e) {
            return null;
        }

        return result;

    }

    public static List<CodeItem> findFund(String name) {

        List<CodeItem> result = new ArrayList<>();
        List<CodeItem> items = getCodeList();

        if (items != null && !items.isEmpty()) {
            for (CodeItem item : items) {
                String fundName = item.getFundName()
                        .replaceAll("[？！”＃＄％＆’（）・＜＞＿｜〜＋ー＊＝＾￥：；「」｛｝、。]", "");
                if (fundName.contains(name)) {
                    result.add(item);
                }
            }
        }

        return result;

    }

}
