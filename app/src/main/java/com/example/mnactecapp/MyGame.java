package com.example.mnactecapp;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;


public class MyGame extends ApplicationAdapter {

    enum Screen{EXPLANATION ,MAIN_GAME, GAME_OVER, WIN }
    private final static int TIMEOUT_TIME = 60;
    private final static int TIMEQUESTION = 30;
    private float timeLeft, timeQuestion;;
    private Screen currentScreen = Screen.EXPLANATION;
    private SpriteBatch batch;
    private BitmapFont font;
    private String textQuestion;
    private float ScreenHeight,ScreenWidth,preguntaX,preguntaY;
    private AssetsManager assets;
    private Rectangle recUC,recCarArea,checkButtonBounds,recCC1,recCC2,recCC3, playButtonBounds, homeButton, backButton;
    private ShapeRenderer shrend;
    private boolean debug;
    private int indexQuestion;
    private float touchX,touchY;
    private float laneA,laneB,laneC,laneD;
    private boolean canMove; // Indica si el jugador puede moverse
    private boolean colision;
    private int lives;
    private int dificulty;
    private int score, questionsAnswered;
    private int language;
    private Array<Question>questions;
    private final Context context;
    private float backgroundY1,backgroundY2;
    private QuestionManager questionManager;
    private Animation<TextureRegion> explosionGame;
    private float stateTime;
    private boolean showExplosion;

    public MyGame(Intent intent, Context context) {
        // Obtener dificultad desde la activity donde se elige la dificultad
        dificulty = intent.getIntExtra("dificulty", 1);
        this.context = context;
    }

    @Override
    public void create () {

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        // Idioma que se obtendrá del app de android
        language = ElementManager.INSTANCE.getIdioma();

        questions = new Array<>();
        loadQuestionsJSON();

        font = new BitmapFont();
        preguntaX = ScreenWidth / 4 + 25;
        preguntaY = ScreenHeight - 24;

        assets = new AssetsManager();
        assets.loadUserCar(dificulty);
        assets.loadAssets();


        shrend = new ShapeRenderer();

        //1 = easy; 2 = normal; 3 = hard;

        switch(dificulty){
            case 1:
                lives = 6;
                break;
            case 2:
                lives = 4;
                break;
            case 3:
                lives = 2;
                break;
            default:
        }

        // Posiciones de los carriles para inicializar los colisionCars
        laneA = 405;
        laneB = 735;
        laneC = 1030;
        laneD = 1350;

        // Limites de los botones
        checkButtonBounds = new Rectangle(ScreenWidth - 32 - assets.buttonCheck.getWidth(), 290, 244, 86);
        playButtonBounds = new Rectangle(ScreenWidth / 2 - 350, 300, 700, 252);
        homeButton = new Rectangle(32, 32, 247, 250);
        backButton = new Rectangle(ScreenWidth - 32 - assets.buttonBack.getWidth(), 32, 247, 250);
        //Area donde el usuario puede mover el vehiculo
        recCarArea = new Rectangle(340,0,1240,1180);
        // Rectangulos para trabajar las colisiones entre el vehiculo del usuario y de los otros coches
        recUC = new Rectangle(ScreenWidth / 2, ScreenHeight / 2,200,350);
        recCC1 = new Rectangle(0, ScreenHeight,145,350);
        recCC2 = new Rectangle(0, ScreenHeight,145,350);
        recCC3 = new Rectangle(0, ScreenHeight,145,350);


        backgroundY1 = 0;
        backgroundY2 = -ScreenHeight;


        // Tiempo para el timeout
        timeLeft = TIMEOUT_TIME;
        timeQuestion = TIMEQUESTION;
        // Para ver los rectangulos
        debug = false;

        questionManager = new QuestionManager();
        // Indice de la pregunta
        indexQuestion = randomQuestion();
        // Para ver cuantas preguntas se contestán en total
        questionsAnswered = 0;
        // Booleano para que el vehiculo del usuario pueda moverse
        canMove = true;

        textQuestion = "";
        colision = true;
        batch = new SpriteBatch();

        TextureRegion[][] regions = TextureRegion.split(assets.sheet, 256, 256); // Ajusta el tamaño del frame
        TextureRegion[] frames = Arrays.copyOf(regions[0], regions[0].length, TextureRegion[].class);
        explosionGame = new Animation<>(0.08f, frames); // duración de cada frame en segundos

        stateTime = 0f;
        showExplosion = false;
    }


