package com.luxoft.training.akka.webcrawler;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.luxoft.training.akka.webcrawler.actor.JobSchedulerActor;
import com.luxoft.training.akka.webcrawler.message.UrlMessage;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Orkhan Gasimov
 */
public class ContextListener implements ServletContextListener  {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
