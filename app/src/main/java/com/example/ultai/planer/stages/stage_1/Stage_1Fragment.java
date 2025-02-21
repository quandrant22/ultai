package com.example.ultai.planer.stages.stage_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.activity.OnBackPressedCallback;

import com.example.ultai.R;
import com.example.ultai.databinding.FragmentStage1Binding;

public class Stage_1Fragment extends Fragment {

    private FragmentStage1Binding binding;
    private NavController navController;
    private Stage_1ViewModel stage1ViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Инициализация ViewBinding
        binding = FragmentStage1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация NavController
        navController = Navigation.findNavController(view);

        // Инициализация ViewModel и установка текста
        stage1ViewModel = new ViewModelProvider(this).get(Stage_1ViewModel.class);
        stage1ViewModel.getText().observe(getViewLifecycleOwner(), binding.textView4::setText);

        // Переход на PlanerFragment по нажатию кнопки
        binding.imageButton3.setOnClickListener(v ->
                navController.navigate(R.id.action_faza1_stages_to_navigation_planer)
        );

        // Обработка системной кнопки "Назад" для возврата на PlanerFragment
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navController.navigate(R.id.action_faza1_stages_to_navigation_planer);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Очищаем binding для предотвращения утечек памяти
    }
}
