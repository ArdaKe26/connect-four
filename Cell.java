public class Cell {
    private String symbol = ".";
    private boolean isObstacle = false;
    private boolean isPortal = false;
    private int targetRow, targetCol;
    
    public Cell() {

    }

    public String getSymbol(){
        return this.symbol;
    }

    public void setSymbol(String s){
        this.symbol = s;
    }

    public void setAsObstacle() {
        this.isObstacle = true;
        this.symbol = "#";
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setAsPortal(int tRow, int tCol) {
        this.isPortal = true;
        this.targetRow = tRow;
        this.targetCol = tCol;
        this.symbol = "@";
    }

    public boolean isPortal() { 
        return isPortal; 
    }
    public int getTargetRow() { 
        return targetRow; 
    }
    public int getTargetCol() { 
        return targetCol; 
    }
    }
