package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Teacher;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByName(String name);
    Teacher findById(int id);

    @Query("MATCH (teacher:Teacher)-[recieve:RECEIVE]-(notification:Notification{itWasSent:false})-[belongs_to:BELONGS_TO]-(student:Student)-[has:HAS]-(absence:Absence)\n" +
            "return teacher,recieve,notification,belongs_to,student,has,absence")
    List<Teacher> findTeachersWithAbsences();

    Teacher findByMail(String email);
}
