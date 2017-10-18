package kmitl.lab07.chanapat58070024.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private PostAdapter postAdapter;
    private View loading;
    private MenuItem listView;
    private MenuItem gridView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        loading = findViewById(R.id.loading);
        postAdapter = new PostAdapter(this);
        layoutManager = new GridLayoutManager(this, 3);
        getUserProfile("android");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        listView = menu.findItem(R.id.listView);
        gridView = menu.findItem(R.id.gridView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.listView:
                layoutManager = new LinearLayoutManager(this);
                viewIconSwitch(gridView, listView);
                displayPosts();
                return true;
            case R.id.gridView:
                layoutManager = new GridLayoutManager(this, 3);
                viewIconSwitch(listView, gridView);
                displayPosts();
                return true;
            case R.id.android:
                getUserProfile("android");
                return true;
            case R.id.nature:
                getUserProfile("nature");
                return true;
            case R.id.cartoon:
                getUserProfile("cartoon");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getUserProfile(String name) {
        loading.setVisibility(View.VISIBLE);
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
                    displayDetail(user);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<UserProfile> call, Throwable t) {

            }
        });
    }

    private void displayDetail(UserProfile userProfile){
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@"+userProfile.getUser());
        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n"+userProfile.getPost());
        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+userProfile.getFollower());
        TextView textFollwing = findViewById(R.id.textFollwing);
        textFollwing.setText("Following\n"+userProfile.getFollowing());
        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        Button btn_follow = findViewById(R.id.btn_follow);
        btn_follow.setVisibility(View.GONE);
        Button btn_followed = findViewById(R.id.btn_followed);
        btn_followed.setVisibility(View.GONE);
        if(userProfile.isFollow()) {
            btn_followed.setVisibility(View.VISIBLE);
        } else {
            btn_follow.setVisibility(View.VISIBLE);
        }

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this)
                .load(userProfile.getUrlProfile())
                .into(imageProfile);

        postAdapter.setData(userProfile.getPosts());
        recyclerView.setAdapter(postAdapter);
        displayPosts();
        loading.setVisibility(View.GONE);
    }

    private void displayPosts() {
        recyclerView.setLayoutManager(layoutManager);
    }

    private void viewIconSwitch(MenuItem showItem,MenuItem hideItem) {
        showItem.setVisible(true);
        hideItem.setVisible(false);
    }
}
