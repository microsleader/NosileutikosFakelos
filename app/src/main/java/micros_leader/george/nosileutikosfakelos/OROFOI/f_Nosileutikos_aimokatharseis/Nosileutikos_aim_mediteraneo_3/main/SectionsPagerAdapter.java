package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.Iatrikes_odigies_fragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.SinexeisFragment;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.fragments.StatheresFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {



    public SectionsPagerAdapter( FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Iatrikes_odigies_fragment(); //ChildFragment1 at position 0
            case 1:
                return new StatheresFragment(); //ChildFragment2 at position 1
            case 2:
                return new SinexeisFragment(); //ChildFragment3 at position 2
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return 3; //three fragments
    }


    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";

        switch (position)
        {
            case 0:
                title =  "Ιατρικές οδηγίες";
                break;
            case 1:
                title =  "Σταθερές μετρήσεις";
                break;
            case 2:
                title = "Συνεχείς μετρήσεις";
        }

        return title;
    }
}