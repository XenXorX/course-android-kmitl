package kmitl.lab09.chanapat58070024.moneyflow.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {LedgerItem.class}, version = 1)
public abstract class LedgerItemDatabase extends RoomDatabase {
    public abstract LedgerItemDAO ledgerItemRoomDAO();
}
