package com.firstfewlines.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="student_mst", schema = "public")
public class Student {

    @Transient
    static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    int rollNo;
    String name;
    String stream;
    Date dateOfBirth;
    int totalMarks;

    @Id
    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Transient
    public String getDateOfBirthFormatted() {
        return df.format(dateOfBirth);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
