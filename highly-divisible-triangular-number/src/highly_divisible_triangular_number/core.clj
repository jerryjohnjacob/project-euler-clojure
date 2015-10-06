(ns highly-divisible-triangular-number.core
  (:gen-class))

(defn is-divisor? [main-number number-to-check]
  (== 0 (rem main-number number-to-check))
)

(defn get-divisors [number]
  (conj (for [x (range 1 (inc (/ number 2)))
              :when (is-divisor? number x)]
          x)
        number))

(defn -main
  [& args]
  (time (println (loop [current-sum 0 current-number 1]
                   (if (> (count (get-divisors current-sum)) 500)
                     current-sum
                     (recur (+ current-sum current-number) (inc current-number)))))))