    public void reset(){
        switch(dificulty){
            case 1:
                lives = 6;
                break;
            case 2:
                lives = 4;
                break;
            case 3:
                lives = 2;
                break;
            default:
        }
        indexQuestion = randomQuestion();
        questionsAnswered = 0;
        score = 0;
        textQuestion= "";
    }

    public void timeout(){
        timeLeft -= Gdx.graphics.getDeltaTime();
        if (timeLeft <= 0){
            dispose();
        }
    }

    @Override
    public void render () {
        handleInput();
        updateGameState();
        drawGame();

    }

//---------------------------------------------------FUNCIONES--------------------------------------------------------------

    private void handleInput(){
        touchX = Gdx.input.getX();
        touchY = ScreenHeight - Gdx.input.getY();
    }

    private void updateGameState() {
        timeout();

        if (currentScreen == Screen.MAIN_GAME) {

            if (Gdx.input.isTouched()) {
                timeLeft = TIMEOUT_TIME;
            }


            if (recCarArea.contains(touchX,touchY)){
                // Movimiento del coche del usuario
                if (canMove) {
                    moverUserCar();
                }
            }

            timeQuestion -= Gdx.graphics.getDeltaTime();


            if (timeQuestion <= 0 || isButtonPressed(checkButtonBounds)) {
                timeQuestion = 0;
                canMove = false;
                // Verificar si el botón de comprobación está presionado
                if (colision) {
                    checkAnswer();
                } else {
                    // Restablecer el tiempo y permitir que el jugador se mueva nuevamente
                    canMove = true;
                    timeQuestion = TIMEQUESTION;
                }
            }

            if (canMove && !isButtonPressed(checkButtonBounds)) {
                colision = true;
            }
        }

    }

    public boolean isButtonPressed(Rectangle button){
        return Gdx.input.justTouched() && button.contains(touchX,touchY);
    }

