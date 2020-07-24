package com.example.thread;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.thread)
    Button mButtonThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.thread)
    void onClick() {
        Intent intent = new Intent(this, ThreadActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rx_java)
    void goRxActivity() {
        Intent intent = new Intent(this, RxActivity.class);
        startActivity(intent);
    }
}