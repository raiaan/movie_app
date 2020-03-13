
package com.example.rayaan.moviefragment.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Review {

    @SerializedName("id")
    private Integer id;
    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<ResultReview> resultReviews = new ArrayList<ResultReview>();
    @SerializedName("totalPages")
    private Integer totalPages;
    @SerializedName("totalResults")
    private Integer totalResults;
    @SerializedName("additionalProperties")
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 
     * @return
     *     The resultReviews
     */
    public List<ResultReview> getResultReviews() {
        return resultReviews;
    }

    /**
     * 
     * @param resultReviews
     *     The resultReviews
     */
    public void setResultReviews(List<ResultReview> resultReviews) {
        this.resultReviews = resultReviews;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
