class Solution {
    static final int MOD=1_000_000_007;
    public int assignEdgeWeights(int[][] edges) {
        int n=edges.length+1;
        List<Integer>[] adj=new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int child[]:edges){
            int u=child[0];
            int v=child[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        for(int i=0;i<adj.length;i++){
                    System.out.println(adj[i]);

        }   
        int depth=dfs(adj,1,-1);
        long res=1;
        for(int i=1;i<depth;i++){
            res=(res*2)%MOD;
        }
        return (int)res;

    }
    public int dfs(List<Integer>[] adj,int child,int parent){
        int depth=0;
        for(int node:adj[child]){
            if(node==parent){
                continue;
            }
            depth=Math.max(depth,1+dfs(adj,node,child));
        }
        return depth;

    }
}