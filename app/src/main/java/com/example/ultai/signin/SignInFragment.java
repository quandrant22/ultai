package com.example.ultai.signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;
import com.example.ultai.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SignInViewModel signinViewModel =
                new ViewModelProvider(this).get(SignInViewModel.class);

        binding = FragmentSignInBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView9;
        signinViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Переход на экран регистрации через TextView
        TextView textViewRegistration = view.findViewById(R.id.textView11);
        textViewRegistration.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_signInFragment_to_registrationFragment);
        });

        // Переход на главную страницу через кнопку входа
        Button buttonNextHome = view.findViewById(R.id.signin_button);
        buttonNextHome.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_signInFragment_to_navigation_home);
        });
    }
}
