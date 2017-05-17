package self.com.news.networking;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import self.com.news.models.NewsResponse;

/**
 * A helper singleton class to make and manage network requests made in the app.
 */
public class ServiceManager {

    private static final String API_BASE_URL = "http://104.236.218.197/";

    private static ServiceManager sInstance = null;

    private OkHttpClient mHttpClient = null;
    private Retrofit mRetrofit = null;
    private NewsApi mNewsApi = null;


    /**
     * Private constructor
     *
     * We setup Retrofit to use our API endpoint
     */
    private ServiceManager() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        mHttpClient = httpClientBuilder.build();

        //create the retrofit object
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(mHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //finally create the api service
        mNewsApi = mRetrofit.create(NewsApi.class);
    }

    /**
     * Helper method to get the instance of the service manager
     *
     * @return - an instance of the ServiceManager class
     */
    public static ServiceManager getInstance(){

        if(sInstance == null){
            sInstance = new ServiceManager();
        }

        return sInstance;
    }

    /**
     * Get business news items
     */
    public void getBusinessNews(final ResponseTaskHandler<NewsResponse> responseTaskHandler) {

        mNewsApi.getBusinessNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    responseTaskHandler.onSuccess(response.body());
                }else{
                    responseTaskHandler.onFailure(new Throwable());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                responseTaskHandler.onFailure(t);
            }
        });
    }

    /**
     * Get top stories
     */
    public void getTopStories(final ResponseTaskHandler<NewsResponse> responseTaskHandler){

        mNewsApi.getTopStories().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    responseTaskHandler.onSuccess(response.body());
                }else{
                    responseTaskHandler.onFailure(new Throwable());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                responseTaskHandler.onFailure(t);
            }
        });
    }

    /**
     * Get entertainment news items
     */
    public void getEntertainmentItems(final ResponseTaskHandler<NewsResponse> responseTaskHandler){

        mNewsApi.getEntertainmentNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    responseTaskHandler.onSuccess(response.body());
                }else{
                    responseTaskHandler.onFailure(new Throwable());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                responseTaskHandler.onFailure(t);
            }
        });
    }
}
