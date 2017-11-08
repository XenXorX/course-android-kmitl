package kmitl.lab09.chanapat58070024.moneyflow;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab09.chanapat58070024.moneyflow.fragment.LedgerFragment;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItemList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LedgerItemList ledgerItemList = new LedgerItemList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, new LedgerFragment().newInstance(ledgerItemList));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
