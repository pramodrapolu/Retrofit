package com.android.mobile.access.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("docs")
    private List<Doc> docs = new ArrayList<Doc>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The meta
     */
    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The meta
     */
    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     * The docs
     */
    @JsonProperty("docs")
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     *
     * @param docs
     * The docs
     */
    @JsonProperty("docs")
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
