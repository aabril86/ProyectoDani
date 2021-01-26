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

import com.example.proyecto.databinding.FragmentReviewBinding;
import com.kusu.loadingbutton.LoadingButton;

public class ReviewFragment extends Fragment {

    private FragmentReviewBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentReviewBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoadingButton loadingButton = (binding.non); loadingButton.showLoading();
                new CountDownTimer(3000, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        loadingButton.hideLoading();
                        navController.navigate(R.id.action_reviewFragment_to_savedFragment);
                    }
                }.start();
            }
        });

        binding.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoadingButton loadingButton = (binding.yes); loadingButton.showLoading();
                new CountDownTimer(3000, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        loadingButton.hideLoading();
                        navController.navigate(R.id.action_reviewFragment_to_savedFragment);
                    }
                }.start();

            }
        });
    }
}