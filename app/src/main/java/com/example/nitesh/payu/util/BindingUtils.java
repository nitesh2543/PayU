package com.example.nitesh.payu.util;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nitesh.payu.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.util.Date;


public class BindingUtils {

    private static final String TAG = BindingUtils.class.getSimpleName();

    @BindingAdapter(value = {"android:src", "default"}, requireAll = true)
    public static void bindImage(ImageView view, String url, Drawable placeHolder) {
        if (TextUtils.isEmpty(url))
            url = null;
        RequestCreator requestCreator =
                Picasso.with(view.getContext()).load(url);

        if (placeHolder != null) {
            requestCreator.placeholder(placeHolder);
        }
        requestCreator.into(view);
    }

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String url) {

        Picasso.with(view.getContext()).load(url)/*.placeholder(R.drawable.azam_place_holder)*/.into(view);
    }

    @BindingAdapter({"bg"})
    public static void setBackground(View view, boolean isSelected) {
        view.setBackground(view.getResources().getDrawable((isSelected) ? R.drawable.selector_list_item_selected : R.drawable.selector_list_item));

    }


    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout view, boolean isRefreshing) {
        view.setRefreshing(isRefreshing);
    }

    @BindingAdapter({"date"})
    public static void setDate(TextView view, String date) {
        try {
            PrettyTime time = new PrettyTime();
            view.setText(time.format(DateUtils.parse(date, DateUtils.LISTING)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"enabled"})
    public static void setOnClick(View view, boolean enabled) {
        view.setClickable(enabled);
        view.setEnabled(enabled);
        if (!enabled)
            view.setBackgroundColor(Color.GRAY);
    }

    @BindingAdapter({"htmlText"})
    public static void setTitle(TextView view, String title) {
        view.setText(Html.fromHtml(title));
    }



    @BindingAdapter({"bind:time"})
    public static void timeStandard(TextView textView, String timeSatmp) {
        try {
            Date date = DateUtils.parse(timeSatmp, DateUtils.LISTING);
            String date1 = DateUtils.getRelativeTime(textView.getContext(), date);
            textView.setText(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
