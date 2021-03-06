package co.wander.healthy;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {


    private EditText editWeight;
    private EditText editHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        editWeight = findViewById(R.id.imc_peso);
        editHeight = findViewById(R.id.imc_altura);
        Button btnSend = findViewById(R.id.imc_send);

        btnSend.setOnClickListener(v -> {
            if (!validate()) {
                Toast.makeText(ImcActivity.this, R.string.fields_message, Toast.LENGTH_SHORT).show();
                return;
            }
            String sHeight = editHeight.getText().toString();
            String sWeight = editWeight.getText().toString();

            int height = Integer.parseInt(sHeight);
            double weight = Double.parseDouble(sWeight);

            double imc = calculateImc(height, weight);
            int resId = imcResponse(imc);

            AlertDialog alertDialog = new AlertDialog.Builder(ImcActivity.this)
                    .setTitle(getString(R.string.imc_response, imc))
                    .setMessage(resId)
                    .setNegativeButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                    .setPositiveButton(R.string.save, (dialog, which) -> {
                        SqlHelper sqlHelper = SqlHelper.getInstance(ImcActivity.this);
                        long calcId = sqlHelper.addItem(SqlHelper.TYPE_IMC, imc);

                        if (calcId > 0)
                            Toast.makeText(ImcActivity.this, R.string.calc_saved, Toast.LENGTH_LONG).show();
                    })
                    .create();
            alertDialog.show();

            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);
            im.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);

        });
    }

    @StringRes
    private int imcResponse(double imc) {
        if (imc < 15)
            return R.string.imc_severely_low_weight;
        else if (imc < 16)
            return R.string.imc_very_low_weight;
        else if (imc < 18.5)
            return R.string.imc_low_weight;
        else if (imc < 25)
            return R.string.imc_normal;
        else if (imc < 30)
            return R.string.imc_high_weight;
        else if (imc < 35)
            return R.string.imc_so_high_weight;
        else if (imc < 40)
            return R.string.imc_severy_high_weight;
        else
            return R.string.imc_extreme_weight;

    }

    private double calculateImc(int height, double weight) {
        return  weight / (((double) height / 100) * ((double) height / 100));

    }

    private boolean validate() {
        return !editHeight.getText().toString().startsWith("0")
                && !editWeight.getText().toString().startsWith("0")
                && !editHeight.getText().toString().isEmpty()
                && !editWeight.getText().toString().isEmpty();
    }
}
