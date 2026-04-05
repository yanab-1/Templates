public class DisJoinSet {
    int[] parent, size, rank;

    public DisJoinSet(int n){
        parent = new int[n];
        size = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) create(i);
    }

    public void create(int v) {
        parent[v] = v;
        size[v] = 1;
        rank[v] = 0; // better
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // path compression
        }
        return parent[node];
    }

    public void unionByRank(int v1, int v2) {
        int re1 = find(v1);
        int re2 = find(v2);

        if(re1 == re2) return;

        if (rank[re1] < rank[re2]) {
            parent[re1] = re2;
        } 
        else if (rank[re1] > rank[re2]) {
            parent[re2] = re1;
        } 
        else {
            parent[re2] = re1;
            rank[re1]++;
        }
    }

    public void unionBySize(int v1, int v2) {
        int re1 = find(v1);
        int re2 = find(v2);

        if(re1 == re2) return;

        if (size[re1] < size[re2]) {
            parent[re1] = re2;
            size[re2] += size[re1];
        } else {
            parent[re2] = re1;
            size[re1] += size[re2];
        }
    }
}
