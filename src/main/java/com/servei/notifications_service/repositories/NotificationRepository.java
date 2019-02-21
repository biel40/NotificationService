package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
