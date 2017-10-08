package com.example.dev.hawaiat.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.example.dev.hawaiat.BR;
import com.example.dev.hawaiat.models.HeaderDrawerModel;
import com.squareup.picasso.Picasso;

/**
 * Created by medo on 16-Sep-17.
 */

public class HeaderDrawerViewModel extends BaseObservable {


    ObservableField<HeaderDrawerModel> headerDrawerModelObservableField = new ObservableField<HeaderDrawerModel>();
    Context context;

    public HeaderDrawerViewModel(Context context) {
        this.context = context;
        HeaderDrawerModel headerDrawerModel = new HeaderDrawerModel();

        headerDrawerModelObservableField.set(headerDrawerModel);

    }

    @BindingAdapter("app:imageResHeader")
    public static void bindImage(ImageView view, String r) {
        // view.setImageResource(r);

        Picasso.with(view.getContext()).load(r).into(view);
    }

    @Bindable
    public String getName() {
        return headerDrawerModelObservableField.get().getmName();
    }

    public void setName(String name) {
        HeaderDrawerModel headerDrawerModel = headerDrawerModelObservableField.get();
        headerDrawerModel.setmName(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getImage() {
        return headerDrawerModelObservableField.get().getNavImage();
    }

    public void setImage(String image) {
        HeaderDrawerModel headerDrawerModel = headerDrawerModelObservableField.get();
        headerDrawerModel.setNavImage(image);
        notifyPropertyChanged(BR.image);
    }


}
