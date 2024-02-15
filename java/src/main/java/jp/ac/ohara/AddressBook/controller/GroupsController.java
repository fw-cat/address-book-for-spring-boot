package jp.ac.ohara.AddressBook.controller;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.ohara.AddressBook.model.AddressGroup;
import jp.ac.ohara.AddressBook.repository.AddressGroupRepository;

@Controller
@RequestMapping("/groups/")
public class GroupsController {

	private final AddressGroupRepository groupRepository;

	public GroupsController(AddressGroupRepository _groupRepository) {
		this.groupRepository = _groupRepository;
	}

	@GetMapping("/")
	public ModelAndView top(ModelAndView model) {
		// グループ一覧の取得
		List<AddressGroup> groupList = this.groupRepository.findAll();

		model.addObject("groupList", groupList);
		model.setViewName(this.viewName("top"));
		return model;
	}

	@GetMapping("/add/")
	public ModelAndView add(AddressGroup addressGroup, ModelAndView model) {
		model.addObject("addressGroup", addressGroup);
		model.setViewName(this.viewName("form"));

		// Viewの設定
		model.addObject("method", "追加");
		model.addObject("action", "confirm");
		model.addObject("button", "確認");

		return model;
	}

	@PostMapping("/confirm")
	public ModelAndView confirm(@Validated @ModelAttribute AddressGroup addressGroup, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this.add(addressGroup, model);
		}
		model.addObject("addressGroup", addressGroup);

		// Viewの設定
		model.addObject("method", "追加");
		model.addObject("action", "complate");
		model.addObject("button", "登録");

		model.setViewName(this.viewName("confirm"));
		return model;
	}

	@PostMapping("/complate")
	public String complate(@ModelAttribute @NonNull AddressGroup addressGroup, RedirectAttributes redirectAttributes) {
		try {
			this.groupRepository.save(addressGroup);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/groups/complate";
	}

	@GetMapping("/complate")
	public ModelAndView cmplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
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
		return "views/groups/" + name;
	}
}
