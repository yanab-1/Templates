public class ModularnCr {
    // Binary Exponentiation
    static long MOD = 1_000_000_007;
    private static long power(long a, long n){
        if(n == 0) return 1;
        long ans = power(a, n / 2);
        ans = (ans * ans) % MOD;
        if(n % 2 == 1) ans = (ans * a) % MOD;
        return ans;
    }

    private static long modInverse(long n) {
        return power(n, MOD - 2); // Fermat's Little Theorem
    }

    private static long nCr(long n, long r) {
        if (r > n) return 0;
        long num = 1;
        for (long i = 0; i < r; i++)
            num = (num * (n - i)) % MOD;

        long den = 1;
        for (long i = 1; i <= r; i++)
            den = (den * i) % MOD;

        return (num * modInverse(den)) % MOD;
    }
}
