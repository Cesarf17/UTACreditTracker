package com.example.utacredittracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.utacredittracker.R;
import com.example.utacredittracker.databinding.FragmentHomeBinding;
import com.example.utacredittracker.degreehandling.Degree_Plan;
import com.example.utacredittracker.ui.shared.SharedViewModel;
import com.example.utacredittracker.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static User userProfile = new User();
    public Degree_Plan userDegreePlan;
    private String degreeFile;

    private SharedViewModel sharedViewModel;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        TextView degreeSelection = root.findViewById(R.id.display_degree);

        Button selectDegreeButton = root.findViewById(R.id.select_degree_button);
        selectDegreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.swe_selection)
                        {
                            InputStream inputStream = v.getResources().openRawResource(R.raw.swe);
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                            try {
                                userDegreePlan = new Degree_Plan(br);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            //System.out.println(userDegreePlan);
                            selectDegreeButton.setVisibility(View.INVISIBLE);
                            degreeSelection.setText(userDegreePlan.getDegreePlanName());
                            sharedViewModel.setSelectedDegree(userDegreePlan.getDegreePlanName());
                            sharedViewModel.setUserDegreePlan(userDegreePlan);

                            return true;
                        }
                        else if (item.getItemId() == R.id.cse_selection)
                        {
                            //degreeFile = new File("com/example/credittrackeruitest/degreeplans/swe.txt");
                            //userProfile = new User(new BufferedReader(new FileReader(degreeFile)));
                            return true;
                        }
                        else if(item.getItemId() == R.id.ce_selection)
                        {
                            InputStream inputStream = v.getResources().openRawResource(R.raw.ce);
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                            try {
                                userDegreePlan = new Degree_Plan(br);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            //System.out.println(userDegreePlan);
                            selectDegreeButton.setVisibility(View.INVISIBLE);
                            degreeSelection.setText(userDegreePlan.getDegreePlanName());
                            sharedViewModel.setSelectedDegree(userDegreePlan.getDegreePlanName());
                            sharedViewModel.setUserDegreePlan(userDegreePlan);
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return root;
    }

    public static User getUserProfile() {return userProfile;}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}