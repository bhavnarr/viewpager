package self.com.news.adapters;

import self.com.news.models.Item;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 *
 * This listener is used to select a feed item
 */
public interface OnFragmentInteractionListener {
    void onSelectFeedItem(Item item);
}
