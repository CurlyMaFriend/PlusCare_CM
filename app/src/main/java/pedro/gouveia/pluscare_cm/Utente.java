package pedro.gouveia.pluscare_cm;

import java.util.Date;

public class Utente {

    private String nome, morada, situacaoMatrimonial, profissao,nivelEducacao, nacionalidade, nomePreferencia, tipoSangue, deficiencias, doencas;
    private int nif, nss, cc;
    private Date dataNascimento;
    private int id;

    public Utente(int id, String nome, String morada, String situacaoMatrimonial, String profissao, String nivelEducacao, String nacionalidade, String nomePreferencia, String tipoSangue, String deficiencias, String doencas, int nif, int nss, int cc, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.situacaoMatrimonial = situacaoMatrimonial;
        this.profissao = profissao;
        this.nivelEducacao = nivelEducacao;
        this.nacionalidade = nacionalidade;
        this.nomePreferencia = nomePreferencia;
        this.tipoSangue = tipoSangue;
        this.deficiencias = deficiencias;
        this.doencas = doencas;
        this.nif = nif;
        this.nss = nss;
        this.cc = cc;
        this.dataNascimento = dataNascimento;
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

    public String getSituacaoMatrimonial() {
        return situacaoMatrimonial;
    }

    public void setSituacaoMatrimonial(String situacaoMatrimonial) {
        this.situacaoMatrimonial = situacaoMatrimonial;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getNivelEducacao() {
        return nivelEducacao;
    }

    public void setNivelEducacao(String nivelEducacao) {
        this.nivelEducacao = nivelEducacao;
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

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public String getDeficiencias() {
        return deficiencias;
    }

    public void setDeficiencias(String deficiencias) {
        this.deficiencias = deficiencias;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getNss() {
        return nss;
    }

    public void setNss(int nss) {
        this.nss = nss;
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
