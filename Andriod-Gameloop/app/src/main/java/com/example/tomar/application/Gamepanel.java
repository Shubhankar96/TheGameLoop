package com.example.tomar.application;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by tomar on 26-11-2015.
 */
public class Gamepanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    public Gamepanel(Context context)
    {
        super(context);
        //add the callback to the Surface Holder to Intercept Events
        getHolder().addCallback(this);

        thread= new MainThread(getHolder(),this);
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder , int format , int width , int height ){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) try {
            thread.setRunning(false);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            retry = false;
        }
    }

        @Override public void surfaceCreated(SurfaceHolder holder){
            // We CAN Safely Start the game loop
            thread.setRunning(true);
            thread.start();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            return super.onTouchEvent(event);
        }
    public void update() {

    }

}




