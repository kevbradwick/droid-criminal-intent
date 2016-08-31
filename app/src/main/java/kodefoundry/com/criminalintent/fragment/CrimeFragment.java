package kodefoundry.com.criminalintent.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import java.util.Date;
import java.util.UUID;

import kodefoundry.com.criminalintent.CrimeActivity;
import kodefoundry.com.criminalintent.R;
import kodefoundry.com.criminalintent.model.Crime;
import kodefoundry.com.criminalintent.model.CrimeLab;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime-id";
    private static final String DIALOG_DATE = "dialog-date";
    private static final String TAG = CrimeFragment.class.getSimpleName();
    private static final int REQUEST_DATE = 0;

    private Crime crime;

    private Button crimeDateButton;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            crimeDateButton.setText(crime.getDateString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(CrimeActivity.EXTRA_CRIME_ID);
        crime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crime, container, false);

        // crime title
        EditText editTextField = (EditText) rootView.findViewById(R.id.crime_title);
        editTextField.setText(crime.getTitle());
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

        crimeDateButton = (Button) rootView.findViewById(R.id.crime_date);
        crimeDateButton.setText(crime.getDateString());
        crimeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dpFragment = DatePickerFragment.newInstance(crime.getDate());
                dpFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dpFragment.show(fm, DIALOG_DATE);
            }
        });

        CheckBox solvedCheckbox = (CheckBox) rootView.findViewById(R.id.crime_solved);
        solvedCheckbox.setChecked(crime.isSolved());
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
