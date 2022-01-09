package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Tarefa {

    private String titulo, descricao, id, idFuncionario, idAndar, estado, idUtente;
    private Date dataTarefa;

    public Tarefa(String idFuncionario, String idAndar, String titulo, String descricao, String estado, String aIdUtente, Date aDateTarefa) {
        this.id = "";
        this.idFuncionario = idFuncionario;
        this.idAndar = idAndar;
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = estado;
        this.idUtente = aIdUtente;
        this.dataTarefa = aDateTarefa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getIdAndar() {
        return idAndar;
    }

    public void setIdAndar(String idAndar) {
        this.idAndar = idAndar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataTarefa() {
        return dataTarefa;
    }

    public void setDataTarefa(Date dataTarefa) {
        this.dataTarefa = dataTarefa;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }
}
