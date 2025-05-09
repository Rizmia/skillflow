  package backend.backend;

 public class GroupControllerTest {
    
 }















// package com.skillflow.skillshare;

// import com.skillflow.skillshare.controller.GroupController;
// import com.skillflow.skillshare.model.StudyGroup;
// import com.skillflow.skillshare.service.StudyGroupService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.Collections;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// @WebMvcTest(GroupController.class)
// public class GroupControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private StudyGroupService service;

//     @Test
//     void testGetAllGroups() throws Exception {
//         StudyGroup group = new StudyGroup("1", "Test Group", "Description", "Category", "user@example.com", "2025-05-08", null);
//         when(service.getAllGroups()).thenReturn(Collections.singletonList(group));

//         mockMvc.perform(get("/api/groups")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].title").value("Test Group"));
//     }
// }