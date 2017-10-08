package com.example.dev.hawaiat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.views.SendOrder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by medo on 25-Sep-17.
 */

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<CompanyRecyclerAdapter.MyViewHolder> {

    private String m;
    private List<String> containersImages;
    private List<String> containerPrice;
    private List<String> containerCapacity;

    private Context ctx;

    public CompanyRecyclerAdapter(Context ctx, List<String> containersImages, List<String> containerPrice, List<String> containerCapacity) {
        this.ctx = ctx;
        this.containersImages = containersImages;
        this.containerPrice = containerPrice;
        this.containerCapacity = containerCapacity;

    }

    @Override
    public CompanyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_container, parent, false);

        return new CompanyRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanyRecyclerAdapter.MyViewHolder holder, int position) {


        m = ctx.getString(R.string.container_capacity) + " " + containerCapacity.get(position) + " " + ctx.getString(R.string.yard) + "\n" + ctx.getString(R.string.container_price) + " " + containerPrice.get(position) + " " + ctx.getString(R.string.rail);
        holder.mTextView.setText(m);
        Picasso.with(ctx).load(containersImages.get(position)).into(holder.mImagView);


    }

    @Override
    public int getItemCount() {
        return containersImages.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImagView;
        Button mButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView9);
            mImagView = (ImageView) itemView.findViewById(R.id.splash_imageview);
            mButton = (Button) itemView.findViewById(R.id.button3);


            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posion = getAdapterPosition();
                    Intent intent = new Intent(ctx, SendOrder.class);
                    intent.putExtra("ContainerCapacity", containerCapacity.get(posion));
                    ctx.startActivity(intent);
                }

            });
        }

    }
}