    private void drawGame(){
        float backgroundVelocity = 15f;
        stateTime += Gdx.graphics.getDeltaTime();
        switch (currentScreen){
            case EXPLANATION:
                ScreenUtils.clear(1, 1, 1, 1);

                if (isButtonPressed(playButtonBounds))
                {
                    currentScreen = Screen.MAIN_GAME;
                }

                batch.begin();
                font.getData().setScale(4f);
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
                break;
            case MAIN_GAME:
                batch.begin();

                // Background Image
                batch.draw(assets.background1, 0, backgroundY1, ScreenWidth, ScreenHeight);
                batch.draw(assets.background2, 0, backgroundY2, ScreenWidth, ScreenHeight);

                // Mueve los fondos hacia abajo
                backgroundY1 -= backgroundVelocity;
                backgroundY2 -= backgroundVelocity;

                if (backgroundY1 + ScreenHeight <= 0) {
                    backgroundY1 = ScreenHeight;
                }

                if (backgroundY2 + ScreenHeight <= 0) {
                    backgroundY2 = ScreenHeight;
                }

                float xUC = recUC.x + recUC.width / 6;
                // User Car
                switch (dificulty){
                    case 1:
                        batch.draw(assets.userCar,xUC,recUC.y, 155, 410 );
                        break;
                    case 2:
                        batch.draw(assets.userCar,xUC,recUC.y, 150, 350 );
                        break;
                    case 3:
                        batch.draw(assets.userCar,xUC,recUC.y, 110, 300 );
                        break;

                }



                // Button Check
                batch.draw(assets.buttonCheck, checkButtonBounds.x, checkButtonBounds.y);

                // Pregunta
                float maxWidth = ScreenWidth - preguntaX * 2;
                batch.draw(assets.blackBackgroundCircle,preguntaX - 20 , preguntaY - 160 , maxWidth + 15, 180 );
                textQuestion = questions.get(indexQuestion).getQuestion();
                font.getData().setScale(3);
                font.setColor(Color.WHITE);
                adjustText(textQuestion,preguntaX,preguntaY,maxWidth);

                // Opciones
                String[] optionsAnswer = questions.get(indexQuestion).getOptions();
                font.getData().setScale(3);
                font.setColor(Color.WHITE);
                showOptions(optionsAnswer);
                batch.draw(assets.A, laneA + 30,16,100,100);
                batch.draw(assets.B, laneB + 30,16,100,100);
                batch.draw(assets.C, laneC + 30,16,100,100);
                batch.draw(assets.D, laneD + 30,16,100,100);
                // Vidas
                showLives();
                // Score
                String scoreTXT = String.valueOf(score);
                font.getData().setScale(3.5F);
                font.setColor(Color.BLACK);
                font.draw(batch, "Score: " + scoreTXT, 16, ScreenHeight - 16);

                // Colision Cars
                spawnColisionCars();

                //Tiempo restante para contestar
                font.getData().setScale(10);
                if (timeQuestion > 15){
                    font.setColor(Color.GREEN);
                }else if(timeQuestion <= 15 && timeQuestion > 5){
                    font.setColor(Color.YELLOW);
                } else if (timeQuestion <= 5 ){
                    font.setColor(Color.RED);
                }
                DecimalFormat format = new DecimalFormat("0");
                String timeQuestionS = format.format(timeQuestion);
                font.draw(batch, timeQuestionS, 16 , ScreenHeight - 120);
                font.getData().setScale(4);

                drawBackButton();


                if (showExplosion && stateTime < explosionGame.getAnimationDuration()){
                    explosionGame.setPlayMode(Animation.PlayMode.NORMAL);
                    TextureRegion currentFrame = explosionGame.getKeyFrame(stateTime, false);
                    batch.draw(currentFrame, ScreenWidth / 2 - 900, ScreenHeight / 2 - 900, 1800,1800);
                }
                batch.end();

                if(debug) {
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

                    shrend.begin(ShapeRenderer.ShapeType.Line);
                    shrend.setColor(Color.CYAN);
                    shrend.rect(recCarArea.x,recCarArea.y, recCarArea.width,recCarArea.height);
                    shrend.end();


                    batch.begin();
                    String timeTimeout = String.valueOf(timeLeft);
                    String qa = String.valueOf(questionsAnswered);
                    String touchXS = String.valueOf(touchX);
                    String touchYS = String.valueOf(touchY);
                    String touchXYS = "TouchX: " + touchXS + "\nTouchY: " +  touchYS;
                    font.getData().setScale(3.5F);
                    font.setColor(Color.BLACK);
                    font.draw(batch, timeTimeout, ScreenWidth - 200, ScreenHeight - 400);
                    font.draw(batch,qa,ScreenWidth - 200, ScreenHeight - 600);
                    font.draw(batch,touchXYS,16, ScreenHeight - 400);
                    batch.end();
                }
                break;
            case GAME_OVER:
                showExplosion = true;
                ScreenUtils.clear(0.3f, 0.3f, 0.3f, 1);
                batch.begin();
                batch.draw(assets.grayBackground, 0, 0, ScreenWidth, ScreenHeight);
                if (showExplosion && stateTime < explosionGame.getAnimationDuration()){
                    explosionGame.setPlayMode(Animation.PlayMode.NORMAL);
                    TextureRegion currentFrame = explosionGame.getKeyFrame(stateTime, false);
                    batch.draw(currentFrame, ScreenWidth / 2 - 3250, ScreenHeight / 2 - 3250, 6500,6500);
                }else{
                    batch.draw(assets.gameOver, (ScreenWidth - 1280 )/ 2, ScreenHeight - 400, 1280,352);
                    font.getData().setScale(3.5f);
                    font.setColor(Color.WHITE);
                    font.draw(batch,finalScore(),ScreenWidth / 2 - 440, ScreenHeight - 440);
                    drawEndgame();
                    drawBackButton();
                }
                batch.end();
                break;
            case WIN:
                ScreenUtils.clear(0.3f, 0.3f, 0.3f, 1);;
                batch.begin();
                batch.draw(assets.grayBackground, 0, 0, ScreenWidth, ScreenHeight);
                font.getData().setScale(3.5f);
                font.setColor(Color.WHITE);
                font.draw(batch,finalScore(),ScreenWidth / 2 - 440, ScreenHeight - 440);
                drawEndgame();
                drawBackButton();
                batch.end();
                break;

        }
    }

