package kmitl.lab03.chanapat58070024.simplemydot.model;

import java.util.LinkedList;

public class DotList {
    private LinkedList<Dot> dotList;
    private DotListChangedListener listener;

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
