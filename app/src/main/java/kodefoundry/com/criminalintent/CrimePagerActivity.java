package kodefoundry.com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

import kodefoundry.com.criminalintent.fragment.CrimeFragment;
import kodefoundry.com.criminalintent.model.Crime;
import kodefoundry.com.criminalintent.model.CrimeLab;

public class CrimePagerActivity extends FragmentActivity {

    private static final String EXTRA_CRIME_ID = "crime-id";

    private List<Crime> crimes;

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        crimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();

        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return CrimeFragment.newInstance(crimes.get(position).getId());
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });

        for (int i=0; i<crimes.size(); i++) {
            Crime crime = crimes.get(i);
            if (crime.getId().equals(crimeId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
