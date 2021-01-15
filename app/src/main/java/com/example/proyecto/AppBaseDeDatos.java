package com.example.proyecto;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Juego.class}, version = 3, exportSchema = false)

public abstract class AppBaseDeDatos extends RoomDatabase {

    private static volatile AppBaseDeDatos INSTANCE;
    static Executor executor = Executors.newSingleThreadExecutor();

    abstract AppDao obtenerJuegosDao();

    public static AppBaseDeDatos getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppBaseDeDatos.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppBaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                                    super.onDestructiveMigration(db);
                                    insertarJuegosIniciales(INSTANCE.obtenerJuegosDao());
                                }

                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    insertarJuegosIniciales(INSTANCE.obtenerJuegosDao());
                                }
                            })

                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Dao
    interface AppDao {

        @Insert
        void  insertarJuego(Juego juego);

        @Query("SELECT * FROM juego")
        LiveData<List<Juego>> obtenerJuegos();
    }

    static void insertarJuegosIniciales(AppDao appDao){
        executor.execute(() -> {
            appDao.insertarJuego(new Juego("Tetris", "1970", "file:///android_asset/tetris.jpg"));
        });
    }
}
