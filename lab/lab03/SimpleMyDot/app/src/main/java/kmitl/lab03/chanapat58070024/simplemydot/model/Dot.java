package kmitl.lab03.chanapat58070024.simplemydot.model;

public class Dot {
    private float centerX;
    private float centerY;
    private int radius;
    private int color;

    //Constructor
    public Dot(int radius) {
        this.radius = radius;
    }

    //Getter and Setter
    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
