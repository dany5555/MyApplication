package com.vision.experienciamatutina;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    DatabaseReference examRef;
    FirebaseDatabase database;
    ArrayList<QuestionModel> questionList = new ArrayList<>();
    QuestionModel questionModel;
    String wait;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        start = findViewById(R.id.button);
        database = FirebaseDatabase.getInstance();
        examRef = database.getReference("Admin");

        examRef.child("testOpen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wait = snapshot.getValue(String.class);

                Log.e("cat", wait);

                if (wait.equals("no")) {
                    start.setVisibility(View.GONE);
                } else {
                    start.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}