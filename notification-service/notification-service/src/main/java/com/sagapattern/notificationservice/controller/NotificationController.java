package com.sagapattern.notificationservice.controller;


import com.sagapattern.notificationservice.entity.Notification;
import com.sagapattern.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Add a new Notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Notification")})
    private ResponseEntity<String> addNewNotification(@RequestBody Notification notification){
        return notificationService.addNewNotification(notification);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Notifications Added")})
    private ResponseEntity<?> getAllNotifications(){
        return notificationService.getAllNotifications();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Notification by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Notification details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Notification with specified Id found")})
    private ResponseEntity<?> getNotificationById(@PathVariable Long id){
        return notificationService.getNotificationById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Notification details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Notification details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Notification with specified Id found")})
    private ResponseEntity<String> updateNotification(@RequestBody Notification notification,@PathVariable Long id){
        return notificationService.updateNotification(notification,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Notification details updated successfully")})
    private ResponseEntity<String> deleteNotificationById(@PathVariable Long id){
        return notificationService.deleteNotificationById(id);
    }
}
