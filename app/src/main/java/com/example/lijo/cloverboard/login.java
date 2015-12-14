package com.example.lijo.cloverboard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class login extends AppCompatActivity{


    private static final String TAG = "LoginActivity";

    private EditText login_email;
    private EditText login_password;
    private Button login_button;
    private TextView forgot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        if(!Controller.isFirstTime(getApplicationContext())){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        login_email=(EditText)findViewById(R.id.input_email);
        login_button=(Button)findViewById(R.id.btn_login);
        login_password=(EditText)findViewById(R.id.input_password);
        forgot=(TextView)findViewById(R.id.forgot_link);

login_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        login_start();


    }
});



forgot.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent intent = new Intent(getApplicationContext(), PasswordReset.class);

        //Intent intent=new Intent();
        //   intent.setClass(this,Signup.class);
        startActivity(intent);
        finish();




    }
});







        TextView textview1=(TextView) findViewById(R.id.link_signup);
        textview1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);

                //Intent intent=new Intent();
                //   intent.setClass(this,Signup.class);
                startActivity(intent);
                finish();

            }

        });



    }

    private void login_start() {

        Log.d(TAG, "Login");
        if (!validate()) {
            Toast.makeText(getApplicationContext(), "Input proper Credentials ", Toast.LENGTH_SHORT).show();
            return;
        }
        login_button.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(login.this,
                R.style.AppCompatAlertDialogStyle);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating User...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onLoginSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);

    }



    private void onLoginSuccess() {

        login_button.setEnabled(true);
        final Firebase ref = new Firebase("https://cloverboard.firebaseio.com");

        final String email=login_email.getText().toString().trim();
        final String password=login_password.getText().toString().trim();


        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
                Toast.makeText(getApplicationContext(), "Authenticated successfully", Toast.LENGTH_SHORT).show();
                login_email.setText("");
                login_password.setText("");
                Controller.changeStatus(login.this);
                startActivity(new Intent(login.this, MainActivity.class));
                finish();

            }

            @Override
            public void onAuthenticationError(FirebaseError error) {


                // Authenticated failed with error firebaseError
                switch (error.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        Toast.makeText(getApplicationContext(), "User Does Not Exist", Toast.LENGTH_SHORT).show();
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                        break;


                    case FirebaseError.INVALID_CREDENTIALS:
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    break;


                    case FirebaseError.UNKNOWN_ERROR:
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
break;


                    default:
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        break;
                }














            }
        };

        ref.authWithPassword(email, password, authResultHandler);







    }









    private boolean validate() {
        boolean valid = true;

        final String email=login_email.getText().toString().trim();
        final String password=login_password.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_email.setError("enter a valid email address");

            valid = false;
        } else {
            login_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            login_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            login_password.setError(null);
        }




        return valid;
    }





}
