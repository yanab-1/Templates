class Lazy_Segment {
    int n;
    int[] tree;
    int[] lazy;
    Lazy_Segment(int n, int[] arr){
        this.n = n;
        lazy = new int[4 * n];
        tree = new int[4 * n];
        build(arr, 0, n - 1, 0);
    }

    private void build(int[] arr, int l, int r, int idx) {
        if(l == r){
            tree[idx] = arr[l];
            return;
        }
        int mid = (l + r) / 2; 
        build(arr, l, mid, 2 * idx + 1);
        build(arr, mid + 1, r, 2 * idx + 2);
        tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2]; // we change logic here for MAX, MIN, GCD
    }

    private void lazy_Update(int st, int end, int idx){
        if(lazy[idx] != 0){
            tree[idx] += (end - st + 1) * lazy[idx];
            if(st != end){
                lazy[2 * idx + 1] += lazy[idx];
                lazy[2 * idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }

    public void update_Range(int l, int r, int val){
        update_Range(l, r, val, 0, n - 1, 0);
    }

    private void update_Range(int l, int r, int val, int st, int end, int idx){
        // lazy update
        lazy_Update(st, end, idx);
        // no overlap
        if(r < st || l > end){
            return;
        }
        // complete overlap
        if(l <= st && end <= r){
            tree[idx] += (end - st + 1) * val;
            if(st != end){
                lazy[2 * idx + 1] += val;
                lazy[2 * idx + 2] += val;
            }
            return;
        }
        // partial overlap
        int mid = (st + end) / 2;
        update_Range(l, r, val, st, mid, 2 * idx + 1);
        update_Range(l, r, val, mid + 1, end, 2 * idx + 2);
        tree[idx] = tree[2 * idx + 1] + tree[2 * idx + 2];
    }

    public int query(int l, int r){
        return query(l, r, 0, n - 1, 0);
    }

    private int query(int l, int r, int st, int end, int idx){
        // lazy update
        lazy_Update(st, end, idx);
        // no overlap
        if(r < st || l > end){
            return 0;
        }
        // complete overlap
        if(l <= st && end <= r){
            return tree[idx];
        }
        // partial overlap
        int mid = (st + end) / 2;
        int left = query(l, r, st, mid, 2 * idx + 1);
        int right = query(l, r, mid + 1, end, 2 * idx + 2);
        return left + right;
    }
}
