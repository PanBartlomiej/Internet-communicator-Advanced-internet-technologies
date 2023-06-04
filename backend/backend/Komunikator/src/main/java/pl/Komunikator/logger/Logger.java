package pl.Komunikator.logger;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.Komunikator.user.User_1;
import pl.Komunikator.znajomi.Znajomi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * klasa Logger realizuje część programowania apsektowego, wykorzystuje Spring AOP.
 * zapisuje do dziennika zdarzeń informacje o zawartych znajomościach. Dane zapisywane
 * są do pliku dodaniZnajomi.txt
 */

@EnableAspectJAutoProxy
@Aspect
@Component
@Order(1)
public class Logger {

    @Pointcut("execution(* pl.Komunikator.znajomi.ZnajomiRepository.save(..)) && args(entity)")
    public <T> void savePointcut(T entity) {}

    @Around("savePointcut(entity)")
    public <T> T logSave(ProceedingJoinPoint joinPoint, T entity) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String logMessage = String.format("Nowi znajomi! : %s i %s",
                ((Znajomi) entity).getId_usera_1().getNick(),((Znajomi) entity).getId_usera_2().getNick());
        writeLogToFile(logMessage);

        return (T) joinPoint.proceed();
    }

    private void writeLogToFile(String logMessage) {
        try {
            FileWriter fileWriter = new FileWriter("dodaniZnajomi.txt", true);
            fileWriter.write(LocalDateTime.now() + " - " + logMessage + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
