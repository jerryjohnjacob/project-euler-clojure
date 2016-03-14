(ns lattice-paths.core
  (:gen-class))

(def grid-x-size (bigint 20))
(def grid-y-size (bigint 20))

(defn iterative-factorial [n current-product]
  (if (= n 1)
    current-product
    (iterative-factorial (- n 1) (* n current-product))))

(defn factorial [n]
  (iterative-factorial n 1))

(defn unique-permutations []
  (/ (factorial (+ grid-x-size grid-y-size))
     (* (factorial grid-x-size)
        (factorial grid-y-size))))

(defn -main
  [& args]
  (println (unique-permutations)))
