package pedro.gouveia.pluscare_cm.classes;

public class MedicamentoUtente {

    private String id, medicamento_id, utente_id,dose,unidade,medicamento_nome,utente_nome;

    public MedicamentoUtente(String id, String medicamento_id, String utente_id, String dose, String unidade, String medicamento_nome, String utente_nome) {
        this.id = id;
        this.medicamento_id = medicamento_id;
        this.utente_id = utente_id;
        this.dose = dose;
        this.unidade = unidade;
        this.medicamento_nome = medicamento_nome;
        this.utente_nome = utente_nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicamento_id() {
        return medicamento_id;
    }

    public void setMedicamento_id(String medicamento_id) {
        this.medicamento_id = medicamento_id;
    }

    public String getUtente_id() {
        return utente_id;
    }

    public void setUtente_id(String utente_id) {
        this.utente_id = utente_id;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getMedicamento_nome() {
        return medicamento_nome;
    }

    public void setMedicamento_nome(String medicamento_nome) {
        this.medicamento_nome = medicamento_nome;
    }

    public String getUtente_nome() {
        return utente_nome;
    }

    public void setUtente_nome(String utente_nome) {
        this.utente_nome = utente_nome;
    }
}
