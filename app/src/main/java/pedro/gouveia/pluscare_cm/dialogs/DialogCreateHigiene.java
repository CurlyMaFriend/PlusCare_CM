package pedro.gouveia.pluscare_cm.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Higiene;
import pedro.gouveia.pluscare_cm.classes.Medicamento;
import pedro.gouveia.pluscare_cm.firebaseManager.FunctionsManager;

public class DialogCreateHigiene extends Dialog implements View.OnClickListener {

    private Button btnCreate, btnCancel;
    private TextInputLayout select;
    private AutoCompleteTextView txtTipoMeds;
    private TextInputEditText editTitulo, editDescricao;
    private ImageButton cancelX;
    private FunctionsManager functionsManager;

    public DialogCreateHigiene(FragmentActivity aFA, FunctionsManager fM){
        super(aFA);
        functionsManager = fM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_add_medicamento);

        btnCreate = findViewById(R.id.btn_Create_Medicamento);
        btnCancel = findViewById(R.id.btn_Cancel_Medicamento);
        editTitulo = findViewById(R.id.medicamentoNomeText);
        editDescricao = findViewById(R.id.medicamentoDescriptionText);
        cancelX = findViewById(R.id.imagemSair4);

        select = findViewById(R.id.selectTipoMedicamento);
        select.setVisibility(View.GONE);

        txtTipoMeds = findViewById(R.id.txtTipoMedicamento);
        txtTipoMeds.setVisibility(View.GONE);

        btnCreate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        cancelX.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Create_Medicamento:
                Higiene hig = new Higiene(editTitulo.getText().toString(),editDescricao.getText().toString());
                functionsManager.addHigiene(hig);
                dismiss();
                break;
            case R.id.btn_Cancel_Medicamento:
            case R.id.imagemSair4:
                dismiss();
                break;
            default:
                break;
        }
    }

}
