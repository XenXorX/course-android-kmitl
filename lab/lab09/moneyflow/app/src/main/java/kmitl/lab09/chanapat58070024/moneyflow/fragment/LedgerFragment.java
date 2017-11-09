package kmitl.lab09.chanapat58070024.moneyflow.fragment;


import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab09.chanapat58070024.moneyflow.R;
import kmitl.lab09.chanapat58070024.moneyflow.adapter.LedgerAdapter;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItem;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItemDatabase;

public class LedgerFragment extends Fragment {
    private LedgerItemDatabase ledgerItemDB;
    private LedgerAdapter adapter;
    private TextView balanceText;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ledgerItemDB = Room.databaseBuilder(getActivity().getApplicationContext(),
                LedgerItemDatabase.class, "DEMOINFO").build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ledger, container, false);

        balanceText = rootView.findViewById(R.id.text_balance);
        adapter = new LedgerAdapter(getActivity().getApplicationContext());

        listView = rootView.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new EditItemFragment().newInstance(adapter.getLedgerItems().get(i)));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        new AsyncTask<Void, Void, List<LedgerItem>>(){

            @Override
            protected List<LedgerItem> doInBackground(Void... voids) {
                List<LedgerItem> result = ledgerItemDB.ledgerItemRoomDAO().getAll();

                return result;
            }

            @Override
            protected void onPostExecute(List<LedgerItem> ledgerItems) {
                ArrayList<LedgerItem> ledgerItemList = new ArrayList<>();

                for(LedgerItem ledgerItem: ledgerItems) {
                    ledgerItemList.add(ledgerItem);
                }

                adapter.setLedgerItems(ledgerItemList);
                listView.setAdapter(adapter);
                balanceText.setText(String.valueOf(calLedger(ledgerItemList)));
            }
        }.execute();


        Button addBtn = rootView.findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new AddItemFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

    public int calLedger(List<LedgerItem> ledgerItemList) {
        int balance = 0;
        int income = 0;

        for(LedgerItem ledgerItem: ledgerItemList) {
            char symbol = ledgerItem.getSymbol();

            if(symbol == '+') {
                balance += ledgerItem.getAmount();
                income += ledgerItem.getAmount();
            } else if(symbol == '-') {
                balance -= ledgerItem.getAmount();
            }
        }

        if(balance > 0.5*income) {
            balanceText.setTextColor(Color.GREEN);
        } else if (balance > 0.25*income) {
            balanceText.setTextColor(Color.YELLOW);
        } else {
            balanceText.setTextColor(Color.RED);
        }

        return balance;
    }
}
