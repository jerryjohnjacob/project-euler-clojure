(ns highly-divisible-triangular-number.core
  (:gen-class))

(defn is-divisor? [main-number number-to-check]
  (== 0 (rem main-number number-to-check)))

(defn square-root [num]
  (int (Math/sqrt num)))

(defn get-divisors [number]
  (flatten (distinct (for [x (range 1 (square-root number))
                           :when (is-divisor? number x)]
                       [x (/ number x)]))))

(defn -main
  [& args]
  (time (println (loop [current-sum 0 current-number 1]
                   (if (> (count (get-divisors current-sum)) 500)
                     current-sum
                     (recur (+ current-sum current-number) (inc current-number)))))))
