package pedro.gouveia.pluscare_cm.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Higiene;

public class DialogEditHigiene extends Dialog implements View.OnClickListener {

    private Dialog d;
    private Button btnEdit, btnDel, btnCancel;
    private TextInputLayout editTituloFrame, editDescricaoFrame;
    private TextInputEditText editTitulo, editDescricao;
    private TextView dialogInfo;
    private ImageButton cancelX;
    private Higiene selectedHigiene;
    private FragmentActivity fa;
    private View v1, v2;

    public DialogEditHigiene(FragmentActivity aFA, Higiene aSelectedHigiene) {
        super(aFA);
        fa = aFA;
        selectedHigiene = aSelectedHigiene;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_edit);

        btnEdit = findViewById(R.id.btn_Edit);
        btnDel = findViewById(R.id.btn_Delete);
        btnCancel = findViewById(R.id.btn_Cancel);
        btnCancel = findViewById(R.id.btn_Cancel);
        dialogInfo = findViewById(R.id.dialog_Info2);
        editTitulo = findViewById(R.id.higieneTitleText);
        editDescricao = findViewById(R.id.higieneDescriptionText);
        editTituloFrame = findViewById(R.id.textInputLayoutTitulo);
        editDescricaoFrame = findViewById(R.id.textInputLayoutDescription);
        cancelX = findViewById(R.id.imagemSair2);

        btnEdit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        cancelX.setOnClickListener(this);

        btnEdit.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        editTituloFrame.setVisibility(View.VISIBLE);
        editDescricaoFrame.setVisibility(View.VISIBLE);

        btnDel.setVisibility(View.GONE);
        dialogInfo.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Edit:
                String newTitle = editTitulo.getText().toString();
                if(newTitle.equals("") || newTitle == null){
                    Toast.makeText(fa, "Title is mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    newTitle = selectedHigiene.getTitulo();
                }
                String newDescription = editDescricao.getText().toString();
                selectedHigiene.setTitulo(newTitle);
                selectedHigiene.setDescricao(newDescription);
                dismiss();
                //PEDRO DO API HERE BITCH
                break;
            case R.id.btn_Cancel:
            case R.id.imagemSair2:
                dismiss();
                break;
            default:
                break;
        }
    }

}
