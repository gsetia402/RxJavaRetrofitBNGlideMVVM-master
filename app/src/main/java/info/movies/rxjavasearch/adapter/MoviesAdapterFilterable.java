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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import info.movies.rxjavasearch.R;
import info.movies.rxjavasearch.network.model.Movie;

public class MoviesAdapterFilterable extends RecyclerView.Adapter<MoviesAdapterFilterable.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Movie> movieList;
    private List<Movie> movieListFiltered;
    private MoviesAdapterListener listener;

    public MoviesAdapterFilterable(Context context, List<Movie> movieList, MoviesAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.movieList = movieList;
        this.movieListFiltered = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Movie movie = movieListFiltered.get(position);
        holder.title.setText(movie.getTitle());
        holder.phone.setText(movie.getYear());
        holder.genre.setText(movie.getGenre());

        Glide.with(context)
                .load(movie.getPoster())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    movieListFiltered = movieList;
                } else {
                    List<Movie> filteredList = new ArrayList<>();
                    for (Movie row : movieList) {

                        // filtering movies with title, genre and year
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getGenre().toLowerCase().contains(charString.toLowerCase()) || row.getYear().contains(charString)) {
                            filteredList.add(row);
                        }
                    }

                    movieListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface MoviesAdapterListener {
        void onMovieSelected(Movie movie);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, phone, genre;
        public ImageView poster;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            genre = view.findViewById(R.id.genre);
            poster = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected movie in callback
                    listener.onMovieSelected(movieListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}