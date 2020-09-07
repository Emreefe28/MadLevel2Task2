package com.example.madlevel2task2

data class Question(val question: String, val correct: Boolean) {

    companion object {
        val QUESTIONS = arrayOf(
            "A val and var are the same",
            "Mobile Application Development grants 12 ECTS.",
            "A unit in Kotlin corresponds to a void in Java",
            "In Kotlin when replaces the switch operator in java",
            "This is false",
            "This is true"
        )

        val CORRECT = arrayOf(false, true, false, true, false, true)
    }
}