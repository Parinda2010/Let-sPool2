package com.example.parinda.letspool;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
public class Login extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login,container,false);
    }

    Button login_btn;
    EditText username;
    EditText password;
    TextView reg_tv;

    DataBaseHelper helper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper =new DataBaseHelper(this.getActivity());
        username = (EditText)getActivity().findViewById(R.id.username);
        password = (EditText)getActivity().findViewById(R.id.password);
        login_btn = (Button)getActivity().findViewById(R.id.login);
        reg_tv=(TextView)getActivity().findViewById(R.id.reg_tv);

       // loginDataBaseAdapter = new LoginDataBaseAdapter(this.getContext());


        /*final Dialog dialog = new Dialog(Login.this.getActivity());
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");*/

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname=username.getText().toString();
                String upwd=password.getText().toString();

                // fetch the Password form database for respective user name


                // check if the Stored password matches with  Password entered by user
              /*  if(upwd.equals(storedPassword))
                {
                    Toast.makeText(getContext(), "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(getContext(), "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }*/


                String chk_pwd=helper.search(uname);

                if(upwd.equals(chk_pwd))
                {
                    Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reg_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Register();

               /* FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager.beginTransaction().add(fragment,"fragment").addToBackStack("fragment").replace(R.id.mainContent,fragment).commit();
                */

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(fragment,"fragment").addToBackStack("fragment").replace(R.id.mainContent,fragment);
                ft.commit();
            }
        });

       // dialog.show();


    }




}
