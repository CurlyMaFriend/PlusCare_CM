package pedro.gouveia.pluscare_cm.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

public class DialogCreateMedicamento extends Dialog implements View.OnClickListener{

    private Button criar, cancelar;
    private TextInputLayout textInputTitulo, textInputDescription;

    public DialogCreateMedicamento(@NonNull Context context) {
        super(context);
    }


    @Override
    public void onClick(View view) {

    }
}
