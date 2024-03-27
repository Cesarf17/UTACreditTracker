package com.example.utacredittracker.calculators;

import com.example.utacredittracker.degreehandling.Degree_Class;
import com.example.utacredittracker.user.User;

public class Auto_Calculator
{
    public User user;

    public Auto_Calculator(User user)
    {
        this.user = user;
    }

    public double calculate()
    {
        double pointSum = 0.0;
        double hourSum = 0.0;
        for(Degree_Class course : user.getClassesTaken())
        {
            if(course.getStatus().equals("completed") && course.hasTaken())
            {
                char currGrade = course.getLetterGrade();
                double currHours = course.getCreditHours();
                double gradePoints = 0.0;

                switch (currGrade)
                {
                    case 'A':
                        gradePoints = 4.0 * currHours;
                        pointSum = pointSum + gradePoints;
                        hourSum = hourSum + currHours;
                        break;
                    case 'B':
                        gradePoints = 3.0 * currHours;
                        pointSum = pointSum + gradePoints;
                        hourSum = hourSum + currHours;
                        break;
                    case 'C':
                        gradePoints = 2.0 * currHours;
                        pointSum = pointSum + gradePoints;
                        hourSum = hourSum + currHours;
                        break;
                    case 'D':
                        gradePoints = currHours;
                        pointSum = pointSum + gradePoints;
                        hourSum = hourSum + currHours;
                        break;
                    default:
                        hourSum = hourSum + currHours;
                        break;
                }
            }


        }

        return pointSum / hourSum;
    }
}
