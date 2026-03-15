public class BinaryExponentiation {
    // Binary Exponentiation
    long MOD = 1_000_000_007;
    private long power(long a, long n){
        if(n == 0) return 1;
        long ans = power(a, n / 2);
        ans = (ans * ans) % MOD;
        if(n % 2 == 1) ans = (ans * a) % MOD;
        return ans;
    }
}
