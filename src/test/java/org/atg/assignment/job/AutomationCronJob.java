package org.atg.assignment.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class AutomationCronJob implements Job {

    private static final Logger LOG = LogManager.getLogger(AutomationCronJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Running Automation tests of ATG ! Current Time is {}", new Date());
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage("org.atg.assignment.tests")
                )
                .filters(includeClassNamePatterns(".*Test"))
                .build();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        Launcher launcher = LauncherFactory.create();
        launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        OutputStream outputStream = IoBuilder
                .forLogger(LOG)
                .buildOutputStream();
        summary.printTo(new PrintWriter(System.out));
        summary.printTo(new PrintWriter(outputStream));
    }
}
