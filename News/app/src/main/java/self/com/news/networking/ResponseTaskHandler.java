package self.com.news.networking;

/**
 * A callback listener to surface api callbacks to the main level
 */
public interface ResponseTaskHandler<T> {
    void onSuccess(T response);
    void onFailure(Throwable t);
}
