package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.adapters.AnexoAdapter;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.adapters.AnexoImagensAdapter;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models.Ouvidoria;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models.OuvidoriaItemAnexo;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.services.OuvidoriaService;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.utils.OuvidoriaUtil;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;

public class OuvidoriaFragment extends Fragment implements ServiceCompliant {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALERIA = 2;
    static final int REQUEST_ARQUIVO = 3;

    public static final String IMAGEM_TYPE = "image/*";
    public static final String AUDIO_TYPE = "audio/*";
    public static final String VIDEO_TYPE = "video/*";

    /**
     * Tamanho máximo de 20 MB
     */
    private static final long TAMANNHO_MAXIMO_ARQUIVO = 20971520;

    private TextView mOuvidoriaTitulo;
    private TextView mOuvidoriaData;
    private TextView mOuvidoriaDescricao;

    private ListView mOuvidoriaListaArquivos;
    private GridView mOuvidoriaGridImagens;

    private AnexoAdapter mArquviosAdapter;
    private AnexoImagensAdapter mImagensAdapter;

    public OuvidoriaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_ouvidoria, container, false);

        mOuvidoriaTitulo = (TextView) rootView.findViewById(R.id.ouvidoriaTitulo);
        mOuvidoriaData = (TextView) rootView.findViewById(R.id.ouvidoriaData);
        mOuvidoriaDescricao = (TextView) rootView.findViewById(R.id.ouvidoriaDescricao);
        mOuvidoriaListaArquivos = (ListView) rootView.findViewById(R.id.ouvidoriaListaAnexos);
        mOuvidoriaGridImagens = (GridView) rootView.findViewById(R.id.ouvidoriaGridImagens);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArquviosAdapter = new AnexoAdapter(getActivity(), new ArrayList<OuvidoriaItemAnexo>());
        mOuvidoriaListaArquivos.setAdapter(mArquviosAdapter);

        mImagensAdapter = new AnexoImagensAdapter(getActivity(), new ArrayList<OuvidoriaItemAnexo>());
        mOuvidoriaGridImagens.setAdapter(mImagensAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ouvidoria, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_enviar:
                enviarMensagem();
                break;

            case R.id.action_camera:
                chooserCamera();
                break;

            case R.id.action_anexar_imagem:
                chooserImagem();
                break;

            case R.id.action_anexar_arquivo:
                chooserArquivo();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Enviar mensagem
     */
    private void enviarMensagem() {
        String titulo = mOuvidoriaTitulo.getText().toString();
        String data = mOuvidoriaData.getText().toString();
        String descricao = mOuvidoriaDescricao.getText().toString();

        Ouvidoria ouvidoria = new Ouvidoria(titulo, data, descricao);

        // Adicionar todos os itens em anexo
        ouvidoria.addAllItensAnexo(mArquviosAdapter.getAll());
        ouvidoria.addAllItensAnexo(mImagensAdapter.getAll());

        showMenssage("Enviando..." + ouvidoria.toString());

        // Enviar a mensagem para a ouvidoria
        OuvidoriaService ouvidoriaService = new OuvidoriaService(this, ouvidoria);
        ouvidoriaService.execute();
    }

    /**
     * Abrir seletor de arquivos de Audio ou Vídeo
     */
    private void chooserArquivo() {
        Intent chooseIntent = new Intent(Intent.ACTION_PICK);
        chooseIntent.setType(AUDIO_TYPE);
        chooseIntent.setType(VIDEO_TYPE);
        chooseIntent.setAction(Intent.ACTION_GET_CONTENT);
        if (chooseIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(chooseIntent, REQUEST_ARQUIVO);
        }
    }

    /**
     * Abrir seletor de imagem
     */
    private void chooserImagem() {
        Intent chooseIntent = new Intent(Intent.ACTION_PICK);
        chooseIntent.setType(IMAGEM_TYPE);
        chooseIntent.setAction(Intent.ACTION_GET_CONTENT);
        if (chooseIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(chooseIntent, REQUEST_IMAGE_GALERIA);
        }
    }

    /**
     * Abrir camera para foto
     */
    private void chooserCamera() {
        Intent chooseIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Certifique-se de que há uma atividade câmera para lidar com a intenção
        if (chooseIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Criar o arquivo onde a foto deve ser salva
            File photoFile = null;
            try {
                photoFile = criarArquivoImagem();
            } catch (IOException ex) {
                showMenssage(ex.getMessage());
            }
            // Continuar apenas se o arquivo estiver criado
            if (photoFile != null) {
                chooseIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(chooseIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private String mFotoDiretorioAtual;

    /**
     * Criar um arquivo para a imagem que sera salva apos a foto
     *
     * @return
     * @throws java.io.IOException
     */
    private File criarArquivoImagem() throws IOException {
        // Criar o nome do arquivo
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imagemNome = "JPEG_" + timeStamp + "_";
        File armazenamentoDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imagemNome,  /* prefixo */
                ".jpg",      /* sufixo */
                armazenamentoDir /* diretorio */
        );

        mFotoDiretorioAtual = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    anexarImagem(mFotoDiretorioAtual);
                    break;
                case REQUEST_IMAGE_GALERIA:
                    anexarImagem(getDiretorio(data.getData()));
                    break;
                case REQUEST_ARQUIVO:
                    anexarArquivo(getDiretorio(data.getData()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @param arquivo
     */
    private void anexarArquivo(String arquivo) {
        try {
            final OuvidoriaItemAnexo itemAnexo = getItemAnexo(arquivo);
            addArquivo(itemAnexo);
        } catch (Exception e) {
            showMenssage(e.getMessage());
        }
    }

    /**
     * @param imagem
     */
    private void anexarImagem(String imagem) {
        try {
            final OuvidoriaItemAnexo itemAnexo = getItemAnexo(imagem);
            addImagem(itemAnexo);
        } catch (Exception e) {
            showMenssage(e.getMessage());
        }
    }

    /**
     * @param diretorio
     * @return
     * @throws Exception
     */
    private OuvidoriaItemAnexo getItemAnexo(String diretorio) throws Exception {

        OuvidoriaItemAnexo itemAnexo;

        // localização do item
        File file = new File(diretorio);
        if (file.exists()) {
            // Verifica o tamanho máximo do arquivo para envio
            if (file.length() > TAMANNHO_MAXIMO_ARQUIVO) {
                throw new Exception(String.format(
                        getResources().getString(R.string.erro_tamanho_maximo_arquivo),
                        getTamanhoMaximo()
                ));
            }
            // Item Anexo
            final String nome = file.getName();
            final Long tamanho = file.length();
            itemAnexo = new OuvidoriaItemAnexo(diretorio, nome, tamanho);

            return itemAnexo;
        } else {
            throw new Exception(String.format(
                    getResources().getString(R.string.erro_arquivo_nao_existe)
            ));
        }
    }

    /**
     * Capturar o diretorio de um arquivo selecionado
     *
     * @param uri
     * @return
     */
    public String getDiretorio(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /**
     * Adicionar uma imagem na lista de anexos
     *
     * @param itemAnexo
     */
    private void addImagem(OuvidoriaItemAnexo itemAnexo) {
        mImagensAdapter.add(itemAnexo);
        mImagensAdapter.notifyDataSetChanged();
    }

    /**
     * Adicionar um arquivo na lista de anexos
     *
     * @param itemAnexo
     */
    private void addArquivo(OuvidoriaItemAnexo itemAnexo) {
        mArquviosAdapter.add(itemAnexo);
        mArquviosAdapter.notifyDataSetChanged();
    }

    /**
     * Obter o tamanho máximo e formatado para o usuário
     *
     * @return
     */
    private String getTamanhoMaximo() {
        return OuvidoriaUtil.bytesParaFormatoLegivel(TAMANNHO_MAXIMO_ARQUIVO, true);
    }

    /**
     * Mostrar o Toast para o usuário
     *
     * @param mensagem
     */
    private void showMenssage(String mensagem) {
        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();
    }

    /**
     * Resposta de erro após enviar mensagem à ouvidoria
     *
     * @param error
     */
    @Override
    public void handleError(String error) {
        Crouton.makeText(this.getActivity(), error, Style.ALERT).show();
    }

    /**
     * Resposta contendo a mensagem de retorno à mensagem enviada
     *
     * @param object
     */
    @Override
    public void readObject(Object object) {
        if (object instanceof String) {
            final String mensagem = (String) object;
            Crouton.makeText(this.getActivity(), mensagem, Style.CONFIRM).show();
        } else {
            Log.e(this.getClass().getName(), "A mensagem de reposta deve ser uma String");
        }
    }

    /**
     * Informar a activity do fragment
     *
     * @return
     */
    @Override
    public Activity getContextActivity() {
        return this.getActivity();
    }
}