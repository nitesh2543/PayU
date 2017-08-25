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
import com.example.nitesh.payu.databinding.FragmentFilterResultBinding;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.mvvm.view.adapter.ProjectListAdapter;
import com.example.nitesh.payu.util.Constants;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterResultFragment extends Fragment {

    private FragmentFilterResultBinding binding;
    private Constants.ProjectClickListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Constants.ProjectClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_result, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Project> projects = Project.listAll(Project.class, "num_backers");
        binding.recyclerView.setAdapter(new ProjectListAdapter(getActivity(), projects,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, View view, int position) {
                        listener.onProjectClicked((Project) o);
                    }
                }, null));

        binding.emptyView.setVisibility(projects.size()==0?View.VISIBLE:View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }
}
