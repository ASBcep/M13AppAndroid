package com.example.mnactecapp;

public class Question {

    private String question;
    private String[] options;
    private String correctOption;


    public Question(String question, String[] options, String correctOption){
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String[] getOptions() {
        return options;
    }
    public String getQuestion() {
        return question;
    }
    public String getCorrectOption() {
        return correctOption;
    }
}
