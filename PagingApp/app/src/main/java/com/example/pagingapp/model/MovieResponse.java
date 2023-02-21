package com.example.pagingapp.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;


import java.util.List;

public class MovieResponse {
    //create for the api response, not all json we need

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private List<Movie> moview = null;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMoview() {
        return moview;
    }

    public void setMoview(List<Movie> moview) {
        this.moview = moview;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
