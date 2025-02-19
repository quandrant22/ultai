package com.example.ultai.planer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;
import com.example.ultai.databinding.FragmentPlanerBinding;

public class PlanerFragment extends Fragment {
    private FragmentPlanerBinding binding;
    private PlanerViewModel planerViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Инициализация ViewModel
        planerViewModel = new ViewModelProvider(this).get(PlanerViewModel.class);

        // Инициализация ViewBinding
        binding = FragmentPlanerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Получаем NavController
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

        // Получаем TextView из binding и подписываемся на изменения LiveData
        final TextView textView = binding.textView4;
        planerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Кнопка "Назад" — переход на HomeFragment
        binding.imageButton2.setOnClickListener(v ->
                navController.navigate(R.id.action_navigation_planer_to_navigation_home)
        );

        // Кнопка "Профиль" — переход на ProfileFragment
        binding.imageButton7.setOnClickListener(v ->
                navController.navigate(R.id.action_navigation_planer_to_profileFragment)
        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Обнуляем binding для предотвращения утечек памяти
        binding = null;
    }
}
