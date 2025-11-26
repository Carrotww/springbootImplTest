
import java.util.*;

public class AlgorithmTemplate {

    /* ===========================
     * 1. 누적합 / 차이 배열 계열
     * ===========================
     */

    // 1D Prefix Sum
    // input : 원본 배열 ary
    // output: ps 배열 (길이 n+1), ps[i] = ary[0] ~ ary[i-1] 의 합
    // 사용처: 구간합 O(1)로 구할 때, 누적 카운트 등
    static int[] prefixSum1D(int[] ary) {
        // TODO: ps[0] = 0 으로 두고, i=1..n 에 대해 ps[i] 채우기

        int n = ary.length;
        int[] ps = new int[n + 1];

        ps[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            ps[i] = ps[i - 1] + ary[i - 1];
        }

        return ps;
    }

    // 2D Prefix Sum
    // input : board[r][c]
    // output: ps[r+1][c+1], ps[i][j] = (0,0) ~ (i-1, j-1) 합
    // 사용처: 직사각형 구간합 O(1)
    static int[][] prefixSum2D(int[][] board) {
        // TODO: ps 배열 만들고, 행/열 돌면서 점화식으로 채우기
        int r = board.length;
        int c = board[0].length;

        int[][] ps = new int[r + 1][c + 1];

        for (int i = 1; i < r + 1; i++) {
            for (int j = 1; j < c + 1; j++) {
                ps[i][j] = board[i - 1][j - 1] + ps[i][j - 1] + ps[i - 1][j] - ps[i - 1][j - 1];
            }
        }

        return ps;
    }

    // 1D Difference Array (차이 배열)
    // input : 길이 n, 구간 [l, r] 에 +val 를 여러 번 더해야 하는 경우
    // 사용처: range add 쿼리 여러 개 -> 마지막에 prefixSum 한 번

    // 여러 번의 [l, r] 구간 +val 업데이트를
    // 차이 배열(diff)에 모아두고,
    // 마지막에 prefix sum 으로 최종 배열을 만든다.
    static int[] differenceArray(int n, int[][] updates) {
        // updates: {l, r, val} 의 리스트라고 가정
        // TODO: diff 배열 만들고, diff[l] += val, diff[r+1] -= val 후 prefix

        if (n == 0) return new int[0];

        int[] diff = new int[n];

        for (int[] update : updates) {
            int l = update[0];
            int r = update[1];
            int val = update[2];

            diff[l] += val;

            if (r + 1 < n) {
                diff[r + 1] -= val;
            }
        }

        int[] ary = new int[n];

        ary[0] = diff[0];

        for (int i = 1; i < n; i++) {
            ary[i] = diff[i] + ary[i - 1];
        }

        /*
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += diff[i];
            ary[i] = cur;
        }
        */

        return ary;
    }


    /* ===========================
     * 2. 그래프: BFS / DFS / Grid
     * ===========================
     */

    // 인접 리스트 DFS (재귀)
    // input : n, graph(1-index or 0-index), start
    // output: visited 배열 or dfs 순서 리스트
    static void dfsList(int n, List<Integer>[] graph, int start, boolean[] visited) {
        // TODO:
        // 1. 현재 노드 방문 처리
        // 2. 인접 노드 돌면서 방문 안했으면 재귀 호출

        if (visited[start]) return;

        visited[start] = true;

        for (int next : graph[start]) {
            if (!visited[next]) {
                dfsList(n, graph, next, visited);
            }
        }

        return;
    }

    // 인접 리스트 BFS
    // input : n, graph, start
    // output: dist 배열 (start로부터의 거리, 도달 불가 == -1 같은 값)
    static int[] bfsList(int n, List<Integer>[] graph, int start) {
        // TODO:
        // 1. 큐 준비, dist 배열 전부 -1 로 초기화
        // 2. start 를 큐에 넣고, dist[start] = 0
        // 3. 큐에서 하나씩 빼면서 인접 노드 확인, 처음 방문 시 dist 채우고 큐에 추가
        return null;
    }

    // Grid BFS (4방향)
    // input : board(r x c), 시작 (sr, sc)
    // output: dist[r][c] or 방문 여부
    static int[][] bfsGrid(char[][] board, int sr, int sc) {
        // TODO:
        // 1. dr/dc = { {1,0}, {-1,0}, {0,1}, {0,-1} }
        // 2. 범위 체크 + 벽/방문 여부 체크
        // 3. dist 배열 or visited 배열 채우기
        return null;
    }

