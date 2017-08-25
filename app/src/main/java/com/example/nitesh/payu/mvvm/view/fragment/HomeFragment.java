package com.example.nitesh.payu.mvvm.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.example.nitesh.payu.R;
import com.example.nitesh.payu.callbacks.OnItemClickListener;
import com.example.nitesh.payu.databinding.FragmentHomeBinding;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.mvvm.view.adapter.ProjectListAdapter;
import com.example.nitesh.payu.util.Constants;;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener ,
        Constants.ProjectListListener {

    private FragmentHomeBinding binding;
    private List<Project> projects = new ArrayList<>();
    private Constants.ProjectClickListener listener;
    private Constants.ProjectFilterListener filterListner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Constants.ProjectClickListener) context;
        filterListner = (Constants.ProjectFilterListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.searchBar.setOnQueryTextListener(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        projects = Project.listAll(Project.class);
        binding.recyclerView.setAdapter(new ProjectListAdapter(getActivity(), projects,
                new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, View view, int position) {
                listener.onProjectClicked((Project) o);
            }
        }, this));
        binding.sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(view.getContext(),getString(R.string.sort_msg));
            }
        });
        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(view.getContext(),getString(R.string.filter_msg));
            }
        });
        binding.emptyView.setVisibility(projects.size() == 0 ? View.GONE : View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newQuery) {
        filter(newQuery);
        return true;
    }

    private void filter(String query) {
        ((ProjectListAdapter) binding.recyclerView.getAdapter()).getFilter().filter(query);
    }

    @Override
    public void onSearchResult(int resultCount) {
        if (resultCount == 0)
            binding.emptyView.setVisibility(View.VISIBLE);
        else
            binding.emptyView.setVisibility(View.GONE);
    }

    public void showAlert(Context context, final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(message.contains("filtered")){
                    filterListner.onFilterClicked();
                    return;
                }
                projects.clear();
                List<Project> sortedList = Project.listAll(Project.class,"title");
                projects.addAll(sortedList);
                binding.recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        builder.create().show();
    }
}
