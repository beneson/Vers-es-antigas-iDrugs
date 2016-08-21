package com.example.beneson.idrugs.Others;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.backendless.geo.GeoPoint;
import com.example.beneson.idrugs.Classes.Estabelecimento;
import com.example.beneson.idrugs.Classes.Ofertado;
import com.example.beneson.idrugs.Classes.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beneson on 31/05/2016.
 */
public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "Beta_IDrugs";
    private static final int VERSAO_BD = 11;

    public BDCore(Context ctx) {

        super(ctx, NOME_BD, (SQLiteDatabase.CursorFactory)null, VERSAO_BD);
    }

    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL("create table ofertado(_idOfertado text primary key, "
                + "idProduto text, "
                + "idEstabelecimento text, "
                + "preco float, "
                + "formaFarmaceutica text, "
                + "qtdApresentacao float);");
        bd.execSQL("create table produto(_idProduto text primary key, "
                + "nomeComercial text, "
                + "nomeQuimico text, "
                + "laboratorio text, "
                + "registroMS text, "
                + "pedeReceita boolean, "
                + "categoria text);");
        bd.execSQL("create table estabelecimento(_idEstabelecimento text primary key, "
                + "nomeEstabelecimento text, "
                + "cnpj text, "
                //+ "idFormasEntrega text, "
                //+ "idFormasPagamento text, "
                + "velocidade float, "
                + "geo_lat double, "
                + "geo_lng double);");
        bd.execSQL("create table formasPagamento(_idformasPagamento integer primary key autoincrement, "
                + "idEstabelecimento text, "
                + "formaPagamento text);");
        bd.execSQL("create table formasEntrega(_idformasEntrega integer primary key autoincrement, "
                + "idEstabelecimento text, "
                + "formaEntrega text);");
    /*    bd.execSQL("create table pedido(_idPedido text primary key, "
                + "qtd text);");
        bd.execSQL("create table pedido_pedidos(_idPedido_pedidos text primary key, "
                + "idPedido text, "
                + "idPedidos text);");
        bd.execSQL("create table pedidos(_idPedidos text primary key, "
                + "idUsuario text, "
                + "idPedido_pedidos text, "
                + "idFormaPagamento text, "
                + "geo_lat double, "
                + "geo_lng double, "
                + "idFormaEntrega text, "
                + "foiEntregue boolean, "
                + "foiSucesso boolean, "
                + "tempoEntrega double);");
        bd.execSQL("create table usuario(_idUsuario text primary key, "
                + "idUser text, "
                + "geo_lat double, "
                + "geo_lng double);");*/

        //User
        //Proprietário (apenas na versão proprietário)

    }

    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {


        bd.execSQL("drop table ofertado;");
        bd.execSQL("drop table produto;");
        bd.execSQL("drop table estabelecimento;");
        bd.execSQL("drop table formasPagamento;");
        bd.execSQL("drop table formasEntrega;");
        /*bd.execSQL("drop table pedido;");
        bd.execSQL("drop table pedido_pedidos;");
        bd.execSQL("drop table pedidos;");
        bd.execSQL("drop table usuario;");*/
        this.onCreate(bd);
    }

    public List<Ofertado> onSelect(){
        List<Ofertado> ofertados = new ArrayList();
        Cursor cofertado= getReadableDatabase().rawQuery("SELECT * FROM ofertado;", null);

        while ((cofertado.moveToNext())) {
            Ofertado ofertado = new Ofertado();
            ofertado.setPreco(cofertado.getFloat(cofertado.getColumnIndex("preco")));
            ofertado.setQtdApresentacao(cofertado.getFloat(cofertado.getColumnIndex("qtdApresentacao")));
            ofertado.setFormaFarmaceutica(cofertado.getString(cofertado.getColumnIndex("formaFarmaceutica")));
            ofertado.setApresentacao(cofertado.getString(cofertado.getColumnIndex("apresentacao")));

            String idProduto = cofertado.getString(cofertado.getColumnIndex("idProduto"));
            Produto produto = new Produto();
            Cursor cproduto = getReadableDatabase().rawQuery("SELECT * FROM produto WHERE idProduto ='"+idProduto+"';", null);
            produto.setNomeComercial(cproduto.getString(cproduto.getColumnIndex("nomeComercial")));
            produto.setNomeQuimico(cproduto.getString(cproduto.getColumnIndex("nomeQuimico")));
            produto.setLaboratorio(cproduto.getString(cproduto.getColumnIndex("laboratorio")));
            produto.setRegistroMS(cproduto.getString(cproduto.getColumnIndex("registroMS")));
            //produto.isPedeReceita(cproduto.getString(cproduto.getColumnIndex("pedeReceita")));
            produto.setCategoria(cproduto.getString(cproduto.getColumnIndex("categoria")));
            ofertado.setProduto(produto);

            String idEstabelecimento = cofertado.getString(cofertado.getColumnIndex("idEstabelecimento"));
            Estabelecimento estabelecimento = new Estabelecimento();
            Cursor cestabelecimento = getReadableDatabase().rawQuery("SELECT * FROM estabelecimento WHERE idEstabelecimento ='"+idEstabelecimento+"';", null);
            estabelecimento.setNomeEstabelecimento(cestabelecimento.getString(cestabelecimento.getColumnIndex("nomeEstabelecimento")));
            estabelecimento.setCnpj(cestabelecimento.getString(cestabelecimento.getColumnIndex("cnpj")));
            //estabelecimento.setFormasEntrega(cestabelecimento.getString(cestabelecimento.getColumnIndex("laboratorio")));
            //estabelecimento.setFormasPagamento(cestabelecimento.getString(cestabelecimento.getColumnIndex("registroMS")));
            //estabelecimento.setProprietario(cestabelecimento.getString(cestabelecimento.getColumnIndex("pedeReceita")));
            estabelecimento.setVelocidade(cestabelecimento.getFloat(cestabelecimento.getColumnIndex("velocidade")));
            Double geo_lat = cestabelecimento.getDouble((cestabelecimento.getColumnIndex("geo_lat")));
            Double geo_lng = cestabelecimento.getDouble((cestabelecimento.getColumnIndex("geo_lng")));
            GeoPoint geoPoint = new GeoPoint(geo_lat, geo_lng);
            estabelecimento.setGeolocaliacao(geoPoint);
            ofertado.setEstabelecimento(estabelecimento);

            ofertados.add(ofertado);
        }
        cofertado.close();

        return ofertados;
    }
}

