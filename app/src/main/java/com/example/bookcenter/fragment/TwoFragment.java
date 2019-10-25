package com.example.bookcenter.fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookcenter.DB.AppDatabase;
import com.example.bookcenter.Entity.User;
import com.example.bookcenter.MainActivity;
import com.example.bookcenter.R;
import com.example.bookcenter.ToUpdateUserActivity;

import static android.content.Context.MODE_PRIVATE;


public class TwoFragment extends Fragment {
    SharedPreferences prf;
    String username;
    String password;
    String im;
    TextView name;
    TextView pwd;
    Button btn;
    Button btnLogout;
    ImageView imageView;
    private RecyclerView recyclerView;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_two,container, false);

        name = view.findViewById(R.id.tvName);
        pwd = view.findViewById(R.id.tvUserPwd);
        btn = view.findViewById(R.id.btnUpdateProfile);
        btnLogout = view.findViewById(R.id.btnLogout);
        imageView = view.findViewById(R.id.userImage);
        //for sharePreference
        prf = getActivity().getSharedPreferences("user_detail",MODE_PRIVATE);
        username = prf.getString("username","Your name");
        password = prf.getString("password","Your password");
        im = prf.getString("image","@drawable/add_person");

        //for displaying

        name.setText(username);
        pwd.setText(password);
        Glide.with(this).load(Uri.parse(im)).into(imageView);
        //imageView.setImageResource(@);

        //for update
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ToUpdateUserActivity.class);

                startActivity(intent);
            }

        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "logout!", Toast.LENGTH_LONG).show();
                //prf = getActivity().getSharedPreferences("user_detail",MODE_PRIVATE);
               //User user = new User(prf.getString("username","Your name"), prf.getString("password","Your password"));

                //AppDatabase.getAppDatabase(getContext()).userDAO().delete(user);
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        });



        return view;
    }
}
