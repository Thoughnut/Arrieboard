package com.thoughnut.soundboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFrag2 extends Fragment {

private RecyclerView myrecyclerview;
private List<String> nameList;
private ArrayList<SoundObject> soundList = new ArrayList<>();



    public DemoFrag2() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo2, container, false);
        nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames));

        myrecyclerview = view.findViewById(R.id.soundboardRecyclerView2);
        SoundboardRecyclerAdapter adapter = new SoundboardRecyclerAdapter(getContext(), soundList);
        myrecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        myrecyclerview.setAdapter(adapter);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        soundList.clear();



        SoundObject[] soundItems = {
                new SoundObject(nameList.get(2), R.raw.gvd),
                    new SoundObject(nameList.get(4), R.raw.arrieiskut),
                    new SoundObject(nameList.get(5), R.raw.arriekut),
                    new SoundObject(nameList.get(8), R.raw.blokkeren),
                    new SoundObject(nameList.get(16), R.raw.hijnoemtzich),
                    new SoundObject(nameList.get(23), R.raw.kutjong),
                    new SoundObject(nameList.get(31), R.raw.nielsroks),
                    new SoundObject(nameList.get(32), R.raw.oftewel),
                    new SoundObject(nameList.get(33), R.raw.pappie),
                    new SoundObject(nameList.get(36), R.raw.profielfoto),
                    new SoundObject(nameList.get(38), R.raw.ree),
                    new SoundObject(nameList.get(46), R.raw.treinengek),
                    new SoundObject(nameList.get(50), R.raw.zorg),
                    new SoundObject(nameList.get(52), R.raw.motto),
                    new SoundObject(nameList.get(57), R.raw.snapchatkaart),
                    new SoundObject(nameList.get(59), R.raw.ikbennico),
                    new SoundObject(nameList.get(60), R.raw.anw),
                    new SoundObject(nameList.get(70), R.raw.jappielith)

        };
        soundList.addAll(Arrays.asList(soundItems));

    }

}
