package pedro.gouveia.pluscare_cm;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import pedro.gouveia.pluscare_cm.classes.Higiene;

public class customDialogEditar extends Dialog implements View.OnClickListener {

    private Button btnEdit, btnCancel;
    private EditText editTitulo, editDescricao;
    private ImageButton cancelX;
    private Higiene higiene;
    private MyViewModel viewModel;
    private FragmentActivity fa;

    public customDialogEditar(FragmentActivity aFA, Higiene aHigiene) {
        super(aFA);
        higiene = aHigiene;
        fa = aFA;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_edit);

        btnEdit = (Button) findViewById(R.id.btn_Edit);
        btnCancel = (Button) findViewById(R.id.btn_Cancel);
        cancelX = (ImageButton) findViewById(R.id.imagemSair2);

        editTitulo = findViewById(R.id.higieneTitleText);
        editDescricao = findViewById(R.id.higieneDescriptionText);

        btnEdit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        cancelX.setOnClickListener(this);

        viewModel = new ViewModelProvider(fa).get(MyViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Edit:

                break;
            case R.id.btn_Cancel:

                break;
            case R.id.imagemSair2:
                dismiss();
                break;
            default:
                break;
        }
    }

}
