package jp.ac.ohara.AddressBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.ohara.AddressBook.model.AddressBook;
import jp.ac.ohara.AddressBook.service.AddressBookService;

@Controller
@RequestMapping("/edit")
public class EditController {
	@Autowired
	private AddressBookService addressBookService;

	@GetMapping("/{id}")
	public ModelAndView index(@PathVariable @NonNull Long id, ModelAndView model) {
		// データ取得
		AddressBook addressBook = this.addressBookService.get(id);

		model.addObject("addressBook", addressBook);
		
		model.addObject("method", "編集");
		model.addObject("action", "/edit/confirm/" + id);

		model.setViewName("/views/form");
		return model;
	}

	@PostMapping("/confirm/{id}")
	public ModelAndView confirm(@Validated @ModelAttribute AddressBook addressBook, BindingResult result, @PathVariable @NonNull Long id, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this.index(id, model);
		}
		model.addObject("addressBook", addressBook);

		// Viewの設定
		model.addObject("method", "編集");
		model.addObject("action", "/edit/complate/" + id);

		model.setViewName("views/confirm");
		return model;
	}

	@PostMapping("/complate/{id}")
	public String complate(@ModelAttribute @NonNull AddressBook addressBook, RedirectAttributes redirectAttributes, @PathVariable @NonNull Long id) {
		try {
			this.addressBookService.save(addressBook);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/edit/complate";
	}

	@GetMapping("/complate")
	public ModelAndView cmplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "編集");
		model.addObject("exception", exception);
		model.setViewName("views/complate");
		return model;
	}
}
