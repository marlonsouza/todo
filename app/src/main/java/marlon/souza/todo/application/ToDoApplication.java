package marlon.souza.todo.application;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by marlonsouza on 12/05/16.
 */
public class ToDoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    JodaTimeAndroid.init(this);
  }
}

