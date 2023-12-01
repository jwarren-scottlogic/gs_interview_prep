package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IpSearch {

    private IpSearch() {
    }
    public static String getCommonIPAddress(String[] logStrings) throws Exception {
        List<String> ipAddresses = separateIpAddresses(logStrings);
        Map<String , Integer> ipFrequenceMap = makeIpFrequenceMap(ipAddresses);
         ipFrequenceMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
//        // code before extension
//        return ipFrequenceMap.entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey).orElseThrow(() -> new Exception("No IP address found"));
        return getFirstMostCommonIPAddress(ipAddresses, ipFrequenceMap);
    }

    private static String getFirstMostCommonIPAddress(List<String> ipAddresses, Map<String , Integer> ipFrequenceMap) throws Exception {
        int maxIpAddressOccurence = ipFrequenceMap.values()
                .stream().mapToInt(i -> i).max().orElseThrow(() -> new Exception("No IP address found"));
        for (String ip : ipAddresses) {
            if (ipFrequenceMap.getOrDefault(ip, 0) == maxIpAddressOccurence) {
                return ip;
            }
        }
        throw new Exception("No IP address found.");
    }

    public static Map<String , Integer> makeIpFrequenceMap(List<String> ipAddresses) {
        Map<String , Integer> ipFrequenceMap = new HashMap<>();
        for (String ipAddress : ipAddresses) {
            ipFrequenceMap.merge(ipAddress, 1, Integer::sum);
        }
        return ipFrequenceMap;
    }

    public static List<String> separateIpAddresses(String[] logStrings) {
        ArrayList<String> ipAddresses = new ArrayList<>();
        for (String logString : logStrings){
            String ipAddress = logString.split(" ")[0];
            ipAddresses.add(ipAddress);
        }
        return ipAddresses;
    }



    public static void main(String[] args) throws Exception {
        String ipAddress1 = "10.0.0.1 – username Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        String ipAddress2 = "10.0.0.2 – username Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        String ipAddress2_2 = "10.0.0.2 – blah blah.";

        String ipAddress3 = "10.0.3.1 – username Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        String ipAddress3_2 = "10.0.3.1 – blah blah blah.";
        String ipAddress3_3 = "10.0.3.1 – 2 blah blah blah.";

        String[] strings = new String[]{ipAddress1, ipAddress2, ipAddress2_2, ipAddress3, ipAddress3_2, ipAddress3_3};
        System.out.println("The most common IP address is: "+IpSearch.getCommonIPAddress(strings));
    }
}