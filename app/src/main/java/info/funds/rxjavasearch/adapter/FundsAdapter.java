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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import info.funds.rxjavasearch.R;
import info.funds.rxjavasearch.network.model.Funds;

public class FundsAdapter extends RecyclerView.Adapter<FundsAdapter.MyViewHolder> {
    private Context context;
    private List<Funds> fundList;
    private FundsAdapterListener listener;

    public FundsAdapter(Context context, List<Funds> fundList, FundsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.fundList = fundList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Funds fund = fundList.get(position);
        holder.name.setText(fund.getName());
        holder.category.setText(fund.getFundCategory());

        Glide.with(context)
                .load(fund.getIcon())
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return fundList.size();
    }

    public interface FundsAdapterListener {
        void onFundSelected(Funds funds
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
                    listener.onFundSelected(fundList.get(getAdapterPosition()));
                }
            });
        }
    }
}
