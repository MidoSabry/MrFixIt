package com.example.fixawy.Client.MakeOrder;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fixawy.Client.MakeOrder.configpaypal.Config;
import com.example.fixawy.Client.MakeOrder.pojos.OrderTree;
import com.example.fixawy.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import static android.app.Activity.RESULT_OK;

public class ThirdOrderFragment extends Fragment {
    public static final int PAYPAL_REQUIRED_CODE = 7171;
    View view;
    String amount = "";
    RadioButton cash, payPalBtn, creditCard;
    ClientMakeOrder clientMakeOrder;
    OrderTree orderTree;
    ThirdOrderViewModel thirdOrderViewModel;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.payPalKey);

    @Override
    public void onDestroy() {
        getActivity().stopService(new Intent(getActivity(), PayPalService.class));
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        getActivity().startService(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_third__order_, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        payPalBtn = view.findViewById(R.id.radio_pay_pal);
        cash = view.findViewById(R.id.radio_cash);
        creditCard = view.findViewById(R.id.radio_credit_card);
        orderTree = new OrderTree();
        orderTree.setLocation(getArguments().getString("Client location"));
        orderTree.setPhone(getArguments().getString("Client phone"));
        orderTree.setDate(getArguments().getString("Order Date"));
        orderTree.setTime(getArguments().getString("Order time"));
       orderTree.setDetails(getArguments().getString("Details"));
       orderTree.setTypeOfOrder(getArguments().getInt("Type"));
      thirdOrderViewModel = new ViewModelProvider(this).get(ThirdOrderViewModel.class);
        clientMakeOrder = (ClientMakeOrder) getActivity();

        clientMakeOrder.Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //TODO GO to another Activity or Fragment from Cash
            }
        });
        payPalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderTree.setPaymentMethod(1);
                thirdOrderViewModel.addData(orderTree);
                processPayment();
            }
        });
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderTree.setPaymentMethod(2);
                thirdOrderViewModel.addData(orderTree);
                Intent intent = new Intent(getActivity(), CreditCardActivity.class);
                startActivity(intent);


            }

        });
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderTree.setPaymentMethod(0);
                thirdOrderViewModel.addData(orderTree);
            }
        });
    }

    private void processPayment() {
        //TODO //amount = textpay.getText().toString();
        //TODO //  PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD","To RepairIt app",PayPalPayment.PAYMENT_INTENT_SALE);
        //TODO add EditText and button to determine amount of money
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf("10")), "USD", "To RepairIt app", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getActivity(), com.paypal.android.sdk.payments.PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        getActivity().startActivityForResult(intent, PAYPAL_REQUIRED_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PAYPAL_REQUIRED_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (paymentConfirmation != null) {
                    try {
                        String paymentDetails = paymentConfirmation.toJSONObject().toString(4);
                        getActivity().startActivity(new Intent(getActivity(), PaymentDetailsActivity.class)
                                .putExtra("payment Details", paymentDetails)
                                .putExtra("payment amount", amount));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                Toast.makeText(getContext(), "cancel", Toast.LENGTH_LONG).show();

            }

        }
    }


}