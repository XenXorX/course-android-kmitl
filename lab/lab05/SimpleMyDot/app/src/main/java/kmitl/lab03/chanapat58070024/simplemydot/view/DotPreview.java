package kmitl.lab03.chanapat58070024.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DotPreview extends View {
    private Paint paint;
    private int radius;
    private int colorR;
    private int colorG;
    private int colorB;
    private DotPreviewChangedListener listener;

    public interface DotPreviewChangedListener {
        void onDotChanged();
    }

    //Constructor
    public DotPreview(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotPreview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotPreview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    //Getter and Setter
    public void setListener(DotPreviewChangedListener listener) {
        this.listener = listener;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        listener.onDotChanged();
    }

    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
        listener.onDotChanged();
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
        listener.onDotChanged();
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
        listener.onDotChanged();
    }

    //Method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.rgb(colorR, colorG,colorB));
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, radius, paint);
    }
}
