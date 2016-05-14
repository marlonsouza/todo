package marlon.souza.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import marlon.souza.todo.db.daos.AgendamentoDAO;
import marlon.souza.todo.model.Agendamento;
import marlon.souza.todo.receiver.RefreshListReceiver;
import marlon.souza.todo.wizard.AgendamentoWizard;

public class MainActivity extends AppCompatActivity {

  private final MainActivity instance = this;
  private AgendamentoDAO agendamentoDAO;

  private ListView agendamentosView;
  private ListAgendamentoAdapter listAgendamentoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    RefreshListReceiver.fixeMainActivity(instance);
    RefreshListReceiver.refreshMe(instance);
    agendamentoDAO = AgendamentoDAO.of(instance);
    agendamentosView = (ListView) findViewById(R.id.list);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AgendamentoWizard.of(instance).newDateTimePickerDialog().show();
      }
    });
  }


  @Override
  protected void onResume() {
    super.onResume();

    refreshList();

  }

  public void refreshList(){
    if(this.listAgendamentoAdapter==null){
      this.listAgendamentoAdapter = new ListAgendamentoAdapter(this, R.layout.agendamento, loadItens());
      agendamentosView.setAdapter(this.listAgendamentoAdapter);
    }else{
      this.listAgendamentoAdapter.clear();
      this.listAgendamentoAdapter.addAll(loadItens());
    }
  }

  private List<Agendamento> loadItens(){
    return agendamentoDAO.listAll();
  }
}
