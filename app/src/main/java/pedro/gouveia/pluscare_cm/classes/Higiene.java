package pedro.gouveia.pluscare_cm.classes;

import java.io.Serializable;

public class Higiene implements Serializable {

    private String id, titulo, descricao;

    public Higiene(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return "Higiene{" +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
