package com.example.quiz;

public class Question {
    String question, answerA, answerB, answerC, correctAnswer;

    public Question(String question, String answerA, String answerB, String answerC, String correctAnswer) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.correctAnswer = correctAnswer;
    }
}