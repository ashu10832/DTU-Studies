package com.example.dell.dtustudies;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.message;
import static com.example.dell.dtustudies.R.id.subject;

public class FeedbackActivity extends AppCompatActivity {

    EditText email;
    EditText feedback;
    Button send_btn;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setTitle("Feedback");
        email = (EditText) findViewById(R.id.email);
        feedback = (EditText) findViewById(R.id.feedback);
        send_btn = (Button) findViewById(R.id.send);
        name = (EditText) findViewById(R.id.name) ;
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback(email,feedback,name);
            }
        });

    }

    private void sendFeedback(EditText email, EditText feedback,EditText name) {

        if(isEmailValid(email.getText())&&validateText(feedback.getText()))
        {
            String subject = "FeedBack For DTU STUDIES";
            String email_id = "ashu10832@gmail.com";
            String message = "Name:  " + name.getText().toString().trim() + "\n";
            message = message + "Email:  " + email.getText().toString().trim() + "\n\n";
            message = message + feedback.getText().toString().trim();
            //Creating SendMail object
            SendMail sm = new SendMail(this, email_id, subject, message);
            //Executing sendmail to send email
            sm.execute();
        }
        else
        {
            if(!validateText(feedback.getText()))
            {
                Toast.makeText(this, "Please enter some of your valuable feedback! :)", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean validateText(Editable text) {
        return !TextUtils.isEmpty(text);
    }

    private boolean isEmailValid(Editable email) {
        if(!validateText(email))
        {
            Toast.makeText(this, "Email cannot be Empty! Try Again", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(this, "Not a valid Email Address! Try Again", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
