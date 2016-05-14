package marlon.souza.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;

import marlon.souza.todo.db.daos.AgendamentoDAO;
import marlon.souza.todo.model.Agendamento;
import marlon.souza.todo.receiver.RefreshListReceiver;

/**
 * Created by marlonsouza on 13/05/16.
 */
public class ListAgendamentoAdapter extends ArrayAdapter<Agendamento> {

  private View myView;
  private List<Agendamento> agendamentos;

  private enum BindText{
    TITULO(R.id.titulo_item),
    DESCRICAO(R.id.descricao_item),
    DATA_HORA(R.id.data_hora_item);

    private int id;

    BindText(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }
  }

  public ListAgendamentoAdapter(Context context, int resource) {
    super(context, resource);
  }

  public ListAgendamentoAdapter(Context context, int resource, List<Agendamento> agendamentos) {
    super(context, resource, agendamentos);
    this.agendamentos = agendamentos;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = LayoutInflater.from(getContext());
    myView = inflater.inflate(R.layout.agendamento, null);

    final Agendamento agendamento = getItem(position);

    TextView titulo = (TextView) myView.findViewById(BindText.TITULO.getId());
    TextView descricao = (TextView) myView.findViewById(BindText.DESCRICAO.getId());
    TextView dataHora = (TextView) myView.findViewById(BindText.DATA_HORA.getId());

    Button apagarItem = (Button) myView.findViewById(R.id.delete_item);

    titulo.setText(agendamento.getTitulo());
    descricao.setText(agendamento.getDescricao());
    dataHora.setText(DateFormat.getDateTimeInstance().format(agendamento.getDataHora().toDate()));


    apagarItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new AlertDialog.Builder(myView.getContext())
            .setMessage("Deseja apagar realmente?")
            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                AgendamentoDAO.of(myView.getContext()).delete(agendamento);
                RefreshListReceiver.refreshMe(myView.getContext());
                Toast.makeText(myView.getContext(), "Agendamento removido com sucesse!", Toast.LENGTH_LONG).show();
              }
            })
            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            })
            .show();
      }
    });

    return myView;
  }
}
