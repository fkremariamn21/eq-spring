package com.example.equib.controller;

import com.example.equib.model.Group;
import com.example.equib.model.GroupStatus; // Add this line
import com.example.equib.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Displays the home page with a list of all groups
    @GetMapping("/")
    public String listGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    // Displays the form to create a new group
    @GetMapping("/add-group")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "addGroup";
    }
 
    // Handles the form submission for creating a new group
    @PostMapping("/save-group")
    public String saveGroup(@ModelAttribute("group") Group group, RedirectAttributes redirectAttributes) {
        try {
            // Set an initial status for a new group
            group.setStatus(GroupStatus.PENDING);
            groupService.createGroup(group);
            redirectAttributes.addFlashAttribute("successMessage", "Group '" + group.getName() + "' created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating group: " + e.getMessage());
        }
        return "redirect:/";
    }

    // Displays details of a specific group
    @GetMapping("/groups/{id}")
    public String groupDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Group group = groupService.getGroupById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid group ID: " + id));
            model.addAttribute("group", group);
            return "groupDetails";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
    }
} 