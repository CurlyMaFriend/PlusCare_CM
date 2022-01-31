package pedro.gouveia.pluscare_cm.classes;

public class AdminStats {

    private int ocorrencias;
    private int tarefas;

    AdminStats(int ocorrencias, int tarefas){
        this.ocorrencias = ocorrencias;
        this.tarefas = tarefas;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(int ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public int getTarefas() {
        return tarefas;
    }

    public void setTarefas(int tarefas) {
        this.tarefas = tarefas;
    }
}
