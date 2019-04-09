package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph
{
    private List<GraphNode> nodeList;

    public Graph()
    {
        this.nodeList = new ArrayList<>();
    }

    public void bfs(GraphNode source)
    {
        Queue<GraphNode> queue = new LinkedList<>();

        for (GraphNode node : nodeList)
        {
            node.color = "white";
            node.distance = -1;
            node.prev = null;
        }

        source.color = "gray";
        source.distance = 0;
        source.prev = null;

        queue.offer(source);
        while (!queue.isEmpty())
        {
            GraphNode node = queue.poll();
            for (GraphNode v : node.adj)
            {
                if(v.color=="white")
                {
                    v.color="gray";
                    v.distance=node.distance+1;
                    v.prev=node;
                    queue.add(v);
                }
            }
            node.color="black";
        }
    }
}