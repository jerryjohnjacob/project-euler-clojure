(ns ten-thousand-and-first-prime.core
  (:gen-class))

(defn divisible? [num1, num2]
  (= (rem num1 num2) 0)
  )

(defn prime? [list-of-primes, num]
  (if (even? num)
    false
    (not (some true? (map divisible? (for [x list-of-primes] num) list-of-primes)))
    )
  )

(defn -main
  [& args]
  (def starter-list [2])
  (def starter-count 1)
  (def limit 10001)
  (println (loop [list-of-primes starter-list current-number (inc (last starter-list))]
             (if (= (count list-of-primes) limit)
               (last list-of-primes)
               (do
                 (if (prime? list-of-primes
                             current-number)
                   (recur (conj list-of-primes current-number)
                          (inc current-number)
                          )
                   (recur list-of-primes
                          (inc current-number)
                          )
                   )
                 )
               )
             ))
  )
