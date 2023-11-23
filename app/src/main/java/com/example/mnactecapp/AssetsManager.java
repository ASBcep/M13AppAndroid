package com.example.mnactecapp;

import android.annotation.SuppressLint;

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


    @SuppressLint("SdCardPath")
    public void loadFileHandle(){

         FTbackground = new FileHandle("/data/data/com.example.mnactecapp/files/img/background.png");
         FuserCar = new FileHandle("/data/data/com.example.mnactecapp/files/img/userCar.png");
         FcolisionCar = new FileHandle("/data/data/com.example.mnactecapp/files/img/colisionCar.png");
         FbuttonCheck = new FileHandle("/data/data/com.example.mnactecapp/files/img/buttonCheck.png");
         Flife = new FileHandle("/data/data/com.example.mnactecapp/files/img/life.png");
         FgameOver = new FileHandle("/data/data/com.example.mnactecapp/files/img/game_over.png");
         Fcongrats = new FileHandle("/data/data/com.example.mnactecapp/files/img/congrats.png");
         Ffelicidades = new FileHandle("/data/data/com.example.mnactecapp/files/img/felicidades.png");
         Ffelicitats = new FileHandle("/data/data/com.example.mnactecapp/files/img/felicitats.png");
         FbuttonJugar = new FileHandle("/data/data/com.example.mnactecapp/files/img/buttonJugar.png");
         FbuttonPlay = new FileHandle("/data/data/com.example.mnactecapp/files/img/buttonPlay.png");
         FbuttonBack = new FileHandle("/data/data/com.example.mnactecapp/files/img/buttonBack.png");
         FbuttonHome = new FileHandle("/data/data/com.example.mnactecapp/files/img/buttonHome.png");
         FassignedVehicle = new FileHandle("/data/data/com.example.mnactecapp/files/img/assignedVehicle.png");
         FA = new FileHandle("/data/data/com.example.mnactecapp/files/img/A.png");
         FB = new FileHandle("/data/data/com.example.mnactecapp/files/img/B.png");
         FC = new FileHandle("/data/data/com.example.mnactecapp/files/img/C.png");
         FD = new FileHandle("/data/data/com.example.mnactecapp/files/img/D.png");

    }
    public void disposeAssets(){
        Tbackground.dispose();
        userCar.dispose();
        colisionCar.dispose();
    }

}