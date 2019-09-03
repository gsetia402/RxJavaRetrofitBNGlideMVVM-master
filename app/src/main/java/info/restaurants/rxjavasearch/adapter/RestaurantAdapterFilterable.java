package info.restaurants.rxjavasearch.adapter;

/**
 * Created on : August 30, 2019
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

import info.restaurants.rxjavasearch.R;
import info.restaurants.rxjavasearch.network.model.Restaurant;

public class RestaurantAdapterFilterable extends RecyclerView.Adapter<RestaurantAdapterFilterable.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Restaurant> restaurantList;
    private List<Restaurant> resListFilteres;
    private RestaurantAdapterListener listener;

    public RestaurantAdapterFilterable(Context context, List<Restaurant> restaurantList, RestaurantAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.restaurantList = restaurantList;
        this.resListFilteres = restaurantList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Restaurant restaurant = resListFilteres.get(position);
        holder.name.setText(restaurant.getRestaurant().getName());
        holder.category.setText(restaurant.getRestaurant().getEvents_url());


        Glide.with(context)
                .load(restaurant.getRestaurant().getThumb())
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return resListFilteres.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    resListFilteres = restaurantList;
                } else {
                    List<Restaurant> filteredList = new ArrayList<>();
                    for (Restaurant row : restaurantList) {

                        if (row.getRestaurant().getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    resListFilteres = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = resListFilteres;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                resListFilteres = (ArrayList<Restaurant>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface RestaurantAdapterListener {
        void onRestaurant(Restaurant restaurant);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, category, rating, interest;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.fund_name);
            category = view.findViewById(R.id.fund_category);
            rating = view.findViewById(R.id.fund_rating);
            interest = view.findViewById(R.id.fund_interest);
            icon = view.findViewById(R.id.fund_icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected restaursnt in callback
                    listener.onRestaurant(resListFilteres.get(getAdapterPosition()));
                }
            });
        }
    }
}