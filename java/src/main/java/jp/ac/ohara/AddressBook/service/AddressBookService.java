package jp.ac.ohara.AddressBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.AddressBook.model.AddressBook;
import jp.ac.ohara.AddressBook.repository.AddressBookRepository;

@Service
@Transactional
public class AddressBookService {

	@Autowired
	private AddressBookRepository repository;

	/**
	 * アドレス帳一覧の取得
	 * @return List<AddressBook>
	 */
	public List<AddressBook> getAddressList() {
	    List<AddressBook> entityList = this.repository.findAll();
	    return entityList;
	}

	/**
	 * アドレス帳一覧の取得
	 * @param AddressBook addressBook
	 */
	public void save(AddressBook addressBook) {
		this.repository.save(addressBook);
	}
}
