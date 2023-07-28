import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import polimi.it.smsSofwareEng.controller.ResultController;
import polimi.it.smsSofwareEng.model.Student;
import polimi.it.smsSofwareEng.model.Teacher;
import polimi.it.smsSofwareEng.model.UserRole;
import polimi.it.smsSofwareEng.repository.TeacherRepository;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(MockitoJUnitRunner.class)
public class ResultControlerTest {
    private MockMvc mockMvc;
    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectwriter=objectMapper.writer();
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private ResultController resultController;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(resultController).build();}
    List<Student> emptyList = new ArrayList<>();
    Teacher teacher=new Teacher(1L,"Elisabetta","Di Nitto","admin","admin",emptyList, UserRole.TEACHER);
    @Test
    public void loginTest() throws Exception{

        Mockito.when(teacherRepository
                .findByUserNameAndPassword(teacher.getUserName(), teacher.getPassword())).thenReturn(teacher);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());}}
