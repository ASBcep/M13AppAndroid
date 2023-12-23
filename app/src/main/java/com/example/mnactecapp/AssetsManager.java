package com.example.mnactecapp;

import android.annotation.SuppressLint;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AssetsManager extends ApplicationAdapter {

    public Texture background1,background2,userCar, colisionCar,buttonCheck,wheel,gameOver,congrats,felicidades,felicitats,
            buttonJugar,buttonPlay,buttonBack,buttonHome,assignedVehicle,A,B,C,D;
    public FileHandle FTbackground,FuserCar, FcolisionCar,FbuttonCheck,Fwheel,FgameOver,Fcongrats,Ffelicidades,Ffelicitats,
            FbuttonJugar,FbuttonPlay,FbuttonBack,FbuttonHome,FassignedVehicle,FA,FB,FC,FD;
    public void loadAssets(){

        /*provar a carregar img directament de drawable
        Tbackground = new Texture(Gdx.files.internal("drawable/imggame/background.png"));
        userCar = new Texture(Gdx.files.internal("drawable/imggame/userCar.png"));
        colisionCar = new Texture(Gdx.files.internal("drawable/imggame/colisionCar.png"));
        buttonCheck = new Texture(Gdx.files.internal("drawable/imggame/buttonCheck.png"));
        life = new Texture(Gdx.files.internal("drawable/imggame/life.png"));
        gameOver = new Texture(Gdx.files.internal("drawable/imggame/game_over.png"));
        congrats = new Texture(Gdx.files.internal("drawable/imggame/congrats.png"));
        felicidades = new Texture(Gdx.files.internal("drawable/imggame/felicidades.png"));
        felicitats = new Texture(Gdx.files.internal("drawable/imggame/felicitats.png"));
        buttonJugar = new Texture(Gdx.files.internal("drawable/imggame/buttonJugar.png"));
        buttonPlay = new Texture(Gdx.files.internal("drawable/imggame/buttonPlay.png"));
        buttonBack = new Texture(Gdx.files.internal("drawable/imggame/buttonBack.png"));
        buttonHome = new Texture(Gdx.files.internal("drawable/imggame/buttonHome.png"));
        assignedVehicle = new Texture(Gdx.files.internal("drawable/imggame/assignedVehicle.png"));
        A = new Texture(Gdx.files.internal("drawable/imggame/A.png"));
        B = new Texture(Gdx.files.internal("drawable/imggame/B.png"));
        C = new Texture(Gdx.files.internal("drawable/imggame/C.png"));
        D = new Texture(Gdx.files.internal("drawable/imggame/D.png"));
        */
        //comentat per provar a carregar img directament de drawable

        loadFileHandle();


        //cargar textura en variables
        background1 = new Texture(FTbackground);
        background2 = new Texture(FTbackground);
        userCar = new Texture(FuserCar);
        colisionCar = new Texture(FcolisionCar);
        buttonCheck = new Texture(FbuttonCheck);
        buttonJugar = new Texture(FbuttonJugar);
        buttonPlay = new Texture(FbuttonPlay);
        buttonBack = new Texture(FbuttonBack);
        buttonHome = new Texture(FbuttonHome);
        A = new Texture(FA);
        B = new Texture(FB);
        C = new Texture(FC);
        D = new Texture(FD);
        wheel = new Texture(Fwheel);
        gameOver = new Texture(FgameOver);
        congrats = new Texture(Fcongrats);
        felicidades = new Texture(Ffelicidades);
        felicitats = new Texture(Ffelicitats);

        //by ia from here
        //assetManager = new AssetManager();
        /*
        try {
            assetManager.load("drawable/imggame/background.png", Texture.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            assetManager.finishLoading(); // Esperar hasta que todas las texturas estén cargadas
        }catch (Exception e){
            e.printStackTrace();
        }
        */
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    assetManager.load("drawable/background.png", Texture.class);
                    // Carga de otras texturas...
                    assetManager.finishLoading(); // Espera a que todas las texturas estén cargadas
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // Obtener las texturas una vez cargadas

        try {
            Tbackground = assetManager.get("drawable/background.png", Texture.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        //end ia*/
    }
    public void loadFileHandle(){

        FTbackground = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/background4.png");
        FuserCar = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/userCar.png");
        FcolisionCar = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/colisionCar.png");
        FbuttonCheck = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/buttonCheck.png");
        Fwheel = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/wheel.png");
        FgameOver = new FileHandle(Gdx.files.getLocalStoragePath() + "/gameimg/game_over.png");
        Fcongrats = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/congrats.png");
        Ffelicidades = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/felicidades.png");
        Ffelicitats = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/felicitats.png");
        FbuttonJugar = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/buttonJugar.png");
        FbuttonPlay = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/buttonPlay.png");
        FbuttonBack = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/buttonBack.png");
        FbuttonHome = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/buttonHome.png");
        FA = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/A.png");
        FB = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/B.png");
        FC = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/C.png");
        FD = new FileHandle(Gdx.files.getLocalStoragePath() + "gameimg/D.png");

    }

    public void loadAssignedVehicle(int difficulty, int score) {
        String difficultyPrefix = "";

        switch (difficulty) {
            case 1:
                difficultyPrefix = "EASY";
                break;
            case 2:
                difficultyPrefix = "NORMAL";
                break;
            case 3:
                difficultyPrefix = "HARD";
                break;
        }

        if (score >= 17 && score <= 20) {
            FassignedVehicle = new FileHandle(Gdx.files.getLocalStoragePath() + "gameAssignedVehicle/assignedVehicle1720" + difficultyPrefix + ".png");
        } else if (score >= 13 && score <= 16) {
            FassignedVehicle = new FileHandle(Gdx.files.getLocalStoragePath() + "gameAssignedVehicle/assignedVehicle1316" + difficultyPrefix + ".png");
        } else if (score >= 9 && score <= 12) {
            FassignedVehicle = new FileHandle(Gdx.files.getLocalStoragePath() + "gameAssignedVehicle/assignedVehicle912" + difficultyPrefix + ".png");
        } else if (score >= 5 && score <= 8) {
            FassignedVehicle = new FileHandle(Gdx.files.getLocalStoragePath() + "gameAssignedVehicle/assignedVehicle58" + difficultyPrefix + ".png");
        } else if (score >= 0 && score <= 4) {
            FassignedVehicle = new FileHandle(Gdx.files.getLocalStoragePath() + "gameAssignedVehicle/assignedVehicle04" + difficultyPrefix + ".png");
        }

        assignedVehicle = new Texture(FassignedVehicle);
    }
    public void disposeAssets(){
        background1.dispose();
        background2.dispose();
        userCar.dispose();
        colisionCar.dispose();
        buttonCheck.dispose();
        buttonJugar.dispose();
        buttonPlay.dispose();
        buttonBack.dispose();
        buttonHome.dispose();
        A.dispose();
        B.dispose();
        C.dispose();
        D.dispose();
        wheel.dispose();
        gameOver.dispose();
        congrats.dispose();
        felicidades.dispose();
        felicitats.dispose();
        assignedVehicle.dispose();
    }

}