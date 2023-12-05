package com.example.mnactecapp;

import android.annotation.SuppressLint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AssetsManager {

    public Texture Tbackground,userCar, colisionCar,buttonCheck,life,gameOver,congrats,felicidades,felicitats,
            buttonJugar,buttonPlay,buttonBack,buttonHome,assignedVehicle,A,B,C,D;
    public FileHandle FTbackground,FuserCar, FcolisionCar,FbuttonCheck,Flife,FgameOver,Fcongrats,Ffelicidades,Ffelicitats,
            FbuttonJugar,FbuttonPlay,FbuttonBack,FbuttonHome,FassignedVehicle,FA,FB,FC,FD;
    public TextureRegion background;
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

        Tbackground = new Texture(FTbackground);
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
        life = new Texture(Flife);
        gameOver = new Texture(FgameOver);
        congrats = new Texture(Fcongrats);
        felicidades = new Texture(Ffelicidades);
        felicitats = new Texture(Ffelicitats);
        assignedVehicle = new Texture(FassignedVehicle);
        background = new TextureRegion(Tbackground,0,0,Tbackground.getWidth(),Tbackground.getHeight());

    }
    public void loadFileHandle(){


         FTbackground = new FileHandle(Gdx.files.getLocalStoragePath() + "img/background.png");
         FuserCar = new FileHandle(Gdx.files.getLocalStoragePath() + "img/userCar.png");
         FcolisionCar = new FileHandle(Gdx.files.getLocalStoragePath() + "img/colisionCar.png");
         FbuttonCheck = new FileHandle(Gdx.files.getLocalStoragePath() + "img/buttonCheck.png");
         Flife = new FileHandle(Gdx.files.getLocalStoragePath() + "img/life.png");
         FgameOver = new FileHandle(Gdx.files.getLocalStoragePath() + "/img/game_over.png");
         Fcongrats = new FileHandle(Gdx.files.getLocalStoragePath() + "img/congrats.png");
         Ffelicidades = new FileHandle(Gdx.files.getLocalStoragePath() + "img/felicidades.png");
         Ffelicitats = new FileHandle(Gdx.files.getLocalStoragePath() + "img/felicitats.png");
         FbuttonJugar = new FileHandle(Gdx.files.getLocalStoragePath() + "img/buttonJugar.png");
         FbuttonPlay = new FileHandle(Gdx.files.getLocalStoragePath() + "img/buttonPlay.png");
         FbuttonBack = new FileHandle(Gdx.files.getLocalStoragePath() + "img/buttonBack.png");
         FbuttonHome = new FileHandle(Gdx.files.getLocalStoragePath() + "img/buttonHome.png");
         FassignedVehicle = new FileHandle(Gdx.files.getLocalStoragePath() + "img/assignedVehicle.png");
         FA = new FileHandle(Gdx.files.getLocalStoragePath() + "img/A.png");
         FB = new FileHandle(Gdx.files.getLocalStoragePath() + "img/B.png");
         FC = new FileHandle(Gdx.files.getLocalStoragePath() + "img/C.png");
         FD = new FileHandle(Gdx.files.getLocalStoragePath() + "img/D.png");

    }
    public void disposeAssets(){
        Tbackground.dispose();
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
        life.dispose();
        gameOver.dispose();
        congrats.dispose();
        felicidades.dispose();
        felicitats.dispose();
        assignedVehicle.dispose();
    }

}