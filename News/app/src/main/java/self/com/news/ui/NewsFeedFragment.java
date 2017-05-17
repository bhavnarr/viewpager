package self.com.news.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import self.com.news.R;
import self.com.news.adapters.NewsFeedAdapter;
import self.com.news.adapters.OnFragmentInteractionListener;
import self.com.news.models.Item;
import self.com.news.models.NewsResponse;
import self.com.news.networking.ResponseTaskHandler;
import self.com.news.networking.ServiceManager;

/**
 * A simple {@link Fragment} subclass. This fragment is used to display a list of feed items
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NewsFeedFragment extends Fragment {

    public static final int FRAGMENT_TOP_STORIES = 0;
    public static final int FRAGMENT_BUSINESS = 1;
    public static final int FRAGMENT_ENTERTAINMENT = 2;

    public static final String KEY_ARGUMENT_TYPE = "KeyParcelableType";

    private static final String KEY_PARCELABLE_DATASET = "KeyParcelableDataset";
    private static final String KEY_PARCELABLE_LAYOUT_STATE = "KeyParcelableLayoutState";


    private RecyclerView mFeedRecyclerView;

    private NewsFeedAdapter mNewsFeedAdapter;
    private ArrayList<Item> mDataset = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private LinearLayoutManager mLinearLayoutManager;

    boolean isRestoringState = false;
    private Parcelable mParcelable;
    private int mType;

    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_business_feed, container, false);

        //get data from arguments
        mType = getArguments().getInt(KEY_ARGUMENT_TYPE, 0);

        //if we already have the list loaded, we restore the state
        if(savedInstanceState != null){

            isRestoringState = true;

            mDataset = savedInstanceState.getParcelableArrayList(KEY_PARCELABLE_DATASET);
            mParcelable = savedInstanceState.getParcelable(KEY_PARCELABLE_LAYOUT_STATE);
        }

        setupRecyclerView(view);

        //restore the state if its already saved
        if(isRestoringState){
            mLinearLayoutManager.onRestoreInstanceState(mParcelable);
        }else {
            makeApiCall();
        }

        return view;
    }

    /**
     * Setup the recycler view
     */
    private void setupRecyclerView(View view){

        mFeedRecyclerView = (RecyclerView)view.findViewById(R.id.feed_recycler_view);
        mFeedRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mFeedRecyclerView.setLayoutManager(mLinearLayoutManager);

        mNewsFeedAdapter = new NewsFeedAdapter(getActivity(), mDataset);

        mFeedRecyclerView.setAdapter(mNewsFeedAdapter);

        mNewsFeedAdapter.setOnClickListener(new NewsFeedAdapter.OnClickListener() {
            @Override
            public void onItemClick(Item item) {

                //surface the event to the activity
                if(mListener != null){
                    mListener.onSelectFeedItem(item);
                }
            }
        });
    }

    /**
     * Make an API call based on the type of content
     */
    private void makeApiCall(){

        switch(mType){

            case FRAGMENT_TOP_STORIES:{
                retrieveTopStories();
                break;
            }

            case FRAGMENT_BUSINESS:{
                retrieveBusinessFeed();
                break;
            }

            case FRAGMENT_ENTERTAINMENT:{
                retrieveEntertainmentFeed();
                break;
            }

            default:{
                break;
            }
        }
    }

    /**
     * Makes an API call to retrieve the business feed
     */
    private void retrieveBusinessFeed(){

        ServiceManager.getInstance().getBusinessNews(new ResponseTaskHandler<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse response) {

                mDataset.addAll(response.getItems());
                mNewsFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.fragment_feed_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Makes an API call to retrieve the top stories feed
     */
    private void retrieveTopStories(){

        ServiceManager.getInstance().getTopStories(new ResponseTaskHandler<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse response) {

                n   .addAll(response.getItems());
                mNewsFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.fragment_feed_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Makes an API call to retrieve the Entertainment feed
     */
    private void retrieveEntertainmentFeed(){

        ServiceManager.getInstance().getEntertainmentItems(new ResponseTaskHandler<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse response) {

                mDataset.addAll(response.getItems());
                mNewsFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.fragment_feed_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        //save the layout state
        outState.putParcelableArrayList(KEY_PARCELABLE_DATASET, mDataset);
        outState.putParcelable(KEY_PARCELABLE_LAYOUT_STATE, mLinearLayoutManager.onSaveInstanceState());

        super.onSaveInstanceState(outState);

    }

}
