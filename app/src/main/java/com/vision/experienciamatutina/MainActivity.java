package com.vision.experienciamatutina;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
    FirebaseDatabase database3 = FirebaseDatabase.getInstance();

    DatabaseReference teamsRef = database.getReference("Team PINS");
    DatabaseReference checkRef;
    DatabaseReference waitRef = database3.getReference("Admin");

    Button startExamButton, adminButton;
    EditText pinEditText;
    TextView waitTextView, instructionsTextView;

    DatabaseReference semiFinalExamRef, finalExamRef;
    ExamActivity examActivity;
    FinalExamActivity finalExamActivity;
    QuestionModel questionModel;

    String checkTestDone, wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        semiFinalExamRef = database.getReference("ExamSemiFinal");
        finalExamRef = database.getReference("ExamFinal");

        pinEditText = findViewById(R.id.team_pin_edit_text);
        waitTextView = findViewById(R.id.wait_text_view);
        instructionsTextView = findViewById(R.id.instructions_text_view);

        waitRef.child("testOpen").addValueEventListener(new ValueEventListener() {
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
        });

        Log.e("boo", "is test open? " + wait);



        semiFinalExamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                examActivity.finalQuestionsList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    examActivity.finalQuestionsList.add(questionModel);
                }

                //QuestionModel questionModel = snapshot.child("Pregunta 1").getValue(QuestionModel.class);
                //finalQuestionsList.add(questionModel);
                //question = snapshot.child("Pregunta 1").getValue(String.class);

                Log.e("cat", examActivity.finalQuestionsList.get(0).question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        finalExamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                finalExamActivity.finalQuestionsList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    questionModel = ds.getValue(QuestionModel.class);
                    finalExamActivity.finalQuestionsList.add(questionModel);
                }

                //QuestionModel questionModel = snapshot.child("Pregunta 1").getValue(QuestionModel.class);
                //finalQuestionsList.add(questionModel);
                //question = snapshot.child("Pregunta 1").getValue(String.class);

                //Log.e("cat", examActivity.finalQuestionsList.get(0).question);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        startExamButton = findViewById(R.id.start_exam_button);
        adminButton = findViewById(R.id.admin_button);

        startExamButton.setOnClickListener(view -> {

            String id = pinEditText.getText().toString();
            checkRef = database2.getReference("Answers").child(id).child("testDone");

            checkRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    checkTestDone = snapshot.getValue(String.class);
                    Log.e("lol", "is test done? " + snapshot);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(getApplicationContext(), "Por favor escribe tu PIN de equipo", Toast.LENGTH_SHORT).show();
            } else {
                teamsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(id)) {
                                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                                // Starts the ExamActivity
                                Intent intent = new Intent(getApplicationContext(), RoundSelectionActivity.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Por favor escribe un PIN válido", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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