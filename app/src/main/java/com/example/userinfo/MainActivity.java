package com.example.userinfo; // Change to your app's package name

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private EditText fullnameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText phoneEditText;
    private EditText countryEditText;
    private EditText stateEditText;
    private EditText interestEditText;
    private EditText birthdayEditText;
    private EditText birthTimeEditText;
    private RadioGroup genderRadioGroup;
    private Button registerButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Change to your actual layout file name

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize views
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        fullnameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        phoneEditText = findViewById(R.id.phone);
        countryEditText = findViewById(R.id.country);
        stateEditText = findViewById(R.id.state);
        interestEditText = findViewById(R.id.interest);
        birthdayEditText = findViewById(R.id.birthday);
        birthTimeEditText = findViewById(R.id.birthtime);
        genderRadioGroup = findViewById(R.id.gender);
        registerButton = findViewById(R.id.registerBtn);

        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String fullname = fullnameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String country = countryEditText.getText().toString().trim();
        String state = stateEditText.getText().toString().trim();
        String interest = interestEditText.getText().toString().trim();
        String birthday = birthdayEditText.getText().toString().trim();
        String birthTime = birthTimeEditText.getText().toString().trim();

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton != null ? selectedGenderButton.getText().toString() : "";

        if (fullname.isEmpty()) {
            Toast.makeText(this, "Full name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Phone number is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (country.isEmpty()) {
            Toast.makeText(this, "Country is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (state.isEmpty()) {
            Toast.makeText(this, "State is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (interest.isEmpty()) {
            Toast.makeText(this, "Interest is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (birthday.isEmpty()) {
            Toast.makeText(this, "Birthday is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (birthTime.isEmpty()) {
            Toast.makeText(this, "Birth time is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (gender.isEmpty()) {
            Toast.makeText(this, "Gender is required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create user with Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveUserInfo(fullname, email, phone, gender, country, state, interest, birthday, birthTime, user.getUid());
                        }
                    } else {
                        // If registration fails, log the exception
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                        Toast.makeText(this, "Registration failed: " + errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void saveUserInfo(String fullname, String email, String phone, String gender, String country, String state, String interest, String birthday, String birthTime, String uid) {
        // Create a user object with the provided information
        User user = new User(fullname, email, phone, gender, country, state, interest, birthday, birthTime);

        // Save user data in Firestore under the "users" collection with the document ID as the user's UID
        db.collection("users").document(uid) // "users" is the collection name, and uid is the document ID
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(MainActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                    // Optionally, you can add code here to navigate to another activity or update the UI
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Error saving user info: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

}
