package com.example.hotwheelscollector;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de ejemplo
        itemList = new ArrayList<>();
        itemList.add(new Item("Item 1", 10.99, 2));
        itemList.add(new Item("Item 2", 5.49, 5));
        itemList.add(new Item("Item 3", 3.99, 1));
        itemList.add(new Item("Item 4", 8.99, 3));

        // Configurar el adaptador
        itemAdapter = new ItemAdapter(itemList, this);
        recyclerView.setAdapter(itemAdapter);
    }
}