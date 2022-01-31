package pedro.gouveia.pluscare_cm.viewModels;

import androidx.lifecycle.MutableLiveData;

public class ViewModelUtente extends androidx.lifecycle.ViewModel {

    public MutableLiveData<Integer> goBack = new MutableLiveData<>();
    public MutableLiveData<String> addUtenteError = new MutableLiveData<>();
    public MutableLiveData<String> addUtenteSuccess = new MutableLiveData<>();

    public MutableLiveData<Integer> getGoBack() {
        return goBack;
    }

    public void setGoBack(Integer goBack) {
        this.goBack.setValue(goBack);
    }

    public MutableLiveData<String> getAddUtenteError() {
        return addUtenteError;
    }

    public void setAddUtenteError(String utenteError) {
        this.addUtenteError.setValue(utenteError);
    }
    public MutableLiveData<String> getAddUtenteSuccess() {
        return addUtenteSuccess;
    }

    public void setAddUtenteSuccess(String utenteSuccess) {
        this.addUtenteSuccess.setValue(utenteSuccess);
    }
}
