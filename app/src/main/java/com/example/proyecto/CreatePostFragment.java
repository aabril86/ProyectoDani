package com.example.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.databinding.FragmentCreatePostBinding;
import com.kusu.loadingbutton.LoadingButton;

public class CreatePostFragment extends Fragment {

    private com.example.proyecto.databinding.FragmentCreatePostBinding binding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentCreatePostBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingButton loadingButton = (binding.createpost); loadingButton.showLoading();
                new CountDownTimer(2000, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        loadingButton.hideLoading();
                        navController.navigate(R.id.action_createPostFragment_to_forumFragment2);
                    }
                }.start();
            }
        });

        binding.arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createPostFragment_to_forumFragment2);
            }
        });
    }
}