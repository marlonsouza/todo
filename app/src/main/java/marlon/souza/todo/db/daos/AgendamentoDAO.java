package marlon.souza.todo.db.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.joda.time.LocalDateTime;

import java.util.Date;
import java.util.List;

import marlon.souza.todo.db.SQLiteService;
import marlon.souza.todo.model.Agendamento;

/**
 * Created by marlonsouza on 14/05/16.
 */
public class AgendamentoDAO {

  private SQLiteService service;
  private static final String TABLE = "agendamento";

  private AgendamentoDAO(Context context) {
    this.service = SQLiteService.of(context);
  }

  public static AgendamentoDAO of(Context context){
    Preconditions.checkNotNull(context);

    return new AgendamentoDAO(context);
  }

  public Agendamento insert(Agendamento agendamento){
    long id = this.service.insert(TABLE, toContentValues(agendamento));

    if(id==-1){
      return null;
    }

    Agendamento persisted = Agendamento.Builder.from(agendamento).id(id).build();

    return persisted;

  }

  public Boolean delete(Agendamento agendamento){
    return this.service.delete(TABLE, String.valueOf(agendamento.getId()));
  }

  private ContentValues toContentValues(Agendamento agendamento){
    ContentValues contentValues = new ContentValues();

    contentValues.put("data_hora",agendamento.getDataHoraMilliseconds());
    contentValues.put("descricao",agendamento.getDescricao());
    contentValues.put("titulo", agendamento.getTitulo());

    return contentValues;
  }

  public List<Agendamento> listAll(){
    StringBuilder sqlBuilder = new StringBuilder();

    sqlBuilder
        .append("SELECT _id,")
        .append("titulo,")
        .append("descricao,")
        .append("data_hora")
        .append(" FROM agendamento");

    Cursor cursorAgendamentos = this.service.list(sqlBuilder.toString());

    cursorAgendamentos.moveToFirst();

    List<Agendamento> agendamentos = Lists.newArrayList();

    for (int i =0; i<cursorAgendamentos.getCount();i++){
      int column = 0;
      Agendamento.Builder agendamentoBuilder = Agendamento.Builder.create();

      agendamentoBuilder.id(cursorAgendamentos.getLong(column++));
      agendamentoBuilder.titulo(cursorAgendamentos.getString(column++));
      agendamentoBuilder.descricao(cursorAgendamentos.getString(column++));

      long dataHoraMilliseconds = cursorAgendamentos.getLong(column++);
      agendamentoBuilder.dataHoraMilliseconds(dataHoraMilliseconds);
      agendamentoBuilder.dataHora(LocalDateTime.fromDateFields(new Date(dataHoraMilliseconds)));

      agendamentos.add(agendamentoBuilder.build());

      cursorAgendamentos.moveToNext();
    }

    return agendamentos;
  }

}
