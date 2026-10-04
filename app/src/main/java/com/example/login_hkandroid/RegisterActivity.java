package com.example.login_hkandroid;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.login_hkandroid.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());

            v.setPadding(systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom);
            return insets;
        });

        binding.rootLayout.setOnClickListener(v -> {
            hideKeyboard();
        });

        binding.btnSubmit.setOnClickListener(view -> {
            hideKeyboard();
            var message = "";
            message += "Email: " + binding.etEmail.getText().toString();
            message += "\nUsername: " + binding.etUsername.getText().toString();
            message += "\nPassword: " + binding.etPwd.getText().toString();

            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", v -> {

                    }).show();
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
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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
        View view = this.getCurrentFocus();

        if (view == null) {
            view = new View(this);
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
