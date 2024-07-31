package run.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import run.api.Dao.StudentRepository;
import run.api.Model.Student;

import java.time.LocalDate;
import java.util.List;

@Service
@Component
public class StudentIMPL {
    @Autowired
    private StudentRepository studentRepository; // Assuming this is the intended dependency

    public Page<Student> FindAll(int PageNumber, int PageSize) {
        return studentRepository.findAll(PageRequest.of(PageNumber,PageSize)); // Use the injected studentRepository
    }

    public Student FindById(int id)
    {
        return studentRepository.findById(id).get();
    }

    public Student Create(Student obj)
    {
        return studentRepository.saveAndFlush(obj);
    }

    public Student Update(Student obj)
    {
        return studentRepository.saveAndFlush(obj);
    }

    public void Delete(int id)
    {
        studentRepository.deleteById(id);
    }

    public List<Student> Search(String fullName , String classname , String hometown , String major , String Gender , double minAvg, double maxAvg
            , int fromDate, int toDate
    )
    {
        return studentRepository.findStudents(fullName , classname , hometown , major , Gender , minAvg, maxAvg
                , fromDate , toDate
        );
    }

    public List<Student> getBirthDay()
    {
        return studentRepository.findStudentsCurrentMonth(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue());
    }
}
