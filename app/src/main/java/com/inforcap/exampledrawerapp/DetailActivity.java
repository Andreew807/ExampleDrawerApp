package com.inforcap.exampledrawerapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.inforcap.exampledrawerapp.databinding.ActivityDetailBinding;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    TextView textViewMensaje;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_keyboard_arrow_left_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewMensaje = binding.textViewMensaje;

        Bundle bundle = this.getIntent().getExtras();

        if(bundle != null){
            String texto = "Marca Seleccionada: " + bundle.getString("marca") + "\n" +
                    "Precio " + new DecimalFormat("$ #,##0").format(bundle.getDouble("precio"));
            textViewMensaje.setText(texto);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}