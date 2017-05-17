package self.com.news.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import self.com.news.R;
import self.com.news.adapters.NewsFeedViewPagerAdapter;
import self.com.news.adapters.OnFragmentInteractionListener;
import self.com.news.models.Item;

/**
 *  Main activity that contains a viewpager with the tab layout to view all the
 *  three sections
 */
public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private static final String KEY_PARCELABLE_STATE = "KeyParcelablePosition";

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private NewsFeedViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        //set title
        mToolbar.setTitle(getResources().getString(R.string.app_name));

        initViewPager();

        //restore the state
        if(savedInstanceState != null){
            int position = savedInstanceState.getInt(KEY_PARCELABLE_STATE, 0);
            mViewPager.setCurrentItem(position);
        }
    }

    /**
     * Init the views
     */
    private void initViews(){

        mViewPager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        mToolbar = (Toolbar)findViewById(R.id.activity_main_toolbar);
        mTabLayout = (TabLayout)findViewById(R.id.activity_main_tab_layout);
    }

    /**
     * Init the view pager
     */
    private void initViewPager(){

        mViewPagerAdapter = new NewsFeedViewPagerAdapter(getSupportFragmentManager());

        List<String> tabTitles = new ArrayList<String>();
        tabTitles.add(getResources().getString(R.string.fragment_label_top_stories));
        tabTitles.add(getResources().getString(R.string.fragment_label_business_feed));
        tabTitles.add(getResources().getString(R.string.fragment_label_entertainment));

        mViewPagerAdapter.setTitles(tabTitles);
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Callback method when an item is selected
     *
     * @param item - The item to view
     */
    @Override
    public void onSelectFeedItem(Item item) {

        //launch intent to view new activity
        Intent intent = new Intent(this, FeedItemViewActivity.class);
        intent.putExtra(FeedItemViewActivity.KEY_PARCELABLE_ITEM, item);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(KEY_PARCELABLE_STATE, mViewPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }
}
