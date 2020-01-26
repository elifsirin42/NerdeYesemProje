package com.example.nerdeyesemproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nerdeyesemproje.Restaurant.Example;
import com.example.nerdeyesemproje.Restaurant.Restaurant_;

import java.io.Serializable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ListView listView;
    Api api;


    Example data;
    final String[] name = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        double lat = Double.parseDouble(intent.getStringExtra("lat"));
        double longi = Double.parseDouble(intent.getStringExtra("longi"));
        listView= (ListView) findViewById(R.id.listViewlist);



        Retrofit retrofit = ApiClient.getClient();
        api=retrofit.create(Api.class);
        // create restaurant object use retrofit
        compositeDisposable.add(api.getRestaurantsBySearch(lat, longi, "real_distance", 5).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Example>() {
                    @Override
                    public void accept(Example example) throws Exception {
                        getData(example);

                    }
                })
        );




    }
    //get restaurant object from compositeDisposable
    public void getData(Example example){

        String[] nameList = new String[5] ;
        final Bundle bundle = new Bundle();

        //restaurant_ object send to DetailsActivity use bundle
        for (int i = 0; i<5;i++){

            Restaurant_ restaurant=example.restaurants.get(i).getRestaurant();
            bundle.putSerializable("dataList"+i,(Serializable) restaurant);

            nameList[i]=example.restaurants.get(i).getRestaurant().getName();
        }
        //listed restaurant's name in ListView
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
        listView.setAdapter(adapter);
        //ListView clickListener  position send to DetailsActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(ListActivity.this,DetailsActivity.class);
                bundle.putSerializable("position", position);
                i.putExtras(bundle);
                startActivity(i);
            }
        } );


    }
}
