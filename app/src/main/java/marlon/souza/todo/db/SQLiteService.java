package marlon.souza.todo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.common.base.Preconditions;

/**
 * Created by marlonsouza on 13/05/16.
 */
public class SQLiteService {

  private final Context context;
  private DatabaseHelper helper;

  private SQLiteService(Context context) {
    this.context = context;
    this.helper = DatabaseHelper.of(context);
  }

  public static SQLiteService of(Context context){
    Preconditions.checkNotNull(context);

    return new SQLiteService(context);
  }

  public Long insert(String table, ContentValues values){
    SQLiteDatabase sqLiteDatabase = this.helper.getWritableDatabase();

    long result = sqLiteDatabase.insert(table, null, values);

    return result;
  }

  public Cursor list(String select){
    SQLiteDatabase sqLiteDatabase = this.helper.getWritableDatabase();

    return sqLiteDatabase.rawQuery(select, null);
  }

  public Boolean delete(String table, String id){
    SQLiteDatabase sqLiteDatabase = this.helper.getWritableDatabase();

    String where[] = new String[]{id};

    int result = sqLiteDatabase.delete(table, "_id = ?", where);

    return result != -1;
  }

}
