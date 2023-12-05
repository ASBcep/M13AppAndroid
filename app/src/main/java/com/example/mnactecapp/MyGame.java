package com.example.mnactecapp;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class MyGame extends ApplicationAdapter {

    enum Screen{EXPLANATION ,MAIN_GAME, GAME_OVER, WIN }
    private final static int TIMEOUT_TIME = 60;
    private float timeLeft;
    private Screen currentScreen = Screen.EXPLANATION;
    private SpriteBatch batch;
    private BitmapFont font;
    private String textQuestion;
    private float ScreenHeight,ScreenWidth,preguntaX,preguntaY,touchAreaWidth,touchAreaHeight;
    private AssetsManager assets;
    private Rectangle recUC, checkButtonBounds,recCC1,recCC2,recCC3, playButtonBounds, homeButton, backButton;;
    private ShapeRenderer shrend;
    private boolean debug;
    private int indexQuestion;
    private float touchX,touchY;
    private float laneA,laneB,laneC,laneD;
    private boolean canMove; // Indica si el jugador puede moverse
    private float touchAreaX; // Coordenada X del borde izquierdo del área de toque
    private float touchAreaY; // Coordenada Y del borde inferior del área de toque
    private boolean colision;
    private int lives;
    private int dificulty;
    private int score, questionsAnswered;
    private String language;
    private Array<Question>questions;
    private Context context;
    public MyGame(Intent intent, Context context) {
        // Obtener dificultad desde la activity donde se elige la dificultad
        dificulty = intent.getIntExtra("dificulty", 1);
        this.context = context;

    }

    @Override
    public void create () {

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        questions = new Array<>();
        loadQuestionsJSON();

        font = new BitmapFont();
        preguntaX = ScreenWidth / 4;
        preguntaY = ScreenHeight - 32;

        assets = new AssetsManager();
        assets.loadAssets();

        shrend = new ShapeRenderer();

        switch(dificulty){
            case 1:
                lives = 5;
                break;
            case 2:
                lives = 3;
                break;
            case 3:
                lives = 1;
                break;
            default:
        }

        // Idioma que se obtendrá del app de android
        language = "ES";

        // Posiciones de los carriles para inicializar los colisionCars
        laneA = 405;
        laneB = 735;
        laneC = 1030;
        laneD = 1350;

        // Limites de los botones
        checkButtonBounds = new Rectangle(32, 290, 244, 86);
        playButtonBounds = new Rectangle(ScreenWidth / 2 - 350, 300, 700, 252);
        homeButton = new Rectangle(32, 32, 247, 250);
        backButton = new Rectangle(ScreenWidth - 32 - assets.buttonBack.getWidth(), 32, 247, 250);
        // Rectangulos para trabajar las colisiones entre el vehiculo del usuario y de los otros coches
        recUC = new Rectangle(ScreenWidth / 2, ScreenHeight / 2,140,350);
        recCC1 = new Rectangle(0, ScreenHeight,145,350);
        recCC2 = new Rectangle(0, ScreenHeight,145,350);
        recCC3 = new Rectangle(0, ScreenHeight,145,350);


        // Tiempo para el timeout
        timeLeft = TIMEOUT_TIME;
        // Para ver los rectangulos
        debug = false;
        // Indice de la pregunta
        indexQuestion = 0;
        //Area donde el usuario puede mover el vehiculo
        touchAreaX = 340;
        touchAreaY = 0;
        // Para ver cuantas preguntas se contestán en total
        questionsAnswered = 0;
        // Booleano para que el vehiculo del usuario pueda moverse
        canMove = true;

        textQuestion = "";
        colision = true;
        batch = new SpriteBatch();

    }


    public void timeout(){
        timeLeft -= Gdx.graphics.getDeltaTime();
        if (timeLeft <= 0){
            dispose();
        }
    }

    @Override
    public void render () {
        touchX = Gdx.input.getX();
        touchY = ScreenHeight - Gdx.input.getY();
//------------------------------------------------------EXPLANATION----------------------------------------------------
        if(currentScreen == Screen.EXPLANATION) {
            ScreenUtils.clear(1, 1, 1, 1);

            if (playButtonBounds.contains(touchX, touchY))
            {
                currentScreen = Screen.MAIN_GAME;
            }

            batch.begin();
            font.getData().setScale(3.5f);
            font.setColor(Color.BLACK);
            drawExplanation();
            drawBackButton();
            batch.end();

            if(debug){
                shrend.begin(ShapeRenderer.ShapeType.Filled);
                shrend.setColor(Color.RED);
                shrend.rect(playButtonBounds.x, playButtonBounds.y, playButtonBounds.width, playButtonBounds.height);
                shrend.end();
            }
        }

//------------------------------------------------------MAIN GAME------------------------------------------------------
        else if(currentScreen == Screen.MAIN_GAME)
        {
            timeout();

            if (Gdx.input.isTouched()){
                timeLeft = TIMEOUT_TIME;
            }

            // Movimiento del coche del usuario
            if (canMove) {
                moverUserCar();
                ajustarLimites();
            }

            if (checkButtonBounds.contains(touchX, touchY)) {
                canMove = false;
                if (colision){
                    activarColisionCars();
                }else{
                    canMove = true;
                }
            }

            if (canMove && !checkButtonBounds.contains(touchX, touchY)){
                colision = true;
            }



            batch.begin();
            // Background Image
            batch.draw(assets.background,0,0);
            // User Car
            batch.draw(assets.userCar,recUC.x,recUC.y, 140, 350 );
            // Colision Cars
            spawnColisionCars();
            // Button Check
            batch.draw(assets.buttonCheck, checkButtonBounds.x, checkButtonBounds.y);
            // Pregunta
            textQuestion = questions.get(indexQuestion).getQuestion();
            font.getData().setScale(3.5F);
            font.setColor(Color.BLACK);
            font.draw(batch, textQuestion, preguntaX, preguntaY);
            // Opciones
            String[] optionsAnswer = questions.get(indexQuestion).getOptions();
            showOptions(optionsAnswer);
            batch.draw(assets.A, laneA + 30,16,100,100);
            batch.draw(assets.B, laneB + 30,16,100,100);
            batch.draw(assets.C, laneC + 30,16,100,100);
            batch.draw(assets.D, laneD + 30,16,100,100);
            // Vidas
            showLives();


            drawBackButton();
            batch.end();

            if(debug){
                //Azul
                shrend.begin(ShapeRenderer.ShapeType.Line);
                shrend.setColor(Color.BLUE);
                shrend.rect(recUC.x, recUC.y, recUC.width, recUC.height);
                shrend.end();

                shrend.begin(ShapeRenderer.ShapeType.Line);
                shrend.setColor(Color.RED);
                shrend.rect(recCC1.x, recCC1.y, recCC1.width, recCC1.height);
                shrend.end();

                shrend.begin(ShapeRenderer.ShapeType.Filled);
                shrend.setColor(Color.RED);
                shrend.rect(checkButtonBounds.x, checkButtonBounds.y, checkButtonBounds.width, checkButtonBounds.height);
                shrend.end();


                batch.begin();
                String time = String.valueOf(timeLeft);
                font.getData().setScale(3.5F);
                font.setColor(Color.BLACK);
                font.draw(batch,time,ScreenWidth - 200, ScreenHeight - 400);
                batch.end();
            }
//--------------------------------------------------GAME OVER----------------------------------------------------------
        }else if( currentScreen == Screen.GAME_OVER){

            ScreenUtils.clear(1, 1, 1, 1);

            batch.begin();
            batch.draw(assets.gameOver, 320, ScreenHeight - 400, 1280,352);
            font.getData().setScale(3.5f);
            font.setColor(Color.BLACK);
            font.draw(batch,finalScore(),ScreenWidth / 4.5f, ScreenHeight - 450);
            drawEndgame();
            drawBackButton();
            batch.end();
//----------------------------------------------------WIN--------------------------------------------------------------
        }else if(currentScreen == Screen.WIN){
            ScreenUtils.clear(1, 1, 1, 1);
            batch.begin();
            font.getData().setScale(3.5f);
            font.setColor(Color.BLACK);
            font.draw(batch,finalScore(),ScreenWidth / 4.5f, ScreenHeight - 450);
            drawEndgame();
            drawBackButton();
            batch.end();

        }

    }

//---------------------------------------------------FUNCIONES--------------------------------------------------------------


    public void drawEndgame(){
        if(currentScreen == Screen.GAME_OVER){
            switch (language){
                case "CAT":
                    font.draw(batch,"Se t'ha assignat el següent vehicle.", ScreenWidth / 4.5f, ScreenHeight - 550);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 250, 150, 500,275);
                    break;
                case "ES":
                    font.draw(batch,"Se te ha asignado el siguiente vehiculo.", ScreenWidth / 4.5f, ScreenHeight - 550);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 250, 150, 500,275);
                    break;
                case "EN":
                    font.draw(batch,"You have been assigned the following vehicle.", ScreenWidth / 4.5f, ScreenHeight - 550);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 250, 150, 500,275);
                    break;
                default:
            }
        }else if(currentScreen == Screen.WIN){
            switch (language){
                case "CAT":
                    batch.draw(assets.felicitats, ScreenWidth / 2 - 800, ScreenHeight - 440,1600,415);
                    font.draw(batch,"Se t'ha assignat el següent vehicle.", ScreenWidth / 4.5f, ScreenHeight - 550);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 250, 150, 500,275);
                    break;
                case "ES":
                    batch.draw(assets.felicidades, ScreenWidth / 2 - 600, ScreenHeight - 440, 1200, 415);
                    font.draw(batch,"Se te ha asignado el siguiente vehiculo.", ScreenWidth / 4.5f, ScreenHeight - 550);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 250, 150, 500,275);
                    break;
                case "EN":
                    batch.draw(assets.congrats, ScreenWidth / 2 - assets.congrats.getWidth()/2f, ScreenHeight - 415);
                    font.draw(batch,"You have been assigned the following vehicle.", ScreenWidth / 4.5f, ScreenHeight - 550);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 250, 150, 500,275);
                    break;
                default:
            }
        }
    }

    public void drawBackButton()
    {
        if (currentScreen == Screen.MAIN_GAME ){
            batch.draw(assets.buttonBack, backButton.x, backButton.y);
            if (backButton.contains(touchX, touchY))
            {
                currentScreen = Screen.EXPLANATION;
            }
        }else if (currentScreen == Screen.EXPLANATION || currentScreen == Screen.WIN || currentScreen == Screen.GAME_OVER){
            batch.draw(assets.buttonHome, homeButton.x, homeButton.y);
            if (homeButton.contains(touchX, touchY))
            {
                dispose();
            }
        }
    }
    public void drawExplanation()
    {
        switch (language){
            case "CAT":
                font.draw(batch,
                        "Aquest joc consisteix en un qüestionari de 4 opcions. Tindràs 4 carrils" +
                                "\nels quals representen cadascun les opcions." +
                                "\nPer respondre a les preguntes, has de moure el teu vehicle" +
                                "\ni deixar-lo a l'opció que creguis que és correcta i després prémer " +
                                "\nel botó 'Comprova' per verificar si has triat correctament" +
                                "\nSi estàs preparat, prem el botó 'Jugar'.", 15, ScreenHeight - 16);
                //font.draw(batch, "Si estàs preparat, prem el botó 'Jugar'.", 15, ScreenHeight - 700);
                batch.draw(assets.buttonJugar, playButtonBounds.x, playButtonBounds.y);

                break;
            case "ES":
                font.draw(batch,
                        "Este juego consiste en un quiz de 4 opciones. Tendrás 4 raíles" +
                                "\nlos cuales reprensentan cada uno las opciones." +
                                "\nPara contestar a las preguntas, tienes que mover tu vehiculo" +
                                "\ny dejarlo en la opción que creas la correcta y después darle " +
                                "\nal botón 'Check' para verificar si has elegido bien" +
                                "\nSi estás preparado dal al botón 'Jugar'.", 15, ScreenHeight - 16);
                //font.draw(batch, "Si estás preparado dal al botón 'Jugar'.", 15, ScreenHeight - 700);
                batch.draw(assets.buttonJugar, playButtonBounds.x, playButtonBounds.y);
                break;
            case "EN":
                font.draw(batch,
                        "This game consists of a quiz with 4 options. You will have 4 lanes" +
                                "\neach representing one of the options." +
                                "\nTo answer the questions, you need to move your vehicle" +
                                "\nand place it on the option you believe is correct, and then press " +
                                "\nthe 'Check' button to verify if you have chosen correctly" +
                                "\nIf you're ready, press the 'Play' button.", 15, ScreenHeight - 16);
                // font.draw(batch, "If you're ready, press the 'Play' button.", 15, ScreenHeight - 700);
                batch.draw(assets.buttonPlay, playButtonBounds.x, playButtonBounds.y);
                break;
            default:
        }
    }
    public String finalScore(){
        switch (language){
            case "CAT":
                if (score > 1 || score == 0){
                    return "Has respost bé " + score + " preguntes de " + questionsAnswered + ".";
                }else{
                    return "Has respost bé " + score + " pregunta de " + questionsAnswered + ".";
                }

            case "ES":
                if (score > 1 || score == 0){
                    return "Has contestado bien " + score + " preguntas de " + questionsAnswered + ".";
                }else{
                    return "Has contestado bien " + score + " pregunta de " + questionsAnswered + ".";
                }
            case "EN":
                if (score > 1 || score == 0){
                    return "You answered " + score + " questions out of " + questionsAnswered + " correctly.";
                }else{
                    return "You answered " + score + " question out of " + questionsAnswered + " correctly.";
                }

            default:
        }
        return null;
    }

    public void showLives(){
        float y = 0;
        for (int i = 0; i < lives; i++){
            batch.draw(assets.life,ScreenWidth - 128 - y, ScreenHeight - 128);
            y += 128;
        }
    }

    public void spawnColisionCars(){
        switch (questions.get(indexQuestion).getCorrectOption()){
            case "A":
                batch.draw(assets.colisionCar, laneB, recCC1.y,145,350);
                batch.draw(assets.colisionCar, laneC, recCC2.y,145,350);
                batch.draw(assets.colisionCar, laneD, recCC3.y,145,350);
                recCC1.setX(laneB);
                recCC2.setX(laneC);
                recCC3.setX(laneD);
                break;
            case "B":
                batch.draw(assets.colisionCar, laneA, recCC1.y,145,350);
                batch.draw(assets.colisionCar, laneC, recCC2.y,145,350);
                batch.draw(assets.colisionCar, laneD, recCC3.y,145,350);
                recCC1.setX(laneA);
                recCC2.setX(laneC);
                recCC3.setX(laneD);
                break;
            case "C":
                batch.draw(assets.colisionCar, laneA, recCC1.y,145,350);
                batch.draw(assets.colisionCar, laneB, recCC2.y,145,350);
                batch.draw(assets.colisionCar, laneD, recCC3.y,145,350);
                recCC1.setX(laneA);
                recCC2.setX(laneB);
                recCC3.setX(laneD);
                break;
            case "D":
                batch.draw(assets.colisionCar, laneA, recCC1.y,180,400);
                batch.draw(assets.colisionCar, laneB, recCC2.y,180,400);
                batch.draw(assets.colisionCar, laneC, recCC3.y,180,400);
                recCC1.setX(laneA);
                recCC2.setX(laneB);
                recCC3.setX(laneC);
                break;
            default:

        }
    }

    public void showOptions(String[] optionsAnswer){
        float opcionesY = preguntaY - 64; // Ajusta la posición vertical de las opciones
        for (int i = 0; i < optionsAnswer.length; i++) {
            String opcion = optionsAnswer[i];
            font.draw(batch, opcion, preguntaX, opcionesY - i * 64);
        }

    }


    public void loadQuestionsJSON() {

        String filepath = Gdx.files.getLocalStoragePath() + "json/questions.json";

        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            Gson gson = new Gson();
            Question[] loadQuestions = gson.fromJson(br, Question[].class);
            questions.addAll(loadQuestions);

            fr.close();
            br.close();
        }catch (IOException e){
            System.out.println(e);
            //si no aconsegueix llegir el json, carregarà preguntes per defecte
            loadQuestions();
        }


    }

    public void loadQuestions(){

        Question pregunta1 = new Question("Pregunta1",new String[]{"A","B","C","D"},"A");
        Question pregunta2 = new Question("Pregunta2",new String[]{"A2","B","C","D"},"B");
        Question pregunta3 = new Question("Pregunta3",new String[]{"A3","B","C","D"},"C");
        Question pregunta4 = new Question("Pregunta4",new String[]{"A4","B","C","D"},"D");
        Question pregunta5 = new Question("Pregunta5",new String[]{"A5","B","C","D"},"A");

        questions.add(pregunta1);
        questions.add(pregunta2);
        questions.add(pregunta3);
        questions.add(pregunta4);
        questions.add(pregunta5);

    }

    // Define una función que ajusta la posición del rectángulo para que no se salga de los límites
    public void ajustarLimites() {
        if (recUC.x < 0) {
            recUC.x = 0;
        }

        if (recUC.x + recUC.width > touchAreaWidth) {
            recUC.x = touchAreaWidth - recUC.width;
        }

        if (recUC.y < 0) {
            recUC.y = 0;
        }

        if (recUC.y + recUC.height > touchAreaHeight) {
            recUC.y = touchAreaHeight - recUC.height;
        }
    }

    public void moverUserCar(){
        touchAreaWidth = 1580; // Ancho del área de toque
        touchAreaHeight = 680;// Alto del área de toque
        if (!checkButtonBounds.contains(touchX,touchY)){
            recUC.x = Math.max(touchAreaX, Math.min(touchAreaX + touchAreaWidth - recUC.width, touchX - recUC.width / 2));
            recUC.y = Math.max(touchAreaY, Math.min(touchAreaY + touchAreaHeight - recUC.height, touchY - recUC.width / 2));
        }
    }



    public void activarColisionCars() {

        timeLeft = TIMEOUT_TIME;
        recCC1.y -= 25f;
        recCC2.y -= 25f;
        recCC3.y -= 25f;

        // Falló la pregunta
        if (recUC.overlaps(recCC1) || recUC.overlaps(recCC2) || recUC.overlaps(recCC3)) {
            lives--;
            questionsAnswered += 1;
            if (lives <= 0) {
                currentScreen = Screen.GAME_OVER;
            }
            // Si hay colisión, reducir las vidas
            // Restablecer las posiciones de los coches y avanzar a la siguiente pregunta
            recCC1.setY(ScreenHeight);
            recCC2.setY(ScreenHeight);
            recCC3.setY(ScreenHeight);
            colision = false;
            checkIndexQuestion();


        }

        // Acertó la pregunta
        if (recCC1.y + recCC1.height < 0 && recCC2.y + recCC2.height < 0 && recCC3.y + recCC3.height < 0) {
            recCC1.setY(ScreenHeight);
            recCC2.setY(ScreenHeight);
            recCC3.setY(ScreenHeight);
            score += 1;
            questionsAnswered += 1;
            colision = false;
            checkIndexQuestion();
        }

    }

    public void checkIndexQuestion(){
        if (indexQuestion < 4){
            indexQuestion += 1;
        }else{
            currentScreen = Screen.WIN;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        assets.disposeAssets();
        shrend.dispose();
        startNewActivity(Activity4.class);
    }

    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

}

