package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;


public class login extends AppCompatActivity {

    TextView signup;
    Button login;
    EditText email_lgn,passwd;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser currentUser;
    CardView googleauth;
    GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // FireBase init
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = auth.getCurrentUser();
        // Id's
        signup = findViewById(R.id.change_to_signup);
        login = findViewById(R.id.btn_login_login);
        googleauth = findViewById(R.id.img_googleauth);
        email_lgn = findViewById(R.id.edt_email_login);
        passwd = findViewById(R.id.edt_password_login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,sign_up.class);
                startActivity(intent);
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);



        googleauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignIn();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    auth.signInWithEmailAndPassword(
                            email_lgn.getText().toString(),
                            passwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(login.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });

                }
                catch (Exception e){
                    Toast.makeText(login.this, "Try Again ...", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();

        if (currentUser != null) {
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);

        }
    }

        private void SignIn() {
            Intent intent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(intent,65);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);


            if (requestCode == 65){
                Task<GoogleSignInAccount> task =GoogleSignIn.getSignedInAccountFromIntent(data);
                try {

                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d("TAG","fireBaseAuthWithGoogle:"+account.getId());
                    fireBaseAuthWithGoogle(account.getIdToken());


                } catch (ApiException e) {
                    Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void fireBaseAuthWithGoogle(String idToken){

            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            auth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(login.this, "success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.getContext(),MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }