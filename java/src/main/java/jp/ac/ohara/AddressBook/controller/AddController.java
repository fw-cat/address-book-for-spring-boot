package jp.ac.ohara.AddressBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.ohara.AddressBook.model.AddressBook;
import jp.ac.ohara.AddressBook.service.AddressBookService;



@Controller
@RequestMapping("/add")
public class AddController {
	@Autowired
	private AddressBookService addressBookService; 

	@GetMapping("/")
	public ModelAndView index(AddressBook addressBook , ModelAndView model) {
		model.addObject("addressBook", addressBook);
		model.addObject("method", "追加");

		model.setViewName("views/form");
		return model;
	}

	@PostMapping("/confirm")
	public ModelAndView confirm(@Validated @ModelAttribute AddressBook addressBook, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this.index(addressBook, model);
		}

		model.addObject("method", "追加");
		model.addObject("addressBook", addressBook);
		model.setViewName("views/confirm");
		return model;
	}

	@PostMapping("/complate")
	public String complate(@ModelAttribute AddressBook addressBook, RedirectAttributes redirectAttributes) {
		try {
			this.addressBookService.save(addressBook);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/add/complate";
	}

	@GetMapping("/complate")
	public ModelAndView cmplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "追加");
		model.addObject("exception", exception);
		model.setViewName("views/complate");
		return model;
	}
	
}
