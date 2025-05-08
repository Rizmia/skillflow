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

    public List<StudyGroup> getAllGroups() {
        return repo.findAll();
    }

    public StudyGroup createGroup(StudyGroup group) {
        return repo.save(group);
    }

    public Optional<StudyGroup> getGroupById(String id) {
        return repo.findById(id);
    }

    public boolean canEditOrDelete(String groupId, String userId, boolean isAdmin) {
        Optional<StudyGroup> group = repo.findById(groupId);
        return group.isPresent() && (group.get().getCreatedBy().equals(userId) || isAdmin);
    }

    public StudyGroup updateGroup(String id, StudyGroup updatedGroup) {
        updatedGroup.setId(id);
        return repo.save(updatedGroup);
    }

    public void deleteGroup(String id) {
        repo.deleteById(id);
    }
}
