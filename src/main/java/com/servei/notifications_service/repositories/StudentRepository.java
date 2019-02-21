package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
