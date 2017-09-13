package kmitl.lab03.chanapat58070024.simplemydot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import kmitl.lab03.chanapat58070024.simplemydot.model.Dot;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotList;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotParcelable;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotSerializable;
import kmitl.lab03.chanapat58070024.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements DotList.DotListChangedListener, DotView.DotViewTouchedListener {
    private DotList dotList;
    private DotView dotView;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);

        final DotSerializable dotSerializable = new DotSerializable(150, 150, 50, Color.RED);
        final DotParcelable dotParcelable = new DotParcelable(130, 130, 30, Color.BLUE);

        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("xValue", 30);
                intent.putExtra("dotSerializable", dotSerializable);
                intent.putExtra("dotParcelable", dotParcelable);

                startActivity(intent);
            }
        });

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setListener(this);

        dotList = new DotList();
        dotList.setListener(this);
        random = new Random();
    }

    public void onRandomDot(View view) {
        Dot dot = new Dot(50);
        dot.setCenterX(random.nextInt(dotView.getWidth()));
        dot.setCenterY(random.nextInt(dotView.getHeight()));
        dot.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

        dotList.addDot(dot);
    }

    public void onClearDot(View view) {
        dotList.clearDot();
    }

    public void onUndo(View view) {
        dotList.undoDot();
    }

    public void onShare(View view) {
        if(requestWriteExternalStoragePermission()) {
            View screen = view.getRootView();
            screen.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(screen.getDrawingCache());
            screen.setDrawingCacheEnabled(false);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(this.getApplicationContext().getContentResolver(), bitmap, "Title", null);

            Uri uriToImage = Uri.parse(path);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            startActivity(Intent.createChooser(intent, getResources().getText(R.string.share)));
        }
    }

    public boolean requestWriteExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            return false;
        } else {
            return true;
        }
    }

    public boolean isPointerInCircle(float pointX, float pointY, Dot dot) {
        return dot.getRadius() >= Math.sqrt(Math.pow(dot.getCenterX() - pointX, 2)
                + Math.pow(dot.getCenterY() - pointY, 2));
    }

    @Override
    public void onDotListChanged(DotList dotList) {
        dotView.setDotList(dotList);
        dotView.invalidate();
    }

    @Override
    public void onDotViewTouched(float x, float y) {
        boolean isRemoved = false;

        LinkedList<Dot> dots = dotList.getDotList();
        Collections.reverse(dots);
        for(Dot dot: dots) {
            if(isPointerInCircle(x, y, dot)) {
                dotList.removeDot(dot);
                isRemoved = true;
                break;
            }
        }
        Collections.reverse(dots);

        if(!isRemoved) {
            Dot dot = new Dot(50);
            dot.setCenterX(x);
            dot.setCenterY(y);
            dot.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

            dotList.addDot(dot);
        }
    }
}
