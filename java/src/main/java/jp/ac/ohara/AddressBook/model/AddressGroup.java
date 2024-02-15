package jp.ac.ohara.AddressBook.model;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jp.ac.ohara.AddressBook.define.ErrorMessage;
import lombok.Data;

@Data
@Entity
@Table(name="address_groups")
@SQLDelete(sql = "UPDATE address_groups SET deleted_at = NOW() WHERE id=?")
@SQLRestriction(value = "deleted_at IS NULL")
public class AddressGroup {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = ErrorMessage.NOT_NULL)
    private String groupName;

	@OneToMany(mappedBy="group")
    private List<AddressBook> addressBooks;
}
