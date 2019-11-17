package io.github.team_sippo.lottie_sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

public class LottieSwitchViewWrapper {
    private LottieAnimationView animationView;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private boolean mChecked = false;
    private Integer switchOnStartFrame;
    private Integer switchOnEndFrame;
    private Integer switchOffStartFrame;
    private Integer switchOffEndFrame;
    private Integer animationDuration;
    private Float switchOnStartProceed;
    private Float switchOnEndProceed;
    private Float switchOffStartProceed;
    private Float switchOffEndProceed;
    private boolean force = false;

    @SuppressLint("ClickableViewAccessibility")
    public LottieSwitchViewWrapper(LottieAnimationView animationView) {
        this.animationView = animationView;
        this.animationView.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                onTouched();
            }
            return true;
        });
    }

    private void init(boolean force){
        if (force || animationDuration == null){
            this.animationDuration = Double.valueOf(String.valueOf(animationView.getMaxFrame())).intValue();
            Log.d("AAA", "duration is " + String.valueOf(this.animationDuration));
            if(this.switchOnStartFrame == null){
                if (switchOnStartProceed != null){
                    this.switchOnStartFrame = (int)((this.animationDuration - 1) * switchOnStartProceed);
                } else {
                    this.switchOnStartFrame = 0;
                }
            }
            Log.d("AAA", "switchOnStartFrame is " + String.valueOf(this.switchOnStartFrame));
            if(switchOnEndFrame == null){
                if(switchOnEndProceed != null){
                    this.switchOnEndFrame = (int)((this.animationDuration - 1) * switchOnEndProceed);
                } else {
                    this.switchOnEndFrame = this.animationDuration - 1;
                }
            }
            Log.d("AAA", "switchOnEndFrame is " + String.valueOf(this.switchOnEndFrame));
            if (switchOffStartFrame == null){
                if(switchOffStartProceed != null){
                    this.switchOffStartFrame = (int)((this.animationDuration - 1) * switchOffStartProceed);
                } else {
                    this.switchOffStartFrame = 0;
                }
            }
            Log.d("AAA", "switchOffStartFrame is " + String.valueOf(this.switchOffStartFrame));
            if(this.switchOffEndFrame == null){
                if(switchOffEndProceed != null){
                    this.switchOffEndFrame = (int)((this.animationDuration - 1) * switchOffEndProceed);
                } else {
                    this.switchOffEndFrame = 0;
                }
            }
            Log.d("AAA", "switchOffEndFrame is " + String.valueOf(this.switchOffEndFrame));
        }
    }


    /**
     * anni
     */
    private void onTouched(){
        init(force);
        if(mChecked){
            mChecked = false;
            if (mOnCheckedChangeListener != null){
                mOnCheckedChangeListener.onCheckedChanged(this, false);
                Log.d("AAA", "switch OFF animation");
                startSwitchOffAnimation();
            }
        } else {
            mChecked = true;
            if (mOnCheckedChangeListener != null){
                mOnCheckedChangeListener.onCheckedChanged(this, true);
                Log.d("AAA", "switch ON animation");
                startSwitchOnAnimation();
            }
        }
    }

    private void allFrameInfoToNull(){
        this.switchOnStartFrame = null;
        this.switchOnEndFrame = null;
        this.switchOffStartFrame = null;
        this.switchOffEndFrame = null;
        this.switchOnStartProceed = null;
        this.switchOnEndProceed = null;
        this.switchOffStartProceed = null;
        this.switchOffEndProceed = null;
    }

    public void setSwitchOnProceed(Float start, Float end){
        allFrameInfoToNull();
        this.switchOnStartProceed = start;
        this.switchOnEndProceed = end;
        this.force = true;
    }

    public void setSwitchOffProceed(Float start, Float end){
        allFrameInfoToNull();
        this.switchOffStartProceed = start;
        this.switchOffEndProceed = end;
        this.force = true;
    }


    public void setSwitchOnFrame(Integer startFrame, Integer endFrame){
        allFrameInfoToNull();
        this.switchOnStartFrame = startFrame;
        this.switchOnEndFrame = endFrame;
        this.force = true;
    }

    public void setSwitchOffFrame(Integer startFrame, Integer endFrame){
        allFrameInfoToNull();
        this.switchOffStartFrame = startFrame;
        this.switchOffEndFrame = endFrame;
        this.force = true;
    }

    private void startSwitchOnAnimation(){
        this.animationView.setMinFrame(switchOnStartFrame);
        this.animationView.setMaxFrame(switchOnEndFrame);
        this.animationView.setSpeed(2);
        this.animationView.playAnimation();
    }

    private void startSwitchOffAnimation(){
        this.animationView.setMinFrame(switchOffStartFrame);
        this.animationView.setMaxFrame(switchOffEndFrame);
        this.animationView.setSpeed(2);
        this.animationView.playAnimation();
    }


    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(@Nullable LottieSwitchViewWrapper.OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changed.
     */
    public static interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(LottieSwitchViewWrapper buttonView, boolean isChecked);
    }

}
