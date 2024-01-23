package jp.ac.ohara.AddressBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.ohara.AddressBook.model.AddressBook;

@Controller
@RequestMapping("/add")
public class AddController {
	@GetMapping("/")
	public ModelAndView index(AddressBook addressBook , ModelAndView model) {
		model.addObject("addressBook", addressBook);
		model.addObject("method", "追加");

		model.setViewName("views/form");
		return model;
	}

	@PostMapping("/confirm")
	public ModelAndView addConfirm(@Validated @ModelAttribute AddressBook addressBook, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this.index(addressBook, model);
		}
		return model;
	}
}
