package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
public class InstructorServicesImplTest
{
    @Autowired
    IInstructorServices ins;
    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Instructor> listUsers = ins.retrieveAllInstructors();
        Assertions.assertEquals(0, listUsers.size());
    }
    @Test
    @Order(2)
    public void testAjout(Instructor i) {
        List<Instructor> listUsers = ins.retrieveAllInstructors();
        int count = listUsers.size();

        ins.addInstructor(i);

        Assertions.assertEquals(count, listUsers.size()+1);
    }

}
