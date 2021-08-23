package com.example.ib_project.data.starter

import kotlin.random.Random

class Start_Deep(

    val arrayOfDeepQuestions: Array<String> = arrayOf(
        "When was the last time you cried and why?",
        "What's something you disagree with about the way you were raised?",
        "What is a mistake people often make about you?",
        "what was the hardest decision you ever had to make?",
        "what thing are you most scared to tell your parents?",
        "What is something you think everyone should do at least once in their lives?",
        "What are you most looking forward to in the next 10 years?",
        "What is something you are certain you won't experience and why?",
        "if you had to change your name , what would you change it to?",
        "What do you believe in despite having no proof of it?",
        "What mistake do you keep making over and over again?",
        "What were the three biggest turning points in your life?",
        "What are the top three things on your bucket list?",
        "What chance encounter changed your life forever?",
        "What's the most illegal thing you've done?",
        "What's one thing you did that you wish you could go back and undo?",
        "What are you most worried about with the next generation?",
        "What's something you are self-conscious about?",
        "What are some of your personal rules that you refuse to break?",
        "What do you regret not doing when you were younger?",
        "What's the most surprising self-realization you've had?",
        "How do you get in the way of your own success?",
        "In what way do you feel your childhood was happier than other people's?",
        "When you die, what do you want to be remembered for?",
        "What stereotype do you completely live up to?"
    )

) {
    fun getRandomQuestion(): String {
        val randomValue = Random.nextInt(arrayOfDeepQuestions.size)
        //return arrayOfDeepQuestions[randomValue]
        return arrayOfDeepQuestions.get(randomValue)
    }
}