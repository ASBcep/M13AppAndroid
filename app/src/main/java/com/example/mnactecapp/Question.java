package com.example.mnactecapp;

public class Question {

    private String question;
    private String[] options;
    private char correctOption;


    public Question(String question, String[] options, char correctOption){
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String[] getOpciones() {
        return options;
    }
    public String getQuestion() {
        return question;
    }
    public char getCorrectOption() {
        return correctOption;
    }
}
