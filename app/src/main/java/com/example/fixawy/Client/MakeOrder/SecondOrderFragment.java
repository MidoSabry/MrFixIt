package com.example.fixawy.Client.MakeOrder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.example.fixawy.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SecondOrderFragment extends Fragment {
    private ImageView timePicker, datePicker;
    private Calendar calendar;
    View view;
    TextView date, time;
    EditText orderLocation, phone;
    ClientMakeOrder clientMakeOrder;
    OrderTree orderTree;
    public SecondOrderViewModel secondOrderViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second__order_, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderTree = new OrderTree();
        orderTree.setTypeOfOrder(getArguments().getInt("Type"));
        orderTree.setDetails(getArguments().getString("Details"));
        timePicker = view.findViewById(R.id.time_picker);
        datePicker = view.findViewById(R.id.date_picker);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        orderLocation = view.findViewById(R.id.location);
        phone = view.findViewById(R.id.make_order_phone);
        calendar = Calendar.getInstance();
        secondOrderViewModel = new ViewModelProvider(this).get(SecondOrderViewModel.class);
        if (getActivity() instanceof ClientMakeOrder) {
            clientMakeOrder = (ClientMakeOrder) getActivity();
            clientMakeOrder.Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderTree.setLocation(orderLocation.getText().toString());
                    orderTree.setPhone(phone.getText().toString());
                    orderTree.setTime(time.getText().toString());
                    orderTree.setDate(date.getText().toString());
                    if (clientMakeOrder.current_state < (clientMakeOrder.DescriptionData.length - 1))
                        clientMakeOrder.current_state = clientMakeOrder.current_state + 1;
                    clientMakeOrder.binding.stepIndicator.setCompletedPosition(clientMakeOrder.current_state).drawView();
                    int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                    Fragment current = getActivity().getSupportFragmentManager().getFragments().get(count > 0 ? count - 1 : count);
                    if (current instanceof SecondOrderFragment) {
                        Bundle bundle = new Bundle();
                        bundle.putString("Client location", orderTree.getLocation());
                        bundle.putString("Client phone", orderTree.getPhone());
                        bundle.putString("Order Date", orderTree.getDate());
                        bundle.putString("Order time", orderTree.getTime());
                        bundle.putInt("Type", orderTree.getTypeOfOrder());
                        bundle.putString("Details", orderTree.getDetails());
                        replaceFragment(new ThirdOrderFragment(), bundle);
                        clientMakeOrder.Next.setText("finish");
                    }
                }
            });
            Places.initialize(getActivity().getApplicationContext(), getString(R.string.api_key));
            orderLocation.setOnClickListener(v -> {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(getActivity());
                startActivityForResult(intent, 1);
            });
            timePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar timeSelected = Calendar.getInstance();
                    int hour = timeSelected.get(Calendar.HOUR_OF_DAY);
                    int minute = timeSelected.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            timeSelected.set(Calendar.MINUTE, selectedMinute);
                            timeSelected.set(Calendar.HOUR_OF_DAY, selectedHour);
                            timeSelected.set(Calendar.SECOND, 0);
                            time.setText(selectedHour + " : " + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });

            datePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            c.set(Calendar.YEAR, year);
                            c.set(Calendar.MONTH, month);
                            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            String datec = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
                            date.setText(datec);
                        }

                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                    dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    dialog.show();
                }


            });
        }
    }

    @Override  // google API and list of notes
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            orderLocation.setText(place.getAddress());
        }
    }


    private void replaceFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.first_fragment_order, fragment);
        fragmentTransaction.commit();

    }
}
