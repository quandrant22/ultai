package com.example.ultai.planer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;
import com.example.ultai.databinding.FragmentPlanerBinding;

public class PlanerFragment extends Fragment {
    private FragmentPlanerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Используем только один метод onCreateView с ViewBinding
        binding = FragmentPlanerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем NavController
        NavController navController = Navigation.findNavController(view);

        // Переход на HomeFragment
        binding.imageButton2.setOnClickListener(v ->
                navController.navigate(R.id.action_navigation_planer_to_navigation_home)
        );

        // Переход на ProfileFragment через ImageButton
        binding.imageButton7.setOnClickListener(v ->
                navController.navigate(R.id.action_navigation_planer_to_profileFragment)
        );

        // Переход на Faza1Stages
        binding.imageView22.setOnClickListener(v ->
                navController.navigate(R.id.action_navigation_planer_to_faza1_stages)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Во избежание утечек памяти
    }
}
