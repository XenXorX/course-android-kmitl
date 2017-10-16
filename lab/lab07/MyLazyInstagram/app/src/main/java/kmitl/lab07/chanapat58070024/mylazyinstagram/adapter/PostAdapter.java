package kmitl.lab07.chanapat58070024.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.chanapat58070024.mylazyinstagram.R;
import kmitl.lab07.chanapat58070024.mylazyinstagram.model.UserPost;

class Holder extends RecyclerView.ViewHolder {
    public TextView textLike;
    public TextView textComment;
    public ImageView image;

    public Holder(View itemView) {
        super(itemView);

        textLike = itemView.findViewById(R.id.textLike);
        textComment = itemView.findViewById(R.id.textComment);
        image = itemView.findViewById(R.id.imageView);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder> {
    private Context c;
    private UserPost[] data;

    public PostAdapter(Context c) {
        this.c = c;
    }

    public UserPost[] getData() {
        return data;
    }

    public void setData(UserPost[] data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        TextView textLike = holder.textLike;
        textLike.setText("Like "+data[position].getLike());
        TextView textCommnet = holder.textComment;
        textCommnet.setText("Comment "+data[position].getComment());

        ImageView image = holder.image;
        Glide.with(c).load(data[position].getUrl()).into(image);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
