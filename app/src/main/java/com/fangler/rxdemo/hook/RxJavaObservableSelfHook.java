package com.fangler.rxdemo.hook;

import android.util.Log;

import rx.Observable;
import rx.Subscription;
import rx.plugins.RxJavaObservableExecutionHook;

/**
 * Created by fangler on 2015/11/2.
 */
public class RxJavaObservableSelfHook extends RxJavaObservableExecutionHook {

  private static final String TAG = "MainActivity";

  private void log(String period) {
    Log.d(TAG, "RxJavaObservableSelfHook "+ period + ".." + Thread.currentThread().getName());
  }

  @Override public <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> f) {
    log("onCreate");
    return super.onCreate(f);
  }

  @Override
  public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> lift) {
    log("onLift");
    return super.onLift(lift);
  }

  @Override public <T> Throwable onSubscribeError(Throwable e) {
    log("onSubscribeError");
    return super.onSubscribeError(e);
  }

  @Override public <T> Subscription onSubscribeReturn(Subscription subscription) {
    log("onSubscribeReturn");
    return super.onSubscribeReturn(subscription);
  }

  @Override
  public <T> Observable.OnSubscribe<T> onSubscribeStart(Observable<? extends T> observableInstance, Observable.OnSubscribe<T> onSubscribe) {
    log("onSubscribeStart");
    return super.onSubscribeStart(observableInstance, onSubscribe);
  }
}
