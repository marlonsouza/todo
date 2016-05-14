package marlon.souza.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import marlon.souza.todo.model.Agendamento;

/**
 * Created by marlonsouza on 13/05/16.
 */
public class ItemAdapter extends ArrayAdapter<Agendamento> {

  private View myView;

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

  public ItemAdapter(Context context, int resource) {
    super(context, resource);
  }

  public ItemAdapter(Context context, int resource, List<Agendamento> agendamentos) {
    super(context, resource, agendamentos);

  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = LayoutInflater.from(getContext());
    myView = inflater.inflate(R.layout.agendamento, null);

    Agendamento agendamento = getItem(position);

    TextView titulo = (TextView) myView.findViewById(BindText.TITULO.getId());
    TextView descricao = (TextView) myView.findViewById(BindText.DESCRICAO.getId());
    TextView dataHora = (TextView) myView.findViewById(BindText.DATA_HORA.getId());

    titulo.setText(agendamento.getTitulo());
    descricao.setText(agendamento.getDescricao());
    dataHora.setText(DateFormat.getDateTimeInstance().format(agendamento.getDataHora().toDate()));

    return myView;
  }
}
