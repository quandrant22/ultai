package com.example.ultai;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.ultai.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.ULTAI);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;

        // Получаем NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(navView, navController);

        // Скрытие BottomNavigationView на определённых экранах
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int destId = destination.getId();

            if (destId == R.id.registrationFragment
                    || destId == R.id.signInFragment
                    || destId == R.id.anketaFragment
                    || destId == R.id.firstFragment) {
                navView.setVisibility(View.GONE);
            } else {
                navView.setVisibility(View.VISIBLE);
            }

            // Обновляем иконки
            updateBottomNavIcons(navView, destId);
        });

        // Обработка нажатий в BottomNavigationView
        navView.setOnItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });
    }

    /**
     * Метод для смены иконок в BottomNavigationView в зависимости от текущего экрана
     */
    private void updateBottomNavIcons(BottomNavigationView navView, int currentDestId) {
        Menu menu = navView.getMenu();

        // Устанавливаем стандартные (неактивные) иконки
        menu.findItem(R.id.navigation_dashboard).setIcon(R.drawable.dashboard_nonactive);
        menu.findItem(R.id.navigation_news).setIcon(R.drawable.news_nonactive);
        menu.findItem(R.id.navigation_planer).setIcon(R.drawable.planner_nonactive);
        menu.findItem(R.id.navigation_ultai).setIcon(R.drawable.chatultai_nonactive);

        // Устанавливаем активную иконку для текущего экрана
        if (currentDestId == R.id.navigation_dashboard) {
            menu.findItem(R.id.navigation_dashboard).setIcon(R.drawable.dashboard_active);
        } else if (currentDestId == R.id.navigation_news) {
            menu.findItem(R.id.navigation_news).setIcon(R.drawable.news_active);
        } else if (currentDestId == R.id.navigation_planer) {
            menu.findItem(R.id.navigation_planer).setIcon(R.drawable.planner_active);
        } else if (currentDestId == R.id.navigation_ultai) {
            menu.findItem(R.id.navigation_ultai).setIcon(R.drawable.chatultai_active);
        }

        // Принудительное обновление меню, чтобы изменения сразу отобразились
        navView.invalidate();
    }
}
