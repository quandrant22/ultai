package com.example.ultai.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ultai.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Переход на DashboardFragment
        View roundedRectangle = view.findViewById(R.id.rounded_rectangle);
        roundedRectangle.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_navigation_dashboard2)
        );

        // Переход на PlanerFragment
        View roundedRectangle2 = view.findViewById(R.id.rounded_rectangle2);
        roundedRectangle2.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_navigation_planer2)
        );

        // Переход на NewsFragment
        View roundedRectangle4 = view.findViewById(R.id.rounded_rectangle4);
        roundedRectangle4.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_navigation_news2)
        );

        // Переход на ProfileFragment через ImageButton
        ImageButton imageButton7 = view.findViewById(R.id.imageButton7);
        imageButton7.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_profileFragment)
        );
    }
}