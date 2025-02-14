package com.example.ultai.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;
import com.example.ultai.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Инициализация ViewModel
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        // Использование ViewBinding
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Наблюдение за данными из ViewModel
        final TextView textView = binding.textProfile;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Находим кнопку и настраиваем переход на другой фрагмент
        ImageButton imageButtonNext = view.findViewById(R.id.imageButton);
        imageButtonNext.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_profileFragment_to_navigation_home);
        });

        // Находим другую кнопку и настраиваем переход на фрагмент настроек
        ImageButton imageButtonSettings = view.findViewById(R.id.imageView19); // Убедитесь, что у вас есть такой ID
        imageButtonSettings.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_profileFragment_to_settingsFragment);
        });

        // Обработка кнопки "Назад"
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Возвращаемся на предыдущий фрагмент
                Navigation.findNavController(view).popBackStack();
            }
        };

        // Регистрируем callback для обработки кнопки "Назад"
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Очищаем binding при уничтожении View
    }
}