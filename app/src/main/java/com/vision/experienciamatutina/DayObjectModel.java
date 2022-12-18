package com.vision.experienciamatutina;

public class DayObjectModel {

    String date, title, verseContent, verse;
    int dayId;

    public DayObjectModel() {}

    public DayObjectModel(int dayId, String date, String title, String verseContent, String verse) {
        this.date = date;
        this.title = title;
        this.verseContent = verseContent;
        this.verse = verse;
        this.dayId = dayId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVerseContent() {
        return verseContent;
    }

    public void setVerseContent(String verseContent) {
        this.verseContent = verseContent;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }
}
