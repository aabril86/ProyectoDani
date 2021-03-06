package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.proyecto.databinding.ActivityMainBinding;
import com.example.proyecto.databinding.NavHeaderMainBinding;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavHeaderMainBinding navHeaderMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        navHeaderMainBinding = NavHeaderMainBinding.bind(binding.navView.getHeaderView(0));
        setSupportActionBar(binding.toolbar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.listaJuegosFragment, R.id.profileFragment, R.id.savedFragment, R.id.forumFragment2, R.id.settingsFragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        //NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
        getSupportActionBar().setTitle("     Home");
        getSupportActionBar().setLogo(R.drawable.ic_hamburguer);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.splashFragment
                        || destination.getId() == R.id.logInFragment
                        || destination.getId() == R.id.signUpFragment
                        || destination.getId() == R.id.juegoFragment
                        || destination.getId() == R.id.juegoReviewFragment
                        || destination.getId() == R.id.profileFragment
                        || destination.getId() == R.id.profileAboutFragment
                        || destination.getId() == R.id.editProfileFragment
                        || destination.getId() == R.id.savedFragment
                        || destination.getId() == R.id.reviewFragment
                        || destination.getId() == R.id.forumFragment2
                        || destination.getId() == R.id.createPostFragment
                        || destination.getId() == R.id.settingsFragment) {
                    binding.toolbar.setVisibility(View.GONE);
                    binding.navView.setVisibility(View.GONE);
                } else {
                    binding.toolbar.setVisibility(View.VISIBLE);
                    binding.navView.setVisibility(View.VISIBLE);
                }
            }
        });

        // Cargar nombre de usuario y foto en el Header del Drawer
        navHeaderMainBinding.usuario.setText("Wade Warren");
        Glide.with(this).load(R.drawable.profile_image).circleCrop().into(navHeaderMainBinding.fotoUsuario);

    }
}