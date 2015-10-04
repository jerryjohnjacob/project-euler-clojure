(ns summation-of-primes.core
  (:gen-class))

(defn divisible? [num1, num2]
  (= (rem num1 num2) 0))

(defn divisible-by-list-of-primes? [num, list-of-primes]
  (let [length-of-list (count list-of-primes) half-num (/ num 2)]
    (loop [index 0]
      (if (= index length-of-list)
        false
        (do
          (let [prime-at-index (nth list-of-primes index)]
            (if (> prime-at-index half-num)
            false
            (do      
              (if (divisible? num prime-at-index)
                true
                (recur (inc index)))))))))))

(defn prime? [list-of-primes, num]
  (not (divisible-by-list-of-primes? num list-of-primes)))

(defn get-primes-under-limit [limit]
  (loop [list-of-primes [2] current-number 3]
    (if (> current-number limit)
      list-of-primes
      (do
        (if (prime? list-of-primes
                    current-number)
          (recur (conj list-of-primes current-number)
                 (+ current-number 2))
          (recur list-of-primes
                 (+ current-number 2)
                 ))))))

(defn -main
  [& args]
  (def limit 2000000)
  (println (reduce + (get-primes-under-limit limit))))
