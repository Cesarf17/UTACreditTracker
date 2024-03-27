// SharedViewModel.java
package com.example.utacredittracker.ui.shared;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.utacredittracker.degreehandling.Degree_Class;
import com.example.utacredittracker.degreehandling.Degree_Plan;


public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedDegree = new MutableLiveData<>();
    private final MutableLiveData<Degree_Plan> userDegreePlan = new MutableLiveData<>();
    private final MutableLiveData<Degree_Class> selectedClass = new MutableLiveData<>();

    public void setSelectedDegree(String degree) {
        selectedDegree.setValue(degree);
    }

    public LiveData<String> getSelectedDegree() {
        return selectedDegree;
    }

    public void setUserDegreePlan(Degree_Plan degreePlan) {
        userDegreePlan.setValue(degreePlan);
    }

    public LiveData<Degree_Plan> getUserDegreePlan() {
        return userDegreePlan;
    }

    public void setSelectedClass(Degree_Class degreeClass) {
        selectedClass.setValue(degreeClass);
    }

    public LiveData<Degree_Class> getSelectedClass() {
        return selectedClass;
    }
}
