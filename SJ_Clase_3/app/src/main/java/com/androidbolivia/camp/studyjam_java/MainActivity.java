package com.androidbolivia.camp.studyjam_java;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText meat;
    private EditText extras;
    private CheckBox mayo;
    private CheckBox mos;
    private CheckBox ket;
    private Button pedirApp;
    private Button pedirEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meat = (EditText) findViewById(R.id.et_meat);
        extras = (EditText) findViewById(R.id.et_extras);
        mayo = (CheckBox) findViewById(R.id.c_mayo);
        mos = (CheckBox) findViewById(R.id.c_mos);
        ket = (CheckBox) findViewById(R.id.c_ket);
        pedirApp = (Button) findViewById(R.id.btn_pedir_app);
        pedirEmail = (Button) findViewById(R.id.btn_pedir_email);
        pedirEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pedir = pedido();
                if (!pedir.equals("Pediste: \nUna hamburguesa de ")) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    String[] a = {"alandaviov@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, a);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "alan ortiz");
                    intent.putExtra(Intent.EXTRA_TEXT, pedir);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Np hiciste tu pedido", Toast.LENGTH_SHORT).show();
                }

            }
        });
        limpiar();

    }

    public String pedido() {
        String pedido = "Pediste: \n";
        pedido += "Una hamburguesa de " + meat.getText().toString();
        if (mayo.isChecked() || mos.isChecked() || ket.isChecked()) {
            pedido += " con ";
            if (mayo.isChecked()) {
                pedido += "mayonesa";
            }
            if (mos.isChecked()) {
                if (pedido.charAt(pedido.length() - 1) == 'a') {
                    pedido += " y mostaza";
                } else {
                    pedido += "mostaza";
                }
            }
            if (ket.isChecked()) {
                if (pedido.charAt(pedido.length() - 1) == 'a') {
                    pedido += " y ketchup";
                } else {
                    pedido += "ketchup ";
                }
            }
        }
        if (!extras.getText().toString().equals("")) {
            pedido += " mas " + extras.getText().toString();
        }
        return pedido;
    }

    public void pedirPorApp(View view) {
        String pedir = pedido();
        if (!pedir.equals("Pediste: \nUna hamburguesa de ")) {
            Intent intent = new Intent(this, PedidoActivity.class);
            intent.putExtra("Pedido", pedir);
            startActivity(intent);
            limpiar();
        } else {
            Toast.makeText(getApplicationContext(), "Np hiciste tu pedido", Toast.LENGTH_SHORT).show();
        }

//        Toast.makeText(this, pedir, Toast.LENGTH_SHORT).show();
    }

    public void limpiar() {
        meat.setText("");
        extras.setText("");
        mayo.setChecked(false);
        mos.setChecked(false);
        ket.setChecked(false);
    }
}
