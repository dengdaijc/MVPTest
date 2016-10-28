package com.by.mvptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private EditText edtPhone, edtPassword;
    private TextView txt;
    private IMainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        txt = (TextView) findViewById(R.id.txt);

        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String phone = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        presenter.login(phone, password);
    }

    @Override
    public void loginSuccess() {
        txt.setText("login success");
    }

    @Override
    public void loginFailed(String msg) {
        txt.setText("login failed " + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.release();
        presenter = null;
    }
}
