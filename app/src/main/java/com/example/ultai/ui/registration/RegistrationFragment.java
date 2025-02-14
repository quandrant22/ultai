package com.example.ultai.ui.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ultai.R;
import com.example.ultai.models.ApiResponse;
import com.example.ultai.models.User;
import com.example.ultai.api.ApiService;
import com.example.ultai.NetworkClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {
    private EditText nameInput, emailInput, phoneInput, passwordInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        nameInput = view.findViewById(R.id.editTextText);
        emailInput = view.findViewById(R.id.editTextTextEmailAddress2);
        phoneInput = view.findViewById(R.id.editTextPhone);
        passwordInput = view.findViewById(R.id.editTextTextPassword2);

        view.findViewById(R.id.register_button).setOnClickListener(v -> registerUser());

        // Обработчик нажатия на TextView для перехода в SignInFragment
        TextView goToSignIn = view.findViewById(R.id.textView20);
        goToSignIn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_registrationFragment_to_signInFragment);
        });

        return view;
    }

    private void registerUser() {
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String password = passwordInput.getText().toString();

        User user = new User(name, email, phone, password);
        ApiService apiService = NetworkClient.getRetrofitClient().create(ApiService.class);

        apiService.registerUser(user).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
