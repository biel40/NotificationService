package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
