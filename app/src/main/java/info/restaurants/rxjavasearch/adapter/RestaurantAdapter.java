package info.restaurants.rxjavasearch.adapter;

/**
 * Created on : Sep 3, 2019
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

import java.util.List;

import info.restaurants.rxjavasearch.R;
import info.restaurants.rxjavasearch.network.model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    private Context context;
    private List<Restaurant> restaurantList;
    private FundsAdapterListener listener;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantList, FundsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.restaurantList = restaurantList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getRestaurant().getName());

        Glide.with(context)
                .load(restaurant.getRestaurant().getThumb())
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public interface FundsAdapterListener {
        void onFundSelected(Restaurant restaurants
        );
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, category;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.fund_name);
            category = view.findViewById(R.id.fund_category);
            icon = view.findViewById(R.id.fund_icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFundSelected(restaurantList.get(getAdapterPosition()));
                }
            });
        }
    }
}
