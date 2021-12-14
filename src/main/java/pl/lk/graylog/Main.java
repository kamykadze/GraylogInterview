package pl.lk.graylog;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.lk.graylog.app.LogSendingApp;

public class Main {

    private static final Logger logger = Logger.getLogger(LogSendingApp.class);

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        logger.info("Starting application");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("pl.lk.graylog");
        LogSendingApp app = applicationContext.getBean(LogSendingApp.class);
        app.bombGraylogWithSampleData();
        logger.info("Stopping application");
    }
}
