package com.example.ultai;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ultai.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Установка темы без Toolbar
        setTheme(R.style.ULTAI);

        // Инициализация view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Настройка BottomNavigationView
        BottomNavigationView navView = findViewById(R.id.nav_view);  // Объявляем только один раз

        // Конфигурация AppBar и NavController
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_news,
                R.id.navigation_planer,
                R.id.navigation_ultai
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


        // Связываем NavController с BottomNavigationView
        NavigationUI.setupWithNavController(navView, navController);

        // Управление видимостью BottomNavigationView для определённых фрагментов
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.registrationFragment
                    || destination.getId() == R.id.signInFragment
                    || destination.getId() == R.id.anketaFragment) {
                navView.setVisibility(View.GONE);
            } else {
                navView.setVisibility(View.VISIBLE);
            }
        });
    }
}
