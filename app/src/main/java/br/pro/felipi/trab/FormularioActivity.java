package br.pro.felipi.trab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import br.pro.adalto.apppersist.R;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etFone;
    private EditText etCidade;
    private Spinner spCategorias;
    private Button btnSalvar;
    private String acao;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        getSupportActionBar().hide();
        etNome = findViewById(R.id.etNome);
        etFone = findViewById(R.id.fone);
        etCidade = findViewById(R.id.cidade);
        spCategorias = findViewById(R.id.spCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar") ){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarFormulario(){

        // Recuperando o ID do produto selecionado (provavelmente de alguma lista que vc tem)
        int id = getIntent().getIntExtra("idProduto", 0);

        // Recuperando o objeto "Produto" a partir do ID.
        produto = ProdutoDAO.getProdutoById(this, id);

        // Com o objeto recuperado é hora de alimentar os componentes na tela.
        etNome.setText( produto.getNome() );
        etFone.setText( produto.getFone() );
        etCidade.setText( produto.getCidade() );

        String[] categorias = getResources().getStringArray(R.array.categorias);
        for (int i = 1; i < categorias.length ;i++){
            if( produto.getCategoria().equals( categorias[i] ) ){
                spCategorias.setSelection(i);
                break;
            }
        }
    }
    private void salvar(){
        // Nesse ponto está sendo recuperado o valor do componente da tela (etNome) e armazenado na variável "nome"
        String nome = etNome.getText().toString();

        if( nome.isEmpty() || spCategorias.getSelectedItemPosition()  == 0 ){
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG ).show();
        }else{
            if( acao.equals("inserir")) {
                produto = new Produto();
            }

            // Também é preciso fazer o mesmo procedimento para os demais componentes, assim como foi feito para o "nome"
            String fone      = etFone.getText().toString();

            String cidade    = etCidade.getText().toString();
            String categoria = spCategorias.getSelectedItem().toString();

            produto.setNome( nome );
            produto.setFone( fone );
            produto.setCidade( cidade );
            produto.setCategoria( categoria );

            if( acao.equals("inserir")) {
                // Insetindo os dados do produto no banco de dados.
                ProdutoDAO.inserir(this, produto);

                // Limpando os componentes da tela
                etNome.setText("");
                etFone.setText("");
                etCidade.setText("");
                spCategorias.setSelection(0, true);
            }else{
                ProdutoDAO.editar(this, produto);
                finish();
            }
        }
    }

}