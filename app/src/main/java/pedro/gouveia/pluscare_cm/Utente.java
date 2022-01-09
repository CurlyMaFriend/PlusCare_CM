package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Utente {

    private String id, andarId, nome, morada, estadoCivil, profissao,grauEscolaridade, nacionalidade, nomePreferencia, tipoSangue, deficiencias;
    private long nif, niss, cc, nus, altura;
    private Date dataNascimento;

    public Utente(String nome, String nomePreferencia, String morada, Date dataNascimento, String estadoCivil,  String grauEscolaridade, String profissao, String nacionalidade, long altura, long cc, long nif, long niss, long nus) {
        this.id = "";
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
        this.andarId = "";
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

    public String getGrauEscolaridade() {
        return grauEscolaridade;
    }

    public void setGrauEscolaridade(String grauEscolaridade) {
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

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public long getNiss() {
        return niss;
    }

    public void setNiss(long niss) {
        this.niss = niss;
    }

    public long getCc() {
        return cc;
    }

    public void setCc(long cc) {
        this.cc = cc;
    }

    public long getNus() {
        return nus;
    }

    public void setNus(long nus) {
        this.nus = nus;
    }

    public String getAndarId() {
        return andarId;
    }

    public void setAndarId(String andarId) {
        this.andarId = andarId;
    }

    public long getAltura() {
        return altura;
    }

    public void setAltura(long altura) {
        this.altura = altura;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
