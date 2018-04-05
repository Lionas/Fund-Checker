package jp.lionas.alexa.fundra;

class Const {

    // メッセージ
    static final String SKILL_NAME = "Fundra";
    static final String LAUNCH_SPEECH_TEXT = "ファンドラへようこそ。調べたいファンド名の後に「知りたい」と話してください。";
    static final String HELP_SPEECH_TEXT =
            "調べたいファンド名の後に「知りたい」と話してください。例えば、日経225が知りたい。のように話してください。";
    static final String CANCEL_SPEECH_TEXT = "またのご利用をお待ちしております。";
    static final String NO_SPOKEN = "指定の銘柄の情報はありません";
    static final String PLUS = "プラス";
    static final String MINUS = "マイナス";
    static final String STATUS_MESSAGE = "%sの価格は%d円です。前日比%s%d円です。";
    static final String ERROR = "情報の取得に失敗しました。";
    static final String NOT_FOUND = "%sの情報はありません。";
    static final String FOUND_MULTIPLE = "全部で%d件の情報が見つかりました。最初の1件をお伝えします。";

    // 変数
    static final String INTENT_NAME = "FundraIntent";
    static final String QUERY_FUND_NAME = "queryFundName";
}
