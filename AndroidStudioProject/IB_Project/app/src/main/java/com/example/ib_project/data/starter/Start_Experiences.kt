package com.example.ib_project.data.starter

import kotlin.random.Random

class Start_Experiences(

    val arrayOfExperienceQuestions: Array<String> = arrayOf(
        "What was the worst haircut you've ever had?",
        "What's the best thing about the opposite gender?",
        "What's the best compliment you've ever received?",
        "What's the best advice you've ever received?",
        "Describe your worst date ever.",
        "What's the worst job you've ever had?",
        "What's the best job you ever had?",
        "What's the best date you've ever been on?",
        "What's state or country do you never want to go back to?",
        "what's your dream car?",
        "What have you bought that you love so much you would happily buy it again?",
        "What is your worst habit?",
        "What is something you will NEVER do again?",
        "What's one of your pet peeves?",
        "Who's the worst boss you've ever had and why?",
        "What's the first concert you ever went to?",
        "What's the first music you bought?",
        "What was your first car?",
        "Who was your first love and when?",
        "What' your most powerful and vivid memory",
        "What's the most embarassing thing that's happened to you during a date?",
        "What's something you did as a child that your parent still retell the story about?",
        "How did you meet your best friend?",
        "What's the wildest party you've ever been to?",
        "What opportunity for love or money have you given up? Do you regret it now?"

        )
) {
    fun getRandomQuestion(): String {
        val randomValue = Random.nextInt(arrayOfExperienceQuestions.size)
        //return arrayOfDeepQuestions[randomValue]
        return arrayOfExperienceQuestions.get(randomValue)
    }
}