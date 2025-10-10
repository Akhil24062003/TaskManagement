package com.SprintProject.TaskManagement.serviceImpl;

import com.SprintProject.TaskManagement.entity.Attachment;
import com.SprintProject.TaskManagement.repository.AttachmentRepository;
import com.SprintProject.TaskManagement.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment addAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment getAttachmentById(Integer id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment doesn't exist"));
    }

    @Override
    public Attachment updateAttachment(Integer id, Attachment attachment) {
        Attachment existing = getAttachmentById(id);
        existing.setFileName(attachment.getFileName());
        existing.setFileType(attachment.getFileType());
        existing.setFileUrl(attachment.getFileUrl());
        return attachmentRepository.save(existing);
    }

    @Override
    public void deleteAttachment(Integer id) {
        Attachment attachment = getAttachmentById(id);
        attachmentRepository.delete(attachment);
    }
}