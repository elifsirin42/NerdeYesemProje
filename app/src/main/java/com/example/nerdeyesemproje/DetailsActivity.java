package com.example.nerdeyesemproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nerdeyesemproje.Restaurant.Restaurant_;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    Bundle bundle;
    ImageView ımageView;
    TextView resName;
    TextView address;
    List<Restaurant_> dataList=new ArrayList<>();
    int position;
    //show the restaurant photo and address
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        resName=findViewById(R.id.restaurantName);
        address=findViewById(R.id.address);
        ımageView=findViewById(R.id.restaurantPhoto);
        Intent i=getIntent();
        Bundle bundle = i.getExtras();
        position=(int)bundle.getSerializable("position");


        for (int k=0;k<5;k++){
            String str="dataList"+k;
            dataList.add((Restaurant_) bundle.getSerializable(str));

        }
// photos_url is not empty  show
        try {
            Picasso.get().load(dataList.get(position).getThumb()).into(ımageView);
        }catch (Exception e){

        }

        resName.setText(dataList.get(position).getName());
        address.setText(dataList.get(position).toString());




    }
}
