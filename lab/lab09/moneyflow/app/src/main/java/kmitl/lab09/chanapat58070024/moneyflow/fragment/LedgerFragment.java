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
    private ArrayList<LedgerItem> ledgerItemList;
    private TextView balanceText;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ledgerItemDB = Room.databaseBuilder(getActivity().getApplicationContext(),
                LedgerItemDatabase.class, "DEMOINFO").build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ledger, container, false);

        ledgerItemList = new ArrayList<>();

        new AsyncTask<Void, Void, List<LedgerItem>>(){

            @Override
            protected List<LedgerItem> doInBackground(Void... voids) {
                List<LedgerItem> result = ledgerItemDB.ledgerItemRoomDAO().getAll();

                return result;
            }

            @Override
            protected void onPostExecute(List<LedgerItem> ledgerItems) {
                for(LedgerItem ledgerItem: ledgerItems) {
                    ledgerItemList.add(ledgerItem);
                }
                LedgerAdapter adapter = new LedgerAdapter(getActivity().getApplicationContext());
                adapter.setLedgerItems(ledgerItemList);

                balanceText = rootView.findViewById(R.id.text_balance);
                balanceText.setText(String.valueOf(calLedger(ledgerItemList)));

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

                ListView list = rootView.findViewById(R.id.list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, new EditItemFragment().newInstance(ledgerItemList.get(i)));
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        }.execute();

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
