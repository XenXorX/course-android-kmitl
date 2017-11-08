package kmitl.lab09.chanapat58070024.moneyflow.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LedgerItem implements Parcelable{
    private char symbol;
    private String description;
    private int amount;
    private LedgerItemChangedListener listener;

    protected LedgerItem(Parcel in) {
        symbol = (char) in.readInt();
        description = in.readString();
        amount = in.readInt();
    }

    public static final Creator<LedgerItem> CREATOR = new Creator<LedgerItem>() {
        @Override
        public LedgerItem createFromParcel(Parcel in) {
            return new LedgerItem(in);
        }

        @Override
        public LedgerItem[] newArray(int size) {
            return new LedgerItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt((int) symbol);
        parcel.writeString(description);
        parcel.writeInt(amount);
    }

    public interface LedgerItemChangedListener {
        void onLedgerItemChanged(LedgerItem ledgerItem);
    }

    public LedgerItem() {}

    public void setListener(LedgerItemChangedListener listener) {
        this.listener = listener;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
        listener.onLedgerItemChanged(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        listener.onLedgerItemChanged(this);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        listener.onLedgerItemChanged(this);
    }
}
