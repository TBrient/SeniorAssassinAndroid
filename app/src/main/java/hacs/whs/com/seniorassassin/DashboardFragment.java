package hacs.whs.com.seniorassassin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ToggleButton showMoreToggle = (ToggleButton)view.findViewById(R.id.accouncements_show_more);

        showMoreToggle.setChecked(false);

        showMoreToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) { //isChecked = true, text = Show Less; isChecked = false, text = Show More;
                if (isChecked) {
                    View seperator = view.findViewById(R.id.announcements_seperator);
                    seperator.setVisibility(GONE);
                    LinearLayout announcementsLL = (LinearLayout)view.findViewById(R.id.announcements_layout);
                    ArrayList<String> announcements = new ArrayList<String>(); //TODO: Populate with actual announcements
                    announcements.add("Announcement 2");
                    announcements.add("Announcement 3");
                    announcements.add("Announcement 4");
                    LinearLayout extraAnnouncementsLL = generateAnnouncements(announcements);
                    extraAnnouncementsLL.setOrientation(LinearLayout.VERTICAL);
                    extraAnnouncementsLL.setGravity(Gravity.CENTER_HORIZONTAL);
                    announcementsLL.addView(extraAnnouncementsLL, 2);
                } else {
                    View seperator = view.findViewById(R.id.announcements_seperator);
                    seperator.setVisibility(View.VISIBLE);
                    LinearLayout announcementsLL = (LinearLayout)view.findViewById(R.id.announcements_layout);
                    announcementsLL.removeViewAt(2);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private LinearLayout generateAnnouncements(ArrayList<String> strings) {
        LinearLayout extraLL = new LinearLayout(getActivity());
        for (int i = 0; i < strings.size(); i++) {
            TextView temp = new TextView(getActivity());
            temp.setText(strings.get(i));
            temp.setPadding(0,5,0,0);
            extraLL.addView(temp);
        }
        return extraLL;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
