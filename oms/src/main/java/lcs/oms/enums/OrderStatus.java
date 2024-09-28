package lcs.oms.enums;

public enum OrderStatus {

    CREATED(1),
    CANCELED(2);
    
    private int statusCode;


    OrderStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
