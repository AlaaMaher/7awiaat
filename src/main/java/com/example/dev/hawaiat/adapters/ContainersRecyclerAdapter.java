package com.example.dev.hawaiat.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dev.hawaiat.models.Containers;

import java.util.ArrayList;


public class ContainersRecyclerAdapter extends RecyclerView.Adapter<ContainersRecyclerAdapter.ContainerViewHolder> {
    private ArrayList<Containers> mList = new ArrayList<>();

    @Override
    public ContainerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ContainerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ContainerViewHolder extends RecyclerView.ViewHolder {

        public ContainerViewHolder(View itemView) {
            super(itemView);
        }
    }

}
