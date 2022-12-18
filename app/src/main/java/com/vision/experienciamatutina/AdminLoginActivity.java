package com.vision.experienciamatutina;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginActivity extends AppCompatActivity {

    Button adminLoginButton;
    FirebaseDatabase database;
    DatabaseReference adminRef;
    EditText adminPinEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        database = FirebaseDatabase.getInstance();
        adminRef = database.getReference("Admin");
        adminPinEditText = findViewById(R.id.admin_pin_edit_text);

        adminLoginButton = findViewById(R.id.admin_login_button);

        adminLoginButton.setOnClickListener(view -> {

            String id = adminPinEditText.getText().toString();

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(getApplicationContext(), "Porfavor escribe tu PIN de Administrador", Toast.LENGTH_SHORT).show();
            } else {
                adminRef.child("adminPin").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (id.equals(snapshot.getValue(String.class))) {

                            // Starts the ExamActivity
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "Acceso", Toast.LENGTH_SHORT).show();


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
    }
}