package com.example.ultai;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
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
            if (destId == R.id.registrationFragment ||
                    destId == R.id.signInFragment ||
                    destId == R.id.anketaFragment ||
                    destId == R.id.firstFragment) {
                navView.setVisibility(View.GONE);
            } else {
                navView.setVisibility(View.VISIBLE);
            }
            updateBottomNavIcons(navView, destId);
        });

        // Обработка нажатий в BottomNavigationView
        navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() != navController.getCurrentDestination().getId()) {
                navController.navigate(item.getItemId());
            }
            return true;
        });

        // Новый обработчик кнопки "Назад"
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                int currentId = navController.getCurrentDestination().getId();
                if (currentId == R.id.navigation_home) {
                    finish(); // Закрытие приложения
                } else if (currentId == R.id.navigation_dashboard ||
                        currentId == R.id.navigation_news ||
                        currentId == R.id.navigation_planer ||
                        currentId == R.id.navigation_ultai) {
                    navigateToHome();
                } else {
                    setEnabled(false);
                    onBackPressed();
                    setEnabled(true);
                }
            }
        });
    }

    /**
     * Метод для смены иконок и их цвета в BottomNavigationView
     */
    private void updateBottomNavIcons(BottomNavigationView navView, int currentDestId) {
        Menu menu = navView.getMenu();

        // Цвета
        int activeColor = ContextCompat.getColor(this, R.color.blue);
        int inactiveColor = ContextCompat.getColor(this, R.color.beige);

        updateMenuItemIcon(menu.findItem(R.id.navigation_dashboard),
                currentDestId == R.id.navigation_dashboard,
                R.drawable.dashboard_active,
                R.drawable.dashboard_nonactive,
                activeColor, inactiveColor);

        updateMenuItemIcon(menu.findItem(R.id.navigation_news),
                currentDestId == R.id.navigation_news,
                R.drawable.news_active,
                R.drawable.news_nonactive,
                activeColor, inactiveColor);

        updateMenuItemIcon(menu.findItem(R.id.navigation_planer),
                currentDestId == R.id.navigation_planer,
                R.drawable.planner_active,
                R.drawable.planner_nonactive,
                activeColor, inactiveColor);

        updateMenuItemIcon(menu.findItem(R.id.navigation_ultai),
                currentDestId == R.id.navigation_ultai,
                R.drawable.chatultai_active,
                R.drawable.chatultai_nonactive,
                activeColor, inactiveColor);

        navView.invalidate(); // Принудительное обновление
    }

    /**
     * Вспомогательный метод для изменения иконки и её цвета
     */
    private void updateMenuItemIcon(MenuItem item, boolean isActive, int activeIcon, int inactiveIcon, int activeColor, int inactiveColor) {
        Drawable icon = ContextCompat.getDrawable(this, isActive ? activeIcon : inactiveIcon);
        if (icon != null) {
            icon.setColorFilter(new PorterDuffColorFilter(isActive ? activeColor : inactiveColor, PorterDuff.Mode.SRC_IN));
            item.setIcon(icon);
        }
    }

    private void navigateToHome() {
        navController.navigate(R.id.navigation_home, null, new NavOptions.Builder()
                .setPopUpTo(R.id.navigation_home, true)
                .build());
    }
}
