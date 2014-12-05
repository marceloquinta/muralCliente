/*
 * ====================================================================
 * LicenÃ§a da FÃ¡brica de Software (UFG)
 *
 * Copyright (c) 2014 FÃ¡brica de Software
 * Instituto de InformÃ¡tica (Universidade Federal de GoiÃ¡s)
 * Todos os direitos reservados.
 *
 * RedistribuiÃ§Ã£o e uso, seja dos fontes ou do formato binÃ¡rio
 * correspondente, com ou sem modificaÃ§Ã£o, sÃ£o permitidos desde que
 * as seguintes condiÃ§Ãµes sejam atendidas:
 *
 * 1. RedistribuiÃ§Ã£o do cÃ³digo fonte deve conter esta nota, em sua
 *    totalidade, ou seja, a nota de copyright acima, as condiÃ§Ãµes
 *    e a observaÃ§Ã£o sobre garantia abaixo.
 *
 * 2. RedistribuiÃ§Ã£o no formato binÃ¡rio deve reproduzir o conteÃºdo
 *    desta nota, em sua totalidade, ou seja, o copyright acima,
 *    esta lista de condiÃ§Ãµes e a observaÃ§Ã£o abaixo na documentaÃ§Ã£o
 *    e/ou materiais fornecidos com a distribuiÃ§Ã£o.
 *
 * 3. A documentaÃ§Ã£o fornecida com a redistribuiÃ§Ã£o,
 *    qualquer que seja esta, deve incluir o seguinte
 *    texto, entre aspas:
 *       "Este produto inclui software desenvolvido pela FÃ¡brica
 *       de Software do Instituto de InformÃ¡tica (UFG)."
 *
 * 4. Os nomes FÃ¡brica de Software, Instituto de InformÃ¡tica e UFG
 *    nÃ£o podem ser empregados para endoÃ§ar ou promover produtos
 *    derivados do presente software sem a explÃ­cita permissÃ£o
 *    por escrito.
 *
 * 5. Produtos derivados deste software nÃ£o podem ser chamados
 *    "FÃ¡brica de Software", "Instituto de InformÃ¡tica", "UFG",
 *    "Universidade Federal de GoiÃ¡s" ou contÃª-los em seus nomes,
 *    sem permissÃ£o por escrito.
 *
 * ESTE SOFTWARE Ã‰ FORNECIDO "COMO ESTÃ�". NENHUMA GARANTIA Ã‰ FORNECIDA,
 * EXPLÃ�CITA OU NÃƒO. NÃƒO SE AFIRMA QUE O PRESENTE SOFTWARE
 * Ã‰ ADEQUADO PARA QUALQUER QUE SEJA O USO. DE FATO, CABE AO
 * INTERESSADO E/OU USUÃ�RIO DO PRESENTE SOFTWARE, IMEDIATO OU NÃƒO,
 * ESTA AVALIAÃ‡ÃƒO E A CONSEQUÃŠNCIA QUE O USO DELE OCASIONAR. QUALQUER
 * DANO QUE DESTE SOFTWARE DERIVAR DEVE SER ATRIBUÃ�DO AO INTERESSADO
 * E/OU USUÃ�RIO DESTE SOFTWARE.
 * ====================================================================
 *
 * Este software Ã© resultado do trabalho de voluntÃ¡rios, estudantes e
 * professores, ao realizar atividades no Ã¢mbito da FÃ¡brica de Software
 * do Instituto de InformÃ¡tica (UFG). Consulte <http://fs.inf.ufg.br>
 * para detalhes.
 */
package muralufg.fabrica.inf.ufg.br.centralufg.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.compromisso.fragments.CollectionFragments;
import muralufg.fabrica.inf.ufg.br.centralufg.frasedodia.fragments.FraseDoDiaFragment;
<<<<<<< HEAD
import muralufg.fabrica.inf.ufg.br.centralufg.locais.fragments.LocaisFragment;
import muralufg.fabrica.inf.ufg.br.centralufg.util.view.cartao.CartoesListFragment;
=======
>>>>>>> 6d1274fda94a551ea94384958417baabecb6fab5
import muralufg.fabrica.inf.ufg.br.centralufg.gcm.GCMRegister;
import muralufg.fabrica.inf.ufg.br.centralufg.linhasdeonibus.fragments.LinhasDeOnibusFragment;
import muralufg.fabrica.inf.ufg.br.centralufg.util.view.cartao.CartoesListFragment;


public class MainActivity extends ActionBarActivity {

    private String[] menuItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    static final String TAG = "MainActivity";
    GoogleCloudMessaging gcm;
    String idRegistroGCM;
    Context context;

    GCMRegister gcmRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTitle = mDrawerTitle = getTitle();

        gcmRegister = new GCMRegister(this);

        menuItems = getResources().getStringArray(R.array.opcoes_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.row_menuitem, menuItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new HelloFragment())
                .commit();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /* Itens no ActionBar, respectivamente,
        host Activity
        DrawerLayout object
        nav drawer icon to replace 'Up' caret
        "open drawer" description
        "close drawer" description
        */
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.label_menu,
                R.string.label_fechar
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (gcmRegister.checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            idRegistroGCM = gcmRegister.getRegistrationId();

            if (idRegistroGCM.isEmpty() || "".equals(idRegistroGCM)) {
                gcmRegister.execute();
            }

        } else {
            Log.i(TAG, "NÃ£o encontrado Google Play Services APK vÃ¡lido.");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            Crouton.makeText(this, item.getTitle() + " selecionado", Style.INFO).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = getFragmentFromPosition(position);
        Bundle args = new Bundle();
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(menuItems[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private Fragment getFragmentFromPosition(int position) {
        switch (position) {
            case 0:
                return new HelloFragment();
            case 1:
                return new FraseDoDiaFragment();
            case 2:
                return new CartoesListFragment();
            case 3:
                return new CollectionFragments();
            case 4:
                return new LinhasDeOnibusFragment();
            case 5:
                return new LocaisFragment();
            default:
                Crouton.makeText(this, getResources().getString(R.string.alerta_opcao_invalida), Style.ALERT).show();
                return new HelloFragment();
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }
}
