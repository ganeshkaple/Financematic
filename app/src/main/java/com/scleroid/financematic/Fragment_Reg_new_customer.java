package com.scleroid.financematic;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by scleroid on 2/3/18.
 * <p>
 * Created by scleroid on 2/3/18.
 */
/**
 * Created by scleroid on 2/3/18.
 */

public class Fragment_Reg_new_customer extends Fragment {
    private Button b;
    private TextInputEditText etname,etmobile,etAddress,etLoan_number, etIDproofno;
    TextView tv;
    Button firstFragment;
    public Fragment_Reg_new_customer() {
        // Required empty public constructor
    }

    public static Fragment_Reg_new_customer newInstance(String param1, String param2) {
        Fragment_Reg_new_customer fragment = new Fragment_Reg_new_customer();
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
        final View rootView = inflater.inflate(R.layout.reg_new_customer, container, false);

//Intend
        firstFragment = (Button) rootView.findViewById(R.id.btn_new_customer_Register1);
        /*firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
*/
       /* b=(Button)rootView.findViewById(R.id.btn_new_customer_Register);*/
     /* tv=(TextView)rootView.findViewById(R.id.display);*/
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etname=(TextInputEditText) rootView.findViewById(R.id.coustomer_Name_EditText);
                etmobile=(TextInputEditText) rootView.findViewById(R.id.Mobile_Number_EditText);
                etAddress=(TextInputEditText) rootView.findViewById(R.id.Address_EditText);
                etLoan_number=(TextInputEditText) rootView.findViewById(R.id.Loan_number_EditText);
                etIDproofno=(TextInputEditText) rootView.findViewById(R.id.IDproofno);

                loadFragment(new Fragment_registor_given_money());
               /* tv.setText("Your Input: \n"+etname.getText().toString()+"\n"+etAddress.getText().toString()+"\n"+etmobile.getText().toString()+"\n"+etLoan_number.getText().toString()+"\n"+etIDproofno.getText().toString()+"\n"+"\nEnd.");*/
            }
        });



        return rootView;
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
