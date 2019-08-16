package info.movies.rxjavasearch.adapter;

/**
 * Created on : August 16, 2019
 * Author     : Setia
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import info.movies.rxjavasearch.R;
import info.movies.rxjavasearch.network.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context context;
    private List<Movie> movieList;
    private MoviesAdapterListener listener;

    public MoviesAdapter(Context context, List<Movie> moviesList, MoviesAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.movieList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Movie movie = movieList.get(position);
        holder.name.setText(movie.getTitle());
        holder.phone.setText(movie.getYear());

        Glide.with(context)
                .load(movie.getPoster())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public interface MoviesAdapterListener {
        void onMovieSelected(Movie movie
        );
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected movie in callback
                    listener.onMovieSelected(movieList.get(getAdapterPosition()));
                }
            });
        }
    }
}
