package com.example.ultai.planer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        // Получаем TextView из binding
        final TextView textView = binding.textView3;

        // Наблюдаем за LiveData и обновляем TextView
        planerViewModel.getText().observe(getViewLifecycleOwner(), text -> {
            textView.setText(text);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Обнуляем binding для предотвращения утечек памяти
        binding = null;
    }
}
