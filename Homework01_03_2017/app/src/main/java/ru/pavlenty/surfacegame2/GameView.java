package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Acer on 27.01.2017.
 */

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Enemy enemies;

    //created a reference of the class Friend
    private Friend friend;

    private DatabaseHelper db;

    private ArrayList<Star> stars = new ArrayList<Star>();

    //defining a boom object to display blast
    private Boom boom;

    //a screenX holder
    int screenX;

    //to count the number of Misses
    int countMisses;

    //indicator that the enemy has just entered the game screen
    boolean flag ;

    //an indicator if the game is Over
    private boolean isGameOver;

    //the score holder
    int score;

    //the high Scores Holder
    int highScore[] = new int[4];

    //Shared Prefernces to store the High Scores


    static MediaPlayer gameOnsound;
    final MediaPlayer killedEnemysound;
    final MediaPlayer gameOversound;

    Context context;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY);

        surfaceHolder = getHolder();
        paint = new Paint();

        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }

        //single enemy initialization
        enemies = new Enemy(context, screenX, screenY);

        //initializing boom object
        boom = new Boom(context);

        //initializing the Friend class object
        friend = new Friend(context, screenX, screenY);

        this.screenX = screenX;
        countMisses = 0;
        isGameOver = false;

//setting the score to 0 initially
        score = 0;
        db=new DatabaseHelper(context);
        Cursor c=db.getScore();
        c.moveToFirst();
        for(int t=0;t <highScore.length;t++){
            if (!(c.isAfterLast())){
                highScore[t] =Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_ADD)));c.moveToNext(); }else{
                highScore[t]=0;
            }
        }


        this.context = context;

//initializing the media players for the game sounds
        gameOnsound = MediaPlayer.create(context,R.raw.gameon);
        killedEnemysound = MediaPlayer.create(context,R.raw.killedenemy);
        gameOversound = MediaPlayer.create(context,R.raw.gameover);

//starting the game music as the game starts
        gameOnsound.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;

        }
        //if the game's over, tappin on game Over screen sends you to MainActivity
        if(isGameOver){
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity(new Intent(context,MainActivity.class));
            }
        }
        return true;
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);


            paint.setColor(Color.WHITE);
            paint.setTextSize(20);

            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            //drawing the score on the game screen
            paint.setTextSize(30);
            canvas.drawText("Score:"+score,100,50,paint);

            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);


            canvas.drawBitmap(
                    enemies.getBitmap(),
                    enemies.getX(),
                    enemies.getY(),
                    paint
            );


            //drawing boom image
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );


            //drawing friends image
            canvas.drawBitmap(

                    friend.getBitmap(),
                    friend.getX(),
                    friend.getY(),
                    paint
            );

            //draw game Over when the game is over
            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over",canvas.getWidth()/2,yPos,paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    //stop the music on exit
    public static void stopMusic(){
        gameOnsound.stop();
    }

    private void update() {
        score++;

        player.update();

        //setting boom outside the screen
        boom.setX(-250);
        boom.setY(-250);

        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        //setting the flag true when the enemy just enters the screen
        if(enemies.getX()==screenX){
            flag = true;
        }

        enemies.update(player.getSpeed());
        //if collision occurs with player
        if (Rect.intersects(player.getDetectCollision(), enemies.getDetectCollision())) {
            //displaying boom at that location
            boom.setX(enemies.getX());
            boom.setY(enemies.getY());

            //playing a sound at the collision between player and the enemy
            killedEnemysound.start();
            enemies.setX(-100);
        }

        else{// the condition where player misses the enemy
            //if the enemy has just entered
            if(flag){
                //if player's x coordinate is equal to enemies's y coordinate
                if(player.getDetectCollision().exactCenterX()>=enemies.getDetectCollision().exactCenterX()){

                    //increment countMisses
                    countMisses++;

                    //setting the flag false so that the else part is executed only when new enemy enters the screen
                    flag = false;

                    //if no of Misses is equal to 3, then game is over.
                    if(countMisses==3){

                        //setting playing false to stop the game.
                        playing = false;
                        isGameOver = true;


                        //stopping the gameon music
                        gameOnsound.stop();
                        //play the game over sound
                        gameOversound.start();

                        //Assigning the scores to the highscore integer array
                        for(int i=0;i<4;i++){
                            if(highScore[i]<score){

                                final int finalI = i;
                                highScore[i] = score;
                                break;
                            }
                        }

                        //storing the scores through shared Preferences

                           db.addScore(highScore);



                    }

                }
            }

        }

        //updating the friend ships coordinates
        friend.update(player.getSpeed());
        //checking for a collision between player and a friend
        if(Rect.intersects(player.getDetectCollision(),friend.getDetectCollision())){

            //displaying the boom at the collision
            boom.setX(friend.getX());
            boom.setY(friend.getY());
            //setting playing false to stop the game
            playing = false;
            //setting the isGameOver true as the game is over
            isGameOver = true;

            //stopping the gameon music
            gameOnsound.stop();
            //play the game over sound
            gameOversound.start();

            //Assigning the scores to the highscore integer array
            for(int i=0;i<4;i++){

                if(highScore[i]<score){

                    final int finalI = i;
                    highScore[i] = score;
                    break;
                }
            }
            //storing the scores through shared Preferences


           db.addScore(highScore);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


}