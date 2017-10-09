package com.example.dev.hawaiat.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.dev.hawaiat.R;
import com.example.dev.hawaiat.adapters.ContainersAdapter;
import com.example.dev.hawaiat.models.Company;
import com.example.dev.hawaiat.models.Containers;
import com.example.dev.hawaiat.webServices.RetrofitWebService;
import com.example.dev.hawaiat.webServices.request.GetCompaniesRequest;
import com.example.dev.hawaiat.webServices.responses.GetCompaniesResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContainerTrash extends AppCompatActivity {


    ListPopupWindow listPopup;
    Button btnShowPopup;
    ArrayList<Containers> containerSizesArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    //private SendOrder.ContainersAdapter mAdapter;
    private Button btncontainer;
    private String apiToken = "4EN93W8g842d6fYsiBw6xQJIpnGw3MwCna2dClHT0TirD0eG7LbzkfmKJSk0otni4X49Ll";
    private String containerType = "trash";
    private List<Company> companies = new ArrayList<>();

    private ContainersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_trash);

        //Intent intent=getIntent();


        btnShowPopup = (Button) findViewById(R.id.btncontainer);

        GetCompaniesRequest getCompaniesRequest = new GetCompaniesRequest(apiToken, containerType);
        RetrofitWebService.getService().getCompaniesfun(getCompaniesRequest).enqueue(new Callback<GetCompaniesResponse>() {
            @Override
            public void onResponse(Call<GetCompaniesResponse> call, Response<GetCompaniesResponse> response) {
                Toast.makeText(ContainerTrash.this, String.valueOf(response.body().getStatus()), Toast.LENGTH_SHORT).show();
                companies = response.body().getCompanies();
                adapter = new ContainersAdapter(ContainerTrash.this, companies);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<GetCompaniesResponse> call, Throwable t) {

            }
        });

        List<ContainerTrash.Item> itemsList = new ArrayList<>();
        ContainerTrash.Item item = new ContainerTrash.Item();
        item.setText(String.valueOf((R.string.yard10)));
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        item = new ContainerTrash.Item();
        item.setText(String.valueOf(R.string.yard15));
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        item = new ContainerTrash.Item();
        item.setText(String.valueOf(R.string.yard10));
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        item = new ContainerTrash.Item();
        item.setText(String.valueOf(R.string.yard20));
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        listPopup = new ListPopupWindow(this); // feed context to ListPopupWindow
        ContainerTrash.CustomAdapter customAdapter = new ContainerTrash.CustomAdapter(this, R.layout.item_layout, itemsList);
        listPopup.setAdapter(customAdapter); // set adapter to created ListPopupMenu

        listPopup.setAnchorView(btnShowPopup);

        btnShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPopup.show();

                btnShowPopup.setText(R.string.containers_size);
            }
        });

        listPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                btnShowPopup.setText(R.string.containers_company);
            }
        });

        //        listPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //                Toast.makeText(MainActivity.this, "item "+ (i+1) +" selected",Toast.LENGTH_SHORT).show();
        //                listPopup.dismiss();
        //            }
        //        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Containers containerSizes;


        //(String companyname,String logo,RatingBar rate,String percentage)
//        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "60%");
//        containerSizesArrayList.add(containerSizes);
//
//        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "80%");
//        containerSizesArrayList.add(containerSizes);
//
//        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "40%");
//        containerSizesArrayList.add(containerSizes);
//
//        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "30%");
//        containerSizesArrayList.add(containerSizes);
//
//        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "40%");
//        containerSizesArrayList.add(containerSizes);
//
//        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "30%");
//        containerSizesArrayList.add(containerSizes);

        //String img="https://i.imgur.com/tGbaZCY.jpg";
        //containerSizes=new ContainerSizes(" two",img,.4f,"70%");

        //containerSizesArrayList.add(containerSizes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //SendOrder.ContainersAdapter adapter = new SendOrder.ContainersAdapter(this, Company);
        //  recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_search, menu);
        // Retrieve the SearchView and plug it into SearchManager


        return true;
    }


    public class CustomAdapter extends ArrayAdapter<ContainerTrash.Item> {
        List<ContainerTrash.Item> items;


        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<ContainerTrash.Item> objects) {
            super(context, resource, objects);

            items = objects;
        }

        @Override
        public View getView(final int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.item_layout, null);
            ImageView icon = (ImageView) view.findViewById(R.id.ImageIcon);
            CheckBox checkBox = view.findViewById(R.id.checkBox);

            checkBox.setChecked(false);
            checkBox.setClickable(false);
            checkBox.setText(items.get(pos).getText());

            Picasso.with(view.getContext())
                    .load(R.drawable.logo1)
                    .into(icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ContainerTrash.this, "item selected" + items.get(pos).getText(), Toast.LENGTH_SHORT).show();
                    listPopup.dismiss();
                }
            });
            return view;
        }

        @Override
        public int getCount() {

            return items.size();
        }
    }

    private class Item {
        String text;
        String imgUrl;

        public int getText() {
            return Integer.parseInt(text);
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}



