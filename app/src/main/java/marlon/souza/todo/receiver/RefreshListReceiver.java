package marlon.souza.todo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import marlon.souza.todo.MainActivity;

/**
 * Created by marlonsouza on 14/05/16.
 */
public class RefreshListReceiver extends BroadcastReceiver{

  private static MainActivity mainActivity;

  public static void fixeMainActivity(MainActivity to){
    mainActivity = to;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    if(mainActivity!=null){
      mainActivity.refreshList();
    }
  }

  public static void refreshMe(Context context){
    Intent refreshListaAgendamento = new Intent(context, RefreshListReceiver.class);

    context.sendBroadcast(refreshListaAgendamento);
  }

}
