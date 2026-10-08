import com.fasterxml.jackson.annotation.JsonProperty;

public class Fan {
    private int id;
    
    // @JsonProperty maps custom JSON keys to your Java field names
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    private String favoriteTeam;

    // 1. Mandatory no-arg constructor for Jackson deserialization
    public Fan() {
    }

    // 2. Convenience constructor for creating test objects
    public Fan(int id, String firstName, String lastName, String favoriteTeam) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteTeam = favoriteTeam;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    @Override
    public String toString() {
        return String.format("Fan [ID=%d, Name=%s %s, Team=%s]", 
                id, firstName, lastName, favoriteTeam);
    }
}