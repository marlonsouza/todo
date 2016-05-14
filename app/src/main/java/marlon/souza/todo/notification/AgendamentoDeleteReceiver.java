package marlon.souza.todo.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import marlon.souza.todo.R;

/**
 * Created by marlonsouza on 07/05/16.
 */
public class AgendamentoDeleteReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

    Toast.makeText(context, "Um belo delete!", Toast.LENGTH_SHORT).show();

    nm.cancel(R.string.app_name);

  }
}
