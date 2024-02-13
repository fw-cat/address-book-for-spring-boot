package jp.ac.ohara.AddressBook.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}
