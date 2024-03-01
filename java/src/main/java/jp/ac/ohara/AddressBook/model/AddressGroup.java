package jp.ac.ohara.AddressBook.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jp.ac.ohara.AddressBook.define.ErrorMessage;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "address_groups")
@SQLDelete(sql = "UPDATE address_groups SET deleted_at = NOW() WHERE id=?")
@SQLRestriction(value = "deleted_at IS NULL")
public class AddressGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = ErrorMessage.NOT_NULL)
	private String groupName;

	@OneToMany(mappedBy = "group")
	private List<AddressBook> addressBooks;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updatedAt;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdAt;
}
