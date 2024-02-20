package jp.ac.ohara.AddressBook.controller;

import java.util.List;

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

import jp.ac.ohara.AddressBook.model.AddressGroup;
import jp.ac.ohara.AddressBook.service.AddressGroupService;

@Controller
@RequestMapping("/groups/")
public class GroupsController {

	private final AddressGroupService groupService;

	public GroupsController(AddressGroupService _groupService) {
		this.groupService = _groupService;
	}

	@GetMapping("/")
	public ModelAndView top(ModelAndView model) {
		// グループ一覧の取得
		List<AddressGroup> groupList = this.groupService.getGroupList();

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
			this.groupService.save(addressGroup);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/groups/complate";
	}
	@GetMapping("/complate")
	public ModelAndView cmplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "登録");
		model.addObject("exception", exception);
		model.setViewName(this.viewName("complate"));
		return model;
	}

	@GetMapping("/edit/{groupId}")
	public ModelAndView edit(@PathVariable @NonNull Long groupId, ModelAndView model) {
		AddressGroup addressGroup = this.groupService.get(groupId);
		model.addObject("addressGroup", addressGroup);

		// Viewの設定
		model.addObject("method", "編集");
		model.addObject("action", "edit/confirm/" + groupId);
		model.addObject("button", "確認");
		model.setViewName(this.viewName("form"));

		return this._edit(groupId, addressGroup, model);
	}
	@PostMapping("edit/confirm/{groupId}")
	public ModelAndView editConfirm(@PathVariable @NonNull Long groupId, @Validated @ModelAttribute AddressGroup addressGroup, BindingResult result, ModelAndView model) {
		if (result.hasErrors()) {
			model.addObject("validationError", "不正な値が入力されました。");
			return this._edit(groupId, addressGroup, model);
		}
		model.addObject("addressGroup", addressGroup);

		// Viewの設定
		model.addObject("method", "編集");
		model.addObject("action", "edit/complate/" + groupId);
		model.addObject("button", "更新");

		model.setViewName(this.viewName("confirm"));
		return model;
	}
	@PostMapping("/edit/complate/{groupId}")
	public String editComplate(@PathVariable @NonNull Long groupId, @ModelAttribute @NonNull AddressGroup addressGroup, RedirectAttributes redirectAttributes) {
		try {
			addressGroup.setId(groupId);
			this.groupService.save(addressGroup);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/groups/edit/complate";
	}
	@GetMapping("/edit/complate")
	public ModelAndView editComplateRedirect(@ModelAttribute("exception") String exception, ModelAndView model) {
		model.addObject("method", "更新");
		model.addObject("exception", exception);
		model.setViewName(this.viewName("complate"));
		return model;
	}
	@GetMapping("/delete/{groupId}")
	public ModelAndView deleteConfirm(@PathVariable @NonNull Long groupId, ModelAndView model) {
		AddressGroup addressGroup = this.groupService.get(groupId);
		model.addObject("addressGroup", addressGroup);

		// Viewの設定
		model.addObject("method", "削除確認");
		model.addObject("action", "delete/" + groupId);
		model.addObject("button", "削除");
		model.setViewName(this.viewName("delete_confirm"));

		return model;
	}
	@PostMapping("/delete/{groupId}")
	public String delete(RedirectAttributes redirectAttributes, @PathVariable @NonNull Long groupId, ModelAndView model) {
		try {
			this.groupService.delete(groupId);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/groups/delete/complate";
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
		return "views/groups/" + name;
	}

	/**
	 * 編集画面を返す
	 * @param addressGroup
	 * @param model
	 * @return
	 */
	private ModelAndView _edit(Long groupId, AddressGroup addressGroup, ModelAndView model) {
		return model;
	}

}
