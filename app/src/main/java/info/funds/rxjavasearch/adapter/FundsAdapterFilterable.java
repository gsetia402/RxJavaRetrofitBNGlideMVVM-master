package info.funds.rxjavasearch.adapter;

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

import info.funds.rxjavasearch.R;
import info.funds.rxjavasearch.network.model.Funds;

public class FundsAdapterFilterable extends RecyclerView.Adapter<FundsAdapterFilterable.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Funds> fundsList;
    private List<Funds> fundListFiltered;
    private FundsAdapterListener listener;

    public FundsAdapterFilterable(Context context, List<Funds> fundsList, FundsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.fundsList = fundsList;
        this.fundListFiltered = fundsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Funds funds = fundListFiltered.get(position);
        holder.name.setText(funds.getName());
        holder.category.setText(funds.getFundCategory());
        holder.rating.setText("" + funds.getRating());
        holder.interest.setText(funds.getNav());


        Glide.with(context)
                .load(funds.getIcon())
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return fundListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    fundListFiltered = fundsList;
                } else {
                    List<Funds> filteredList = new ArrayList<>();
                    for (Funds row : fundsList) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    fundListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fundListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                fundListFiltered = (ArrayList<Funds>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface FundsAdapterListener {
        void onFundSelected(Funds funds);
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
                    // send selected fund in callback
                    listener.onFundSelected(fundListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}