package com.nikitasutulov.lab8;

public class WagonOverloadedException extends Exception
{
    public static final String MESSAGE = "Too many people and luggage to load to a wagon";
    public WagonOverloadedException()
    {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
