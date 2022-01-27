package pedro.gouveia.pluscare_cm.classes;

import java.util.Date;

public class Tarefa {

    private String id, funcionario_id, utente_id, andar_id, medicamento_id, higiene_id;
    private String titulo, descricao;
    private String dataFim, dataInicio, estado, tipo;

    public Tarefa(String id, String funcionario_id, String utente_id, String andar_id, String titulo, String descricao, Date dataFim, Date dataInicio) {
        this.id = id;
        this.funcionario_id = funcionario_id;
        this.andar_id = andar_id;
        this.utente_id = utente_id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio.toString();
        this.dataFim = dataFim.toString();
    }

    public String getTipo() {
        return estado;
    }

    public void setTipo(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(String funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public String getAndar_id() {
        return andar_id;
    }

    public void setAndar_id(String andar_id) {
        this.andar_id = andar_id;
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

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim.toString();
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio.toString();
    }

    public String getMedicamento_id() {
        return medicamento_id;
    }

    public void setMedicamento_id(String medicamento_id) {
        this.medicamento_id = medicamento_id;
    }

    public String getHigiene_id() {
        return higiene_id;
    }

    public void setHigiene_id(String higiene_id) {
        this.higiene_id = higiene_id;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id='" + id + '\'' +
                ", funcionario_id='" + funcionario_id + '\'' +
                ", utente_id='" + utente_id + '\'' +
                ", andar_id='" + andar_id + '\'' +
                ", medicamento_id='" + medicamento_id + '\'' +
                ", higiene_id='" + higiene_id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
