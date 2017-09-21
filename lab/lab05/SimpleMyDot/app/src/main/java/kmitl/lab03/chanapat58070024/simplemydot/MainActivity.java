package kmitl.lab03.chanapat58070024.simplemydot;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab03.chanapat58070024.simplemydot.fragment.MainFragment;
import kmitl.lab03.chanapat58070024.simplemydot.model.DotList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DotList dotList = new DotList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, new MainFragment().newInstance(dotList));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
