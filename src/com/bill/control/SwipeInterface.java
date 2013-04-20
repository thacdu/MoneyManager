package com.bill.control;

import android.view.View;

public interface SwipeInterface {

    public void bottom2top(View view, int position);

    public void left2right(View view, int position);

    public void right2left(View view, int position);

    public void top2bottom(View view, int position);
    
    public void touch(View view, int position);

}
