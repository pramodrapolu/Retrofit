package myapps.mobile.times_now.access.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Headline {
    @JsonProperty("main")
    private String main;
    @JsonProperty("kicker")
    private String kicker;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The main
     */
    @JsonProperty("main")
    public String getMain() {
        return main;
    }

    /**
     *
     * @param main
     * The main
     */
    @JsonProperty("main")
    public void setMain(String main) {
        this.main = main;
    }

    /**
     *
     * @return
     * The kicker
     */
    @JsonProperty("kicker")
    public String getKicker() {
        return kicker;
    }

    /**
     *
     * @param kicker
     * The kicker
     */
    @JsonProperty("kicker")
    public void setKicker(String kicker) {
        this.kicker = kicker;
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
