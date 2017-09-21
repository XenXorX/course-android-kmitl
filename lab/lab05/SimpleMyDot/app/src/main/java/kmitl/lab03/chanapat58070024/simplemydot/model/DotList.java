package kmitl.lab03.chanapat58070024.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;

public class DotList implements Parcelable {
    private LinkedList<Dot> dotList;
    private DotListChangedListener listener;

    protected DotList(Parcel in) {
    }

    public static final Creator<DotList> CREATOR = new Creator<DotList>() {
        @Override
        public DotList createFromParcel(Parcel in) {
            return new DotList(in);
        }

        @Override
        public DotList[] newArray(int size) {
            return new DotList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public interface DotListChangedListener {
        void onDotListChanged(DotList dotList);
    }

    //Constructor
    public DotList() {
        dotList = new LinkedList();
    }

    //Getter and Setter
    public void setListener(DotListChangedListener listener) {
        this.listener = listener;
    }

    public LinkedList<Dot> getDotList() {
        return dotList;
    }

    //Method
    public void addDot(Dot dot) {
        dotList.add(dot);
        listener.onDotListChanged(this);
    }

    public void removeDot(Dot dot) {
        dotList.remove(dot);
        listener.onDotListChanged(this);
    }

    public void clearDot() {
        dotList.clear();
        listener.onDotListChanged(this);
    }

    public void undoDot() {
        if(!dotList.isEmpty()) {
            dotList.removeLast();
            listener.onDotListChanged(this);
        }
    }
}
