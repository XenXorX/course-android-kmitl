package kmitl.lab09.chanapat58070024.moneyflow.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab09.chanapat58070024.moneyflow.R;
import kmitl.lab09.chanapat58070024.moneyflow.model.LedgerItem;

public class LedgerAdapter extends BaseAdapter {
    private final int bgColor = Color.rgb(220, 220, 220);

    private Context context;
    private ArrayList<LedgerItem> ledgerItems;

    public LedgerAdapter(Context context) {
        this.context = context;
    }

    public List<LedgerItem> getLedgerItems() {
        return ledgerItems;
    }

    public void setLedgerItems(ArrayList<LedgerItem> ledgerItems) {
        this.ledgerItems = ledgerItems;
    }

    @Override
    public int getCount() {
        return ledgerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.ledger_item, viewGroup, false);

        LedgerItem ledgerItem = ledgerItems.get(i);

        TextView symbolText = view.findViewById(R.id.text_symbol);
        symbolText.setText(String.valueOf(ledgerItem.getSymbol()));

        TextView descriptionText = view.findViewById(R.id.text_description);
        descriptionText.setText(ledgerItem.getDescription());

        TextView amountText = view.findViewById(R.id.text_amount);
        amountText.setText(String.valueOf(ledgerItem.getAmount()));

        if(i%2 == 1) {
            symbolText.setBackgroundColor(bgColor);
            descriptionText.setBackgroundColor(bgColor);
            amountText.setBackgroundColor(bgColor);
        }

        return view;
    }
}
