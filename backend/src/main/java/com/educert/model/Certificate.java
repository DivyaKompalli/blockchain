package com.educert.model;

import jakarta.persistence.*;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String course;
    
    @Column(nullable = false, unique = true)
    private String hash;
    
    private String topicId;
    private String messageTimestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }

    public String getTopicId() { return topicId; }
    public void setTopicId(String topicId) { this.topicId = topicId; }

    public String getMessageTimestamp() { return messageTimestamp; }
    public void setMessageTimestamp(String messageTimestamp) { this.messageTimestamp = messageTimestamp; }
}
