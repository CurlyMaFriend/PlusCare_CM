package pedro.gouveia.pluscare_cm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;

import pedro.gouveia.pluscare_cm.classes.Tarefa;

public class FragmentTarefasPorFazer extends Fragment {

    private ArrayList<Tarefa> tarefas;
    private LinearLayout containerTarefas;

    FragmentTarefasPorFazer(ArrayList<Tarefa> aTarefas){
        tarefas = aTarefas;

        if(tarefas == null){
            tarefas = new ArrayList<>();
        }
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
        containerTarefas.removeAllViews();
        for (Tarefa tarefa: tarefas){
            addCardTarefa(tarefa);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas_por_fazer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        containerTarefas = view.findViewById(R.id.tarefasPorFazerContainer);
        containerTarefas.removeAllViews();
        for (Tarefa tarefa: tarefas){
            addCardTarefa(tarefa);
        }

    }

    private void addCardTarefa(Tarefa tarefa){

        View view = getLayoutInflater().inflate(R.layout.task_card, null);

        TextView txtTaskName = view.findViewById(R.id.txtTaskName);
        TextView txtTaskType = view.findViewById(R.id.txtTaskType);
        TextView txtTaskTime = view.findViewById(R.id.txtTaskTime);
        Button btnDetailsTarefa = view.findViewById(R.id.btn_details_task);

        txtTaskName.setText(tarefa.getTitulo());

        String tipo = "";

        if(tarefa.getHigiene_id() != null && !tarefa.getHigiene_id().equals("")){
            tipo = "Higiene";
        } else if(tarefa.getMedicamento_id() != null && !tarefa.getMedicamento_id().equals("")){
            tipo = "Medicamento";
        } else {
            tipo = "Outro";
        }

        txtTaskType.setText(tipo);

        String time = "";

        try {
            LocalDateTime dateTime = LocalDateTime.parse(tarefa.getDataInicio());

            String minutes = "";

            if(dateTime.getMinute() < 10){
                minutes = "0" + dateTime.getMinute();
            } else {
                minutes = dateTime.getMinute()+"";
            }

            time = dateTime.getHour() + ":" + minutes;

            /*Calendar dataIO = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
            dataIO.setTime(sdf.parse(tarefa.getDataInicio()));

            time = dataIO.get(Calendar.HOUR) + ":" + dataIO.get(Calendar.MINUTE);*/
        } catch (Exception e) {
            time = "--:--";
            e.printStackTrace();
        }




        txtTaskTime.setText(time);

        btnDetailsTarefa.setOnClickListener(v -> {
            Log.d("teste", tarefa.toString());
            //userDetails(utente);
        });

        containerTarefas.addView(view);
    }
}