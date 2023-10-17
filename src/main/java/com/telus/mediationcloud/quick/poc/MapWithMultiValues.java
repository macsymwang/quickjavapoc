package com.telus.mediationcloud.quick.poc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MapWithMultiValues implements CommandLineRunner {
    @Override

    public void run(String... args) throws Exception {
        Map<String, ArrayList<String>> multiValueMap = new HashMap<String, ArrayList<String>>();
        multiValueMap.put("ford", new ArrayList<String>());
        multiValueMap.put("dodge", new ArrayList<String>());
        multiValueMap.get("ford").add("GT");
        multiValueMap.get("ford").add("Mustang Mach-E");
        multiValueMap.get("ford").add("Pantera");
        multiValueMap.get("dodge").add("Pantera");

        System.out.println(multiValueMap.get("ford"));

        MultiValuedMap<String, String> multiMap = new ArrayListValuedHashMap<String, String>();

        multiMap.put("ford", "Mustang Mach-E");
        multiMap.put("ford", "GT");
        multiMap.put("ford", "Pantera");
        multiMap.put("dodge", "Pantera");

        System.out.println(multiMap.get("ford"));
    }

}
