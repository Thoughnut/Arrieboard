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
public class DemoFrag1 extends Fragment {



    public DemoFrag1() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);



        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
     ArrayList<SoundObject> soundList = new ArrayList<>();

     RecyclerView SoundView;
     SoundboardRecyclerAdapter SoundAdapter = new SoundboardRecyclerAdapter(getContext(),soundList);
     RecyclerView.LayoutManager SoundLayoutManager;

        soundList.clear();

                    List<String> nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames));

                    SoundObject[] soundItems = {
                            new SoundObject(nameList.get(0), R.raw.ammo),
                            new SoundObject(nameList.get(1), R.raw.bakkiechips),
                            new SoundObject(nameList.get(3), R.raw.aimbot),
                            new SoundObject(nameList.get(6), R.raw.arriepenis),
                            new SoundObject(nameList.get(7), R.raw.beterkanniet),
                            new SoundObject(nameList.get(9), R.raw.chimestelen),
                            new SoundObject(nameList.get(10), R.raw.douchebag),
                            new SoundObject(nameList.get(12), R.raw.genietengeblazen),
                            new SoundObject(nameList.get(13), R.raw.getverdown),
                            new SoundObject(nameList.get(14), R.raw.grasmaaier),
                            new SoundObject(nameList.get(15), R.raw.hebjedierooie),
                            new SoundObject(nameList.get(17), R.raw.hoerehond),
                            new SoundObject(nameList.get(18), R.raw.hoeremoffe),
                            new SoundObject(nameList.get(19), R.raw.isdatffbale),
                            new SoundObject(nameList.get(20), R.raw.joh),
                            new SoundObject(nameList.get(21), R.raw.jongejongejoostenzeg),
                            new SoundObject(nameList.get(22), R.raw.khebjeuk),
                            new SoundObject(nameList.get(24), R.raw.kutjoosten),
                            new SoundObject(nameList.get(25), R.raw.makeeveryshotcount),
                            new SoundObject(nameList.get(26), R.raw.metskoop),
                            new SoundObject(nameList.get(27), R.raw.minkukel),
                            new SoundObject(nameList.get(28), R.raw.narrbenee),
                            new SoundObject(nameList.get(35), R.raw.primitiveren),
                            new SoundObject(nameList.get(37), R.raw.rambam),
                            new SoundObject(nameList.get(39), R.raw.resonantiehayo),
                            new SoundObject(nameList.get(40), R.raw.rpg),
                            new SoundObject(nameList.get(41), R.raw.ruimtenodig),
                            new SoundObject(nameList.get(42), R.raw.sint),
                            new SoundObject(nameList.get(43), R.raw.slightolul),
                            new SoundObject(nameList.get(44), R.raw.snotverpatje),
                            new SoundObject(nameList.get(45), R.raw.sodehoere),
                            new SoundObject(nameList.get(47), R.raw.vanleeuwen),
                            new SoundObject(nameList.get(48), R.raw.vroemmm),
                            new SoundObject(nameList.get(49), R.raw.zingen),
                            new SoundObject(nameList.get(53),R.raw.sisijaja),
                            new SoundObject(nameList.get(54),R.raw.hayooooooo),
                            new SoundObject(nameList.get(55),R.raw.rotjes)

                    };

                    soundList.addAll(Arrays.asList(soundItems));
                    SoundView = (RecyclerView) getActivity().findViewById(R.id.soundboardRecyclerView1);
                    SoundAdapter.notifyDataSetChanged();





        SoundLayoutManager = new GridLayoutManager(getContext(), 3);

        SoundView.setLayoutManager(SoundLayoutManager);

        SoundView.setAdapter(SoundAdapter);




    }

}
