package kmitl.lab09.chanapat58070024.moneyflow.fragment;


import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kmitl.lab09.chanapat58070024.moneyflow.R;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItem;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItemDatabase;

public class EditItemFragment extends Fragment {
    private final int incomeBtnColor = Color.rgb(120, 255, 120);
    private final int expenseBtnColor = Color.rgb(255, 120, 120);
    private final int hidBtnColor = Color.rgb(255, 255, 255);

    private LedgerItemDatabase ledgerItemDB;
    private LedgerItem ledgerItem;
    private Button incomeBtn;
    private Button expenseBtn;
    private EditText descriptionEt;
    private EditText amountEt;
    private char symbol;

    public EditItemFragment() {}

    public static EditItemFragment newInstance(LedgerItem ledgerItem) {
        EditItemFragment fragment = new EditItemFragment();
        Bundle args = new Bundle();
        args.putParcelable("ledgerItem", ledgerItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ledgerItemDB = Room.databaseBuilder(getActivity().getApplicationContext(),
                LedgerItemDatabase.class, "DEMOINFO").build();
        if (getArguments() != null) {
            ledgerItem = getArguments().getParcelable("ledgerItem");
            symbol = ledgerItem.getSymbol();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_item, container, false);

        descriptionEt = rootView.findViewById(R.id.et_description);
        descriptionEt.setText(ledgerItem.getDescription());
        amountEt = rootView.findViewById(R.id.et_amount);
        amountEt.setText(String.valueOf(ledgerItem.getAmount()));
        amountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = String.valueOf(editable);
                if(text.equals("")) {
                    amountEt.setText("0");
                    text = "0";
                }

                int width = Integer.parseInt(text);
                if(width < 0) {
                    amountEt.setText("0");
                }
            }
        });

        Button applyBtn = rootView.findViewById(R.id.btn_apply);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, LedgerItem>(){

                    @Override
                    protected LedgerItem doInBackground(Void... voids) {
                        ledgerItem.setSymbol(symbol);
                        ledgerItem.setDescription(String.valueOf(descriptionEt.getText()));
                        ledgerItem.setAmount(Integer.parseInt(String.valueOf(amountEt.getText())));

                        ledgerItemDB.ledgerItemRoomDAO().update(ledgerItem);

                        return null;
                    }
                }.execute();

                getFragmentManager().popBackStack();
            }
        });

        Button deleteBtn = rootView.findViewById(R.id.btn_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, LedgerItem>(){

                    @Override
                    protected LedgerItem doInBackground(Void... voids) {
                        ledgerItemDB.ledgerItemRoomDAO().delete(ledgerItem);

                        return null;
                    }
                }.execute();

                getFragmentManager().popBackStack();
            }
        });

        incomeBtn = rootView.findViewById(R.id.btn_income);
        incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = '+';
                onTypeChanged(symbol);
            }
        });

        expenseBtn = rootView.findViewById(R.id.btn_expense);
        expenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = '-';
                onTypeChanged(symbol);
            }
        });

        onTypeChanged(symbol);

        return rootView;
    }

    private void onTypeChanged(char symbol) {
        if(symbol == '+') {
            incomeBtn.setBackgroundColor(incomeBtnColor);
            expenseBtn.setBackgroundColor(hidBtnColor);
        } else if(symbol == '-') {
            incomeBtn.setBackgroundColor(hidBtnColor);
            expenseBtn.setBackgroundColor(expenseBtnColor);
        }
    }
}
