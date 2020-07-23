package com.example.thread;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadActivity extends AppCompatActivity {

    @BindView(R.id.start)
    Button mButtonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.start) void onClick(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mButtonStart.post(new Runnable() {
                    @Override
                    public void run() {
                        mButtonStart.setText("hello");
                    }
                });
            }
        }).start();
    }
}