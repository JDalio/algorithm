package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode
{
    GraphNode prev;
    List<GraphNode> adj;
    String color;
    int distance;
}