    public void drawEndgame(){
        if(currentScreen == Screen.GAME_OVER){
            switch (language){
                case 0:
                    assets.loadAssignedVehicle(dificulty,score);
                    font.draw(batch,"Se t'ha assignat el següent vehicle.", ScreenWidth / 2 - 440, ScreenHeight - 540);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 375 , 32, 750,425);
                    batch.draw(assets.frame, ScreenWidth / 2 - 380 , 32, 760,430);
                    font.getData().setScale(3.5f);
                    font.draw(batch,"Sortir", (homeButton.getWidth() - 70) / 2 , homeButton.getY() + homeButton.height + 60);
                    font.draw(batch,"Tornar a jugar", ScreenWidth - 316, backButton.getY() + backButton.height + 60);
                    break;
                case 1:
                    assets.loadAssignedVehicle(dificulty,score);
                    font.draw(batch,"Se te ha asignado el siguiente vehiculo.", ScreenWidth / 2 - 440, ScreenHeight - 540);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 375 , 32, 750,425);
                    batch.draw(assets.frame, ScreenWidth / 2 - 380 , 32, 760,430);
                    font.getData().setScale(3.5f);
                    font.draw(batch,"Salir", (homeButton.getWidth() - 60) / 2 , homeButton.getY() + homeButton.height + 60);
                    font.draw(batch,"Volver a jugar", ScreenWidth - 316, backButton.getY() + backButton.height + 60);
                    break;
                case 2:
                    assets.loadAssignedVehicle(dificulty,score);
                    font.draw(batch,"You have been assigned the following vehicle.", ScreenWidth / 2 - 440, ScreenHeight - 540);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 375 , 32, 750,425);
                    batch.draw(assets.frame, ScreenWidth / 2 - 380 , 32, 760,430);
                    font.getData().setScale(3.5f);
                    font.draw(batch,"Leave", (homeButton.getWidth() - 70) / 2 , homeButton.getY() + homeButton.height + 60);
                    font.draw(batch,"Try again", ScreenWidth - 300, backButton.getY() + backButton.height + 60);
                    break;
                default:
            }
        }else if(currentScreen == Screen.WIN){

            switch (language){
                case 0:
                    assets.loadAssignedVehicle(dificulty,score);
                    batch.draw(assets.felicitats, ScreenWidth / 2 - 800, ScreenHeight - 430,1600,415);
                    font.draw(batch,"Se t'ha assignat el següent vehicle.", ScreenWidth / 2 - 440, ScreenHeight - 540);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 375 , 32, 750,425);
                    batch.draw(assets.frame, ScreenWidth / 2 - 380 , 32, 760,430);
                    font.getData().setScale(3.5f);
                    font.draw(batch,"Sortir", (homeButton.getWidth() - 70) / 2 , homeButton.getY() + homeButton.height + 60);
                    font.draw(batch,"Tornar a jugar", ScreenWidth - 316, backButton.getY() + backButton.height + 60);
                    break;
                case 1:
                    assets.loadAssignedVehicle(dificulty,score);
                    batch.draw(assets.felicidades, ScreenWidth / 2 - 600, ScreenHeight - 430, 1200, 415);
                    font.draw(batch,"Se te ha asignado el siguiente vehiculo.", ScreenWidth / 2 - 440, ScreenHeight - 540);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 375 , 32, 750,425);
                    batch.draw(assets.frame, ScreenWidth / 2 - 380 , 32, 760,430);
                    font.getData().setScale(3.5f);
                    font.draw(batch,"Salir", (homeButton.getWidth() - 60) / 2 , homeButton.getY() + homeButton.height + 60);
                    font.draw(batch,"Volver a jugar", ScreenWidth - 316, backButton.getY() + backButton.height + 60);
                    break;
                case 2:
                    assets.loadAssignedVehicle(dificulty,score);
                    batch.draw(assets.congrats, ScreenWidth / 2 - assets.congrats.getWidth()/2f, ScreenHeight - 415);
                    font.draw(batch,"You have been assigned the following vehicle.", ScreenWidth / 2 - 440, ScreenHeight - 540);
                    batch.draw(assets.assignedVehicle, ScreenWidth / 2 - 375 , 32, 750,425);
                    batch.draw(assets.frame, ScreenWidth / 2 - 380 , 32, 760,430);
                    font.getData().setScale(3.5f);
                    font.draw(batch,"Leave", (homeButton.getWidth() - 70) / 2 , homeButton.getY() + homeButton.height + 60);
                    font.draw(batch,"Play again", ScreenWidth - 285, backButton.getY() + backButton.height + 60);
                    break;
                default:
            }
        }
    }


    public void backButtonAction(){
        if (homeButton.contains(touchX,touchY)){
            dispose();
        }
        if (backButton.contains(touchX,touchY)){
            currentScreen = Screen.EXPLANATION;
        }
    }

    public void drawBackButton()
    {
        if (currentScreen == Screen.MAIN_GAME ){

            batch.draw(assets.buttonBack, backButton.x, backButton.y);

            if (isButtonPressed(backButton))
            {
                backButtonAction();
            }
        }else if (currentScreen == Screen.EXPLANATION){

            batch.draw(assets.buttonHome, homeButton.x, homeButton.y);

            if (isButtonPressed(homeButton))
            {
                backButtonAction();
            }
        }else if(currentScreen == Screen.WIN || currentScreen == Screen.GAME_OVER){

            batch.draw(assets.buttonBack, backButton.x, backButton.y);
            batch.draw(assets.buttonHome, homeButton.x, homeButton.y);

            if (isButtonPressed(homeButton))
            {
                backButtonAction();
            }
            if (isButtonPressed(backButton))
            {
                reset();
                backButtonAction();
            }
        }
    }

    public void drawExplanation()
    {
        String explanation;
        float explanationX = 5;
        float explanationY = ScreenHeight - 32;
        float maxWidth = ScreenWidth; // Ancho máximo para el texto
        switch (language){
            case 0:
                explanation = "Aquest joc consisteix en un qüestionari 20 preguntes amb 4 opcions. Tindràs 4 carrils els quals representen cadascun les opcions. Per respondre a les preguntes, has de moure el teu vehicle i deixar-lo a l'opció que creguis que és correcta abans que s'acabi el temps o prémer el botó 'Check' per verificar si has triat correctament. Si estàs preparat, prem el botó 'Jugar'.";
                adjustText(explanation,explanationX, explanationY, maxWidth);
                batch.draw(assets.buttonJugar, playButtonBounds.x, playButtonBounds.y);
                font.draw(batch,"Sortir", (homeButton.getWidth() - 70) / 2 , homeButton.getY() + homeButton.height + 60);
                break;
            case 1:
                explanation = "Este juego consiste en un cuestionario de 20 preguntas con 4 opciones. Tendrás 4 carriles que representan cada una de las opciones. Para responder a las preguntas, debes mover tu vehículo y dejarlo en la opción que creas que es correcta antes de que se acabe el tiempo o presionar el botón 'Check' para verificar si has elegido correctamente. Si estás listo, presiona el botón 'Jugar'.";
                adjustText(explanation,explanationX, explanationY, maxWidth);
                batch.draw(assets.buttonJugar, playButtonBounds.x, playButtonBounds.y);
                font.draw(batch,"Salir", (homeButton.getWidth() - 60) / 2 , homeButton.getY() + homeButton.height + 60);
                break;
            case 2:
                explanation = "This game consists of a quiz of 20 questions with 4 options. You will have 4 lanes, each representing one of the options. To answer the questions, you need to move your vehicle and place it on the option you believe is correct before time runs out or press the 'Check' button to verify if you have chosen correctly. If you're ready, press the 'Play' button.";
                adjustText(explanation,explanationX, explanationY, maxWidth);
                batch.draw(assets.buttonPlay, playButtonBounds.x, playButtonBounds.y);
                font.draw(batch,"Leave", (homeButton.getWidth() - 70) / 2 , homeButton.getY() + homeButton.height + 60);
                break;
            default:
        }

    }

    public void adjustText(String text, float textX, float textY, float maxWidth){

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        float textWidth = glyphLayout.width;

        float lineSpacingMultiplier = 1.55f; // Ajusta este valor según tus necesidades
        float lineHeight = glyphLayout.height * lineSpacingMultiplier;

        if (textWidth > maxWidth) {
            // El texto es demasiado ancho, dividir en líneas
            String[] lines = text.split(" ");
            StringBuilder currentLine = new StringBuilder();
            for (String word : lines) {
                glyphLayout.setText(font, currentLine + word);
                if (glyphLayout.width > maxWidth) {
                    // La línea actual excede el ancho máximo, dibújala y comienza una nueva línea
                    font.draw(batch, currentLine.toString(), textX, textY);
                    currentLine = new StringBuilder(word + " ");
                    textY -= lineHeight;
                } else {
                    // La línea actual sigue siendo válida, agrega la palabra a la línea
                    currentLine.append(word).append(" ");
                }
            }
            // Dibuja la última línea
            font.draw(batch, currentLine.toString(), textX, textY);
        }else {
            // El texto cabe en una sola línea
            font.draw(batch, text, textX, textY);
        }

    }

    private int randomQuestion() {
        if (questions.size == 0) {
            // No hay preguntas disponibles
            return -1;
        }

        int totalQuestions = questions.size;
        // Utiliza el índice seleccionado
        return questionManager.getRandomUnusedIndex(totalQuestions);
    }

    public String finalScore(){

        if (currentScreen == Screen.GAME_OVER){
            switch (language){
                case 0:
                    if (score > 1 || score == 0){
                        return "Has respost bé " + score + " preguntes de " + questionsAnswered + ".";
                    }else{
                        return "Has respost bé " + score + " pregunta de " + questionsAnswered + ".";
                    }

                case 1:
                    if (score > 1 || score == 0){
                        return "Has contestado bien " + score + " preguntas de " + questionsAnswered + ".";
                    }else{
                        return "Has contestado bien " + score + " pregunta de " + questionsAnswered + ".";
                    }
                case 2:
                    if (score > 1 || score == 0){
                        return "You answered " + score + " questions out of " + questionsAnswered + " correctly.";
                    }else{
                        return "You answered " + score + " question out of " + questionsAnswered + " correctly.";
                    }

                default:
            }
        }else if (currentScreen == Screen.WIN){
            switch (language){
                case 0:
                        return "T'han quedat " + lives + " rodas";
                case 1:
                        return "Te han quedado " + lives + " ruedas";
                case 2:
                        return "You had " +  lives + " wheels left";
                default:
            }
        }

        return null;
    }

    public void showLives(){
        float y = 0;
        for (int i = 0; i < lives; i++){
            batch.draw(assets.wheel,ScreenWidth - 64 - y - 16, ScreenHeight - 64 - 16, 64,64);
            y += 64;
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

    public void showOptions(String[] optionsAnswer) {
        float opcionesY = 800; // Ajusta la posición vertical de las opciones
        for (int i = 0; i < optionsAnswer.length; i++) {
            String opcion = optionsAnswer[i];
            String letra = obtenerLetra(i); // Obtener la letra correspondiente (A, B, C, D)

            // Concatenar la letra y la opción
            String opcionConLetra = letra + ": " + opcion;

            adjustText(opcionConLetra,12,opcionesY - i * 165,recCarArea.x - 8);

            // Dibujar la opción con la letra correspondiente
            //font.draw(batch, opcionConLetra, preguntaX, opcionesY - i * 62);
        }
    }

    private String obtenerLetra(int indice) {
        switch (indice) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            default:
                return ""; // Manejar casos adicionales si es necesario
        }
    }


    public void loadQuestionsJSON() {
        String filepath = "";
        switch (language){
            case 0:
                filepath = Gdx.files.getLocalStoragePath() + "json/questionsCAT.json";
                break;
            case 1:
                filepath = Gdx.files.getLocalStoragePath() + "json/questionsES.json";
                break;
            case 2:
                filepath = Gdx.files.getLocalStoragePath() + "json/questionsEN.json";
                break;
        }

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

    public void moverUserCar(){
        if (!checkButtonBounds.contains(touchX,touchY)){
            recUC.x = Math.max(recCarArea.x, Math.min(recCarArea.x + recCarArea.width - recUC.width, touchX - recUC.width / 2));
            recUC.y = Math.max(recCarArea.y, Math.min(recCarArea.y + recCarArea.height - recUC.height, touchY - recUC.width / 2));
        }
    }


    public void checkAnswer() {
        timeLeft = TIMEOUT_TIME;
        recCC1.y -= 25f;
        recCC2.y -= 25f;
        recCC3.y -= 25f;

        // Falló la pregunta
        if (recUC.overlaps(recCC1) || recUC.overlaps(recCC2) || recUC.overlaps(recCC3)) {
            stateTime = 0f;
            // Si hay colisión, reducir las vidas
            // Restablecer las posiciones de los coches y avanzar a la siguiente pregunta
            showExplosion = true;
            questionsAnswered += 1;
            lives--;
            checkIndexQuestion();
            if (lives <= 0) {
                showExplosion = false;
                currentScreen = Screen.GAME_OVER;
            }
            recCC1.setY(ScreenHeight);
            recCC2.setY(ScreenHeight);
            recCC3.setY(ScreenHeight);
            colision = false;

        }

        // Acertó la pregunta
        if (recCC1.y + recCC1.height < 0 && recCC2.y + recCC2.height < 0 && recCC3.y + recCC3.height < 0) {
            questionsAnswered += 1;
            score += 1;
            checkIndexQuestion();
            recCC1.setY(ScreenHeight);
            recCC2.setY(ScreenHeight);
            recCC3.setY(ScreenHeight);
            colision = false;

        }

    }

    public void checkIndexQuestion(){
        if (questionsAnswered < 20){
            indexQuestion = randomQuestion();
        }else if (questionsAnswered == 20) {
            showExplosion = false;
            currentScreen = Screen.WIN;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        assets.disposeAssets();
        shrend.dispose();
        startNewActivity();
    }
    private void startNewActivity() {
        Intent intent = new Intent(context, Activity4.class);
        context.startActivity(intent);
    }
}



