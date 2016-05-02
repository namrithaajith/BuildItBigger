package android.example.com.joketelling;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.util.Pair;

import com.example.namritha.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.junit.Test;


import java.io.IOException;
import java.util.concurrent.TimeUnit;



public class AsyncTaskTest extends AndroidTestCase {

    String result;
    EndpointsAsyncTaskTest task;

    Context context;
    public AsyncTaskTest() {
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        result = null;

        task = new EndpointsAsyncTaskTest(getContext()){

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        };
    }
    @Test
    public void testAsyncReturnType() {


        try{
            //Default timeout for the GCM server is 20 seconds
            //If the .get can't get the result in 10 seconds, something is wrong anyway
            //Greater than 20 seconds results in an error string returned and requires further interpretation

            //task.execute(this);
            task.execute(new Pair<Context, String>(getContext(), "Namritha "));
            System.out.println("Result........" + result);
            result = task.get(10, TimeUnit.SECONDS);
            System.out.println("Result........" + result);
            assertNotNull(result);

        }catch (Exception e){

            fail("Timed out");
        }
    }




}

class EndpointsAsyncTaskTest extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private ProgressDialog dialog;


    public EndpointsAsyncTaskTest() {

    }

    public EndpointsAsyncTaskTest(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        // protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
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
        //context = params[0];
        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
            //return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}