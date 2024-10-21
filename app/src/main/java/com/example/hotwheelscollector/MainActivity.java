package com.example.hotwheelscollector;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btn_add);
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar un nuevo item a la lista
                itemList.add(new Item("Nuevo Item", 0.00, 1));
                itemAdapter.notifyItemInserted(itemList.size() - 1);  // Notificar al adaptador que se ha agregado un nuevo elemento
                recyclerView.scrollToPosition(itemList.size() - 1);  // Desplazar la vista al final de la lista
            }
        });
    }
}