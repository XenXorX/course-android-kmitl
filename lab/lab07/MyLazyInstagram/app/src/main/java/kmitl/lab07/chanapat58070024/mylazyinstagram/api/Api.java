package kmitl.lab07.chanapat58070024.mylazyinstagram.api;

import kmitl.lab07.chanapat58070024.mylazyinstagram.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}
