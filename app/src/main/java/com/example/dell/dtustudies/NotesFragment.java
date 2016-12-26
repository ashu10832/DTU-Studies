package com.example.dell.dtustudies;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import static android.content.Context.DOWNLOAD_SERVICE;
import static com.example.dell.dtustudies.R.id.subject;


public class NotesFragment extends Fragment {
    private static final String TAG = "NotesFragment";


    public NotesFragment() {
        // Required empty public constructor
    }


    public static NotesFragment newInstance(String param1, String param2) {
        return new NotesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    List<Document> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();
        String subject = getActivity().getIntent().getExtras().getString("Subject");
        Log.e(TAG, "Subject " + subject);
        prepareList(subject);
        CustomAdapter adapter = new CustomAdapter(list);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Document document = list.get(position);
                DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(document.getUrl()));
                long enqueue = dm.enqueue(request);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return rootView;
    }

    private void prepareList( String subject) {
        if(subject.equals("MATHS"))
        {
            if(getActivity().getIntent().getExtras().getString("Group").equals("A")) {
                list.add(new Document("Maths", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldc0dwQnNFNi1yQlU", "HC Taneja Group A", "This is descrpition"));
                list.add(new Document("Maths", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldNWk4NU44MzE4Y28", "HC Taneja1", "This is descrpition"));
            }
            else if(getActivity().getIntent().getExtras().getString("Group").equals("B"))
            {
                list.add(new Document("Maths", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldNWk4NU44MzE4Y28", "HC Taneja Group B", "This is descrpition"));

            }
        }

        else if(subject.equals("CHEMISTRY"))
        {
            list.add(new Document("CHEMISTRY", "Papers", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzalddmhLenJXMGxUWXc", "Assignment", "Important Questions from Titration, Spectroscopy and Thermal Methods of Analysis"));
            list.add(new Document("CHEMISTRY", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldLWt5dnRORkZxbms", "Class Notes", "Hand Written Class Notes of Phase Rule, Spectroscopy and Polymers"));
            list.add(new Document("CHEMISTRY", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzalddjJBeW1FcWpIRGs", "Class Notes", "Hand Written Class Notes of Titration, Spectroscopy, Polymers and Bio-Molecules"));
            list.add(new Document("CHEMISTRY", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldYmpLb05YMkU4RDQ", "NCERT - ELECTROCHEMISTRY", "Complete Electrochemistry chapter from NCERT Class 12th"));
            list.add(new Document("CHEMISTRY", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldNk1qMks3V0FQMFk", "NCERT - POLYMERS", "Complete POLYMERS chapter from NCERT Class 12th"));
            list.add(new Document("CHEMISTRY", "Notes", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldN3UzUDhkcVpVNmM", "Spectroscopy Notes", "Detailed notes of Spectroscopy"));
        }
        else if (subject.equals("COMMUNICATION SKILLS"))
        {
            list.add(new Document("COMMUNICATION SKILLS","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldVFlxWWdNckpISVU" , "Phonetics Class Notes","Complete hand written notes of Phonetics and Syllables"));
            list.add(new Document("COMMUNICATION SKILLS","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzalda1RJRk9QblBzTEE" , "Phonetics Notes","Phonetics Alphabets Reference with Examples"));
            list.add(new Document("COMMUNICATION SKILLS","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldQXY2bEpBV3FSZTg" , "Character Sketches (1)","Character Sketches of Victor Frankenstein and The Monster"));
            list.add(new Document("COMMUNICATION SKILLS","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldZjgxWDBtbkVEUU0" , "Character Sketches (2)","Character Sketch of Robert Walton"));

        }
        else if(subject.equals("BME"))
        {
            list.add(new Document("BME","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldT0lKb1oyc1hzQXc","PART-B Measuring Instruments ","Hand written notes of Measuring Standards and Measuring Instruments"));
            list.add(new Document("BME","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldVWhsSG92Q1A0N3M","PART-A Important Derivations ","Precise Derivations of Diesel Cycles and Pascals Law and Short Notes on Steam Power Plant and Newtons law of viscosity"));
            list.add(new Document("BME","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldNW1hWmlWQUJuMGs","PART-A Fluid Mechanics ","All Important derivations and definitions of fluid mechanics with numericals and examples"));
            list.add(new Document("BME","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldLXRSN2t4NWh2YlU","PART-B Engineering Materials ","UNIT-5 Introduction to Engineering Materials Complete Notes by Roop Lal Sir"));
            list.add(new Document("BME","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldM3Z5SDNSU0xndnc","PART-B Casting ","UNIT-6 Casting Processes Complete Notes by Roop Lal Sir"));
        }

        else if(subject.equals("PROGRAMMING"))
        {
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldSk5LbC1HWHRXSnc","Introduction to C","Introductory knowledge of programming, history of C, algorithm and flowcharts"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldZHA1UkVsUWJhRmM","Flowcharts","Precise and well formatted typed notes of Flowcharts with examples"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldcGxRYVZtQjk3N1E","Basics","Topics covered in this - Header Files, Variables, Keywords, Constants, Parameters, Data Types, typedef, modifiers, Arrays, Type Conversion, Arithematic Statement"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldMGF3X25wUTZvLXc","Basics - 2","Topics covered in this - Tokens and Operators"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldWFJNVEQ0bjNkZE0","Flow of Control","Topics in this - Functions, Loops, if-else, switch, break and go to"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldeWozTEJwdnlHdTA","Pointers","Complete and easy to understand notes on Pointers"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldazlfaEk4ODNFek0","Pointers - 2","Pointers and Arrays (single-dimension and multi-dimension) with examples"));
            list.add(new Document("PROGRAMMING","Notes","https://drive.google.com/uc?export=download&id=0B-qYGNdFzaldQkRuM3BHM1F3Qlk","Structures","Complete Structures topic covered with examples"));
        }
        else if(subject.equals("ELECTRICAL"))
        {
            list.add(new Document("ELECTRICAL","Notes","URL","DC Circuit","Introduction to Electrical Circuits, Loop Analysis, Node Voltage, Transformations, Superposition Theorem, Thevenin's and Norton's Theorem"));
            list.add(new Document("ELECTRICAL","Notes","URL","DC Transient","RL RC LRC Circuits"));
            list.add(new Document("ELECTRICAL","Notes","URL","Single Phase AC","Generation of single phase, sinusodial and phasor, steady state, parallel and series, resonance, apparent active and reactive power"));
            list.add(new Document("ELECTRICAL","Notes","URL","Three Phase AC","Generation of three-phase voltage, line and phase quantities in star- and delta-connection and their relations, Solution and Measurement "));
            list.add(new Document("ELECTRICAL","Notes","URL","Magnetic Circuits",""));



        }



    }


}
