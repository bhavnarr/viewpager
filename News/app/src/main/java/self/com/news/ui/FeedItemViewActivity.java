package self.com.news.ui;

import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import self.com.news.R;
import self.com.news.models.Item;

/**
 * Activity to view a particular feed item
 */
public class FeedItemViewActivity extends AppCompatActivity {

    public static final String KEY_PARCELABLE_ITEM = "KeyParcelableItem";

    private ImageView mImageView;
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private Toolbar mToolbar;

    private Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed_item_view);

        initViews();

        mItem = getIntent().getParcelableExtra(KEY_PARCELABLE_ITEM);

        loadItem();
    }

    /**
     * initialize the views
     */
    private void initViews(){

        mImageView = (ImageView)findViewById(R.id.activity_feed_item_image_view);
        mTitleTextView = (TextView)findViewById(R.id.activity_feed_item_title_text_view);
        mDescriptionTextView = (TextView)findViewById(R.id.activity_feed_item_desc_text_view);
        mToolbar = (Toolbar)findViewById(R.id.activity_feed_item_toolbar);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Load the information for the item on to the view
     */
    private void loadItem(){

        if(mItem != null){

            mTitleTextView.setText(mItem.getTitle());
            mDescriptionTextView.setText(mItem.getDescription());

            Picasso.with(this).load(mItem.getThumbnailUrl()).into(mImageView);

            setActionBarTitle(mItem.getTitle());
        }
    }

    /**
     * Set the title of the action bar
     */
    private void setActionBarTitle(String title){

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * Handle activity back press button
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:{
                finish();
                break;
            }
        }

        return false;
    }
}
