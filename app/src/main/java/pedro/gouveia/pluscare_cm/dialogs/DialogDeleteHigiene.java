package pedro.gouveia.pluscare_cm.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pedro.gouveia.pluscare_cm.R;
import pedro.gouveia.pluscare_cm.classes.Higiene;

public class DialogDeleteHigiene extends Dialog implements View.OnClickListener {

    private Dialog d;
    private Button btnEdit, btnDel, btnCancel;
    private TextInputLayout editTituloFrame, editDescricaoFrame;
    private TextInputEditText editTitulo, editDescricao;
    private TextView dialogInfo;
    private ImageButton cancelX;
    private Higiene selectedHigiene;
    private FragmentActivity fa;
    private View v1, v2;

    public DialogDeleteHigiene(FragmentActivity aFA, Higiene aSelectedHigiene) {
        super(aFA);
        fa = aFA;
        selectedHigiene = aSelectedHigiene;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_edit);

        btnDel = findViewById(R.id.btn_Delete);
        btnEdit = findViewById(R.id.btn_Edit);
        btnCancel = findViewById(R.id.btn_Cancel);
        dialogInfo = findViewById(R.id.dialog_Info2);
        editTituloFrame = findViewById(R.id.textInputLayoutTitulo);
        editDescricaoFrame = findViewById(R.id.textInputLayoutDescription);
        editTitulo = findViewById(R.id.higieneTitleText);
        editDescricao = findViewById(R.id.higieneDescriptionText);
        cancelX = findViewById(R.id.imagemSair2);

        btnDel.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        cancelX.setOnClickListener(this);

        btnDel.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        dialogInfo.setVisibility(View.VISIBLE);

        btnEdit.setVisibility(View.GONE);
        editTituloFrame.setVisibility(View.GONE);
        editDescricaoFrame.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Delete:
                //Pedro, do this bitch
                Log.d("teste","vai fazer alguma coisa");
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
