public class Cell {
    private String symbol = ".";
    private boolean isObstacle = false;

    public Cell() {

    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String s) {
        this.symbol = s;
    }

    public void setAsObstacle() {
        this.isObstacle = true;
        this.symbol = "#";
    }

    public boolean isObstacle() {
        return isObstacle;
    }
}
