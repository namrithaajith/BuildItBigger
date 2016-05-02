package android.example.com.joketelling;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.example.com.jokedisplay.JokeDisplayActivity;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.namritha.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


public class EndpointsAsyncTaskFree extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private ProgressDialog dialog;

    public EndpointsAsyncTaskFree(Context context) {
        this.context = context;
    }

    public EndpointsAsyncTaskFree() {

    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
   // protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("https://joke-telling.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

          context = params[0].first;
          String name = params[0].second;

        try {
             return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Fetching Jokes.....");
        dialog.show();

    }

    @Override
    protected void onPostExecute(String result) {

       // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        dialog.dismiss();
        Intent intent;
        intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.EXTRA_TEXT, result);
        context.startActivity(intent);
    }


}