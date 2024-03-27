package com.example.utacredittracker.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.utacredittracker.R;
import com.example.utacredittracker.degreehandling.Degree_Class;
import com.example.utacredittracker.ui.shared.SharedViewModel;

public class ClassPicked extends AppCompatActivity {

    private SharedViewModel sharedViewModel;
    private Degree_Class selectedClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_picked);

        // Initialize SharedViewModel
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        // Assume you have received the selected class from the previous activity
        selectedClass = getIntent().getParcelableExtra("selectedClass");
        Log.d("SELECTED CLASS",""+selectedClass);

        // Initialize the Spinner with letter grades
        Spinner gradeSpinner = findViewById(R.id.gradeSpinner);
        initializeGradeSpinner(gradeSpinner);

        // Initialize the Confirm button
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmButtonClick();
            }
        });

        sharedViewModel.getSelectedClass().observe(this, new Observer<Degree_Class>() {
            @Override
            public void onChanged(Degree_Class selectedClass) {
                if (selectedClass != null) {
                    Log.d("ClassPicked", "Selected class: " + selectedClass.getCourseName());
                }
            }
        });

        // Observe changes in the selected class
        sharedViewModel.getSelectedClass().observe(this, new Observer<Degree_Class>() {
            @Override
            public void onChanged(Degree_Class degreeClass) {
                // Handle changes to the selected class, if needed
                Log.d("ClassPicked", "Selected class changed: " + degreeClass.getCourseName());
            }
        });


        // You can use other views and UI elements to display information about the selectedClass
    }

    private void initializeGradeSpinner(Spinner gradeSpinner) {
        if (selectedClass != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.letter_grades, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            gradeSpinner.setAdapter(adapter);

            gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Handle the selected item (grade) here
                    String selectedGrade = parentView.getItemAtPosition(position).toString();
                    Toast.makeText(ClassPicked.this, "Selected Grade: " + selectedGrade, Toast.LENGTH_SHORT).show();

                    // Update the selected class with the chosen grade
                    selectedClass.setLetterGrade(selectedGrade.charAt(0));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }
            });
        }
    }

    public void onConfirmButtonClick() {
        if (selectedClass != null) {
            selectedClass.setHasTaken(true);
            Toast.makeText(this, "Grade confirmed and class status updated", Toast.LENGTH_SHORT).show();

            // Update the sharedViewModel with the modified class
            sharedViewModel.setSelectedClass(selectedClass);


            finish();
        }
    }
}
