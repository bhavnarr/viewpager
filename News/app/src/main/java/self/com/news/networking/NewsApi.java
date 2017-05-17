package self.com.news.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import self.com.news.models.NewsResponse;

/**
 * An interface class to define the news API
 */
public interface NewsApi {

    /**
     * API Call to get business news items
     *
     * @return - A news response object containing a list of business news items
     */
    @GET("business.json")
    Call<NewsResponse> getBusinessNews();

    /**
     * API Call to get top stories items
     *
     * @return - A news response object containing a list of top stories items
     */
    @GET("top-stories.json")
    Call<NewsResponse> getTopStories();

    /**
     * API Call to get entertainment news items
     *
     * @return - A news response object containing a list of entertainment news items
     */
    @GET("entertainment.json")
    Call<NewsResponse> getEntertainmentNews();
}