    // 트리 DFS: 서브트리 크기 구하기
    // input : n, tree(무방향 그래프, 1을 root라고 가정)
    // output: subtreeSize[u] = u를 루트로 하는 서브트리에 포함된 노드 수
    static int dfsSubtreeSize(int cur, int parent, List<Integer>[] tree, int[] subtreeSize) {
        // TODO:
        // 1. 현재 노드 크기 1로 시작
        // 2. 자식들에 대해 재귀 호출, 돌아온 값 더하기
        // 3. subtreeSize[cur] 에 최종 값 저장, 리턴
        return 0;
    }

        /* ===========================
     * 1. MST - Prim 알고리즘
     * ===========================
     */

    // Prim MST (PriorityQueue 사용, 인접 리스트)
    // input :
    //   n : 정점 개수 (0 ~ n-1)
    //   graph[u] : {v, w} 형태의 간선 (무방향, 양수 가중치)
    // output :
    //   MST의 총 가중치 (연결 안돼 있으면 -1 같은거 리턴)
    static long primMST(int n, List<int[]>[] graph) {
        // TODO:
        // 1. visited[n] = false 로 초기화
        // 2. pq : (weight, node) 오름차순
        // 3. 시작점을 0이라고 치고, pq에 (0, 0) 넣기
        // 4. pq에서 하나씩 꺼내서:
        //    - 이미 방문이면 continue
        //    - 방문 처리하고, 그 간선 가중치 더하기
        //    - 해당 정점에서 뻗어나가는 간선들 중
        //      아직 방문 안 된 정점들 pq에 push
        // 5. 방문한 정점 수가 n개가 아니면 MST 불가
        return 0L;
    }


    /* ===========================
     * 2. MST - Kruskal 알고리즘
     * ===========================
     */

    // Kruskal MST
    // input :
    //   n : 정점 개수
    //   edges : {u, v, w} 리스트 (무방향)
    // output :
    //   MST 총 가중치 (연결 안돼 있으면 -1 등)
    static long kruskalMST(int n, int[][] edges) {
        // TODO:
        // 1. edges를 w 기준 오름차순 정렬
        // 2. Union-Find 준비
        // 3. 작은 간선부터 차례대로:
        //    - union(u, v)가 성공(=원래 다른 집합)이면
        //      -> MST에 포함, weight 더하기, 사용한 간선 수 증가
        // 4. 사용한 간선 수가 n-1이 아니면 MST 불가
        return 0L;
    }

    static class UF {
        int[] parent, rank;
        UF(int n) {
            // TODO: parent[i]=i, rank[i]=0
        }

        int find(int x) {
            // TODO: path compression
            return 0;
        }

        boolean union(int a, int b) {
            // TODO: root 다르면 붙이고 true, 같으면 false
            return false;
        }
    }


    /* ===========================
     * 3. Bellman-Ford
     *    (단일 시작점 최단거리, 음수 간선 가능)
     * ===========================
     */

    // Bellman-Ford
    // input :
    //   n : 정점 개수
    //   edges : {u, v, w} 방향 간선 리스트
    //   start : 시작 정점
    // output :
    //   dist 배열 (음의 사이클에 의해 더 줄어들 수 있으면 표시 필요)
    static long[] bellmanFord(int n, int[][] edges, int start) {
        // TODO:
        // 1. dist 모두 INF, dist[start] = 0
        // 2. (n-1)번:

        //    for (각 간선 u,v,w)
        //      dist[v] > dist[u] + w 이면 갱신

        // 3. 한 번 더 전체 간선 돌면서
        //    여전히 갱신이 일어나면 음수 사이클 영향 있음을 체크
        return null;
    }


    /* ===========================
     * 4. Floyd-Warshall
     *    (모든 쌍 최단거리, n <= ~200 정도에서)
     * ===========================
     */

