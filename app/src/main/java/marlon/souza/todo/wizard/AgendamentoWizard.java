package marlon.souza.todo.wizard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.common.base.Preconditions;

import org.joda.time.LocalDateTime;

import java.util.Calendar;

import marlon.souza.todo.R;
import marlon.souza.todo.alarm.AlarmHelper;
import marlon.souza.todo.model.Agendamento;

/**
 * Created by marlonsouza on 12/05/16.
 */
public class AgendamentoWizard {
  private Activity activity;
  private LocalDateTime agendamento;
  private Calendar calendar;

  private Integer year;
  private Integer month;
  private Integer day;
  private Integer hour;
  private Integer minute;

  private Agendamento entity;

  private final Integer SECOND = 0;

  private static Integer max = 0;

  private Integer id;

  private final AgendamentoWizard instance = this;

  private AgendamentoWizard(Activity activity){
    this.activity = activity;
    this.id = ++max;
  }

  public static AgendamentoWizard of(Activity activity){
    Preconditions.checkNotNull(activity);

    return new AgendamentoWizard(activity);
  }

  private TimePickerDialog newTimePickerDialog(){

    final Calendar calendar = Calendar.getInstance();

    return new TimePickerDialog(this.activity, getListener(), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
  }

  @NonNull
  private TimePickerDialog.OnTimeSetListener getListener() {
    return new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        instance.hour = hourOfDay;
        instance.minute = minute;

        calendar = Calendar.getInstance();

        calendar.set(
            instance.year,
            instance.month,
            instance.day,
            instance.hour,
            instance.minute,
            SECOND);

        agendamento = LocalDateTime.fromCalendarFields(calendar);

      }
    };
  }

  private Dialog newDialogCadastro(){
    final AlertDialog dialog = new AlertDialog.Builder(this.activity)
        .setView(activity.getLayoutInflater().inflate(R.layout.cadastro_agendamento, null))
        .setTitle(R.string.agendar)
        .create();

    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
      @Override
      public void onShow(DialogInterface d) {
        EditText tituloEditText = (EditText) dialog.findViewById(R.id.titulo_edit_text);
        EditText descricaoEditText = (EditText) dialog.findViewById(R.id.descricao_edit_text);

        entity = Agendamento.Builder.create()
            .id(instance.id)
            .dataHoraMilliseconds(instance.agendamento.toDateTime().getMillis())
            .titulo(tituloEditText.getText().toString())
            .descricao(descricaoEditText.getText().toString())
            .build();

        AlarmHelper.agendarAlarmPara(activity, agendamento.toDateTime().getMillis(), entity);
      }
    });

    return dialog;
  }

  public DatePickerDialog newDateTimePickerDialog(){
    final Calendar calendar = Calendar.getInstance();

    return new DatePickerDialog(this.activity, getCallBackDatePicker(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
  }

  @NonNull
  private DatePickerDialog.OnDateSetListener getCallBackDatePicker() {
    return new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        instance.year = year;
        instance.month = monthOfYear;
        instance.day = dayOfMonth;

        newTimePickerDialog().show();
      }
    };
  }
}
