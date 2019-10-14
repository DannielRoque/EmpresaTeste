package com.example.testenovatoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testenovatoandroid.dao.EmpresaDAO;
import com.example.testenovatoandroid.model.Funcionario;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Funcionario> lista;
    private EmpresaDAO dao;
    private ArrayAdapter<Funcionario> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Minha empresa");
        abreFormularioCadastro();
        dao = new EmpresaDAO(this);
        configuraLista();
        configuraDadosDashboard();

    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
        configuraDadosDashboard();
    }

    private void configuraDadosDashboard() {
        long totalFuncionario = dao.totalFuncionario();
        TextView totalF = findViewById(R.id.activity_main_colaboradores_resultados);
        String tot = String.valueOf(totalFuncionario);
        totalF.setText(tot);

        float totalMedia = dao.mediaNota();
        TextView mediaN = findViewById(R.id.activity_main_media_colaboradores_resultado);
        String med = String.valueOf(totalMedia);
        mediaN.setText(med);


        RatingBar mediaRatingBar = findViewById(R.id.activity_main_star);
        mediaRatingBar.setRating(totalMedia);

        long totalComercial = dao.totalFuncionarioComercial();
        TextView totalC = findViewById(R.id.activity_main_comercial_resposta);
        String totC = String.valueOf(totalComercial);
        totalC.setText(totC);

        long totalAdministrativo = dao.totalFuncionarioAdministrativo();
        TextView totalA = findViewById(R.id.activity_main_administrativo_resposta);
        String totA = String.valueOf(totalAdministrativo);
        totalA.setText(totA);

        long totalDesenvolvimento = dao.totalFuncionarioDesenvolvimento();
        TextView totalD = findViewById(R.id.activity_main_desenvolvimento_resposta);
        String totD = String.valueOf(totalDesenvolvimento);
        totalD.setText(totD);

        long totalSuporte = dao.totalFuncionarioSuporte();
        TextView totalS = findViewById(R.id.activity_main_suporte_resposta);
        String totS = String.valueOf(totalSuporte);
        totalS.setText(totS);
    }


    private void configuraLista() {
        ListView listViewActivity = findViewById(R.id.activity_main_listagem);
        lista = dao.selectAll();
        adapter = new ArrayAdapter<>(this, R.layout.item_funcionario, lista);
        listViewActivity.setAdapter(adapter);
//        listViewActivity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Log.e("Teste Widget", String.valueOf(id));
//                Funcionario funcionarioSelecionado = (Funcionario) adapterView.getItemAtPosition(position);
//                dao.delete(funcionarioSelecionado);
//                adapter.remove(funcionarioSelecionado);
//                return true;
//            }
//        });
    }

    private void abreFormularioCadastro() {
        Button botaoNovoCadastro = findViewById(R.id.activity_novo_cadastro_fab);
        botaoNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormularioCadastroActivity.class));
            }
        });
    }




}
