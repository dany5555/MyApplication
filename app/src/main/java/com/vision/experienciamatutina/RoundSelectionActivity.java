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

public class RoundSelectionActivity extends AppCompatActivity {

    Button qualificationButton, semiFinalButton, finalButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference checkRef, checkRefFinal;

    String checkTestDone, checkTestDoneFinal, teamUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_selection);

        teamUid = getIntent().getStringExtra("id");
        checkRef = database.getReference("Answers Semi Final").child(teamUid).child("testDone");
        checkRefFinal = database.getReference("Answers Final").child(teamUid).child("testDone");

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

        checkRefFinal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checkTestDoneFinal = snapshot.getValue(String.class);
                Log.e("lol", "is test done? " + snapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        qualificationButton = findViewById(R.id.qualification_button);
        semiFinalButton = findViewById(R.id.semi_final_button);
        finalButton = findViewById(R.id.final_button);

        qualificationButton.setOnClickListener(view -> {

        });

        semiFinalButton.setOnClickListener(view -> {
            if (checkTestDone.equals("no")) {
                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                // Starts the ExamActivity
                Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
                intent.putExtra("id", teamUid);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Ya han hecho el examen", Toast.LENGTH_SHORT).show();
            }

        });

        finalButton.setOnClickListener(view -> {
            if (checkTestDoneFinal.equals("no")) {
                Toast.makeText(getApplicationContext(), "Bienvenidos", Toast.LENGTH_SHORT).show();

                // Starts the ExamActivity
                Intent intent = new Intent(getApplicationContext(), FinalExamActivity.class);
                intent.putExtra("id", teamUid);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Ya han hecho el examen", Toast.LENGTH_SHORT).show();
            }
        });
    }
}