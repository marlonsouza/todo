package marlon.souza.todo.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import marlon.souza.todo.model.Agendamento;
import marlon.souza.todo.receiver.AgendamentoReceiver;
import marlon.souza.todo.wizard.AgendamentoWizard;

/**
 * Created by marlonsouza on 07/05/16.
 */
public class AlarmHelper {

  public static void agendarAlarm(Context context){
    Intent intent = new Intent(context, AgendamentoReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

    Long dezSegundos = System.currentTimeMillis() + 10000;

    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.RTC_WAKEUP, dezSegundos, pendingIntent);
  }

  public static void agendarAlarmPara(Context context, Long to, Agendamento agendamento){
    Intent intent = new Intent(context, AgendamentoReceiver.class);

    intent.putExtra(AgendamentoWizard.KEY_AGENDAMENTO, agendamento);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, agendamento.getId(), intent, PendingIntent.FLAG_ONE_SHOT);

    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.RTC_WAKEUP, to, pendingIntent);
  }

}
