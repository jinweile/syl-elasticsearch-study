package syl.study.elasticsearch.client;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Mtime on 2016/10/13.
 */
public class TClient{


    private static TransportClient client;

    private static Object _lock = new Object();



    public static TransportClient getClient() throws UnknownHostException {
        if (client == null){
            synchronized (_lock){
                if (client == null){
                    initCluster();
                }
            }
        }
        return client;
    }


    /**
     * 初始化集群
     * @throws UnknownHostException
     */
    private static void initCluster() throws UnknownHostException {
        //如果集群的名称不是elasticsearch， 就需要设置集群的名称
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", "shiyanlei")
                .put("client.transport.sniff", true)
                .build();
        client = TransportClient.builder().settings(settings).build();
        //设置transport addresses  是通过9300端口进行通讯的
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.52.104"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.52.105"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.51.105"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.51.104"), 9300));
    }
}
