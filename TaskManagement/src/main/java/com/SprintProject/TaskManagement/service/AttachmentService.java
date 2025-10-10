package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.Attachment;

import java.util.List;

public interface AttachmentService {
    Attachment addAttachment(Attachment attachment);
    List<Attachment> getAllAttachments();
    Attachment getAttachmentById(Integer id);
    Attachment updateAttachment(Integer id, Attachment attachment);
    void deleteAttachment(Integer id);
}