package pkgController;

import android.os.AsyncTask;

import java.net.URL;

import pkgEnum.ENUM_SERVICE;

/**
 * Created by David on 19.01.2016.
 */
public class ControllerSendData extends AsyncTask<Object, Void, String> {
    @Override
    protected String doInBackground(Object... params) {
        String enum_service = params[0].toString();
        if (enum_service.equals(ENUM_SERVICE.SERVICE_ADRESSE)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_HAUSHALT)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_MITGLIED)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_ORT)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_STRASSE)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_VERWALTUNGSPERSONAL)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_WASSERZAEHLER)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_WASSERSTANDSMELDUNG)) {

        }
        return null;
    }
}
