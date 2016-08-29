package kodefoundry.com.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import kodefoundry.com.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    Fragment createFragment() {
        return new CrimeListFragment();
    }
}
