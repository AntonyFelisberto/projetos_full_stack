package com.projetooficina.sistemaoficinaback.scheduler;

import com.projetooficina.sistemaoficinaback.model.Cronograma;
import com.projetooficina.sistemaoficinaback.services.CronogramaServices;
import com.projetooficina.sistemaoficinaback.services.MessageService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulerJob {

    private final CronogramaServices cronogramaServices;
    private final List<Cronograma> cronograma;
    private final MessageService messageService;

    @Scheduled(initialDelay = 1000L,fixedDelayString = "PT1M")
    private void pegarDadosDoBanco() throws InterruptedException {
        Calendar dateInicioBusca = Calendar.getInstance();
        Date tempoAtualMaisDezMinutos= DateUtils.addMinutes(dateInicioBusca.getTime(), 10);
        Date tempoAtualMaisTrintaMinutos = DateUtils.addMinutes(dateInicioBusca.getTime(), 30);
        cronograma.addAll(cronogramaServices.encontrarTodosCronogramaPorHora(tempoAtualMaisDezMinutos,tempoAtualMaisTrintaMinutos));
        Thread.sleep(1000L);
    }

    @Scheduled(cron = "0 */2 * * * *")
    private void iniciarEnvioDeSMS() throws InterruptedException {
        if(cronograma.size() == 0) {
            Thread.sleep(2000L);
        }else {
            for(int numeroDeMensagens = 0; numeroDeMensagens <=cronograma.size(); numeroDeMensagens++){
                messageService.sendSms(
                                        cronograma.get(numeroDeMensagens).getDescricao()+": REALIZAR ATÉ A DATA "+cronograma.get(numeroDeMensagens).getDataFim(),
                                        cronograma.get(numeroDeMensagens).getUsuario().getTelefone()
                                      );
                cronogramaServices.deleteCronograma(cronograma.get(numeroDeMensagens).getIdCronograma());
                cronograma.remove(numeroDeMensagens);
                Thread.sleep(5000L);
            }
        }
    }

}
