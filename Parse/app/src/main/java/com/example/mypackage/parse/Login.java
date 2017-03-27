package com.example.mypackage.parse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btLogin;
    TextView tvAttempt;
    ProgressDialog dialog;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPw);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvAttempt = (TextView) findViewById(R.id.tvAttempt);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                No attempts
                if(count >= 2) {
                    btLogin.setEnabled(false);
                    btLogin.setTextColor(Color.GRAY);
                    tvAttempt.setText("Attempt left : 0");
                }

//                Login success
                else if (etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")) {
                    final Intent intent = new Intent(Login.this, MainActivity.class);

                    /** Toast widget
                    Toast.makeText(Login.this, "Redirecting...", Toast.LENGTH_LONG).show();
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(3000);
                                startActivity(intent);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                     **/


                /** Using Progress Dialog **/
                    dialog = ProgressDialog.show(Login.this, "Redirecting...", "Redirecting...", true);

                    AsyncTask<Void, Void, Boolean> loginWait = new AsyncTask<Void, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(Void... params) {
                            // wait for 1.5 ms
                            try {
                                Thread.sleep(3000);
                                startActivity(intent);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            dialog.dismiss();
                            return null;
                        }
                    };
                    loginWait.execute(null, null, null);
                }

//                Login failure
                else if ( !etUsername.getText().toString().equals("admin") || !etPassword.getText().toString().equals("admin")) {
                    Toast.makeText(Login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                    tvAttempt.setText("Attempt left : " + (2 - count));
                    count++;
                }
            }
        });
    }
}
