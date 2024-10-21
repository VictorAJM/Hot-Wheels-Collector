package com.example.hotwheelscollector;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    private Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_carrito_widget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.name.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        // Botón Actualizar
        holder.btnUpdate.setOnClickListener(v -> {
            Toast.makeText(context, "Actualizar " + item.getName(), Toast.LENGTH_SHORT).show();
            // Implementar la lógica de actualización aquí
        });

        // Botón Borrar
        holder.btnDelete.setOnClickListener(v -> {
            Toast.makeText(context, "Borrar " + item.getName(), Toast.LENGTH_SHORT).show();
            // Implementar la lógica de borrado aquí
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, quantity;
        public Button btnUpdate, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            quantity = itemView.findViewById(R.id.item_quantity);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
