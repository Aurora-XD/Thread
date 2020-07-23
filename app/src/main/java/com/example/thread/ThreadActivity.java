package com.example.thread;

import android.os.Bundle;
import android.os.SystemClock;
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

    @OnClick(R.id.start)
    void onClick() {
        new Thread(new Runnable() {
            private int index = 0;

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    SystemClock.sleep(1000);
                    index++;
                    setButtonText(mButtonStart, String.valueOf(index));
                }
            }
        }).start();
    }

    private void setButtonText(Button button, String text) {
        mButtonStart.post(new Runnable() {
            @Override
            public void run() {
                mButtonStart.setText(text);
            }
        });
    }
}