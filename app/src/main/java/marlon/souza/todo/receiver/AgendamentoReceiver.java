package marlon.souza.todo.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import marlon.souza.todo.R;
import marlon.souza.todo.model.Agendamento;
import marlon.souza.todo.notification.AgendamentoDeleteReceiver;
import marlon.souza.todo.wizard.AgendamentoWizard;

/**
 * Created by marlonsouza on 07/05/16.
 */
public class AgendamentoReceiver extends BroadcastReceiver {

  private static Integer max = 0;

  @Override
  public void onReceive(Context context, Intent intent) {

    Intent intentAgendaNotification = new Intent(context, AgendamentoDeleteReceiver.class);

    Agendamento agendamento = (Agendamento) intent.getExtras().getSerializable(AgendamentoWizard.KEY_AGENDAMENTO);

    intentAgendaNotification.putExtra(AgendamentoWizard.KEY_AGENDAMENTO, agendamento);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ++max, intentAgendaNotification, PendingIntent.FLAG_ONE_SHOT);

    Notification agendamentoNotification = new Notification.Builder(context)
        .setContentTitle(agendamento.getTitulo())
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentText(agendamento.getDescricao())
        .setContentIntent(pendingIntent)
        .setWhen(System.currentTimeMillis())
        .setAutoCancel(Boolean.TRUE)
        .build();

    NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    nm.notify(max, agendamentoNotification);

  }
}
