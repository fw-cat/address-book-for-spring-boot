package jp.ac.ohara.AddressBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.websocket.server.PathParam;
import jp.ac.ohara.AddressBook.service.AddressBookService;
import jp.ac.ohara.AddressBook.service.AddressGroupService;

@Controller
public class TopController {

	private final AddressBookService addressBookService;
	private final AddressGroupService groupService;

	public TopController(AddressBookService _addressBookService, AddressGroupService _groupService) {
		this.addressBookService = _addressBookService;
		this.groupService = _groupService;
	}

	@GetMapping("/")
	public ModelAndView top(ModelAndView model) {
		model.addObject("addressList", this.addressBookService.getAddressList());
		model.addObject("groups", this.groupService.getGroupList());

		model.setViewName("views/top");
		return model;
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detail(@PathParam("id") Long id, ModelAndView model) {
		model.addObject("addressList", this.addressBookService.getAddressList());

		model.setViewName("views/top");
		return model;
	}
}
