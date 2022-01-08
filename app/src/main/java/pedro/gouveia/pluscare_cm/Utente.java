package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Utente {

    private String nome, morada, estadoCivil, profissao,grauEscolaridade, nacionalidade, nomePreferencia, tipoSangue, deficiencias, altura;
    private int nif, niss, cc, nus, andarId;
    private Date dataNascimento;
    private int id;

    public Utente(int id, int andarId, String nome, String morada, String estadoCivil, String profissao, String altura, String grauEscolaridade, String nacionalidade, String nomePreferencia, String tipoSangue, String deficiencias, String doencas, int nif, int niss, int cc, int nus, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.nomePreferencia = nomePreferencia;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.grauEscolaridade = grauEscolaridade;
        this.profissao = profissao;
        this.nacionalidade = nacionalidade;
        this.altura = altura;
        this.cc = cc;
        this.nif = nif;
        this.niss = niss;
        this.nus = nus;
        this.andarId = andarId;
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
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getNivelEducacao() {
        return grauEscolaridade;
    }

    public void setNivelEducacao(String grauEscolaridade) {
        this.grauEscolaridade = grauEscolaridade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNomePreferencia() {
        return nomePreferencia;
    }

    public void setNomePreferencia(String nomePreferencia) {
        this.nomePreferencia = nomePreferencia;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getNss() {
        return niss;
    }

    public void setNss(int niss) {
        this.niss = niss;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
