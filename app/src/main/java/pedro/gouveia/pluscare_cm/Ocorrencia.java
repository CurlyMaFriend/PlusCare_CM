package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Ocorrencia {

    private String titulo, descricao, id;
    private Date dataInicio;

    public Ocorrencia(String titulo, String descricao, Date dataInicio) {
        this.id = "";
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

}
