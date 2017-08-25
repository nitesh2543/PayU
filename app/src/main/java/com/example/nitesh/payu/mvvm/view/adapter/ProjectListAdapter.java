package com.example.nitesh.payu.mvvm.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.nitesh.payu.BR;
import com.example.nitesh.payu.R;
import com.example.nitesh.payu.callbacks.OnItemClickListener;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.mvvm.viewmodel.ProjectViewModel;
import com.example.nitesh.payu.util.Constants;
import com.example.nitesh.payu.util.FooterViewHolder;
import com.example.nitesh.payu.util.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesh on 13/8/17.
 */

public class ProjectListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private final LayoutInflater layoutInflater;
    private final List<Project> orignalList;
    private final OnItemClickListener onItemClickListener;
    private final ProjectFilter filter;
    private List<Project> filteredList;
    private final Constants.ProjectListListener projectListListener;



    public ProjectListAdapter(Context context, List<Project> projects,
                              OnItemClickListener onItemClickListener , Constants.ProjectListListener listener) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        orignalList = projects;
        this.onItemClickListener = onItemClickListener;
        filteredList = projects;
        filter = new ProjectFilter();
        projectListListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constants.ViewType.FOOTER:
                return new FooterViewHolder(layoutInflater.inflate(R.layout.footer_progress_bar, parent, false));
            default:
                ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_project_list_item, parent, false);
                return new RecyclerViewHolder<>(binding);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            Project project = filteredList.get(position);
            final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
            viewHolder.getBinding().setVariable(BR.projectViewModel, new ProjectViewModel(project));
            viewHolder.getBinding().executePendingBindings();
            viewHolder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(orignalList.get(position), v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return orignalList.get(position).getViewType();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ProjectFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                results.count = orignalList.size();
                results.values = orignalList;
            } else {
                List<Project> filteredList = new ArrayList<>();
                for (Project item : orignalList) {
                    if (item.getTitle().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<Project>) results.values;
            notifyDataSetChanged();
            projectListListener.onSearchResult(results.count);
        }
    }

}
