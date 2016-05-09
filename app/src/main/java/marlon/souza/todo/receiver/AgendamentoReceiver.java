package marlon.souza.todo.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import marlon.souza.todo.R;
import marlon.souza.todo.notification.AgendamentoNotification;

/**
 * Created by marlonsouza on 07/05/16.
 */
public class AgendamentoReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    String titulo = "Titulo";
    String tickerText = "Ticker Text";
    String mensagem = "Mensagem";

    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, AgendamentoNotification.class),0);

    Notification agendamentoNotification = new Notification.Builder(context)
        .setTicker(tickerText)
        .setContentTitle(titulo)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentText(mensagem)
        .setContentIntent(pendingIntent)
        .setWhen(System.currentTimeMillis())
        .build();

    NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    nm.notify(R.string.app_name, agendamentoNotification);
  }
}
