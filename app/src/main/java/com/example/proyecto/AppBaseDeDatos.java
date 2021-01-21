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

@Database(entities = {Juego.class}, version = 9, exportSchema = false)

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

        @Query("SELECT * FROM juego WHERE year > 2018")
        LiveData<List<Juego>> obtenerJuegosNuevos();

        @Query("SELECT * FROM juego WHERE year < 2018")
        LiveData<List<Juego>> obtenerJuegosPopulares();

    }

    static void insertarJuegosIniciales(AppDao appDao){
        executor.execute(() -> {
            appDao.insertarJuego(new Juego("Rocket", "2019", "PC","file:///android_asset/rocket_league_portada.png"));
            appDao.insertarJuego(new Juego("Genshin", "2019", "PC","file:///android_asset/genshin_impact_portada.png"));
            appDao.insertarJuego(new Juego("Valorant", "2019", "PC",  "file:///android_asset/valorant_portada.png"));
            appDao.insertarJuego(new Juego("Warzone", "2019", "PC","file:///android_asset/cod_portada.png"));
            appDao.insertarJuego(new Juego("Runeterra", "2019", "PC", "file:///android_asset/runeterra_portada.png"));
            appDao.insertarJuego(new Juego("CSGO", "2016", "Steam", "file:///android_asset/icon_csgo.png"));
            appDao.insertarJuego(new Juego("Fortnite", "2017", "Epic", "file:///android_asset/icon_fortnite.png"));
            appDao.insertarJuego(new Juego("DOTA", "2016", "Steam", "file:///android_asset/icon_dota.png"));
            appDao.insertarJuego(new Juego("Path of Exile", "2015", "Steam", "file:///android_asset/icon_poe.png"));
            appDao.insertarJuego(new Juego("League of Legends", "2015", "/", "file:///android_asset/icon_lol.png"));
        });
    }
}
