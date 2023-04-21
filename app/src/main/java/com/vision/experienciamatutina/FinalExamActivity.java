package com.vision.experienciamatutina;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FinalExamActivity extends AppCompatActivity {

    TextView questionNumberTextView, timerTextView, questionLevelTextView, questionTextView, teamNameTextView, correctOrIncorrectTextView, endPoints;
    EditText answer1EditTextView, answer2EditTextView, answer3EditTextView;
    Button sendAnswerButton, nextQuestionButton, endExitButton;
    String teamUid;
    Dialog dialog, endDialog;
    ImageView correctOrIncorrectImageView;
    int currentQuestion = 1;
    CountDownTimer countDownTimer;
    public static ArrayList<QuestionModel> finalQuestionsList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference teamRef = database.getReference("Answers Final Round");
    DatabaseReference teamNameRef = database.getReference("Team PINS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_exam);

        teamUid = getIntent().getStringExtra("id");

        questionNumberTextView = findViewById(R.id.question_number_textView);
        timerTextView = findViewById(R.id.timer_textView);
        questionLevelTextView = findViewById(R.id.question_level_textView);
        questionTextView = findViewById(R.id.question_textView);
        teamNameTextView = findViewById(R.id.team_name_textView);
        answer1EditTextView = findViewById(R.id.answer1_editText);
        answer2EditTextView = findViewById(R.id.answer2_editText);
        answer3EditTextView = findViewById(R.id.answer3_editText);
        sendAnswerButton = findViewById(R.id.send_answer_button);

        teamNameRef.child(teamUid).child("team").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String teamName = snapshot.getValue(String.class);
                teamNameTextView.setText("Hola " + teamName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 1")) {
            countDownTimer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long l) {
                    timerTextView.setText("" + l / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0");
                    correctOrIncorrectTextView.setText("Se terminó el tiempo");
                    correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                    endTmeSaveData(currentQuestion);
                    dialog.show();
                    currentQuestion++;
                }
            }.start();
        } else if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 2")){
            countDownTimer = new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long l) {
                    timerTextView.setText("" + l / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0");
                    correctOrIncorrectTextView.setText("Se terminó el tiempo");
                    correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                    endTmeSaveData(currentQuestion);
                    dialog.show();
                    currentQuestion++;
                }
            }.start();
        } else {
            countDownTimer = new CountDownTimer(180000, 1000) {
                @Override
                public void onTick(long l) {
                    timerTextView.setText("" + l / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0");
                    correctOrIncorrectTextView.setText("Se terminó el tiempo");
                    correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                    endTmeSaveData(currentQuestion);
                    dialog.show();
                    currentQuestion++;
                }
            }.start();
        }

        setDataToViews(currentQuestion);


        dialog = new Dialog(FinalExamActivity.this);
        dialog.setContentView(R.layout.correct_answer_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.setCanceledOnTouchOutside(false);

        nextQuestionButton = dialog.findViewById(R.id.next_question_button);
        correctOrIncorrectTextView = dialog.findViewById(R.id.correct_incorrect_textView);
        correctOrIncorrectImageView = dialog.findViewById(R.id.correct_incorrect_imageView);

        endDialog = new Dialog(FinalExamActivity.this);
        endDialog.setContentView(R.layout.end_exam_dialog);
        endDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        endDialog.setCancelable(false);
        endDialog.setCanceledOnTouchOutside(false);
        //endDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        endExitButton = endDialog.findViewById(R.id.exit_button);
        endPoints = endDialog.findViewById(R.id.final_points_textView);

        if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 1")) {
            answer1EditTextView.setVisibility(View.VISIBLE);
            answer2EditTextView.setVisibility(View.GONE);
            answer3EditTextView.setVisibility(View.GONE);
        } else if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 2")) {
            answer1EditTextView.setVisibility(View.VISIBLE);
            answer2EditTextView.setVisibility(View.VISIBLE);
            answer3EditTextView.setVisibility(View.GONE);
        } else {
            answer1EditTextView.setVisibility(View.VISIBLE);
            answer2EditTextView.setVisibility(View.VISIBLE);
            answer3EditTextView.setVisibility(View.VISIBLE);
        }

        endExitButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //onBackPressed();
            endDialog.dismiss();
        });

        nextQuestionButton.setOnClickListener(view -> {
            if (currentQuestion <= finalQuestionsList.size()) {
                setDataToViews(currentQuestion);

                if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 1")) {
                    answer1EditTextView.setVisibility(View.VISIBLE);
                    answer2EditTextView.setVisibility(View.GONE);
                    answer3EditTextView.setVisibility(View.GONE);
                } else if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 2")) {
                    answer1EditTextView.setVisibility(View.VISIBLE);
                    answer2EditTextView.setVisibility(View.VISIBLE);
                    answer3EditTextView.setVisibility(View.GONE);
                } else {
                    answer1EditTextView.setVisibility(View.VISIBLE);
                    answer2EditTextView.setVisibility(View.VISIBLE);
                    answer3EditTextView.setVisibility(View.VISIBLE);
                }


                //timer.setText("60");

                if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 1")) {
                    countDownTimer = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timerTextView.setText("" + l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText("0");
                            correctOrIncorrectTextView.setText("Se terminó el tiempo");
                            correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                            endTmeSaveData(currentQuestion);
                            dialog.show();
                            currentQuestion++;

                        }
                    }.start();
                } else if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 2")){
                    countDownTimer = new CountDownTimer(120000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timerTextView.setText("" + l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText("0");
                            correctOrIncorrectTextView.setText("Se terminó el tiempo");
                            correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                            endTmeSaveData(currentQuestion);
                            dialog.show();
                            currentQuestion++;
                        }
                    }.start();
                } else {
                    countDownTimer = new CountDownTimer(180000, 1000) {
                        @Override
                        public void onTick(long l) {
                            timerTextView.setText("" + l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timerTextView.setText("0");
                            correctOrIncorrectTextView.setText("Se terminó el tiempo");
                            correctOrIncorrectImageView.setImageResource(R.drawable.ic_incorrect);
                            endTmeSaveData(currentQuestion);
                            dialog.show();
                            currentQuestion++;
                        }
                    }.start();
                }

                dialog.dismiss();

            } else {
                // Place another dialog that tells the user that the exam has ended and with a
                // button to go back to the MainActivity.
                teamRef.child(teamUid).child("testDone").setValue("yes");
                endPoints.setText("Calificación Pendiente");
                endDialog.show();
            }
        });

        sendAnswerButton.setOnClickListener(view -> {
            String answer1 = answer1EditTextView.getText().toString();
            String answer2 = answer2EditTextView.getText().toString();
            String answer3 = answer3EditTextView.getText().toString();
            int timeElapsed;

            if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 1")) {
                timeElapsed = 60 - Integer.valueOf(timerTextView.getText().toString());
                //teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                //Log.e("lol", "time elapsed: " + timeElapsed);
            } else if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 2")) {
                timeElapsed = 120 - Integer.valueOf(timerTextView.getText().toString());
                //teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                //Log.e("lol", "time elapsed: " + timeElapsed);
            } else {
                timeElapsed = 180 - Integer.valueOf(timerTextView.getText().toString());
                //teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
                //Log.e("lol", "time elapsed: " + timeElapsed);
            }

            if (currentQuestion < 10) {
                teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("parte 1").setValue(answer1);
                teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("parte 2").setValue(answer2);
                teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("parte 3").setValue(answer3);
                teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));

            } else {
                teamRef.child(teamUid).child("respuesta " + currentQuestion).child("parte 1").setValue(answer1);
                teamRef.child(teamUid).child("respuesta " + currentQuestion).child("parte 2").setValue(answer2);
                teamRef.child(teamUid).child("respuesta " + currentQuestion).child("parte 3").setValue(answer3);
                teamRef.child(teamUid).child("respuesta " + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
            }

            correctOrIncorrectTextView.setText("Tu respuesta será calificada más tarde");
            correctOrIncorrectImageView.setImageResource(R.drawable.pending_icon);
            dialog.show();
            currentQuestion++;

            countDownTimer.cancel();
        });


    }

    public void setDataToViews(int currentQuestion) {
        questionTextView.setText(finalQuestionsList.get(currentQuestion - 1).getQuestion());
        questionLevelTextView.setText(finalQuestionsList.get(currentQuestion - 1).getQuestionType());
        answer1EditTextView.setText("");
        answer2EditTextView.setText("");
        answer3EditTextView.setText("");
        questionNumberTextView.setText(currentQuestion + " de " + finalQuestionsList.size());
    }

    public void endTmeSaveData(int currentQuestion) {
        String answer1 = answer1EditTextView.getText().toString();
        String answer2 = answer2EditTextView.getText().toString();
        String answer3 = answer3EditTextView.getText().toString();

        if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 1")) {
            int timeElapsed = 60;
            teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
            //Log.e("lol", "time elapsed: " + timeElapsed);
        } else if (finalQuestionsList.get(currentQuestion - 1).getQuestionType().equals("Nivel 2")) {
            int timeElapsed = 120;
            teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
            //Log.e("lol", "time elapsed: " + timeElapsed);
        } else {
            int timeElapsed = 180;
            teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("tiempo transcurrido").setValue(String.valueOf(timeElapsed));
            //Log.e("lol", "time elapsed: " + timeElapsed);
        }

        if (currentQuestion < 10) {
            teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("parte 1").setValue(answer1);
            teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("parte 2").setValue(answer2);
            teamRef.child(teamUid).child("respuesta " + "0" + currentQuestion).child("parte 3").setValue(answer3);
        } else {
            teamRef.child(teamUid).child("respuesta " + currentQuestion).child("parte 1").setValue(answer1);
            teamRef.child(teamUid).child("respuesta " + currentQuestion).child("parte 2").setValue(answer2);
            teamRef.child(teamUid).child("respuesta " + currentQuestion).child("parte 3").setValue(answer3);
        }
    }

    @Override
    public void onBackPressed() {
    }
}