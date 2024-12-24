package com.example.ultai.ultai;

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
import com.example.ultai.databinding.FragmentUltaiBinding;


public class UltaiFragment extends Fragment {

    private FragmentUltaiBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UltaiViewModel ultaiViewModel =
                new ViewModelProvider(this).get(UltaiViewModel.class);

        binding = FragmentUltaiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUltai;
        ultaiViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
