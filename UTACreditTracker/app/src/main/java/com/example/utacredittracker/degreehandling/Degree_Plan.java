package com.example.utacredittracker.degreehandling;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;

public class Degree_Plan
{
    public String degreePlanName = "";
    ArrayList<Degree_Class> degreeClasses = new ArrayList<>();
    int numberOfCourses;

    public Degree_Plan(){}

    public Degree_Plan(BufferedReader in) throws IOException
    {
        degreePlanName = in.readLine();
        numberOfCourses = Integer.parseInt(in.readLine());

        for (int i = 0; i < numberOfCourses; i++)
        {
            final String courseType = in.readLine();
            final int courseNumber = Integer.parseInt(in.readLine());
            final String courseName = in.readLine();
            final double creditHours = Double.parseDouble(in.readLine());
            final int numberOfPrerequisites = Integer.parseInt(in.readLine());
            final ArrayList<String> prerequisites = new ArrayList<>();

            if(numberOfPrerequisites > 0)
            {
                for(int j = 0; j < numberOfPrerequisites; j++)
                {
                    prerequisites.add(in.readLine());
                }
            }
            degreeClasses.add(new Degree_Class(courseType, courseNumber, courseName,
                    creditHours, numberOfPrerequisites, prerequisites));
        }
    }

    public String getDegreePlanName() {return degreePlanName;}

    public int getNumberOfCourses() {return numberOfCourses;}

    public ArrayList<Degree_Class> getDegreeClasses() {return degreeClasses;}

    @Override
    public String toString(){
        return "ENTIRE DEGREE PLAN FOR " + getDegreePlanName() + ".\n\n " +
                getDegreeClasses();
    }
}
