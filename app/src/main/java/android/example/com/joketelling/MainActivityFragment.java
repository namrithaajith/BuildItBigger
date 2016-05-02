package android.example.com.joketelling;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Context context ;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) rootview.findViewById(R.id.button_id);
        context = getActivity();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Inside btn onClick");
                tellJoke();
            }
            });
        return rootview;
    }

    public void tellJoke()
    {
        new EndpointsAsyncTask(context).execute(new Pair<Context, String>(context, "Ajith "));
    }

}
