package self.com.news.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import self.com.news.ui.NewsFeedFragment;

/**
 * A view pager adapter to load the news feed fragments
 */
public class NewsFeedViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<String> mTabTitles = new ArrayList<>();

    public NewsFeedViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        NewsFeedFragment fragment = new NewsFeedFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(NewsFeedFragment.KEY_ARGUMENT_TYPE, position);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setTitles(List<String> tabTitles){
        mTabTitles = tabTitles;
    }
}
