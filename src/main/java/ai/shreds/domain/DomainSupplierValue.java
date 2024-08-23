package ai.shreds.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents the value object for Supplier containing essential attributes such as name, contact information, and address.
 */
@Getter
@ToString
@RequiredArgsConstructor
public class DomainSupplierValue {
    @NonNull
    @Size(min = 1, message = "Supplier name is required.")
    private final String name;

    @NonNull
    @Pattern(regexp = "(\d{10,})\s*,\s*(.+@.+\..+)", message = "Supplier contact information must include a valid phone number and email address.")
    private final String contact_info;

    @NonNull
    private final String address;
}