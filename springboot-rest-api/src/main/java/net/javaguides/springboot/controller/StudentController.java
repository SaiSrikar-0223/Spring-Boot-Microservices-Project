package net.javaguides.springboot.controller;


import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Kalyan",
                "Gudala"
        );
        //return new ResponseEntity<>(student,HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header","Kalyan")
                .body(student);
    }

    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students =new ArrayList<>();
        students.add(new Student(1,"Kalyan","Gudala"));
        students.add(new Student(2,"Sai Srikar","Gudala"));
        students.add(new Student(3,"Saritha","Gudala"));
        students.add(new Student(4,"Ravi", "Krishna"));
        return ResponseEntity.ok(students);
    }

    //SpringBoot Rest API with Path Variable
    // {id} ==> URI Template Variable
    //http://localhost:8080/students/1/kalyan/Gudala
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        //return new Student(studentId,firstName,lastName);
        Student student = new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //SpringBoot Rest API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Kalyan&lastName=gudala
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        //return new Student(id,firstName,lastName);
        Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot Rest API that handles http POST request --> Creating new resource
    //@PostMapping and @RequestBody
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //Spring Boot Rest API that handles HTTP PUT Request --> Updating existing resource
    @PutMapping("{id}/update")
     public ResponseEntity<Student> updateStudent(@PathVariable("id") int studentId,
                                  @RequestBody Student student){
         System.out.println(student.getFirstName());
         System.out.println(student.getLastName());
         //return student;
         return ResponseEntity.ok(student);
     }

    //Spring Boot Rest API that handles HTTP DELETE Request ==> deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@ PathVariable("id") int studentId){
        //return "Student record is deleted successfully..!!";
        return ResponseEntity.ok("Student record is deleted successfully..!!");
    }
}



