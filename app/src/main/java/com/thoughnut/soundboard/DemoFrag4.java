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
public class DemoFrag4 extends Fragment {

private RecyclerView myrecyclerview;
private List<String> nameList;
private ArrayList<SoundObject> soundList = new ArrayList<>();



    public DemoFrag4() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo4, container, false);
        nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames));

        myrecyclerview = view.findViewById(R.id.soundboardRecyclerView4);
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
                new SoundObject(nameList.get(11), R.raw.echteenmogool),
                    new SoundObject(nameList.get(51), R.raw.hayoisnietzwart),
                    new SoundObject(nameList.get(56), R.raw.melk),
                    new SoundObject(nameList.get(58), R.raw.tyfop),
                    new SoundObject(nameList.get(61), R.raw.iangaatopdate),
                    new SoundObject(nameList.get(62),R.raw.wariskars),
                    new SoundObject(nameList.get(63),R.raw.civilwar),
                    new SoundObject(nameList.get(64),R.raw.hebgeentieten),
                    new SoundObject(nameList.get(65),R.raw.lul),
                    new SoundObject(nameList.get(66),R.raw.magiknaartoilet),
                    new SoundObject(nameList.get(67),R.raw.kramerscheld),
                    new SoundObject(nameList.get(68),R.raw.admiraal),
                    new SoundObject(nameList.get(69),R.raw.shotgun),
                    new SoundObject(nameList.get(29), R.raw.nee),
                    new SoundObject(nameList.get(30), R.raw.nee2),
                    new SoundObject(nameList.get(34), R.raw.penis)

        };
        soundList.addAll(Arrays.asList(soundItems));

    }

}
