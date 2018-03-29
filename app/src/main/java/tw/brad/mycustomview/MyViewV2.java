package tw.brad.mycustomview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

// 開始自訂 View 的實作
public class MyViewV2 extends View {
    private Bitmap bmpBg1;
    private Resources resources;
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;

    public MyViewV2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        resources = context.getResources();

        //setBackgroundColor(Color.GREEN);
        setBackgroundResource(R.drawable.bg1);

        lines = new LinkedList<>();

    }

    // 開始實作 onDraw() 方法, 實現出自訂畫面內容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);

        for (LinkedList<HashMap<String,Float>> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"),
                        p1.get("x"), p1.get("y"), paint);
            }
        }

    }

    public void clearLines(){
        lines.clear();
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float px = event.getX(), py = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            LinkedList<HashMap<String,Float>> line =
                    new LinkedList<>();
            HashMap<String,Float> point =
                    new HashMap<>();
            point.put("x", px); point.put("y", py);
            line.add(point);
            lines.add(line);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            HashMap<String,Float> point =
                    new HashMap<>();
            point.put("x", px); point.put("y", py);
            lines.getLast().add(point);

        }


        invalidate();

        return  true;   // 持續偵測使用者觸擊事件
    }
}
