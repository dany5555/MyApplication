package com.vision.experienciamatutina;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoundSelectionPartBActivity extends AppCompatActivity {

    Button firstRoundButton, secondRoundButton, thirdRoundButton, finalRoundButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference checkRound1, checkRound2, checkRound3, checkFinalRound;

    String firstRoundDone, secondRoundDone, thirdRoundDone, finalRoundDone, teamUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_selection_parte_b);

        teamUid = getIntent().getStringExtra("id");
        checkRound1 = database.getReference("Answers Round 1").child(teamUid).child("testDone");
        checkRound2 = database.getReference("Answers Round 2").child(teamUid).child("testDone");
        checkRound3 = database.getReference("Answers Round 3").child(teamUid).child("testDone");
        checkFinalRound = database.getReference("Answers Final Round").child(teamUid).child("testDone");

        checkRound1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firstRoundDone = snapshot.getValue(String.class);
                Log.e("lol", "is test done? " + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        checkRound2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                secondRoundDone = snapshot.getValue(String.class);
                Log.e("lol", "is test done? " + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        checkRound3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                thirdRoundDone = snapshot.getValue(String.class);
                Log.e("lol", "is test done? " + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        checkFinalRound.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                finalRoundDone = snapshot.getValue(String.class);
                Log.e("lol", "is test done? " + snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        firstRoundButton = findViewById(R.id.first_round_button);
        secondRoundButton = findViewById(R.id.second_round_button);
        thirdRoundButton = findViewById(R.id.third_round_button);
        finalRoundButton = findViewById(R.id.final_round_button);

        firstRoundButton.setOnClickListener(view -> {

            if (firstRoundDone.equals("no")) {
                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                // Starts the ExamActivity
                Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
                intent.putExtra("id", teamUid);
                intent.putExtra("round", "firstRound");

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Ya han hecho el examen", Toast.LENGTH_SHORT).show();
            }
        });

        secondRoundButton.setOnClickListener(view -> {

            if (secondRoundDone.equals("no")) {
                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                // Starts the ExamActivity
                Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
                intent.putExtra("id", teamUid);
                intent.putExtra("round", "secondRound");

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Ya han hecho el examen", Toast.LENGTH_SHORT).show();
            }
        });

        thirdRoundButton.setOnClickListener(view -> {

            if (thirdRoundDone.equals("no")) {
                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                // Starts the ExamActivity
                Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
                intent.putExtra("id", teamUid);
                intent.putExtra("round", "thirdRound");

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Ya han hecho el examen", Toast.LENGTH_SHORT).show();
            }
        });

        finalRoundButton.setOnClickListener(view -> {

            if (finalRoundDone.equals("no")) {
                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                // Starts the ExamActivity
                Intent intent = new Intent(getApplicationContext(), FinalExamActivity.class);
                intent.putExtra("id", teamUid);
                intent.putExtra("round", "finalRound");

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Ya han hecho el examen", Toast.LENGTH_SHORT).show();
            }
        });
    }
}