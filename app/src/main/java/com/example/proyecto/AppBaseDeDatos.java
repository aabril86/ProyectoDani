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

@Database(entities = {Juego.class}, version = 13, exportSchema = false)

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
            appDao.insertarJuego(new Juego("Rocket", "2019", "Psyonix","Rocket League is a high-powered hybrid of arcade-style soccer and vehicular mayhem with easy-to-understand controls and fluid, physics-driven competition." ,"PC","file:///android_asset/rocket_league_portada.png"));
            appDao.insertarJuego(new Juego("Genshin", "2019", "miHoYo","Genshin Impact is an open world action role-playing game that allows the player to control one of four interchangeable characters in a party. Switching between characters can be done quickly and during combat to the player to use several different combinations of skills and attacks.","PC","file:///android_asset/genshin_impact_portada.png"));
            appDao.insertarJuego(new Juego("Valorant", "2019", "Riot","Valorant is a team-based tactical shooter and first-person shooter set in the near-future. Players play as one of a set of agents, characters designed based on several countries and cultures around the world","PC","file:///android_asset/valorant_portada.png"));
            appDao.insertarJuego(new Juego("Warzone", "2019", "Infinity Award","Warzone features two primary game modes: Battle Royale and Plunder.[5] It is the second main battle royale installment in the Call of Duty franchise, following the \"Blackout\" mode of Call of Duty: Black Ops 4 (2018)","PC","file:///android_asset/cod_portada.png"));
            appDao.insertarJuego(new Juego("Runeterra", "2019", "Riot","Legends of Runeterra is played 1v1. Each player begins the match with a hand of four playing cards randomly selected from their card deck, a 20-health-point nexus, and zero mana.","PC", "file:///android_asset/runeterra_portada.png"));
            appDao.insertarJuego(new Juego("CSGO", "2016", "Valve","Two opposing teams, known as the Terrorists and the Counter-Terrorists, compete in game modes to complete objectives, such as securing a location to plant or defuse a bomb and rescuing or guarding hostages.","Steam", "file:///android_asset/icon_csgo.png"));
            appDao.insertarJuego(new Juego("Fortnite", "2017", "Epic","Players can use their pickaxe to knock down existing structures on the map to collect basic resources that are wood, brick, and metal. Subsequently, in all modes, the player can use these materials to build fortifications, such as walls, floors, and stairs.","Epic", "file:///android_asset/icon_fortnite.png"));
            appDao.insertarJuego(new Juego("DOTA", "2016", "Valve","Dota 2 is a multiplayer online battle arena (MOBA) video game in which two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.","Steam", "file:///android_asset/icon_dota.png"));
            appDao.insertarJuego(new Juego("Path of Exile", "2015","Valve","Dota 2 is a multiplayer online battle arena (MOBA) video game in which two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.", "Steam", "file:///android_asset/icon_poe.png"));
            appDao.insertarJuego(new Juego("League of Legends", "2015", "Riot","As in other multiplayer online battle arena (MOBA) games, each player in League of Legends controls a character (\"champion\") with a set of unique abilities.[2] Most games involve two teams of five players, with each player using a different champion.","/", "file:///android_asset/icon_lol.png"));
        });
    }
}
