package com.example.equib.service;


import com.example.equib.model.Group;
import com.example.equib.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
 
@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
 