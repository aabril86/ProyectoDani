package com.example.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.proyecto.databinding.FragmentListaJuegosBinding;
import com.example.proyecto.databinding.ViewholderJuegoBinding;

import java.util.List;

public class ListaJuegosFragment extends Fragment {

    FragmentListaJuegosBinding binding;
    private JuegosViewModel juegosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentListaJuegosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //AQUI VA EL CODIGO
        NavController navController = Navigation.findNavController(view);

        juegosViewModel = new ViewModelProvider(this).get(JuegosViewModel.class);

        binding.irAInsertarJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_listaJuegosFragment_to_insertarJuegosFragment);
            }
        });


        AlbumsAdapter albumsAdapter = new AlbumsAdapter();
        binding.recyclerView.setAdapter(albumsAdapter);

        juegosViewModel.obtenerJuegos().observe(getViewLifecycleOwner(), albums -> {
            albumsAdapter.setAlbumList(albums);
        });
    }


    class AlbumsAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

        List<Juego> albumList;

        @NonNull
        @Override
        public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AlbumViewHolder(ViewholderJuegoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
            Juego album = albumList.get(position);

            holder.binding.titulo.setText(album.titulo);
            holder.binding.anyo.setText(album.year);
            Glide.with(holder.itemView).load(album.imagen).into(holder.binding.portada);
        }

        @Override
        public int getItemCount() {
            return albumList == null ? 0 : albumList.size();
        }

        public void setAlbumList(List<Juego> albumList) {
            this.albumList = albumList;
            notifyDataSetChanged();
        }
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ViewholderJuegoBinding binding;

        public AlbumViewHolder(@NonNull ViewholderJuegoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}