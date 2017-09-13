package kmitl.lab03.chanapat58070024.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotList;

public class DotView extends View {
    private Paint paint;
    private DotList dotList;
    private DotViewTouchedListener listener;

    public interface DotViewTouchedListener {
        void onDotViewTouched(float x, float y);
    }

    //Constructor
    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    //Getter and Setter
    public void setListener(DotViewTouchedListener listener) {
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
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.listener.onDotViewTouched(event.getX(), event.getY());
                return true;
        }
        return false;
    }
}
