package com.example.myfavouritestracks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginMenu extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRequest();
        findViewById(R.id.sign_in_button).setOnClickListener(v -> signIn());

        mAuth = FirebaseAuth.getInstance();
    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void signIn() {
        EditText mUserName = findViewById(R.id.textPassword);
        EditText mPassword = findViewById(R.id.userName);
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    public void register(View view) {

    }

    public void onStart() {

        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    public void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Toast.makeText(this, "You are signed in", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MyFavouritesTracks.class));
        } else {
            Toast.makeText(this,"You are not signed in yet", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
            updateUI(account);
        } catch (ApiException e) {
            Log.e("Firebase Auth", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(String.valueOf(idToken), null);
        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.e("Firebase Auth", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(getApplicationContext(), MyFavouritesTracks.class));

                    } else {
                        Log.e("Firebase Auth", "signInWithCredential:failure", task.getException());
                        updateUI(null);
                    }

                    // ...
                });


    }



}