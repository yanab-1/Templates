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
		rank[v] = 1;
	}

	public int find(int node) {
		if (parent[node] == node) {
			return node;
		}
		int n = find(parent[node]);
		parent[node] = n;// path Compression
		return n;

	}

	public void unionByRank(int v1, int v2) {
		int re1 = find(v1);// v1 ka re node hai
		int re2 = find(v2);// v2 ka re node hai
		if(parent[re1] == parent[re2]) return;
		else if (rank[re1] == rank[re2]){
			parent[re1] = re2;
			rank[re2]++;
		}
		else if (rank[re1] < rank[re2]) {
			parent[re1] = re2;
		} 
		else {
			parent[re2] = re1;
		}
	}

	public void unionBySize(int v1, int v2) {
		int re1 = find(v1);// v1 ka re node hai
		int re2 = find(v2);// v2 ka re node hai
		if(parent[re1] == parent[re2]) return;
		else if (size[re1] < size[re2]) {
			parent[re1] = re2;
			size[re2] += size[re1];
		} else {
			parent[re2] = re1;
			size[re1] += size[re2];
		}
	}
}