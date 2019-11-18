package io.github.team_sippo.lottie_sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;

public class LottieSwitchView extends LottieAnimationView{
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


    public LottieSwitchView(Context context) {
        super(context);
        init();
    }

    public LottieSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LottieSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setChecked(boolean isChecked){
        mChecked = isChecked;
    }


    @Override
    public void setComposition(@NonNull LottieComposition composition) {
        super.setComposition(composition);
        init_param(force);
        setSwitchStart(mChecked);
        Log.d("AAA", "onMeasureEnd max frame :" + String.valueOf(getMaxFrame()));
    }

    private void setSwitchStart(boolean isChecked){
        if(isChecked){
            setMinAndMaxFrame(switchOffStartFrame, switchOffStartFrame+1);
        } else {
            setMinAndMaxFrame(switchOnStartFrame, switchOnStartFrame+1);
        }
        playAnimation();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(){
        this.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                onTouched();
            }
            return true;
        });

    }
    private void init_param(boolean force){
        if(animationDuration == null){
            this.animationDuration = Double.valueOf(String.valueOf(this.getMaxFrame())).intValue();
        }
        if (force){
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
            Log.d("AAA", "switchOnEndProceed is " + String.valueOf(this.switchOnEndProceed));
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
        init_param(force);
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

    private void allFrameInfoToNull(boolean switchOn){
        if (switchOn){
            this.switchOnStartFrame = null;
            this.switchOnEndFrame = null;
            this.switchOnStartProceed = null;
            this.switchOnEndProceed = null;
        } else {
            this.switchOffStartFrame = null;
            this.switchOffEndFrame = null;
            this.switchOffStartProceed = null;
            this.switchOffEndProceed = null;
        }
    }

    public void setSwitchOnProceed(Float start, Float end){
        allFrameInfoToNull(true);
        this.switchOnStartProceed = start;
        this.switchOnEndProceed = end;
        this.force = true;
    }

    public void setSwitchOffProceed(Float start, Float end){
        allFrameInfoToNull(false);
        this.switchOffStartProceed = start;
        this.switchOffEndProceed = end;
        this.force = true;
    }


    public void setSwitchOnFrame(Integer startFrame, Integer endFrame){
        allFrameInfoToNull(true);
        this.switchOnStartFrame = startFrame;
        this.switchOnEndFrame = endFrame;
        this.force = true;
    }

    public void setSwitchOffFrame(Integer startFrame, Integer endFrame){
        allFrameInfoToNull(false);
        this.switchOffStartFrame = startFrame;
        this.switchOffEndFrame = endFrame;
        this.force = true;
    }

    private void startSwitchOnAnimation(){
        this.setMinFrame(switchOnStartFrame);
        this.setMaxFrame(switchOnEndFrame);
        this.setSpeed(2);
        this.playAnimation();
    }

    private void startSwitchOffAnimation(){
        this.setMinFrame(switchOffStartFrame);
        this.setMaxFrame(switchOffEndFrame);
        this.playAnimation();
        this.setSpeed(2);
    }


    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(@Nullable LottieSwitchView.OnCheckedChangeListener listener) {
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
         * @param switchView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(LottieSwitchView switchView, boolean isChecked);
    }

}
