package com.ui.automation.Utilities.POSManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Arrays;

public class POSInstanceManager {
    private static POSInstanceManager instance;
    private final BlockingQueue<POSInstance> instanceQueue;
    private final int poolSize;

    private POSInstanceManager() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
        String baseUrl = props.getProperty("pos.url");
        List<String> wkids = Arrays.asList(props.getProperty("WKID").split(","));
        List<String> ipids = Arrays.asList(props.getProperty("IPID").split(","));
        List<String> cashierIds = Arrays.asList(props.getProperty("CashierID").split(","));
        List<String> passwords = Arrays.asList(props.getProperty("Password").split(","));
        poolSize = Math.min(Math.min(wkids.size(), ipids.size()), Math.min(cashierIds.size(), passwords.size()));
        instanceQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < poolSize; i++) {
            String url = baseUrl + "?wkid=" + wkids.get(i) + "&ipid=" + ipids.get(i);
            instanceQueue.add(new POSInstance(url, wkids.get(i), ipids.get(i), cashierIds.get(i), passwords.get(i)));
        }
    }

    public static synchronized POSInstanceManager getInstance() {
        if (instance == null) {
            instance = new POSInstanceManager();
        }
        return instance;
    }

    public POSInstance acquireInstance() throws InterruptedException {
        return instanceQueue.take();
    }

    public void releaseInstance(POSInstance posInstance) {
        instanceQueue.offer(posInstance);
    }

    public int getPoolSize() {
        return poolSize;
    }
} 