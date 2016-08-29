package kodefoundry.com.criminalintent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;

import kodefoundry.com.criminalintent.R;
import kodefoundry.com.criminalintent.model.Crime;

public class CrimeFragment extends Fragment {

    public static final String FRAGMENT_TAG = "crime-fragment";
    private static final String TAG = CrimeFragment.class.getSimpleName();

    private Crime crime;
    private EditText editTextField;
    private Button crimeDateButton;
    private CheckBox solvedCheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crime, container, false);

        // crime title
        editTextField = (EditText) rootView.findViewById(R.id.crime_title);
        editTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "beforeTextChanged(): '" + charSequence + "'");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                crime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        String dateString = DateFormat.format("E, MMMM d, yyyy", crime.getDate()).toString();
        crimeDateButton = (Button) rootView.findViewById(R.id.crime_date);
        crimeDateButton.setEnabled(false);
        crimeDateButton.setText(dateString);

        solvedCheckbox = (CheckBox) rootView.findViewById(R.id.crime_solved);
        solvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Log.i(TAG, "Crime marked as " + (isChecked ? "solved" : "unsolved"));
                crime.setSolved(isChecked);
            }
        });

        return rootView;
    }
}
