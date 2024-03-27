// DashboardFragment.java
package com.example.utacredittracker.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.utacredittracker.R;
import com.example.utacredittracker.degreehandling.Degree_Class;
import com.example.utacredittracker.ui.shared.SharedViewModel;
import com.example.utacredittracker.degreehandling.Degree_Plan;
import com.example.utacredittracker.ui.dashboard.ClassPicked;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Observe changes in the userDegreePlan
        sharedViewModel.getUserDegreePlan().observe(getViewLifecycleOwner(), new Observer<Degree_Plan>() {
            @Override
            public void onChanged(Degree_Plan userDegreePlan) {
                if (userDegreePlan != null) {
                    updateUI(userDegreePlan);
                } else {
                    Log.d("Dashboard","Error");
                }
            }
        });

        return root;
    }

    private void updateUI(Degree_Plan userDegreePlan) {
        ListView classListView = requireView().findViewById(R.id.classListView);

        ArrayList<Degree_Class> allCourses = new ArrayList<>();
        ArrayList<String> classList = new ArrayList<>();

        for (Degree_Class degreeClass : userDegreePlan.getDegreeClasses()) {
            String classInfo = String.format("Course Type: %s\n Course Number: %d\nCourse Name: %s\n Hours: %.1f\n",
                    degreeClass.getCourseType(),
                    degreeClass.getCourseNumber(),
                    degreeClass.getCourseName(),
                    degreeClass.getCreditHours());

            classList.add(classInfo);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, classList);

        classListView.setAdapter(adapter);

        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Degree_Class selectedClass = userDegreePlan.getDegreeClasses().get(position);
                    sharedViewModel.setSelectedClass(selectedClass);
                    startActivity(new Intent(requireContext(), ClassPicked.class));
                    Log.d("IN DASHBOARD","Class: "+sharedViewModel.getSelectedClass().getValue());
                }
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
