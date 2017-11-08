package kmitl.lab09.chanapat58070024.moneyflow.fragment;


import android.graphics.Color;
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

import kmitl.lab09.chanapat58070024.moneyflow.R;
import kmitl.lab09.chanapat58070024.moneyflow.adapter.LedgerAdapter;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItem;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItemList;

public class LedgerFragment extends Fragment implements LedgerItemList.LedgerItemListChangedListener {
    private LedgerItemList ledgerItemList;
    private TextView balanceText;

    public LedgerFragment() {}

    public static LedgerFragment newInstance(LedgerItemList ledgerItemList) {
        LedgerFragment fragment = new LedgerFragment();
        Bundle args = new Bundle();
        args.putParcelable("ledgerItems", ledgerItemList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ledgerItemList = getArguments().getParcelable("ledgerItems");
            ledgerItemList.setListener(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ledger, container, false);

        LedgerAdapter adapter = new LedgerAdapter(getActivity().getApplicationContext());
        adapter.setLedgerItems(ledgerItemList.getLedgerItems());

        balanceText = rootView.findViewById(R.id.text_balance);
        balanceText.setText(String.valueOf(ledgerItemList.getBalance()));

        int balance = ledgerItemList.getBalance();
        int income = ledgerItemList.getIncome();
        if(balance > 0.5*income) {
            balanceText.setTextColor(Color.GREEN);
        } else if (balance > 0.25*income) {
            balanceText.setTextColor(Color.YELLOW);
        } else {
            balanceText.setTextColor(Color.RED);
        }

        Button addBtn = rootView.findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new AddItemFragment().newInstance(ledgerItemList));
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
                transaction.replace(R.id.fragmentContainer, new EditItemFragment().newInstance(ledgerItemList, i));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

    @Override
    public void onLedgerItemListChanged(LedgerItemList ledgerItemList) {
        int balance = 0;
        int income = 0;

        for(LedgerItem ledgerItem: ledgerItemList.getLedgerItems()) {
            char symbol = ledgerItem.getSymbol();

            if(symbol == '+') {
                balance += ledgerItem.getAmount();
                income += ledgerItem.getAmount();
            } else if(symbol == '-') {
                balance -= ledgerItem.getAmount();
            }
        }

        ledgerItemList.setBalance(balance);
        ledgerItemList.setIncome(income);
    }
}
