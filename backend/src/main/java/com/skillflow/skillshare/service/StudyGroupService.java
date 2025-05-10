package com.skillflow.skillshare.service;

import com.skillflow.skillshare.model.StudyGroup;
import com.skillflow.skillshare.repository.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyGroupService {

    @Autowired
    private StudyGroupRepository repo;

    public List<StudyGroup> getAllGroups() { return repo.findAll(); }
    public StudyGroup createGroup(StudyGroup group) { return repo.save(group); }
    public Optional<StudyGroup> getGroupById(String id) { return repo.findById(id); }
    public StudyGroup updateGroup(String id, StudyGroup updatedGroup) {
        updatedGroup.setId(id);
        return repo.save(updatedGroup);
    }
    public void deleteGroup(String id) { repo.deleteById(id); }

    public StudyGroup addMemberToGroup(String groupId, StudyGroup.Member member) {
        Optional<StudyGroup> groupOpt = repo.findById(groupId);
        if (groupOpt.isPresent()) {
            StudyGroup group = groupOpt.get();
            boolean memberExists = group.getMembers().stream()
                    .anyMatch(m -> m.getEmail().equalsIgnoreCase(member.getEmail()));
            if (!memberExists) {
                group.getMembers().add(member);
                return repo.save(group);
            } else {
                throw new IllegalArgumentException("User with email " + member.getEmail() + " is already a member.");
            }
        } else {
            throw new IllegalArgumentException("Group not found.");
        }
    }

    public StudyGroup removeMemberFromGroup(String groupId, String email) {
        Optional<StudyGroup> groupOpt = repo.findById(groupId);
        if (groupOpt.isPresent()) {
            StudyGroup group = groupOpt.get();
            group.setMembers(group.getMembers().stream()
                    .filter(m -> !m.getEmail().equalsIgnoreCase(email))
                    .toList());
            return repo.save(group);
        } else {
            throw new IllegalArgumentException("Group not found.");
        }
    }
}                       