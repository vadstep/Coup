package com.example.vadym.coupbatterystatusapp.UI;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vadym.coupbatterystatusapp.Models.Scooters;
import com.example.vadym.coupbatterystatusapp.NetworkController.ResultListener;
import com.example.vadym.coupbatterystatusapp.NetworkController.VolleyController;
import com.example.vadym.coupbatterystatusapp.R;
import com.google.gson.Gson;

/**
 * Created by vadym on 7/18/17.
 */

public class SplashScreen extends AppCompatActivity {
    private  final int DONE_REQUEST = 200;
    private  final int ERROR_REQUEST = 400;
    private Handler mResponseHandler;
    boolean isScootersUploaded;
    boolean isAreasUploaded;
    boolean isRequestsFinished;
    private LottieAnimationView animationView;
    private Animator.AnimatorListener animatorListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash_screen);
        setAnimation();
        initHandler();
        getData();
    }
    /**
     * set Animation for splash screen
     */
    private void setAnimation() {
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("bike_loader.json");
        animatorListener =new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startMapScreen();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if(isRequestsFinished){
                    animationView.loop(false);
                }
            }
        };
        animationView.addAnimatorListener( animatorListener);
        animationView.loop(true);
        animationView.playAnimation();
    }
    /**
     * getting data from server
     */
    private void getData() {
        VolleyController.getInstance(this).getBikes(new ResultListener(){

            @Override
            public void onSuccess(Object result) {
                isScootersUploaded = true;
                if (mResponseHandler != null)
                    mResponseHandler.sendEmptyMessage(DONE_REQUEST);
            }

            @Override
            public void onError(Throwable throwable) {
                isScootersUploaded = false;
                if (mResponseHandler != null)
                    mResponseHandler.sendEmptyMessage(ERROR_REQUEST);
            }
        });
        VolleyController.getInstance(this).getAreas(new ResultListener(){

            @Override
            public void onSuccess(Object result) {
                isAreasUploaded = true;
                if (mResponseHandler != null)
                    mResponseHandler.sendEmptyMessage(DONE_REQUEST);
            }

            @Override
            public void onError(Throwable throwable) {
                isAreasUploaded = false;
                if (mResponseHandler != null)
                    mResponseHandler.sendEmptyMessage(ERROR_REQUEST);
            }
        });
    }

    /**
     * init of request status handler
     */
    private void initHandler() {
        mResponseHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case ERROR_REQUEST:
                        notifyDataUploadFinish(false);
                        break;
                    case DONE_REQUEST:
                        if (isAreasUploaded && isScootersUploaded) {
                            notifyDataUploadFinish(true);
                        }
                        break;
                }
            }
        };
    }
    private void notifyDataUploadFinish(boolean uploadResult) {
        if (!uploadResult) {
            Toast.makeText(this, "Error loading data occurred", Toast.LENGTH_SHORT).show();//TODO put text to strings.xml
        }
        isRequestsFinished=true;
    }
    private void startMapScreen(){
        this.startActivity(new Intent(SplashScreen.this,MapsActivity.class));
        this.finish();
    }
    @Override
    protected void onDestroy() {
        if(animationView!=null){
            animationView.cancelAnimation();
            animationView.removeAnimatorListener(animatorListener);
        }
        super.onDestroy();
    }
}

