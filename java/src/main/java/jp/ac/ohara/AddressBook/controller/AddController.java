package jp.ac.ohara.AddressBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.ohara.AddressBook.model.AddressBook;

@Controller
public class AddController {
	@GetMapping("/add/")
	public ModelAndView add(ModelAndView model) {
		AddressBook addressBook = new AddressBook();
		model.addObject("addressBook", addressBook);
		model.addObject("method", "追加");

		model.setViewName("views/form");
		return model;
	}

	@PostMapping("/add/confirm")
	public ModelAndView addConfirm(@ModelAttribute AddressBook addressBook, ModelAndView model) {
		
		return model;
	}
}
