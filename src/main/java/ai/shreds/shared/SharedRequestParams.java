package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for filtering supplier queries.
 * This class encapsulates the optional filter criteria for querying suppliers such as name, contact information, and address.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedRequestParams {
    private String name;
    private String contact_info;
    private String address;

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}