package ru.team1.sorting.utils.exceptions;

public class ParseBookException extends IllegalArgumentException{
    public ParseBookException(String errMsg) {
        super(errMsg);
    }
    public ParseBookException(String errMsg, Throwable err) {
    super(errMsg,err);
}
}
