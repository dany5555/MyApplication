package com.vision.experienciamatutina;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {

    // General things
    TextView questionTextView, questionNumberTextView, correctOrIncorrectTextView, endPoints, timer, teamNameTextView;
    Button exitButton, nextQuestionButton, endExitButton;
    Dialog dialog, endDialog;
    ImageView correctOrIncorrectImageView;
    int currentScore = 0, currentQuestion = 1;
    CountDownTimer countDownTimer;
    long timeLeftInMilliseconds = 720000; // 12 minutes
    boolean timerRunning;

    public static ArrayList<QuestionModel> firstRoundQuestionList = new ArrayList<>();
    public static ArrayList<QuestionModel> secondRoundQuestionList = new ArrayList<>();
    public static ArrayList<QuestionModel> thirdRoundQuestionList = new ArrayList<>();
    public static ArrayList<QuestionModel> finalRoundQuestionList = new ArrayList<>();

    public static ArrayList<QuestionModel> exam1RAList = new ArrayList<>();
    public static ArrayList<QuestionModel> exam2RAList = new ArrayList<>();
    public static ArrayList<QuestionModel> exam3RAList = new ArrayList<>();
    public static ArrayList<QuestionModel> examFRAList = new ArrayList<>();

    public static ArrayList<QuestionModel> exam1RBList = new ArrayList<>();
    public static ArrayList<QuestionModel> exam2RBList = new ArrayList<>();
    public static ArrayList<QuestionModel> exam3RBList = new ArrayList<>();
    public static ArrayList<QuestionModel> examFRBList = new ArrayList<>();


    public ArrayList<QuestionModel> currentQuestionList = new ArrayList<>();


    String teamUid, round;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference currentRoundRef = database.getReference("Answers Round 1");
    DatabaseReference firstRoundRef = database.getReference("Answers Round 1");
    DatabaseReference secondRoundRef = database.getReference("Answers Round 2");
    DatabaseReference thirdRoundRef = database.getReference("Answers Round 3");
    DatabaseReference finalRoundRef = database.getReference("Answers Final Round");

    DatabaseReference exam1RARef = database.getReference("Answers 1RA");
    DatabaseReference exam2RARef = database.getReference("Answers 2RA");
    DatabaseReference exam3RARef = database.getReference("Answers 3RA");
    DatabaseReference examFRARef = database.getReference("Answers FRA");

    DatabaseReference exam1RBRef = database.getReference("Answers 1RB");
    DatabaseReference exam2RBRef = database.getReference("Answers 2RB");
    DatabaseReference exam3RBRef = database.getReference("Answers 3RB");
    DatabaseReference examFRBRef = database.getReference("Answers FRB");

    DatabaseReference teamNameRef = database.getReference("Participants");

    // Multiple choice things
    Button option1Button, option2Button, option3Button, option4Button;
    ScrollView multipleChoiceView;

    // Fill in the blank things
    EditText fillInTheBlankAnswerEditText;
    Button enterFillInTheBlankAnswerButton;

    // True or false things
    Button falseButton, trueButton;
    ScrollView trueOrFalseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_new);

        teamUid = getIntent().getStringExtra("id");
        round = getIntent().getStringExtra("round");

        multipleChoiceView = findViewById(R.id.multiple_choice_view);
        trueOrFalseView = findViewById(R.id.true_or_false_view);
        fillInTheBlankAnswerEditText = findViewById(R.id.fill_in_the_blank_answer_edit_text);

        teamNameRef.child(teamUid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String teamName = snapshot.getValue(String.class);
                teamNameTextView.setText("Hola " + teamName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Log.e("lol", "");
        //Collections.shuffle(finalQuestionsList);

        if (round.equals("firstRound")) {
            currentQuestionList = firstRoundQuestionList;
            currentRoundRef = firstRoundRef;
        } else if (round.equals("secondRound")) {
            currentQuestionList = secondRoundQuestionList;
            currentRoundRef = secondRoundRef;
        } else if (round.equals("thirdRound")) {
            currentQuestionList = thirdRoundQuestionList;
            currentRoundRef = thirdRoundRef;
        } else if (round.equals("1RA")){
            currentQuestionList = exam1RAList;
            currentRoundRef = exam1RARef;
        } else if (round.equals("1RB")){
            currentQuestionList = exam1RBList;
            currentRoundRef = exam1RBRef;
        } else if (round.equals("2RA")){
            currentQuestionList = exam2RAList;
            currentRoundRef = exam2RARef;
        } else if (round.equals("2RB")){
            currentQuestionList = exam2RBList;
            currentRoundRef = exam2RBRef;
        } else if (round.equals("3RA")){
            currentQuestionList = exam3RAList;
            currentRoundRef = exam3RARef;
        } else if (round.equals("3RB")){
            currentQuestionList = exam3RBList;
            currentRoundRef = exam3RBRef;
        } else if (round.equals("FRA")){
            currentQuestionList = examFRAList;
            currentRoundRef = examFRARef;
        } else if (round.equals("FRB")){
            currentQuestionList = examFRBList;
            currentRoundRef = examFRBRef;
        }

        startStopTimer();

        timer = findViewById(R.id.timer_textView);
        questionTextView = findViewById(R.id.question_textView);
        teamNameTextView = findViewById(R.id.team_name_textView);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.answer2_editText);
        option3Button = findViewById(R.id.answer3_editText);
        option4Button = findViewById(R.id.option4_button);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        fillInTheBlankAnswerEditText = findViewById(R.id.fill_in_the_blank_answer_edit_text);
        exitButton = findViewById(R.id.exit_button);
        questionNumberTextView = findViewById(R.id.question_number_textView);

        dialog = new Dialog(ExamActivity.this);
        dialog.setContentView(R.layout.correct_answer_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.setCanceledOnTouchOutside(false);

        nextQuestionButton = dialog.findViewById(R.id.next_question_button);
        enterFillInTheBlankAnswerButton = findViewById(R.id.send_answer_button);
        correctOrIncorrectTextView = dialog.findViewById(R.id.correct_incorrect_textView);
        correctOrIncorrectImageView = dialog.findViewById(R.id.correct_incorrect_imageView);

        endDialog = new Dialog(ExamActivity.this);
        endDialog.setContentView(R.layout.end_exam_dialog);
        endDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        endDialog.setCancelable(false);
        endDialog.setCanceledOnTouchOutside(false);
        //endDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        endExitButton = endDialog.findViewById(R.id.exit_button);
        endPoints = endDialog.findViewById(R.id.final_points_textView);

        if (currentQuestionList.get(currentQuestion - 1).getQuestionType().equals("MC")) {
            multipleChoiceView.setVisibility(View.VISIBLE);
            trueOrFalseView.setVisibility(View.GONE);
            fillInTheBlankAnswerEditText.setVisibility(View.GONE);
            enterFillInTheBlankAnswerButton.setVisibility(View.GONE);
        } else if (currentQuestionList.get(currentQuestion - 1).getQuestionType().equals("TF")) {
            multipleChoiceView.setVisibility(View.GONE);
            trueOrFalseView.setVisibility(View.VISIBLE);
            fillInTheBlankAnswerEditText.setVisibility(View.GONE);
            enterFillInTheBlankAnswerButton.setVisibility(View.GONE);
        } else {
            multipleChoiceView.setVisibility(View.GONE);
            trueOrFalseView.setVisibility(View.GONE);
            fillInTheBlankAnswerEditText.setVisibility(View.VISIBLE);
            enterFillInTheBlankAnswerButton.setVisibility(View.VISIBLE);
        }

        endExitButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //onBackPressed();
            endDialog.dismiss();
        });

        nextQuestionButton.setOnClickListener(view -> {
            if (currentQuestion <= currentQuestionList.size()) {
                setDataToViews(currentQuestion);

                if (currentQuestionList.get(currentQuestion - 1).getQuestionType().equals("MC")) {
                    multipleChoiceView.setVisibility(View.VISIBLE);
                    trueOrFalseView.setVisibility(View.GONE);
                    fillInTheBlankAnswerEditText.setVisibility(View.GONE);
                    enterFillInTheBlankAnswerButton.setVisibility(View.GONE);
                } else if (currentQuestionList.get(currentQuestion - 1).getQuestionType().equals("TF")) {
                    multipleChoiceView.setVisibility(View.GONE);
                    trueOrFalseView.setVisibility(View.VISIBLE);
                    fillInTheBlankAnswerEditText.setVisibility(View.GONE);
                    enterFillInTheBlankAnswerButton.setVisibility(View.GONE);
                } else {
                    multipleChoiceView.setVisibility(View.GONE);
                    trueOrFalseView.setVisibility(View.GONE);
                    fillInTheBlankAnswerEditText.setVisibility(View.VISIBLE);
                    enterFillInTheBlankAnswerButton.setVisibility(View.VISIBLE);
                }

                fillInTheBlankAnswerEditText.setText("");

                //timer.setText("60");
                startStopTimer();

                dialog.dismiss();

            } else {
                // Place another dialog that tells the user that the exam has ended and with a
                // button to go back to the MainActivity.
                currentRoundRef.child(teamUid).child("testDone").setValue("yes");
                endPoints.setText(currentScore + " de " + currentQuestionList.size());
                endDialog.show();
            }
        });

        setDataToViews(currentQuestion);

        enterFillInTheBlankAnswerButton.setOnClickListener(view -> {
            String answer = fillInTheBlankAnswerEditText.getText().toString();
            //int timeElapsed = 120 - Integer.parseInt(timer.getText().toString());
            if (currentQuestion < 10) {
                currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(answer);
                //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
            } else {
                currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(answer);
                //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
            }

            correctOrIncorrectTextView.setText("Tu respuesta será calificada más tarde");
            correctOrIncorrectImageView.setImageResource(R.drawable.pending_icon);
            dialog.show();
            currentQuestion++;

            stopTimer();
        });

        trueButton.setOnClickListener(view -> {
            //int timeElapsed = 60 - Integer.parseInt(timer.getText().toString());
            if (currentQuestionList.get(currentQuestion - 1).getOption2().equals(currentQuestionList.get(currentQuestion - 1).getAnswer())) {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                currentScore++;
                correctOrIncorrectTextView.setText("¡Respuesta Correcta!");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_correct);
                dialog.show();
            } else {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                }
                correctOrIncorrectTextView.setText("Respuesta Incorrecta");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                dialog.show();
            }
            currentQuestion++;

            stopTimer();
            //timer.setText("60");
        });

        falseButton.setOnClickListener(view -> {
            //int timeElapsed = 60 - Integer.parseInt(timer.getText().toString());
            if (currentQuestionList.get(currentQuestion - 1).getOption1().equals(currentQuestionList.get(currentQuestion - 1).getAnswer())) {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                }
                currentScore++;
                correctOrIncorrectTextView.setText("¡Respuesta Correcta!");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_correct);
                dialog.show();
            } else {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                }
                correctOrIncorrectTextView.setText("Respuesta Incorrecta");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                dialog.show();
            }
            currentQuestion++;

            stopTimer();
            //timer.setText("60");
        });

        option1Button.setOnClickListener(view -> {
            //int timeElapsed = 60 - Integer.parseInt(timer.getText().toString());
            if (currentQuestionList.get(currentQuestion - 1).getOption1().equals(currentQuestionList.get(currentQuestion - 1).getAnswer())) {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                }
                currentScore++;
                correctOrIncorrectTextView.setText("¡Respuesta Correcta!");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_correct);
                dialog.show();
            } else {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption1());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                }
                correctOrIncorrectTextView.setText("Respuesta Incorrecta");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                dialog.show();
            }
            currentQuestion++;

            stopTimer();
            //timer.setText("60");

            /**if (currentQuestion <= practiceFragment.finalQuestionsList.size()) {
             setDataToViews(currentQuestion);
             } else {
             // Place another dialog that tells the user that the exam has ended and with a
             // button to go back to the MainActivity.
             Toast.makeText(getApplicationContext(), "Fin de Examen", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(intent);
             }*/

        });

        option2Button.setOnClickListener(view -> {
            //int timeElapsed = 60 - Integer.parseInt(timer.getText().toString());
            if (currentQuestionList.get(currentQuestion - 1).getOption2().equals(currentQuestionList.get(currentQuestion - 1).getAnswer())) {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                currentScore++;
                correctOrIncorrectTextView.setText("¡Respuesta Correcta!");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_correct);
                dialog.show();
            } else {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption2());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                correctOrIncorrectTextView.setText("Respuesta Incorrecta");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                dialog.show();
            }
            currentQuestion++;

            stopTimer();
            //timer.setText("60");

            /**if (currentQuestion <= practiceFragment.finalQuestionsList.size()) {
             setDataToViews(currentQuestion);
             } else {
             Toast.makeText(getApplicationContext(), "Fin de Examen", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(intent);
             }*/
        });

        option3Button.setOnClickListener(view -> {
            //int timeElapsed = 60 - Integer.parseInt(timer.getText().toString());
            if (currentQuestionList.get(currentQuestion - 1).getOption3().equals(currentQuestionList.get(currentQuestion - 1).getAnswer())) {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption3());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption3());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                currentScore++;
                correctOrIncorrectTextView.setText("¡Respuesta Correcta!");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_correct);
                dialog.show();
            } else {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption3());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption3());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                correctOrIncorrectTextView.setText("Respuesta Incorrecta");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                dialog.show();
            }
            currentQuestion++;

            stopTimer();
            //timer.setText("60");

            /**if (currentQuestion <= practiceFragment.finalQuestionsList.size()) {
             setDataToViews(currentQuestion);
             } else {
             Toast.makeText(getApplicationContext(), "Fin de Examen", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(intent);
             }*/
        });

        option4Button.setOnClickListener(view -> {
            //int timeElapsed = 60 - Integer.parseInt(timer.getText().toString());
            if (currentQuestionList.get(currentQuestion - 1).getOption4().equals(currentQuestionList.get(currentQuestion - 1).getAnswer())) {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption4());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption4());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                currentScore++;
                correctOrIncorrectTextView.setText("¡Respuesta Correcta!");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_correct);
                dialog.show();
            } else {
                if (currentQuestion < 10) {
                    currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption4());
                    //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                } else {
                    currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(currentQuestionList.get(currentQuestion - 1).getOption4());
                    //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                }
                correctOrIncorrectTextView.setText("Respuesta Incorrecta");
                correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                dialog.show();
            }
            currentQuestion++;

            stopTimer();
            //timer.setText("60");

            /**if (currentQuestion <= practiceFragment.finalQuestionsList.size()) {
             setDataToViews(currentQuestion);
             } else {
             Toast.makeText(getApplicationContext(), "Fin de Examen", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(intent);
             }*/
        });


    }

    public void setDataToViews(int currentQuestion) {
        questionTextView.setText(currentQuestionList.get(currentQuestion - 1).getQuestion());
        option1Button.setText("A) " + currentQuestionList.get(currentQuestion - 1).getOption1());
        option2Button.setText("B) " + currentQuestionList.get(currentQuestion - 1).getOption2());
        option3Button.setText("C) " + currentQuestionList.get(currentQuestion - 1).getOption3());
        option4Button.setText("D) " + currentQuestionList.get(currentQuestion - 1).getOption4());
        questionNumberTextView.setText(currentQuestion + " / " + currentQuestionList.size());
    }

    @Override
    public void onBackPressed() {
    }

    public void endTmeSaveData(int currentQuestion) {
        if (currentQuestionList.get(currentQuestion - 1).getQuestionType().equals("FB")) {
            // Save whatever text is currently on the editTextView as soon as the timer runs out.
            String answer = fillInTheBlankAnswerEditText.getText().toString();
            if (currentQuestion < 10) {
                currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue(answer);
                //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue("120");
            } else {
                currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue(answer);
                //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue("120");
            }
        } else {
            if (currentQuestion < 10) {
                currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("answer").setValue("No respondió a tiempo");
                //currentRoundRef.child(teamUid).child("answer" + "0" + currentQuestion).child("tiempo transcurrido").setValue("60");
            } else {
                currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("answer").setValue("No respondió a tiempo");
                //currentRoundRef.child(teamUid).child("answer" + currentQuestion).child("tiempo transcurrido").setValue("60");
            }
        }

    }

    public void startStopTimer () {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer () {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();

                if (timer.getText().equals("0:00")) {
                    Toast.makeText(getApplicationContext(), "sdfsdfds", Toast.LENGTH_SHORT).show();
                    endDialog = new Dialog(ExamActivity.this);
                    endDialog.setContentView(R.layout.end_exam_dialog);
                    endDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
                    endDialog.setCancelable(false);
                    endDialog.setCanceledOnTouchOutside(false);
                    endDialog.show();
                }

                endExitButton.setOnClickListener(view -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //onBackPressed();
                    endDialog.dismiss();
                });

            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer () {
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTimer () {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        timer.setText(timeLeftText);
    }
}