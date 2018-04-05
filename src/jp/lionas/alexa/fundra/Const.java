package jp.lionas.alexa.fundra;

public class Const {

    // メッセージ
    public static final String SKILL_NAME = "Fundra";
    static final String LAUNCH_SPEECH_TEXT = "ファンドラへようこそ。調べたいファンド名の後に「知りたい」と話してください。";
    static final String HELP_SPEECH_TEXT =
            "調べたいファンド名の後に「知りたい」と話してください。例えば、日経225が知りたい。のように話してください。";
    static final String CANCEL_SPEECH_TEXT = "またのご利用をお待ちしております。";
    static final String NO_SPOKEN = "指定の銘柄の情報はありません";
    public static final String PLUS = "プラス";
    public static final String MINUS = "マイナス";
    public static final String STATUS_MESSAGE = "%sの価格は%d円です。前日比%s%d円です。";
    public static final String ERROR = "情報の取得に失敗しました。";
    static final String NOT_FOUND = "%sの情報はありません。";
    public static final String FOUND_MULTIPLE = "全部で%d件の情報が見つかりました。最初の1件をお伝えします。";
    public static final String ASK_NEXT = "次の情報を確認しますか？";

    // TODO 後で別ファイルにする
    // インテント
    static final String START_INTENT_NAME = "StartIntent";
    static final String ASK_YES_INTENT_NAME = "AskYesIntent";
    static final String ASK_NO_INTENT_NAME = "AskNoIntent";

    // TODO
    // 状態
    public static final String STATE_KEY = "state";
    static final String STATE_START = "start";
    public static final String STATE_REPEAT = "repeat";
    public static final String STATE_END = "end";
    public static final String INDEX_KEY = "index";
    public static final String FUND_NAME = "fundName";

    // TODO 後で別ファイルにする
    // 変数
    static final String QUERY_FUND_NAME = "queryFundName";
}
