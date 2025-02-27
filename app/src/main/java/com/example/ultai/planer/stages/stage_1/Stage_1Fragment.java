package com.example.ultai.planer.stages.stage_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;

public class Stage_1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Инициализация макета
        View view = inflater.inflate(R.layout.fragment_stage_1, container, false);

        // Настройка слушателей для ImageView в горизонтальном скролле
        setupImageClickListeners(view);

        return view;
    }

    /**
     * Настройка слушателей для ImageView в горизонтальном скролле
     */
    private void setupImageClickListeners(View view) {
        // Этап 1
        view.findViewById(R.id.stage_1).setOnClickListener(v -> switchContent(view, 0));

        // Этап 2
        view.findViewById(R.id.stage_2).setOnClickListener(v -> switchContent(view, 1));

        // Этап 3
        view.findViewById(R.id.stage_3).setOnClickListener(v -> switchContent(view, 2));

        // Этап 4
        view.findViewById(R.id.stage_4).setOnClickListener(v -> switchContent(view, 3));
    }

    /**
     * Переключение содержимого вертикального скролла
     *
     * @param view   Корневой View фрагмента
     * @param index Индекс выбранного элемента
     */
    private void switchContent(View view, int index) {
        // Скрываем все вертикальные скроллы
        hideAllScrollViews(view);

        // Показываем нужный скролл по индексу
        showScrollViewByIndex(view, index);
    }

    /**
     * Скрытие всех вертикальных скроллов
     *
     * @param view Корневой View фрагмента
     */
    private void hideAllScrollViews(View view) {
        View scroll1 = view.findViewById(R.id.scroll_content_1);
        View scroll2 = view.findViewById(R.id.scroll_content_2);
        View scroll3 = view.findViewById(R.id.scroll_content_3);
        View scroll4 = view.findViewById(R.id.scroll_content_4);

        if (scroll1 != null) scroll1.setVisibility(View.GONE);
        if (scroll2 != null) scroll2.setVisibility(View.GONE);
        if (scroll3 != null) scroll3.setVisibility(View.GONE);
        if (scroll4 != null) scroll4.setVisibility(View.GONE);
    }

    /**
     * Показ вертикального скролла по индексу
     *
     * @param view  Корневой View фрагмента
     * @param index Индекс скролла
     */
    private void showScrollViewByIndex(View view, int index) {
        switch (index) {
            case 0:
                view.findViewById(R.id.scroll_content_1).setVisibility(View.VISIBLE);
                break;
            case 1:
                view.findViewById(R.id.scroll_content_2).setVisibility(View.VISIBLE);
                break;
            case 2:
                view.findViewById(R.id.scroll_content_3).setVisibility(View.VISIBLE);
                break;
            case 3:
                view.findViewById(R.id.scroll_content_4).setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Находим кнопку и настраиваем переход на другой фрагмент
        ImageButton imageButtonNext = view.findViewById(R.id.imageButton3);
        imageButtonNext.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_faza1_stages_to_navigation_planer);
        });
        ImageButton imageButtonSettings = view.findViewById(R.id.imageButton7); // Убедитесь, что у вас есть такой ID
        imageButtonSettings.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_faza1_stages_to_profileFragment);
        });
}

}