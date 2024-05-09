package com.luv2code.springmvc;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradeDao;
import com.luv2code.springmvc.repository.MathGradeDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    @Value("${sql.scripts.create.student}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;




    @BeforeEach
    public void setupDatabase(){
        jdbc.execute(sqlAddStudent);

        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);


    }

    @Test
    public void createStudentService(){

        studentService.createStudent("Chad", "Darby", "chad.darby@luv2code_school.com");

        CollegeStudent student= studentDao.findByEmailAddress
                ("chad.darby@luv2code_school.com");

         assertEquals("chad.darby@luv2code_school.com", student.getEmailAddress(), "find by Email");
    }

    @Test
    public void isStudentNullCheck(){

        assertTrue(studentService.checkIfStudentIsNull(1),"Again");
        assertFalse(studentService.checkIfStudentIsNull(0),"Again");
    }

    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> deletedCollegeStudent=studentDao.findById(1);
        Iterable<MathGrade> deletedMathGrade=mathGradeDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> deletedScienceGrade=scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> deletedHistoryGrade=historyGradeDao.findGradeByStudentId(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Student should be present");
        assertTrue(deletedMathGrade.iterator().hasNext());
        assertTrue(deletedScienceGrade.iterator().hasNext());
        assertTrue(deletedHistoryGrade.iterator().hasNext());

        studentService.deleteStudent(1);

        deletedCollegeStudent=studentDao.findById(1);
        Iterable<MathGrade> mathGrade=mathGradeDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrade=scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrade=historyGradeDao.findGradeByStudentId(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Student should not be true.");
        assertFalse(mathGrade.iterator().hasNext());
        assertFalse(scienceGrade.iterator().hasNext());
        assertFalse(historyGrade.iterator().hasNext());
    }

    @Sql("/insertData.sql")
    @Test
    public void getGradeBookService(){
        Iterable<CollegeStudent> iterableCollegeStudents= studentService.getGradeBook();

        List<CollegeStudent> collegeStudents= new ArrayList<>();

        for(CollegeStudent collegeStudent: iterableCollegeStudents){
            collegeStudents.add(collegeStudent);
        }

        assertEquals(5, collegeStudents.size(), "Size should be the same");
    }

    @Test
    public void createGradeService(){

        assertTrue(studentService.createGrade(80.5, 1, "math"));
        assertTrue(studentService.createGrade(80.5, 1, "science"));
        assertTrue(studentService.createGrade(80.5, 1, "history"));

        Iterable<MathGrade> mathGrades=mathGradeDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades=scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades=historyGradeDao.findGradeByStudentId(1);

        assertTrue(((Collection<MathGrade>) mathGrades).size()==2,"Student should have math grades");
        assertTrue(((Collection<ScienceGrade>) scienceGrades).size()==2, "Student should have science grades");
        assertTrue(((Collection<HistoryGrade>) historyGrades).size()==2, "Student should have science grades");
    }

    @Test
    public void createGradeServiceReturnFalse(){
        assertFalse(studentService.createGrade(105, 1, "math"));
        assertFalse(studentService.createGrade(-5, 1, "math"));
        assertFalse(studentService.createGrade(85.8, 4, "math"));
        assertFalse(studentService.createGrade(85.8, 1, "literature"));
    }

    @Test
    public void deleteGradeService(){
        assertEquals(1, studentService.deleteGrade(1,"math"),
                "Returns student id after delete");

        assertEquals(1, studentService.deleteGrade(1,"science"),
                "Returns student id after delete");

        assertEquals(1, studentService.deleteGrade(1,"history"),
                "Returns student id after delete");

    }

    @Test
    public void deleteGradeServiceReturnStudentIdZero(){
        assertEquals(0, studentService.deleteGrade(3, "math"));
        assertEquals(0, studentService.deleteGrade(3, "science"));
        assertEquals(0, studentService.deleteGrade(3, "history"));
        assertEquals(0, studentService.deleteGrade(1, "filipino"));
        assertEquals(0, studentService.deleteGrade(1, "literature"));
        assertEquals(0, studentService.deleteGrade(1, "cooking"));

    }

    @Test
    public void studentInformation(){

        GradebookCollegeStudent gradebookCollegeStudent =
                studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent, "student should be not null");
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Eric", gradebookCollegeStudent.getFirstname());
        assertEquals("Roby", gradebookCollegeStudent.getLastname());
        assertEquals("eric.roby@luv2code_school.com",
                gradebookCollegeStudent.getEmailAddress());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size()==1);

    }

    @Test
    public void studentInformationServiceReturnNull(){
        GradebookCollegeStudent gradebookCollegeStudent =
                studentService.studentInformation(0);

        assertNull(gradebookCollegeStudent);
    }


    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }









}
