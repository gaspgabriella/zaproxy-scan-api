package com.ggaspar.apizap.scans;

import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

import java.nio.charset.StandardCharsets;

public class ActiveScan {

    private static final int ZAP_PORT = 8090;
    private static final String ZAP_API_KEY = "fookey";
    private static final String ZAP_ADDRESS = "localhost";
    private static final String TARGET = "http://localhost:3000";

    public static String scan() {
        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
        try {
            System.out.println("Spider Scanning target : " + TARGET);
            // Adicionar servidor local para conseguir fazer o spider e o ascan sem erros de URL not found in Subtree
            api.network.addLocalServer("localhost", "3000", null, null, null, null, null);
            ApiResponse spiderResponse = api.spider.scan(TARGET, null, "True", null, null);
            // Aguardar completar o spider scan para conseguir realizar o active scan
            try{
                String scanid = ((ApiResponseElement) spiderResponse).getValue();
                int progress = 0;
                System.out.println(scanid);
                do {
                    Thread.sleep(5000);
                    progress =
                            Integer.parseInt(
                                    ((ApiResponseElement) api.spider.status(scanid)).getValue());
                    System.out.println("Active Scan progress : " + progress + "%");
                } while (progress < 100);
            }catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("Active Scanning target : " + TARGET);
            // Realizar scan, resultado será retornado no Cron. (ResultSync class);
            api.ascan.scan(TARGET, "True", "False", null, "GET", null);
            return null;

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}