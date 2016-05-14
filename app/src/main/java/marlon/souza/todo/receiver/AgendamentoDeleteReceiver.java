package marlon.souza.todo.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import marlon.souza.todo.R;
import marlon.souza.todo.db.daos.AgendamentoDAO;
import marlon.souza.todo.model.Agendamento;
import marlon.souza.todo.wizard.AgendamentoWizard;

/**
 * Created by marlonsouza on 07/05/16.
 */
public class AgendamentoDeleteReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

    Toast.makeText(context, "Um belo delete!", Toast.LENGTH_SHORT).show();

    Agendamento agendamento = (Agendamento) intent.getExtras().getSerializable(AgendamentoWizard.KEY_AGENDAMENTO);

    AgendamentoDAO.of(context).delete(agendamento);

    RefreshListReceiver.refreshMe(context);

    nm.cancel(R.string.app_name);

  }
}
