package com.example.login_hkandroid;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.login_hkandroid.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        // Listen for callback results returning from RegisterFragment
        navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("registered_username")
                .observe(getViewLifecycleOwner(), result -> {
                    if (result != null) {
                        String username = (String) result;
                        binding.etUsername.setText(username);
                        Snackbar.make(binding.getRoot(), "Account registered: " + username, Snackbar.LENGTH_SHORT).show();

                        // Clear result after reading
                        navController.getCurrentBackStackEntry()
                                .getSavedStateHandle()
                                .remove("registered_username");
                    }
                });

        binding.rootLayout.setOnClickListener(v -> hideKeyboard());

        // Navigate to RegisterFragment (Teacher's style)
        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        binding.btnSubmit.setOnClickListener(v -> {
            hideKeyboard();
            var message = "Login successful for " + binding.etUsername.getText().toString();

            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT)
                    .setAction("OK", dialog -> {})
                    .show();

            navController.navigate(R.id.action_loginFragment_to_movieListFragment);
        });

        addTextInputListener();
    }

    private void validateButtonSubmit() {
        if (binding.etUsername.getText().toString().isEmpty() || binding.etPwd.getText().toString().isEmpty()) {
            binding.btnSubmit.setEnabled(false);
        } else {
            binding.btnSubmit.setEnabled(true);
        }
    }

    private void addTextInputListener() {
        TextWatcher handleTextChange = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateButtonSubmit();
            }
        };

        binding.etUsername.addTextChangedListener(handleTextChange);
        binding.etPwd.addTextChangedListener(handleTextChange);
    }

    private void hideKeyboard() {
        if (getActivity() != null) {
            View view = getActivity().getCurrentFocus();
            if (view == null) {
                view = new View(getActivity());
            }
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
