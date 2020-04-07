package com.example.cep;

import android.os.AsyncTask;

import com.google.gson.Gson;


import java.net.HttpURLConnection;
import java.net.URL;
import 	java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, CEP> {
    private final String cep;
    StringBuilder resposta = new StringBuilder();

    public HttpService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        if(this.cep != null && this.cep.length() == 8){
            try{
                    URL url = new URL("http://ws.matheuscastiglioni.com.br/ws/cep/find/" + this.cep + "/json/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(5000);
                    connection.connect();
                    resposta = new StringBuilder();
               Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    resposta.append(sc.next());
                }
            } catch (Exception ex){
                    ex.printStackTrace();
                }

            }
      return new Gson().fromJson(resposta.toString(), CEP.class);
        }

    }


