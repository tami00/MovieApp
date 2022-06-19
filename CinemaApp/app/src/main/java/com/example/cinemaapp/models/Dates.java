package com.example.cinemaapp.models;

public class Dates
{
    private String maximum;

    private String minimum;

    public String getMaximum ()
    {
        return maximum;
    }

    public void setMaximum (String maximum)
    {
        this.maximum = maximum;
    }

    public String getMinimum ()
    {
        return minimum;
    }

    public void setMinimum (String minimum)
    {
        this.minimum = minimum;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [maximum = "+maximum+", minimum = "+minimum+"]";
    }
}
