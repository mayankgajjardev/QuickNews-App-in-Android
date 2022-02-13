package com.example.quicknews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quicknews.Adapters.CustomAdapter;
import com.example.quicknews.Models.NewsApiResponse;
import com.example.quicknews.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;

    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7;

    SearchView searchView;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News...");
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener, null, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                RequestManager manager = new RequestManager(MainActivity.this);
//                manager.getNewsHeadlines(listener, null, newText);
                return false;
            }
        });


        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);



        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Articles...");
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);

    }

    private  final  OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {

            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"No Data Found !!",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }else{
                shoNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"An Error Occur !",Toast.LENGTH_LONG).show();
        }
    };

    private void shoNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClick(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                .putExtra("data", headlines)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        String tool = button.getTooltipText().toString();

        dialog.setTitle("Fetching Category Of " + tool);
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category, null);
    }


//    Custom Action Menu In Appbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.action_settings:
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            return true;

    }
        return(super.onOptionsItemSelected(item));
    }



}