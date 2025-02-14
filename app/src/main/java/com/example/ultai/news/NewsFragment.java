package com.example.ultai.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;
import com.example.ultai.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // Обновляем текст в TextView
        final TextView textView = binding.news;
        newsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Навигация по нажатию на кнопку
        binding.imageButton5.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_navigation_news_to_navigation_home2);
        });
        // Обработка кнопки "Назад"
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

                        // Если текущий фрагмент не является HomeFragment
                        if (navController.getCurrentDestination().getId() != R.id.homeFragment) {
                            // Переходим на HomeFragment и очищаем стек навигации
                            navController.popBackStack(R.id.homeFragment, false);
                        } else {
                            // Если уже на HomeFragment, закрываем приложение
                            requireActivity().finish();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
