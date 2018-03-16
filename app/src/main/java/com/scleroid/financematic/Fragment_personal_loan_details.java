package com.scleroid.financematic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scleroid on 2/3/18.
 * <p>
 * Created by scleroid on 2/3/18.
 * <p>
 * Created by scleroid on 2/3/18.
 */
/**
 * Created by scleroid on 2/3/18.
 */
/**
 * Created by scleroid on 2/3/18.
 */

public class Fragment_personal_loan_details  extends Fragment {

    private List<Personal_summery_loan_details> summeryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter_personal_summery_details mAdapter;

    public Fragment_personal_loan_details () {
        // Required empty public constructor
    }

    public static Fragment_personal_loan_details  newInstance(String param1, String param2) {
        Fragment_personal_loan_details  fragment = new Fragment_personal_loan_details ();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.personal_loan_aacount_details, container, false);


        Button button_pay=(Button)rootView.findViewById(R.id.Btn_pay_rx_summery);
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Fragment_registor_received_amount());
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.pesonal_summery_details_recycler);

        mAdapter = new Adapter_personal_summery_details(summeryList);

        recyclerView.setHasFixedSize(true);



        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Personal_summery_loan_details loan = summeryList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), loan.getSummery_date() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareLoanData();

        return rootView;


    }


    private void prepareLoanData() {
        Personal_summery_loan_details loan = new Personal_summery_loan_details("14/6/2018", "Received", "2000");
        summeryList.add(loan);
        loan = new Personal_summery_loan_details("1/5/2018", "Received", "5000");
        summeryList.add(loan);
        loan = new Personal_summery_loan_details("12/2/2018", "Received", "1000");
        summeryList.add(loan);



        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }
    //for intend passook
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit(); // save the changes
        // load fragment

    }


}
