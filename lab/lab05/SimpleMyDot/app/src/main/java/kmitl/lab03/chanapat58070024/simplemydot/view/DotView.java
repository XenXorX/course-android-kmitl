package kmitl.lab03.chanapat58070024.simplemydot.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotList;

public class DotView extends View implements GestureDetector.OnGestureListener {
    private Paint paint;
    private DotList dotList;
    private DotViewListener listener;
    private GestureDetectorCompat gestureDetector;

    public interface DotViewListener {
        void onDotViewTouched(float x, float y);
        void onDotViewHeld(float x, float y);
    }

    private void initVariable() {
        paint = new Paint();
        gestureDetector = new GestureDetectorCompat(getContext(), this);
    }

    //Constructor
    public DotView(Context context) {
        super(context);
        initVariable();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVariable();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVariable();
    }

    //Getter and Setter
    public void setListener(DotViewListener listener) {
        this.listener = listener;
    }

    public void setDotList(DotList dotList) {
        this.dotList = dotList;
    }

    //Method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dotList != null) {
            for(Dot dot: dotList.getDotList()) {
                paint.setColor(Color.rgb(dot.getColorR(), dot.getColorG(), dot.getColorB()));
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        listener.onDotViewTouched(e.getX(), e.getY());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        listener.onDotViewHeld(e.getX(), e.getY());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }
}