package tran_frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matschema.my.matschema.*;

import foodSchedule_fragment.FoodScheduleFragment;

/**
 * Created by my on 2015-01-28.
 */
public class TrainingFragment extends Fragment {

    private FragmentTabHost mTabHost;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //allt+6 logcat
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout, container, false);
/*
        mTabHost = (FragmentTabHost) v.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity().getBaseContext(), getFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Workout").setIndicator("Workout", null),
                FoodScheduleFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Food Schedule").setIndicator("Food Schedule", null),
                FoodScheduleFragment.class, null);



        mTabHost = (FragmentTabHost)v.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("top").setIndicator("Fragment B"),
                FoodScheduleFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("recent").setIndicator("Fragment C"),
                FoodScheduleFragment.class, null);

        */
        return v;
    }



}
