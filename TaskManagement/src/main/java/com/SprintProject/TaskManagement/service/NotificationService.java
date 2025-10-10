package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.Notification;

import java.util.List;

public interface NotificationService {
    Notification addNotification(Notification notification);
    List<Notification> getAllNotifications();
    Notification getNotificationById(Integer id);
    Notification updateNotification(Integer id, Notification notification);
    void deleteNotification(Integer id);
}