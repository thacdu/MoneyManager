package com.bill.control;

import android.view.MotionEvent;
import android.view.View;

public class ActivitySwipeDetector implements View.OnTouchListener {

	static final String logTag = "ActivitySwipeDetector";
	private SwipeInterface activity;
	static final int MIN_DISTANCE = 10;
	private float downX, downY, upX, upY;
	
	public ActivitySwipeDetector(SwipeInterface activity){
	    this.activity = activity;
	}
	
	public void onRightToLeftSwipe(View view){
	    //Toast.makeText(activity, "RightToLeftSwipe!", Toast.LENGTH_SHORT).show();
		activity.right2left(view);
	}
	
	public void onLeftToRightSwipe(View view){
		//Toast.makeText(activity, "LeftToRightSwipe!", Toast.LENGTH_SHORT).show();
		activity.left2right(view);
	}
	
	public void onTopToBottomSwipe(View view){
		//Toast.makeText(activity, "onTopToBottomSwipe!", Toast.LENGTH_SHORT).show();
		activity.top2bottom(view);
	}
	
	public void onBottomToTopSwipe(View view){
		//Toast.makeText(activity, "onBottomToTopSwipe!", Toast.LENGTH_SHORT).show();
		activity.bottom2top(view);
	}
	
	
	public boolean onTouch(View view, MotionEvent event) {
	    switch(event.getAction()){
	        case MotionEvent.ACTION_DOWN: {
	            downX = event.getX();
	            downY = event.getY();
	            return true;
	        }
	        case MotionEvent.ACTION_UP: {
	            upX = event.getX();
	            upY = event.getY();
	
	            float deltaX = downX - upX;
	            float deltaY = downY - upY;
	
	            // swipe horizontal?
	            if(Math.abs(deltaX) > MIN_DISTANCE){
	                // left or right
	                if(deltaX < 0) { this.onLeftToRightSwipe(view); return true; }
	                if(deltaX > 0) { this.onRightToLeftSwipe(view); return true; }
	            }
	            else {
	            	return false; // We don't consume the event
	            }
	
	            // swipe vertical?
	            if(Math.abs(deltaY) > MIN_DISTANCE){
	                // top or down
	                if(deltaY < 0) { this.onTopToBottomSwipe(view); return true; }
	                if(deltaY > 0) { this.onBottomToTopSwipe(view); return true; }
	            }
	            else {
	            	return false; // We don't consume the event
	            }
	
	            return true;
	        }
	    }
	    return false;
	}

}