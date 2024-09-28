package lcs.oms.enums;

public enum ResponseStatus {

    SUCCESS(1),
    FAILED(2);

    private int statusCode;
    ResponseStatus(int statusCode){
        this.statusCode = statusCode;
    }
}
