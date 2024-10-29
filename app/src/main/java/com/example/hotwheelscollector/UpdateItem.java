package com.example.hotwheelscollector;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateItem extends AppCompatActivity {
    private EditText editName, editPrice, editQuantity;
    private Button btnConfirmUpdate;
    private Button btnReturnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        // Initialize views
        editName = findViewById(R.id.edit_item_name);
        editPrice = findViewById(R.id.edit_item_price);
        editQuantity = findViewById(R.id.edit_item_quantity);
        btnConfirmUpdate = findViewById(R.id.btn_confirm_update);
        btnReturnHome = findViewById(R.id.btn_return_home);

        // Get the passed item data
        String name = getIntent().getStringExtra("itemName");
        double price = getIntent().getDoubleExtra("itemPrice", 0);
        int quantity = getIntent().getIntExtra("itemQuantity", 0);

        // Set the data to EditText fields
        editName.setText(name);
        editPrice.setText(String.valueOf(price));
        editQuantity.setText(String.valueOf(quantity));

        // Update confirmation button
        btnConfirmUpdate.setOnClickListener(v -> {
            String updatedName = editName.getText().toString().trim();
            String updatedPriceText = editPrice.getText().toString().trim();
            String updatedQuantityText = editQuantity.getText().toString().trim();

            // Validate non-empty fields
            if (updatedName.isEmpty() || updatedPriceText.isEmpty() || updatedQuantityText.isEmpty()) {
                Toast.makeText(UpdateItem.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate numeric values for price and quantity
            double updatedPrice;
            int updatedQuantity;
            try {
                updatedPrice = Double.parseDouble(updatedPriceText);
                updatedQuantity = Integer.parseInt(updatedQuantityText);
            } catch (NumberFormatException e) {
                Toast.makeText(UpdateItem.this, "Please enter valid numbers for price and quantity.", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Actualizar en la DB:

            finish();
        });

        btnReturnHome.setOnClickListener(v -> {
            finish();
        });
    }
}