    // Floyd-Warshall
    // input :
    //   n : 정점 개수
    //   dist[i][j] : 초기 거리 (없으면 INF, 자기 자신은 0)
    // output :
    //   dist[i][j] = i->j 최단거리 (음수 사이클 체크는 별도)
    static void floydWarshall(int n, long[][] dist) {
        // TODO:
        // for k=0..n-1
        //   for i=0..n-1
        //     for j=0..n-1
        //       dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
    }

    /* ===========================
     * 3. 최단거리 / 위상정렬 / 유니온파인드
     * ===========================
     */

    // Dijkstra (가중치 양수, 인접 리스트)
    // graph[u] 에는 {v, w} 형태의 int[2] 배열 들어있다고 가정
    // input : n, graph, start
    // output: dist[1..n], 도달 불가 시 INF 같은 큰 값
    static int[] dijkstra(int n, List<int[]>[] graph, int start) {
        // TODO:
        // 1. dist 배열 모두 INF, dist[start] = 0
        // 2. (dist, node) 형태 min-heap(PriorityQueue) 사용
        // 3. pq에서 꺼낸 값이 이미 더 큰 거리면 skip
        // 4. 인접 간선 relaxed
        return null;
    }

    // Topological Sort (Kahn)
    // input : n, graph(방향그래프), indegree 배열
    // output: 위상 정렬 순서 리스트 (사이클 있으면 사이즈 < n)
    static List<Integer> topoSort(int n, List<Integer>[] graph, int[] indegree) {
        // TODO:
        // 1. indegree 0 인 것들 큐에 넣기
        // 2. 큐에서 하나씩 꺼내 result에 넣고, 나가는 간선들 indegree 줄이기
        // 3. indegree 0 되면 큐에 넣기
        return null;
    }

    // Union-Find (Disjoint Set)
    // 사용처: 연결 여부, 사이클 체크, 컴포넌트 개수 등
    static class UnionFind {
        int[] parent;
        int[] rank; // or size

        UnionFind(int n) {
            // TODO: parent[i] = i, rank[i] = 1 초기화
        }

        int find(int x) {
            // TODO: 경로 압축 (path compression)
            return 0;
        }

        boolean union(int a, int b) {
            // TODO:
            // 1. rootA, rootB 찾기
            // 2. 같으면 false 리턴 (이미 같은 집합)
            // 3. rank/size 기준으로 작은 쪽을 큰 쪽 밑으로
            // 4. true 리턴
            return false;
        }

        boolean isConnected(int a, int b) {
            // TODO: find(a) == find(b)
            return false;
        }
    }


    /* ===========================
     * 4. 백트래킹(조합/순열/부분집합)
     * ===========================
     */

    // 조합 (n개 중 k개 뽑기)
    // input : ary, k
    // output: result 리스트에 모든 조합 추가
    static void combine(int[] ary, int k, int start, List<Integer> path, List<List<Integer>> result) {
        // TODO:
        // 1. path.size() == k 이면 result에 copy 추가 후 return
        // 2. i = start ~ ary.length-1 돌면서
        //    - path에 ary[i] 추가 -> 재귀 -> 다시 제거
    }

    // 순열 (ary 전체 순열)
    // input : ary
    // output: result 리스트에 모든 순열 추가
    static void permute(int[] ary, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        // TODO:
        // 1. path.size() == ary.length 이면 result에 추가 후 return
        // 2. i=0..n-1 돌면서 사용 안된 것들 골라서 path에 넣고 재귀
    }

    // 부분집합 (power set)
    // input : ary
    // output: result 리스트에 모든 부분집합 추가
    static void subsets(int[] ary, int idx, List<Integer> path, List<List<Integer>> result) {
        // TODO:
        // 1. idx == ary.length 이면 result에 path copy 추가 후 return
        // 2. 현재 원소를 "안 고르는" 경우 재귀
        // 3. "고르는" 경우 path에 넣고 재귀, 이후 제거
    }


    /* ===========================
     * 5. DP 패턴 (1D, 2D, 비트마스크)
     * ===========================
     */

    // 1D DP 기본형
    // 예: dp[i] = dp[i-1] + something, or 최댓값/최솟값
    // input : n, 필요하면 ary 같이
    // output: dp[n] or 배열 전체
    static int dp1D(int[] ary) {
        // TODO:
        // 1. n = ary.length
        // 2. dp 배열 만들고 초기값 설정
        // 3. for i=1..n-1: 점화식 채우기
        return 0;
    }

