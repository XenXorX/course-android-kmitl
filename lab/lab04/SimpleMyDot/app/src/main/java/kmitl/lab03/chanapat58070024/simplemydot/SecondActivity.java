package kmitl.lab03.chanapat58070024.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab03.chanapat58070024.simplemydot.model.DotParcelable;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotSerializable;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnOpenActivity = (Button) findViewById(R.id.btnBack);
        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tvValueX = (TextView) findViewById(R.id.tvValueX);
        TextView tvDot = (TextView) findViewById(R.id.tvDot);

        int x = getIntent().getIntExtra("xValue", 0);
        DotSerializable dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");
        DotParcelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");

        tvValueX.setText(String.valueOf(x));
        tvDot.setText("centerX : " + dotParcelable.getCenterX()
                    + "\ncenterY : " + dotParcelable.getCenterY()
                    + "\nradius : " + dotParcelable.getRadius());
        tvDot.setTextColor(dotParcelable.getColor());
    }
}
