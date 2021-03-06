
package com.example.luisca.space_invaders;


import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = MediaPlayer.create(this, R.raw.sound);
        setContentView(R.layout.activity_game); // Establece el xml con la vista
        h3.postDelayed(run2, 0);

        }
    public void reproducirSonido(){
        mp.start();
    }
    Handler h2 = new Handler();
    Handler h3 = new Handler();


      Runnable run2 = new Runnable() {

        @Override
        public void run() {
            ImageView bullet = (ImageView) findViewById(R.id.bullet);
            ImageButton ship = (ImageButton) findViewById(R.id.ship);
            ImageView invader1 = (ImageView) findViewById(R.id.invader1);

            bullet.setVisibility(View.VISIBLE);
            invader1.setVisibility(View.VISIBLE);
            ship.setEnabled(false);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            bullet.setY(bullet.getY()-60); //Mueve el disparo hacia arriba
            if((bullet.getY()+bullet.getHeight() <10 )) //Si el disparo sale de la pantalla desaparece
            {
                reproducirSonido(); //El sonido se reproduce cuando el disparo desaparece.
                bullet.setVisibility(View.INVISIBLE);
                h3.removeCallbacks(run2);
                bullet.setX(ship.getX() + ship.getWidth() / 2 - bullet.getWidth() / 2); //Ajustar centro de bala a centro de nave
                bullet.setY(ship.getY() - ship.getHeight()); //Ajustar alto de la bala a final de la nave
            }

            h3.postDelayed(this, 50);
        }
    };


    public void shot(View v){ // Este metodo se ejecuta al pulsar sobre la nave
        ImageView bullet = (ImageView) findViewById(R.id.bullet);
        ImageButton ship = (ImageButton) findViewById(R.id.ship);
        bullet.setX(ship.getX() + ship.getWidth() / 2 - bullet.getWidth() / 2); //Ajustar centro de bala a centro de nave
        bullet.setY(ship.getY() - ship.getHeight()); //Ajustar alto de la bala a final de la nave
        bullet.setVisibility(View.VISIBLE);
        ship.setEnabled(false);
        h3.postDelayed(run2, 0);
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) { // Este metodo está pendiente de cuando pulsas la pantalla para mover la nave
        float currX;
        ImageButton ship = (ImageButton) findViewById(R.id.ship);
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int width = metrics.widthPixels; // ancho absoluto en pixels
                int height = metrics.heightPixels; // alto absoluto en pixels

                Float x = event.getX();
                ship.setX(x);

            case MotionEvent.ACTION_MOVE:
                currX = event.getRawX();

                ship.setX(currX-ship.getWidth()/2);


            default:
                return super.onTouchEvent(event);
        }
    }





}
