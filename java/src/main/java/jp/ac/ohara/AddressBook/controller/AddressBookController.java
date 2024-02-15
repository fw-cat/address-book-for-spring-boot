package jp.ac.ohara.AddressBook.controller;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.websocket.server.PathParam;
import jp.ac.ohara.AddressBook.model.AddressBook;
import jp.ac.ohara.AddressBook.service.AddressBookService;
import jp.ac.ohara.AddressBook.service.AddressGroupService;

@Controller
public class AddressBookController {

	private final AddressBookService addressBookService;
	private final AddressGroupService groupService;

	public AddressBookController(AddressBookService _addressBookService, AddressGroupService _groupService) {
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

	@GetMapping("/address/add/")
	public ModelAndView add(AddressBook addressBook , ModelAndView model) {
		model.addObject("addressBook", addressBook);
		model.addObject("groups", this.groupService.getGroupList());

		// Viewの設定
		model.addObject("method", "追加");
		model.addObject("action", "/address/confirm");

		model.setViewName(this.viewName("form"));
		return model;
	}

	@PostMapping("/address/confirm")
	public ModelAndView confirm(@Validated @ModelAttribute AddressBook addressBook, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this.add(addressBook, model);
		}
		model.addObject("addressBook", addressBook);

		// Viewの設定
		model.addObject("method", "追加");
		model.addObject("action", "/address/complate");

		model.setViewName(this.viewName("confirm"));
		return model;
	}

	@PostMapping("/address/complate")
	public String complate(@ModelAttribute @NonNull AddressBook addressBook, RedirectAttributes redirectAttributes) {
		try {
			this.addressBookService.save(addressBook);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/address/complate";
	}

	@GetMapping("/address/complate")
	public ModelAndView cmplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "追加");
		model.addObject("exception", exception);
		model.setViewName(this.viewName("complate"));
		return model;
	}

/**
	 * Model名を返す
	 * @param name
	 * @return String
	 */
	private String viewName(String name) {
		return "views/address/" + name;
	}
}
