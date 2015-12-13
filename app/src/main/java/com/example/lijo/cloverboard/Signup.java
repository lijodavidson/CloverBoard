package com.example.lijo.cloverboard;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import static com.example.lijo.cloverboard.R.drawable.ic_action_content_drafts;

public class Signup extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private EditText signup_email;
    private EditText signup_password;
    private EditText signup_name;
    private Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_signup);

        signup_email=(EditText)findViewById(R.id.input_email);
        signup_password=(EditText)findViewById(R.id.input_password);
        signup_name=(EditText)findViewById(R.id.input_name);
        signup_button=(Button)findViewById(R.id.btn_signup);


        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                signup();


            }


        });





    }

    private void signup() {





        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signup_button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AppCompatAlertDialogStyle);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();




        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);




    }

    private void onSignupSuccess() {
        signup_button.setEnabled(true);



      final   String crt_name=signup_name.getText().toString().trim();
        final String crt_email=signup_email.getText().toString().trim();
       final  String crt_password=signup_password.getText().toString().trim();

     final    Firebase ref = new Firebase("https://cloverboard.firebaseio.com");


        ref.createUser(crt_email, crt_password, new Firebase.ValueResultHandler<Map<String, Object>>()

        {
            @Override
            public void onSuccess(Map<String, Object> result) {

                Firebase postRef = ref.child("users");

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", crt_name);
                map.put("email",crt_email);
                map.put("uid",result.get("uid"));

                postRef.push().setValue(map);


                Toast.makeText(getApplicationContext(), "user created", Toast.LENGTH_SHORT).show();

                      /*  System.out.println("Successfully created user account with uid: " + result.get("uid"));*/


                setResult(RESULT_OK, null);
                finish();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error

                onSignupFailed();

            }
        });


















    }

    private void onSignupFailed() {


        Toast.makeText(getApplicationContext(), "error user id already exist", Toast.LENGTH_SHORT).show();


    }






    public boolean validate() {
        boolean valid = true;

        String name = signup_name.getText().toString();
        String email = signup_email.getText().toString();
        String password = signup_password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {

            signup_name.setError("at least 3 characters");
            valid = false;
        } else {
            signup_name.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signup_email.setError("enter a valid email address");

            valid = false;
        } else {
            signup_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            signup_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            signup_password.setError(null);
        }

        return valid;
    }
















}
