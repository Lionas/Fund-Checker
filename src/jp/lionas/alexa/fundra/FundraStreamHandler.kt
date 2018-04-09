package jp.lionas.alexa.fundra

import com.amazon.ask.Skill
import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills

class FundraStreamHandler() : SkillStreamHandler(skill) {

    companion object {
        val skill: Skill
            get() = Skills.standard()
                    .addRequestHandlers(
                            CancelAndStopIntentHandler(),
                            StartIntentHandler(),
                            AnswerYesIntentHandler(),
                            AnswerNoIntentHandler(),
                            HelpIntentHandler(),
                            LaunchRequestHandler(),
                            SessionEndedRequestHandler())
                    .build()
    }

}
