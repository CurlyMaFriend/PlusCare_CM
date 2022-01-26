package pedro.gouveia.pluscare_cm.classes;

import java.io.Serializable;
import java.util.Date;

public class Utente implements Serializable {

    //, tipoSangue, deficiencias

    private String nome, nome_preferencia, morada, data_nascimento, estado_civil, grau_escolaridade, profissao, nacionalidade;
    private long altura, cc, nif, niss, nus, andar_id;
    private String id;

    public Utente(String nome, String nomePreferencia, String morada, String dataNascimento, String estadoCivil,  String grauEscolaridade, String profissao, String nacionalidade, long altura, long cc, long nif, long niss, long nus) {
        this.id = "0";
        this.nome = nome;
        this.nome_preferencia = nomePreferencia;
        this.morada = morada;
        this.data_nascimento = dataNascimento;
        this.estado_civil = estadoCivil;
        this.grau_escolaridade = grauEscolaridade;
        this.profissao = profissao;
        this.nacionalidade = nacionalidade;
        this.altura = altura;
        this.cc = cc;
        this.nif = nif;
        this.niss = niss;
        this.nus = nus;
        this.andar_id = -1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEstadoCivil() {
        return estado_civil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estado_civil = estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getNivelEducacao() {
        return grau_escolaridade;
    }

    public void setNivelEducacao(String grauEscolaridade) {
        this.grau_escolaridade = grauEscolaridade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNomePreferencia() {
        return nome_preferencia;
    }

    public void setNomePreferencia(String nomePreferencia) {
        this.nome_preferencia = nomePreferencia;
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public long getNss() {
        return niss;
    }

    public void setNss(long niss) {
        this.niss = niss;
    }

    public long getCc() {
        return cc;
    }

    public void setCc(long cc) {
        this.cc = cc;
    }

    public String getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.data_nascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", estado_civil='" + estado_civil + '\'' +
                ", profissao='" + profissao + '\'' +
                ", grau_escolaridade='" + grau_escolaridade + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", nome_preferencia='" + nome_preferencia + '\'' +
                ", altura='" + altura + '\'' +
                ", nif=" + nif +
                ", niss=" + niss +
                ", cc=" + cc +
                ", nus=" + nus +
                ", andar_id=" + andar_id +
                ", data_nascimento=" + data_nascimento +
                ", id=" + id +
                '}';
    }
}
