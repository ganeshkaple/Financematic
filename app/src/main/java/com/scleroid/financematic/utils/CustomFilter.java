package com.scleroid.financematic.utils;

import android.widget.Filter;

import com.scleroid.financematic.adapter.PeopleAdapter;
import com.scleroid.financematic.model.List_all_peoples;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scleroid on 28/3/18.
 */

/**
 * Created by Hp on 3/17/2016.
 */
public class CustomFilter extends Filter {

    PeopleAdapter adapter;
    ArrayList<List_all_peoples> filterList;


    public CustomFilter(List<List_all_peoples> filterList, PeopleAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList= (ArrayList<List_all_peoples>) filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<List_all_peoples> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getList_person_name().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.list_all_peoplesList= (ArrayList<List_all_peoples>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
