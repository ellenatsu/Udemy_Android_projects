package com.example.firestoreapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.FontsContractCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    //firebase authentication
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser curUser;

    //connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("User");

    EditText pwd_create;
    EditText email_create;
    Button signupBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupBtn = findViewById(R.id.signup);
        email_create = findViewById(R.id.email_new);
        pwd_create = findViewById(R.id.password_new);

        //authentication
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                curUser = firebaseAuth.getCurrentUser();
                if(curUser != null){
                    //already log in
                }else{
                    // no user yet
                }
            }
        };

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(email_create.getText().toString())
                && !TextUtils.isEmpty(pwd_create.getText().toString())){

                    String email = email_create.getText().toString().trim();
                    String pwd = pwd_create.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //take user to next activity
                                        curUser = firebaseAuth.getCurrentUser();
                                        assert  curUser != null;
                                        final String curUserId = curUser.getUid();

                                        //create a userMap so we can create a user in user collection in firebase
                                        Map<String, String> userObj = new HashMap<>();
                                        userObj.put("userId", curUserId);
                                        userObj.put("username", email);

                                        //add user to firebase
                                        collectionReference.add(userObj)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {

                                                        documentReference.get()
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if(Objects.requireNonNull(task.getResult()).exists()){
                                                                            String name = task.getResult().getString("username");

                                                                            // if user register successfully, move to journal activity
                                                                    }
                                                                }


                                                        });
                                                    }
                                                });
                                    }

                                }
                            });
                }else{
                    Toast.makeText(SignUpActivity.this, "empty fields detected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
