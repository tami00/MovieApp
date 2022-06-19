package com.example.cinemaapp.models;

public class ReviewPOJO
{
    private String copyright;

    private String has_more;

    private ReviewResults[] results;

    private String num_results;

    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getHas_more ()
    {
        return has_more;
    }

    public void setHas_more (String has_more)
    {
        this.has_more = has_more;
    }

    public ReviewResults[] getResults ()
    {
        return results;
    }

    public void setResults (ReviewResults[] results)
    {
        this.results = results;
    }

    public String getNum_results ()
    {
        return num_results;
    }

    public void setNum_results (String num_results)
    {
        this.num_results = num_results;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [copyright = "+copyright+", has_more = "+has_more+", results = "+results+", num_results = "+num_results+", status = "+status+"]";
    }
}
