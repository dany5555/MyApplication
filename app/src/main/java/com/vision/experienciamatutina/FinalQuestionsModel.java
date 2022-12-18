package com.vision.experienciamatutina;

public class FinalQuestionsModel {

    String question, answerOption1, answerOption2, answerOption3, answerOption4;

    public FinalQuestionsModel(){}

    public FinalQuestionsModel(String question, String answerOption1) {
        this.question = question;
        this.answerOption1 = answerOption1;
    }

    public FinalQuestionsModel(String question, String answerOption1, String answerOption2, String answerOption3, String answerOption4) {
        this.question = question;
        this.answerOption1 = answerOption1;
        this.answerOption2 = answerOption2;
        this.answerOption3 = answerOption3;
        this.answerOption4 = answerOption4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOption1() {
        return answerOption1;
    }

    public void setAnswerOption1(String answerOption1) {
        this.answerOption1 = answerOption1;
    }

    public String getAnswerOption2() {
        return answerOption2;
    }

    public void setAnswerOption2(String answerOption2) {
        this.answerOption2 = answerOption2;
    }

    public String getAnswerOption3() {
        return answerOption3;
    }

    public void setAnswerOption3(String answerOption3) {
        this.answerOption3 = answerOption3;
    }

    public String getAnswerOption4() {
        return answerOption4;
    }

    public void setAnswerOption4(String answerOption4) {
        this.answerOption4 = answerOption4;
    }
}
