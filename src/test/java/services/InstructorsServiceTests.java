package services;

import com.example.school.SchoolApplication;
import com.example.school.dto.Instructor;
import com.example.school.entities.InstructorEntity;
import com.example.school.repositories.InstructorRepository;
import com.example.school.services.InstructorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class InstructorsServiceTests {

    @Autowired
    private InstructorService instructorsService;



    @MockBean
    private InstructorRepository instructorsRepository;

    @Test
    public void findAll(){
        List<InstructorEntity> entityList = new ArrayList<>();
        entityList.add(createNew("Abd"));
        entityList.add(createNew("Ali"));
        when(instructorsRepository.findAll()).thenReturn(entityList);
        List<Instructor> instructors = instructorsService.findAll();
        assertEquals(instructors.get(0).getFirstMidName(), "Abd");
        assertEquals(instructors.get(1).getFirstMidName(), "Ali");
    }

    private InstructorEntity createNew(String name){
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setFirstMidName(name);
        return instructorEntity;
    }


}
