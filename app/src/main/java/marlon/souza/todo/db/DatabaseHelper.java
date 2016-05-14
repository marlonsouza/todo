package marlon.souza.todo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marlonsouza on 13/05/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "ToDo";
  private static final Integer VERSAO = 1;

  public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, DATABASE_NAME, factory, VERSAO);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(createTabelaAgendamento());
  }

  private String createTabelaAgendamento() {
    return new StringBuilder()
        .append("CREATE TABLE agendamento(")
        .append("_id INTEGER PRIMARY KEY,")
        .append("titulo TEXT,")
        .append("descricao TEXT,")
        .append("data_hora INTEGER);")
        .toString();
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
