package com.example.hotwheelscollector;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import androidx.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_ITEM_REQUEST = 1;
    private static final int UPDATE_ITEM_REQUEST = 2;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private Button btnAdd;

    private DatabaseManager dbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbm = new DatabaseManager(this);
        itemList = dbm.getItemList();

        itemAdapter = new ItemAdapter(itemList, this);
        recyclerView.setAdapter(itemAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar EditItemActivity para agregar un nuevo item
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("item_name");
            double price = data.getDoubleExtra("item_price", 0.00);
            int quantity = data.getIntExtra("item_quantity", 1);

            dbm.insertItem(new Item(name, price, quantity));
            itemAdapter.updateItemList(false);
            itemAdapter.notifyItemInserted(itemAdapter.itemList.size() - 1);
            recyclerView.scrollToPosition(itemAdapter.itemList.size() - 1);
        }else if(requestCode == UPDATE_ITEM_REQUEST && resultCode == RESULT_OK && data != null){
            itemAdapter.updateItemList(true);
        }

        Log.v("Request code", requestCode+"  -> " + (resultCode==RESULT_OK));
    }
}