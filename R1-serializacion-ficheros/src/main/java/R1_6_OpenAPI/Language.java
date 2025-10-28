package R1_6_OpenAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Language {
    private String alpha2;
    @JsonProperty("English")
    private String english;

    public Language() {
    }

    public Language(String english, String alpha2) {
        this.english = english;
        this.alpha2 = alpha2;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getEnglish() {
        return english;
    }

    @Override
    public String toString() {
        return "[" + alpha2 + "]" + english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
