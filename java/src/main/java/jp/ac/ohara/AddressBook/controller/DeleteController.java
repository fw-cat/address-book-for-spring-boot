package jp.ac.ohara.AddressBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.ohara.AddressBook.service.AddressBookService;


@Controller
@RequestMapping("/delete")
public class DeleteController {
	@Autowired
	private AddressBookService addressBookService; 

	@PostMapping("/complate/{id}")
	public String complate(RedirectAttributes redirectAttributes, @PathVariable @NonNull Long id) {
		try {
			this.addressBookService.delete(id);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/delete/complate";
	}

	@GetMapping("/complate")
	public ModelAndView cmplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "削除");
		model.addObject("exception", exception);
		model.setViewName("views/complate");
		return model;
	}}
