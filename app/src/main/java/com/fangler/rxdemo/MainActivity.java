package com.fangler.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangler.rxdemo.hook.RxJavaObservableSelfHook;
import com.google.gson.internal.LinkedHashTreeMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by fangler on 2015/11/2.
 */
public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Bind(R.id.srcTv) TextView srcTv;
  @Bind(R.id.resultTv) TextView resultTv;
  @Bind(R.id.spinner) Spinner spinner;
  @Bind(R.id.timestamp) TextView timestampTv;

  private final static int[] srcList = {0, 1, 2, 3, 4, 5, 6, 7, 8};

  private String srcText;
  private StringBuilder resultText = new StringBuilder();
  private long timestamp;

  private static Map<String, Func1<Observable, Observable>> map = new LinkedHashTreeMap<>();

  private Observable one2EightObservable= null;
  private final CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    hookObservable(true);//打开hook
    /*
     * 测试subscrbeOn和observeOn，如不需要可以注释。
     */
//    subscribeTwice();

    hookObservable(false); //关闭hook

    one2EightObservable = Observable.range(0, 9, Schedulers.io());
    initMap();
    srcText = Arrays.toString(srcList);
    srcTv.setText(srcText);

    spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getKeyList(map)));
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        resultTv.setText("");
        String key = getKeyList(map).get(position);
        Log.d(TAG, "onItemSelected..." + key);
        compositeSubscription.add(map.get(key).call(one2EightObservable).compose(scheduleTransformer).subscribe(produceSubscirber()));
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });

  }

  /**
   * used for {@see subscribeOne, subscribeTwo, subscribeTwice}.
   */
  Action1<String> action1 = new Action1<String>() {
    @Override public void call(String s) {
      Log.d(TAG, "Consumer in " + Thread.currentThread().getName());
    }
  };

  /**
   * 一次subscribeOn在io线程，一次observeOn在main线程
   */
  private Subscription subscribeOne() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        Log.d(TAG, "Producer in " + Thread.currentThread().getName());
        subscriber.onNext("1");
        subscriber.onCompleted();
      }

    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(action1);
  }

  /**
   * 一次subscribeOn在io线程，一次subscribeOn在新的线程，一次observeOn在main线程
   */
  private Subscription subscribeTwo() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        Log.d(TAG, "Producer in " + Thread.currentThread().getName());
        subscriber.onNext("1");
        subscriber.onCompleted();
      }

    }).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(action1);
  }

  /**
   * 一次subscribeOn在io线程，一次observeOn在主线程，
   * 一次subscribeOn在新的线程，一次observeOn在新的线程
   */
  private Subscription subscribeTwice() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        Log.d(TAG, "Producer in " + Thread.currentThread().getName());
        subscriber.onNext("1");
        subscriber.onCompleted();
      }

    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.newThread()).subscribe(action1);
  }

  /**
   * use a self {@link RxJavaObservableSelfHook}
   */
  private void hookObservable(boolean hookIt) {
    RxJavaPlugins instance = RxJavaPlugins.getInstance();
    Class clazz = instance.getClass();
    Method method = null;
    try {
      method = clazz.getDeclaredMethod("reset");
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    if (method != null) {
      try {
        method.setAccessible(true);
        method.invoke(instance);
        if(hookIt) {
          instance.registerObservableExecutionHook(new RxJavaObservableSelfHook());
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   *
   * @param src
   * @return a list of keys from the {@param src}
   */
  private List<String> getKeyList(Map<String, Func1<Observable, Observable>> src) {
    List<String> keys = new ArrayList<>();
    for (String tmp : src.keySet()) {
      keys.add(tmp);
    }
    return keys;
  }

  /**
   * init the map
   */
  private void initMap() {
    map.put("take(5)", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.take(5);
      }
    });

    map.put("delay(1秒)", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.delay(1, TimeUnit.SECONDS);
      }
    });

    map.put("merge", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.mergeWith(Observable.create(new Observable.OnSubscribe<Subscriber>() {
          @Override public void call(Subscriber o) {
            o.onNext(11);
            o.onNext(21);
            o.onNext(31);
            o.onCompleted();
          }
        }));
      }
    });

    map.put("scan", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.scan(new Func2<Integer, Integer, Integer>() {
          @Override public Integer call(Integer o, Integer o2) {
            return o + o2;
          }
        });
      }
    });

    map.put("starWith(-1)", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.startWith(-1);
      }
    });

    map.put("last", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.last();
      }
    });

    map.put("skip(2)", new Func1<Observable, Observable>() {
      @Override public Observable call(Observable observable) {
        return observable.skip(2);
      }
    });

  }

  /**
   * @return Subscriber 处理map中对应的Observabal
   */
  private final Subscriber<Integer> produceSubscirber() {
    return new Subscriber<Integer>() {
      @Override public void onStart() {
        super.onStart();
        timestamp = System.currentTimeMillis();
        resultText.delete(0, resultText.length());
      }

      @Override public void onCompleted() {
        Log.d(TAG, "onCompleted...");
        int i = resultText.lastIndexOf("-->");
        resultText.delete(i, resultText.length());
        resultTv.setText(resultText);
        timestampTv.setText(String.format("总用时:%d毫秒", System.currentTimeMillis() - timestamp));
      }

      @Override public void onError(Throwable e) {
        Log.d(TAG, "onError..." + e.getMessage());
      }

      @Override public void onNext(Integer integer) {
        Log.d(TAG, "onNext..." + integer);
        resultText.append(integer).append("-->");
      }
    };
  }

  /**
   * 一个Transformer来在主线程观察结果
   */
  private final Observable.Transformer scheduleTransformer = new Observable.Transformer() {
    @Override public Object call(Object o) {
      return ((Observable) o).observeOn(AndroidSchedulers.mainThread());
    }
  };

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override protected void onPause() {

    compositeSubscription.unsubscribe();
    super.onPause();
  }
}
