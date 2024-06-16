package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.R;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment;

    //TODO: Esta classe possui 4 Tratamentos de Exceção
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_app), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                carregaFragment(bundle);
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_Main, new fragment_inicial());
                fragmentTransaction.commit();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao iniciar a atividade: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void carregaFragment(Bundle bundle) {
        try {
            String tipo = bundle.getString("tipo");
            if (tipo.equals("cliente")) {
                fragment = new fragment_cliente();
            } else if (tipo.equals("carro")) {
                fragment = new fragment_carro();
            } else {
                fragment = new fragment_aluguel();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_Main, fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao carregar o fragmento: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao criar o menu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            int id = item.getItemId();
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, MainActivity.class);

            if (id == R.id.item_cliente) {
                bundle.putString("tipo", "cliente");
                intent.putExtras(bundle);
                this.startActivity(intent);
                this.finish();
                return true;

            } else if (id == R.id.item_carro) {
                bundle.putString("tipo", "carro");
                intent.putExtras(bundle);
                this.startActivity(intent);
                this.finish();
                return true;

            } else {
                bundle.putString("tipo", "aluguel");
                intent.putExtras(bundle);
                this.startActivity(intent);
                this.finish();
                return true;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao selecionar o item de menu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
