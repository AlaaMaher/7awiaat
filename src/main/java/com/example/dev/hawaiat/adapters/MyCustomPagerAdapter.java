package com.example.dev.hawaiat.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by medo on 27-Aug-17.
 */

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    List<String> images;
    LayoutInflater layoutInflater;


    public MyCustomPagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public  int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.view_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        //imageView.setImageResource(images[position]);
        Picasso.with(context).load(images.get(position)).into(imageView);

        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    public  void setLeftIconViability(ImageView imageView, int position) {
        if (0 == position) imageView.setVisibility(View.GONE);
        else imageView.setVisibility(View.VISIBLE);
    }

    public  void setRightIconViability(ImageView imageView, int position) {
        if (getCount()-1 == position) imageView.setVisibility(View.GONE);
        else imageView.setVisibility(View.VISIBLE);
    }

}
