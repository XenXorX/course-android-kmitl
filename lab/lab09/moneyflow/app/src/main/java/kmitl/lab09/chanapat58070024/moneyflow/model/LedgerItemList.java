package kmitl.lab09.chanapat58070024.moneyflow.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class LedgerItemList implements Parcelable {
    private ArrayList<LedgerItem> ledgerItems;
    private int balance;
    private int income;
    private LedgerItemListChangedListener listener;

    protected LedgerItemList(Parcel in) {
        ledgerItems = in.createTypedArrayList(LedgerItem.CREATOR);
        balance = in.readInt();
        income = in.readInt();
    }

    public static final Creator<LedgerItemList> CREATOR = new Creator<LedgerItemList>() {
        @Override
        public LedgerItemList createFromParcel(Parcel in) {
            return new LedgerItemList(in);
        }

        @Override
        public LedgerItemList[] newArray(int size) {
            return new LedgerItemList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(ledgerItems);
        parcel.writeInt(balance);
        parcel.writeInt(income);
    }

    public interface LedgerItemListChangedListener {
        void onLedgerItemListChanged(LedgerItemList ledgerItemList);
    }

    public LedgerItemList() {
        ledgerItems = new ArrayList<>();
        balance = 0;
        income = 0;
    }

    public LedgerItemListChangedListener getListener() {
        return listener;
    }

    public void setListener(LedgerItemListChangedListener listener) {
        this.listener = listener;
    }

    public ArrayList<LedgerItem> getLedgerItems() {
        return ledgerItems;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addItem(LedgerItem ledgerItem) {
        ledgerItems.add(ledgerItem);
        listener.onLedgerItemListChanged(this);
    }

    public void removeItem(LedgerItem ledgerItem) {
        ledgerItems.remove(ledgerItem);
        listener.onLedgerItemListChanged(this);
    }
}
