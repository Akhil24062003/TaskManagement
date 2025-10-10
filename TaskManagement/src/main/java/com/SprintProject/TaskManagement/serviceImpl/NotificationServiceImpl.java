package com.SprintProject.TaskManagement.serviceImpl;

import com.SprintProject.TaskManagement.entity.Notification;
import com.SprintProject.TaskManagement.repository.NotificationRepository;
import com.SprintProject.TaskManagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Integer id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification doesn't exist"));
    }

    @Override
    public Notification updateNotification(Integer id, Notification notification) {
        Notification existing = getNotificationById(id);
        existing.setMessage(notification.getMessage());
        existing.setRead(notification.isRead());
        return notificationRepository.save(existing);
    }

    @Override
    public void deleteNotification(Integer id) {
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }
}