package com.r2soft.freedoministriesinternational.ui.main.packages;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.r2soft.freedoministriesinternational.MainActivity;
import com.r2soft.freedoministriesinternational.R;
import com.r2soft.freedoministriesinternational.packages.payment.PaymentAdapter;
import com.r2soft.freedoministriesinternational.packages.payment.PaymentService;
import com.r2soft.freedoministriesinternational.packages.template.TemplateAdapter;
import com.r2soft.freedoministriesinternational.packages.template.TemplateService;

public class DonationView extends Fragment {

    PaymentService paymentService = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.donation_layout, container, false);

        initEvents(view);

        return view;

    }

    private void initEvents(final View view){
        Button btn_do_payment = view.findViewById(R.id.btn_do_payment);
        paymentService = new PaymentService(new PaymentAdapter(getActivity(),view));

        btn_do_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getCardInputs(view);
               paymentService.doPayment();
            }
        });
    }

    private void getCardInputs(View view){

        EditText card_number = view.findViewById(R.id.card_number);
        EditText card_expiry_month = view.findViewById(R.id.card_expiry_month);
        EditText card_expiry_year = view.findViewById(R.id.card_expiry_year);
        EditText card_cvv = view.findViewById(R.id.card_cvv);
        EditText reason = view.findViewById(R.id.reason);
        EditText card_amount = view.findViewById(R.id.card_amount);
        EditText email = view.findViewById(R.id.email);

      try {
          paymentService.setCardNumber(card_number.getText().toString());
          paymentService.setCardExpiryMonth(Integer.valueOf(card_expiry_month.getText().toString()));
          paymentService.setCardExpiryYear(Integer.valueOf(card_expiry_year.getText().toString()));
          paymentService.setCardCvv(card_cvv.getText().toString());
          paymentService.setAmount(Integer.valueOf(card_amount.getText().toString()));
          paymentService.setEmail(email.getText().toString());
      }catch (Exception ex){
          paymentService.showErrorMessage();
      }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
