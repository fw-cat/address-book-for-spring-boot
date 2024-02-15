package jp.ac.ohara.AddressBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.AddressBook.model.AddressGroup;

@Repository
public interface AddressGroupRepository extends JpaRepository<AddressGroup, Long> {
	
}
