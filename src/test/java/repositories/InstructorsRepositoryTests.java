package repositories;

import com.example.school.SchoolApplication;
import com.example.school.entities.InstructorEntity;
import com.example.school.repositories.InstructorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SchoolApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class InstructorsRepositoryTests {
    @Autowired
    private InstructorRepository instructorsRepository;



    @Test
    public void createInstructor() {
        InstructorEntity entity = new InstructorEntity();
        entity.setFirstMidName("suha");
        entity.setLastName("Taher");
        instructorsRepository.save(entity);
        assertEquals(instructorsRepository.count(), 1);
    }

}
