package com.example.parinda.letspool;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Parinda on 11-12-2016.
 */
public class Register extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register,container,false);
    }
    EditText name,email,cno,dob,pwd,cpwd;
    Button register;
    TextView log_tv;
   // LoginDataBaseAdapter loginDataBaseAdapter;


    DataBaseHelper helper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper=new DataBaseHelper(this.getActivity());
        name = (EditText)getActivity().findViewById(R.id.name);
        email = (EditText)getActivity().findViewById(R.id.email);
        cno = (EditText)getActivity().findViewById(R.id.cno);
        dob = (EditText)getActivity().findViewById(R.id.dob);
        pwd = (EditText)getActivity().findViewById(R.id.pwd);
        cpwd = (EditText)getActivity().findViewById(R.id.cpwd);
        register = (Button)getActivity().findViewById(R.id.register);

        log_tv=(TextView)getActivity().findViewById(R.id.log_tv);

       // loginDataBaseAdapter = new LoginDataBaseAdapter(this.getContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String ucno = cno.getText().toString();
                String udob = dob.getText().toString();
                String upwd = pwd.getText().toString();
                String ucpwd = cpwd.getText().toString();

                if(uname.equals("")||upwd.equals("")||ucpwd.equals("")||ucno.equals("")||udob.equals("")||uemail.equals(""))
                {
                    Toast.makeText(getContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!upwd.equals(ucpwd))
                {
                    Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Save the Data in Database
                    helper.insertData(uname,uemail,ucno,udob,upwd);
                    Toast.makeText(getContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });

        log_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Login();

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(fragment,"fragment").addToBackStack("fragment").replace(R.id.mainContent,fragment);
                ft.commit();
            }
        });

    }
}
