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

import com.google.android.material.textfield.TextInputLayout;

import pedro.gouveia.pluscare_cm.R;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private Dialog d;
    private Button btnEdit, btnDel, btnPub, btnSave, btnCancel;
    private EditText editTitulo;
    private TextInputLayout editTituloOuter;
    private TextView dialogInfo;
    private ImageButton cancelX;
    private int selectedNoteId;
    private String selectedNoteTitle;
    private FragmentActivity fa;
    private View v1, v2;

    public CustomDialog(FragmentActivity aFA) {
        super(aFA);
        fa = aFA;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        btnEdit = findViewById(R.id.btn_Edit);
        btnDel = findViewById(R.id.btn_Delete);
        btnPub = findViewById(R.id.btnPublishNote);
        btnSave = findViewById(R.id.btn_Save);
        btnCancel = findViewById(R.id.btn_Cancel);
        editTitulo = findViewById(R.id.edit_Titulo);
        editTituloOuter = findViewById(R.id.editTituloOuter);
        cancelX = findViewById(R.id.imageSair);
        v1 = findViewById(R.id.seperator1);
        v2 = findViewById(R.id.seperator2);

        dialogInfo = findViewById(R.id.dialog_Info);

        btnEdit.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnPub.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        cancelX.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Edit:
                btnEdit.setVisibility(View.GONE);
                btnDel.setVisibility(View.GONE);
                btnPub.setVisibility(View.GONE);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                editTituloOuter.setVisibility(View.VISIBLE);
                //editTitulo.setText(selectedNoteTitle);
                btnSave.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_Delete:
                //viewModel.deleteTitleNote(selectedNoteId);
                dismiss();
                break;
            case R.id.btnPublishNote:
                //PublishDialog sd = new PublishDialog(fa, selectedNote);
                //sd.show();

                //Window window = sd.getWindow();
               // window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                //dismiss();
                break;
            case R.id.btn_Save:
                String title = editTitulo.getText().toString();
                if(!title.equals("")){
                   // viewModel.editTitleNote(selectedNoteId, title);
                    dismiss();
                } else {
                    Toast.makeText(fa, "Title is mandatory", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_Cancel:
                btnEdit.setVisibility(View.VISIBLE);
                btnDel.setVisibility(View.VISIBLE);
                btnPub.setVisibility(View.VISIBLE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.VISIBLE);
                editTituloOuter.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                break;
            case R.id.imageSair:
                dismiss();
                break;
            default:
                break;
        }
    }

}
