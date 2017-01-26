package com.example.parabola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;

import static java.lang.String.valueOf;

/**
 * Created by Сергей on 24.01.2017.
 */

public class ParabolaCanvas extends View {
    public int a=1;
    public int b=-2;
    public int c=1;
    Paint mPaint=new Paint();
    public ParabolaCanvas(Context context) {
        super(context);
    }
    @Override
    protected  void onDraw(Canvas canvas){
        mPaint.setColor(Color.BLACK);
          canvas.drawLine(0,400,800,400,mPaint);
        float pointed=800-((a*(-40)*(-40)+b*(-40)+c)*10+400);
        canvas.drawLine(400,0,400,800,mPaint);
        for(int i=1;i<=8000;i++){
            float coor=((i/10)-400)/10;

            mPaint.setColor(Color.RED);

            canvas.drawLine((float)((i/10)-0.1),pointed,(i/10),800-((a*coor*coor+b*coor+c)*10+400),mPaint);
            pointed=800-((a*coor*coor+b*coor+c)*10+400);
        }


    }
    @Override
    protected void onMeasure(int w,int h){
        setMeasuredDimension(800,800);
    }
}
