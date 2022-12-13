package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityRegisterBinding;
public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener {
    private ActivityRegisterBinding binding;
    UserDetails userDetails;
    MyDbHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myDbHelper = new MyDbHelper(this);
        userDetails = new UserDetails();
        binding.SignUpbuttonid.setOnClickListener(this);
        binding.SigninHerebuttonid.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SignUpbuttonid) {
            String name =
                    binding.signUpnameEditText.getText().toString();
            String email =
                    binding.signiUpEmailEditText.getText().toString();
            String username =
                    binding.signUpusernameEditText.getText().toString();
            String password =
                    binding.signUppasswordEditText.getText().toString();
            //checking the validity of the Name
            if (name.isEmpty()) {
                binding.signUpnameEditText.setError("Masukin Nama mu cok");
                        binding.signUpnameEditText.requestFocus();
                return;
            }
            //checking the validity of the US
            if (username.isEmpty()) {
                binding.signUpusernameEditText.setError("Masukin username mu cok");
                        binding.signUpusernameEditText.requestFocus();
                return;
            }
            //checking the validity of the Email
            if (email.isEmpty()) {
                binding.signiUpEmailEditText.setError("Masukin Email mu cok");
                        binding.signiUpEmailEditText.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                binding.signiUpEmailEditText.setError("Ngisi itu yang bener asu");
                        binding.signiUpEmailEditText.requestFocus();
                return;
            }
            //checking the validity of the password
            if (password.isEmpty()) {
                binding.signUppasswordEditText.setError("Masukin Passowrod ya asu");
                        binding.signUppasswordEditText.requestFocus();
                return;
            }
            if (password.length() < 6) {
                binding.signUppasswordEditText.setError("Passowrd itu minimal 6 blog goblok");
                        binding.signUppasswordEditText.requestFocus();
                return;
            }
            userDetails.setName(name);
            userDetails.setEmail(email);
            userDetails.setUsername(username);
            userDetails.setPassword(password);
            long rowid = myDbHelper.insertData(userDetails);
            if (rowid > 0) {
                Toast.makeText(getApplicationContext(), "Row " +
                        rowid + "is successfully inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Row inserted inserted ", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.SigninHerebuttonid) {
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
        }
    }
}