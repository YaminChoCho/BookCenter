package com.example.bookcenter.fragment;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookcenter.DB.AppDatabase;
import com.example.bookcenter.R;


public class ThreeFragment extends Fragment {
    TextView tv;
    private RecyclerView recyclerView;
    public ThreeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_three,container, false);
        int count = AppDatabase.getAppDatabase(getContext()).bookDAO().countBooks();
        tv = (TextView)view.findViewById(R.id.fk);
        tv.setText(count+"");

        return view;
    }
}
