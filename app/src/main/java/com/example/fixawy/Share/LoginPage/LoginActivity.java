package com.example.fixawy.Share.LoginPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fixawy.Client.HomePageClient.HomePageClientActivity;
import com.example.fixawy.Share.ForgetPassword.ForgetPassword;

import com.example.fixawy.R;
import com.example.fixawy.Share.RegisterPage.RegisterActivity;

import com.example.fixawy.Worker.HomePageWorker.RequestedHomePageActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {


    TextInputLayout editTextPhone, editTextPassword;
    Button buttonSignIn;
    String phone,password;
    TextView textViewSignUp,textViewForgetPassword;
    FirebaseAuth fAuth;
    ImageView gmailLogin,facebookLogin;
    GoogleSignInClient mGoogleSignInClient;
    static final int GOOGLE_SIGN_IN = 123;
    private CallbackManager mCallbackManager;
    String email,type,userName,jobTitle;
    FirebaseUser user;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPhone = findViewById(R.id.edit_phone);
        editTextPassword = findViewById(R.id.edit_password);
        buttonSignIn = findViewById(R.id.login);
        textViewSignUp=findViewById(R.id.SignUp);
        gmailLogin=findViewById(R.id.gmail_login);
        facebookLogin=findViewById(R.id.facebook_login);
        textViewForgetPassword=findViewById(R.id.forgetPassword);
        fAuth = FirebaseAuth.getInstance();
        mCallbackManager=CallbackManager.Factory.create();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        user=fAuth.getCurrentUser();
        type=getIntent().getExtras().getString("type");
        jobTitle=getIntent().getExtras().getString("jobTitle");


        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        textViewForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgetPassword.class);
                intent.putExtra("type",type);
                intent.putExtra("jobTitle",jobTitle);
                startActivity(intent);
            }
        });

        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin(mCallbackManager);
            }
        });

        gmailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInGoogle();
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneLogin();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
    }

    private void facebookLogin(CallbackManager mCallbackManager) {

        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.d("TAG2", "facebook:onCancel");
            }
            @Override
            public void onError(FacebookException error) {
                Log.d("TAG2", "facebook:onError", error);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = fAuth.getCurrentUser(); FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("name").addListenerForSingleValueEvent(listener);

                            updateUIFacebook(user);
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (fAuth.getCurrentUser() != null){
        }
    }

    // sign with google..........
    void SignInGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

                Log.w("TAG", "Google sign in failed", e);

            }
        }
        else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    //Google Authentication
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = fAuth.getCurrentUser();
                            updateUI(user);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .addListenerForSingleValueEvent(listener);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    ValueEventListener listener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.putExtra("userName",userName);
            intent.putExtra("email",email);
            intent.putExtra("type",type);
            intent.putExtra("jobTitle",jobTitle);
            startActivity(intent);
            finish();


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private void updateUIFacebook(FirebaseUser user) {
        if (user != null) {
            userName = user.getDisplayName();
            email= user.getEmail();
        }
    }

    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            userName = account.getDisplayName();
            email = account.getEmail();

        }

    }

    public void phoneLogin() {
        phone = editTextPhone.getEditText().getText().toString();
        password = editTextPassword.getEditText().getText().toString();

        if (phone.isEmpty()) {
            editTextPhone.setError("phone is required");
            editTextPhone.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
// check if Owner...
        if (type.equals("Owner")){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Client").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(phone).exists()) {
                        if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                            startActivity(new Intent(LoginActivity.this, HomePageClientActivity.class)
                                    .putExtra("phone",phone));
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
// check if worker with jobTitle...
        else if (type.equals("Worker")) {
            if (jobTitle.equals("Electricity")) {
                databaseReference.child("Worker").child("Electricity").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Plumber")){
                databaseReference.child("Worker").child("Plumber").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Carpenter")){
                databaseReference.child("Worker").child("Carpenter").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Painter")){
                databaseReference.child("Worker").child("Painter").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Tiles Handyman")){
                databaseReference.child("Worker").child("Tiles Handyman").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Mason")){
                databaseReference.child("Worker").child("Mason").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Smith")){
                databaseReference.child("Worker").child("Smith").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Parquet")){
                databaseReference.child("Worker").child("Parquet").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Gypsum")){
                databaseReference.child("Worker").child("Gypsum").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


            else if (jobTitle.equals("Marble")){
                databaseReference.child("Worker").child("Marble").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Alumetal")){
                databaseReference.child("Worker").child("Alumetal").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Glass")){
                databaseReference.child("Worker").child("Glass").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Wood")){
                databaseReference.child("Worker").child("Wood").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Curtains")){
                databaseReference.child("Worker").child("Curtains").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Satellite")){
                databaseReference.child("Worker").child("Satellite").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            else if (jobTitle.equals("Appliances Maintenance")){
                databaseReference.child("Worker").child("Appliances Maintenance").child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phone).exists()) {
                            if (dataSnapshot.child(phone).child("password").getValue(String.class).equals(password)) {
                                Toast.makeText(LoginActivity.this, jobTitle, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, RequestedHomePageActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "No Data is existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        }

    }
}