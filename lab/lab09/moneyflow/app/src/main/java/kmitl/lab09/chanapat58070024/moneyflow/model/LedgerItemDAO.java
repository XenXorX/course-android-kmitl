package kmitl.lab09.chanapat58070024.moneyflow.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface LedgerItemDAO {
    @Query("SELECT * FROM LEDGER_ITEM")
    List<LedgerItem> getAll();

    @Insert
    void insert(LedgerItem ledgerItem);

    @Update
    void update(LedgerItem ledgerItem);

    @Delete
    void delete(LedgerItem ledgerItem);
}
