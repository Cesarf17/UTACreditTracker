package com.example.utacredittracker.user;

import com.example.utacredittracker.degreehandling.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User
{
    public Degree_Plan userDegree = new Degree_Plan();
    private int numberOfClassesTaken;
    private ArrayList<Degree_Class> classesTaken = new ArrayList<>();

    public User() {}

    public User(BufferedReader in) throws IOException
    {
        userDegree.degreePlanName = in.readLine();
        numberOfClassesTaken = Integer.parseInt(in.readLine());

        for (int i = 0; i < numberOfClassesTaken; i++)
        {
            String courseType = in.readLine();
            int courseNumber = Integer.parseInt(in.readLine());
            String courseName = in.readLine();
            double creditHours = Double.parseDouble(in.readLine());
            int numberOfPrerequisites = Integer.parseInt(in.readLine());
            ArrayList<String> prerequisites = new ArrayList<>();

            for(int j = 0; j < numberOfPrerequisites; j++)
            {
                prerequisites.add(in.readLine());
            }

            char letterGrade = in.readLine().charAt(0);
            boolean beenTaken = Boolean.parseBoolean(in.readLine());
            String status = in.readLine();

            classesTaken.add(new Degree_Class(courseType, courseNumber, courseName,
                    creditHours, numberOfPrerequisites, prerequisites, letterGrade, beenTaken, status));
        }
    }

    public void save(BufferedWriter out) throws IOException
    {
        out.write(userDegree.getDegreePlanName() + "\n");
        out.write(numberOfClassesTaken + "\n");

        for(int i = 0; i < numberOfClassesTaken; i++)
        {
            out.write(classesTaken.get(i).getCourseType() + "\n");
            out.write(classesTaken.get(i).getCourseNumber() + "\n");
            out.write(classesTaken.get(i).getCourseName() + "\n");
            out.write(classesTaken.get(i).getCreditHours() + "\n");
            out.write(classesTaken.get(i).getNumberOfPrerequisites() + "\n");

            if(classesTaken.get(i).getNumberOfPrerequisites() > 0)
            {
                for(int j = 0; j < classesTaken.get(i).getNumberOfPrerequisites(); j++)
                {
                    out.write(classesTaken.get(i).getPrerequisites().get(j));
                }
            }

            out.write(classesTaken.get(i).getLetterGrade() + "\n");
            out.write(classesTaken.get(i).hasTaken() + "\n");
            out.write(classesTaken.get(i).getStatus() + "\n");
        }
    }

    public void addClass(Degree_Class newClass) {classesTaken.add(newClass);}

    public String getUserDegreeName() {return userDegree.degreePlanName;}

    public ArrayList<Degree_Class> getClassesTaken() {return classesTaken;}
}
