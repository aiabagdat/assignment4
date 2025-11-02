package io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import common.Graph;
import java.io.File;

public class JsonGraphIO {
    public static Graph load(String path) throws Exception {
        ObjectMapper om=new ObjectMapper();
        JsonNode r=om.readTree(new File(path));
        Graph g=new Graph(r.get("n").asInt(), r.get("directed").asBoolean());
        for(var e:r.get("edges"))
            g.addEdge(e.get("u").asInt(), e.get("v").asInt(), e.get("w").asInt());
        return g;
    }
}
