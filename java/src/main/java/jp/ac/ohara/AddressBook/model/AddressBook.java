package jp.ac.ohara.AddressBook.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jp.ac.ohara.AddressBook.define.ErrorMessage;
import lombok.Data;

@Data
@Entity
@Table(name="address_books")
public class AddressBook {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = ErrorMessage.NOT_NULL)
    private String lastName;

	@NotBlank(message = ErrorMessage.NOT_NULL)
    private String firstName;

	@NotBlank(message = ErrorMessage.NOT_NULL)
	@Email
    private String mailAddress;

	@NotBlank(message = ErrorMessage.NOT_NULL)
	@Size(min=10, max=13)
	@Pattern(regexp = "0\\d{1,4}-?\\d{1,4}-?\\d{4}", message = ErrorMessage.PATTERN_PHONE)
    private String phoneNumber;

	@Pattern(regexp = "\\d{3}-?\\d{4}", message = ErrorMessage.PATTERN_ZIP_CODE)
    private String zipCode;
    private String prefecture;
    private String city;
    private String address;
    private String building;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    /**
     * 住所の文字列を返す
     * @return 住所
     */
    public String getAddressText() {
    	if (this.prefecture == null || this.prefecture.isEmpty() ||
    		this.city == null || this.city.isEmpty() ||
    		this.address == null || this.address.isEmpty()) {
        	return "";
    	}

    	String addressText = this.prefecture + this.city + this.address;
    	if (!this.building.isEmpty()) {
    		addressText  += "　" + this.building;
    	}
    	return addressText;
    }
}
