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

import com.example.login_hkandroid.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rootLayout.setOnClickListener(v -> hideKeyboard());

        // Navigate back to LoginFragment (Teacher's style)
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                String username = binding.etUsername.getText().toString();

                NavController navController = Navigation.findNavController(v);

                // Pass registered username back to LoginFragment
                if (navController.getPreviousBackStackEntry() != null) {
                    navController.getPreviousBackStackEntry()
                            .getSavedStateHandle()
                            .set("registered_username", username);
                }

                navController.popBackStack();
            }
        });

        addTextInputListener();
    }

    private void validateButtonSubmit() {
        if (binding.etEmail.getText().toString().isEmpty() ||
                binding.etUsername.getText().toString().isEmpty() ||
                binding.etPwd.getText().toString().isEmpty() ||
                binding.etConfirmPwd.getText().toString().isEmpty()) {
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

        binding.etEmail.addTextChangedListener(handleTextChange);
        binding.etUsername.addTextChangedListener(handleTextChange);
        binding.etPwd.addTextChangedListener(handleTextChange);
        binding.etConfirmPwd.addTextChangedListener(handleTextChange);
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
