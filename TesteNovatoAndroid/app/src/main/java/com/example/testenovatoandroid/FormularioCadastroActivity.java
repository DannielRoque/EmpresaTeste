package com.example.testenovatoandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testenovatoandroid.dao.EmpresaDAO;
import com.example.testenovatoandroid.model.Funcionario;
import java.util.ArrayList;

public class FormularioCadastroActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Minha empresa";
    Spinner empresa;
    private ArrayList<Funcionario> lista;
    private Funcionario funcionario;
    private EmpresaDAO dao;
    private EditText campoNome;
    private EditText campoSobrenome;
    private EditText campoDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        dao = new EmpresaDAO(this);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

        empresa = (Spinner)findViewById(R.id.activity_formulario_spinner);
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.cargo_empresa,
                R.layout.activity_formulario_spinner);
        empresa.setAdapter(adapter);

        final RatingBar valorRating = findViewById(R.id.testeHUss);
        final int rating = (int) valorRating.getRating();
        botaoNovoAluno(valorRating);
  }

    private void botaoNovoAluno(final RatingBar valorRating) {
        Button botaoSalvar = findViewById(R.id.activity_formulario_cadastro_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Funcionario funcionario = criarFuncionario(campoNome, campoSobrenome, campoDataNascimento, valorRating);
                salvar(funcionario);

            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_cadastro_nome);
        campoSobrenome = findViewById(R.id.activity_formulario_cadastro_sobrenome);
        campoDataNascimento = findViewById(R.id.activity_formulario_cadastro_data_nascimento);
    }

    private void salvar(Funcionario funcionario) {
        dao.insert(funcionario);
        finish();
    }

    private Funcionario criarFuncionario(EditText campoNome, EditText campoSobrenome, EditText campoDataNascimento, RatingBar valorRating) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(campoNome.getText().toString());
        funcionario.setSobrenome(campoSobrenome.getText().toString());
        funcionario.setDataNascimento(campoDataNascimento.getText().toString());
        funcionario.setCargo(empresa.getSelectedItem().toString());
        funcionario.setNota((int) valorRating.getRating());
        return funcionario;
    }
}
