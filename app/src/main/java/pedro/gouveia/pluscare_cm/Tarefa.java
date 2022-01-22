package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Tarefa {

    private int id, idFuncionario, idAndar;
    private String titulo, descricao;
    private Date dataFim, dataInicio;

    public Tarefa(int id, int idFuncionario, int idAndar, String titulo, String descricao, Date dataFim, Date dataInicio) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.idAndar = idAndar;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdAndar() {
        return idAndar;
    }

    public void setIdAndar(int idAndar) {
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

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
}
