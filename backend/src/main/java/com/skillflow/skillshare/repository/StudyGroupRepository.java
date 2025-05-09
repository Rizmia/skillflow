// package com.skillflow.skillshare.repository;

// import com.skillflow.skillshare.model.StudyGroup;
// import org.springframework.data.mongodb.repository.MongoRepository;
// import java.util.List;

// public interface StudyGroupRepository extends MongoRepository<StudyGroup, String> {
//     List<StudyGroup> findByCreatedBy(String createdBy);
// }

package com.skillflow.skillshare.repository;

import com.skillflow.skillshare.model.StudyGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StudyGroupRepository extends MongoRepository<StudyGroup, String> {
    List<StudyGroup> findByCreatedBy(String createdBy);
}