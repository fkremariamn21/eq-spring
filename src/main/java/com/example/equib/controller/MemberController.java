package com.example.equib.controller;

import com.example.equib.model.Group;
import com.example.equib.model.Member;
import com.example.equib.service.GroupService;
import com.example.equib.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
 
@Controller 
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final GroupService groupService;

    @Autowired
    public MemberController(MemberService memberService, GroupService groupService) {
        this.memberService = memberService;
        this.groupService = groupService;
    }

    @GetMapping("/group/{groupId}")
    public String listMembers(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));
        model.addAttribute("group", group);
        model.addAttribute("members", group.getMembers());
        return "membersList";
    }

    @GetMapping("/add/{groupId}")
    public String showAddMemberForm(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + groupId));
        Member member = new Member();
        member.setGroup(group);
        model.addAttribute("member", member);
        return "addMember";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member) {
        memberService.createMember(member);
        return "redirect:/groups";
    }
}