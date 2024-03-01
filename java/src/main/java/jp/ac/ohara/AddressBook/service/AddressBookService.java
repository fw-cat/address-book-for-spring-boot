package jp.ac.ohara.AddressBook.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.AddressBook.model.AddressBook;
import jp.ac.ohara.AddressBook.repository.AddressBookRepository;

@Service
@Transactional
public class AddressBookService {

	private final AddressBookRepository repository;

	/**
	 * コンストラクタ
	 * @param AddressBookRepository _repository
	 */
	public AddressBookService(AddressBookRepository _repository) {
		this.repository = _repository;
	}
	
	/**
	 * アドレス帳一覧の取得
	 * @return List<AddressBook>
	 */
	public List<AddressBook> getAddressList() {
	    List<AddressBook> entityList = this.repository.findAllByOrderByFirstNameDesc();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressBook
	 */
	public AddressBook get(@NonNull Long index) {
		AddressBook addressBook = this.repository.findById(index).orElse(new AddressBook());
		return addressBook;
	}

	/**
	 * アドレス帳データの保存
	 * @param @NonNull AddressBook addressBook
	 */
	public void save(@NonNull AddressBook addressBook) {
		this.repository.saveAndFlush(addressBook);
	}
	/**
	 * アドレス帳データの更新
	 * @param @NonNull AddressBook addressBook
	 * @param Long id
	 */
	public void save(@NonNull AddressBook addressBook, Long id) {
		addressBook.setId(id);
		this.save(addressBook);
	}

	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}
