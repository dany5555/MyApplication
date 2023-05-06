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
        datesList.add("1 de Abril");
        datesList.add("2 de Abril");
        datesList.add("3 de Abril");
        datesList.add("4 de Abril");
        datesList.add("5 de Abril");
        datesList.add("6 de Abril");
        datesList.add("7 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates2RList() {
        datesList.add("8 de Abril");
        datesList.add("9 de Abril");
        datesList.add("10 de Abril");
        datesList.add("11 de Abril");
        datesList.add("12 de Abril");
        datesList.add("13 de Abril");
        datesList.add("14 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates3RList() {
        datesList.add("15 de Abril");
        datesList.add("16 de Abril");
        datesList.add("17 de Abril");
        datesList.add("18 de Abril");
        datesList.add("19 de Abril");
        datesList.add("20 de Abril");
        datesList.add("21 de Abril");
        datesList.add("22 de Abril");

        return datesList;
    }

    public ArrayList<String> getDatesFRList() {
        datesList.add("23 de Abril");
        datesList.add("24 de Abril");
        datesList.add("25 de Abril");
        datesList.add("26 de Abril");
        datesList.add("27 de Abril");
        datesList.add("28 de Abril");
        datesList.add("29 de Abril");
        datesList.add("30 de Abril");

        return datesList;
    }

    public ArrayList<String> getTitles1RList() {
        titlesList.add("\"Mucho más sabios\"");
        titlesList.add("\"La belleza de las formas\"");
        titlesList.add("\"¡Menudo cálculo!\"");
        titlesList.add("\"No siempre le entiendo pero siempre me ama\"");
        titlesList.add("\"Una mirada justa, juicios justos\"");
        titlesList.add("\"Indicadores\"");
        titlesList.add("\"Y fue así\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RList() {
        titlesList.add("\"Menos lobos, Caperucita\"");
        titlesList.add("\"'Time lapse'\"");
        titlesList.add("\"Cataratas\"");
        titlesList.add("\"Ingeniería inversa\"");
        titlesList.add("\"'Big Big Data'\"");
        titlesList.add("\"Serendipia\"");
        titlesList.add("\"Ni la Academia ni el Liceo\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RList() {
        titlesList.add("\"La fruta nuestra de cada día\"");
        titlesList.add("\"'Slow food'\"");
        titlesList.add("\"Preciosura\"");
        titlesList.add("\"La taxonomía de Cristo\"");
        titlesList.add("\"Las primeras cosas\"");
        titlesList.add("\"Gracia con una pizca de sal\"");
        titlesList.add("\"A examen\"");
        titlesList.add("\"El mejor orador\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFRList() {
        titlesList.add("\"Las palabras caducas y las palabras perennes\"");
        titlesList.add("\"Reconoce y confía\"");
        titlesList.add("\"Asertividad y más asertividad\"");
        titlesList.add("\"Zombis\"");
        titlesList.add("\"Voz de los silenciados\"");
        titlesList.add("\"Los tres filtros\"");
        titlesList.add("\"Una palabra cariñosa\"");
        titlesList.add("\"Cómo ser amable y no morir en el intento\"");

        return titlesList;
    }

    public ArrayList<String> getVerseContents1RList() {
        verseContentsList.add("«¿Quién es sabio para que sepa esto, y prudente para que lo comprenda? Porque los caminos de Jehová son rectos, por ellos andarán los justos, mas los rebeldes caerán en ellos»");
        verseContentsList.add("«Todo lo hizo hermoso en su tiempo, y ha puesto eternidad en el corazón del hombre, sin que este alcance a comprender la obra hecha por Dios desde el principio hasta el fin»");
        verseContentsList.add("«Él cuenta el número de las estrellas; a todas ellas llama por sus nombres. Grande es el Señor nuestro y mucho su poder, y su entendimiento es infinito»");
        verseContentsList.add("«\"Porque mis pensamientos no son vuestros pensamientos ni vuestros caminos mis caminos\", dice Jehová»");
        verseContentsList.add("«¡Profundidad de las riquezas, de la sabiduría y del conocimiento de Dios! ¡Cuán insondables son sus juicios e inescrutables sus caminos!»");
        verseContentsList.add("«Pero la sabiduría que es de lo alto es primeramente pura, después pacífica, amable, benigna, llena de misericordia y de buenos frutos, sin incertidumbre ni hipocresía»");
        verseContentsList.add("«E hizo Dios un firmamento que separó las aguas que estaban debajo del firmamento, de las aguas que estaban sobre el firmamento. Y fue así»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RList() {
        verseContentsList.add("«Estos son los orígenes de los cielos y de la tierra cuando fueron creados»");
        verseContentsList.add("«Él es quien cambia los tiempos y las edades; quita reyes y pone reyes; da sabiduría a los sabios, y conocimiento a los entendidos»");
        verseContentsList.add("«Ahora vemos por espejo, oscuramente; pero entonces veremos cara a cara. Ahora conozco en parte, pero entonces conoceré como fui conocido»");
        verseContentsList.add("«Debemos siempre dar gracias a Dios por vosotros, hermanos, como es digno, por cuanto vuestra fe va creciendo y el amor de todos y cada uno de vosotros abunda para con los demás»");
        verseContentsList.add("«Para que el Dios de nuestro Señor Jesucristo, el Padre de gloria, os dé espíritu de sabiduría y de revelación en el conocimiento de él»");
        verseContentsList.add("«Si alguno de vosotros tiene falta de sabiduría, pídala a Dios, el cual da a todos abundantemente y sin reproche, y le será dada»");
        verseContentsList.add("«¡Alaben la misericordia de Jehová y sus maravillas para con los hijos de los hombres!, porque sacia el alma menesterosa, y llena de bien al alma hambrienta»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RList() {
        verseContentsList.add("«Y mandó Jehová Dios al hombre, diciendo: \"De todo árbol del huerto podrás comer…\".»");
        verseContentsList.add("«Entonces Jacob dio a Esaú pan y del guisado de las lentejas; él comió y bebió, se levantó y se fue...»");
        verseContentsList.add("«Porque ¡cuánta es su bondad y cuánta su hermosura!...»");
        verseContentsList.add("«Aunque andamos en la carne, no militamos según la carne»");
        verseContentsList.add("«A los hijos de sus concubinas les dio Abraham regalos...»");
        verseContentsList.add("«Sea vuestra palabra siempre con gracia, sazonada con sal, para que sepáis cómo debéis responder a cada uno»");
        verseContentsList.add("«Examinadlo todo y retened lo bueno»");
        verseContentsList.add("«Los alguaciles respondieron: ¡Jamás hombre alguno ha hablado como este hombre habla!»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFRList() {
        verseContentsList.add("«El labio veraz permanece para siempre; la lengua mentirosa, solo por un momento»");
        verseContentsList.add("«El hombre le respondió: -La mujer que me diste por compañera me dio del árbol, y yo comí»");
        verseContentsList.add("«El hombre se alegra con la respuesta de su boca; la palabra a su tiempo, ¡cuán buena es!»");
        verseContentsList.add("«La muerte y la vida están en poder de la lengua; el que la ama, comerá de sus frutos»");
        verseContentsList.add("«Abre tu boca en favor del mudo en el juicio de todos los desvalidos»");
        verseContentsList.add("«Pero yo os digo que toda palabra ociosa que hablen de los hombres, de ella darán cuenta en el día del juicio»");
        verseContentsList.add("«Entonces dijo Dios: \"Hagamos al hombre a nuestra imagen, conforme a nuestra semejanza; y tenga potestad sobre los peces del mar, las aves de los cielos y las bestias, sobre toda la tierra y sobre todo animal que se arrastra sobre la tierra\".»");
        verseContentsList.add("«No devolváis mal por mal, ni maldición por maldición, sino por el contrario, bendiciendo...»");

        return verseContentsList;
    }

    public ArrayList<String> getVerses1RList() {
        versesList.add("\"Oseas 14:9\"");
        versesList.add("\"Eclesiastés 3:11");
        versesList.add("\"Salmos 147:4-5\"");
        versesList.add("\"Isaías 55:8\"");
        versesList.add("\"Romanos 11:33\"");
        versesList.add("\"Santiago 3:17\"");
        versesList.add("\"Génesis 1:7\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RList() {
        versesList.add("\"Génesis 2:4\"");
        versesList.add("\"Daniel 2:21, LBLA");
        versesList.add("\"1 Corintios 13:12");
        versesList.add("\"2 Tesalonicenses 1:3");
        versesList.add("\"Efesios 1:17");
        versesList.add("\"Santiago 1:5");
        versesList.add("\"Salmos 107:8-9\"");

        return versesList;
    }

    public ArrayList<String> getVerses3RList() {
        versesList.add("\"Génesis 2:16\"");
        versesList.add("\"Génesis 25:34");
        versesList.add("\"Zacarías 9:17");
        versesList.add("\"1 Corintios 10:3");
        versesList.add("\"Génesis 25:6");
        versesList.add("\"Colosenses 4:6");
        versesList.add("\"1 Tesalonicenses 5:21\"");
        versesList.add("\"Juan 7:46, LBLA\"");

        return versesList;
    }

    public ArrayList<String> getVersesFRList() {
        versesList.add("\"Proverbios 12:19\"");
        versesList.add("\"Génesis 3:12");
        versesList.add("\"Proverbios 15:23\"");
        versesList.add("\"Proverbios 18:21\"");
        versesList.add("\"Proverbios 31:8\"");
        versesList.add("\"Mateo 12:36\"");
        versesList.add("\"Génesis 1:26\"");
        versesList.add("\"1 Pedro 3:9\"");

        return versesList;
    }

    public ArrayList<String> getDates1RAList() {
        datesList.add("1 de Abril");
        datesList.add("2 de Abril");
        datesList.add("3 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates1RBList() {
        datesList.add("4 de Abril");
        datesList.add("5 de Abril");
        datesList.add("6 de Abril");
        datesList.add("7 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates2RAList() {
        datesList.add("11 de Abril");
        datesList.add("12 de Abril");
        datesList.add("13 de Abril");
        datesList.add("14 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates2RBList() {
        datesList.add("8 de Abril");
        datesList.add("9 de Abril");
        datesList.add("10 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates3RAList() {
        datesList.add("15 de Abril");
        datesList.add("16 de Abril");
        datesList.add("17 de Abril");
        datesList.add("18 de Abril");

        return datesList;
    }

    public ArrayList<String> getDates3RBList() {
        datesList.add("19 de Abril");
        datesList.add("20 de Abril");
        datesList.add("21 de Abril");
        datesList.add("22 de Abril");

        return datesList;
    }

    public ArrayList<String> getDatesFRAList() {
        datesList.add("27 de Abril");
        datesList.add("28 de Abril");
        datesList.add("29 de Abril");
        datesList.add("30 de Abril");

        return datesList;
    }

    public ArrayList<String> getDatesFRBList() {
        datesList.add("23 de Abril");
        datesList.add("24 de Abril");
        datesList.add("25 de Abril");
        datesList.add("26 de Abril");

        return datesList;
    }

    public ArrayList<String> getTitles1RAList() {
        titlesList.add("\"Mucho más sabios\"");
        titlesList.add("\"La belleza de las formas\"");
        titlesList.add("\"¡Menudo cálculo!\"");

        return titlesList;
    }

    public ArrayList<String> getTitles1RBList() {
        titlesList.add("\"No siempre le entiendo pero siempre me ama\"");
        titlesList.add("\"Una mirada justa, juicios justos\"");
        titlesList.add("\"Indicadores\"");
        titlesList.add("\"Y fue así\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RAList() {
        titlesList.add("\"Ingeniería inversa\"");
        titlesList.add("\"'Big Big Data'\"");
        titlesList.add("\"Serendipia\"");
        titlesList.add("\"Ni la Academia ni el Liceo\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RBList() {
        titlesList.add("\"Menos lobos, Caperucita\"");
        titlesList.add("\"'Time lapse'\"");
        titlesList.add("\"Cataratas\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RAList() {
        titlesList.add("\"La fruta nuestra de cada día\"");
        titlesList.add("\"'Slow food'\"");
        titlesList.add("\"Preciosura\"");
        titlesList.add("\"La taxonomía de Cristo\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RBList() {
        titlesList.add("\"Las primeras cosas\"");
        titlesList.add("\"Gracia con una pizca de sal\"");
        titlesList.add("\"A examen\"");
        titlesList.add("\"El mejor orador\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFRAList() {
        titlesList.add("\"Voz de los silenciados\"");
        titlesList.add("\"Los tres filtros\"");
        titlesList.add("\"Una palabra cariñosa\"");
        titlesList.add("\"Cómo ser amable y no morir en el intento\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFRBList() {
        titlesList.add("\"Las palabras caducas y las palabras perennes\"");
        titlesList.add("\"Reconoce y confía\"");
        titlesList.add("\"Asertividad y más asertividad\"");
        titlesList.add("\"Zombis\"");

        return titlesList;
    }

    public ArrayList<String> getVerseContents1RAList() {
        verseContentsList.add("«¿Quién es sabio para que sepa esto, y prudente para que lo comprenda? Porque los caminos de Jehová son rectos, por ellos andarán los justos, mas los rebeldes caerán en ellos»");
        verseContentsList.add("«Todo lo hizo hermoso en su tiempo, y ha puesto eternidad en el corazón del hombre, sin que este alcance a comprender la obra hecha por Dios desde el principio hasta el fin»");
        verseContentsList.add("«Él cuenta el número de las estrellas; a todas ellas llama por sus nombres. Grande es el Señor nuestro y mucho su poder, y su entendimiento es infinito»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents1RBList() {
        verseContentsList.add("«\"Porque mis pensamientos no son vuestros pensamientos ni vuestros caminos mis caminos\", dice Jehová»");
        verseContentsList.add("«¡Profundidad de las riquezas, de la sabiduría y del conocimiento de Dios! ¡Cuán insondables son sus juicios e inescrutables sus caminos!»");
        verseContentsList.add("«Pero la sabiduría que es de lo alto es primeramente pura, después pacífica, amable, benigna, llena de misericordia y de buenos frutos, sin incertidumbre ni hipocresía»");
        verseContentsList.add("«E hizo Dios un firmamento que separó las aguas que estaban debajo del firmamento, de las aguas que estaban sobre el firmamento. Y fue así»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RAList() {
        verseContentsList.add("«Debemos siempre dar gracias a Dios por vosotros, hermanos, como es digno, por cuanto vuestra fe va creciendo y el amor de todos y cada uno de vosotros abunda para con los demás»");
        verseContentsList.add("«Para que el Dios de nuestro Señor Jesucristo, el Padre de gloria, os dé espíritu de sabiduría y de revelación en el conocimiento de él»");
        verseContentsList.add("«Si alguno de vosotros tiene falta de sabiduría, pídala a Dios, el cual da a todos abundantemente y sin reproche, y le será dada»");
        verseContentsList.add("«¡Alaben la misericordia de Jehová y sus maravillas para con los hijos de los hombres!, porque sacia el alma menesterosa, y llena de bien al alma hambrienta»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RBList() {
        verseContentsList.add("«Estos son los orígenes de los cielos y de la tierra cuando fueron creados»");
        verseContentsList.add("«Él es quien cambia los tiempos y las edades; quita reyes y pone reyes; da sabiduría a los sabios, y conocimiento a los entendidos»");
        verseContentsList.add("«Ahora vemos por espejo, oscuramente; pero entonces veremos cara a cara. Ahora conozco en parte, pero entonces conoceré como fui conocido»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RAList() {
        verseContentsList.add("«Y mandó Jehová Dios al hombre, diciendo: \"De todo árbol del huerto podrás comer…\".»");
        verseContentsList.add("«Entonces Jacob dio a Esaú pan y del guisado de las lentejas; él comió y bebió, se levantó y se fue...»");
        verseContentsList.add("«Porque ¡cuánta es su bondad y cuánta su hermosura!...»");
        verseContentsList.add("«Aunque andamos en la carne, no militamos según la carne»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RBList() {
        verseContentsList.add("«A los hijos de sus concubinas les dio Abraham regalos...»");
        verseContentsList.add("«Sea vuestra palabra siempre con gracia, sazonada con sal, para que sepáis cómo debéis responder a cada uno»");
        verseContentsList.add("«Examinadlo todo y retened lo bueno»");
        verseContentsList.add("«Los alguaciles respondieron: ¡Jamás hombre alguno ha hablado como este hombre habla!»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFRAList() {
        verseContentsList.add("«Abre tu boca en favor del mudo en el juicio de todos los desvalidos»");
        verseContentsList.add("«Pero yo os digo que toda palabra ociosa que hablen de los hombres, de ella darán cuenta en el día del juicio»");
        verseContentsList.add("«Entonces dijo Dios: \"Hagamos al hombre a nuestra imagen, conforme a nuestra semejanza; y tenga potestad sobre los peces del mar, las aves de los cielos y las bestias, sobre toda la tierra y sobre todo animal que se arrastra sobre la tierra\".»");
        verseContentsList.add("«No devolváis mal por mal, ni maldición por maldición, sino por el contrario, bendiciendo...»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFRBList() {
        verseContentsList.add("«El labio veraz permanece para siempre; la lengua mentirosa, solo por un momento»");
        verseContentsList.add("«El hombre le respondió: -La mujer que me diste por compañera me dio del árbol, y yo comí»");
        verseContentsList.add("«El hombre se alegra con la respuesta de su boca; la palabra a su tiempo, ¡cuán buena es!»");
        verseContentsList.add("«La muerte y la vida están en poder de la lengua; el que la ama, comerá de sus frutos»");

        return verseContentsList;
    }

    public ArrayList<String> getVerses1RAList() {
        versesList.add("\"Oseas 14:9\"");
        versesList.add("\"Eclesiastés 3:11");
        versesList.add("\"Salmos 147:4-5\"");

        return versesList;
    }

    public ArrayList<String> getVerses1RBList() {
        versesList.add("\"Isaías 55:8\"");
        versesList.add("\"Romanos 11:33\"");
        versesList.add("\"Santiago 3:17\"");
        versesList.add("\"Génesis 1:7\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RAList() {
        versesList.add("\"2 Tesalonicenses 1:3");
        versesList.add("\"Efesios 1:17");
        versesList.add("\"Santiago 1:5");
        versesList.add("\"Salmos 107:8-9\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RBList() {
        versesList.add("\"Génesis 2:4\"");
        versesList.add("\"Daniel 2:21, LBLA");
        versesList.add("\"1 Corintios 13:12");

        return versesList;
    }

    public ArrayList<String> getVerses3RAList() {
        versesList.add("\"Génesis 2:16\"");
        versesList.add("\"Génesis 25:34");
        versesList.add("\"Zacarías 9:17");
        versesList.add("\"1 Corintios 10:3");

        return versesList;
    }

    public ArrayList<String> getVerses3RBList() {
        versesList.add("\"Génesis 25:6");
        versesList.add("\"Colosenses 4:6");
        versesList.add("\"1 Tesalonicenses 5:21\"");
        versesList.add("\"Juan 7:46, LBLA\"");

        return versesList;
    }

    public ArrayList<String> getVersesFRAList() {
        versesList.add("\"Proverbios 31:8\"");
        versesList.add("\"Mateo 12:36\"");
        versesList.add("\"Génesis 1:26\"");
        versesList.add("\"1 Pedro 3:9\"");

        return versesList;
    }

    public ArrayList<String> getVersesFRBList() {
        versesList.add("\"Proverbios 12:19\"");
        versesList.add("\"Génesis 3:12");
        versesList.add("\"Proverbios 15:23\"");
        versesList.add("\"Proverbios 18:21\"");

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
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y la cita bíblica el versículo " + selectedVerseContent + "?", selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

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