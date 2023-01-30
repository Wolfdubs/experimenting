package Concepts;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetworkingDemo {

    public static void main(String[] args) {
        String url = "www.womble.com";
        InetAddress inetAddress = null;
        try {
            inetAddress = Inet4Address.getByName(url);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Address = " + Arrays.toString(inetAddress.getAddress()));
        System.out.println("Host Address = " + inetAddress.getHostAddress());
        System.out.println("Is local address = " + inetAddress.isAnyLocalAddress());
        System.out.println("Is link local address = " + inetAddress.isLinkLocalAddress());

    }
}
