package marlon.souza.todo.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.PersistableBundle;

import marlon.souza.todo.R;

/**
 * Created by marlonsouza on 07/05/16.
 */
public class AgendamentoNotification extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);

    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    nm.cancel(R.string.app_name);

  }
}
