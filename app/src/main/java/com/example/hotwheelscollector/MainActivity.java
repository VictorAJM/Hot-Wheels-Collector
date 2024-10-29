package com.example.hotwheelscollector;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import androidx.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_ITEM_REQUEST = 1;
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

        // Crear lista de ejemplo
        itemList = new ArrayList<>();
        itemList.add(new Item("Item 1", 10.99, 2));
        itemList.add(new Item("Item 2", 5.49, 5));
        itemList.add(new Item("Item 3", 3.99, 1));
        itemList.add(new Item("Item 4", 8.99, 3));

        dbm.insertItem("Item 1", 2, 10.99);

        // Configurar el adaptador
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

        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
            // Obtener los datos del nuevo item
            String name = data.getStringExtra("item_name");
            double price = data.getDoubleExtra("item_price", 0.00);
            int quantity = data.getIntExtra("item_quantity", 1);

            // Crear un nuevo item y agregarlo a la lista
            Item newItem = new Item(name, price, quantity);
            itemList.add(newItem);
            itemAdapter.notifyItemInserted(itemList.size() - 1);
            recyclerView.scrollToPosition(itemList.size() - 1); // Desplazar la vista al último item agregado
        }
    }
}