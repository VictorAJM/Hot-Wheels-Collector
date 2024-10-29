package com.example.hotwheelscollector;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
            // Logic to update the item
            String updatedName = editName.getText().toString();
            double updatedPrice = Double.parseDouble(editPrice.getText().toString());
            int updatedQuantity = Integer.parseInt(editQuantity.getText().toString());

            // Handle the update logic here (e.g., update in database)

            // Close the activity after updating
            finish();
        });

        btnReturnHome.setOnClickListener(v -> {
            finish();
        });
    }
}
