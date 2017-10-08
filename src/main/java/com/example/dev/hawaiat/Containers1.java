package com.example.dev.hawaiat;

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

import com.example.dev.hawaiat.views.SendOrder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Containers1 extends AppCompatActivity {


    ListPopupWindow listPopup;
    Button btnShowPopup;
    ArrayList<SendOrder.ContainerSizes> containerSizesArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SendOrder.ContainersAdapter mAdapter;
    private Button btncontainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_trash);

        btnShowPopup = (Button) findViewById(R.id.btncontainer);

        List<Item> itemsList = new ArrayList<>();
        Item item = new Item();
        item.setText("Container 10 Yard");
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        item = new Item();
        item.setText("Container 40 Yard");
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        item = new Item();
        item.setText("Container 20 Yard");
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        item = new Item();
        item.setText("Container 80 Yard");
        item.setImgUrl("https://s.cafebazaar.ir/1/icons/com.limatech.limagapp_512x512.png");
        itemsList.add(item);

        listPopup = new ListPopupWindow(this); // feed context to ListPopupWindow
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.item_layout, itemsList);
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
//                Toast.makeText(LoginOrReg.this, "item "+ (i+1) +" selected",Toast.LENGTH_SHORT).show();
//                listPopup.dismiss();
//            }
//        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        SendOrder.ContainerSizes containerSizes;


        //(String companyname,String logo,RatingBar rate,String percentage)
        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "60%");
        containerSizesArrayList.add(containerSizes);

        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "80%");
        containerSizesArrayList.add(containerSizes);

        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "40%");
        containerSizesArrayList.add(containerSizes);

        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "30%");
        containerSizesArrayList.add(containerSizes);

        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "40%");
        containerSizesArrayList.add(containerSizes);

        containerSizes = new SendOrder.ContainerSizes(" AlFatah Company For Renting Containers ", "https://uploud.wikimedia.org/wikipedia/en/0/0d/Simpsons_FamilyPicture.png", .1f, "30%");
        containerSizesArrayList.add(containerSizes);

        //String img="https://i.imgur.com/tGbaZCY.jpg";
        //containerSizes=new ContainerSizes(" two",img,.4f,"70%");

        //containerSizesArrayList.add(containerSizes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SendOrder.ContainersAdapter adapter = new SendOrder.ContainersAdapter(this, containerSizesArrayList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_search, menu);
        // Retrieve the SearchView and plug it into SearchManager


        return true;
    }

    public class CustomAdapter extends ArrayAdapter<Item> {
        List<Item> items;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
            super(context, resource, objects);

            items = objects;
        }

        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.item_layout, null);
            ImageView icon = (ImageView) view.findViewById(R.id.imageView);
            CheckBox checkBox = view.findViewById(R.id.checkBox);

            checkBox.setChecked(false);
            checkBox.setClickable(false);
            checkBox.setText(items.get(pos).getText());

            Picasso.with(view.getContext())
                    .load(R.mipmap.ic_launcher)
                    .into(icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Containers1.this, "item selected", Toast.LENGTH_SHORT).show();
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

        public String getText() {
            return text;
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