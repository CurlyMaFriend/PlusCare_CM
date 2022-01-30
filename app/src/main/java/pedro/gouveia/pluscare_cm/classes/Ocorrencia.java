package pedro.gouveia.pluscare_cm.classes;

import java.util.Date;

public class Ocorrencia {

    private String id;
    private String titulo, descricao, utente_id, utente_nome;
    private String data;

    public Ocorrencia(String utente_id, String titulo, String descricao, String data) {
        this.utente_id = utente_id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", utente_id='" + utente_id + '\'' +
                ", utente_nome='" + utente_nome + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
