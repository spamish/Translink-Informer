
package com.spamish.project.translinkinformer.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggested {

    @SerializedName("Suggestions")
    @Expose
    private List<Suggestion> suggestions = new ArrayList<>();

    /**
     * 
     * @return
     *     The suggestions
     */
    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     *
     * @param suggestions
     *     The Suggestions
     */
    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

}
