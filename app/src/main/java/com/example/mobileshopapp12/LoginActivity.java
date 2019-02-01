package com.example.mobileshopapp12;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout useremailwrapper,userpasswordWrapper;
    EditText email,password;
    Button loginbtn;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        useremailwrapper=findViewById(R.id.useremailwrapper);
        userpasswordWrapper=findViewById(R.id.userpasswrapper);
        email=findViewById(R.id.user_email);
        password=findViewById(R.id.user_password);
        loginbtn=findViewById(R.id.login_btn);
        mAuth=FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                if (Email.isEmpty()){
                    useremailwrapper.setError("Enter Email");
                    useremailwrapper.requestFocus();
                    return;
                }
                if (Password.isEmpty()){
                    userpasswordWrapper.setError("Enter Password");
                    userpasswordWrapper.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent=new Intent(LoginActivity.this,ShopActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            
                        }else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        
                    }
                });


            }
        });
    }
}
