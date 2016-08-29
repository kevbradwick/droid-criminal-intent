package kodefoundry.com.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kodefoundry.com.criminalintent.fragment.CrimeFragment;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(CrimeFragment.FRAGMENT_TAG);

        if (fragment == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_container, new CrimeFragment(), CrimeFragment.FRAGMENT_TAG)
                    .commit();
        }
    }
}
