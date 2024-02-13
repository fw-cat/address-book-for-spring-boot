package jp.ac.ohara.AddressBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.ohara.AddressBook.service.AddressBookService;

@Controller
public class TopController {
	@Autowired
	private AddressBookService addressBookService; 

	@GetMapping("/")
	public ModelAndView top(ModelAndView model) {
		model.addObject("addressList", this.addressBookService.getAddressList());
		model.setViewName("views/top");
		return model;
	}
}
