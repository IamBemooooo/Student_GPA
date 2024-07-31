package run.api.controller;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import run.api.Exception.StudentException;
import run.api.Model.Student;
import run.api.Service.StudentIMPL;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Students")
public class StudentController {

    @Autowired
    private StudentIMPL Ser;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);


    @GetMapping("/getall")
    public Page<Student> GetAll(@RequestParam int PageNumber, @RequestParam int PageSize)
    {
        try {
            Page<Student> rs = Ser.FindAll(PageNumber,PageSize);
            if(rs!=null)
            {
                logger.info(rs.getContent().toString());
                return rs;
            }
            return null;
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/add")
    public Student add(@RequestBody Student student) throws StudentException
    {
        boolean check = true;
        if(student.getFullname() == null || student.getFullname().isEmpty())
        {
            check = false;
            throw new StudentException("fullname is empty");
        }
        if (student.getBirthday() == null) {
            check = false;
            throw new StudentException("Student's birthday cannot be null");
        }
        if (student.getGender() == null || student.getGender().isEmpty()) {
            check = false;
            throw new StudentException("Student's gender cannot be null or empty");
        }
        if (student.getMajor() == null || student.getMajor().isEmpty()) {
            check = false;
            throw new StudentException("Student's major cannot be null or empty");
        }
        if (student.getHometown() == null || student.getHometown().isEmpty()) {
            check = false;
            throw new StudentException("Student's hometown cannot be null or empty");
        }
        if (student.getClassname() == null || student.getClassname().isEmpty()) {
            check = false;
            throw new StudentException("Student's class name cannot be null or empty");
        }
        if(student.getAvgmark() > 10 || student.getAvgmark() < 0 )
        {
            check = false;
            throw new StudentException("Student's Avgmark must be between 0 and 10");
        }
        if(check)
        {
            return Ser.Create(student);
        }
        return null;
    }

    @GetMapping("/search")
    public List<Student> Search(@RequestParam  String Name,
                                @RequestParam  String classname,
                                @RequestParam  String hometown,
                                @RequestParam  String major,
                                @RequestParam  String gender,
                                @RequestParam  String minAvg,
                                @RequestParam  String maxAvg,
                                @RequestParam  String fromDate,
                                @RequestParam  String toDate
                                )
    {
        List<Student> students = Ser.Search(Name,
                classname,
                hometown,
                major,
                (String) gender,
                (minAvg.matches("-?\\d+(\\.\\d+)?"))? Double.parseDouble(minAvg) : 0 ,
                (maxAvg.matches("-?\\d+(\\.\\d+)?"))? Double.parseDouble(maxAvg) : 0,
                (fromDate.matches("-?\\d+(\\.\\d+)?"))? Integer.parseInt(fromDate) : 0,
                (toDate.matches("-?\\d+(\\.\\d+)?"))? Integer.parseInt(toDate) : 0
        );
        return students;
    }

    @GetMapping("/birthday")
    public List<Student> getbirthday() throws StudentException
    {
       List<Student> lst = Ser.getBirthDay();
       if (lst == null)
       {
           throw new StudentException("list is empty");
       }
       logger.info(lst.toString());
       return lst;
    }

    @PutMapping("/put/{id}")
    public Student Update(@PathVariable int id, @RequestBody Student student) throws StudentException
    {
        Student obj = Ser.FindById(id);
        if (obj == null)
        {
            throw new StudentException("Student not found");
        }
        obj.setFullname(student.getFullname());
        obj.setBirthday(student.getBirthday());
        obj.setClassname(student.getClassname());
        obj.setGender(student.getGender());
        obj.setMajor(student.getMajor());
        obj.setHometown(student.getHometown());
        obj.setAvgmark(student.getAvgmark());
        try{
            logger.info(obj.toString());
            return Ser.Update(obj);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable int id) throws StudentException
    {
        try {
            Ser.Delete(id);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
    }


}
