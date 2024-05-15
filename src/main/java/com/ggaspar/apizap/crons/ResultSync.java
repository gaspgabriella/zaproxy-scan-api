package com.ggaspar.apizap.crons;

import org.springframework.scheduling.annotation.Scheduled;

public class ResultSync {

    // Every day runs cron at 00:00
    @Scheduled(cron = "0 0 * * *")
    public void syncResults() {
        //TODO: Realizar sync dos resultados com o repository. (Pesquisar alertas da aplicação e syncar os resultados de riscos no banco de dados)

        // E.g. Injecao de dependencia em ResultRepository e .save com os resultados retornados do ClientApi.alerts
    }


}
