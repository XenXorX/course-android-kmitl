package kmitl.lab03.chanapat58070024.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;
import kmitl.lab03.chanapat58070024.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity {
    private DotView dotView;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);

        random = new Random();
    }

    public void onRandomDot(View view) {
        Dot dot = new Dot(50);
        dot.setCenterX(random.nextInt(dotView.getWidth()));
        dot.setCenterY(random.nextInt(dotView.getHeight()));
        dot.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

        dotView.addDotToList(dot);
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        dotView.clearDot();
        dotView.invalidate();
    }
}
