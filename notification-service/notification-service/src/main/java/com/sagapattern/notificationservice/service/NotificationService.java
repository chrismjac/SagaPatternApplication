package com.sagapattern.notificationservice.service;
import com.sagapattern.notificationservice.entity.Notification;
import com.sagapattern.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public ResponseEntity<String> addNewNotification(Notification notification) {
        try {
            notificationRepository.save(notification);
            return new ResponseEntity<>("Notification Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new notification" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllNotifications() {
        List<Notification> allNotifications = notificationRepository.findAll();
        if (!allNotifications.isEmpty()) {
            return ResponseEntity.ok(allNotifications);
        }
        return new ResponseEntity<>("No Notifications Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getNotificationById(Long id) {
        Optional<Notification> notificationDetails = notificationRepository.findById(id);
        if (notificationDetails.isPresent()) {
            return ResponseEntity.ok(notificationDetails.get());
        }
        return new ResponseEntity<>("No notification details with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateNotification(Notification notification, Long id) {
        ResponseEntity<?> notificationDetails = getNotificationById(id);
        if (notificationDetails.getStatusCode().is2xxSuccessful()) {
            try {
                notificationRepository.save(notification);
                return new ResponseEntity<>("Notification Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating notification" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No notification with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteNotificationById(Long id) {
        notificationRepository.deleteById(id);
        return new ResponseEntity<>("Notification Details deleted successfully", HttpStatus.OK);
    }
}

