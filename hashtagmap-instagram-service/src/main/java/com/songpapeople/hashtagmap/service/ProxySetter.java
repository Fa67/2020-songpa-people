package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.proxy.Proxies;

import java.util.Random;

public class ProxySetter {
    private final Proxies proxies;
    private final Random random = new Random();

    public ProxySetter(Proxies proxies) {
        this.proxies = proxies;
    }

    public void setProxy() {
        int size = proxies.size();
        proxies.setHostAndPort(random.nextInt(size));
    }
}
