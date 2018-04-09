package jp.lionas.alexa.fundra

class Const {

    companion object {

        // メッセージ
        internal const val SKILL_NAME = "Fundra"
        internal const val LAUNCH_SPEECH_TEXT = "ファンドラへようこそ。調べたいファンド名の後に「知りたい」と話してください。"
        internal const val HELP_SPEECH_TEXT = "調べたいファンド名の後に「知りたい」と話してください。例えば、日経225が知りたい。のように話してください。"
        internal const val CANCEL_SPEECH_TEXT = "またのご利用をお待ちしております。"
        internal const val NO_SPOKEN = "指定の銘柄の情報はありません"
        internal const val PLUS = "プラス"
        internal const val MINUS = "マイナス"
        internal const val STATUS_MESSAGE = "%sの価格は%d円です。前日比%s%d円です。"
        internal const val ERROR = "情報の取得に失敗しました。"
        internal const val NOT_FOUND = "%sの情報はありません。"
        internal const val FOUND_MULTIPLE = "全部で%d件の情報が見つかりました。最初の1件をお伝えします。"
        internal const val ASK_NEXT = "次の情報を確認しますか？"

        // TODO 後で別ファイルにする
        // インテント
        internal const val START_INTENT_NAME = "StartIntent"
        internal const val ANSWER_YES_INTENT_NAME = "AnswerYesIntent"
        internal const val ANSWER_NO_INTENT_NAME = "AnswerNoIntent"

        // TODO
        // 状態
        internal const val STATE_KEY = "state"
        internal const val STATE_START = "start"
        internal const val STATE_REPEAT = "repeat"
        internal const val STATE_END = "end"
        internal const val INDEX_KEY = "index"
        internal const val FUND_NAME = "fundName"

        // TODO 後で別ファイルにする
        // 変数
        internal const val QUERY_FUND_NAME = "queryFundName"

    }

}