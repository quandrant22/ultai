package com.example.ultai;

import android.os.Bundle;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
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
                } else {
                    // В любом другом случае мы будем переходить на HomeFragment
                    navigateToHome();
                }
            }
        });
    }

    /**
     * Перенаправляет пользователя на HomeFragment, очищая стек навигации.
     */
    private void navigateToHome() {
        // Удаляем все фрагменты до HomeFragment из стека
        navController.popBackStack(R.id.navigation_home, false);

        // Теперь навигация на HomeFragment с очисткой стека
        navController.navigate(R.id.navigation_home, null, new NavOptions.Builder()
                .setPopUpTo(R.id.navigation_home, true) // Очистка стека до HomeFragment
                .setLaunchSingleTop(true)  // Для предотвращения множества экземпляров
                .build());
    }
}