    // 2D DP 기본형
    // 예: 격자에서 오른쪽/아래로만 이동하는 최단경로/최댓값 등
    // input : board, r, c
    // output: dp[r-1][c-1] 등
    static int dp2D(int[][] board) {
        // TODO:
        // 1. dp[i][j] 를 정의 (예: (0,0)에서 (i,j)까지 최댓값)
        // 2. base case 설정, (0,0) 등
        // 3. for i, for j 돌면서 dp[i][j] 채우기
        return 0;
    }

    // Bitmask DP (부분집합 상태 DP)
    // 예: TSP, 모든 도시 방문, or "이미 고른 집합"을 상태로 쓰는 DP
    // input : n (최대 20 정도), cost[][] 등
    // output: dp[마지막 상태]
    static int bitmaskDP(int n, int[][] cost) {
        // TODO:
        // 1. 상태: mask(방문 집합), last(마지막으로 방문한 곳)
        // 2. dp[mask][last] 정의
        // 3. mask 를 작은 것부터 큰 것 순서대로 돌면서 상태 전이
        return 0;
    }


    /* ===========================
     * 6. 수학 / 비트 / 이진 탐색 응용
     * ===========================
     */

    // GCD / LCM
    static int gcd(int a, int b) {
        // TODO: 유클리드 호제법
        return 0;
    }

    static int lcm(int a, int b) {
        // TODO: a / gcd(a,b) * b
        return 0;
    }

    // 빠른 거듭제곱 (Binary Exponentiation)
    // input : base, exp
    // output: base^exp (mod 없다고 가정, 필요하면 mod 추가)
    static long fastPow(long base, long exp) {
        // TODO:
        // 1. 반복문 또는 재귀로 exp를 이진수로 보면서 제곱/곱셈
        return 0L;
    }

    // 진법 변환: 10진수 -> 2진수 문자열
    static String toBinaryString(int x) {
        // TODO:
        // 1. x가 0이면 "0"
        // 2. while (x > 0) -> (x % 2)를 StringBuilder에 append
        // 3. 뒤집어서 리턴
        return null;
    }

    // 진법 변환: (예) 10진수 -> k진수 문자열
    static String toBaseKString(long x, int k) {
        // TODO: 위와 비슷하게 하되, 0~9, A~Z 등으로 문자 매핑
        return null;
    }

    // 비트마스크: 특정 mask의 부분집합들 모두 순회
    // input : mask
    static void iterateSubmasks(int mask) {
        // TODO:
        // for (int sub = mask; sub > 0; sub = (sub - 1) & mask) 패턴 사용
    }

    // Parametric Search (답에 대한 이진탐색)
    // input : 답의 범위 [lo, hi], check(mid)가 "조건 만족 여부"를 반환
    // output: 조건을 만족하는 최소/최대 값
    static int binarySearchOnAnswer(int lo, int hi) {
        // TODO:
        // while (lo < hi) {
        //   mid = (lo+hi)/2;
        //   if (check(mid)) hi = mid;  // or lo = mid+1;
        //   else lo = mid+1;          // or hi = mid;
        // }
        return 0;
    }


    /* ===========================
     * 7. Two Pointers / Sliding Window 응용
     * ===========================
     */

    // Two Pointer on Sorted Array (음수/양수 섞인 경우, 합 = target)
    // input : 정렬된 ary, target
    // output: target을 만드는 pair 개수 or 존재 여부
    static int twoPointerSorted(int[] ary, int target) {
        // TODO:
        // 1. left=0, right=n-1
        // 2. sum = ary[left] + ary[right]
        // 3. sum < target -> left++
        //    sum > target -> right--
        //    sum == target -> 정답 처리 후 중복 처리
        return 0;
    }

    // Sliding Window - 최소 길이 subarray, 조건: 합 >= target
    // input : ary (양수 가정), target
    // output: 조건 만족하는 subarray 최소 길이, 없으면 0 또는 INF
    static int minLengthSubarraySumAtLeast(int[] ary, int target) {
        // TODO:
        // 1. start=0, sum=0, answer=큰 값
        // 2. end를 0..n-1 로 증가시키며 sum += ary[end]
        // 3. while(sum >= target) 에서 answer 갱신, sum -= ary[start++]
        return 0;
    }
}
