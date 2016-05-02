package android.example.com.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeDisplayFragment extends Fragment {

    public JokeDisplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Inside JokeDisplay fragment");
        View rootView = inflater.inflate(R.layout.jokedisplay_fragment_main, container, false);
        Intent intent = getActivity().getIntent();
        String message  = intent.getStringExtra(JokeDisplayActivity.EXTRA_TEXT);
        System.out.println("Message....."+message);
        TextView jokeTextView = (TextView) rootView.findViewById(R.id.joke_text_view);
        jokeTextView.setText(message);
        return rootView;

    }
}
