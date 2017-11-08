package kmitl.lab09.chanapat58070024.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "LEDGER_ITEM")
public class LedgerItem implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "SYMBOL")
    private char symbol;

    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    @ColumnInfo(name = "AMOUNT")
    private int amount;

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

    public LedgerItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
