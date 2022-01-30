package pedro.gouveia.pluscare_cm.admin;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import pedro.gouveia.pluscare_cm.R;

public class dialogTest extends Dialog implements View.OnClickListener {


    public dialogTest(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
    }

    @Override
    public void onClick(View view) {

    }
}
