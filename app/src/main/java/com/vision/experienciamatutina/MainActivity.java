package com.vision.experienciamatutina;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference participantPinsRef = database.getReference("Participants");
    DatabaseReference secondRoundExamRef, thirdRoundExamRef, finalRoundExamRef;
    DatabaseReference exam1RARef, exam2RARef, exam3RARef, examFRARef;
    DatabaseReference exam1RBRef, exam2RBRef, exam3RBRef, examFRBRef;
    //DatabaseReference checkRef;
    //DatabaseReference waitRef = database.getReference("Admin");

    Button loginButton, adminButton;
    EditText pinEditText;
    TextView waitTextView, instructionsTextView;

    DatabaseReference firstRoundExamRef, finalExamRef;
    FinalExamActivity finalExamActivity;
    QuestionModel questionModel;

    String checkTestDone, wait, part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firstRoundExamRef = database.getReference("Exam Round 1");
        //secondRoundExamRef = database.getReference("Exam Round 2");
        //thirdRoundExamRef = database.getReference("Exam Round 3");
        //finalRoundExamRef = database.getReference("Exam Final Round");
        exam1RARef = database.getReference("Exam Round 1 Part A");
        exam2RARef = database.getReference("Exam Round 2 Part A");
        exam3RARef = database.getReference("Exam Round 3 Part A");
        examFRARef = database.getReference("Exam Final Round Part A");

        exam1RBRef = database.getReference("Exam Round 1 Part B");
        exam2RBRef = database.getReference("Exam Round 2 Part B");
        exam3RBRef = database.getReference("Exam Round 3 Part B");
        examFRBRef = database.getReference("Exam Final Round Part B");

        //finalExamRef = database.getReference("ExamFinal");

        pinEditText = findViewById(R.id.team_pin_edit_text);
        waitTextView = findViewById(R.id.wait_text_view);
        instructionsTextView = findViewById(R.id.instructions_text_view);

        /**waitRef.child("testOpen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wait = snapshot.getValue(String.class);

                if (wait.equals("no")) {
                    // Show wait text
                    waitTextView.setVisibility(View.VISIBLE);
                    startExamButton.setVisibility(View.GONE);
                    instructionsTextView.setVisibility(View.GONE);
                    pinEditText.setVisibility(View.GONE);
                } else {
                    // Show the pin edit text and start button
                    waitTextView.setVisibility(View.GONE);
                    startExamButton.setVisibility(View.VISIBLE);
                    instructionsTextView.setVisibility(View.VISIBLE);
                    pinEditText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        Log.e("boo", "is test open? " + wait);

        exam1RARef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.exam1RAList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.exam1RAList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exam2RARef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.exam2RAList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.exam2RAList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exam3RARef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.exam3RAList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.exam3RAList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        examFRARef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FinalExamActivity.examFRAList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    FinalExamActivity.examFRAList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exam1RBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.exam1RBList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.exam1RBList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exam2RBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.exam2RBList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.exam2RBList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exam3RBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.exam3RBList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.exam3RBList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        examFRBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FinalExamActivity.examFRBList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    FinalExamActivity.examFRBList.add(questionModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        /**firstRoundExamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.firstRoundQuestionList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.firstRoundQuestionList.add(questionModel);
                }

                //QuestionModel questionModel = snapshot.child("Pregunta 1").getValue(QuestionModel.class);
                //finalQuestionsList.add(questionModel);
                //question = snapshot.child("Pregunta 1").getValue(String.class);

                Log.e("cat", ExamActivity.firstRoundQuestionList.get(0).question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        secondRoundExamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.secondRoundQuestionList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.secondRoundQuestionList.add(questionModel);
                }

                //QuestionModel questionModel = snapshot.child("Pregunta 1").getValue(QuestionModel.class);
                //finalQuestionsList.add(questionModel);
                //question = snapshot.child("Pregunta 1").getValue(String.class);

                Log.e("cat", ExamActivity.secondRoundQuestionList.get(0).question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        thirdRoundExamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExamActivity.thirdRoundQuestionList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    ExamActivity.thirdRoundQuestionList.add(questionModel);
                }

                //QuestionModel questionModel = snapshot.child("Pregunta 1").getValue(QuestionModel.class);
                //finalQuestionsList.add(questionModel);
                //question = snapshot.child("Pregunta 1").getValue(String.class);

                Log.e("cat", ExamActivity.thirdRoundQuestionList.get(0).question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        finalRoundExamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FinalExamActivity.finalQuestionsList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    FinalExamActivity.finalQuestionsList.add(questionModel);
                }

                //QuestionModel questionModel = snapshot.child("Pregunta 1").getValue(QuestionModel.class);
                //finalQuestionsList.add(questionModel);
                //question = snapshot.child("Pregunta 1").getValue(String.class);

                Log.e("cat", FinalExamActivity.finalQuestionsList.get(0).question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        loginButton = findViewById(R.id.start_exam_button);
        adminButton = findViewById(R.id.admin_button);

        loginButton.setOnClickListener(view -> {

            String id = pinEditText.getText().toString();
            //checkRef = database.getReference("Answers").child(id).child("testDone");

            /**checkRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    checkTestDone = snapshot.getValue(String.class);
                    Log.e("lol", "is test done? " + snapshot);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(getApplicationContext(), "Por favor escribe tu PIN", Toast.LENGTH_SHORT).show();
            } else {
                participantPinsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(id)) {
                                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                                part = String.valueOf(snapshot.child(id).child("parte").getValue());
                                Log.e("boo", part);

                                if (part.equals("Parte A")){
                                    // Start round selection activity for Part A.
                                    Intent intent = new Intent(getApplicationContext(), RoundSelectionPartAActivity.class);
                                    intent.putExtra("id", id);
                                    startActivity(intent);

                                } else if (part.equals("Parte B")){
                                    // Start round selection activity for Part B.
                                    Intent intent = new Intent(getApplicationContext(), RoundSelectionPartBActivity.class);
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                }
                        } else {
                            Toast.makeText(getApplicationContext(), "Por favor escribe un PIN válido", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Verifica tu conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        adminButton.setOnClickListener(view -> {
            // Starts the AdminActivity
            Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
            startActivity(intent);
        });

    }
}