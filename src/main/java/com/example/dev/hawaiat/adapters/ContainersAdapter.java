package com.example.dev.hawaiat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.models.Company;
import com.example.dev.hawaiat.views.Company_Detail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 25/09/17.
 */

public class ContainersAdapter extends RecyclerView.Adapter<ContainersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Company> containerSizesList = new ArrayList<>();

    public ContainersAdapter(Context mContext, List<Company> containerSizesList) {

        this.containerSizesList = containerSizesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.companyname.setText(containerSizesList.get(position).getName());
        holder.percentage.setText(String.valueOf(containerSizesList.get(position).getId()));
        holder.rate.setRating((float) containerSizesList.get(position).getRate());
        String imageUrl = containerSizesList.get(position).getLogo();

        Picasso.with(mContext).load(imageUrl).resize(93, 60).into(holder.logo);


    }

    @Override
    public int getItemCount() {
        return containerSizesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView companyname, percentage;
        private RatingBar rate;
        private ImageView logo;

        public MyViewHolder(View view) {
            super(view);
            companyname = (TextView) view.findViewById(R.id.companyname);
            percentage = (TextView) view.findViewById(R.id.percentage);
            logo = (ImageView) view.findViewById(R.id.logo);
            rate = (RatingBar) view.findViewById(R.id.rate);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();

         //  Toast.makeText(mContext," company id "+containerSizesList.get(position).getId(),Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(mContext, Company_Detail.class);
           intent.putExtra("CompanyID",containerSizesList.get(position).getId());
           mContext.startActivity(intent);

        }
    }
}