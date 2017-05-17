package self.com.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import self.com.news.R;
import self.com.news.models.Item;

/**
 * News feed adapter to populate the recycler view with the feed items
 */
public class NewsFeedAdapter extends RecyclerView.Adapter<NewsItemViewHolder> {

    private ArrayList<Item> mDataset;
    private Context mContext;
    private OnClickListener mListener;

    public NewsFeedAdapter(Context context, ArrayList<Item> dataset) {
        mContext = context;
        mDataset = dataset;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_feed_item, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {

        final Item item = mDataset.get(position);

        //load the data into the view holder
        holder.mTitleTextView.setText(item.getTitle());
        Picasso.with(mContext).load(item.getThumbnailUrl()).into(holder.mThumbnailImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mListener != null){
                    mListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnClickListener(OnClickListener listener){
        mListener = listener;
    }

    /**
     * Interface to handle onclick events
     */
    public interface OnClickListener{
        void onItemClick(Item item);
    }
}
