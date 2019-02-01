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
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActiviity extends AppCompatActivity {
    EditText ufastname,ulastname,uemailadd,upass,uconpass,uconnumber;
    Button registerbtn;
    TextInputLayout ufastnamewrapper,ulastnamewrapper,uemailwrapper,upasswrapper,uconpasswrapper,ucontractnowrapper;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activiity);
        mAuth=FirebaseAuth.getInstance();
        ufastname=findViewById(R.id.user_fastName);
        ulastname=findViewById(R.id.user_lastName);
        uemailadd=findViewById(R.id.user_email);
        upass=findViewById(R.id.rpass);
        uconpass =findViewById(R.id.user_confirm_pass);
        uconnumber=findViewById(R.id.user_contact_number);

        ufastnamewrapper=findViewById(R.id.userfnamewrapper);
        ulastnamewrapper=findViewById(R.id.userlnamewrapper);
        uemailwrapper =findViewById(R.id.useremailwrapper);
        upasswrapper=findViewById(R.id.userpasswrapper);
        uconpasswrapper =findViewById(R.id.usercpasswrapper);
        ucontractnowrapper=findViewById(R.id.usernumberwrapper);


        registerbtn=findViewById(R.id.btn_register);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {

                } else {

                    final String fastname = ufastname.getText().toString().trim();
                     final String lastname = ulastname.getText().toString().trim();
                     final String email = uemailadd.getText().toString().trim();
                    String password = upass.getText().toString().trim();
                    String confirmpass = uconpass.getText().toString().trim();
                    final String contractnumber = uconnumber.getText().toString().trim();
                    if (fastname.isEmpty()) {
                        ufastnamewrapper.setError("Enter Firstname");
                        ufastnamewrapper.requestFocus();
                        return;
                    }

                    if (lastname.isEmpty()) {
                        ulastnamewrapper.setError("Enter Lastname");
                        ulastnamewrapper.requestFocus();
                        return;
                    }

                    if (email.isEmpty()) {
                        uemailwrapper.setError("Enter Email");
                        uemailwrapper.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        upasswrapper.setError("Enter pass");
                        upasswrapper.requestFocus();
                        return;
                    }
                    if (confirmpass.isEmpty()) {
                        uconpasswrapper.setError("Enter confirm password");
                        uconpasswrapper.requestFocus();
                        return;
                    }
                    if (!password.equals(confirmpass)) {
                        uconpasswrapper.setError("Password didn,t match");
                        uconpasswrapper.requestFocus();
                        return;
                    }
                    if (contractnumber.isEmpty()) {
                        ucontractnowrapper.setError("Enter contact number");
                        ucontractnowrapper.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user =new User(fastname,lastname,email,contractnumber);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegistrationActiviity.this, "User created sucessful", Toast.LENGTH_LONG).show();
                                            Intent intent =new Intent(RegistrationActiviity.this,LoginActivity.class);
                                            startActivity(intent);


                                        }else{
                                            Toast.makeText(RegistrationActiviity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(RegistrationActiviity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }
            }
        });

    }
}
