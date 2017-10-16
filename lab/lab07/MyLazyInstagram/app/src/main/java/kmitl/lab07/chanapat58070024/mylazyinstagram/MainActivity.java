package kmitl.lab07.chanapat58070024.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.chanapat58070024.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.chanapat58070024.mylazyinstagram.api.Api;
import kmitl.lab07.chanapat58070024.mylazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("nature");
    }

    private void getUserProfile(String name) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<UserProfile> call = api.getProfile(name);
        call.enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    UserProfile user = response.body();
                    display(user);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<UserProfile> call, Throwable t) {

            }
        });
    }

    private void display(UserProfile userProfile){
        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText("@"+userProfile.getUser());
        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText("Post\n"+userProfile.getPost());
        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+userProfile.getFollower());
        TextView textFollwing = (TextView) findViewById(R.id.textFollwing);
        textFollwing.setText("Following\n"+userProfile.getFollowing());
        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(imageProfile);

        PostAdapter postAdapter = new PostAdapter(this);
        postAdapter.setData(userProfile.getPosts());
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
    }
}
