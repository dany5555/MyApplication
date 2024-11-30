package com.vision.experienciamatutina;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AdminActivity extends AppCompatActivity {

    Button generate1RCompleteExamButton, generate2RCompleteExamButton, generate3RCompleteExamButton, generateFinalRoundExamButton, generateFRCompleteExamButton;
    Button generate1RAExamButton, generate2RAExamButton, generate3RAExamButton, generateFRAExamButton;
    Button generate1RBExamButton, generate2RBExamButton, generate3RBExamButton, generateFRBExamButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference firstRoundExamRef = database.getReference("Exam Round 1");
    DatabaseReference secondRoundExamRef = database.getReference("Exam Round 2");
    DatabaseReference thirdRoundExamRef = database.getReference("Exam Round 3");
    DatabaseReference finalRoundExamRef = database.getReference("Exam Final Round");
    DatabaseReference examRefFinal = database.getReference("Exam Final Round");

    DatabaseReference exam1RARef = database.getReference("Exam Round 1 Part A");
    DatabaseReference exam2RARef = database.getReference("Exam Round 2 Part A");
    DatabaseReference exam3RARef = database.getReference("Exam Round 3 Part A");
    DatabaseReference examFRARef = database.getReference("Exam Final Round Part A");
    DatabaseReference exam1RBRef = database.getReference("Exam Round 1 Part B");
    DatabaseReference exam2RBRef = database.getReference("Exam Round 2 Part B");
    DatabaseReference exam3RBRef = database.getReference("Exam Round 3 Part B");
    DatabaseReference examFRBRef = database.getReference("Exam Final Round Part B");

    ArrayList<String> datesList = new ArrayList<>();
    ArrayList<String> titlesList = new ArrayList<>();
    ArrayList<String> verseContentsList = new ArrayList<>();
    ArrayList<String> versesList = new ArrayList<>();
    ArrayList<String> questionTypeList = new ArrayList<>();
    ArrayList<String> randomDataList = new ArrayList<>();
    ArrayList<Integer> dayIdsList = new ArrayList<>();
    ArrayList<Integer> randomNumbers = new ArrayList<>();
    ArrayList<QuestionModel> tempQuestionList = new ArrayList<>();


    ArrayList<DayObjectModel> dayObjectList = new ArrayList<>();

    public static ArrayList<QuestionModel> finalQuestionsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        generate1RCompleteExamButton = findViewById(R.id.generate_1R_exam_button);
        generate2RCompleteExamButton = findViewById(R.id.generate_2R_exam_button);
        generate3RCompleteExamButton = findViewById(R.id.generate_3R_exam_button);
        generateFRCompleteExamButton = findViewById(R.id.generate_FR_exam_button);

        generate1RAExamButton = findViewById(R.id.generate_1RA_exam_button);
        generate2RAExamButton = findViewById(R.id.generate_2RA_exam_button);
        generate3RAExamButton = findViewById(R.id.generate_3RA_exam_button);
        generateFRAExamButton = findViewById(R.id.generate_FRA_exam_button);

        generate1RBExamButton = findViewById(R.id.generate_1RB_exam_button);
        generate2RBExamButton = findViewById(R.id.generate_2RB_exam_button);
        generate3RBExamButton = findViewById(R.id.generate_3RB_exam_button);
        generateFRBExamButton = findViewById(R.id.generate_FRB_exam_button);

        generate1RAExamButton.setOnClickListener(view -> {

            getDates1RAList();
            getTitles1RAList();
            getVerseContents1RAList();
            getVerses1RAList();
            getDayIds();
            getQuestionType4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    exam1RARef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    exam1RARef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generate2RAExamButton.setOnClickListener(view -> {

            getDates2RAList();
            getTitles2RAList();
            getVerseContents2RAList();
            getVerses2RAList();
            getDayIds();
            getQuestionType4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    exam2RARef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    exam2RARef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generate3RAExamButton.setOnClickListener(view -> {

            getDates3RAList();
            getTitles3RAList();
            getVerseContents3RAList();
            getVerses3RAList();
            getDayIds();
            getQuestionType4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    exam3RARef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    exam3RARef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generateFRAExamButton.setOnClickListener(view -> {

            getDatesFRAList();
            getTitlesFRAList();
            getVerseContentsFRAList();
            getVersesFRAList();
            getDayIds();
            getQuestionLevel4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            //Collections.shuffle(dayIdsList);

            for (int i = 0; i < dayIdsList.size(); i++) {
                generateLevel1Question(i);
                generateLevel2Question(i);
                generateLevel3Question(i);
            }

            Log.e("lol", "size: " + questionTypeList.size());

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examFRARef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examFRARef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });

        generate1RBExamButton.setOnClickListener(view -> {

            getDates1RBList();
            getTitles1RBList();
            getVerseContents1RBList();
            getVerses1RBList();
            getDayIds();
            getQuestionType3DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 3);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    exam1RBRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    exam1RBRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generate2RBExamButton.setOnClickListener(view -> {

            getDates2RBList();
            getTitles2RBList();
            getVerseContents2RBList();
            getVerses2RBList();
            getDayIds();
            getQuestionType4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    exam2RBRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    exam2RBRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generate3RBExamButton.setOnClickListener(view -> {

            getDates3RBList();
            getTitles3RBList();
            getVerseContents3RBList();
            getVerses3RBList();
            getDayIds();
            getQuestionType4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    exam3RBRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    exam3RBRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generateFRBExamButton.setOnClickListener(view -> {

            getDatesFRBList();
            getTitlesFRBList();
            getVerseContentsFRBList();
            getVersesFRBList();
            getDayIds();
            getQuestionLevel4DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            //Collections.shuffle(dayIdsList);

            for (int i = 0; i < dayIdsList.size(); i++) {
                generateLevel1Question(i);
                generateLevel2Question(i);
                generateLevel3Question(i);
            }

            Log.e("lol", "size: " + questionTypeList.size());

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examFRBRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examFRBRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });

        generate1RCompleteExamButton.setOnClickListener(view -> {

            getDates1RList();
            getTitles1RList();
            getVerseContents1RList();
            getVerses1RList();
            getDayIds();
            getQuestionTypeComplete7DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    firstRoundExamRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    firstRoundExamRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generate2RCompleteExamButton.setOnClickListener(view -> {

            getDates2RList();
            getTitles2RList();
            getVerseContents2RList();
            getVerses2RList();
            getDayIds();
            getQuestionTypeComplete8DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 3 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    secondRoundExamRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    secondRoundExamRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });

        generate3RCompleteExamButton.setOnClickListener(view -> {

            getDates3RList();
            getTitles3RList();
            getVerseContents3RList();
            getVerses3RList();
            getDayIds();
            getQuestionTypeComplete8DayList();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 2 questions only per every day.
                for (int j = 0; j < 3; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i), 4);
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    thirdRoundExamRef.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    thirdRoundExamRef.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generateFRCompleteExamButton.setOnClickListener(view -> {

            getDatesFRList();
            getTitlesFRList();
            getVerseContentsFRList();
            getVersesFRList();
            getDayIds();
            getQuestionLevelFinal();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            //Collections.shuffle(dayIdsList);

            for (int i = 0; i < dayIdsList.size(); i++) {
                generateLevel1Question(i);
                generateLevel2Question(i);
                generateLevel3Question(i);
            }

            Log.e("lol", "size: " + questionTypeList.size());

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examRefFinal.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examRefFinal.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });


    }

    public ArrayList<String> getQuestionTypeComplete7DayList() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");


        return questionTypeList;
    }

    public ArrayList<String> getQuestionTypeComplete8DayList() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");

        return questionTypeList;
    }

    public ArrayList<String> getQuestionTypeQualificationPhase() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");


        return questionTypeList;
    }

    public ArrayList<String> getQuestionType3DayList() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");

        return questionTypeList;
    }

    public ArrayList<String> getQuestionType4DayList() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("FB");

        return questionTypeList;
    }

    public ArrayList<String> getQuestionTypeSemiFinal() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        return questionTypeList;
    }

    public ArrayList<String> getQuestionLevelFinal() {
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");

        return questionTypeList;
    }

    public ArrayList<String> getQuestionLevel4DayList() {
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");

        return questionTypeList;
    }

    public ArrayList<String> getQuestionLevel3DayList() {
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");

        return questionTypeList;
    }


    public ArrayList<String> getDates1RList() {
        datesList.add("1 de Octubre");
        datesList.add("2 de Octubre");
        datesList.add("3 de Octubre");
        datesList.add("4 de Octubre");
        datesList.add("5 de Octubre");
        datesList.add("6 de Octubre");
        datesList.add("7 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates2RList() {
        datesList.add("8 de Octubre");
        datesList.add("9 de Octubre");
        datesList.add("10 de Octubre");
        datesList.add("11 de Octubre");
        datesList.add("12 de Octubre");
        datesList.add("13 de Octubre");
        datesList.add("14 de Octubre");
        datesList.add("15 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates3RList() {
        datesList.add("16 de Octubre");
        datesList.add("17 de Octubre");
        datesList.add("18 de Octubre");
        datesList.add("19 de Octubre");
        datesList.add("20 de Octubre");
        datesList.add("21 de Octubre");
        datesList.add("22 de Octubre");
        datesList.add("23 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDatesFRList() {
        datesList.add("24 de Octubre");
        datesList.add("25 de Octubre");
        datesList.add("26 de Octubre");
        datesList.add("27 de Octubre");
        datesList.add("28 de Octubre");
        datesList.add("29 de Octubre");
        datesList.add("30 de Octubre");
        datesList.add("31 de Octubre");

        return datesList;
    }

    public ArrayList<String> getTitles1RList() {
        titlesList.add("\"Las virutas de lápiz\"");
        titlesList.add("\"El convertidor catalítico\"");
        titlesList.add("\"Un resaltador\"");
        titlesList.add("\"Una unidad de memoria USB\"");
        titlesList.add("\"La tuba\"");
        titlesList.add("\"Un foco fluorescente\"");
        titlesList.add("\"Una antena parabólica\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RList() {
        titlesList.add("\"Un tatuaje\"");
        titlesList.add("\"Los jeans\"");
        titlesList.add("\"Una tetera\"");
        titlesList.add("\"Una madeja de lana\"");
        titlesList.add("\"Una banqueta\"");
        titlesList.add("\"La licencia de conducir\"");
        titlesList.add("\"Un cartucho de tinta\"");
        titlesList.add("\"Un palito de paleta\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RList() {
        titlesList.add("\"Una bandeja de comida\"");
        titlesList.add("\"Las astas del alce\"");
        titlesList.add("\"Un desatascador de inodoro\"");
        titlesList.add("\"El espaciador\"");
        titlesList.add("\"Un apósito\"");
        titlesList.add("\"El fleco de una bufanda\"");
        titlesList.add("\"Una bolsa de dormir\"");
        titlesList.add("\"La pantimedias\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFRList() {
        titlesList.add("\"Un soplador de hojas\"");
        titlesList.add("\"Un suéter que pica\"");
        titlesList.add("\"Las pompones de las porristas\"");
        titlesList.add("\"Los Legos\"");
        titlesList.add("\"El flash de la cámara fotográfica\"");
        titlesList.add("\"Un inhalador\"");
        titlesList.add("\"Una navaja\"");
        titlesList.add("\"Una calabaza de Halloween\"");

        return titlesList;
    }

    public ArrayList<String> getVerseContents1RList() {
        verseContentsList.add("«Todo pámpano que en mí no lleva fruto, lo quitará; y todo aquel que lleva fruto, lo limpiará, para que lleve más fruto»");
        verseContentsList.add("«Dios es el que nos mantiene firmes en Cristo, tanto a nosotros como a ustedes. Él nos ungió, nos selló como propiedad suya y puso su Espíritu en nuestro corazón como garantía de sus promesas»");
        verseContentsList.add("«Respondiendo Jesús, le dijo: Marta, Marta, afanada y turbada estás con muchas cosas. Pero solo una cosa es necesaria; y María ha escogido la buena parte, la cual no le será quitada»");
        verseContentsList.add("«Echa sobre Jehová tu carga, y él te sustentará; no dejará para siempre caído al justo»");
        verseContentsList.add("«En su bondad, Dios los llamó a ustedes a que participen de su gloria eterna por medio de Cristo Jesús. Entonces, después de que hayan sufrido un poco de tiempo, él los restaurará, los sostendrá, los fortalecerá y los afirmará sobre un fundamento sólido»");
        verseContentsList.add("«Porque somos hechura suya, creados en Cristo Jesús para buenas obras, las cuales Dios preparó de antemano para que anduviésemos en ellas»");
        verseContentsList.add("«Y Pablo, como acostumbraba, fue a ellos, y por tres días de reposo discutió con ellos»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RList() {
        verseContentsList.add("«Después miré, y he aquí el Cordero estaba en pie sobre el monte de Sion, y con él ciento cuarenta y cuatro mil, que tenían el nombre de él y el de su Padre escrito en la frente»");
        verseContentsList.add("«Harás el manto del efod todo de azul; y en medio de él por arriba habrá una abertura, la cual tendrá un borde alrededor de obra tejida, como el cuello de un coselete, para que no se rompa»");
        verseContentsList.add("«Serán luego vueltos atrás mis enemigos, el día en que yo clamare; esto sé, que Dios está por mí»");
        verseContentsList.add("«Cualquiera, pues, que me oye estas palabras, y las hace, le compararé a un hombre prudente, que edificó su casa sobre la roca»");
        verseContentsList.add("«Así que, si tu enemigo tuviere hambre, dale de comer; si tuviere sed, dale de beber; pues haciendo esto, ascuas de fuego amontonarás sobre su cabeza»");
        verseContentsList.add("«Porque vosotros, hermanos, a libertad fuisteis llamados; solamente que no uséis la libertad como ocasión para la carne, sino servíos por amor los unos a los otros»");
        verseContentsList.add("«Por lo cual, teniendo nosotros este ministerio según la misericordia que hemos recibido, no desmayamos»");
        verseContentsList.add("«Al que responde palabra antes de oír, le es fatuidad y oprobio»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RList() {
        verseContentsList.add("«La sabiduría del prudente es discernir sus caminos, pero al necio lo engaña su propia necedad»");
        verseContentsList.add("«No os unáis en yugo desigual con los incrédulos; porque ¿qué compañerismo tiene la justicia con la injusticia? ¿Y qué comunión la luz con las tinieblas?»");
        verseContentsList.add("«Entonces Jehová dijo a Moisés: ¿Por qué clamas a mí? Di a los hijos de Israel que marchen»");
        verseContentsList.add("«En aquellos días él fue al monte a orar, y pasó la noche orando a Dios»");
        verseContentsList.add("«Quítense de vosotros toda amargura, enojo, ira, gritería y maledicencia, y toda malicia. Antes sed benignos unos con otros, misericordiosos, perdonándoos unos a otros, como Dios también os perdonó a vosotros en Cristo»");
        verseContentsList.add("«No se amolden al mundo actual...»");
        verseContentsList.add("«Muchos dolores habrá para el impío; mas al que espera en Jehová, le rodea la misericordia»");
        verseContentsList.add("«El ojo del adúltero está aguardando la noche, diciendo: No me verá nadie; y esconde su rostro»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFRList() {
        verseContentsList.add("«Pero hágase todo decentemente y con orden»");
        verseContentsList.add("«...El hombre mira lo que está delante de sus ojos, pero Jehová mira el corazón»");
        verseContentsList.add("«Entonces una mujer de la ciudad, que era pecadora, al saber que Jesús estaba a la mesa en casa del fariseo, trajo un frasco de alabastro con perfume; y estando detrás de él a sus pies, llorando, comenzó a regar con lágrimas sus pies, y los enjugaba con sus cabellos; y besaba sus pies, y los ungía con el perfume»");
        verseContentsList.add("«Por lo cual, animaos unos a otros, y edificaos unos a otros, así como lo hacéis»");
        verseContentsList.add("«La luz en las tinieblas resplandece, y las tinieblas no prevalecieron contra ella»");
        verseContentsList.add("«Esta es, pues, la parábola: La semilla es la palabra de Dios… Mas la que cayó en buena tierra, estos son los que con corazón bueno y recto retienen la palabra oída, y dan fruto con perseverancia»");
        verseContentsList.add("«Toda la Escritura es inspirada por Dios, y útil para enseñar, para redargüir, para corregir, para instruir en justicia,  a fin de que el hombre de Dios sea perfecto, enteramente preparado para toda buena obra»");
        verseContentsList.add("«Yo me fui llena, pero Jehová me ha vuelto con las manos vacías. ¿Por qué me llamaréis Noemí, ya que Jehová ha dado testimonio contra mí, y el Todopoderoso me ha afligido?»");

        return verseContentsList;
    }

    public ArrayList<String> getVerses1RList() {
        versesList.add("\"Juan 15: 2\"");
        versesList.add("\"1 Corintios 1: 21, 22, NVI\"");
        versesList.add("\"Lucas 10: 41, 42\"");
        versesList.add("\"Salmos 55: 22\"");
        versesList.add("\"1 Pedro 5: 10, NTV\"");
        versesList.add("\"Efesios  2: 10\"");
        versesList.add("\"Hechos  17: 2\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RList() {
        versesList.add("\"Apocalipsis 14: 1\"");
        versesList.add("\"Éxodo 28: 31, 32\"");
        versesList.add("\"Salmos 56: 9\"");
        versesList.add("\"Mateo 7: 24\"");
        versesList.add("\"Romanos 12: 20\"");
        versesList.add("\"Gálatas 5: 13\"");
        versesList.add("\"2 Corintios 4: 1\"");
        versesList.add("\"Proverbios 18: 13\"");

        return versesList;
    }

    public ArrayList<String> getVerses3RList() {
        versesList.add("\"Proverbios 14: 8, NVI\"");
        versesList.add("\"2 Corintios 6: 14\"");
        versesList.add("\"Éxodo 14: 15\"");
        versesList.add("\"Lucas 6: 12\"");
        versesList.add("\"Efesios 4: 31, 32\"");
        versesList.add("\"Romanos 12: 2, NVI\"");
        versesList.add("\"Salmos 32: 10\"");
        versesList.add("\"Job 24: 15\"");

        return versesList;
    }

    public ArrayList<String> getVersesFRList() {
        versesList.add("\"1 Corintios 14: 40\"");
        versesList.add("\"1 Samuel 16: 7\"");
        versesList.add("\"Lucas 7: 37, 38\"");
        versesList.add("\"1 Tesalonicenses 5: 11\"");
        versesList.add("\"Juan 1: 5\"");
        versesList.add("\"Lucas 8: 11, 15\"");
        versesList.add("\"2 Timoteo 3: 16, 17\"");
        versesList.add("\"Rut 1: 21\"");

        return versesList;
    }

    public ArrayList<String> getDates1RAList() {
        datesList.add("3 de Octubre");
        datesList.add("4 de Octubre");
        datesList.add("6 de Octubre");
        datesList.add("7 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates1RBList() {
        datesList.add("1 de Octubre");
        datesList.add("2 de Octubre");
        datesList.add("5 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates2RAList() {
        datesList.add("8 de Octubre");
        datesList.add("9 de Octubre");
        datesList.add("11 de Octubre");
        datesList.add("15 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates2RBList() {
        datesList.add("10 de Octubre");
        datesList.add("12 de Octubre");
        datesList.add("13 de Octubre");
        datesList.add("14 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates3RAList() {
        datesList.add("19 de Octubre");
        datesList.add("21 de Octubre");
        datesList.add("22 de Octubre");
        datesList.add("23 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates3RBList() {
        datesList.add("16 de Octubre");
        datesList.add("17 de Octubre");
        datesList.add("18 de Octubre");
        datesList.add("20 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDatesFRAList() {
        datesList.add("24 de Octubre");
        datesList.add("26 de Octubre");
        datesList.add("28 de Octubre");
        datesList.add("30 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDatesFRBList() {
        datesList.add("25 de Octubre");
        datesList.add("27 de Octubre");
        datesList.add("29 de Octubre");
        datesList.add("31 de Octubre");

        return datesList;
    }

    public ArrayList<String> getTitles1RAList() {
        titlesList.add("\"Y esto, ¿cómo se puede arreglar?\"");
        titlesList.add("\"Dietas y coronas\"");
        titlesList.add("\"Renuncio\"");
        titlesList.add("\"Arremangados\"");

        return titlesList;
    }

    public ArrayList<String> getTitles1RBList() {
        titlesList.add("\"Todos funambulistas\"");
        titlesList.add("\"Por puro placer\"");
        titlesList.add("\"Las fronteras de la religión\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RAList() {
        titlesList.add("\"Caducidad necesaria\"");
        titlesList.add("\"No hay 'que' que valga\"");
        titlesList.add("\"Leer entre líneas\"");
        titlesList.add("\"Nada y todo\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RBList() {
        titlesList.add("\"Síntesis de síntesis\"");
        titlesList.add("\"Kong Nyong\"");
        titlesList.add("\"¿Ejemplos?\"");
        titlesList.add("\"Igual de persona\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RAList() {
        titlesList.add("\"A su i-magen\"");
        titlesList.add("\"'El Avaro', de Moliére\"");
        titlesList.add("\"Efemérides\"");
        titlesList.add("\"Casi de oro puro\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RBList() {
        titlesList.add("\"La mejor inversión\"");
        titlesList.add("\"Sin dinero pero no sin coste\"");
        titlesList.add("\"Sin olvidar las pelotas de golf\"");
        titlesList.add("\"Ahora me encargo yo\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFRAList() {
        titlesList.add("\"Término medio\"");
        titlesList.add("\"'Bon appétit'\"");
        titlesList.add("\"Colache\"");
        titlesList.add("\"En línea recta\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFRBList() {
        titlesList.add("\"El precio justo\"");
        titlesList.add("\"Discromatopsia\"");
        titlesList.add("\"La mirada del ángel\"");
        titlesList.add("\"Increíble\"");

        return titlesList;
    }

    public ArrayList<String> getVerseContents1RAList() {
        verseContentsList.add("«Pero al disertar Pablo acerca de la justicia, del dominio propio y del juicio venidero, Félix se espantó y dijo: Ahora vete, y cuando tenga oportunidad, te llamaré»");
        verseContentsList.add("«Todo aquel que lucha, de todo se abstiene; ellos, a la verdad, para recibir una corona incorruptible, pero nosotros, una incorruptible»");
        verseContentsList.add("«La gracia de Dios se ha manifestado para salvación a toda la humanidad, y nos enseña que, renunciando a la impiedad y a los deseos mundanos, vivamos en este siglo sobria, justa y piadosamente»");
        verseContentsList.add("«Por tanto, ceñid los lomos de vuestro entendimiento, sed...»");
        return verseContentsList;
    }

    public ArrayList<String> getVerseContents1RBList() {
        verseContentsList.add("«¡Que Dios me pese en la balanza de la justicia y reconocerá mi integridad!»");
        verseContentsList.add("«Pon un cuchillo a tu garganta, si tienes mucho apetito»");
        verseContentsList.add("«Pretenden ser doctores de la Ley, cuando no entienden ni lo que hablan ni lo que afirman»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RAList() {
        verseContentsList.add("«Delante de las canas te levantarás y honrarás el rostro del anciano. De tu Dios tendrás temor. Yo, Jehová»");
        verseContentsList.add("«Por eso Jehová, el Dios de Israel, dice: \"Yo había prometido que tu casa y la casa de tu padre andarían siempre delante de mí\"; pero ahora ha dicho Jehová: \"Nunca haga yo tal cosa, porque yo honro a los que me honran, y los que me desprecian serán tenidos en poco\"»");
        verseContentsList.add("«Le preguntaron, diciendo: -Maestro, sabemos que dices y enseñas rectamente, y que no haces acepción de persona, sino que enseñas el camino de Dios con verdad»");
        verseContentsList.add("«Bienaventurados los pobres en espíritu, porque de ellos es el reino de los cielos»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RBList() {
        verseContentsList.add("«Así que todas las cosas que queráis que los hombres hagan con vosotros, así también haced vosotros con ellos, pues esto es la Ley y los Profetas»");
        verseContentsList.add("«Pero el que actúa con injusticia recibirá la injusticia que haya cometido, porque no hay acepción de personas»");
        verseContentsList.add("«Acordaos de vuestros padres, que os hablaron la palabra de Dios; considerad cuál haya sido el resultado de su conducta e imitad su fe»");
        verseContentsList.add("«Vosotros, maridos, igualmente, vivid con ellas sabiamente, dando honor a la mujer como a vaso más frágil y como a coherederos de la gracia de la vida, para que vuestras oraciones no tengan estorbo»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RAList() {
        verseContentsList.add("«Ningún siervo puede servir a dos señores, porque odiará al uno y amará al otro, o estimará al uno y menospreciar al otro...»");
        verseContentsList.add("«Sean vuestras costumbres sin avaricia, contentos con lo que tenéis ahora, pues él dijo: No te desampararé ni te dejaré»");
        verseContentsList.add("«...El juez se sentó y los libros fueron abiertos»");
        verseContentsList.add("«Jehová abomina el peso falso, pero la pesa cabal agrada»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RBList() {
        verseContentsList.add("«...De gracia recibisteis, dad de gracia»");
        verseContentsList.add("«Porque así dice Jehová: De balde fuisteis vendidos; por tanto, sin dinero seréis rescatados»");
        verseContentsList.add("«Buscad primeramente el reino de Dios y su justicia, y todas estas cosas os serán añadidas»");
        verseContentsList.add("«Mi Dios, pues, suplirá todo lo que os falta conforme a sus riquezas en gloria en Cristo Jesús»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFRAList() {
        verseContentsList.add("«La justicia, solo la justicia seguirás, para que vivas y heredes la tierra que Jehová, tu Dios, te da»");
        verseContentsList.add("«Bienaventurados los que tienen hambre y sed de justicia, porque serán saciados»");
        verseContentsList.add("«Ve si hay en mí camino de perversidad y guíame en el camino eterno»");
        verseContentsList.add("«No os unáis en yugo desigual con los incrédulos, porque ¿qué compañerismo tiene la justicia con la injusticia? ¿Y qué comunión, la luz con las tinieblas?»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFRBList() {
        verseContentsList.add("«En gran manera me gozaré en Jehová , mi alma se alegrará en mi Dios, porque me vistió con vestiduras de salvación, me rodeó de manto de justicia...»");
        verseContentsList.add("«Tan pronto la nube se apartó del Tabernáculo, María se llenó de lepra, y tenía la piel blanca como la nieve»");
        verseContentsList.add("«Mirad que no menospreciéis a uno de estos pequeños, porque os digo que sus ángeles en los cielos ven siempre el rostro de mi Padre que está en los cielos»");
        verseContentsList.add("«No os engañéis; Dios no puede ser burlado, pues todo lo que el hombre siembre, eso también segará»");

        return verseContentsList;
    }

    public ArrayList<String> getVerses1RAList() {
        versesList.add("\"Hechos 24: 25\"");
        versesList.add("\"1 Corintios 9: 25\"");
        versesList.add("\"Tito 2: 11-12\"");
        versesList.add("\"1 Pedro 1: 13\"");

        return versesList;
    }

    public ArrayList<String> getVerses1RBList() {
        versesList.add("\"Job 31: 6\"");
        versesList.add("\"Proverbios 23: 2\"");
        versesList.add("\"1 Timoteo 1: 7\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RAList() {
        versesList.add("\"Levítico 19: 32\"");
        versesList.add("\"1 Samuel 2: 30\"");
        versesList.add("\"Lucas 20: 21\"");
        versesList.add("\"Mateo 5: 3\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RBList() {
        versesList.add("\"Mateo 7: 12\"");
        versesList.add("\"Colosenses 3: 25\"");
        versesList.add("\"Hebreos 13: 7\"");
        versesList.add("\"1 Pedro 3: 7\"");

        return versesList;
    }

    public ArrayList<String> getVerses3RAList() {
        versesList.add("\"Lucas 16: 13\"");
        versesList.add("\"Hebreos 13: 5\"");
        versesList.add("\"Daniel 7: 10\"");
        versesList.add("\"Proverbios 11: 1\"");

        return versesList;
    }

    public ArrayList<String> getVerses3RBList() {
        versesList.add("\"Mateo 10: 8\"");
        versesList.add("\"Isaías 52: 3\"");
        versesList.add("\"Mateo 6: 33\"");
        versesList.add("\"Filipenses 4: 19\"");

        return versesList;
    }

    public ArrayList<String> getVersesFRAList() {
        versesList.add("\"Deuteronomio 16: 20\"");
        versesList.add("\"Mateo 5: 6\"");
        versesList.add("\"Salmos 139: 24\"");
        versesList.add("\"2 Corintios 6: 14\"");

        return versesList;
    }

    public ArrayList<String> getVersesFRBList() {
        versesList.add("\"Isaías 61: 10\"");
        versesList.add("\"Números 12: 10\"");
        versesList.add("\"Mateo 18: 10\"");
        versesList.add("\"Gálatas 6: 7\"");

        return versesList;
    }

    public ArrayList<Integer> getDayIds() {
        for (int i = 0; i < datesList.size(); i++) {
            dayIdsList.add(i);
        }
        return dayIdsList;
    }

    public void generateMultipleChoiceQuestion(int currentQuestionId, int numberOfDays) {

        // Randomly choose 2 options among 4 and save them in a string.
        // 1:date, 2:title, 3:verseContent, 4: verse.
        randomDataArray();
        String option1 = randomDataList.get(0);
        String option2 = randomDataList.get(1);
        ArrayList<Integer> randomIDList = new ArrayList<>();

        if (option1.equals("date") && option2.equals("title")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(0)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(1)).getTitle());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getTitle());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el título del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el título del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("title") && option2.equals("date")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getDate());
            answers.add(dayObjectList.get(randomIDList.get(0)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(1)).getDate());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getDate());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("date") && option2.equals("verseContent")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerseContent());

            if(numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getVerseContent());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("verseContent") && option2.equals("date")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getDate());
            answers.add(dayObjectList.get(randomIDList.get(0)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(1)).getDate());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getDate());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("date") && option2.equals("verse")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerse());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getVerse());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("verse") && option2.equals("date")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getDate());
            answers.add(dayObjectList.get(randomIDList.get(0)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(1)).getDate());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getDate());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("title") && option2.equals("verseContent")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerseContent());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getVerseContent());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("verseContent") && option2.equals("title")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(0)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(1)).getTitle());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getTitle());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el título del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el título del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("title") && option2.equals("verse")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerse());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getVerse());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("verse") && option2.equals("title")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(0)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(1)).getTitle());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getTitle());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el título de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el título de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("verseContent") && option2.equals("verse")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerse());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getVerse());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        } else if (option1.equals("verse") && option2.equals("verseContent")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId, numberOfDays);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerseContent());

            if (numberOfDays > 3) {
                answers.add(dayObjectList.get(randomIDList.get(2)).getVerseContent());
            }

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line for 4 day questions.
            // If more than 3 days, use the  else statement.
            if (numberOfDays > 3) {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
            } else {
                finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), "MC"));
            }
        }
    }

    public void generateTrueOrFalseQuestions(int currentQuestionId) {

        // Randomly choose 2 options among 4 and save them in a string.
        // 1:date, 2:title, 3:verseContent, 4: verse.
        randomDataArray();
        String option1 = randomDataList.get(0);
        String option2 = randomDataList.get(1);
        String question = "", answer = "", answerOption1 = "", answerOption2 = "", randDate, randTitle, randVerseContent, randVerse;

        // Generates random number between 1 and 2 to see if question will be 1:True or 2:False.
        Random rand = new Random();
        int trueOrFalse = rand.nextInt(2 - 1 + 1) + 1;

        // If it's true, we can generate question statement by getting correct data from
        // our data array based on the id that was saved in the chosen questions for this
        // category. There are 12 possible combinations, so 12 possible cases.
        if (trueOrFalse == 1) {
            //Generate question from selected data
            if (option1.equals("date") && option2.equals("title")) {
                question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("title") && option2.equals("date")) {
                question = "El título " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("date") && option2.equals("verseContent")) {
                question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verseContent") && option2.equals("date")) {
                question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("date") && option2.equals("verse")) {
                question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verse") && option2.equals("date")) {
                question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("title") && option2.equals("verseContent")) {
                question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verseContent") && option2.equals("title")) {
                question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("title") && option2.equals("verse")) {
                question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verse") && option2.equals("title")) {
                question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verseContent") && option2.equals("verse")) {
                question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verse") && option2.equals("verseContent")) {
                question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            }
            // If it's false, then we have to choose another random piece of data. We will
            // randomly choose if the changing value will be option1 or option2.
        } else {
            // Generates random number between 1 and 2 to see if option1 or option2 will be the
            // changing value.
            Random rand2 = new Random();
            int chosenChangeValue = rand2.nextInt(2 - 1 + 1) + 1;

            // If it's 1, then option1 will need to be changed at random.
            if (chosenChangeValue == 1) {
                if (option1.equals("date")) {
                    int randID = generateRandomID(currentQuestionId);
                    randDate = dayObjectList.get(randID).getDate();

                    if (option2.equals("title")) {
                        question = "La fecha " + randDate + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verseContent")) {
                        question = "La fecha " + randDate + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verse")) {
                        question = "La fecha " + randDate + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option1.equals("title")) {
                    int randID = generateRandomID(currentQuestionId);
                    randTitle = dayObjectList.get(randID).getTitle();

                    if (option2.equals("date")) {
                        question = "El título " + randTitle + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verseContent")) {
                        question = "El titulo " + randTitle + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verse")) {
                        question = "El titulo " + randTitle + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option1.equals("verseContent")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerseContent = dayObjectList.get(randID).getVerseContent();

                    if (option2.equals("date")) {
                        question = "El versículo " + randVerseContent + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("title")) {
                        question = "El versículo " + randVerseContent + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verse")) {
                        question = "El versículo " + randVerseContent + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option1.equals("verse")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerse = dayObjectList.get(randID).getVerse();

                    if (option2.equals("date")) {
                        question = "La cita bíblica " + randVerse + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("title")) {
                        question = "La cita bíblica " + randVerse + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verseContent")) {
                        question = "La cita bíblica " + randVerse + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                }
            } else if (chosenChangeValue == 2) {
                if (option2.equals("date")) {
                    int randID = generateRandomID(currentQuestionId);
                    randDate = dayObjectList.get(randID).getDate();

                    if (option1.equals("title")) {
                        question = "El título " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al " + randDate + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verseContent")) {
                        question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al " + randDate + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verse")) {
                        question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al " + randDate + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option2.equals("title")) {
                    int randID = generateRandomID(currentQuestionId);
                    randTitle = dayObjectList.get(randID).title;

                    if (option1.equals("date")) {
                        question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al título " + randTitle + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verseContent")) {
                        question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al título " + randTitle + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verse")) {
                        question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al título " + randTitle + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option2.equals("verseContent")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerseContent = dayObjectList.get(randID).verseContent;

                    if (option1.equals("date")) {
                        question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al versículo " + randVerseContent + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("title")) {
                        question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al versículo " + randVerseContent + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verse")) {
                        question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al versículo " + randVerseContent + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option2.equals("verse")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerse = dayObjectList.get(randID).verse;

                    if (option1.equals("date")) {
                        question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde a la cita bíblica " + randVerse + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("title")) {
                        question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde a la cita bíblica " + randVerse + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verseContent")) {
                        question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde a la cita bíblica " + randVerse + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                }
            }
        }

        // Add generated question and answer to final array.
        finalQuestionsList.add(new QuestionModel(question, answer, answerOption1, answerOption2, "","", "TF"));
    }

    public void generateFillInTheBlankQuestions(int currentQuestionId) {

        String verseContent = dayObjectList.get(currentQuestionId).getVerseContent();
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(verseContent.split("\\s")));
        ArrayList<Integer> numberOfWords = new ArrayList<>();

        int totalWordsRemoved = (int) Math.round((wordsList.size()*30.0)/100.0);
        //Log.e("", String.valueOf(Math.round((wordsList.size()*30.0)/100.0)));

        // Generate a random number between 0 and the size of the array to replace a word
        // in the title with "______".
        for (int i = 0; i < wordsList.size(); i++) {
            numberOfWords.add(i);
        }

        Collections.shuffle(numberOfWords);

        for (int i = 0; i < totalWordsRemoved; i++) {
            String removedWord = wordsList.get(numberOfWords.get(i));
            wordsList.set(numberOfWords.get(i), "________");
        }

        // Generate and save question along with answer.
        String newTitle = "";
        for (int x = 0; x < wordsList.size(); x++) {
            newTitle += wordsList.get(x) + " ";
        }
        String question = newTitle + ". (" + totalWordsRemoved + " Pts.)";

        finalQuestionsList.add(new QuestionModel(question, "", "", "", "", "", "FB"));
    }

    public void randomDataArray() {
        randomDataList.clear();
        randomDataList.add("date");
        randomDataList.add("title");
        randomDataList.add("verseContent");
        randomDataList.add("verse");

        Collections.shuffle(randomDataList, new Random());
    }

    public ArrayList<Integer> generateRandomIDMultipleChoice(int currentQuestionId, int numberOfDays) {
        // Generates random number between 1 and the size of the total verses
        // at random.
        Random rand3 = new Random();
        int randID = rand3.nextInt(dayIdsList.size());

        ArrayList<Integer> randomIDList = new ArrayList<>();

        // Check if the random number isn't the same as the current question ID.
        for (int x = 0; x < (numberOfDays - 1); x++) {
            while (randID == dayObjectList.get(currentQuestionId).getDayId() || randomIDList.contains(randID)) {
                randID = rand3.nextInt(dayIdsList.size());
            }
            randomIDList.add(randID);
            randID = rand3.nextInt(dayIdsList.size());
        }

        Log.e("lol", "random id content: " + randomIDList.toString());


        return randomIDList;
    }

    public int generateRandomID(int i) {
        // Generates random number between 1 and 13 to get a new piece of data
        // at random.
        Random rand3 = new Random();
        int randID = rand3.nextInt(dayIdsList.size());

        // Check if the random number isn't the same as the current question ID.
        while (randID == dayObjectList.get(i).getDayId()) {
            randID = rand3.nextInt(dayIdsList.size());
        }

        return randID;

    }

    public void generateLevel1Question(int currentQuestionId) {

            String selectedDate = dayObjectList.get(currentQuestionId).getDate();
            String selectedTitle = dayObjectList.get(currentQuestionId).getTitle();
            String selectedVerseContent = dayObjectList.get(currentQuestionId).getVerseContent();
            String selectedVerse = dayObjectList.get(currentQuestionId).getVerse();

            tempQuestionList.clear();

            tempQuestionList.add(new QuestionModel("¿Cuál es el título del " + selectedDate + "?", selectedTitle, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título del versículo " + selectedVerseContent + "?", selectedTitle, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título de la cita bíblica " + selectedVerse + "?", selectedTitle, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo del " + selectedDate + "?", "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo del título " + selectedTitle + "?", "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo de la cita bíblica " + selectedVerse + "?", "\""+ selectedVerseContent + "\"", "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la cita bíblica del " + selectedDate + "?", selectedVerse, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la cita bíblica del título " + selectedTitle + "?", selectedVerse, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la cita bíblica del versículo " + selectedVerseContent + "?", selectedVerse, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha del título " + selectedTitle + "?", selectedDate, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha del versículo " + selectedVerseContent + "?", selectedDate, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha de la cita bíblica " + selectedVerse + "?", selectedDate, "", "", "", "Nivel 1"));

            Collections.shuffle(tempQuestionList);

        finalQuestionsList.add(tempQuestionList.get(0));
            //level1FinalQuestionsList.add(tempQuestionList.get(1));
            //Collections.shuffle(level1FinalQuestionsList);


        //Collections.shuffle(level1FinalQuestionsList);
    }

    public void generateLevel2Question(int currentQuestionId) {
        randomNumbers.clear();
        randomNumbers();

        String selectedDate = dayObjectList.get(currentQuestionId).getDate();
        String selectedTitle = dayObjectList.get(currentQuestionId).getTitle();
        String selectedVerseContent = dayObjectList.get(currentQuestionId).getVerseContent();
        String selectedVerse = dayObjectList.get(currentQuestionId).getVerse();

            tempQuestionList.clear();

            tempQuestionList.add(new QuestionModel("¿Cuál es el título y el versículo del " + selectedDate + "?", selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y la cita bíblica del " + selectedDate + "?", selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo y la cita bíblica del " + selectedDate + "?", "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el versículo del título " + selectedTitle + "?", selectedDate + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y la cita bíblica del título " + selectedTitle + "?", selectedDate + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo y la cita bíblica del título " + selectedTitle + "?", "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el título del versículo " + selectedVerseContent + "?", selectedDate + "\n\n" + selectedTitle, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y la cita bíblica del versículo " + selectedVerseContent + "?", selectedDate + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y la cita bíblica del versículo " + selectedVerseContent + "?", selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el título de la cita bíblica " + selectedVerse + "?", selectedDate + "\n\n" + selectedTitle, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el versículo de la cita bíblica " + selectedVerse + "?", selectedDate + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y el versículo de la cita bíblica " + selectedVerse + "?", selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));

            Collections.shuffle(tempQuestionList);

        finalQuestionsList.add(tempQuestionList.get(0));
            //level2FinalQuestionsList.add(tempQuestionList.get(1));
            //Collections.shuffle(level2FinalQuestionsList);


        //Collections.shuffle(level2FinalQuestionsList);
    }

    public void generateLevel3Question(int currentQuestionId) {

        randomNumbers.clear();
        randomNumbers();

        String selectedDate = dayObjectList.get(currentQuestionId).getDate();
        String selectedTitle = dayObjectList.get(currentQuestionId).getTitle();
        String selectedVerseContent = dayObjectList.get(currentQuestionId).getVerseContent();
        String selectedVerse = dayObjectList.get(currentQuestionId).getVerse();

            tempQuestionList.clear();

            tempQuestionList.add(new QuestionModel("¿Cuál es el título, el versículo y la cita bíblica del " + selectedDate + "?", selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 3"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha, el versículo y la cita bíblica del título " + selectedTitle + "?", selectedDate + "\n\n" + "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 3"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha, el título y la cita bíblica del versículo " + selectedVerseContent + "?", selectedDate + "\n\n" + selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 3"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha, el título y el versículo de la cita bíblica " + selectedVerse + "?", selectedDate + "\n\n" + selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 3"));

            Collections.shuffle(tempQuestionList);

        finalQuestionsList.add(tempQuestionList.get(0));
            //level3FinalQuestionsList.add(tempQuestionList.get(1));
            //Collections.shuffle(level3FinalQuestionsList);


        //Collections.shuffle(level3FinalQuestionsList);
    }

    public ArrayList<Integer> randomNumbers () {
        randomNumbers.add(0);
        randomNumbers.add(1);
        randomNumbers.add(2);
        randomNumbers.add(3);
        randomNumbers.add(4);
        randomNumbers.add(5);
        randomNumbers.add(6);
        randomNumbers.add(7);

        Collections.shuffle(randomNumbers);

        return randomNumbers;
    }


}