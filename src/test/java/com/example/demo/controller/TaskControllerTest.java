//
//
//import com.example.demo.controller.TaskController;
//import com.example.demo.model.Task;
//import com.example.demo.service.TaskService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.springframework.http.RequestEntity.post;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(TaskController.class)
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//public class TaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TaskService taskService;
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void findAll_shouldReturnTaskList_whenAdmin() throws Exception {
//        List<Task> tasks = List.of(new Task(1L, "Task 1", "no", 1L), new Task(2L, "Task 2", "no", 1L));
//        Mockito.when(taskService.findAll()).thenReturn(tasks);
//
//        mockMvc.perform(get("/task").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void findAll_shouldReturnForbidden_whenUser() throws Exception {
//        mockMvc.perform(get("/task").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void findById_shouldReturnTask_whenUser() throws Exception {
//        Task task = new Task(1L, "Task 1", "no", 1L);
//        Mockito.when(taskService.findById(1L)).thenReturn(task);
//
//        mockMvc.perform(get("/task/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Task 1"));
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void save_shouldReturnSavedTask_whenAdmin() throws Exception {
//        Task task = new Task(1L, "Task 1", "no", 1L);
//        Mockito.when(taskService.save(Mockito.any(Task.class))).thenReturn(task);
//
//        mockMvc.perform(post("/task")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(task)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("New Task"));
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void save_shouldReturnForbidden_whenUser() throws Exception {
//        mockMvc.perform(post("/task")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{"id":1,"name":"New Task"}"))
//                .andExpect(status().isForbidden());
//    }
//}
