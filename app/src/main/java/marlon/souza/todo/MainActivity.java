package marlon.souza.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.joda.time.LocalDateTime;

import java.util.List;

import marlon.souza.todo.model.Agendamento;
import marlon.souza.todo.wizard.AgendamentoWizard;

public class MainActivity extends AppCompatActivity {

  private final MainActivity instance = this;

  private ListView agendamentosView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    agendamentosView = (ListView) findViewById(R.id.list);
    refreshList();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AgendamentoWizard.of(instance).newDateTimePickerDialog().show();
      }
    });
  }


  public void refreshList(){
    agendamentosView.setAdapter(new ItemAdapter(this, R.layout.agendamento, loadItens()));
  }

  private List<Agendamento> loadItens(){
    return ImmutableList.copyOf(mockList());
  }

  private List<Agendamento> mockList(){

    final List<Agendamento> agendamentosMock = Lists.newArrayList();

    Stream.of(ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .forEach(new Consumer<Integer>() {
          @Override
          public void accept(Integer value) {
            agendamentosMock.add(mock(value));
          }
        });



    return agendamentosMock;
  }

  private Agendamento mock(Integer id){
    return Agendamento.Builder.create()
        .dataHora(LocalDateTime.now())
        .descricao("Agenda "+id)
        .titulo("Titulo Agenda "+id)
        .dataHoraMilliseconds(LocalDateTime.now().toDateTime().getMillis())
        .id(id)
        .build();
  }
}
