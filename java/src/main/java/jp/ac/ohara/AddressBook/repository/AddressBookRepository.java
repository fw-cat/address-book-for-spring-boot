package jp.ac.ohara.AddressBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.AddressBook.model.AddressBook;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
	
}
