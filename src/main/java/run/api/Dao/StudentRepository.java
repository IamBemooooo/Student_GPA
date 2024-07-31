package run.api.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import run.api.Model.Student;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE " +
            "(LOWER(s.fullname) LIKE LOWER(CONCAT('%', :fullname, '%')) OR :fullname IS NULL) and " +
            "(LOWER(s.classname) LIKE LOWER(CONCAT('%', :classname, '%')) OR :classname IS NULL) and " +
            "(LOWER(s.hometown) LIKE LOWER(CONCAT('%', :hometown, '%')) OR :hometown IS NULL) and " +
            "(LOWER(s.major) LIKE LOWER(CONCAT('%', :major, '%')) OR :major IS NULL) and " +
            "(LOWER(s.gender) LIKE LOWER(CONCAT('%', :gender, '%')) OR :gender IS NULL) and " +
            "s.avgmark between :minAvg and :maxAvg and " +
            "day(s.birthday) between  :fromDate and :toDate"
    )


    List<Student> findStudents(@Param("fullname") String fullname,
                               @Param("classname") String classname,
                               @Param("hometown") String hometown,
                               @Param("major") String major,
                               @Param("gender") String gender,
                               @Param("minAvg") double minAvg,
                               @Param("maxAvg") double maxAvg,
                               @Param("fromDate") int fromDate,
                               @Param("toDate") int toDate
                               );

    @Query("SELECT s FROM Student s WHERE " +
            "day(s.birthday) = :currentday and" +
            " month(s.birthday) = :currentmonth")
    List<Student> findStudentsCurrentMonth(@Param("currentday") int currentday,@Param("currentmonth") int currentmonth);
}
