package marlon.souza.todo.model;

import com.google.common.base.Preconditions;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

/**
 * Created by marlonsouza on 13/05/16.
 */
public class Agendamento implements Serializable {

  private Long dataHoraMilliseconds;
  private String titulo;
  private String descricao;
  private Integer id;
  private LocalDateTime dataHora;

  public Long getDataHoraMilliseconds() {
    return dataHoraMilliseconds;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Integer getId() {
    return id;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public static class Builder{
    private Agendamento entity;

    public Builder(Agendamento agendamento) {
      this.entity = agendamento;
    }

    public static Builder create(){
      return new Builder(new Agendamento());
    }

    public Builder dataHoraMilliseconds(Long dataHoraMilliseconds){
      Preconditions.checkNotNull(dataHoraMilliseconds);
      entity.dataHoraMilliseconds = dataHoraMilliseconds;
      return this;
    }

    public Builder titulo(String titulo){
      Preconditions.checkNotNull(titulo);
      entity.titulo = titulo;
      return this;
    }

    public Builder descricao(String descricao){
      Preconditions.checkNotNull(descricao);
      entity.descricao = descricao;
      return this;
    }

    public Builder dataHora(LocalDateTime dataHora){
      Preconditions.checkNotNull(dataHora);
      entity.dataHora = dataHora;
      return this;
    }

    public Builder id(Integer id){
      Preconditions.checkNotNull(id);
      entity.id = id;
      return this;
    }

    public Agendamento build(){
      return entity;
    }
  }
}
