package com.example.nitesh.payu.mvvm.view.fragment;


import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nitesh.payu.R;
import com.example.nitesh.payu.callbacks.OnItemClickListener;
import com.example.nitesh.payu.databinding.FragmentMenuBinding;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.mvvm.view.adapter.ProjectListAdapter;
import com.example.nitesh.payu.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LovedProjectsFragment extends Fragment {

    private FragmentMenuBinding binding;
    private Constants.ProjectClickListener listener;
    private List<Project> projects = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Constants.ProjectClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //projects = Project.listAll(Project.class, "title");
        projects.addAll(Project.find(Project.class, "num_backers > ? and percentage_funded >= ?", "30000", "60"));

        binding.recyclerView.setAdapter(new ProjectListAdapter(getActivity(), projects,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, View view, int position) {
                        listener.onProjectClicked((Project) o, Constants.ProjectType.LOVELY);
                    }
                }, null));
    }
}
