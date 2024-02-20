package jp.ac.ohara.AddressBook.controller;

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
import jp.ac.ohara.AddressBook.service.AddressGroupService;

@Controller
@RequestMapping("/address/")
public class AddressController {

	private final AddressBookService addressBookService;
	private final AddressGroupService groupService;

	public AddressController(AddressBookService _addressBookService, AddressGroupService _groupService) {
		this.addressBookService = _addressBookService;
		this.groupService = _groupService;
	}

	@GetMapping("/add/")
	public ModelAndView add(AddressBook addressBook , ModelAndView model) {
		model.addObject("addressBook", addressBook);
		model.addObject("groups", this.groupService.getGroupList());

		// Viewの設定
		model.addObject("method", "追加");
		model.addObject("action", "/address/confirm");
		model.addObject("button", "確認");

		model.setViewName(this.viewName("form"));
		return model;
	}

	@PostMapping("/confirm")
	public ModelAndView confirm(@Validated @ModelAttribute AddressBook addressBook, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this.add(addressBook, model);
		}
		model.addObject("addressBook", addressBook);

		// Viewの設定
		model.addObject("method", "追加");
		model.addObject("action", "/address/complate");
		model.addObject("button", "確認");

		model.setViewName(this.viewName("confirm"));
		return model;
	}

	@PostMapping("/complate")
	public String complate(@ModelAttribute @NonNull AddressBook addressBook, RedirectAttributes redirectAttributes) {
		try {
			this.addressBookService.save(addressBook);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/address/complate";
	}

	@GetMapping("/complate")
	public ModelAndView complateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "追加");
		model.addObject("exception", exception);
		model.setViewName(this.viewName("complate"));
		return model;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable @NonNull Long id, ModelAndView model) {
		AddressBook addressBook = this.addressBookService.get(id);
		model.addObject("addressBook", addressBook);
		model.addObject("groups", this.groupService.getGroupList());

		// Viewの設定
		model.addObject("method", "編集");
		model.addObject("action", "/address/edit/confirm/" + id);
		model.addObject("button", "確認");

		model.setViewName(this.viewName("form"));

		return this._edit(id, addressBook, model);
	}
	@PostMapping("/edit/confirm/{id}")
	public ModelAndView editConfirm(@PathVariable @NonNull Long id, @Validated @ModelAttribute AddressBook addressBook, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this._edit(id, addressBook, model);
		}
		model.addObject("addressBook", addressBook);

		// Viewの設定
		model.addObject("method", "編集");
		model.addObject("action", "/address/edit/complate/" + id);
		model.addObject("button", "更新");

		model.setViewName(this.viewName("confirm"));
		return model;
	}
	@PostMapping("/edit/complate/{id}")
	public String editComplate(@PathVariable @NonNull Long id, @Validated @ModelAttribute AddressBook addressBook, RedirectAttributes redirectAttributes) {
		try {
			addressBook.setId(id);
			this.addressBookService.save(addressBook);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/address/edit/complate";
	}
	@GetMapping("/edit/complate")
	public ModelAndView editComplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "編集");
		model.addObject("exception", exception);
		model.setViewName(this.viewName("complate"));
		return model;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteConfirm(@PathVariable @NonNull Long id, ModelAndView model) {
		AddressBook addressBook= this.addressBookService.get(id);
		model.addObject("addressBook", addressBook);

		// Viewの設定
		model.addObject("method", "削除確認");
		model.addObject("action", "/address/delete/" + id);
		model.addObject("button", "削除");
		model.setViewName(this.viewName("delete_confirm"));

		return model;
	}
	@PostMapping("/delete/{id}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable @NonNull Long id, ModelAndView model) {
		try {
			this.addressBookService.delete(id);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/address/delete/complate";
	}
	@GetMapping("/delete/complate")
	public ModelAndView deleteComp(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "削除");
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

	/**
	 * 編集画面を返す
	 * @param addressBook
	 * @param model
	 * @return
	 */
	private ModelAndView _edit(Long id, AddressBook addressBook, ModelAndView model) {
		return model;
	}
}
