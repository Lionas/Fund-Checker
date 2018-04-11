package jp.lionas.alexa.fundra.util

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Slot

internal class SlotUtil {

    companion object {

        @JvmStatic
        fun getSlots(handlerInput: HandlerInput): MutableMap<String, Slot>? {

            val request = handlerInput.requestEnvelope.request
            val intentRequest = request as IntentRequest?
            val intent = intentRequest?.intent

            return intent?.slots

        }

    }

}
