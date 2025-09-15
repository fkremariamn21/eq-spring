package com.example.equib.controller;

import com.example.equib.model.Group;
import com.example.equib.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller 
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups"; // templates/groups.html
    }

    @GetMapping("/add-group")
    public String addGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "addGroup"; // templates/addGroup.html
    }

    @PostMapping("/add-group")
    public String saveGroup(@ModelAttribute Group group) {
        groupService.saveGroup(group);
        return "redirect:/";
    }

    @GetMapping("/group/{id}")
    public String groupDetails(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "groupDetails"; // templates/groupDetails.html
    }
}
