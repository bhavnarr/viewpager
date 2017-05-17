package self.com.news.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import self.com.news.R;

/**
 * View holder class to represent a news item
 */
public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    TextView mTitleTextView;
    ImageView mThumbnailImageView;

    public NewsItemViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_feed_title_view);
        mThumbnailImageView = (ImageView) itemView.findViewById(R.id.list_item_feed_image_view);
    }

}