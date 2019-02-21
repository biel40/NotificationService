package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Absence;
import org.springframework.data.repository.CrudRepository;

public interface AbsenceRepository extends CrudRepository<Absence, Long> {
}
