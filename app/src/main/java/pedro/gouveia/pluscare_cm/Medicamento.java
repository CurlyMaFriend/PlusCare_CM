package pedro.gouveia.pluscare_cm;

public class Medicamento {

    private String id, nome, descricao, tipo;

    public Medicamento(String aId, String aNome, String aDescricao, String aTipo){
        this.id = aId;
        this.nome = aNome;
        this.descricao = aDescricao;
        this.tipo = aTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
