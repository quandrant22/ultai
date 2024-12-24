package com.example.ultai.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ultai.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner customSpinner = view.findViewById(R.id.customSpinner);

        // Пример данных
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(items);
        customSpinner.setAdapter(adapter);
    }

    private class CustomSpinnerAdapter extends ArrayAdapter<String> {
        private final List<String> items;

        public CustomSpinnerAdapter(List<String> items) {
            super(requireContext(), R.layout.item_spinner, items);
            this.items = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return createItemView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return createItemView(position, convertView, parent);
        }

        private View createItemView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(requireContext()).inflate(R.layout.item_spinner, parent, false);
            }

            TextView textView = convertView.findViewById(R.id.textView);
            Button button = convertView.findViewById(R.id.button);

            String item = items.get(position);
            textView.setText(item);

            button.setOnClickListener(v ->
                    Toast.makeText(requireContext(), "Clicked on " + item, Toast.LENGTH_SHORT).show()
            );

            return convertView;
        }
    }
}
