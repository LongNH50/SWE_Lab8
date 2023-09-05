package edu.mum.cs.cs425.studentmgmt;

import edu.mum.cs.cs425.studentmgmt.model.Classroom;
import edu.mum.cs.cs425.studentmgmt.model.Student;
import edu.mum.cs.cs425.studentmgmt.model.Transript;
import edu.mum.cs.cs425.studentmgmt.repository.ClassroomRepository;
import edu.mum.cs.cs425.studentmgmt.repository.StudentRepository;
import edu.mum.cs.cs425.studentmgmt.repository.TranscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@Transactional
public class MyStudentMgmtAppApplication implements CommandLineRunner {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public ClassroomRepository classroomRepository;

    @Autowired
    public TranscriptRepository transcriptRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyStudentMgmtAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse("2019/05/24", formatter);

        Student student1 = new Student("000-61-0001", "Anna", "Lyn", "Smith", 3.45, date);
        Student student2 = new Student("000-61-0002", "Aaron", "LLuynn", "Smith1", 2, date);
        Student student3 = new Student("000-61-0003", "Lambd", "LyLy", "Smith2", 5, date);
        Student student4 = new Student("000-61-0004", "Rambo", "Linh", "Smith3", 2, date);
        saveStudent(student1);

        Transript transript = new Transript("BS Computer Science", student1);
        saveTranscript(transript);


        Classroom classroom1 = new Classroom("McLaughlin building", "M105");
        Classroom classroom2 = new Classroom("McLaughlin building", "M106");
        Classroom classroom3 = new Classroom("McLaughlin building", "M107");
        Classroom classroom4 = new Classroom("McLaughlin building", "M108");

        List<Student> students = new ArrayList<>(Arrays.asList(student1, student2, student3, student4));
        List<Classroom> classroomList = new ArrayList<>(Arrays.asList(classroom1, classroom2, classroom3, classroom4));

        classroom1.setStudents(students);
        saveClassroom(classroom1);


    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Transript saveTranscript(Transript transript) {
        return transcriptRepository.save(transript);
    }

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }
}
