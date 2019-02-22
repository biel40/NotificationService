package com.servei.notifications_service.repositories;

import com.servei.notifications_service.nodes.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Optional<Notification> findById(Long id);
}
