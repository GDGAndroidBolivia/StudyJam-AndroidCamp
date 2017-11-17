package com.androidbolivia.camp.studyjam_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {
    private TextView pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        pedido = (TextView) findViewById(R.id.tv_pedido);

        Bundle bundle = getIntent().getExtras();
        pedido.setText(bundle.getString("Pedido"));
    }

    public void finish(View view) {
        finish();
    }
}
