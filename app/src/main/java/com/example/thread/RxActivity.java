package com.example.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    @BindView(R.id.rx_start)
    Button mButton;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.rx_start)
    void onclick() {

        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 1; i <= 10; i++) {
                    SystemClock.sleep(1000);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                mButton.setEnabled(false);
            }

            @Override
            public void onNext(String value) {
                mButton.setText(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                mButton.setEnabled(true);
            }
        };

        integerObservable.map(item -> "the number is " + item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    protected void onDestroy() {
        if (Objects.nonNull(disposable) && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}