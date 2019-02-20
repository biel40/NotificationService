package com.servei.notifications_service.modules;

import com.servei.notifications_service.nodes.Absence;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tanin on 20/02/2019.
 */
public interface AbsencesRepository extends CrudRepository<Absence, Long>{
    Absence findAbsenceByAssignatura(String assignatura);
}
