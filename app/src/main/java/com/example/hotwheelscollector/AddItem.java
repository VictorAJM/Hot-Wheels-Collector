package com.example.hotwheelscollector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {
    private EditText editName, editPrice, editQuantity;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editName = findViewById(R.id.edit_item_name);
        editPrice = findViewById(R.id.edit_item_price);
        editQuantity = findViewById(R.id.edit_item_quantity);
        btnSave = findViewById(R.id.btn_save_item);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String priceString = editPrice.getText().toString();
                String quantityString = editQuantity.getText().toString();

                if (name.isEmpty() || priceString.isEmpty() || quantityString.isEmpty()) {
                    Toast.makeText(AddItem.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double price = Double.parseDouble(priceString);
                int quantity = Integer.parseInt(quantityString);

                // Crear un Intent para devolver los datos
                Intent resultIntent = new Intent();
                resultIntent.putExtra("item_name", name);
                resultIntent.putExtra("item_price", price);
                resultIntent.putExtra("item_quantity", quantity);

                // Devolver el resultado a la actividad anterior
                setResult(RESULT_OK, resultIntent);
                finish(); // Cerrar la actividad
            }
        });
    }
}