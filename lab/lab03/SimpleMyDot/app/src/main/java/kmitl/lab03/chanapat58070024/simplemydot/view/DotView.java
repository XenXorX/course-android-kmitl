package kmitl.lab03.chanapat58070024.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;

public class DotView extends View {
    private Paint paint;
    private LinkedList<Dot> dotList;
    private Random random;

    private void initVariable() {
        paint = new Paint();
        dotList = new LinkedList<>();
        random = new Random();
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

    //Method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Dot dot: dotList) {
            paint.setColor(dot.getColor());
            canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        boolean isRemoved = false;
        Collections.reverse(dotList);

        for(Dot dot: dotList) {
            if(isPointerInCircle(pointX, pointY, dot)) {
                dotList.remove(dot);
                isRemoved = true;
                break;
            }
        }
        Collections.reverse(dotList);

        if(!isRemoved) {
            Dot dot = new Dot(50);
            dot.setCenterX(pointX);
            dot.setCenterY(pointY);
            dot.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

            dotList.add(dot);
        }

        invalidate();
        return super.onTouchEvent(event);
    }

    public void addDotToList(Dot dot) {
        dotList.add(dot);
    }

    public void clearDot() {
        dotList.clear();
    }

    public boolean isPointerInCircle(float pointX, float pointY, Dot dot) {
        return dot.getRadius() >= Math.sqrt(Math.pow(dot.getCenterX() - pointX, 2)
                + Math.pow(dot.getCenterY() - pointY, 2));
    }
}
