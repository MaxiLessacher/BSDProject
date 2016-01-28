package pkgController;

import android.app.DownloadManager;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kobjects.xml.XmlReader;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import javax.xml.transform.stream.StreamResult;

import pkgClasses.Mitglied;
import pkgClasses.Ort;
import pkgEnum.ENUM_SERVICE;

/**
 * Created by David on 19.01.2016.
 */
public class ControllerGetData extends AsyncTask<String, Void, Vector<Object>> {
    private final String IP = "http://169.254.224.197:8080/GemeindeAPI/rest";
    private final String URL_ADRESSEN = IP + "/ServiceAdresse/getAdressen";
    private final String URL_HAUSHALTE = IP + "/ServiceHaushalt/getHaushalte";
    private final String URL_MITGLIEDER = IP + "/ServiceMitglieder/getMitglieder";
    private final String URL_ORTE = IP + "/ServiceOrt/getOrte";
    private final String URL_STRASSEN = IP +"/ServiceStrasse/getStrassen";
    private final String URL_VERWALTUNGSPERSONAL = IP+"/ServiceVerwaltungspersonal/getVerwaltungspersonal";
    private final String URL_WASSERZAEHLER = IP+"/ServiceWasserzaehler/getWasserzaehler";
    private final String URL_WASSERSTANDSMELDUNG = IP+"/ServiceWasserstandsmeldung/getWasserstandsmeldungen";

    public ControllerGetData() {
        super();
    }

    @Override
    protected Vector<Object> doInBackground(String... params) {
        String enum_service;
        if (params!= null) {
            enum_service = params[0];
        } else {
            enum_service = ENUM_SERVICE.SERVICE_ORT.toString();
        }
        Vector<Object> vecResult = new Vector<>();
        URL url = null;
        URLConnection conn = null;
        if (enum_service.equals(ENUM_SERVICE.SERVICE_ADRESSE.toString())) {
            url = null;
            try {
                url = new URL(URL_ADRESSEN);
                // send data to server
                conn = url.openConnection();
                //conn.setDoOutput(true);

                // get data from server

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_HAUSHALT)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_MITGLIED)) {
            try {
                url = new URL(URL_MITGLIEDER);
                // send data to server
                conn = url.openConnection();
                //conn.setDoOutput(true);
                // get data from server
                Object object = conn.getContent();
                Vector<Mitglied> vecMitglieder;
                //vecMitglieder = ((ServiceObject) object).getVecMitglieder();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_ORT.toString())) {
            try {
                url = new URL(URL_ORTE);
                // send data to server
                conn = url.openConnection();
                // get data from server
                /*******************************************************/
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String content = sb.toString();
                JSONArray jsonArray = new JSONArray(content);
                for (int i = 0; i < jsonArray.length(); i++) {
                    vecResult.add(new Ort(jsonArray.getJSONObject(i).getInt("plz"), jsonArray.getJSONObject(i).getString("ort")));
                }
                 /******************************************************/
                /*******************************************************
                String NAMESPACE = "http://192.168.1.108:8080/";
                String METHOD_NAME = "getOrte";
                String SOAP_ACTION = IP+URL_ORTE;
                String URL = IP + URL_ORTE;

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


                System.out.println("ausgabe 1");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = false;
                envelope.setOutputSoapObject(request);

                System.out.println("ausgabe 2");
                AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
                try {
                    androidHttpTransport.call(SOAP_ACTION, envelope);
                    SoapObject response = (SoapObject)envelope.getResponse();
                    Vector<Ort> vecOrt = (Vector<Ort>)response.getAttribute("orts");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }*************************************************************/

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_STRASSE)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_VERWALTUNGSPERSONAL)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_WASSERZAEHLER)) {

        } else if (enum_service.equals(ENUM_SERVICE.SERVICE_WASSERSTANDSMELDUNG)) {

        }
        return vecResult;
    }
}
