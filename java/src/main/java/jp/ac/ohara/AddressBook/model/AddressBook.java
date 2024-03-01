package jp.ac.ohara.AddressBook.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jp.ac.ohara.AddressBook.define.ErrorMessage;
import lombok.Data;

@Data
@Entity
@Table(name="address_books")
@SQLDelete(sql = "UPDATE address_books SET deleted_at = NOW() WHERE id=?")
@SQLRestriction(value = "deleted_at IS NULL")
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

	@PastOrPresent(message = ErrorMessage.BIRTH_DATE)
	@Column(name = "birth_dt")
	private Date birthDay;
	
	@Pattern(regexp = "\\d{3}-?\\d{4}", message = ErrorMessage.PATTERN_ZIP_CODE)
    private String zipCode;
    private String prefecture;
    private String city;
    private String address;
    private String building;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private AddressGroup group;

	@Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

	@PrePersist
    protected void onCreate() {
    	this.updatedAt = new Date();
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
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

    /**
     * 誕生日から年齢を算出
     * @return int 年齢
     */
    public int getAge() {
    	if (this.birthDay.equals(null)) {
    		// 誕生日がない場合は0を返す
    		return 0;
    	}
    	// 現在の日付をLocalDate形式で取得
        LocalDate now = LocalDate.now();
        // 誕生日をLocalDate形式に変換
        LocalDate birthday = this.birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // 年齢を計算
        return Period.between(birthday, now).getYears();
    }
    /**
     * 誕生日から年齢を算出
     * @return String 年齢（歳）
     */
    public String getAgeText() {
    	if (this.birthDay.equals(null)) {
    		// 誕生日がない場合は0を返す
    		return "不明";
    	}
    	// 現在の日付をLocalDate形式で取得
        LocalDate now = LocalDate.now();
        // 誕生日をLocalDate形式に変換
        LocalDate birthday = this.birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // 年齢を計算
        return Period.between(birthday, now).getYears() + "歳";
    }
    
    
}
