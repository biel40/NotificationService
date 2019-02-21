package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByName(String name);
}
