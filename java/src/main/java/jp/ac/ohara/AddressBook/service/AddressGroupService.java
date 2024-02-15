package jp.ac.ohara.AddressBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.AddressBook.model.AddressGroup;
import jp.ac.ohara.AddressBook.repository.AddressGroupRepository;

@Service
@Transactional
public class AddressGroupService {

	@Autowired
	private AddressGroupRepository repository;

	/**
	 * アドレス帳一覧の取得
	 * @return List<AddressGroup>
	 */
	public List<AddressGroup> getGroupList() {
	    List<AddressGroup> entityList = this.repository.findAll();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressGroup
	 */
	public AddressGroup get(@NonNull Long index) {
		AddressGroup addressGroup = this.repository.findById(index).orElse(new AddressGroup());
		return addressGroup;
	}

	/**
	 * アドレス帳一覧の取得
	 * @param AddressBook addressBook
	 */
	public void save(@NonNull AddressGroup addressGroup) {
		this.repository.save(addressGroup);
	}

	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
}
