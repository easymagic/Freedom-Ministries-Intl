package com.r2soft.freedoministriesinternational.packages.apis;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.r2soft.freedoministriesinternational.R;
import com.r2soft.freedoministriesinternational.packages.ajax.AjaxRequestPort;
import com.r2soft.freedoministriesinternational.packages.payment.PaymentAdapter;
import com.r2soft.freedoministriesinternational.packages.payment.PaymentService;

import org.json.JSONException;
import org.json.JSONObject;

public class DonationApiRequestAdapter implements AjaxRequestPort {

    View act = null;
    Activity act_ = null;

    public DonationApiRequestAdapter(View act,Activity act_){
        this.act = act;
        this.act_ = act_;
    }

    @Override
    public String getUrl() {
        return "https://freedoministriesinternational.com/api/log-transaction";
    }
    //log-transaction

    @Override
    public JSONObject getPayloadData() {
        EditText card_number = act.findViewById(R.id.card_number);
        EditText card_expiry_month = act.findViewById(R.id.card_expiry_month);
        EditText card_expiry_year = act.findViewById(R.id.card_expiry_year);
        EditText card_cvv = act.findViewById(R.id.card_cvv);

        EditText reason = act.findViewById(R.id.reason);
        EditText card_amount = act.findViewById(R.id.card_amount);
        EditText email = act.findViewById(R.id.email);

        JSONObject jsonObject = new JSONObject();

        PaymentService paymentService = new PaymentService(new PaymentAdapter(act_,act));

        try {
            jsonObject.put("amount",card_amount.getText().toString());
            jsonObject.put("cause",reason.getText().toString());
            jsonObject.put("email",email.getText().toString());
            jsonObject.put("payment_ref",paymentService.getRef());
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        $obj->amount = $this->request->amount;
//        $obj->cause  = $this->request->cause;
//        $obj->email  = $this->request->email;
//        $obj->payment_status = 'success';
//        $obj->payment_ref = $this->request->payment_ref;

        //clear the fields.
        card_number.setText("");
        card_expiry_month.setText("");
        card_expiry_year.setText("");
        card_cvv.setText("");

        return jsonObject;
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void saveResponse(String key, String response) {

    }

    @Override
    public String getResponse(String key) {
        return null;
    }

    @Override
    public boolean hasResponse(String key) {
        return false;
    }

}
