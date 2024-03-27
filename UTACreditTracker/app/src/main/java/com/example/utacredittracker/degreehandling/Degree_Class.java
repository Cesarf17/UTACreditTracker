package com.example.utacredittracker.degreehandling;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Degree_Class
{
    private String courseType;
    private String courseName;
    private int courseNumber;
    private double creditHours;
    private int numberOfPrerequisites;
    private ArrayList<String> prerequisites = new ArrayList<>();
    private char letterGrade;
    private Boolean beenTaken;
    private String status;

    public Degree_Class(String courseType, int courseNumber, String courseName, double creditHours, int numberOfPrerequisites, ArrayList<String> prerequisites)
    {
        this(courseType, courseNumber, courseName, creditHours, numberOfPrerequisites, prerequisites, 'I', false, "planned");
    }

    public Degree_Class(String courseType, int courseNumber, String courseName, double creditHours, int numberOfPrerequisites, ArrayList<String> prerequisites, char letterGrade, Boolean beenTaken, String status)
    {
        this.courseType = courseType;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.numberOfPrerequisites = numberOfPrerequisites;
        this.prerequisites = prerequisites;
        this.letterGrade = letterGrade;
        this.beenTaken = beenTaken;
        this.status = status;
    }

    public String getCourseType() {return courseType;}

    public String getCourseName() {return courseName;}

    public int getCourseNumber() {return courseNumber;}

    public double getCreditHours() {return creditHours;}

    public int getNumberOfPrerequisites() {return numberOfPrerequisites;}

    public ArrayList<String> getPrerequisites() {return prerequisites;}

    public char getLetterGrade() {return letterGrade;}

    public Boolean hasTaken() {return beenTaken;}

    public String getStatus() {return status;}


    @Override
    public String toString()
    {
        if(numberOfPrerequisites < 1)
        {
            return "\nCourse Type: " +courseType + "\nCourse Number: " +
                    courseNumber + "\nCourseName: " + courseName + "\nCourse Hours: " +
                    creditHours + "\nLetter Grade: " + letterGrade + "\nTaken: " +
                    beenTaken + "\nStatus: " + status + "\nNumber of Prerequisites: " +
                    numberOfPrerequisites + "\n";
        }
        return "\nCourse Type: " +courseType + "\nCourse Number: " +
                courseNumber + "\nCourseName: " + courseName + "\nCourse Hours: " +
                creditHours + "\nLetter Grade: " + letterGrade + "\nTaken: " +
                beenTaken + "\nStatus: " + status + "\nNumber of Prerequisites: " +
                numberOfPrerequisites + "\nPrerequisites: " + prerequisites + "\n";
    }
    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }

    public void setHasTaken(boolean a){
        this.beenTaken = true;
    }
}
